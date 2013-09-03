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

	public static void main(String[] args) throws AWTException {
        
        Point point;
        Rectangle screenRect;
        Robot robot = new Robot();
        
        
        // setup frame and panel
        JFrame screen = new JFrame("Colorimeter");
        screen.setDefaultLookAndFeelDecorated(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(340, 185);
        screen.setResizable(false);
        
        ColorimeterPanel colorimeterPanel = new ColorimeterPanel();
        colorimeterPanel.setLayout(null);
        colorimeterPanel.setBackground(Color.LIGHT_GRAY);
        
        JLabel scaleLabel = new JLabel("Scale for RGB values:");
        JRadioButton floatButton = new JRadioButton("0.0 - 1.0");
        JRadioButton intButton = new JRadioButton("0 - 255");
        ButtonGroup group = new ButtonGroup();
        group.add(floatButton);
        group.add(intButton);

        intButton.setBounds(200, 25, 100, 25);
        floatButton.setBounds(200, 45, 100, 25);
        scaleLabel.setBounds(180, 5, 200, 25);
        intButton.setSelected(true);
        
        colorimeterPanel.add(floatButton);
        colorimeterPanel.add(intButton);
        colorimeterPanel.add(scaleLabel);
        colorimeterPanel.setSize(340, 185);

        screen.setContentPane(colorimeterPanel);
        screen.setLocationRelativeTo(null);
        colorimeterPanel.repaint();
        screen.setVisible(true);
        
        while (true) {
            point = MouseInfo.getPointerInfo().getLocation();
            screenRect = new Rectangle((int)(point.getX() - 7), (int)(point.getY() - 7), 15, 15);
            
            colorimeterPanel.setColor(robot.getPixelColor((int)point.getX(), (int)point.getY()));
            
            colorimeterPanel.setImage(robot.createScreenCapture(screenRect));
            
            if (intButton.isSelected()) {
                colorimeterPanel.setColorValues(0);
            } else {
                colorimeterPanel.setColorValues(1);
            }
            
            colorimeterPanel.repaint();
            
            robot.delay(20);
        }
	}
}


