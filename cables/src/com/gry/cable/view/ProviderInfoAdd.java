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
import javax.swing.JTextField;

import com.gry.cable.service.ProviderInfoService;

public class ProviderInfoAdd extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	JTextField name;
	JTextField contacts;
	JTextField telePhone;
	JTextField address;
	JTextField warehouse;
	JTextField remark;
	public ProviderInfoAdd(){
		super("添加供应商信息", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 8, screenSize.height / 16,
				screenSize.width / 4, screenSize.height / 3);
		getContentPane().setLayout(new FlowLayout());
		JPanel jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(screenSize.width / 4 - 30, screenSize.height / 5*2));
		jPanel.setLayout(new FlowLayout());
		this.add(jPanel);
		JLabel nameJLabel = new JLabel("客户单位:");
		JLabel contactsJLabel = new JLabel("联系人    :");
		JLabel telePhoneJLabel = new JLabel("联系电话:");
		JLabel addressJLabel = new JLabel("地         址:");
		JLabel warehouseJLabel = new JLabel("仓库地址:");
		JLabel remarkJLabel = new JLabel("备         注:");
		
		name = new JTextField();
		name.setColumns(20);
		contacts = new JTextField();
		contacts.setColumns(20);
		telePhone = new JTextField();
		telePhone.setColumns(20);
		address = new JTextField();
		address.setColumns(20);
		warehouse = new JTextField();
		warehouse.setColumns(20);
		remark = new JTextField();
		remark.setColumns(20);
		jPanel.add(nameJLabel);
		jPanel.add(name);
		jPanel.add(contactsJLabel);
		jPanel.add(contacts);
		jPanel.add(telePhoneJLabel);
		jPanel.add(telePhone);
		jPanel.add(addressJLabel);
		jPanel.add(address);
		jPanel.add(warehouseJLabel);
		jPanel.add(warehouse);
		jPanel.add(remarkJLabel);
		jPanel.add(remark);
		final JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (name.getText().trim().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "客户单位不能为空！","警告",JOptionPane.WARNING_MESSAGE);
				}else if(contacts.getText().trim().length() == 0){
					JOptionPane.showMessageDialog(null, "联系人不能为空！","警告",JOptionPane.WARNING_MESSAGE);
				}else if(telePhone.getText().trim().length() == 0){
					JOptionPane.showMessageDialog(null, "联系电话不能为空！","警告",JOptionPane.WARNING_MESSAGE);
				}else{
					ProviderInfoService providerInfoService = new ProviderInfoService();
					if(providerInfoService.saveProviderInfo(name.getText(),contacts.getText(),
							telePhone.getText(),address.getText(),warehouse.getText(),remark.getText())==1)
					JOptionPane.showMessageDialog(null, "保存成功！","警告",JOptionPane.WARNING_MESSAGE);
					saveButton.setEnabled(false);
				}
			}
		});
        jPanel.add(saveButton);
	}
	
}
