package com.rs.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainApp {

	public JFrame frame;
	public static MainApp window = new MainApp();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 610, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton btnListAll = new JButton("\u67E5\u770B\u8D27\u54C1\u4FE1\u606F");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame listFrame = new ListFrame();
				
				listFrame.show();
				window.frame.setVisible(false);
				
			}
		});
		
		btnListAll.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel.putConstraint(SpringLayout.NORTH, btnListAll, 136, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnListAll, 54, SpringLayout.WEST, panel);
		panel.add(btnListAll);
		
		JButton btnAddProduct = new JButton("\u6DFB\u52A0\u8D27\u54C1\u4FE1\u606F");
		btnAddProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProductFrame addProductFrame = new AddProductFrame();
				addProductFrame.show();
				window.frame.setVisible(false);
			}
		});
		
		sl_panel.putConstraint(SpringLayout.NORTH, btnAddProduct, 136, SpringLayout.NORTH, panel);
		btnAddProduct.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel.putConstraint(SpringLayout.WEST, btnAddProduct, 52, SpringLayout.EAST, btnListAll);
		panel.add(btnAddProduct);
		
		JButton btnSearchProduct = new JButton("\u641C\u7D22\u5546\u54C1\u4FE1\u606F");
		btnSearchProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFrame searchFrame = new SearchFrame();
				searchFrame.show();
				window.frame.setVisible(false);
			}
		});
		
		
		sl_panel.putConstraint(SpringLayout.WEST, btnSearchProduct, 54, SpringLayout.WEST, panel);
		btnSearchProduct.setFont(new Font("宋体", Font.PLAIN, 30));
		sl_panel.putConstraint(SpringLayout.NORTH, btnSearchProduct, 79, SpringLayout.SOUTH, btnListAll);
		panel.add(btnSearchProduct);
		
		JButton btnModify = new JButton("修改/删除");
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyFrame modifyFrame = new ModifyFrame();
				modifyFrame.show();
				window.frame.setVisible(false);
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnModify, 79, SpringLayout.SOUTH, btnAddProduct);
		sl_panel.putConstraint(SpringLayout.WEST, btnModify, 52, SpringLayout.EAST, btnSearchProduct);
		btnModify.setFont(new Font("宋体", Font.PLAIN, 30));
		panel.add(btnModify);
	}

}
