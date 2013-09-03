/**************************************************************
* Colorimeter was inspired by the built-in Mac application
* DigitalColor Meter.
*
* Ian Burns - 8/31/2013
**************************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;

public class ColorimeterFrame extends JFrame {

    private ColorimeterPanel colorimeterPanel;
    public static JRadioButton floatButton;
    
    public ColorimeterFrame() {
        super("Default");
    }
    
    public ColorimeterFrame(String s) {
        super(s);
        
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 185);
        setResizable(false);
        
        colorimeterPanel = new ColorimeterPanel();
        colorimeterPanel.setLayout(null);
        colorimeterPanel.setBackground(Color.LIGHT_GRAY);
        
        JLabel scaleLabel = new JLabel("Scale for RGB values:");
        floatButton = new JRadioButton("0.0 - 1.0");
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
        colorimeterPanel.setFocusable(true);
        colorimeterPanel.requestFocusInWindow();
        
        setContentPane(colorimeterPanel);
        setLocationRelativeTo(null);
        colorimeterPanel.repaint();
        setVisible(true);
    }
    
    public void refresh() {
        colorimeterPanel.repaint();
    }

    public void setImage(BufferedImage image) {
        colorimeterPanel.setImage(image);
    }
    
    public void setColor(Color color) {
        colorimeterPanel.setColor(color);
    }
    
    public void setColorValues(int value) {
        colorimeterPanel.setColorValues(value);
    }
    
    public boolean useFloats() {
        return floatButton.isSelected();
    }
    
    private class ColorimeterPanel extends JPanel {
        
        private Color color;
        private BufferedImage image;
        private int colorValues;
        
        
        public ColorimeterPanel() {
            Action a = new AbstractAction() {
                private static final long serialVersionUID = 1L;
                @Override public void actionPerformed(ActionEvent e) {
                    System.out.println("Herp");
                }
            };
            
            KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK);
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, a);
            this.getActionMap().put("ESCAPE", a);
        }
        
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
        
        public void copyCurrentValueToClipboard() {
            
            StringSelection stringSelection;
            
            if (colorValues == 1) {
                stringSelection = new StringSelection (String.format("R: %.4f G: %.4f B: %.4f", color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0));
            } else {
                stringSelection = new StringSelection ("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
            }
            Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
            clpbrd.setContents (stringSelection, null);
        }
    }
}



