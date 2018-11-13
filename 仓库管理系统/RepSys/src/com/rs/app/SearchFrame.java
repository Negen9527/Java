package com.rs.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rs.entity.Product;
import com.rs.utils.FileUtil;
import com.rs.utils.StringUtil;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private List<Product> products;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
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
	public SearchFrame() {
		products = FileUtil.readFile();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 832, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, panel_1, 30, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 62, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, 149, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_1, 736, SpringLayout.WEST, panel);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, panel_2, 30, SpringLayout.SOUTH, panel_1);
		sl_panel.putConstraint(SpringLayout.WEST, panel_2, 170, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_2, 380, SpringLayout.SOUTH, panel_1);
		sl_panel.putConstraint(SpringLayout.EAST, panel_2, 700, SpringLayout.WEST, panel);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel labelProductId = new JLabel("\u8BF7\u8F93\u5165\u8D27\u53F7");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, labelProductId, -40, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, labelProductId, -418, SpringLayout.EAST, panel_1);
		labelProductId.setFont(new Font("����", Font.PLAIN, 30));
		panel_1.add(labelProductId);
		
		textFieldSearch = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.WEST, textFieldSearch, 16, SpringLayout.EAST, labelProductId);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textFieldSearch, -37, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textFieldSearch, -229, SpringLayout.EAST, panel_1);
		textFieldSearch.setFont(new Font("����", Font.PLAIN, 30));
		panel_1.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("\u641C\u7D22");
		

		
		
		
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton, -4, SpringLayout.NORTH, labelProductId);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton, 23, SpringLayout.EAST, textFieldSearch);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton, -36, SpringLayout.SOUTH, panel_1);
		btnNewButton.setFont(new Font("����", Font.PLAIN, 30));
		panel_1.add(btnNewButton);
		panel.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22\u7ED3\u679C\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 22));
		sl_panel_2.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, panel_2);
		panel_2.add(lblNewLabel);
		
		JLabel labelResult = new JLabel("");
		labelResult.setFont(new Font("����", Font.PLAIN, 22));
		labelResult.setVerticalAlignment(SwingConstants.TOP);
		sl_panel_2.putConstraint(SpringLayout.NORTH, labelResult, 13, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.WEST, labelResult, 10, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, labelResult, 291, SpringLayout.SOUTH, lblNewLabel);
		sl_panel_2.putConstraint(SpringLayout.EAST, labelResult, 509, SpringLayout.WEST, panel_2);
		panel_2.add(labelResult);
	
	
		/**
		 * 	���ڼ����¼�
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainApp.window.frame.setVisible(true);
			}
		});
		
	
		//�����¼�
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resultStr = "";
				String searchStr = textFieldSearch.getText();	
				if(StringUtil.isEmpty(searchStr)) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ļ�Ʒ��", "����", JOptionPane.WARNING_MESSAGE);
				}else {
					//��ʼ����
					resultStr = "û�ҵ���Ʒ�������Ʒ���Ƿ���ȷ";
					for (Product product : products) {
						if(searchStr.trim().equals(product.getProductId())) {
							resultStr = "<html><body>"
											 + "��Ʒ��:" + product.getProductId() + "<br>"
											 + "��Ʒ��:" + product.getName() + "<br>"
											 + "���:" + product.getCount() + "<br>"
											 + "��������:" + product.getInTime()+ "<br>"
											 + "��Ʒ����:" + product.getPrice()+ "(RMB)<br>"
											 + "��������:" + product.getManufacturer()+ "<br>"
											 + "��Ӧ��:" + product.getSupplier() + "<br>"
							                 + "</body></html>";
							
							
							break;
						}
					}
					
					labelResult.setText(resultStr);
				}
			}
		});
	}
}
