import java.util.Scanner;

/**
 * Created by MES on 7/14/2015.
 */
public class MonthlyPayment {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the credit card balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter the APR: ");
        double apr = scanner.nextDouble();
        System.out.print("Enter the number of months: ");
        int numMonths = scanner.nextInt();

        scanner.close();


        System.out.println("\nMonthly payment: " + getMonthlyPayment(balance, apr, numMonths));
    }

    /**
     * Similar to Excel's PMT function
     *
     * @param a
     *          Initial balance
     * @param r
     *          Interest rate, in percent
     * @param n
     *          Number of months
     * @return  Amount you need to pay per month
     */
    private static double getMonthlyPayment(double a, double r, int n) {
        r = 1 + r/1200;
        double denominator = 0;
        for (int i=n-1; i>=0; i--) {
            denominator += Math.pow(r, i);
        }

        double numerator = Math.pow(r, n) * a;

        return numerator / denominator;
    }
}
