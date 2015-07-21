/**
 * Created by MES on 7/15/2015.
 */
import acm.graphics.*;
import acm.util.*;
import acm.program.*;
import java.awt.*;


public class Seurat extends GraphicsProgram{

    private static final int NUM_PICTURES = 1;
    private static final int SPLOTCH_DIAMETER = 6;
    private RandomGenerator rgen = new RandomGenerator();

    public static void main(String[] args) {
        new Seurat().start(args);
    }

    public void run() {
        GImage image = new GImage("day2/images/vangogh.jpg");
        image.setLocation(0, 0);
        add(image);

        while (true) {

            int xPos = rgen.nextInt((int) (image.getWidth() - SPLOTCH_DIAMETER));
            int yPos = rgen.nextInt((int) (image.getHeight() - SPLOTCH_DIAMETER));
            Color color = getColorAt(image, xPos, yPos);

            int circleXPos = xPos - SPLOTCH_DIAMETER/2;
            int circleYPos = yPos - SPLOTCH_DIAMETER/2;
            drawCircle(circleXPos, circleYPos, color);
        }
    }

    private void drawCircle(int xPos, int yPos, Color circleColor) {
        GOval circle = new GOval(xPos, yPos, SPLOTCH_DIAMETER, SPLOTCH_DIAMETER);
        circle.setColor(circleColor);
        circle.setFilled(true);
        add(circle);
    }

        /**
         *
         * @param image
         * @param x of a pixel
         * @param y of a pixel
         * @return the color found at a specific pixel
         */
    private Color getColorAt(GImage image, int x, int y) {
        // feel free to ignore how the program is looking up the pixel color
        // this syntax is not very nice and you don't need to understand it.
        return new Color(image.getPixelArray()[y][x]);
    }





}
