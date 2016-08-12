package com.gry.cable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.gry.cable.view.LoginFrame;

public class MIS
{
	public static void main(String[] args)
	{
			JFrame.setDefaultLookAndFeelDecorated(true);
			@SuppressWarnings("unused")
			LoginFrame loginFrame = new LoginFrame();
	}
}