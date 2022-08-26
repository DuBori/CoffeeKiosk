package kios.menu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import kios.db.Static;

public class PutIn {
	boolean chk=false;
	int arryCount = Integer.parseInt(menuOrder.spinner_1.getValue().toString());
	int i;
	
	public PutIn() {}

	// ########################### 첫번째 인자 생성자 ###########################
	public PutIn(String text) {
		
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		JButton j1 = new JButton("-");
		JButton j2 = new JButton("+");
		JButton j3 = new JButton("x");
		JPanel TestPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		TestPanel.setBorder(new LineBorder(new Color(0,0,0)));
		menuOrder.cupSize = menuOrder.comboBox.getSelectedItem().toString();
		menuOrder.shotCount = Integer.parseInt(menuOrder.comboBox_1.getSelectedItem().toString());
		menuOrder.cost = Integer.parseInt(menuOrder.textField.getText());

		ArrayList<Object> inner_ArrayList = new ArrayList<>(); // 2차원 ArrayList 내부 ArrayList 객체 생성

		inner_ArrayList.add(text);
		inner_ArrayList.add(menuOrder.cupSize);
		inner_ArrayList.add(menuOrder.IceHot);
		inner_ArrayList.add(menuOrder.coffeePrice + menuOrder.sizePrice);
		inner_ArrayList.add(menuOrder.shotCount);
		
		for (i = 0; i < Static.outer_ArrayList.size(); i++) {
			System.out.println("for문 i : "+i);
			if(inner_ArrayList.get(0) == Static.outer_ArrayList.get(i).get(0) &&
					inner_ArrayList.get(1) == Static.outer_ArrayList.get(i).get(1) &&
					inner_ArrayList.get(4) == Static.outer_ArrayList.get(i).get(4)) {
				// 현재 메뉴 옵션과 동일한 주문을 찾으면
				arryCount = arryCount + (int)Static.outer_ArrayList.get(i).get(5);
				Static.outer_ArrayList.remove(i);
				Static.panel_3.remove(i);

				System.out.println("arryCount : "+arryCount);
				System.out.println("if문 i : "+i);
				chk=true;
				break;
			}
			
		}
		inner_ArrayList.add(arryCount);
		inner_ArrayList.add(menuOrder.cost);
		inner_ArrayList.add(System.identityHashCode(inner_ArrayList));
		
		Static.outer_ArrayList.add(i, inner_ArrayList); // 내부 ArrayList를 외부 ArrayList에 추가

		TestPanel.add(new JLabel(text));
		TestPanel.add(j1);
		// - 버튼 액션
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				arryCount -= 1;
				if (arryCount < 1) {
					arryCount = 1;
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));
				} else {
					if(Static.value>0) {
						Static.value-=1;
					}
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));
				}
				
				inner_ArrayList.set(5, arryCount);
				inner_ArrayList.set(6,(menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount);
				
			}
		});

		TestPanel.add(L1);
		TestPanel.add(j2);
		// + 버튼 액션
		j2.addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {
	            QuantityLimit quantityLimit = new QuantityLimit(text);
	            if (quantityLimit.productCount <=arryCount) {
	               JOptionPane.showMessageDialog(null, "재고가 부족합니다. \n 남은 수량 : " + quantityLimit.productCount, "안내", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	               arryCount += 1;
	               Static.value+=1;
	               L1.setText(String.valueOf(arryCount));
	               L2.setText(String.valueOf((menuOrder.coffeePrice+ menuOrder.sizePrice+ menuOrder.addShot) * arryCount));
	               
	               inner_ArrayList.set(5, arryCount);
	               inner_ArrayList.set(6, (menuOrder.coffeePrice+ menuOrder.sizePrice+ menuOrder.addShot) * arryCount);
	            }
	         }
	      });

		TestPanel.add(j3);
		// X 버튼 액션
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < Static.outer_ArrayList.size(); i++) {
					if ((int)Static.outer_ArrayList.get(i).get(7) == System.identityHashCode(inner_ArrayList)) {
						Static.panel_3.remove(TestPanel);
						Static.outer_ArrayList.remove(i);
						menuOrder.scrollPane.setViewportView(Static.panel_3);
						}
				}
				Static.value=0;
				
			}
		});

		TestPanel.add(L2);

		L1.setText(String.valueOf(arryCount));
		L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));
		
		if(chk)
		{
			Static.panel_3.add(TestPanel,i).setVisible(true);
		}else {
			Static.panel_3.add(TestPanel).setVisible(true);
		}
		menuOrder.scrollPane.setViewportView(Static.panel_3);
		
		menuOrder.addShot = 0;
		menuOrder.sizePrice = 0;
		System.out.println("PutIn맨끝 i : "+i);
}

	// ########################### 두번째 인자 생성자 ###########################
	public PutIn(String text, int count) {
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		JButton	j1 = new JButton("-");
		JButton	j2 = new JButton("+");
		JButton	j3 = new JButton("x");

        JPanel TestPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		TestPanel.setBorder(new LineBorder(new Color(0,0,0)));
		menuOrder.cost=Integer.parseInt(menuOrder.textField.getText());

		ArrayList<Object> inner_ArrayList = new ArrayList<>(); // 2차원 ArrayList 내부 ArrayList 객체 생성

		inner_ArrayList.add(text);
		inner_ArrayList.add(menuOrder.cupSize);
		inner_ArrayList.add(menuOrder.IceHot);
		inner_ArrayList.add(menuOrder.coffeePrice + menuOrder.sizePrice);
		inner_ArrayList.add(menuOrder.shotCount);
		for (i = 0; i < Static.outer_ArrayList.size(); i++) {
			
			if(inner_ArrayList.get(0) == Static.outer_ArrayList.get(i).get(0)) {
				
				arryCount = arryCount + (int)Static.outer_ArrayList.get(i).get(5);
				Static.outer_ArrayList.remove(i);
				Static.panel_3.remove(i);
				chk=true;
				break;
			}
		}
		inner_ArrayList.add(arryCount);
		inner_ArrayList.add(menuOrder.cost);
		inner_ArrayList.add(System.identityHashCode(inner_ArrayList));
		
		Static.outer_ArrayList.add(i, inner_ArrayList); // 내부 ArrayList를 외부 ArrayList에 추가

		TestPanel.add(new JLabel(text));
		TestPanel.add(j1);
	// - 버튼 액션
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				arryCount -= 1;
				if (arryCount < 1) {
					arryCount = 1;
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
				} else {
					if(Static.value>0) {
						Static.value-=1;
					}
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf((menuOrder.foodPrice * arryCount)));
				}
				inner_ArrayList.set(5, arryCount);
				inner_ArrayList.set(6,(menuOrder.foodPrice * arryCount));
				
			}
		});

		TestPanel.add(L1);
		TestPanel.add(j2);
		// + 버튼 액션
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	            QuantityLimit quantityLimit = new QuantityLimit(text);
	            if (quantityLimit.productCount <=arryCount) {
	               JOptionPane.showMessageDialog(null, "재고가 부족합니다. \n 남은 수량 : " + quantityLimit.productCount, "안내", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	               arryCount += 1;
	               Static.value+=1;
	               L1.setText(String.valueOf(arryCount));
	               L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
	               inner_ArrayList.set(5, arryCount);
	               inner_ArrayList.set(6, (menuOrder.foodPrice * arryCount) );
	            }
			}
		});

		TestPanel.add(j3);
	// X 버튼 액션
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < Static.outer_ArrayList.size(); i++) {
					if ((int)Static.outer_ArrayList.get(i).get(7) == System.identityHashCode(inner_ArrayList)) {
						Static.panel_3.remove(TestPanel);
						Static.outer_ArrayList.remove(i);
						menuOrder.scrollPane.setViewportView(Static.panel_3);
						}
				}
				
		}
		});

		TestPanel.add(L2);

		L1.setText(String.valueOf(arryCount));
		L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
		
		if(chk)
		{
			Static.panel_3.add(TestPanel,i).setVisible(true);
		}else {
			Static.panel_3.add(TestPanel).setVisible(true);
		}
		menuOrder.scrollPane.setViewportView(Static.panel_3);
}

}