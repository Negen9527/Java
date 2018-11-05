package com.rs.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtProductId;
	private JTextField txtCount;
	private JTextField txtInTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel title = new JLabel("\u6DFB\u52A0\u8D27\u54C1");
		sl_contentPane.putConstraint(SpringLayout.NORTH, title, 36, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, title, 168, SpringLayout.WEST, contentPane);
		title.setFont(new Font("宋体", Font.PLAIN, 30));
		contentPane.add(title);
		
		JLabel name = new JLabel("\u8D27\u54C1\u540D");
		sl_contentPane.putConstraint(SpringLayout.WEST, name, 58, SpringLayout.WEST, contentPane);
		name.setFont(new Font("宋体", Font.PLAIN, 22));
		sl_contentPane.putConstraint(SpringLayout.NORTH, name, 177, SpringLayout.NORTH, contentPane);
		contentPane.add(name);
		
		txtName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtName, 0, SpringLayout.NORTH, name);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtName, 45, SpringLayout.EAST, name);
		txtName.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel productId = new JLabel("\u8D27\u54C1\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, productId, 38, SpringLayout.SOUTH, name);
		sl_contentPane.putConstraint(SpringLayout.WEST, productId, 58, SpringLayout.WEST, contentPane);
		productId.setFont(new Font("宋体", Font.PLAIN, 22));
		contentPane.add(productId);
		
		txtProductId = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtProductId, 241, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtName, 0, SpringLayout.EAST, txtProductId);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtProductId, 45, SpringLayout.EAST, productId);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtProductId, 292, SpringLayout.EAST, productId);
		txtProductId.setFont(new Font("宋体", Font.PLAIN, 20));
		txtProductId.setColumns(10);
		contentPane.add(txtProductId);
		
		JLabel count = new JLabel("\u6570\u91CF");
		sl_contentPane.putConstraint(SpringLayout.NORTH, count, 38, SpringLayout.SOUTH, productId);
		sl_contentPane.putConstraint(SpringLayout.EAST, count, 0, SpringLayout.EAST, name);
		count.setFont(new Font("宋体", Font.PLAIN, 22));
		contentPane.add(count);
		
		txtCount = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtCount, -1, SpringLayout.NORTH, count);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtCount, 0, SpringLayout.WEST, title);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtCount, -1, SpringLayout.EAST, txtName);
		txtCount.setFont(new Font("宋体", Font.PLAIN, 20));
		txtCount.setColumns(10);
		contentPane.add(txtCount);
		
		JLabel inTime = new JLabel("\u8FDB\u8D27\u65E5\u671F");
		sl_contentPane.putConstraint(SpringLayout.NORTH, inTime, 38, SpringLayout.SOUTH, count);
		sl_contentPane.putConstraint(SpringLayout.EAST, inTime, 0, SpringLayout.EAST, name);
		inTime.setFont(new Font("宋体", Font.PLAIN, 22));
		contentPane.add(inTime);
		
		txtInTime = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtInTime, -1, SpringLayout.SOUTH, count);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtInTime, 0, SpringLayout.EAST, title);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtInTime, -1, SpringLayout.EAST, txtName);
		txtInTime.setFont(new Font("宋体", Font.PLAIN, 20));
		txtInTime.setColumns(10);
		contentPane.add(txtInTime);
	}
}
