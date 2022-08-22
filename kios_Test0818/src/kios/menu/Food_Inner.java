package kios.menu;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

public class Food_Inner extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	int foodPrice;
	int viewCost;
	JSpinner spinner_1;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;
	int count,cost;
	
	/**
	 * Create the frame.
	 */
	public Food_Inner(String text) {

		setBounds(100, 100, 470, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		최종 가격 표시하는 텍스트 필드
		textField = new JTextField();
		textField.setText(String.valueOf(foodPrice));
		textField.setBounds(307, 144, 77, 28);
		contentPane.add(textField);
		textField.setColumns(10);

//		음료 이미지 출력을 위한 공간
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 19, 194, 194);
		contentPane.add(btnNewButton_2);
		String m = menuOrder.picNum;
		switch(m) {
		case "cheese":
			foodPrice = 5500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/americano.jpg"));
			break;
		case "tiramisu":
			foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffelatte.jpg"));
			break;
		case "egg":
			foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffemocha.png"));
			break;
		case "danhobak":
			foodPrice = 6500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/cappuccino.jpg"));
			break;
		case "scorn":
			foodPrice = 3000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caramelm.jpg"));
			break;
		case "macaron":
			foodPrice = 2500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		case "waffle":
			foodPrice = 3500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		}
		
		textField.setText(String.valueOf(foodPrice));

//		갯수를 입력받는 스피너
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
				textField.setText(String.valueOf(viewPrice(foodPrice)));
			}
		});

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
			// TODO HOT, ICE 가지고 올 String 따오기
			// TODO 라벨값 따오기
				dispose();
				
				count=Integer.parseInt(spinner_1.getValue().toString());	
				cost=Integer.parseInt(textField.getText());
				new updateMenu(text,foodPrice,count);
				menuOrder.textArea.append(text+"\t"+"\t"+"\t"+"\t"+count+"\t"+cost+"\n");
				
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
		setVisible(true);

	}
	
	public Food_Inner() {
	}

	private int viewPrice(int fP) {
		viewCost = fP * (int) spinner_1.getValue();

		return viewCost;
	}
	
}