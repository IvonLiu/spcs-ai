/**
 * Created by MES on 7/15/2015.
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.Scanner;


public class Memes extends GraphicsProgram {

    //constant pause time
    private static final int PAUSE_TIME = 20;

    //Basic screen settings
    public static final int APPLICATION_WIDTH = 405;
    public static final int APPLICATION_HEIGHT = 405;
    private GLabel topText;
    private GLabel bottomText;


    public static void main(String[] args) {
        new Memes().start(args);
    }

    //main code logic here:
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the top text: ");
        String topStr = scanner.nextLine();
        System.out.print("Please enter the bottom text: ");
        String bottomStr = scanner.nextLine();
        scanner.close();

        GImage image = new GImage("day2/images/hipster_cat.jpg");
        image.setLocation(0, 0);
        add(image);

        GLabel topText = new GLabel(topStr, 30, 50);
        topText.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        GLabel bottomText = new GLabel(bottomStr, 30, image.getHeight()-80);
        bottomText.setFont(new Font("Sans-Serif", Font.BOLD, 30));

        add(topText);
        add(bottomText);
    }
}
