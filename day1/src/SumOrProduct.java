import java.util.Scanner;

/**
 * Created by MES on 7/14/2015.
 */
public class SumOrProduct {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter a number: ");
        int n = scanner.nextInt();

        System.out.println("What would you like to do?");
        System.out.println("1: sum from 1 to " + n);
        System.out.println("2: product from 1 to " + n);
        System.out.print("Enter a choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("The sum from 1 to " + n + " is " + sum(n));
                break;
            case 2:
                System.out.println("The product from 1 to " + n + " is " + product(n));
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static int sum(int n) {
        int sum = 0;
        for (int i=1; i<=n; i++) {
            sum += i;
        }
        return sum;
    }

    private static int product(int n) {
        int product = 1;
        for (int i=1; i<=n; i++) {
            product *= i;
        }
        return product;
    }
}
