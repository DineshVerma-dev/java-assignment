
import java.util.Scanner;

class Billing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] menuItem = {
                "1.Stuffed Grape Leaves       ",
                "2.Mediterranean Mezze Platter",
                "3.Spicy Tuna Tartare         ",
                "4.Falafel with Tahini Sauce  ",
                "5.Margherita Pizza           "
        };

        int[] rate = { 100, 300, 400, 903, 599 };
        int Bill = 0;
        int[] quantity = new int[5];

        System.out.println("-".repeat(80));
        System.out.println("    Menu                          Rate            Quantity");
        System.out.println("-".repeat(80));

        for (int i = 0; i < 5; i++) {

            System.out.print(menuItem[i] + "      " + rate[i] + "           ");
            quantity[i] = sc.nextInt();
            Bill = Bill + quantity[i] * rate[i];

        }

        System.out.print("-".repeat(80));

        System.out.println(" ");

        System.out.println("   Initial Bill   =  " + Bill);
        System.out.print("-".repeat(80));

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("-".repeat(80));
        System.out.println(" ".repeat(27) + "SWIGGY BILL");
        System.out.println("-".repeat(80));

        // Here is the actual calculation of the bill //
        System.out.println("    Initial Bill  :         " + Bill);

        sc.nextLine();

        System.out.print("    Enter Promo code to avail discount :         ");
        String promocode = sc.nextLine();

        System.out.println(" ");

        System.out.println("    Promo code Applied      :         " + promocode);

        double serviceCharge = 0.1 * Bill;
        double gst = 0.18 * Bill;
        double platformCharge = 30;
        double discount = 0;

        if (Bill >= 300 && Bill <= 500 && promocode.equals("INDIA10")) {

            discount = 0.1 * Bill;

        } else if (Bill >= 500 && Bill <= 1000 && promocode.equals("INDIA20")) {

            discount = 0.2 * Bill;

        } else if (Bill > 1000 && promocode.equals("INDIA30")) {
            discount = 0.3 * Bill;
            if (discount > 1500) {
                discount = 1500;
            }
        }

        double totalBill = Bill + serviceCharge + gst + platformCharge - discount;

        System.out.println("    Service charge  (10%)   :         " + serviceCharge);
        System.out.println("    GST             (18%)   :         " + gst);
        System.out.println("    Platform charge (30/-)  :         " + platformCharge);
        System.out.println("    Discount                :        -" + discount);

        System.out.println("-".repeat(80));
        System.out.println("    NET BILL                :         " + totalBill);

        System.out.println("-".repeat(80));

        sc.close();
    }
}
