/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.bean;

/**
 *
 * @author Negen
 */
import javax.swing.*; 
import java.awt.*; 

class C extends JFrame { 
    C() { 
        final Point point = new Point(); 

        add(new JPanel() { 
            public void paintComponent(Graphics g) { 
                g.fillOval(point.x, point.y, 20, 20); 
                point.x = (point.x + 2) % getWidth(); 
                point.y = (point.x * point.x) / 1000;   // 造就弧线的简单二次方程。 
            } 
        }); 

        setSize(777, 666); 
        setVisible(true); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 

        while (true) { 
            repaint(point.x, point.y, 50, 50); 
            try { Thread.sleep(10); } catch (Exception ex) {} 
        } 
    } 

    public static void main(String[] args) { 
        new C(); 
    } 
} 