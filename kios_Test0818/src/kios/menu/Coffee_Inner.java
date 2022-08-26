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

public class Coffee_Inner extends JFrame {

	private JPanel contentPane;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;
	
	public Coffee_Inner(String text) {

		setBounds(100, 100, 470, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

//		최종 가격 표시하는 텍스트 필드.
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
		switch (m) {
		case "ameri":
			menuOrder.coffeePrice = 4500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/americano.jpg"));
			break;
		case "latte":
			menuOrder.coffeePrice = 5000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffelatte.jpg"));
			break;
		case "mocha":
			menuOrder.coffeePrice = 5500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffemocha.png"));
			break;
		case "cappu":
			menuOrder.coffeePrice = 5000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/cappuccino.jpg"));
			break;
		case "caramel":
			menuOrder.coffeePrice = 5900;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caramelm.jpg"));
			break;
		case "espresso":
			menuOrder.coffeePrice = 4200;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		}

		menuOrder.textField.setText(String.valueOf(menuOrder.coffeePrice));

//		온도(HOT or ICE) 선택 라디오 버튼
		JRadioButton rdbtnHot = new JRadioButton("HOT", true);
		rdbtnHot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/americano.jpg"));
				menuOrder.IceHot = rdbtnHot.getText();
			}
		});
		menuOrder.IceHot = rdbtnHot.getText();

		rdbtnHot.setBounds(212, 30, 66, 23);
		contentPane.add(rdbtnHot);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("ICE");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/ice_americano.jpg"));
				menuOrder.IceHot = rdbtnNewRadioButton.getText();
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
		menuOrder.comboBox.setBounds(252, 89, 68, 23);
		contentPane.add(menuOrder.comboBox);

		JLabel lblNewLabel_2 = new JLabel("사이즈");
		lblNewLabel_2.setBounds(210, 92, 40, 15);
		contentPane.add(lblNewLabel_2);

//		샷 추가를 입력받는 콤보박스
		String[] shot = { "0", "1", "2", "3", "4", "5" };
		menuOrder.comboBox_1 = new JComboBox(shot);
		menuOrder.comboBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String d = (String) menuOrder.comboBox_1.getSelectedItem();
				switch (d) {
				case "0":
					menuOrder.addShot = 0;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "1":
					menuOrder.addShot = 500;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "2":
					menuOrder.addShot = 1000;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "3":
					menuOrder.addShot = 1500;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "4":
					menuOrder.addShot = 2000;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				case "5":
					menuOrder.addShot = 2500;
					menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.coffeePrice, menuOrder.sizePrice, menuOrder.addShot)));
					break;
				}
			}
		});
		menuOrder.comboBox_1.setBounds(375, 89, 69, 23);
		contentPane.add(menuOrder.comboBox_1);

		JLabel lblNewLabel_3 = new JLabel("샷 추가");
		lblNewLabel_3.setBounds(330, 92, 40, 15);
		contentPane.add(lblNewLabel_3);

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
	        	 System.out.println("str은 "+Static.str);
	        	 QuantityLimit quantityLimit = new QuantityLimit(text);
	        	 if(!Static.str.equals(text)) {
	        		 Static.value=0;
	        	 }
	        	Static.str=text;
	        	 dispose();
	           
	            if (quantityLimit.productCount < (int)menuOrder.spinner_1.getValue() + Static.value) {
	               JOptionPane.showMessageDialog(null, "재고가 부족합니다. \n 남은 수량 : " + quantityLimit.productCount, "안내", JOptionPane.INFORMATION_MESSAGE);
	            } else {
		               Static.value+= (int)menuOrder.spinner_1.getValue();
	               new PutIn(text);
	            }
	            System.out.println(Static.value);
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

	public Coffee_Inner() {
	}

	public int viewPrice(int aP, int sP, int aS) {
		menuOrder.viewCost = (aP + sP + aS) * (int) menuOrder.spinner_1.getValue();

		return menuOrder.viewCost;
	}

}