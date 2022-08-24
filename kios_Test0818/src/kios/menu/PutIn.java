package kios.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PutIn {
	
	int arryCount = Integer.parseInt(menuOrder.spinner_1.getValue().toString());
	int removeCount=menuOrder.removeCount;
	
	public PutIn() {
		// TODO Auto-generated constructor stub
	}
	public PutIn(String text) {
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		JButton j1 = new JButton("-");
		JButton j2 = new JButton("+");
		JButton j3 = new JButton("x");

		menuOrder.cupSize = menuOrder.comboBox.getSelectedItem().toString();
		menuOrder.shotCount = Integer.parseInt(menuOrder.comboBox_1.getSelectedItem().toString());
		menuOrder.cost = Integer.parseInt(menuOrder.textField.getText());

		ArrayList<Object> inner_ArrayList = new ArrayList<>(); // 2차원 ArrayList 내부 ArrayList 객체 생성

		inner_ArrayList.add(text);
		inner_ArrayList.add(menuOrder.cupSize);
		inner_ArrayList.add(menuOrder.IceHot);
		inner_ArrayList.add(menuOrder.coffeePrice + menuOrder.sizePrice);
		inner_ArrayList.add(menuOrder.shotCount);
		inner_ArrayList.add(arryCount);
		inner_ArrayList.add(menuOrder.cost);
		System.out.println(text + "\t" + menuOrder.cupSize + "\t" + menuOrder.IceHot + "\t" + menuOrder.coffeePrice + menuOrder.sizePrice + "\t"
				+ menuOrder.shotCount + "\t" + arryCount + "\t" + menuOrder.cost);

		menuOrder.outer_ArrayList.add(inner_ArrayList); // 내부 ArrayList를 외부 ArrayList에 추가
		menuOrder.Panel.add(new JLabel(text));
		menuOrder.Panel.add(j1);
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
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));
				}
				inner_ArrayList.set(5, arryCount);
			}
		});

		menuOrder.Panel.add(L1);
		menuOrder.Panel.add(j2);
		// + 버튼 액션
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				arryCount += 1;
				
				L1.setText(String.valueOf(arryCount));
				L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));
				inner_ArrayList.set(5, arryCount);
			}

		});

		menuOrder.Panel.add(j3);
		// X 버튼 액션
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuOrder.panel_3.remove(menuOrder.Panel);
				menuOrder.outer_ArrayList.remove(removeCount);
				menuOrder.removeCount--;
			}
		});

		menuOrder.Panel.add(L2);

		L1.setText(String.valueOf(arryCount));
		L2.setText(String.valueOf((menuOrder.coffeePrice + menuOrder.sizePrice + menuOrder.addShot) * arryCount));

		menuOrder.panel_3.add(menuOrder.Panel).setVisible(true);

		menuOrder.removeCount++;
	}
	
	public PutIn(String text, int count) {
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		JButton	j1 = new JButton("-");
		JButton	j2 = new JButton("+");
		JButton	j3 = new JButton("x");
		arryCount=Integer.parseInt(menuOrder.spinner_1.getValue().toString());	
		menuOrder.cost=Integer.parseInt(menuOrder.textField.getText());
		
		menuOrder.Panel.add(new JLabel(text));
		menuOrder.Panel.add(j1);
		j1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				arryCount -= 1;
				if(arryCount < 1) {
					arryCount = 1;
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
				}else {
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
				}
				
			}
		});
			
		menuOrder.Panel.add(L1);
		menuOrder.Panel.add(j2);
		j2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				arryCount += 1;
						
					L1.setText(String.valueOf(arryCount));
					L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
				
			}
		});
		menuOrder.Panel.add(j3);
		j3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("static removeCount 값 : "+menuOrder.removeCount+"\n지역변수 removeCount 값 : "+removeCount);
				menuOrder.panel_3.remove(menuOrder.Panel);
				menuOrder.outer_ArrayList.remove(removeCount);
				menuOrder.removeCount--;
			}
		});
		
		menuOrder.Panel.add(L2);
		
		L1.setText(String.valueOf(arryCount));
		L2.setText(String.valueOf(menuOrder.foodPrice * arryCount));
		System.out.println(String.valueOf(arryCount)+"\t"+String.valueOf(menuOrder.foodPrice * arryCount));
		
		menuOrder.panel_3.add(menuOrder.Panel);
		menuOrder.scrollPane.setViewportView(menuOrder.panel_3);
		
		menuOrder.removeCount++;
	}

}