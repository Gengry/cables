package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.gry.cable.service.CustomerInfoService;

public class CustomerInfoFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JTable jTable;
	String[] header;
	String[][] data;
	DefaultTableModel tableModel;
	JTextField searchName;
	JTextField searchContact;
	public CustomerInfoFrame(){
		super("客户信息管理", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 12, screenSize.height / 20,
				screenSize.width / 3*2, screenSize.height / 3*2);
		getContentPane().setLayout(new FlowLayout());
		JLabel jLabel = new JLabel("客户信息管理");
		jLabel.setLocation(screenSize.width / 3-75, 0);
		jLabel.setSize(150, 30);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		header = new String[]{"客户单位","联系人","联系电话","地址","仓库地址","备注","id"};
		tableModel = new DefaultTableModel(data,header);
	
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 30));
		panel.setLayout(null);
		getContentPane().add(panel);
		panel.add(jLabel);
		JButton addCableButton = new JButton("添加客户信息");	
		addCableButton.setLocation(screenSize.width / 3*2 -170,10);
		addCableButton.setSize(120, 20);
		addCableButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerInfoAdd customerInfoAdd = new CustomerInfoAdd();
				MainFrame.getMainFrame().getContentPane().add(customerInfoAdd);
				customerInfoAdd.setVisible(true);
			}
		});
		panel.add(addCableButton);
		Panel search = new Panel();
		search.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		search.setLayout(new FlowLayout());
		getContentPane().add(search);
		JLabel searchName1 = new JLabel("客户单位：");
		searchName = new JTextField(10);
		JLabel searchContact1 = new JLabel("联系人：");
		searchContact = new JTextField(10);
		search.add(searchName1);
		search.add(searchName);
		search.add(searchContact1);
		search.add(searchContact);
		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfoService customerInfoService = new CustomerInfoService();
				//String[][] datas = null;
				data = customerInfoService.getCustomerInfo(searchName.getText(),searchContact.getText());
				tableModel.setDataVector(data,header);
			}
		});
		search.add(searchButton);
		
		jTable = new JTable(tableModel);
		TableColumn column = jTable.getColumnModel().getColumn(6);
        column.setMaxWidth(0);
        column.setMinWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
		jTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width /3* 2-40,screenSize.height / 3*2 - 200));
		JScrollPane jScrollPane = new JScrollPane(jTable);
		getContentPane().add(jScrollPane);

		JButton delButton = new JButton("删除");
		delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = jTable.getSelectedRow();//获得选中行的索引
                CustomerInfoService customerInfoService = new CustomerInfoService();
                int rs = 1;
                if(selectedRow!=-1)  //存在选中行
                {
                	rs = customerInfoService.deleteCableInfo(Integer.valueOf((String) jTable.getValueAt(selectedRow, 6)));
                	if(rs == -1){
                		JOptionPane.showMessageDialog(null, "删除失败！","警告",JOptionPane.WARNING_MESSAGE);
                	}else{
                		JOptionPane.showMessageDialog(null, "删除成功！","警告",JOptionPane.WARNING_MESSAGE);
                	}
                    tableModel.removeRow(selectedRow);  //删除行
                }
            }
        });
		Panel delete = new Panel();
		delete.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		delete.setLayout(new FlowLayout());
		getContentPane().add(delete);
		delete.add(delButton);
	}
	
}
