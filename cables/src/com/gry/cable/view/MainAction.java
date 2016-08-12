package com.gry.cable.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gry.cable.service.ColorInfoService;
import com.gry.cable.service.QualityInfoService;
import com.gry.cable.service.UnitInfoService;


public class MainAction
{
	static int height;
	
	public static ActionListener clickCableInfoManager()
	{

		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CableInfoFrame cableInfoFrame = new CableInfoFrame();
				MainFrame.getMainFrame().getContentPane().add(cableInfoFrame);
				cableInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener clickCustomerInfoManager()
	{
		
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CustomerInfoFrame customerInfoFrame = new CustomerInfoFrame();
				MainFrame.getMainFrame().getContentPane().add(customerInfoFrame);
				customerInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener clickProviderInfoManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ProviderInfoFrame providerInfoFrame = new ProviderInfoFrame();
				MainFrame.getMainFrame().getContentPane().add(providerInfoFrame);
				providerInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener clickUnitInfoManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				UnitInfoService unitInfoService = new UnitInfoService();
				UnitInfoFrame unitInfoFrame = new UnitInfoFrame(unitInfoService.getUnitInfo());
				MainFrame.getMainFrame().getContentPane().add(unitInfoFrame);
				unitInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener clickColorInfoManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ColorInfoService unitInfoService = new ColorInfoService();
				ColorInfoFrame unitInfoFrame = new ColorInfoFrame(unitInfoService.getUnitInfo());
				MainFrame.getMainFrame().getContentPane().add(unitInfoFrame);
				unitInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener clickQualityInfoManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				QualityInfoService unitInfoService = new QualityInfoService();
				QualityInfoFrame unitInfoFrame = new QualityInfoFrame(unitInfoService.getUnitInfo());
				MainFrame.getMainFrame().getContentPane().add(unitInfoFrame);
				unitInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener OrderInManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				OrderInInfoFrame orderInInfoFrame = new OrderInInfoFrame();
				MainFrame.getMainFrame().getContentPane().add(orderInInfoFrame);
				orderInInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener OrderOutManager() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				OrderOutInfoFrame orderOutInfoFrame = new OrderOutInfoFrame();
				MainFrame.getMainFrame().getContentPane().add(orderOutInfoFrame);
				orderOutInfoFrame.setVisible(true);
			}
		};
	}
	public static ActionListener storageCheck() {
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StorageCheckFrame storageCheckFrame = new StorageCheckFrame();
				MainFrame.getMainFrame().getContentPane().add(storageCheckFrame);
				storageCheckFrame.setVisible(true);
			}
		};
	}


}
