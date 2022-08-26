package kios.main;

import javax.swing.*;
import javax.swing.border.LineBorder;

import kios.menu.menuOrder;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

public class subMainFrame extends JFrame {

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	String using;
	
	public subMainFrame() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("매장/포장");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		getContentPane().setLayout(new GridLayout(0,2));
		
		JLabel store = new JLabel("");
		store.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						using="매장";
						new menuOrder();
						System.out.println("이용방법 : "+using);
						dispose();  //기존에 있던 창은 없애주는 메서드.
						
					}
				});
		store.setIcon(new ImageIcon(subMainFrame.class.getResource("/image/store.png")));
		getContentPane().add(store);
		
		JLabel takeout = new JLabel("");
		takeout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				using="포장";
				new menuOrder();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
			}
		});
		takeout.setIcon(new ImageIcon(subMainFrame.class.getResource("/image/takeout.png")));
		getContentPane().add(takeout);
		
        
		setVisible(true);
	}

}