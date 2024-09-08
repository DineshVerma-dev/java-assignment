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
    int credit;
    String grade;

    public Subject(String subname, int totalMarks, int credit) {
        this.subname = subname;
        this.totalMarks = totalMarks;
        this.credit = credit;
    }

    public void calculateResult() {
        percentage = ((double) marksObtained / totalMarks) * 100;
        if (marksObtained < 0.4 * totalMarks) {
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
        System.out.println("-".repeat(100));
        System.out.println(subname + "               " + totalMarks + "                " + marksObtained + "                " + grade + "              " + roundedPercentage + "               " + pointer);        
    }
}

class Table {
    Subject[] subjects;

    public Table() {
        subjects = new Subject[] {
           
            new Subject(" EM-II     ", 80, 4),
            new Subject(" EP-II     ", 60, 4),
            new Subject(" EC-II     ", 60, 3),
            new Subject(" EG        ", 60, 2),
            new Subject(" CP        ", 60, 1),
            new Subject(" PCE-I     ", 40, 1),

            // Internal subjects (no credits included for internal in this example)
            new Subject(" maths     ", 20, 0),
            new Subject(" physics   ", 15, 0),
            new Subject(" chemistry ", 15, 0),
            new Subject(" graphics  ", 15, 0),
            new Subject(" c programm", 15, 0),
            new Subject(" PCE-Iinter", 15, 0),

            // Term work subjects with credits
            new Subject(" EM-II TW  ", 25, 1),
            new Subject(" EP-II TW  ", 25, 1),
            new Subject(" EC-II TW  ", 25, 1),
            new Subject(" EG TW     ", 25, 1),
            new Subject(" CP TW     ", 25, 1),
            new Subject(" PCE-I TW  ", 25, 1),
            new Subject(" Workshop  ", 50, 2),
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
    int totalKTs; // Flag to track if the student has failed any subject

    for (int i = 0; i < subjects.length; i++) {
        System.out.print("Enter marks for " + subjects[i].subname + ": ");
        subjects[i].marksObtained = sc.nextInt();
        subjects[i].calculateResult();

        if (subjects[i].result.equals("F")) {
            hasFailed = true; // Set flag if the student has failed any subject
        }

        if (subjects[i].totalMarks <= 20 && subjects[i].result.equals("F")) {
            internalKTs++;
        }

        if (subjects[i].totalMarks > 20 && subjects[i].totalMarks != 25 && subjects[i].result.equals("F")) {
            externalKTs++;
        }

        if (subjects[i].totalMarks == 25 && subjects[i].result.equals("F")) {
            termWorkKTs++;
        }

        if (subjects[i].credit > 0) { 
            obtainedCredited += subjects[i].pointer * subjects[i].credit;
            totalCredits += subjects[i].credit;
        }

        totalMarksOfStudent += subjects[i].marksObtained;
        totalMarksOutoff   += subjects[i].totalMarks;
    }

     totalKTs = internalKTs + externalKTs;

    System.out.println("-".repeat(100));
    System.out.println("\nSubject             Total Marks        Marks Obtained        Pass/Fail        Percentage        Pointer");
    for (int i = 0; i < subjects.length; i++) {
        subjects[i].displaySubject();
    }

    System.out.println("-".repeat(100));
         
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
    System.out.println("Total Marks :        "+totalMarksOutoff);
    System.out.println("     ");
    System.out.println("TotalKts             "+totalKTs);
    System.out.println("     ");
   
    if (totalKTs > 9) {
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
    System.out.println("-".repeat(100));
}

      
    

    private double roundToThreeDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
