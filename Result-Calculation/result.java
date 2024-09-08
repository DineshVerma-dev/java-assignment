import java.util.Scanner;

public class Result {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create table for students including term works
        Table student1 = new Table();
        student1.createTable(sc);

        Table student2 = new Table();
        student2.createTable(sc);

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
            // External subjects
            new Subject("EM-II", 80),
            new Subject("EP-II", 60),
            new Subject("EC-II", 60),
            new Subject("EG", 60),
            new Subject("CP", 60),
            new Subject("PCE-I", 40),

            // Internal subjects
            new Subject("maths", 20),
            new Subject("physics", 15),
            new Subject("chemistry", 15),
            new Subject("graphics", 15),
            new Subject("c programming", 15),
            new Subject("PCE-I internal", 15),

            // Term work subjects
            new Subject("EM-II TW", 25),
            new Subject("EP-II TW", 25),
            new Subject("EC-II TW", 25),
            new Subject("EG TW", 25),
            new Subject("CP TW", 25),
            new Subject("PCE-I TW", 25),
            new Subject("Workshop", 50),
        };
    }

    public void createTable(Scanner sc) {
        String name;
        System.out.print("Enter the name of the student: ");
        name = sc.nextLine();

        System.out.println("\nSubject        Total Marks     Marks Obtained     Pass/Fail");
        System.out.println("-".repeat(90));

        int internalKTs = 0;
        int externalKTs = 0;
        int termWorkKTs = 0;
        int totalMarksOfStudent = 0;

        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter marks for " + subjects[i].subname + ": ");
            subjects[i].marksObtained = sc.nextInt();
            subjects[i].calculateResult();
            subjects[i].displaySubject();

            // Count internal KTs (for internal subjects with total marks less than or equal to 20)
            if (subjects[i].totalMarks <= 20 && subjects[i].result.equals("F")) {
                internalKTs++;
            }
            // Count external KTs (for external subjects with total marks greater than 20 and not term work)
            if (subjects[i].totalMarks > 20 && subjects[i].totalMarks != 25 && subjects[i].result.equals("F")) {
                externalKTs++;
            }
            // Count term work KTs (for subjects with total marks of 25)
            if (subjects[i].totalMarks == 25 && subjects[i].result.equals("F")) {
                termWorkKTs++;
            }

            totalMarksOfStudent += subjects[i].marksObtained;
        }

        int totalKTs = internalKTs + externalKTs;

        // Display the total number of KTs
        System.out.println("\nResults for " + name);
        System.out.println("Total Internal KTs: " + internalKTs);
        System.out.println("Total External KTs: " + externalKTs);
        System.out.println("Total Term Work KTs: " + termWorkKTs);
        System.out.println("Total Marks obtained: " + totalMarksOfStudent);

        // Check for year drop condition
        if (totalKTs > 9) {
            System.out.println("Year drop");
        }
        System.out.println("-".repeat(90));
    }
}
