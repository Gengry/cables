package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gry.cable.service.CableInfoService;
import com.gry.cable.service.ColorInfoService;
import com.gry.cable.service.QualityInfoService;
import com.gry.cable.service.StorageService;
import com.gry.cable.service.UnitInfoService;

public class StorageCheckFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JTable jTable;
	String[] header;
	String[][] data;
	DefaultTableModel tableModel;
	JComboBox<String> modelBox;
	JComboBox<String> standardbox;
	JComboBox<String> unitbox;
	JComboBox<String> qualitybox;
	JComboBox<String> colorbox;
	String[] unitItem;
	String[] qualityItem;
	String[] colorItem;
	String[] modelItem;
	String[] StandardItem;
	StorageService storageService = new StorageService();
	CableInfoService cableInfoService = new CableInfoService();
	UnitInfoService unitInfoService = new UnitInfoService();
	QualityInfoService qualityInfoService = new QualityInfoService();
	ColorInfoService colorInfoService = new ColorInfoService();
	public StorageCheckFrame(){
		super("库存盘点", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 12, screenSize.height / 20,
				screenSize.width / 4*3, screenSize.height / 4*3);
		getContentPane().setLayout(new FlowLayout());
		JLabel jLabel = new JLabel("库存查询");
		jLabel.setLocation(screenSize.width / 3-75, 0);
		jLabel.setSize(150, 30);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		header = new String[]{"序号","线缆型号","线缆规格","单位","质量","颜色","数量","供应商","入库时间","仓库地址","备注"};
		tableModel = new DefaultTableModel(data,header);
	
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 30));
		panel.setLayout(null);
		getContentPane().add(panel);
		panel.add(jLabel);
		Panel search = new Panel();
		search.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		search.setLayout(new FlowLayout());
		
		modelItem = getModelItem();
		modelBox = new JComboBox<String>(modelItem);
		search.add(new JLabel("型号："));
		search.add(modelBox);
		modelBox.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					standardbox.removeAllItems();
					StandardItem = getStandard(e.getItem().toString());
					for (String item : StandardItem) {
						standardbox.addItem(item);
					}
					
				}
			}
		});
		search.add(new JLabel("规格："));
		StandardItem = getStandard(null);
		standardbox = new JComboBox<String>(StandardItem);
		search.add(standardbox);
		
		JLabel unitJLabel = new JLabel("单位");
		search.add(unitJLabel);
		unitItem = getUnitItem();
		unitbox = new JComboBox<String>(unitItem);
		search.add(unitbox);
		JLabel qualityJLabel = new JLabel("质量");
		search.add(qualityJLabel);
		qualityItem = getQualityItem();
		qualitybox = new JComboBox<String>(qualityItem);
		search.add(qualitybox);
		JLabel colorJLabel = new JLabel("颜色");
		search.add(colorJLabel);
		colorItem = getColorItem();
		colorbox = new JComboBox<String>(colorItem);
		search.add(colorbox);
		
		getContentPane().add(search);
		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String model = modelBox.getSelectedItem().toString();
				String standard = standardbox.getSelectedItem().toString();
				data = storageService.getStorageCheck(model,standard,unitbox.getSelectedItem().toString(),qualitybox.getSelectedItem().toString(),colorbox.getSelectedItem().toString());
				tableModel.setDataVector(data,header);
			}
		});
		search.add(searchButton);
		
		jTable = new JTable(tableModel);
		jTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width /3* 2-40,screenSize.height / 4*3 - 150));
		JScrollPane jScrollPane = new JScrollPane(jTable);
		getContentPane().add(jScrollPane);

	}
	
	private String[] getColorItem() {
		return colorInfoService.getUnit1();
	}
	private String[] getQualityItem() {
		return qualityInfoService.getUnit1();
	}
	private String[] getUnitItem() {
		return unitInfoService.getUnit1();
	}
	
	private String[] getStandard(String modelname) {
		if(modelname==null){
			return cableInfoService.getStandardByModel1(modelItem[1]);
		}
		return cableInfoService.getStandardByModel1(modelBox.getSelectedItem().toString());
	}
	private String[] getModelItem() {
		return cableInfoService.getCableInfoModel1();
	}
	
}
