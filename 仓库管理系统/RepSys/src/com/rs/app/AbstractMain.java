package com.rs.app;
/**
 * 主窗口测试类
 */
@SuppressWarnings("serial")
public class AbstractMain extends MainFrame {
	//定义主函数
	public static void main(String[] args) {
		new AbstractMain().setVisible(true);
	}
	//覆盖父类中展示管理员界面的方法
	@Override
	public void showAdminDialog() {
//		System.out.println("进入管理界面");
		MainApp mainApp = new MainApp();
		mainApp.window.frame.show();
		this.setVisible(false);
	} 
}
