package com.rs.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.rs.entity.Product;
import com.rs.utils.FileUtil;
import com.rs.utils.GUITools;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		
		/**
		 * 	窗口监听事件
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainApp.window.frame.setVisible(true);
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1092, 741);
		GUITools.center(this);//设置窗口在屏幕上的位置
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[]names={"货品号","货品名","库存","进货日期","货品单价","库存总价","生产厂家","供应商"};
		DefaultTableModel model = new DefaultTableModel(names,10);
		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		
		//居中显示开始
/*		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,   r);*/
		//居中显示结束
		
		
		
		JTableHeader header = table.getTableHeader();
		table.setRowHeight(35);
		header.setFont(new Font("楷体", Font.PLAIN, 30));
		table.setModel(model);
		
		
		table.getCellEditor(1,2).getTableCellEditorComponent(table, "-1", true, 1, 2).setBackground(Color.RED);
		//读取文件  获取数据
		List<Product> products = FileUtil.readFile();
		//库存总价排序
		products.sort(Comparator.naturalOrder());
		Integer rowNum = 0;
		//低于5库存的行号
		List<Integer> rowNums = new ArrayList<>();
		for (Product product : products) {
			System.out.println(product.getCount()*product.getPrice());
			Object[] rowData = {product.getProductId(),
								product.getName(),
								product.getCount(),
								product.getInTime(),
								product.getPrice(),
								product.getCount()*product.getPrice(),
								product.getManufacturer(),
								product.getSupplier()};
			model.insertRow(rowNum, rowData);
			if(product.getCount() <=5 ) {
				rowNums.add(rowNum);
			}
			rowNum++;
		}
		
		TableColumn countColumn = table.getColumn("库存");
		 //绘制库存列的字体颜色   
        DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   

            public void setValue(Object value) { //重写setValue方法，从而可以动态设置列单元字体颜色   
            	Integer a = (value instanceof Integer) ? ((Integer) value).intValue() : -1; //获取月薪列中的值   
                if(a != 0) {
                    setBackground((a  < 6 && a > -1) ? Color.red : Color.white); //库存小于等于5，就将背景色设置为红色   
                    setText((value == null) ? "" : value.toString());   
                }

            }   

        };   
		countColumn.setCellRenderer(fontColor);
        
		scrollPane.setViewportView(table);

	}

}
