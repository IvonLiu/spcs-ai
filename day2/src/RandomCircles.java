import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

/**
 * Created by MES on 7/15/2015.
 */
public class RandomCircles extends GraphicsProgram {

    private static final int PAUSE_TIME = 100;
    private static final int MAX_RADIUS = 100;
    private static final int MIN_DIAMETER = 50;
    private static final int DIAM_DIFF = 40;
    private RandomGenerator rgen = new RandomGenerator();
    GOval oval;
    private static final int BALL_RADIUS = 10;  // Radius of the ball in pixels
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    public static void main(String[] args) {
        new RandomCircles().start(args);
    }

    public void run() {
        while (true) {

            // Draw circles
            int diameter = rgen.nextInt(MIN_DIAMETER, MAX_RADIUS*2);
            int xPos = rgen.nextInt(getWidth() - diameter);
            int yPos = rgen.nextInt(getHeight() - diameter);
            oval = new GOval(xPos, yPos, diameter, diameter);

            int red = rgen.nextInt(256);
            int green = rgen.nextInt(256);
            int blue = rgen.nextInt(256);
            oval.setColor(new Color(red, green, blue));
            oval.setFilled(true);

            add(oval);

            // Turn them into donuts
            int innerDiameter = diameter - DIAM_DIFF;
            int outerXPos = xPos + DIAM_DIFF/2;
            int outerYPos = yPos + DIAM_DIFF/2;
            oval = new GOval(outerXPos, outerYPos, innerDiameter, innerDiameter);
            oval.setColor(Color.WHITE);
            oval.setFilled(true);

            add(oval);

            try {
                Thread.sleep(PAUSE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
