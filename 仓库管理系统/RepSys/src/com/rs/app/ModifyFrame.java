package com.rs.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.io.FileReader;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.rs.entity.Product;
import com.rs.utils.DateUtil;
import com.rs.utils.FileUtil;
import com.rs.utils.StringUtil;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ModifyFrame extends JFrame {
	private static String name;                //��Ʒ��
	private static String productId;           //��Ʒ��
	private static Integer count;              //���
	private static String inTime;              //��������
	private static Double price;               //��Ʒ����
	private static String manufacturer;        //��������
	private static String supplier;            //��Ӧ��
	
	private JPanel contentPane;
	private JTextField textFieldProductId;
	private JTextField textFieldProductName;
	private JTextField textFieldCount;
	private JTextField textFieldInTime;
	private JTextField textFieldPrice;
	private JTextField textFieldManufacturer;
	private JTextField textFieldSupplier;
	private JComboBox comboBox = new JComboBox();
	private List<Product> products;
	private Integer selectIndex = -1;
	
	private String oldProductId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyFrame frame = new ModifyFrame();
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
	public ModifyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		
		sl_panel.putConstraint(SpringLayout.WEST, comboBox, 172, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, comboBox, 368, SpringLayout.WEST, panel);
		comboBox.setFont(new Font("����", Font.PLAIN, 24));
		panel.add(comboBox);
		
		JButton btnDetail = new JButton("\u67E5\u770B\u8BE6\u60C5");

		btnDetail.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel.putConstraint(SpringLayout.WEST, btnDetail, 36, SpringLayout.EAST, comboBox);
		panel.add(btnDetail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sl_panel.putConstraint(SpringLayout.SOUTH, btnDetail, -36, SpringLayout.NORTH, panel_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, comboBox, -36, SpringLayout.NORTH, panel_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, -21, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, panel_1, -500, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 60, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_1, 538, SpringLayout.WEST, panel);
		panel.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel lblNewLabel = new JLabel("\u8D27\u54C1\u53F7");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel, 38, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel, 90, SpringLayout.WEST, panel_1);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 24));
		panel_1.add(lblNewLabel);
		
		textFieldProductId = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldProductId, -50, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel, -49, SpringLayout.WEST, textFieldProductId);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldProductId, 35, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldProductId, 211, SpringLayout.WEST, panel_1);
		textFieldProductId.setFont(new Font("����", Font.PLAIN, 24));
		panel_1.add(textFieldProductId);
		textFieldProductId.setColumns(10);
		
		JLabel label = new JLabel("\u8D27\u54C1\u540D");
		sl_panel_1.putConstraint(SpringLayout.WEST, label, 90, SpringLayout.WEST, panel_1);
		label.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, label, 34, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_1.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.add(label);
		
		textFieldProductName = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldProductName, 28, SpringLayout.SOUTH, textFieldProductId);
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldProductName, 49, SpringLayout.EAST, label);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldProductName, -50, SpringLayout.EAST, panel_1);
		textFieldProductName.setFont(new Font("����", Font.PLAIN, 24));
		textFieldProductName.setColumns(10);
		panel_1.add(textFieldProductName);
		
		textFieldCount = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldCount, 28, SpringLayout.SOUTH, textFieldProductName);
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldCount, 211, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldCount, -50, SpringLayout.EAST, panel_1);
		textFieldCount.setFont(new Font("����", Font.PLAIN, 24));
		textFieldCount.setColumns(10);
		panel_1.add(textFieldCount);
		
		JLabel label_1 = new JLabel("\u5E93\u5B58");
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 34, SpringLayout.SOUTH, label);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 114, SpringLayout.WEST, panel_1);
		label_1.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.EAST, label_1, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.add(label_1);
		
		textFieldInTime = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldInTime, -50, SpringLayout.EAST, panel_1);
		textFieldInTime.setFont(new Font("����", Font.PLAIN, 24));
		textFieldInTime.setColumns(10);
		panel_1.add(textFieldInTime);
		
		JLabel label_2 = new JLabel("\u8FDB\u8D27\u65E5\u671F");
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldInTime, 49, SpringLayout.EAST, label_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_2, 66, SpringLayout.WEST, panel_1);
		label_2.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_2, 36, SpringLayout.SOUTH, label_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldInTime, -3, SpringLayout.NORTH, label_2);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_2, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.add(label_2);
		
		textFieldPrice = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldPrice, 28, SpringLayout.SOUTH, textFieldInTime);
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldPrice, 211, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldPrice, 428, SpringLayout.WEST, panel_1);
		textFieldPrice.setFont(new Font("����", Font.PLAIN, 24));
		textFieldPrice.setColumns(10);
		panel_1.add(textFieldPrice);
		
		JLabel label_3 = new JLabel("\u8D27\u54C1\u5355\u4EF7");
		label_3.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_3, 31, SpringLayout.SOUTH, label_2);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label_2);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("\u751F\u4EA7\u5382\u5BB6");
		sl_panel_1.putConstraint(SpringLayout.WEST, label_4, 66, SpringLayout.WEST, panel_1);
		label_4.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_4, 34, SpringLayout.SOUTH, label_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_4, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.add(label_4);
		
		textFieldManufacturer = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldManufacturer, 346, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldManufacturer, 49, SpringLayout.EAST, label_4);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldManufacturer, -50, SpringLayout.EAST, panel_1);
		textFieldManufacturer.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textFieldManufacturer, 0, SpringLayout.SOUTH, label_4);
		textFieldManufacturer.setColumns(10);
		panel_1.add(textFieldManufacturer);
		
		JLabel label_5 = new JLabel("  \u4F9B\u5E94\u5546");
		sl_panel_1.putConstraint(SpringLayout.WEST, label_5, 66, SpringLayout.WEST, panel_1);
		label_5.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_5, 31, SpringLayout.SOUTH, label_4);
		sl_panel_1.putConstraint(SpringLayout.EAST, label_5, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.add(label_5);
		
		textFieldSupplier = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldSupplier, 50, SpringLayout.EAST, label_5);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldSupplier, -50, SpringLayout.EAST, panel_1);
		textFieldSupplier.setFont(new Font("����", Font.PLAIN, 24));
		sl_panel_1.putConstraint(SpringLayout.NORTH, textFieldSupplier, -3, SpringLayout.NORTH, label_5);
		textFieldSupplier.setColumns(10);
		panel_1.add(textFieldSupplier);
		
		JButton btnDelete = new JButton("\u5220\u9664");

		sl_panel.putConstraint(SpringLayout.SOUTH, btnDelete, -392, SpringLayout.SOUTH, panel);
		btnDelete.setFont(new Font("����", Font.PLAIN, 28));
		sl_panel.putConstraint(SpringLayout.WEST, btnDelete, 16, SpringLayout.EAST, panel_1);
		panel.add(btnDelete);
		
		JButton btnModify = new JButton("\u4FEE\u6539");

		sl_panel.putConstraint(SpringLayout.NORTH, btnModify, 38, SpringLayout.SOUTH, btnDelete);
		sl_panel.putConstraint(SpringLayout.WEST, btnModify, 16, SpringLayout.EAST, panel_1);
		btnModify.setFont(new Font("����", Font.PLAIN, 28));
		panel.add(btnModify);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainApp.window.frame.setVisible(true);
			}
		});
		//��ʼ������������
		loadProducts();
		/**
		 * 	�鿴����
		 */
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectIndex = comboBox.getSelectedIndex();
				String productId = (String)comboBox.getSelectedItem();
				Product currentProduct = products.get(selectIndex);
				oldProductId = currentProduct.getProductId();
				textFieldProductId.setText(oldProductId);
				textFieldProductName.setText(currentProduct.getName());
				textFieldCount.setText(Integer.toString(currentProduct.getCount()));
				textFieldInTime.setText(currentProduct.getInTime());
				textFieldPrice.setText(Double.toString(currentProduct.getPrice()));
				textFieldManufacturer.setText(currentProduct.getManufacturer());
				textFieldSupplier.setText(currentProduct.getSupplier());
				
			}
		});
		
		
		
		/**
		 * 	ɾ��
		 */
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectIndex < 0) {
					JOptionPane.showMessageDialog(null, "���Ȳ鿴����", "��ʾ", JOptionPane.DEFAULT_OPTION);
				}else{
					deleteProduct(selectIndex);
					selectIndex = -1;
				}
				
			}
		});
	
	
		/**
		 * 	�޸��¼�
		 */
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyProduct();
			
			}
		});
		
		
	}
	
	
	
	
	
	
	/**
	 * 	��������
	 */
	private void loadProducts() {
		products = FileUtil.readFile();
		for (Product product : products) {
			comboBox.addItem(product.getProductId());
		}
	}

	/**
	 * 	���
	 */
	private void clearAll() {
		textFieldProductName.setText("");
		textFieldProductId.setText("");
		textFieldCount.setText("");
		textFieldInTime.setText("");
		textFieldPrice.setText("");
		textFieldManufacturer.setText("");
		textFieldSupplier.setText("");
	}
	
	/**
	 *	  ɾ��
	 */
	private void deleteProduct(Integer index) {
		products.remove(index);
		clearAll();
		comboBox.removeAllItems();
		products.remove(products.get(selectIndex));
		writeIn();
	}
	
	
	/**
	 * 	�޸Ĳ�Ʒ��Ϣ
	 */
	private void modifyProduct() {
		Boolean blnResult = true;
		name = textFieldProductName.getText();
		productId = textFieldProductId.getText();
		String countStr = textFieldCount.getText();
		String inTimeStr = textFieldInTime.getText();
		String priceStr = textFieldPrice.getText();
		manufacturer = textFieldManufacturer.getText();
		supplier = textFieldSupplier.getText();	
		
		if(selectIndex > -1) {
			if(StringUtil.isEmpty(name)) {
				JOptionPane.showMessageDialog(null, "��Ʒ������Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}
			if(StringUtil.isEmpty(productId)) {JOptionPane.showMessageDialog(null, "��Ʒ�Ų���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				List<Product> products = FileUtil.readFile();			
				for (Product product : products) {
					if(productId.trim().equals(product.getProductId()) && !oldProductId.equals(product.getProductId())) {
						JOptionPane.showMessageDialog(null, "��Ʒ���Ѵ��ڣ�����������", "����", JOptionPane.WARNING_MESSAGE);
						textFieldProductId.setText("");
						blnResult = false;
						break;
					}
				}
				
			}
			
			if(StringUtil.isEmpty(countStr)) {
				JOptionPane.showMessageDialog(null, "��������Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					count = Integer.parseInt(countStr);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "��������������", "����", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(inTimeStr)) {
				JOptionPane.showMessageDialog(null, "�������ڲ���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					DateUtil.str2date(inTimeStr);
					inTime = inTimeStr;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "��������ȷ�����ڣ��磺2018-01-23", "����", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(priceStr)) {
				JOptionPane.showMessageDialog(null, "��Ʒ���۲���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}else {
				try {
					price = Double.parseDouble(priceStr);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "��������ȷ�ļ۸��磺22.5", "����", JOptionPane.WARNING_MESSAGE);
					blnResult = false;
				}
			}
			
			if(StringUtil.isEmpty(manufacturer)) {
				JOptionPane.showMessageDialog(null, "�������Ҳ���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}	
			
			if(StringUtil.isEmpty(supplier)) {
				JOptionPane.showMessageDialog(null, "��Ӧ�̲���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				blnResult = false;
			}	
		}else {
			JOptionPane.showMessageDialog(null, "���Ȳ鿴����", "��ʾ", JOptionPane.DEFAULT_OPTION);
		}
		
		
		if(blnResult && selectIndex > -1) {
			products.remove(products.get(selectIndex));
			Product product = new Product();
			product.setName(name);
			product.setProductId(productId);
			product.setCount(Integer.parseInt(countStr));
			product.setInTime(inTimeStr);
			product.setPrice(Double.parseDouble(priceStr));
			product.setManufacturer(manufacturer);
			product.setSupplier(supplier);
			products.add(product);
			System.out.println(product.toString());
//			System.out.println(product.toString());
			comboBox.removeAllItems();
			writeIn();
			selectIndex = -1;
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
			clearAll();
		}
		
		
	}
	
	private void writeIn() {
		String outputStr = "";
		for (Product product : products) {
			outputStr += product.myToString() + "\n";
		}
		FileUtil.writeCoverFile(outputStr);
		loadProducts();
	}
	
}
