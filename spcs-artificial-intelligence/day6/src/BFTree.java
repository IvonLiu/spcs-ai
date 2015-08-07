/**
 * Created by MES on 7/22/2015.
 */
public class BFTree {

    public static void main(String[] args) {
        System.out.println(entropy(0.5, 0.5));
    }

    private static double informationGain() {
        entropy(5, 6, 3, 766, 3, 6.2342342, 3.1415, 0);
        return 0;
    }

    private static double entropy(double... probabilities) {
        double entropy = 0;
        for (double p : probabilities) {
            entropy += (-1 * p * (Math.log(p)/Math.log(2)));
        }
        return entropy;
    }

    private static double conditionalEntropy() {
        return 0;
    }
}
