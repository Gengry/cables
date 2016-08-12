package com.gry.cable.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gry.cable.common.StructUtil;
import com.gry.cable.service.CableInfoService;
import com.gry.cable.service.ColorInfoService;
import com.gry.cable.service.OrderItemService;
import com.gry.cable.service.OrderService;
import com.gry.cable.service.ProviderInfoService;
import com.gry.cable.service.QualityInfoService;
import com.gry.cable.service.StorageService;
import com.gry.cable.service.UnitInfoService;

public class OrderInInfoFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JTextField jTextField;
	JLabel totalpriceJLabel;
	JTextField totalprice;
	String[] header;
	String[][] data;
	DefaultTableModel tableModel;
	JComboBox<String> box;
	JComboBox<String> modelbox;
	JComboBox<String> standardbox;
	JComboBox<String> unitbox;
	JComboBox<String> qualitybox;
	JComboBox<String> colorbox;
	JComboBox<String> providerbox;
	JTextField numberField;
	JTextField priceField;
	JTextField discountField;
	String[] modelItem;
	String[] StandardItem;
	String[] unitItem;
	String[] qualityItem;
	String[] colorItem;
	int id = 1;
	JTable jTable;
	CableInfoService cableInfoService = new CableInfoService();
	UnitInfoService unitInfoService = new UnitInfoService();
	QualityInfoService qualityInfoService = new QualityInfoService();
	ColorInfoService colorInfoService = new ColorInfoService();
	OrderService orderService = new OrderService();
	OrderItemService orderItemService = new OrderItemService();
	StorageService storageService = new StorageService();
	Map<Object,Map<String,Object>> maps;
	public OrderInInfoFrame() {
		super("进货单", false, true, false, true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 12, screenSize.height / 20,
				screenSize.width / 4*3, screenSize.height / 4*3);
		getContentPane().setLayout(new FlowLayout());
		JLabel jLabel = new JLabel("进货单");
		jLabel.setLocation(screenSize.width / 3-75, 0);
		jLabel.setSize(150, 30);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 23));
		final Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		final String datetime = format.format(date);
		JLabel dateJLable = new JLabel(datetime);
		header = new String[]{"序号","线缆规格","线缆型号","单位","质量","颜色","数量","单价","折扣","小计","备注"};
		tableModel = new DefaultTableModel(data,header);
	
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(screenSize.width / 3*2 -40, 35));
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);
		panel.add(jLabel);
		panel.add(dateJLable);
		panel.add(new JLabel("      供应商："));
		providerbox = getProvider();
		panel.add(providerbox);
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(screenSize.width /6, 35));
		panel.add(panel2);
		totalpriceJLabel = new JLabel("总 计：");
		panel.add(totalpriceJLabel);
		totalprice = new JTextField(6);
		panel.add(totalprice);
		
		jTable = new JTable(tableModel);
		jTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width /3* 2-40,screenSize.height / 3*2 - 200));
		jTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		getContentPane().add(jScrollPane);
		JPanel addPanel = new JPanel(new FlowLayout());
		addPanel.setPreferredSize(new Dimension(screenSize.width /3* 2, 40));
		this.add(addPanel);
		JLabel modelAddJLabel = new JLabel("型号：");
		addPanel.add(modelAddJLabel);
		modelItem = getmodel();
		StandardItem = getStandard(null);
		modelbox = new JComboBox<String>(modelItem);
		addPanel.add(modelbox);
		modelbox.addItemListener(new ItemListener() {
			
			@Override
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
		JLabel standardJLabel = new JLabel("规格");
		addPanel.add(standardJLabel);
		standardbox = new JComboBox<String>(StandardItem);
		addPanel.add(standardbox);
		JLabel unitJLabel = new JLabel("单位");
		addPanel.add(unitJLabel);
		unitItem = getUnitItem();
		unitbox = new JComboBox<String>(unitItem);
		addPanel.add(unitbox);
		JLabel qualityJLabel = new JLabel("质量");
		addPanel.add(qualityJLabel);
		qualityItem = getQualityItem();
		qualitybox = new JComboBox<String>(qualityItem);
		addPanel.add(qualitybox);
		JLabel colorJLabel = new JLabel("颜色");
		addPanel.add(colorJLabel);
		colorItem = getColorItem();
		colorbox = new JComboBox<String>(colorItem);
		addPanel.add(colorbox);
		JLabel numJLabel = new JLabel("数量");
		addPanel.add(numJLabel);
		numberField = new JTextField(6);
		addPanel.add(numberField);
		JLabel priceJLabel = new JLabel("单价");
		addPanel.add(priceJLabel);
		priceField = new JTextField(6);
		addPanel.add(priceField);
		JLabel discountJLabel = new JLabel("折扣");
		addPanel.add(discountJLabel);
		discountField = new JTextField(6);
		addPanel.add(discountField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(screenSize.width /3* 2, 40));
		this.add(buttonPanel);
		
		final JButton addButton = new JButton("添加条目");
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (numberField.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "数量不能为空！","警告",JOptionPane.WARNING_MESSAGE);
					numberField.requestFocus();
					return;
				}
				if(!StructUtil.isDouble(numberField.getText())){
					JOptionPane.showMessageDialog(null, "数量必须是数字！","警告",JOptionPane.WARNING_MESSAGE);
					numberField.requestFocus();
					return;
				}
				if (priceField.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "单价不能为空！","警告",JOptionPane.WARNING_MESSAGE);
					priceField.requestFocus();
					return;
				}
				if(!StructUtil.isDouble(priceField.getText())){
					JOptionPane.showMessageDialog(null, "单价必须是数字！","警告",JOptionPane.WARNING_MESSAGE);
					priceField.requestFocus();
					return;
				}
				if(!StructUtil.isDouble(discountField.getText())){
					JOptionPane.showMessageDialog(null, "折扣必须是数字！","警告",JOptionPane.WARNING_MESSAGE);
					discountField.requestFocus();
					return;
				}
				float num1 = Float.valueOf(numberField.getText()==null||"".equals(numberField.getText())?"0":numberField.getText());
				float price1 = Float.valueOf(priceField.getText()==null||"".equals(priceField.getText())?"0":priceField.getText());
				float discount1 = Float.valueOf(discountField.getText()==null||"".equals(discountField.getText())?"10":discountField.getText());
				float total = num1*price1*discount1/10;
                String []rowValues = {id+++"",modelbox.getSelectedItem().toString(),standardbox.getSelectedItem().toString(),
            		   unitbox.getSelectedItem().toString(),qualitybox.getSelectedItem().toString(),colorbox.getSelectedItem().toString(),numberField.getText(),
            		   priceField.getText(),discountField.getText(),total+""};
                tableModel.addRow(rowValues);  //添加一行
                float totalprice1 = 0;
                for(int i=0;i<jTable.getRowCount();i++){
                	totalprice1 += Float.valueOf(jTable.getValueAt(i, 9).toString()); 
                }
                totalprice.setText(totalprice1+"");
                numberField.setText("");
                priceField.setText("");
			}
		});
        buttonPanel.add(addButton);
        final JButton delButton = new JButton("删除条目");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = jTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                }
            }
        });
        buttonPanel.add(delButton);
        
        final JButton saveButton = new JButton("入     库");
        saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getDataVector().size()>0){
					Map<String, Object> params = maps.get(providerbox.getSelectedItem());
					params.put("companyname", params.get("providername"));
					params.put("ordertime", datetime);
					params.put("inorout", 1);
					params.put("totalprice", Float.valueOf(totalprice.getText()));
					int orderId = orderService.saveOrder(params);
					Vector vector = tableModel.getDataVector();
					if(orderId>0){
						orderId = orderItemService.saveOrderItems(vector, orderId);
						if(orderId>0){
							orderId = storageService.storageIn(vector,providerbox.getSelectedItem().toString(),date);
							if(orderId<0){
								JOptionPane.showMessageDialog(null, "入库失败！","警告",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}else{
							JOptionPane.showMessageDialog(null, "入库失败！","警告",JOptionPane.WARNING_MESSAGE);
							return;
						}
					}else{
						JOptionPane.showMessageDialog(null, "入库失败！","警告",JOptionPane.WARNING_MESSAGE);
						return;
					}
					saveButton.setEnabled(false);
					JOptionPane.showMessageDialog(null, "入库成功！","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
        buttonPanel.add(saveButton);
        final JButton printButton = new JButton("打     印");
        buttonPanel.add(printButton);
        
	}
	private String[] getColorItem() {
		return colorInfoService.getUnit();
	}
	private String[] getQualityItem() {
		return qualityInfoService.getUnit();
	}
	private String[] getUnitItem() {
		return unitInfoService.getUnit();
	}
	private String[] getStandard(String modelname) {
		if(modelname==null){
			return cableInfoService.getStandardByModel(modelItem[0]);
		}
		return cableInfoService.getStandardByModel(modelbox.getSelectedItem().toString());
	}
	private String[] getmodel() {
		return cableInfoService.getCableInfoModel();
	}
	private JComboBox<String> getProvider() {
		ProviderInfoService providerInfoService = new ProviderInfoService();
		List<Map<String,Object>> list = providerInfoService.getAllProvider();
		maps = StructUtil.listToMap(list, "providername");
		String[] items = new String[maps.size()];
		int i = 0;
		for (Entry<Object, Map<String, Object>> entry : maps.entrySet()) {
			
			items[i] =entry.getKey().toString();
			i++;
		}
		JComboBox<String> box = new JComboBox<String>(items);
		box.setPreferredSize(new Dimension(100, 25));
		return box;
	}

	

	
}
