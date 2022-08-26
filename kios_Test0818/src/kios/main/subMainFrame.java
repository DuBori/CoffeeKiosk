package kios.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kios.menu.menuOrder;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(subMainFrame.class.getResource("/image/logo.png")));
		setTitle("매장/포장");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		getContentPane().setLayout(new GridLayout(0,2));
        getContentPane().setBackground(new Color(74, 68, 61));
		JLabel lb1 = new JLabel();
		lb1.setIcon(new ImageIcon("kios_Test0818/src/image/store.png"));
		lb1.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				using="매장";
				new menuOrder();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
				
			};
		
		});
			
		JLabel lb2 = new JLabel();
		lb2.setIcon(new ImageIcon("kios_Test0818/src/image/takeOut.png"));
		lb2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				using="매장";
				new menuOrder();
				System.out.println("이용방법 : "+using);
				dispose();  // 기존에 있던 창은 없애주는 메서드.
				
			};
		
		});
		
		
		getContentPane().add(lb1);
        getContentPane().add(lb2);
		
        setLocationRelativeTo(null);
		setVisible(true);
	}

}
