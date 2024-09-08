import java.util.Scanner;

public class result {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
                new Subject("EM-II", 80),
                new Subject("EP-II", 60),
                new Subject("EC-II", 60),
                new Subject("EG   ", 60),
                new Subject("CP   ", 60)
        };
    }

    public void createTable(Scanner sc) {
        System.out.println(" Subject        Total Marks     Marks Obtained     Pass/Fail ");
        System.out.println("-".repeat(90));

        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Enter marks for " + subjects[i].subname + ": ");
            subjects[i].marksObtained = sc.nextInt();
            subjects[i].calculateResult();
            subjects[i].displaySubject();
        }
    }
}
