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

public class Coffee_Inner_Others extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	int ameriPrice;
	int addShot;
	int viewCost;
	JSpinner spinner_1;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;
	/**
	 * Create the frame.
	 */

	public Coffee_Inner_Others(String text) {

		setBounds(100, 100, 457, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText(String.valueOf(ameriPrice));
		textField.setBounds(307, 144, 77, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 19, 194, 194);
		contentPane.add(btnNewButton_2);
		String m = menuOrder.picNum;
		switch(m) {
		case "latte":
			btnNewButton_2.setIcon(new ImageIcon("src/image/caffelatte.jpg"));
			break;
		case "mocha":
			btnNewButton_2.setIcon(new ImageIcon("src/image/caffemocha.png"));
			break;
		case "cappu":
			btnNewButton_2.setIcon(new ImageIcon("src/image/cappuccino.jpg"));
			break;
		case "caramel":
			btnNewButton_2.setIcon(new ImageIcon("src/image/caramelm.jpg"));
			break;
		case "cold":
			btnNewButton_2.setIcon(new ImageIcon("src/image/coldbrew.jpg"));
			break;
		}


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
				textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
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
					ameriPrice = 4500;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "Grande":
					ameriPrice = 5000;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "Venti":
					ameriPrice = 5500;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
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
				case "1":
					addShot = 500;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "2":
					addShot = 1000;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "3":
					addShot = 1500;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "4":
					addShot = 2000;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				case "5":
					addShot = 2500;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
					break;
				default:
					addShot = 0;
					textField.setText(String.valueOf(viewPrice(ameriPrice, addShot)));
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

	private int viewPrice(int aP, int aS) {
		viewCost = (aP + aS) * (int) spinner_1.getValue();

		return viewCost;
	}
}