package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gry.cable.service.CableInfoService;

public class CableInfoFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JTextField jTextField;
	String[] header;
	String[][] data;
	DefaultTableModel tableModel;
	JComboBox<String> box;
	JTable jTable;
	CableInfoService cableInfoService;
	public CableInfoFrame() {
		super("线缆信息管理", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 12, screenSize.height / 20,
				screenSize.width / 3*2, screenSize.height / 3*2);
		getContentPane().setLayout(new FlowLayout());
		JLabel jLabel = new JLabel("线缆信息管理");
		jLabel.setLocation(screenSize.width / 3-75, 0);
		jLabel.setSize(150, 30);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		header = new String[]{"线缆型号","线缆规格"};
		tableModel = new DefaultTableModel(data,header);
	
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		panel.setLayout(null);
		getContentPane().add(panel);
		panel.add(jLabel);
		JButton addCableButton = new JButton("添加线缆信息");	
		addCableButton.setLocation(screenSize.width / 3*2 -170,10);
		addCableButton.setSize(120, 20);
		addCableButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CableInfoAdd cableInfoAdd = new CableInfoAdd();
				MainFrame.getMainFrame().getContentPane().add(cableInfoAdd);
				cableInfoAdd.setVisible(true);
			}
		});
		panel.add(addCableButton);
		Panel search = new Panel();
		search.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		search.setLayout(new FlowLayout());
		getContentPane().add(search);
		box = new JComboBox<String>(new String[]{"按型号查询","按规格查询"});
		JLabel jLabel2 = new JLabel("线缆型号");
		jTextField = new JTextField(10);
		JButton searchCable = new JButton("搜索");
		searchCable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CableInfoService cableInfoService = new CableInfoService();
				String[][] datas = null;
				if(box.getSelectedItem().toString().equals("按型号查询")){
					datas = cableInfoService.getCableInfoByModel(jTextField.getText());
					header = new String[]{"线缆型号"};
				}else{
					if(jTextField.getText() == null||"".equals(jTextField.getText())){
						JOptionPane.showMessageDialog(null, "请输入线缆型号！","警告",JOptionPane.WARNING_MESSAGE);
						return;
					}
					datas = cableInfoService.getCableInfoByStandard(jTextField.getText());
					header = new String[]{"线缆型号","线缆规格"};
				}
				tableModel.setDataVector(datas,header);
			}
		});
		search.add(box);
		search.add(jLabel2);
		search.add(jTextField);
		search.add(searchCable);
		
		jTable = new JTable(tableModel);
		jTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width /3* 2-40,screenSize.height / 3*2 - 200));
		JScrollPane jScrollPane = new JScrollPane(jTable);
		getContentPane().add(jScrollPane);
		
		JButton delButton = new JButton("删除");
		delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = jTable.getSelectedRow();//获得选中行的索引
                cableInfoService = new CableInfoService();
                int rs = 1;
                if(selectedRow!=-1)  //存在选中行
                {
                	int count = tableModel.getColumnCount();
                	if(count == 1){
                		rs = cableInfoService.deleteCableInfo((String)tableModel.getValueAt(selectedRow, 0), null);
                		
                	}
                	if(count == 2){
                		rs = cableInfoService.deleteCableInfo((String)tableModel.getValueAt(selectedRow, 0), (String)tableModel.getValueAt(selectedRow, 1));
                	}
                	if(rs == -1){
                		JOptionPane.showMessageDialog(null, "删除失败！","警告",JOptionPane.WARNING_MESSAGE);
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
