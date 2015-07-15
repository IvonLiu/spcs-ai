/**
 * Created by MES on 7/15/2015.
 */
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;


public class Recurse extends GraphicsProgram {

    //constant pause time
    private static final int PAUSE_TIME = 20;

    //Initial Ball Radius
    private static final int BALL_RADIUS = 200;

    //Our Recursion Tree Depth
    private static final int DEPTH = 7;

    //Basic screen settings
    public static final int APPLICATION_WIDTH = 1440;
    public static final int APPLICATION_HEIGHT = 980;

    //start our Recurse objects
    public static void main(String[] args) {
        new Recurse().start(args);
    }


    //main code logic here:
    public void run() {
        TriangleSplit(0, addBall(0, 0, BALL_RADIUS, Color.BLACK, true));
    }

    /**
     * @param depth is how many splits we want to do.
     * @param ball is the first ball to be split
     * @return void
     * Method takes a ball and splits it into three
     */
    public void TriangleSplit(int depth, GOval ball) {

        if (depth >= DEPTH) {
            return;
        }

        double radius, xPos, yPos;
        radius = ball.getWidth() / 4;

        xPos = ball.getX() + ball.getWidth()/2 - radius;
        yPos = ball.getY();
        GOval ball1 = addBall(xPos, yPos, radius, Color.BLACK, true);

        xPos = ball.getX();
        yPos = ball.getY() + ball.getHeight() - radius*2;
        GOval ball2 = addBall(xPos, yPos, radius, Color.BLACK, true);

        xPos = ball.getX() + ball.getWidth() - radius*2;
        yPos = ball.getY() + ball.getHeight() - radius*2;
        GOval ball3 = addBall(xPos, yPos, radius, Color.BLACK, true);

        remove(ball);

        try {
            Thread.sleep(PAUSE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        depth++;
        TriangleSplit(depth, ball1);
        TriangleSplit(depth, ball2);
        TriangleSplit(depth, ball3);
    }

    /**
     *
     * @param x ball x location
     * @param y ball y location
     * @param radius ball radius
     * @param color ball color
     * @param filled ball filled
     * @return the ball to add
     */
    public GOval addBall(double x,double y, double radius, Color color, boolean filled) {
        GOval aball = new GOval(x,y,radius*2,radius*2);
        aball.setFilled(filled);
        aball.setColor(color);
        add(aball);
        return aball;
    }

}
