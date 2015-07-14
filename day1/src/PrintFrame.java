/**
 * Created by MES on 7/14/2015.
 */
public class PrintFrame {

    public static void main(String[] args) {

        String[] strings = new String[] {
                "Hello",
                "world",
                "in",
                "a",
                "frame"
        };

        printStringsInFrame(strings);

    }

    private static void printStringsInFrame(String[] strings) {
        int maxLength = 0;
        for (String s : strings) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        // Print first line
        for (int i=0; i<maxLength+4; i++) {
            System.out.print("*");
        }
        System.out.print("\n");

        // Print strings
        for (String s : strings) {
            String format = "%-" + maxLength + "s";
            System.out.printf("* " + format + " *%n", s);
        }

        // Print bottom line
        for (int i=0; i<maxLength+4; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }
}
