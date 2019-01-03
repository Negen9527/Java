

/**
 *
 * @author Negen
 */

import java.awt.*;
import java.util.Date;

import javax.swing.*;


class Disk{
	public static int tOne = 0,tTwo = -1,tThree = -1;					//表示每层塔的个数
	public static int towerOne[] = new int[30], towerTwo[] = new int[30],towerThree[] = new int[30];		//表示每个塔有塔的层数
	public static int n = 2;
	public static int rectxOne, rectxTwo, rectY = 440, rectMid, rectMidt = 315;			//表示要画的矩形左右坐标
	public static int t = 0,t2 = 0;							
	public static int tower[] = towerOne;//tower为指向要移动的塔的指针
	public static int bool = 1;
}


public class Hanio1220 extends JFrame implements Runnable{
	public Thread threadHanio = new Thread(this);
	static long time;
        static int moveCount = 0;   //移动次数
        
	public Hanio1220(){
		init();
                time = System.currentTimeMillis();
		add(new Paints());
		//runs();
		input();
		threadHanio.start();
	}
	public void init(){
		for(int i=0;i<30;i++)									//分别为每个塔里层数编号
		{
			Disk.towerOne[i]=i;
		}
		for(int i=0;i<30;i++)
		{
			Disk.towerTwo[i]=i;
		}
		for(int i=0;i<30;i++)
		{
			Disk.towerThree[i]=i;
		}
	}
	public void input(){
		String str = JOptionPane.showInputDialog(new JLabel("<html><h2><font color='blue'>请输入0到14之间的整数</font></html>"));
		int x=(int)Float.parseFloat(str);
		if(!(x>=1&&x<=13)){
			JOptionPane.showMessageDialog(null,"<html><h2><font color='red'>请输入0到14之间的整数</font></html>");
			System.exit(0);
		}
		else{
			Disk.n=x;
			Disk.tOne=x-1;
		}
			
	}
	public void moves(int a,int b)
	{ 
            
	    switch(a)
	    {
	        case 1:Disk.rectMid=155;Disk.tower=Disk.towerOne;Disk.t=Disk.tOne;Disk.tOne-=1;break;   //t表示要移动的塔中塔数有几个
	        case 2:Disk.rectMid=315;Disk.tower=Disk.towerTwo;Disk.t=Disk.tTwo;Disk.tTwo-=1;break;
	        case 3:Disk.rectMid=475;Disk.tower=Disk.towerThree;Disk.t=Disk.tThree;Disk.tThree-=1;break;
	    }
	    switch(b)
	    {
	        case 1:Disk.rectMidt=155;Disk.t2=Disk.tOne;break;			 //t2表示要移动到的塔有几个
	        case 2:Disk.rectMidt=315;Disk.t2=Disk.tTwo;break;
	        case 3:Disk.rectMidt=475;Disk.t2=Disk.tThree;break;
	    } 
	    if(a>=b) Disk.bool=1;
		else	Disk.bool=0;
	    Disk.rectxOne=Disk.rectMid-(14-Disk.tower[Disk.t])*5;					//计算要画的矩形左边坐标
	    Disk.rectxTwo=Disk.rectMid+(14-Disk.tower[Disk.t])*5;
	    Disk.rectY=440-Disk.t*5;	
	    while(Disk.rectY>=200)
	    {
	    	Disk.rectY-=10;									//塔向上移动到坐标100处
	        repaint();
	        try {
	        	Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    while(Disk.rectMid!=Disk.rectMidt)					//rectmidt表示中间塔中间的位置
	    {
	       if(1==Disk.bool)
	       {
	    	   Disk.rectMid-=5;
	    	   Disk.rectxOne=Disk.rectMid-(14-Disk.tower[Disk.t])*5;		//rectx2-rectx1 是变长的
	    	   Disk.rectxTwo=Disk.rectMid+(14-Disk.tower[Disk.t])*5;
	            repaint();
		        try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
	        }
	        else
	        {
	        	Disk.rectMid+=5;
	        	Disk.rectxOne=Disk.rectMid-(14-Disk.tower[Disk.t])*5;
	        	Disk.rectxTwo=Disk.rectMid+(14-Disk.tower[Disk.t])*5;
	            repaint();
	            try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
	        }
	    }
	    while(Disk.rectY<=(430-(Disk.t2)*5))
	    {
	    	Disk.rectY+=10;							//塔向下移动 
	    	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
	    	repaint();
	    }
	    switch(b)
	    {
                //选择移动到的塔层数加1，并且把当前塔的大小，传递过去。
	        case 1:
                    Disk.tOne+=1;
                    Disk.towerOne[Disk.tOne]=Disk.tower[Disk.t];
                    
                    repaint();
                    break;		
	        case 2:
                    Disk.tTwo+=1;
                    Disk.towerTwo[Disk.tTwo]=Disk.tower[Disk.t];
                    repaint();
                    break;
	        case 3:
                    Disk.tThree+=1;
                    Disk.towerThree[Disk.tThree]=Disk.tower[Disk.t];
                    repaint();
                    break;
	    }
            Hanio1220.moveCount++;
  
	    
	}
	public void hanoi(int n,int one,int two,int three)
	{
            
	    if(n==1) 
	    {
			moves(one,three);
	    	//JOptionPane.showMessageDialog(null,n);
	    }
		else
	    {
	        hanoi(n-1,one,three,two);
	        moves(one,three); 
	        hanoi(n-1,two,one,three);
               
	    }    
	}
	public void run(){
		hanoi(Disk.n,1,2,3);

	}
        
        
	public static void main(String[] args){
		Hanio1220  frame= new Hanio1220();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(660, 550);
                int windowWidth = frame.getWidth();                     //获得窗口宽
                int windowHeight = frame.getHeight();                   //获得窗口高
                Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
                Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
                int screenWidth = screenSize.width;                     //获取屏幕的宽
                int screenHeight = screenSize.height;                   //获取屏幕的高        
        //        this.setDefaultCloseOperation(3);
                frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
                frame.setTitle("汉诺塔算法演示动画");
                frame.setVisible(true);
                frame.setResizable(false);
	}

}

/**
 * 画图类，重写画图
 * @author Negen
 */
class Paints extends JPanel{
	protected void paintComponent(Graphics g){
		int i,j,rectMidOne;
			//JOptionPane.showMessageDialog(null, "画图启动");
                        g.setFont(new Font("宋体", 20 ,30));
			g.drawString("汉诺塔算法演示动画", 200, 50);
                        //运行的时长
                        long cosTime = System.currentTimeMillis() - Hanio1220.time;
                        g.drawString("耗时：" + cosTime + " (ms)", 200, 100);
                        g.drawString("移动次数：" + Hanio1220.moveCount + " (次)", 200, 150);
                        g.setColor(Color.GRAY);
                        g.fillRect(80,450,150,30);
                        g.fillRect(240,450,150,30);  
                        g.fillRect(400,450,150,30);
                        g.setColor(Color.BLUE);
                        g.drawString("A", 150, 475);
                        g.drawString("B", 310, 475);
                        g.drawString("C", 470, 475);
                        
			rectMidOne=155;int rectY1=440;
                        
//			g.drawRect(Disk.rectxOne,Disk.rectY,Disk.rectxTwo-Disk.rectxOne,9);             //画出移动的矩形
                        g.fillOval(Disk.rectxOne,Disk.rectY,Disk.rectxTwo-Disk.rectxOne,9);
                        g.setColor(Color.red);
                        
			for(i=0;i<=Disk.tOne;i++)
                                g.fillOval(155-(14-Disk.towerOne[i])*5,rectY1-10*i,(14-Disk.towerOne[i])*10,10);
			for(i=0;i<=Disk.tTwo;i++)
                                g.fillOval(315-(14-Disk.towerTwo[i])*5,rectY1-10*i,(14-Disk.towerTwo[i])*10,10);
			for(i=0;i<=Disk.tThree;i++)
                                g.fillOval(475-(14-Disk.towerThree[i])*5,rectY1-10*i,(14-Disk.towerThree[i])*10,10);
	}
}


