package kios.menu;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import kios.db.Static;

public class NonCoffee_Inner extends JFrame {

	private JPanel contentPane;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;
	int temp,i;
	public NonCoffee_Inner(String text) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(kios.menu.NonCoffee_Inner.class.getResource("/image/logo.png")));
		setBounds(100, 100, 470, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		최종 가격 표시하는 텍스트 필드
		menuOrder.textField = new JTextField();
		menuOrder.textField.setText(String.valueOf(menuOrder.coffeePrice));
		menuOrder.textField.setBounds(307, 144, 77, 28);
		contentPane.add(menuOrder.textField);
		menuOrder.textField.setColumns(10);

//		음료 이미지 출력을 위한 공간
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 19, 194, 194);
		contentPane.add(btnNewButton_2);
		String m = menuOrder.picNum;
		switch(m) {
		case "chamo":
			menuOrder.coffeePrice = 4500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/chamomile.png")));
			break;
		case "earlgrey":
			menuOrder.coffeePrice = 4500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/earlgrey.png")));
			break;
		case "jamong":
			menuOrder.coffeePrice = 5700;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/jamong.png")));
			break;
		case "saenggang":
			menuOrder.coffeePrice = 5200;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/saenggang.png")));
			break;
		case "green":
			menuOrder.coffeePrice = 5100;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/green.png")));
			break;
		case "maesil":
			menuOrder.coffeePrice = 5200;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/maesil.png")));
			break;
		case "yuja":
			menuOrder.coffeePrice = 5500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/yuja.png")));
			break;
		}
		
		menuOrder.textField.setText(String.valueOf(menuOrder.coffeePrice));
		
//		온도(HOT or ICE) 선택 라디오 버튼
		JRadioButton rdbtnHot = new JRadioButton("HOT", true);
		rdbtnHot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/americano.jpg"));
				menuOrder.IceHot=rdbtnHot.getText();
			}
		});
		menuOrder.IceHot=rdbtnHot.getText();
		
		rdbtnHot.setBounds(212, 30, 66, 23);
		contentPane.add(rdbtnHot);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("ICE");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/ice_americano.jpg"));
				menuOrder.IceHot=rdbtnNewRadioButton.getText();
			}
		});
		rdbtnNewRadioButton.setBounds(321, 30, 67, 23);
		contentPane.add(rdbtnNewRadioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnHot);

//		음료 갯수를 입력받는 스피너
		menuOrder.spinner_1 = new JSpinner();
		menuOrder.spinner_1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		JComponent comp = menuOrder.spinner_1.getEditor();
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		formatter.setCommitsOnValidEdit(true);
		menuOrder.spinner_1.setBounds(245, 147, 37, 22);
		contentPane.add(menuOrder.spinner_1);
		menuOrder.spinner_1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
			}
		});

//		사이즈를 입력받는 콤보박스
		String[] size = { "Tall", "Grande", "Venti" };
		menuOrder.comboBox = new JComboBox(size);
		menuOrder.comboBox.setSelectedItem("Tall");
		menuOrder.comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String s = (String) menuOrder.comboBox.getSelectedItem();
				switch (s) {
				case "Tall":
					menuOrder.sizePrice = 0;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "Grande":
					menuOrder.sizePrice = 500;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "Venti":
					menuOrder.sizePrice = 1000;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				}
				// 사이즈별 가격 * 수량을 텍스트필드에 반영한다

			}
		});
		menuOrder.comboBox.setBounds(282, 89, 100, 23);
		contentPane.add(menuOrder.comboBox);

		JLabel lblNewLabel_2 = new JLabel("사이즈");
		lblNewLabel_2.setBounds(240, 92, 40, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("수량");
		lblNewLabel_2_1.setBounds(213, 150, 30, 15);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("원");
		lblNewLabel_1_1.setBounds(389, 157, 22, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btn_putIn = new JButton("담기");
		
		btn_putIn.setBounds(204, 184, 117, 29);
		contentPane.add(btn_putIn);

		btn_putIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
	        	 Static.value=(int)menuOrder.spinner_1.getValue();
	        	 ArrayList<ArrayList<Object>> list =Static.outer_ArrayList;

	        	 QuantityLimit quantityLimit = new QuantityLimit(text);
	        
	        	 temp=list.size();
	        	 while(temp-->0)
	        	 {               

	        		if( list.get(i).get(0).equals(text))
	        		{

	        			Static.value=(int)menuOrder.spinner_1.getValue()+(int) list.get(i).get(5);
	        			break; 
	        		}else {     	
	        			Static.value=(int)menuOrder.spinner_1.getValue();
	        			 i++;
	        		}
	        	 }
	        	 
	           //10개 였는데 9개 시키고 다음 1개를 주문시 Static value = 0 -> 9이 되며, 
	            if (quantityLimit.productCount < Static.value) { // 1 < 1+9
	               JOptionPane.showMessageDialog(null, "재고가 부족합니다. \n 남은 수량 : " + quantityLimit.productCount, "안내", JOptionPane.INFORMATION_MESSAGE);
	            } else {
		               new PutIn(text);
	            }
	         }
		});
		
		JButton btn_cancel = new JButton("취소");
		btn_cancel.setBounds(330, 184, 117, 29);
		contentPane.add(btn_cancel);
		
		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public NonCoffee_Inner() {
	}

	private int viewPrice(int aP, int sP, int aS) {
		menuOrder.viewCost = (aP + sP + aS) * (int) menuOrder.spinner_1.getValue();

		return menuOrder.viewCost;
	}
	
}