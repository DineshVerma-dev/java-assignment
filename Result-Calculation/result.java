import java.util.Scanner;

public class Result {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create table for main subjects and internal subjects
        Table table = new Table();
        table.createTable(sc);

        sc.close();
    }
}

class Subject {
    String subname;
    int totalMarks;
    int marksObtained;
    String result;

    public Subject(String subname, int totalMarks) {
        this.subname = subname;
        this.totalMarks = totalMarks;
    }

    public void calculateResult() {
        if (marksObtained < 0.4 * totalMarks) {
            result = "F"; // Fail
        } else {
            result = "P"; // Pass
        }
    }

    public void displaySubject() {
        System.out.println(subname + "        " + totalMarks + "       " + marksObtained + "      " + result);
    }
}

class Table {
    Subject[] subjects;

    public Table() {
        subjects = new Subject[] {
            // Main subjects
            new Subject("EM-II", 80),
            new Subject("EP-II", 60),
            new Subject("EC-II", 60),
            new Subject("EG   ", 60),
            new Subject("CP   ", 60),

            // Internal subjects
            new Subject("maths", 20),
            new Subject("physics", 15),
            new Subject("chemistry", 15),
            new Subject("graphics", 15),
            new Subject("c programming", 15)
        };
    }

    public void createTable(Scanner sc) {
        System.out.println(" Subject        Total Marks     Marks Obtained     Pass/Fail ");
        System.out.println("-".repeat(90));

        int internalKTs = 0;
        int externalKTs = 0;

        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter marks for " + subjects[i].subname + ": ");
            subjects[i].marksObtained = sc.nextInt();
            subjects[i].calculateResult();
            subjects[i].displaySubject();

            // Count internal KTs (for internal subjects with total marks less than or equal to 20)
            if (subjects[i].totalMarks <= 20 && subjects[i].result.equals("F")) {
                internalKTs++;
            }
            // Count external KTs (for external subjects with total marks greater than 20)
            if (subjects[i].totalMarks > 20 && subjects[i].result.equals("F")) {
                externalKTs++;
            }
        }

        int totalKTs = internalKTs + externalKTs;

        // Display the total number of KTs
        System.out.println("\nTotal Internal KTs: " + internalKTs);
        System.out.println("Total External KTs: " + externalKTs);
        System.out.println("Total Number of KTs: " + totalKTs);

        // Check for year drop condition
        if (totalKTs > 9) {
            System.out.println("Year drop");
        }
    }
}
