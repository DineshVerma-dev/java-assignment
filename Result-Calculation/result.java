import java.util.Scanner;

public class Result {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Table student1 = new Table();
        student1.createTable(sc);

        sc.close();
    }
}

class Subject {
    String subname;
    int totalMarks;
    int marksObtained;
    String result;
    double percentage;
    int pointer;
    double credit;
    String grade;

    int combinedMarks; // combined marks of external + internal
    int combinedTotal; // combined total marks of external + internal

    public Subject(String subname, int totalMarks, double credit) {
        this.subname = subname;
        this.totalMarks = totalMarks;
        this.credit = credit;
    }

    public void calculateResult() {
        percentage = ((double) combinedMarks / combinedTotal) * 100;
        if (combinedMarks < 0.4 * combinedTotal) {
            result = "F"; // Fail
            pointer = 0;
        } else {
            result = "P"; // Pass
            calculatePointer();
        }
    }

    public void calculatePointer() {
        if (percentage >= 80) {
            pointer = 10;
            grade = "O";
        } else if (percentage >= 75 && percentage <= 79.99) {
            pointer = 9;
            grade = "A";
        } else if (percentage >= 70 && percentage <= 74.99) {
            pointer = 8;
            grade = "B";
        } else if (percentage >= 60 && percentage <= 69.99) {
            pointer = 7;
            grade = "C";
        } else if (percentage >= 50 && percentage <= 59.99) {
            pointer = 6;
            grade = "D";
        } else if (percentage >= 45 && percentage <= 49.99) {
            pointer = 5;
            grade = "E";
        } else if (percentage >= 40 && percentage <= 44.99) {
            pointer = 4;
            grade = "P";
        } else {
            pointer = 0; // Fail case
            grade = "F";
        }
    }

    private double roundToThreeDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public void displaySubject() {
        double roundedPercentage = roundToThreeDecimal(percentage);
        System.out.println("-".repeat(110));
        System.out.println(subname + "                 " + combinedTotal + "                 " + combinedMarks
                + "                 " + grade + "                 " + roundedPercentage + "                 "
                + pointer);
    }
}

class Table {
    Subject[] externalSubjects;
    Subject[] internalSubjects;
    Subject[] termWorkSubjects;

    public Table() {
        // External subjects
        externalSubjects = new Subject[] {
                new Subject(" EM-II     ", 80, 4),
                new Subject(" EP-II     ", 60, 2),
                new Subject(" EC-II     ", 60, 2),
                new Subject(" EG        ", 60, 2),
                new Subject(" CP        ", 60, 2),
                new Subject(" PCE-I     ", 40, 2),
        };

        // Internal subjects
        internalSubjects = new Subject[] {
                new Subject(" maths     ", 20, 0),
                new Subject(" physics   ", 15, 0),
                new Subject(" chemistry ", 15, 0),
                new Subject(" graphics  ", 15, 0),
                new Subject(" c programm", 15, 0),
                new Subject(" PCE-Iinter", 15, 0),
        };

        // Term Work subjects
        termWorkSubjects = new Subject[] {
                new Subject(" EM-II TW  ", 25, 1),
                new Subject(" EP-II TW  ", 25, 0.5),
                new Subject(" EC-II TW  ", 25, 0.5),
                new Subject(" EG TW     ", 25, 2),
                new Subject(" CP TW     ", 25, 1),
                new Subject(" PCE-I TW  ", 25, 1),
                new Subject(" Workshop  ", 50, 1),
        };
    }

