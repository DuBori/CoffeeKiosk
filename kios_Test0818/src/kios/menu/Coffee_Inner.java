package kios.menu;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

public class Coffee_Inner extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	int coffeePrice;
	int sizePrice;
	int addShot;
	int viewCost;
	JSpinner spinner_1;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;
	String IceHot,cupSize;
	int shotCount,count,cost;
	
	/**
	 * Create the frame.
	 */
	public Coffee_Inner(String text) {

		setBounds(100, 100, 457, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText(String.valueOf(coffeePrice));
		textField.setBounds(307, 144, 77, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 19, 194, 194);
		contentPane.add(btnNewButton_2);
		String m = menuOrder.picNum;
		switch(m) {
		case "ameri":
			coffeePrice = 4500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/americano.jpg"));
			break;
		case "latte":
			coffeePrice = 5000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffelatte.jpg"));
			break;
		case "mocha":
			coffeePrice = 5500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffemocha.png"));
			break;
		case "cappu":
			coffeePrice = 5000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/cappuccino.jpg"));
			break;
		case "caramel":
			coffeePrice = 5900;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caramelm.jpg"));
			break;
		case "espresso":
			coffeePrice = 4200;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		}
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("ICE");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/ice_americano.jpg"));
				IceHot=rdbtnNewRadioButton.getText();
			}
		});
		rdbtnNewRadioButton.setBounds(321, 30, 67, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnHot = new JRadioButton("HOT");
		rdbtnHot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnNewButton_2.setIcon(new ImageIcon("image/americano.jpg"));
				IceHot=rdbtnHot.getText();
			}
		});

		rdbtnHot.setBounds(212, 30, 66, 23);
		contentPane.add(rdbtnHot);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnHot);

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★edited

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		JComponent comp = spinner_1.getEditor();
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		formatter.setCommitsOnValidEdit(true);
		spinner_1.setBounds(245, 147, 37, 22);
		contentPane.add(spinner_1);
		spinner_1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				// 입력값을 받아 단가와 곱한 값을 최종가격으로 넘겨준다
				// 아메리카노 단가 : 4500원
				textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
			}
		});

		String[] size = { "사이즈", "Tall", "Grande", "Venti" };
		JComboBox comboBox = new JComboBox(size);
		comboBox.setSelectedItem("사이즈");
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String s = (String) comboBox.getSelectedItem();
				switch (s) {
				case "Tall":
					sizePrice = 0;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "Grande":
					sizePrice = 500;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "Venti":
					sizePrice = 1000;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				default:
					sizePrice = 0;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				}
				// 사이즈별 가격 * 수량을 텍스트필드에 반영한다

			}
		});
		comboBox.setBounds(210, 89, 68, 23);
		contentPane.add(comboBox);

		String[] shot = { "샷 추가", "1", "2", "3", "4", "5" };
		JComboBox comboBox_1 = new JComboBox(shot);
		comboBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String d = (String) comboBox_1.getSelectedItem();
				switch (d) {
				case "샷 추가":
					addShot = 0;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "1":
					addShot = 500;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "2":
					addShot = 1000;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "3":
					addShot = 1500;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "4":
					addShot = 2000;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				case "5":
					addShot = 2500;
					textField.setText(String.valueOf(viewPrice(coffeePrice, sizePrice, addShot)));
					break;
				}
			}
		});
		comboBox_1.setBounds(319, 89, 69, 23);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_2_1 = new JLabel("수량");
		lblNewLabel_2_1.setBounds(213, 150, 30, 15);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("원");
		lblNewLabel_1_1.setBounds(389, 157, 22, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("담기");
		btnNewButton.setBounds(204, 184, 117, 29);
		contentPane.add(btnNewButton);
		

		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO HOT, ICE 가지고 올 String 따오기
			// TODO 라벨값 따오기
				dispose();
				cupSize=comboBox.getSelectedItem().toString();
				shotCount=Integer.parseInt(comboBox_1.getSelectedItem().toString());
				count=Integer.parseInt(spinner_1.getValue().toString());	
				cost=Integer.parseInt(textField.getText());
				new updateMenu(text,cupSize,IceHot,coffeePrice+sizePrice,shotCount,count,cost);
				menuOrder.textArea.append(text+"\t"+cupSize+"\t"+IceHot+"\t"+shotCount+"\n");
				menuOrder.textArea1.append(count+"\n");
				menuOrder.textArea2.append(cost+"\n");
			}
		});
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setBounds(321, 184, 117, 29);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		setVisible(true);

	}
	
	public Coffee_Inner() {
	}

	private int viewPrice(int aP, int sP, int aS) {
		viewCost = (aP + sP + aS) * (int) spinner_1.getValue();

		return viewCost;
	}
	
}