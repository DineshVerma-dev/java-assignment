import java.util.Scanner;

public class Tax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the Annual Salary  :  ");
        int salary = sc.nextInt();
        
       
        Slab TaxforPerson1 = new Slab();
        TaxforPerson1.calculateSlab(salary);

       

        
        sc.close();
    }
}

class Slab {

    public void calculateSlab(int salary) {
        double tax = 0;
        
       
        if (salary <= 300000) {
            tax = 0;  // No tax for income up to 3 lakh
        } else if (salary <= 700000) {
            tax = 0.05 * (salary - 300000);  // 5% for income between 3 lakh to 7 lakh
        } else if (salary <= 1000000) {
            tax = (0.05 * 400000) + 0.1 * (salary - 700000);  // 10% for income between 7 lakh to 10 lakh
        } else if (salary <= 1200000) {
            tax = (0.05 * 400000) + (0.1 * 300000) + 0.15 * (salary - 1000000);  // 15% for income between 10 lakh to 12 lakh
        } else if (salary <= 1500000) {
            tax = (0.05 * 400000) + (0.1 * 300000) + (0.15 * 200000) + 0.2 * (salary - 1200000);  // 20% for income between 12 lakh to 15 lakh
        } else {
            tax = (0.05 * 400000) + (0.1 * 300000) + (0.15 * 200000) + (0.2 * 300000) + 0.3 * (salary - 1500000);  // 30% for income above 15 lakh
        }

        System.out.println("Total Tax to be paid: ₹ " + tax);
    }
}
