/**************************************************************
* Colorimeter was inspired by the built-in Mac application
* DigitalColor Meter.
*
* Ian Burns - 8/31/2013
**************************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ColorimeterFrame extends JFrame {

    private ColorimeterPanel colorimeterPanel;
    
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
    
    public Color getColor() {
        return colorimeterPanel.getColor();
    }
    
    public boolean useFloats() {
        return colorimeterPanel.useFloats();
    }
    
    private class CopyAction extends AbstractAction {
        public CopyAction() {
            super();
        }
        public void actionPerformed(ActionEvent e) {
            Colorimeter.copyColorToClipboard();
          
        }
    }
    
    private class ColorimeterPanel extends JPanel {
        
        private Color color;
        private BufferedImage image;
        private int colorValues;
        private CopyAction copyAction;
        private JRadioButton floatButton;
        
        public ColorimeterPanel() {
            
            // add key bindings for ctl-c and cmd-c
            copyAction = new CopyAction();
            getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "copy");
            getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), "copy");
            getActionMap().put("copy", copyAction);
            
            // setup the rest of the panel
            setLayout(null);
            setBackground(Color.LIGHT_GRAY);
            
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
            
            add(floatButton);
            add(intButton);
            add(scaleLabel);
            setSize(340, 185);
            setFocusable(true);
            requestFocusInWindow();
            
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // make sure the panel has focus so that the keybindings work
            grabFocus();
            
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
        
        public boolean useFloats() {
            return this.floatButton.isSelected();
        }
        
        public void setColor(Color color) {
            this.color = color;
        }
        
        public Color getColor() {
            return this.color;
        }
        
        public void setImage(BufferedImage image) {
            this.image = image;
        }
        
        public void setColorValues(int value) {
            this.colorValues = value;
        }
    }
}



