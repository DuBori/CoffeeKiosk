package kios.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kios.menu.menuOrder;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class subMainFrame extends JFrame {

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	String using;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public subMainFrame() {
		setTitle("매장/포장");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		getContentPane().setLayout(new GridLayout(0,2));
        
		JButton btn1 = new JButton("매장이용");
		btn1.setIcon(new ImageIcon("kios_Test0818/src/image/store.png"));
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				using="매장";
				new menuOrder();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
				
			}
		});
		JButton btn2 = new JButton("포장");
		btn2.setIcon(new ImageIcon("kios_Test0818/src/image/takeOut.png"));
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				using="포장";
				new menuOrder();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
			}
		});
		
		
		getContentPane().add(btn1);
        getContentPane().add(btn2);
		
        setLocationRelativeTo(null);
		setVisible(true);
	}

}
