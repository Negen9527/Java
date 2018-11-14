package com.rs.app;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

import com.rs.entity.Product;
import com.rs.utils.DateUtil;
import com.rs.utils.FileUtil;
import com.rs.utils.GUITools;
import com.rs.utils.StringUtil;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddProductFrame extends JFrame {

	private static JPanel contentPane;
	private static JTextField textFieldName;
	private static JTextField textFieldProductId;
	private static JTextField textFieldCount;
	private static JTextField textFieldInTime;
	private static JTextField textFieldPrice;
	private static JTextField textFieldManufacturer;
	private static JTextField textFieldSupplier;
	
	
	private static Integer id;       
	private static String name;                //货品名
	private static String productId;           //货品号
	private static Integer count;              //库存
	private static String inTime;                //进货日期
	private static Double price;               //货品单价
	private static String manufacturer;        //生产厂家
	private static String supplier;            //供应商

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductFrame frame = new AddProductFrame();
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
	public AddProductFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 888, 832);
		GUITools.center(this);//设置窗口在屏幕上的位置
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel title = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, title, 65, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, title, 194, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, title, -218, SpringLayout.EAST, contentPane);
		title.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(title);
		
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, title, -55, SpringLayout.NORTH, content);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, content, -156, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, content, -600, SpringLayout.SOUTH, contentPane);
		content.setForeground(Color.WHITE);
		content.setBorder(null);
		
		JLabel lblNewLabel = new JLabel("\u6DFB\u52A0\u8D27\u54C1");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 36));
		title.add(lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, content, 70, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, content, 778, SpringLayout.WEST, contentPane);
		contentPane.add(content);
		content.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel labelName = new JLabel("\u8D27\u54C1\u540D",JLabel.CENTER);
		labelName.setFont(new Font("宋体", Font.PLAIN, 24));
		labelName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		content.add(labelName);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel labelProductId = new JLabel("\u8D27\u54C1\u53F7",JLabel.CENTER);
		labelProductId.setFont(new Font("宋体", Font.PLAIN, 24));
		labelProductId.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		content.add(labelProductId);
		
		textFieldProductId = new JTextField();
		textFieldProductId.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldProductId);
		textFieldProductId.setColumns(10);
		
		JLabel labelCount = new JLabel("\u6570\u91CF",JLabel.CENTER);
		labelCount.setFont(new Font("宋体", Font.PLAIN, 24));
		labelCount.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		content.add(labelCount);
		
		textFieldCount = new JTextField();
		textFieldCount.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldCount);
		textFieldCount.setColumns(10);
		
		JLabel labelInTime = new JLabel("\u8FDB\u8D27\u65E5\u671F",JLabel.CENTER);
		labelInTime.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		labelInTime.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(labelInTime);
		
		textFieldInTime = new JTextField();
		textFieldInTime.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldInTime);
		textFieldInTime.setColumns(10);
		
		JLabel labelPrice = new JLabel("\u8D27\u54C1\u5355\u4EF7",JLabel.CENTER);
		labelPrice.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		labelPrice.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(labelPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel labelManufacturer = new JLabel("\u751F\u4EA7\u5382\u5BB6",JLabel.CENTER);
		labelManufacturer.setFont(new Font("宋体", Font.PLAIN, 24));
		labelManufacturer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		content.add(labelManufacturer);
		
		textFieldManufacturer = new JTextField();
		textFieldManufacturer.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldManufacturer);
		textFieldManufacturer.setColumns(10);
		
		JLabel labelSupplier = new JLabel("\u4F9B\u5E94\u5546",JLabel.CENTER);
		labelSupplier.setFont(new Font("宋体", Font.PLAIN, 24));
		labelSupplier.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		content.add(labelSupplier);
		
		textFieldSupplier = new JTextField();
		textFieldSupplier.setFont(new Font("宋体", Font.PLAIN, 24));
		content.add(textFieldSupplier);
		textFieldSupplier.setColumns(10);
		
		JButton btnReset = new JButton("\u91CD  \u7F6E");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置
				getValues(0);
			}
		});
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnReset, 47, SpringLayout.SOUTH, content);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnReset, 193, SpringLayout.WEST, contentPane);
		btnReset.setFont(new Font("宋体", Font.PLAIN, 24));
		contentPane.add(btnReset);

		/**
		 * 	窗口监听事件
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainApp.window.frame.setVisible(true);
			}
		});
		
		
		
		
		
		
		
		JButton btnSave = new JButton("\u4FDD  \u5B58");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//保存
				Boolean blnIsOk = getValues(1);
				if(blnIsOk) {
					Product product = new Product();
					product.setName(name);
					product.setProductId(productId);
					product.setCount(count);
					product.setInTime(inTime);
					product.setPrice(price);
					product.setManufacturer(manufacturer);
					product.setSupplier(supplier);
					FileUtil.writeFile(product.myToString() + "\n");
					JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.DEFAULT_OPTION);
					getValues(0);
				}
			}
		});
		
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSave, 47, SpringLayout.SOUTH, content);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSave, -193, SpringLayout.EAST, contentPane);
		btnSave.setFont(new Font("宋体", Font.PLAIN, 24));
		contentPane.add(btnSave);
		
		
		

		
	}
	
	
	private static Boolean getValues(Integer choice) {
		Boolean blnResult = true;
		name = textFieldName.getText();
		productId = textFieldProductId.getText();
		String countStr = textFieldCount.getText();
		String inTimeStr = textFieldInTime.getText();
		String priceStr = textFieldPrice.getText();
		manufacturer = textFieldManufacturer.getText();
		supplier = textFieldSupplier.getText();	
		
		
		if(1 == choice) {
			//save(保存)
			if(StringUtil.isEmpty(name)) {
				JOptionPane.showMessageDialog(null, "货品名不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}
			if(StringUtil.isEmpty(productId)) {JOptionPane.showMessageDialog(null, "货品号不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				List<Product> products = FileUtil.readFile();			
				for (Product product : products) {
					if(productId.trim().equals(product.getProductId())) {
						JOptionPane.showMessageDialog(null, "货品号已存在，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
						textFieldProductId.setText("");
						blnResult = false;
						break;
					}
				}
				
			}
			
			
			if(StringUtil.isEmpty(countStr)) {
				JOptionPane.showMessageDialog(null, "数量不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					count = Integer.parseInt(countStr);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "请输入整数数量", "警告", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(inTimeStr)) {
				JOptionPane.showMessageDialog(null, "进货日期不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					DateUtil.str2date(inTimeStr);
					inTime = inTimeStr;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "请输入正确的日期，如：2018-01-23", "警告", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(priceStr)) {
				JOptionPane.showMessageDialog(null, "货品单价不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					price = Double.parseDouble(priceStr);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "请输入正确的价格，如：22.5", "警告", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(manufacturer)) {
				JOptionPane.showMessageDialog(null, "生产厂家不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}	
			
			if(StringUtil.isEmpty(supplier)) {
				JOptionPane.showMessageDialog(null, "供应商不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}	
			
		
		}else{
			//reset(重置)
			textFieldName.setText("");
			textFieldProductId.setText("");
			textFieldCount.setText("");
			textFieldInTime.setText("");
			textFieldPrice.setText("");
			textFieldManufacturer.setText("");
			textFieldSupplier.setText("");
		}
		
		
		return blnResult;
		
	}

}
