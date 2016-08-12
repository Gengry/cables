package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.gry.cable.service.ColorInfoService;

public class ColorInfoFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JTextField jTextField;
	String[] header;
	String[][] data;
	DefaultTableModel tableModel;
	JComboBox<String> box;
	JTable jTable;
	ColorInfoService unitInfoService = new ColorInfoService();
	public ColorInfoFrame(String[][] datas) {
		super("线缆颜色管理", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 4, screenSize.height / 20,
				screenSize.width / 3, screenSize.height / 3*2);
		getContentPane().setLayout(new FlowLayout());
		JLabel jLabel = new JLabel("线缆颜色管理");
		//jLabel.setLocation(75, 0);
		jLabel.setSize(150, 30);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		header = new String[]{"id","线缆颜色"};
		this.data = datas;
		tableModel = new DefaultTableModel(data,header);
		jTable = new JTable(tableModel);
		jTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width / 7,screenSize.height / 3*2 - 200));
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumnModel().getColumn(1).setCellRenderer(render);
        TableColumn column = jTable.getColumnModel().getColumn(0);
        column.setMaxWidth(0);
        column.setMinWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(screenSize.width / 3 -40, 35));
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);
		panel.add(jLabel);
		
		JScrollPane jScrollPane = new JScrollPane(jTable);
		this.add(jScrollPane);
		
		final JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(screenSize.width / 3 -40, 35));
		panel1.setLayout(new FlowLayout());
        this.add(panel1);
        final JTextField textField = new JTextField(8);
        panel1.add(textField);
        final JButton addButton = new JButton("添加");   //添加按钮
        addButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                String []rowValues = {"",textField.getText()};
                if("".equals(textField.getText())||textField.getText()==null){
                	JOptionPane.showMessageDialog(null, "不能为空！","警告",JOptionPane.WARNING_MESSAGE);
                	return;
                }
                if(unitInfoService.saveUnitInfo(textField.getText())==1){
                	JOptionPane.showMessageDialog(null, "保存成功！","警告",JOptionPane.WARNING_MESSAGE);
                }else{
                	JOptionPane.showMessageDialog(null, "保存失败！","警告",JOptionPane.WARNING_MESSAGE);
                }
                tableModel.addRow(rowValues);  //添加一行
                textField.setText(null);
            }
        });
        panel1.add(addButton);  
        final JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = jTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {	
                	 int rs = 1;
                     if(selectedRow!=-1)  //存在选中行
                     {
                     	rs = unitInfoService.deleteUnitInfo(jTable.getValueAt(selectedRow, 0).toString());
                     	if(rs == -1){
                     		JOptionPane.showMessageDialog(null, "删除失败！","警告",JOptionPane.WARNING_MESSAGE);
                     	}else{
                     		JOptionPane.showMessageDialog(null, "删除成功！","警告",JOptionPane.WARNING_MESSAGE);
                     	}
                         tableModel.removeRow(selectedRow);  //删除行
                     }
                }
            }
        });
        panel1.add(delButton);
	}

	

	
}
