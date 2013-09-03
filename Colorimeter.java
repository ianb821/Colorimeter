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
import java.awt.datatransfer.*;

public class Colorimeter {
    
    private static Point point;
    private static Rectangle screenRect;
    private static Robot robot;
    private static ColorimeterFrame screen;
    

	public static void main(String[] args) throws AWTException {
        
        robot = new Robot();
        screen = new ColorimeterFrame("Colorimeter");
        
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
    
    public static void copyColorToClipboard() {
        Color color = screen.getColor();
        
        StringSelection stringSelection;
        
        if (screen.useFloats()) {
            stringSelection = new StringSelection (String.format("R: %.4f G: %.4f B: %.4f", color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0));
        } else {
            stringSelection = new StringSelection ("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
        }
        Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
    }
}


