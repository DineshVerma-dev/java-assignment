import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SpaceInvaders extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private ArrayList<Bullet> bullets; // List to hold multiple bullets
    private ArrayList<Enemy> enemies;
    private int score;
    private Random random; // Random generator for enemy spawning

    public SpaceInvaders() {
        setBackground(Color.BLACK);
        setFocusable(true);
        player = new Player(300, 550);
        bullets = new ArrayList<>(); // Initialize bullet list
        enemies = new ArrayList<>();
        score = 0;
        random = new Random(); // Initialize random number generator

        // Initialize the timer
        timer = new Timer(20, this);
        timer.start();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.move(-10);
                break;
            case KeyEvent.VK_RIGHT:
                player.move(10);
                break;
            case KeyEvent.VK_SPACE:
                bullets.add(new Bullet(player.getX() + 10, player.getY())); // Allow shooting bullets
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Create and fill the gradient background
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, 0, getHeight(), Color.GRAY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        player.draw(g);

        // Draw all bullets
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        // Draw all enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // Draw the score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    private void createEnemies(int count) {
        int frameWidth = getWidth(); // Get the current width of the frame
        for (int i = 0; i < count; i++) {
            if (frameWidth > 50) { // Ensure the width is greater than the enemy size
                int x = random.nextInt(frameWidth - 50); // Use frameWidth for random x position
                int shapeType = random.nextInt(3); // Randomly select shape type
                enemies.add(new Enemy(x, 50, shapeType)); // Create enemy with random shape
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update bullets
        updateBullets();

        // Move enemies and check for game over condition
        moveEnemies();

        // Spawn new enemies periodically
        if (enemies.size() < 10) { // Limit the number of enemies
            createEnemies(1); // Add one new enemy
        }

        repaint();
    }

    private void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.move();
            if (bullet.getY() < 0) {
                bullets.remove(i);
                i--; // Adjust index after removal
            } else {
                checkBulletCollision(bullet, i);
            }
        }
    }

    private void checkBulletCollision(Bullet bullet, int bulletIndex) {
        for (int j = 0; j < enemies.size(); j++) {
            if (bullet.getBounds().intersects(enemies.get(j).getBounds())) {
                bullets.remove(bulletIndex); // Bullet hits the enemy
                enemies.remove(j); // Remove the enemy
                score += 10; // Increase score
                break; // Exit loop after hitting one enemy
            }
        }
    }

    private void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.move();
            if (enemy.getY() > getHeight()) {
                // Handle game over condition
                JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        SpaceInvaders game = new SpaceInvaders();
        frame.add(game);
        frame.setSize(800, 600); // Set frame width to full
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Create initial enemies after setting the frame size
        game.createEnemies(5); // Create 5 enemies at the start with the frame width
    }
}

// Player class
class Player {
    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx) {
        x += dx;
        x = Math.max(0, Math.min(x, 780)); // Prevent moving out of bounds
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 20, 20);
    }
}

// Bullet class
class Bullet {
    private int x, y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= 5; // Move bullet up
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 10);
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 10);
    }
}

// Enemy class
class Enemy {
    private int x, y;
    private int shapeType; // 0: rectangle, 1: circle, 2: triangle

    public Enemy(int x, int y, int shapeType) {
        this.x = x;
        this.y = y;
        this.shapeType = shapeType; // Set the shape type
    }

    public void move() {
        y += 1; // Move down
    }

    public int getY() {
        return y; // Return the current y position
    }

    public Rectangle getBounds() {
        // Define bounds for collision detection based on shape
        return new Rectangle(x, y, 30, 30); // Simplified for now
    }

    public void draw(Graphics g) {
        switch (shapeType) {
            case 0: // Rectangle
                g.setColor(Color.RED);
                  g.fillRect(x, y, 30, 30);
                break;
            case 1: // Circle
                g.setColor(Color.BLUE);
                g.fillOval(x, y, 30, 30);
                break;
            case 2: // Triangle
                g.setColor(Color.MAGENTA);
                int[] xPoints = {x, x + 15, x + 30};
                int[] yPoints = {y + 30, y, y + 30};
                g.fillPolygon(xPoints, yPoints, 3);
                break;
        }
    }
}
