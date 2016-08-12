package com.gry.cable.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainFrame mainFrame;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("进销存管理系统");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取屏幕的边界 // 获取底部任务栏高度screenInsets.bottom
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration());
		screenSize.setSize(screenSize.width - 50, screenSize.height - 2
				* screenInsets.bottom);
		setSize(screenSize);
		setLocation(20, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktopPane = new JDesktopPane();

		desktopPane.setOpaque(true);

		this.setContentPane(desktopPane);
		this.setJMenuBar(createMenuBar());

		// Set up the backgound image
		desktopPane.setBackground(new Color(200, 218, 235));
		// Make dragging a little faster but perhaps uglier.
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

	}

	public static MainFrame getMainFrame() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}

	/**
	 * 创建主窗体的菜单栏
	 * 
	 * @return JMenuBar
	 */
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("基础信息管理");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("线缆信息管理");
		menuItem.addActionListener(MainAction.clickCableInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("客户信息管理");
		menuItem.addActionListener(MainAction.clickCustomerInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("供应商信息管理");
		menuItem.addActionListener(MainAction.clickProviderInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("计量单位管理");
		menuItem.addActionListener(MainAction.clickUnitInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("线缆颜色管理");
		menuItem.addActionListener(MainAction.clickColorInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("线缆质量管理");
		menuItem.addActionListener(MainAction.clickQualityInfoManager());
		menu.add(menuItem);

		menu = new JMenu("进出货管理");
		menuBar.add(menu);
		menuItem = new JMenuItem("进货单");
		menuItem.addActionListener(MainAction.OrderInManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("送货单");
		menuItem.addActionListener(MainAction.OrderOutManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("进货记录查询");
		// menuItem.addActionListener(MainAction.stockCable());
		menu.add(menuItem);
		menuItem = new JMenuItem("送货记录查询");
		// menuItem.addActionListener(MainAction.outportGoods());
		menu.add(menuItem);

		menu = new JMenu("库存管理");
		menuBar.add(menu);
		menuItem = new JMenuItem("库存查询");
		menuItem.addActionListener(MainAction.storageCheck());
		menu.add(menuItem);

		menu = new JMenu("系统管理");
		menuBar.add(menu);
		menuItem = new JMenuItem("更改密码");
//		menuItem.addActionListener(MainAction.changePassword());
		menu.add(menuItem);

		menuItem = new JMenuItem("操作员管理");
//		menuItem.addActionListener(MainAction.operaterManager());
		menu.add(menuItem);

		return menuBar;
	}
}
