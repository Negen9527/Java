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
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

public class JFrameTest extends Frame implements MouseMotionListener{
    int State;        //状态
    static final int START = 1;    //开始 
    static final int STOP = 2;     //停止
    static final int RUNNING = 3;  //运行
    static final int BANG = 4;     //击中
    static final int OVER = 5;     //结束
    double time = 0.0;
    
    
    
    
    
    Image ImageBuffer = null;  
    Graphics GraImage = null;  
    Random rand = new Random();
    //定义移动变量
    int x = 450;
    int y = 0;
    int m = 1;
    int n = 1;
    int _x = 250;     //炮台x
    int _y = 435;     //炮台y
    
    int bulletX = _x - 5;      //子弹x
    int bulletY = _y - 5;      //子弹y

    
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
        
        State = RUNNING;
        
//        this.setDefaultCloseOperation(3);
        this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        this.setResizable(false);
        this.setTitle("测试框架");
        this.addMouseMotionListener(this);
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
                State = RUNNING;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标出去了");
                _x = 250;     //炮台x
                _y = 435;     //炮台y
                State = STOP;
            }
            
            /**
             *  鼠标左键点击触发射击
             * @param e 
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    int mouseX =  e.getX();
                    int mouseY = e.getY();     
                    System.out.println(mouseX + " , " + mouseY);
                    State = BANG;
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
        g.drawRect(210, 475, 80, 50);
        g.drawString("炮台", 240,490);
        g.drawLine(250, 475, _x, _y);

        g.setColor(Color.RED);
        g.fillOval(bulletX, bulletY, 10, 10);
        
        

    }
    //定义小球移动轨迹
    public void move(){
        while(true){
            switch(State){
                case BANG:
                    bang();
                    break;
                case RUNNING:
                     x --;
                     if(bulletX != _x -5 || bulletY != _y -5){
                         bulletX--;
                         bulletY--;
                     }
                     break;
                case STOP:
                    break;
                
            }
         
            if(x <= -50){
                x = 450;
                y = rand.nextInt(400);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 鼠标移动位置监听
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        
                int mouseX =  e.getX();
                int mouseY = e.getY();  
                System.out.println("x:" + mouseX + " , " + "y:" + mouseY);
                double percent = Math.sqrt(Math.pow(mouseX - 250, 2) + Math.pow(mouseY - 475, 2))/40;
                
                if(mouseX > 250){
                    _x = 250 + (int)(Math.sqrt(Math.pow(mouseX - 250, 2)) / percent);
                    _y = 475 - (int)(Math.sqrt(Math.pow(mouseY - 475, 2)) / percent);


                }
                if(mouseX < 250){
                    _x = 250 - (int)(Math.sqrt(Math.pow(mouseX - 250, 2)) / percent);
                    _y = 475 - (int)(Math.sqrt(Math.pow(mouseY - 475, 2)) / percent);
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
    
    //击中小鸟
    private void bang(){
        bulletX--;
        bulletY--;
        
        
        x--;
        double nowTime = time + 0.001; 
        double h = 0.5 * 9.8 * (nowTime * nowTime - time * time);
        y = y + (int)h;
        time++;
        if(y >= this.getHeight() - 50 || x <= -50){
           time = 0;
           State = RUNNING;
           repaint();
        }

          
        
    }
    
    
    
}