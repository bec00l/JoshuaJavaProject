/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Random;
import java.awt.Point;

public class Draw  {
    private static JFrame f;
    Point pointStart = null;
    Point pointEnd   = null;
    public static void main(String[] args) {
        createAndShowGUI();
        addMouseEvents();
    }
    
    private static void createAndShowGUI() {
        f = new JFrame("Random Background Color Generator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        
        //uncomment the folowing line to add the paint panel
        //f.add(addPaintPanel());
        f.setVisible(true);
    }
    
    private static JPanel addPaintPanel(){
        JPanel p = new JPanel() {
        
        //set the to and from point
        Point pointStart = null;
        Point pointEnd   = null;
        
        {
            //add listeners so set the first drawing point
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    pointStart = e.getPoint();
                }

                public void mouseReleased(MouseEvent e) {
                    pointStart = null;
                }
            });
            
            //add motion listeners on movement and dragging
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent e) {
                    pointEnd = e.getPoint();
                }

                public void mouseDragged(MouseEvent e) {
                    pointEnd = e.getPoint();
                    repaint();
                }
            });
        }
        
        //set paint method to call
        public void paint(Graphics g) {
            super.paint(g);
            if (pointStart != null) {
                g.setColor(Color.RED);
                g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
            }
        }
        
    }; 
        //return the JPanel
        return p;
    }
    
    private static void addMouseEvents(){
        f.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                f.getContentPane().setBackground(getRandomColor());
            }
        });
    }
    
    //generate a random RGB Color
    private static Color getRandomColor() {
        Random r = new Random();
        int rValue = r.nextInt(256); 
        int gValue = r.nextInt(256);  
        int bValue = r.nextInt(256); 
        return new Color(rValue, gValue, bValue);
    }
}
