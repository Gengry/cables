package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.gry.cable.service.UserService;

public class ChangePasswordFrame extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	JPasswordField oldPassword;
	JPasswordField newPassword1;
	JPasswordField newPassword2;
	
	public ChangePasswordFrame(){
		super("修改登录密码", true, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width - screenSize.width / 4)/2, screenSize.height / 16,
				screenSize.width / 4, screenSize.height / 3);
		getContentPane().setLayout(new FlowLayout());
		JPanel jPanel0 = new JPanel();
		jPanel0.setPreferredSize(new Dimension(screenSize.width / 4, 20));
		JPanel jPanel1 = new JPanel();
		jPanel1.setPreferredSize(new Dimension(screenSize.width / 4, 40));
		JPanel jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(screenSize.width / 4, 40));
		JPanel jPanel3 = new JPanel();
		jPanel3.setPreferredSize(new Dimension(screenSize.width / 4, 40));
		JPanel jPanel4 = new JPanel();
		jPanel4.setPreferredSize(new Dimension(screenSize.width / 4, 40));
		add(jPanel0);
		add(jPanel1);
		add(jPanel2);
		add(jPanel3);
		add(jPanel4);
		JLabel oldJLabel = new JLabel("旧密码  ：");
		jPanel1.add(oldJLabel);
		oldPassword = new JPasswordField(15);
		jPanel1.add(oldPassword);
		JLabel newJLabel1 = new JLabel("新密码  ：");
		jPanel2.add(newJLabel1);
		newPassword1 = new JPasswordField(15);
		jPanel2.add(newPassword1);
		JLabel newJLabel2 = new JLabel("再次输入：");
		jPanel3.add(newJLabel2);
		newPassword2 = new JPasswordField(15);
		jPanel3.add(newPassword2);
		JButton saveButton = new JButton("保存");
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Object> map = userService.getPassword(MainFrame.getMainFrame().getUsername());
				if(!map.get("password").equals(String.valueOf(oldPassword.getPassword()))){
					JOptionPane.showMessageDialog(null, "旧密码错误！","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if("".equals(String.valueOf(newPassword1.getPassword()))||"".equals(String.valueOf(newPassword2.getPassword()))){
					JOptionPane.showMessageDialog(null, "设置密码不能为空！","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!String.valueOf(newPassword1.getPassword()).equals(String.valueOf(newPassword2.getPassword()))){
					JOptionPane.showMessageDialog(null, "两次输入密码必须相同！","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(userService.changePassword(Integer.valueOf( map.get("userid").toString()), String.valueOf(newPassword1.getPassword()))==1){
					JOptionPane.showMessageDialog(null, "修改成功！","提示",JOptionPane.WARNING_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "修改失败！","警告",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		jPanel4.add(saveButton);
		JButton button = new JButton("取消");
		jPanel4.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
	
}
