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
		 * 	���ڼ����¼�
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				MainApp.window.frame.setVisible(true);
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1092, 741);
		GUITools.center(this);//���ô�������Ļ�ϵ�λ��
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[]names={"��Ʒ��","��Ʒ��","���","��������","��Ʒ����","����ܼ�","��������","��Ӧ��"};
		DefaultTableModel model = new DefaultTableModel(names,10);
		table = new JTable();
		table.setFont(new Font("����", Font.PLAIN, 20));
		
		//������ʾ��ʼ
/*		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,   r);*/
		//������ʾ����
		
		
		
		JTableHeader header = table.getTableHeader();
		table.setRowHeight(35);
		header.setFont(new Font("����", Font.PLAIN, 30));
		table.setModel(model);
		
		
		table.getCellEditor(1,2).getTableCellEditorComponent(table, "-1", true, 1, 2).setBackground(Color.RED);
		//��ȡ�ļ�  ��ȡ����
		List<Product> products = FileUtil.readFile();
		//����ܼ�����
		products.sort(Comparator.naturalOrder());
		Integer rowNum = 0;
		//����5�����к�
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
		
		TableColumn countColumn = table.getColumn("���");
		 //���ƿ���е�������ɫ   
        DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {   

            public void setValue(Object value) { //��дsetValue�������Ӷ����Զ�̬�����е�Ԫ������ɫ   
            	Integer a = (value instanceof Integer) ? ((Integer) value).intValue() : -1; //��ȡ��н���е�ֵ   
                if(a != 0) {
                    setBackground((a  < 6 && a > -1) ? Color.red : Color.white); //���С�ڵ���5���ͽ�����ɫ����Ϊ��ɫ   
                    setText((value == null) ? "" : value.toString());   
                }

            }   

        };   
		countColumn.setCellRenderer(fontColor);
        
		scrollPane.setViewportView(table);

	}

}
