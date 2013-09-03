/**************************************************************
* Colorimeter was inspired by the built-in Mac application
* DigitalColor Meter.  It gives you the RGB color value of any
* point on the screen in either 0-255 int values or 0.0-1.0 float
* values.
*
* Ian Burns - 8/31/2013
**************************************************************/

import java.awt.*;
import javax.swing.*;

public class Colorimeter {
    
    public static Point point;
    public static Rectangle screenRect;
    public static Robot robot;

	public static void main(String[] args) throws AWTException {
        
        robot = new Robot();
        ColorimeterFrame screen = new ColorimeterFrame("Colorimeter");
        
        while (true) {
            point = MouseInfo.getPointerInfo().getLocation();
            screenRect = new Rectangle((int)(point.getX() - 7), (int)(point.getY() - 7), 15, 15);
            
            screen.setColor(robot.getPixelColor((int)point.getX(), (int)point.getY()));
            
            screen.setImage(robot.createScreenCapture(screenRect));
            
            if (screen.useFloats()) {
                screen.setColorValues(1);
            } else {
                screen.setColorValues(0);
            }
            
            screen.refresh();
            

            
            robot.delay(20);
        }
	}
    
    
}


