package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChangePasswordFrame extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	JTextField oldPassword;
	JTextField newPassword1;
	JTextField newPassword2;
	
	public ChangePasswordFrame(){
		super("修改登录密码", true, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width - screenSize.width / 4)/2, screenSize.height / 16,
				screenSize.width / 4, screenSize.height / 3);
		GridLayout gridLayout = new GridLayout(3,2);
		gridLayout.setHgap(3);
		getContentPane().setLayout(gridLayout);
		JLabel oldJLabel = new JLabel("旧密码");
		getContentPane().add(oldJLabel);
		oldPassword = new JTextField(2);
		getContentPane().add(oldPassword);
	}
	
}
