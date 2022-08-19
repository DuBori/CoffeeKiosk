package kios.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kios.menu.Menu_Order;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class subMainFrame extends JFrame {

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	public String using;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		subMainFrame frame = new subMainFrame();
	}

	/**
	 * Create the frame.
	 */
	public subMainFrame() {
		setTitle("매장/포장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		getContentPane().setLayout(new GridLayout(0,2));
        
		JButton btn1 = new JButton("매장이용");
		btn1.setIcon(new ImageIcon(subMainFrame.class.getResource("/image/store.png")));
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				using="매장";
				new Menu_Order();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
				
			}
		});
		JButton btn2 = new JButton("포장");
		btn2.setIcon(new ImageIcon(subMainFrame.class.getResource("/image/takeOut.png")));
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				using="포장";
				new Menu_Order();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
			}
		});
		
		
		getContentPane().add(btn1);
        getContentPane().add(btn2);
		
        
		setVisible(true);
	}

}
