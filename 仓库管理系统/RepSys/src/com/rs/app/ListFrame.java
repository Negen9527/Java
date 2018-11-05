package com.rs.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rs.entity.Product;
import com.rs.utils.FileUtil;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ListFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFrame frame = new ListFrame();
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
	public ListFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[]names={"货品号","货品名","库存","进货日期","货品单价","生产厂家","供应商"};
		DefaultTableModel model = new DefaultTableModel(names,15);
		table = new JTable();
		
		JTableHeader header = table.getTableHeader();
		table.setRowHeight(35);
		header.setFont(new Font("楷体", Font.PLAIN, 35));
		table.setModel(model);
		
		//读取文件  获取数据
		List<Product> products = FileUtil.readFile();
		Integer rowNum = 0;
		for (Product product : products) {
			Object[] rowData = {product.getProductId(),
								product.getName(),
								product.getCount(),
								product.getInTime(),
								product.getPrice(),
								product.getManufacturer(),
								product.getSupplier()};
			model.insertRow(rowNum, rowData);
			rowNum++;
		}
		
		scrollPane.setViewportView(table);

	}

}
