package kios.menu;
 
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	int temp,i;
	public Food_Inner(String text) {
		setBounds(100, 100, 470, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		최종 가격 표시하는 텍스트 필드
		menuOrder.textField = new JTextField();
		menuOrder.textField.setText(String.valueOf(menuOrder.foodPrice));
		menuOrder.textField.setBounds(307, 88, 77, 28);
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
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/cheese.png")));
			break;
		case "tiramisu":
			menuOrder.foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/tiramisu.png")));
			break;
		case "egg":
			menuOrder.foodPrice = 6000;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/egg_inner.png")));
			break;
		case "danhobak":
			menuOrder.foodPrice = 6500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/danhobak_inner.png")));
			break;
		case "scorn":
			menuOrder.foodPrice = 3000;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/scone_inner.png")));
			break;
		case "macaron":
			menuOrder.foodPrice = 2500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/macaron.png")));
			break;
		case "waffle":
			menuOrder.foodPrice = 3500;
			btnNewButton_2.setIcon(new ImageIcon(menuOrder.class.getResource("/image/waffle.png")));
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
		menuOrder.spinner_1.setBounds(245, 92, 37, 22);
		contentPane.add(menuOrder.spinner_1);
		menuOrder.spinner_1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				menuOrder.textField.setText(String.valueOf(viewPrice(menuOrder.foodPrice)));
			}
		});

		JLabel lblNewLabel_2_1 = new JLabel("수량");
		lblNewLabel_2_1.setBounds(213, 95, 30, 15);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("원");
		lblNewLabel_1_1.setBounds(389, 94, 22, 15);
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
		               new PutIn(text,1);
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
	
	public Food_Inner() {
	}

	private int viewPrice(int fP) {
		menuOrder.viewCost = fP * (int) menuOrder.spinner_1.getValue();

		return menuOrder.viewCost;
	}
	
}