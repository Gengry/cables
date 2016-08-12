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

import com.gry.cable.service.CustomerInfoService;

public class CustomerInfoAdd extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	CustomerInfoService customerInfoService;
	JTextField name;
	JTextField contacts;
	JTextField telePhone;
	JTextField address;
	JTextField warehouse;
	JTextField remark;
	public CustomerInfoAdd(){
		super("添加客户信息", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 8, screenSize.height / 16,
				screenSize.width / 4, 300);
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
		JPanel jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(nameJLabel);
		jPanel2.add(name);
		jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(contactsJLabel);
		jPanel2.add(contacts);
		jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(telePhoneJLabel);
		jPanel2.add(telePhone);
		jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(addressJLabel);
		jPanel2.add(address);
		jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(warehouseJLabel);
		jPanel2.add(warehouse);
		jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4 - 30, 30));
		jPanel2.setLayout(new FlowLayout());
		jPanel.add(jPanel2);
		jPanel2.add(remarkJLabel);
		jPanel2.add(remark);
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
					customerInfoService = new CustomerInfoService();
					if(customerInfoService.saveCustomerInfo(name.getText(),contacts.getText(),
							telePhone.getText(),address.getText(),warehouse.getText(),remark.getText())==1)
					JOptionPane.showMessageDialog(null, "保存成功！","警告",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        jPanel.add(saveButton);
	}
	
}
