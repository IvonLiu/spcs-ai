import java.math.BigInteger;

/**
 * Created by MES on 7/14/2015.
 */
public class Fibonacci {

    private static BigInteger[] fibonacciNumbers;

    public static void main(String[] args) {

        fibonacciNumbers = new BigInteger[100];
        fibonacci(100);

        for (int i=0; i<fibonacciNumbers.length; i++) {
            System.out.println((i+1) + ": " + fibonacciNumbers[i]);
        }
    }

    public static BigInteger fibonacci(int n) {

        if (fibonacciNumbers[n-1] != null) {
            return fibonacciNumbers[n-1];
        }

        if (n == 1 || n == 2) {
            fibonacciNumbers[n-1] = BigInteger.valueOf(1);
        } else {
            fibonacciNumbers[n-1] = fibonacci(n-1).add(fibonacci(n-2));
        }

        return fibonacciNumbers[n-1];
    }
}
