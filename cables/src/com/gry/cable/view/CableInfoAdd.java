package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.table.TableCellEditor;

import com.gry.cable.service.CableInfoService;

public class CableInfoAdd extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JLabel jLabel;
	JTextField cablemodelJTextField;
	JPanel jPanel;
	JPanel jPanel1;
	JScrollPane jScrollPane;
	JTable jTable;
	JTextField standardField;
	DefaultTableModel tableModel;
	String[][] datas;
	public CableInfoAdd(){
		super("添加线缆信息", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 8, screenSize.height / 16,
				screenSize.width / 4, screenSize.height / 3*2);
		getContentPane().setLayout(new FlowLayout());
		jLabel = new JLabel("线缆型号");
		jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(screenSize.width / 4 - 5, 40));
		add(jPanel);
		jPanel.add(jLabel);
		cablemodelJTextField = new JTextField();
		cablemodelJTextField.setColumns(15);
		jPanel.add(cablemodelJTextField);
		String[] columnNames = {"线缆规格"};   //列名
		tableModel = new DefaultTableModel(datas,columnNames);
		jTable = new JTable(tableModel);
		jScrollPane = new JScrollPane(jTable);
		jScrollPane.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 2 -40));
		add(jScrollPane);
		jPanel1 = new JPanel();
		jPanel1.setPreferredSize(new Dimension(screenSize.width / 4 - 5, 40));
		add(jPanel1);
		standardField = new JTextField(5);
		jPanel1.add(standardField);
		final JButton addButton = new JButton("添加规格");   //添加按钮
        addButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                String []rowValues = {standardField.getText()};
                tableModel.addRow(rowValues);  //添加一行
                standardField.setText("");
            }
        });
        jPanel1.add(addButton);
        final JButton delButton = new JButton("删除规格");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = jTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                }
            }
        });
        jPanel1.add(delButton);
        final JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				CableInfoService cableInfoService = new CableInfoService();
				if (jTable.isEditing()) {  //判断是否在编辑
				    int row = jTable.getEditingColumn();//那一行
				    int col = jTable.getEditingColumn(); //那一列
				    TableCellEditor editor = jTable.getCellEditor(row, col);
				    editor.stopCellEditing();
				}
				if("".equals(cablemodelJTextField.getText())||cablemodelJTextField.getText()==null){
					JOptionPane.showMessageDialog(null, "线缆型号不能为空！","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(jTable.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "线缆规格不能为空！","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(cableInfoService.saveCableInfo(tableModel.getDataVector(), cablemodelJTextField.getText())==1){
					JOptionPane.showMessageDialog(null, "保存成功！","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        jPanel1.add(saveButton);
	}
}