    public void createTable(Scanner sc) {
        String name;
        System.out.print("Enter the name of the student: ");
        name = sc.nextLine();

        int internalKTs = 0;
        int externalKTs = 0;
        int termWorkKTs = 0;
        int totalMarksOfStudent = 0;
        int totalCredits = 0;
        int obtainedCredited = 0; // To store the sum of (pointer * credit)
        int totalMarksOutoff = 0;
        boolean hasFailed = false;
        int totalKTs;

        System.out.println("-".repeat(110));

        // External and internal subjects
        for (int i = 0; i < externalSubjects.length; i++) {
            System.out.print("Enter marks for External " + externalSubjects[i].subname + ": ");
            externalSubjects[i].marksObtained = sc.nextInt();

            System.out.print("Enter marks for Internal " + internalSubjects[i].subname + ": ");
            internalSubjects[i].marksObtained = sc.nextInt();

            // Combine internal and external marks and total
            externalSubjects[i].combinedMarks = externalSubjects[i].marksObtained + internalSubjects[i].marksObtained;
            externalSubjects[i].combinedTotal = externalSubjects[i].totalMarks + internalSubjects[i].totalMarks;

            externalSubjects[i].calculateResult();

            // Checking if failed
            if (externalSubjects[i].combinedMarks < 0.4 * externalSubjects[i].combinedTotal) {
                hasFailed = true;
                if (externalSubjects[i].totalMarks > 20)
                    externalKTs++;
                else
                    internalKTs++;
            }

            if (externalSubjects[i].credit > 0) {
                obtainedCredited += externalSubjects[i].pointer * externalSubjects[i].credit;
                totalCredits += externalSubjects[i].credit;
            }

            totalMarksOfStudent += externalSubjects[i].combinedMarks;
            totalMarksOutoff += externalSubjects[i].combinedTotal;
        }

        // Term work subjects
        for (int i = 0; i < termWorkSubjects.length; i++) {
            System.out.print("Enter marks for Term Work " + termWorkSubjects[i].subname + ": ");
            termWorkSubjects[i].marksObtained = sc.nextInt();
            termWorkSubjects[i].combinedMarks = termWorkSubjects[i].marksObtained;
            termWorkSubjects[i].combinedTotal = termWorkSubjects[i].totalMarks;

            termWorkSubjects[i].calculateResult();

            if (termWorkSubjects[i].marksObtained < 0.4 * termWorkSubjects[i].totalMarks) {
                hasFailed = true;
                termWorkKTs++;
            }

            if (termWorkSubjects[i].credit > 0) {
                obtainedCredited += termWorkSubjects[i].pointer * termWorkSubjects[i].credit;
                totalCredits += termWorkSubjects[i].credit;
            }

            totalMarksOfStudent += termWorkSubjects[i].marksObtained;
            totalMarksOutoff += termWorkSubjects[i].totalMarks;
        }

        totalKTs = internalKTs + externalKTs + termWorkKTs;
        int yearDropconditionKTs = internalKTs + externalKTs;

        System.out.println("-".repeat(110));
        System.out.println(
                "\nSubject             Combined Total        Combined Marks         Grades         Percentage        Pointer");
        for (int i = 0; i < externalSubjects.length; i++) {
            externalSubjects[i].displaySubject();
        }
        for (int i = 0; i < termWorkSubjects.length; i++) {
            termWorkSubjects[i].displaySubject();
        }

        System.out.println("-".repeat(110));

        System.out.println("     ");
        System.out.println("\nResults for        " + name);
        System.out.println("     ");
        System.out.println("Total Internal KTs:  " + internalKTs);
        System.out.println("     ");
        System.out.println("Total External KTs:  " + externalKTs);
        System.out.println("     ");
        System.out.println("Total Term Work KTs: " + termWorkKTs);
        System.out.println("     ");
        System.out.println("Total Marks obtained:" + totalMarksOfStudent);
        System.out.println("     ");
        System.out.println("Total Marks :        " + totalMarksOutoff);
        System.out.println("     ");
        System.out.println("Total KTs:           " + totalKTs);
        System.out.println("     ");

        if (yearDropconditionKTs > 9) {
            System.out.println("Year drop");
        }

        if (hasFailed) {
            System.out.println("Unsuccessful");
            System.out.println("     ");
        } else {
            double cgpa = (double) obtainedCredited / totalCredits;
            double roundedCGPA = roundToThreeDecimal(cgpa);
            System.out.println("CGPI : " + roundedCGPA);
        }
        System.out.println("-".repeat(110));
    }

    private double roundToThreeDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
