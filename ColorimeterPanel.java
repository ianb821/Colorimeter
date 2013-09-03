/**************************************************************
* Colorimeter was inspired by the built-in Mac application
* DigitalColor Meter.
*
* Ian Burns - 8/31/2013
**************************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ColorimeterPanel extends JPanel {
    
    private Color color;
    private BufferedImage image;
    private int colorValues;
    
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        
        if (image != null) {
            g.drawImage(image, 10, 6, 150, 150, null);
        }
        
		g.setColor(Color.WHITE);
		g.draw3DRect(185, 80, 75, 75, true);
        
        if (color != null) {
            g.setColor(color);
        } else {
            g.setColor(Color.WHITE);
        }
        
        g.fill3DRect(185, 80, 75, 75, true);
        
        g.setColor(Color.BLACK);
        g.drawRect(81, 81, 6, 6);
        
        if (color != null) {
            switch (colorValues) {
                case 0:
                    g.drawString("R: " + color.getRed(), 275, 100);
                    g.drawString("G: " + color.getGreen(), 274, 125);
                    g.drawString("B: " + color.getBlue(), 275, 150);
                    break;
                case 1:
                    g.drawString(String.format("R: %.4f", (color.getRed() / 255.0)), 275, 100);
                    g.drawString(String.format("G: %.4f", (color.getGreen() / 255.0)), 274, 125);
                    g.drawString(String.format("B: %.4f", (color.getBlue() / 255.0)), 275, 150);
                    break;
            }
        } else {
            g.drawString("R: ", 275, 100);
            g.drawString("G: ", 274, 125);
            g.drawString("B: ", 275, 150);
        }
	}
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void setColorValues(int value) {
        this.colorValues = value;
    }
}
