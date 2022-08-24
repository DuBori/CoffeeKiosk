package kios.menu;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;


//import jdk.nashorn.internal.ir.ContinueNode;
import kios.db.DBconnection;
import kios.db.Static;
import kios.main.mainClick;
import kios.main.subMainFrame;
import kios.mileage.Ex_Payment;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

 
public class menuOrder extends JFrame {

	DefaultTableModel model;
	private JPanel contentPane;

	public static JButton btnNewButton_7;
	public static JPanel panel_3;
	static String picNum;
	public static List<JPanel> list;

	static JTextField textField;
	static JSpinner spinner_1;
	static JComboBox comboBox, comboBox_1;
	public static JScrollPane scrollPane;
	public static JPanel Panel;
	static int coffeePrice, foodPrice, sizePrice, addShot, viewCost, shotCount, count, cost, removeCount;
	static String IceHot, cupSize;

	static ArrayList<ArrayList<Object>> outer_ArrayList = new ArrayList<ArrayList<Object>>(); // 2차원 ArrayList 외부ArrayList 객체 생성

	int total = 0;
	int col = 0;
	int row = 0;
	String contents = "";

	public menuOrder() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 710, 710);
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

		JButton btnNewButton_ameri = new JButton("아메리카노");
		
		btnNewButton_ameri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "ameri";
				new Coffee_Inner(btnNewButton_ameri.getText());
			}

		});
		btnNewButton_ameri.setBounds(12, 100, 211, 188);
		panel_8.add(btnNewButton_ameri);
		
		
		JButton btn_latte = new JButton("카페라떼");
		
		btn_latte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "latte";
				new Coffee_Inner(btn_latte.getText());
			}

		});
		btn_latte.setBounds(235, 100, 211, 188);
		panel_8.add(btn_latte);

		
		JButton btn_mocha = new JButton("카페모카");
		
		btn_mocha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "mocha";
				new Coffee_Inner(btn_mocha.getText());
			}

		});
		btn_mocha.setBounds(458, 100, 211, 188);
		panel_8.add(btn_mocha);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_Coffee.addTab("New tab", null, panel_9, null);
		panel_9.setLayout(null);

		
		JButton btn_cappu = new JButton("카푸치노");

		btn_cappu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "cappu";
				new Coffee_Inner(btn_cappu.getText());
			}
		});
		btn_cappu.setBounds(12, 100, 211, 188);
		panel_9.add(btn_cappu);


		JButton btn_caramel = new JButton("카라멜마키아또");

		btn_caramel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "caramel";
				new Coffee_Inner(btn_caramel.getText());
			}
		});
		btn_caramel.setBounds(235, 100, 211, 188);
		panel_9.add(btn_caramel);

		

		JButton btn_espresso = new JButton("에스프레소");

		btn_espresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "espresso";
				new Coffee_Inner(btn_espresso.getText());
			}
		});
		btn_espresso.setBounds(458, 100, 211, 188);
		panel_9.add(btn_espresso);

		
		JButton btn_prev_Coffee = new JButton("이전");
		btn_prev_Coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Coffee.getSelectedIndex();
				if (nowTabNum <= 0) {
					tabbedPane_Coffee.setSelectedIndex(0);
				} else {
					tabbedPane_Coffee.setSelectedIndex(nowTabNum - 1);
				}
			}
		});
		btn_prev_Coffee.setBounds(10, 454, 97, 23);
		panel.add(btn_prev_Coffee);

		JButton btn_next_Coffee = new JButton("다음");
		btn_next_Coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Coffee.getSelectedIndex();
				if (nowTabNum >= 1) {
					tabbedPane_Coffee.setSelectedIndex(1);
				} else {
					tabbedPane_Coffee.setSelectedIndex(nowTabNum + 1);
				}

			}
		});
		btn_next_Coffee.setBounds(576, 454, 97, 23);
		panel.add(btn_next_Coffee);

		JPanel panel_1 = new JPanel();

		tabbedPane_Main.addTab("Non-Coffee", null, panel_1, null);

		panel_1.setLayout(null);

		JTabbedPane tabbedPane_NonCoffee = new JTabbedPane(JTabbedPane.TOP); // NonCoffee 내부 Tab
		tabbedPane_NonCoffee.setBounds(0, -27, 685, 472);
		panel_1.add(tabbedPane_NonCoffee);

		JPanel panel_6 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);

		JButton btn_chamo = new JButton("케모마일 티");
		btn_chamo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "chamo";
				new NonCoffee_Inner(btn_chamo.getText());

			}
		});
		btn_chamo.setBounds(12, 100, 211, 188);
		panel_6.add(btn_chamo);

		JButton btn_earlgrey = new JButton("얼그레이 티");
		btn_earlgrey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "earlgrey";
				new NonCoffee_Inner(btn_earlgrey.getText());

			}
		});
		btn_earlgrey.setBounds(235, 100, 211, 188);
		panel_6.add(btn_earlgrey);

		JButton btn_jamong = new JButton("자몽 허니 블랙 티");
		btn_jamong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "jamong";
				new NonCoffee_Inner(btn_jamong.getText());

			}
		});
		btn_jamong.setBounds(457, 100, 211, 188);
		panel_6.add(btn_jamong);

		JPanel panel_7 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_7, null);
		panel_7.setLayout(null);

		JButton btn_saenggang = new JButton("생강차");
		btn_saenggang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "saenggang";
				new NonCoffee_Inner(btn_saenggang.getText());

			}
		});
		btn_saenggang.setBounds(11, 12, 211, 188);
		panel_7.add(btn_saenggang);

		JButton btn_green = new JButton("녹차");
		btn_green.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "green";
				new NonCoffee_Inner(btn_green.getText());

			}
		});
		btn_green.setBounds(234, 12, 211, 188);
		panel_7.add(btn_green);

		JButton btn_maesil = new JButton("매실차");
		btn_maesil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "maesil";
				new NonCoffee_Inner(btn_maesil.getText());

			}
		});
		btn_maesil.setBounds(457, 12, 211, 188);
		panel_7.add(btn_maesil);

		JButton btn_yuja = new JButton("유자차");
		btn_yuja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "yuja";
				new NonCoffee_Inner(btn_yuja.getText());

			}
		});
		btn_yuja.setBounds(11, 234, 211, 188);
		panel_7.add(btn_yuja);

		JButton btn_prev_NonCoffee = new JButton("이전");
		btn_prev_NonCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_NonCoffee.getSelectedIndex();
				if (nowTabNum <= 0) {
					tabbedPane_NonCoffee.setSelectedIndex(0);
				} else {
					tabbedPane_NonCoffee.setSelectedIndex(nowTabNum - 1);
				}
			}
		});
		btn_prev_NonCoffee.setBounds(10, 454, 97, 23);
		panel_1.add(btn_prev_NonCoffee);

		JButton btn_next_NonCoffee = new JButton("다음");
		btn_next_NonCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_NonCoffee.getSelectedIndex();
				if (nowTabNum >= 1) {
					tabbedPane_NonCoffee.setSelectedIndex(1);
				} else {
					tabbedPane_NonCoffee.setSelectedIndex(nowTabNum + 1);
				}
			}
		});
		btn_next_NonCoffee.setBounds(576, 454, 97, 23);
		panel_1.add(btn_next_NonCoffee);

		JPanel panel_2 = new JPanel();

		tabbedPane_Main.addTab("Food", null, panel_2, null);

		panel_2.setLayout(null);

		JTabbedPane tabbedPane_Food = new JTabbedPane(JTabbedPane.TOP); // Food 내부 Tab
		tabbedPane_Food.setBounds(0, -27, 685, 472);
		panel_2.add(tabbedPane_Food);

		JPanel panel_food1 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food1, null);
		panel_food1.setLayout(null);

		JButton btn_cheese = new JButton("치즈 케이크");
		btn_cheese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "cheese";
				new Food_Inner(btn_cheese.getText());

			}
		});
		btn_cheese.setBounds(12, 100, 211, 188);
		panel_food1.add(btn_cheese);

		JButton btn_tiramisu = new JButton("티라미수 케이크");
		btn_tiramisu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "tiramisu";
				new Food_Inner(btn_tiramisu.getText());

			}
		});
		btn_tiramisu.setBounds(235, 100, 211, 188);
		panel_food1.add(btn_tiramisu);

		JPanel panel_food2 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food2, null);
		panel_food2.setLayout(null);

		JButton btn_egg = new JButton("에그 샌드위치");
		btn_egg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "egg";
				new Food_Inner(btn_egg.getText());

			}
		});
		btn_egg.setBounds(12, 100, 211, 188);
		panel_food2.add(btn_egg);

		JButton btn_danhobak = new JButton("단호박 샌드위치");
		btn_danhobak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "danhobak";
				new Food_Inner(btn_danhobak.getText());

			}
		});
		btn_danhobak.setBounds(235, 100, 211, 188);
		panel_food2.add(btn_danhobak);

		JPanel panel_food3 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food3, null);
		panel_food3.setLayout(null);

		JButton btn_scorn = new JButton("스콘");
		btn_scorn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "scorn";
				new Food_Inner(btn_scorn.getText());

			}
		});
		btn_scorn.setBounds(12, 100, 211, 188);
		panel_food3.add(btn_scorn);

		JButton btn_macaron = new JButton("마카롱");
		btn_macaron.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "macaron";
				new Food_Inner(btn_macaron.getText());

			}
		});
		btn_macaron.setBounds(235, 100, 211, 188);
		panel_food3.add(btn_macaron);

		JButton btn_waffle = new JButton("와플");
		btn_waffle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "waffle";
				new Food_Inner(btn_waffle.getText());

			}
		});
		btn_waffle.setBounds(458, 100, 211, 188);
		panel_food3.add(btn_waffle);

		JButton btn_prev_Food = new JButton("이전");
		btn_prev_Food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Food.getSelectedIndex();
				if (nowTabNum <= 0) {
					tabbedPane_Food.setSelectedIndex(0);
				} else {
					tabbedPane_Food.setSelectedIndex(nowTabNum - 1);
				}
			}
		});
		btn_prev_Food.setBounds(10, 454, 97, 23);
		panel_2.add(btn_prev_Food);

		JButton btn_next_Food = new JButton("다음");
		btn_next_Food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nowTabNum = tabbedPane_Food.getSelectedIndex();
				if (nowTabNum >= 2) {
					tabbedPane_Food.setSelectedIndex(2);
				} else {
					tabbedPane_Food.setSelectedIndex(nowTabNum + 1);
				}
			}
		});
		btn_next_Food.setBounds(576, 454, 97, 23);
		panel_2.add(btn_next_Food);

		scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("결제하기");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Ex_Payment();

			}
		});
		
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.addMouseListener(new mainClick()
		{
		@Override
		public void mouseClicked(MouseEvent e) {
			dispose();
			new subMainFrame();
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
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane_Main, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(20, 1, 80, 0));
		list = new ArrayList<JPanel>();
		scrollPane.setViewportView(panel_3);


		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}