package kios.menu;
 

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import kios.mileage.Ex_Payment;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class menuOrder extends JFrame {

 
	private JPanel contentPane;
	public static JTextArea textArea;
	static String picNum;

	public static void main(String[] args) {
		new menuOrder();
	}
	 
	public menuOrder() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane_Main = new JTabbedPane(JTabbedPane.TOP);	// 메인 tab

		JPanel panel = new JPanel();
		tabbedPane_Main.addTab("Coffee", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_Coffee = new JTabbedPane(JTabbedPane.TOP);	// Coffee 내부 Tab
		tabbedPane_Coffee.setBounds(0, -27, 685, 472);
		panel.add(tabbedPane_Coffee);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_Coffee.addTab("New tab", null, panel_8, null);
		panel_8.setLayout(null);
		
		JButton btnNewButton_1_1 = new JButton("아메리카노");
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "ameri";
				new Coffee_Inner(btnNewButton_1_1.getText());
			}

		});
		btnNewButton_1_1.setBounds(12, 100, 211, 188);
		panel_8.add(btnNewButton_1_1);
		
		
		JButton btnNewButton_1 = new JButton("카페라떼");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "latte";
				new Coffee_Inner(btnNewButton_1.getText());
			}

		});
		btnNewButton_1.setBounds(235, 100, 211, 188);
		panel_8.add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("카페모카");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "mocha";
				new Coffee_Inner(btnNewButton_2.getText());
			}

		});
		btnNewButton_2.setBounds(458, 100, 211, 188);
		panel_8.add(btnNewButton_2);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_Coffee.addTab("New tab", null, panel_9, null);
		panel_9.setLayout(null);
		
		
		JButton btnNewButton_3 = new JButton("카푸치노");

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "cappu";
				new Coffee_Inner(btnNewButton_3.getText());
			}
		});
		btnNewButton_3.setBounds(12, 100, 211, 188);
		panel_9.add(btnNewButton_3);
		
		
		JButton btnNewButton_4 = new JButton("카라멜마키아또");

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "caramel";
				new Coffee_Inner(btnNewButton_4.getText());
			}
		});
		btnNewButton_4.setBounds(235, 100, 211, 188);
		panel_9.add(btnNewButton_4);
		
		
		JButton btnNewButton_5 = new JButton("에스프레소");

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "espresso";
				new Coffee_Inner(btnNewButton_5.getText());
			}
		});
		btnNewButton_5.setBounds(458, 100, 211, 188);
		panel_9.add(btnNewButton_5);
		
		
		JButton btnNewButton_7_1_1 = new JButton("이전");
		btnNewButton_7_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Coffee.getSelectedIndex();
				if(nowTabNum <= 0) {
					tabbedPane_Coffee.setSelectedIndex(0);
				}else {
					tabbedPane_Coffee.setSelectedIndex(nowTabNum-1);
				}
			}
		});
		btnNewButton_7_1_1.setBounds(10, 454, 97, 23);
		panel.add(btnNewButton_7_1_1);
		
		JButton btnNewButton_8_1_1 = new JButton("다음");
		btnNewButton_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Coffee.getSelectedIndex();
				if(nowTabNum >= 1) {
					tabbedPane_Coffee.setSelectedIndex(1);
				}else {
					tabbedPane_Coffee.setSelectedIndex(nowTabNum+1);
				}
			}
		});
		btnNewButton_8_1_1.setBounds(576, 454, 97, 23);
		panel.add(btnNewButton_8_1_1);

		

		JPanel panel_1 = new JPanel();

		tabbedPane_Main.addTab("Non-Coffee", null, panel_1, null);

		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_NonCoffee = new JTabbedPane(JTabbedPane.TOP);	// NonCoffee 내부 Tab
		tabbedPane_NonCoffee.setBounds(0, -27, 685, 472);
		panel_1.add(tabbedPane_NonCoffee);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);
		
		JButton btnNewButton_6 = new JButton("카모마일티");
		btnNewButton_6.setBounds(12, 10, 211, 188);
		panel_6.add(btnNewButton_6);
		
		JButton btnNewButton_6_1 = new JButton("얼그레이 티");
		btnNewButton_6_1.setBounds(235, 10, 211, 188);
		panel_6.add(btnNewButton_6_1);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_7, null);
		panel_7.setLayout(null);
		
		JButton btnNewButton_6_2 = new JButton("매실차");
		btnNewButton_6_2.setBounds(457, 12, 211, 188);
		panel_7.add(btnNewButton_6_2);
		
		JButton btnNewButton_6_3 = new JButton("유자차");
		btnNewButton_6_3.setBounds(11, 234, 211, 188);
		panel_7.add(btnNewButton_6_3);
		
		JButton btnNewButton_6_3_1 = new JButton("우유");
		btnNewButton_6_3_1.setBounds(457, 234, 211, 188);
		panel_7.add(btnNewButton_6_3_1);
		
		JButton btnNewButton_6_3_2 = new JButton("대추차");
		btnNewButton_6_3_2.setBounds(234, 234, 211, 188);
		panel_7.add(btnNewButton_6_3_2);
		
		JButton btnNewButton_6_3_1_1 = new JButton("생강차");
		btnNewButton_6_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_6_3_1_1.setBounds(11, 12, 211, 188);
		panel_7.add(btnNewButton_6_3_1_1);
		
		JButton btnNewButton_6_3_1_2 = new JButton("녹차");
		btnNewButton_6_3_1_2.setBounds(234, 12, 211, 188);
		panel_7.add(btnNewButton_6_3_1_2);
		
		JButton btnNewButton_7_1 = new JButton("이전");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_NonCoffee.getSelectedIndex();
				if(nowTabNum <= 0) {
					tabbedPane_NonCoffee.setSelectedIndex(0);
				}else {
					tabbedPane_NonCoffee.setSelectedIndex(nowTabNum-1);
				}
			}
		});
		btnNewButton_7_1.setBounds(10, 454, 97, 23);
		panel_1.add(btnNewButton_7_1);
		
		JButton btnNewButton_8_1 = new JButton("다음");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_NonCoffee.getSelectedIndex();
				if(nowTabNum >= 1) {
					tabbedPane_NonCoffee.setSelectedIndex(1);
				}else {
					tabbedPane_NonCoffee.setSelectedIndex(nowTabNum+1);
				}
			}
		});
		btnNewButton_8_1.setBounds(576, 454, 97, 23);
		panel_1.add(btnNewButton_8_1);

		

		JPanel panel_2 = new JPanel();

		tabbedPane_Main.addTab("Food", null, panel_2, null);

		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane_Food = new JTabbedPane(JTabbedPane.TOP);	// Food 내부 Tab
		tabbedPane_Food.setBounds(0, -27, 685, 472);
		panel_2.add(tabbedPane_Food);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnNewButton_6_4 = new JButton("케이크(1조각)");
		btnNewButton_6_4.setBounds(12, 100, 211, 188);
		panel_3.add(btnNewButton_6_4);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		
		JButton btnNewButton_6_1_1 = new JButton("샌드위치");
		btnNewButton_6_1_1.setBounds(12, 100, 211, 188);
		panel_4.add(btnNewButton_6_1_1);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);
		
		JButton btnNewButton_6_2_1 = new JButton("스콘");
		btnNewButton_6_2_1.setBounds(12, 100, 211, 188);
		panel_5.add(btnNewButton_6_2_1);
		
		JButton btnNewButton_7 = new JButton("이전");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Food.getSelectedIndex();
				if(nowTabNum <= 0) {
					tabbedPane_Food.setSelectedIndex(0);
				}else {
					tabbedPane_Food.setSelectedIndex(nowTabNum-1);
				}
			}
		});
		btnNewButton_7.setBounds(10, 454, 97, 23);
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("다음");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int nowTabNum = tabbedPane_Food.getSelectedIndex();
					if(nowTabNum >= 2) {
						tabbedPane_Food.setSelectedIndex(2);
					}else {
						tabbedPane_Food.setSelectedIndex(nowTabNum+1);
					}
			}
		});
		btnNewButton_8.setBounds(576, 454, 97, 23);
		panel_2.add(btnNewButton_8);

		

		JScrollPane scrollPane = new JScrollPane();

		

		JButton btnNewButton = new JButton("결제하기");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Ex_Payment();
				
			}
		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane_Main, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
							.addGap(61)
							.addComponent(btnNewButton)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane_Main, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
					.addContainerGap())
		);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);

		setVisible(true);
		
	}
}