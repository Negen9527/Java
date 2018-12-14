/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.bean;
/**
 * 使用java实现持续移动的小球
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

public class JFrameTest extends Frame {
    Image ImageBuffer = null;  
    Graphics GraImage = null;  
    Random rand = new Random();
    //定义移动变量
    int x = 450;
    int y = 0;
    int m = 1;
    int n = 1;
    //主函数
    public static void main(String[] args) {
        new JFrameTest();
    }
    //使用构造器创建窗体并设置
    public JFrameTest(){


        
        this.setVisible(true);
        this.setSize(500,500);         //设置窗口大小
        
        int windowWidth = this.getWidth();                     //获得窗口宽
        int windowHeight = this.getHeight();                   //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        
//        this.setDefaultCloseOperation(3);
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        this.setResizable(false);
        this.setTitle("测试框架");
        //退出程序
        this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e)
          {
             System.exit(0);
          }
        });
        
            // 鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入了");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标出去了");
            }
            

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    int mouseX =  e.getX();
                    int mouseY = e.getY();
                    System.out.println(mouseX +" , " + mouseY);
               }

            }
            
        });
        
        move();
    }
    //重写画图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);
        g.fillOval(x, 50 + y, 50, 50);

    }
    //定义小球移动轨迹
    public void move(){
        while(true){
            x -- ;
            
            if(x <= -50){
                x = 450;
                y = rand.nextInt(400);
            }
//            y += n;
//            if(x >= 450){
//                m = -m; 
//            }
//            if(x < 0){
//                m = 1;
//            }
//            if(y >= 425){
//                n = -n;
//            }
//            if(y < 0){
//                n = 1;
//            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            repaint();
        }
    }

    
    
    
    
    
    
    
    
    /**
     *  双缓冲：解决刷新闪烁问题（轻量级组件：Frame、Jpanel）
     * @param g 
     */
    @Override
    public void update(Graphics g) {
        ImageBuffer = createImage(this.getWidth(), this.getHeight());   //创建图形缓冲区  
        GraImage = ImageBuffer.getGraphics();       //获取图形缓冲区的图形上下文  
        paint(GraImage);        //用paint方法中编写的绘图过程对图形缓冲区绘图  
        GraImage.dispose();     //释放图形上下文资源  
        g.drawImage(ImageBuffer, 0, 0, this);   //将图形缓冲区绘制到屏幕上 
    }
    
    
}