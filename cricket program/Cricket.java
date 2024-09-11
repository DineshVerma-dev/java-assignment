import java.util.Scanner;

public class Cricket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balls = 6;
        int fours = 0, sixes = 0, dotBalls = 0, wickets = 0;
        int b1Runs = 0, b2Runs = 0;
        boolean strike = true;

        System.out.println("Enter the outcome for each ball (-1 for wicket, 0 for a dot ball, 1-6 for runs):");
        for (int i = 1; i <= balls; i++) {
            System.out.print("Ball " + i + ": ");
            int outcome = sc.nextInt();
            switch (outcome) {
                case 4:
                    fours++;
                    if (strike) {
                        b1Runs += 4;
                    } else {
                        b2Runs += 4;
                    }
                    break;
                case 6:
                    sixes++;
                    if (strike) {
                        b1Runs += 6;
                    } else {
                        b2Runs += 6;
                    }
                    break;
                case 0:
                    dotBalls++;
                    break;
                case -1:
                    wickets++;
                    break;
                case 1:
                case 2:
                case 3:
                    if (strike) {
                        b1Runs += outcome;
                    } else {
                        b2Runs += outcome;
                    }
                    // Change strike if runs are odd
                    if (outcome % 2 == 1) {
                        strike = !strike;
                    }
                    break;
                default:
                    System.out.println("Please enter a valid outcome.");
            }
        }

        // Display the results
        System.out.println("\nSummary:");
        System.out.println("Number of 4's: " + fours);
        System.out.println("Number of 6's: " + sixes);
        System.out.println("Number of dot balls: " + dotBalls);
        System.out.println("Number of wickets: " + wickets);
        System.out.println("Runs by Batsman 1: " + b1Runs);
        System.out.println("Runs by Batsman 2: " + b2Runs);

        sc.close();
    }
}
