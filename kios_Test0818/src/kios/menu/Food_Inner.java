package kios.menu;
 
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import kios.db.Static;

public class Food_Inner extends JFrame {

	private JPanel contentPane;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	DefaultTableModel model;

	public Food_Inner(String text) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(kios.menu.Food_Inner.class.getResource("/image/logo.png")));
		setBounds(100, 100, 470, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		최종 가격 표시하는 텍스트 필드
		menuOrder.textField = new JTextField();
		menuOrder.textField.setText(String.valueOf(menuOrder.foodPrice));
		menuOrder.textField.setBounds(307, 144, 77, 28);
		contentPane.add(menuOrder.textField);
		menuOrder.textField.setColumns(10);

//		음료 이미지 출력을 위한 공간
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 19, 194, 194);
		contentPane.add(btnNewButton_2);
		String m = menuOrder.picNum;
		switch(m) {
		case "cheese":
			menuOrder.foodPrice = 5500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/americano.jpg"));
			break;
		case "tiramisu":
			menuOrder.foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffelatte.jpg"));
			break;
		case "egg":
			menuOrder.foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caffemocha.png"));
			break;
		case "danhobak":
			menuOrder.foodPrice = 6500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/cappuccino.jpg"));
			break;
		case "scorn":
			menuOrder.foodPrice = 3000;
			btnNewButton_2.setIcon(new ImageIcon("src/images/caramelm.jpg"));
			break;
		case "macaron":
			menuOrder.foodPrice = 2500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		case "waffle":
			menuOrder.foodPrice = 3500;
			btnNewButton_2.setIcon(new ImageIcon("src/images/espresso.jpg"));
			break;
		}
		
		menuOrder.textField.setText(String.valueOf(menuOrder.foodPrice));

//		갯수를 입력받는 스피너
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
		           if(Static.value>0  ) {
		        	   Static.value=0;
		           }
				dispose();
	            QuantityLimit quantityLimit = new QuantityLimit(text);

	            if (quantityLimit.productCount < (int)menuOrder.spinner_1.getValue() + Static.value) {
	               JOptionPane.showMessageDialog(null, "재고가 부족합니다. \n 남은 수량 : " + quantityLimit.productCount, "안내", JOptionPane.INFORMATION_MESSAGE);
	            } else {

		               Static.value+= (int)menuOrder.spinner_1.getValue();
	               new PutIn(text);
	            }
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
				dispose();
				PutIn putIn = new PutIn(text, menuOrder.count);
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
	
	public Food_Inner() {
	}

	private int viewPrice(int fP) {
		menuOrder.viewCost = fP * (int) menuOrder.spinner_1.getValue();

		return menuOrder.viewCost;
	}
	
}