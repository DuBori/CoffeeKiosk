package kios.menu;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;


//import jdk.nashorn.internal.ir.ContinueNode;
import kios.db.DBconnection;
import kios.db.Static;
import kios.main.*;
import kios.mileage.Ex_Payment;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeEvent;

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
	public static int coffeePrice, foodPrice, sizePrice, addShot, viewCost, shotCount, count, cost, removeCount;
	static String IceHot, cupSize;

	int total = 0;
	int col = 0;
	int row = 0;
	String contents = "";

	public menuOrder() {
		
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(111, 56, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane_Main = new JTabbedPane(JTabbedPane.TOP);	// 메인 tab

		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 220, 181));
		tabbedPane_Main.addTab("Coffee", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_Coffee = new JTabbedPane(JTabbedPane.TOP);	// Coffee 내부 Tab
		tabbedPane_Coffee.setForeground(Color.WHITE);
		tabbedPane_Coffee.setBackground(Color.WHITE);
		tabbedPane_Coffee.setBounds(0, -27, 663, 472);
		panel.add(tabbedPane_Coffee);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(244, 220, 181));
		tabbedPane_Coffee.addTab("New tab", null, panel_8, null);
		panel_8.setLayout(null);

		JButton btnNewButton_ameri = new JButton("아메리카노");
		btnNewButton_ameri.setBackground(Color.WHITE);
		btnNewButton_ameri.setFont(new Font("Arial", Font.PLAIN, 0));
		btnNewButton_ameri.setIcon(new ImageIcon(menuOrder.class.getResource("/image/americano.png")));
		
		btnNewButton_ameri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "ameri";
				new Coffee_Inner(btnNewButton_ameri.getText());
			}

		});
		btnNewButton_ameri.setBounds(4, 135, 211, 188);
		panel_8.add(btnNewButton_ameri);
		
		
		JButton btn_latte = new JButton("카페라떼");
		btn_latte.setIcon(new ImageIcon(menuOrder.class.getResource("/image/latte.png")));
		btn_latte.setFont(new Font("Arial", Font.PLAIN, 0));
		
		btn_latte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "latte";
				new Coffee_Inner(btn_latte.getText());
			}

		});
		btn_latte.setBounds(216, 135, 211, 188);
		panel_8.add(btn_latte);

		
		JButton btn_mocha = new JButton("카페모카");
		btn_mocha.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_mocha.setIcon(new ImageIcon(menuOrder.class.getResource("/image/mocha.png")));
		btn_mocha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "mocha";
				new Coffee_Inner(btn_mocha.getText());
			}

		});
		btn_mocha.setBounds(427, 135, 211, 188);
		panel_8.add(btn_mocha);
		
		JLabel l_ameri = new JLabel("아메리카노");
		l_ameri.setForeground(new Color(111, 56, 38));
		l_ameri.setBounds(83, 336, 61, 16);
		panel_8.add(l_ameri);
		
		JLabel l_latte = new JLabel("카페라떼");
		l_latte.setForeground(new Color(111, 56, 38));
		l_latte.setBounds(297, 336, 44, 16);
		panel_8.add(l_latte);
		
		JLabel l_mocha = new JLabel("카페모카");
		l_mocha.setForeground(new Color(111, 56, 38));
		l_mocha.setBounds(511, 335, 61, 16);
		panel_8.add(l_mocha);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(244, 220, 181));
		tabbedPane_Coffee.addTab("New tab", null, panel_9, null);
		panel_9.setLayout(null);

		
		JButton btn_cappu = new JButton("카푸치노");
		btn_cappu.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_cappu.setIcon(new ImageIcon(menuOrder.class.getResource("/image/cappu.png")));
		btn_cappu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "cappu";
				new Coffee_Inner(btn_cappu.getText());
			}
		});
		btn_cappu.setBounds(4, 135, 211, 188);
		panel_9.add(btn_cappu);


		JButton btn_caramel = new JButton("카라멜마키아또");
		btn_caramel.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_caramel.setIcon(new ImageIcon(menuOrder.class.getResource("/image/caramel.png")));
		btn_caramel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "caramel";
				new Coffee_Inner(btn_caramel.getText());
			}
		});
		btn_caramel.setBounds(216, 135, 211, 188);
		panel_9.add(btn_caramel);

		JButton btn_espresso = new JButton("에스프레소");
		btn_espresso.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_espresso.setIcon(new ImageIcon(menuOrder.class.getResource("/image/espresso.png")));
		btn_espresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "espresso";
				new Coffee_Inner(btn_espresso.getText());
			}
		});
		btn_espresso.setBounds(427, 135, 211, 188);
		panel_9.add(btn_espresso);

		JLabel l_1 = new JLabel("카푸치노");
		l_1.setForeground(new Color(111, 56, 38));
		l_1.setBounds(83, 336, 61, 16);
		panel_9.add(l_1);
		
		JLabel l_2 = new JLabel("카라멜 마키아또");
		l_2.setForeground(new Color(111, 56, 38));
		l_2.setBounds(278, 335, 100, 16);
		panel_9.add(l_2);
		
		JLabel l_3 = new JLabel("에스프레소");
		l_3.setForeground(new Color(111, 56, 38));
		l_3.setBounds(506, 335, 61, 16);
		panel_9.add(l_3);
		
		JButton btn_prev_Coffee = new JButton("이전");
		btn_prev_Coffee.setForeground(new Color(111, 56, 38));
		btn_prev_Coffee.setBackground(new Color(225, 242, 252));
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
		btn_prev_Coffee.setBounds(10, 441, 97, 23);
		panel.add(btn_prev_Coffee);

		JButton btn_next_Coffee = new JButton("다음");
		btn_next_Coffee.setBackground(Color.white);
		btn_next_Coffee.setForeground(new Color(111, 56, 38));
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
		btn_next_Coffee.setBounds(556, 441, 97, 23);
		panel.add(btn_next_Coffee);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 220, 181));

		tabbedPane_Main.addTab("Non-Coffee", null, panel_1, null);

		panel_1.setLayout(null);

		JTabbedPane tabbedPane_NonCoffee = new JTabbedPane(JTabbedPane.TOP); // NonCoffee 내부 Tab
		tabbedPane_NonCoffee.setForeground(Color.WHITE);
		tabbedPane_NonCoffee.setBounds(0, -27, 663, 472);
		panel_1.add(tabbedPane_NonCoffee);

		JPanel panel_6 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(244, 220, 181));

		JButton btn_chamo = new JButton("케모마일 티");
		btn_chamo.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_chamo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "chamo";
				new NonCoffee_Inner(btn_chamo.getText());

			}
		});
		btn_chamo.setBounds(4, 135, 211, 188);
		btn_chamo.setBackground(Color.WHITE);
		btn_chamo.setIcon(new ImageIcon(menuOrder.class.getResource("/image/chamomile.png")));
		panel_6.add(btn_chamo);

		JButton btn_earlgrey = new JButton("얼그레이 티");
		btn_earlgrey.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_earlgrey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "earlgrey";
				new NonCoffee_Inner(btn_earlgrey.getText());

			}
		});
		btn_earlgrey.setBounds(216, 135, 211, 188);
		btn_earlgrey.setIcon(new ImageIcon(menuOrder.class.getResource("/image/earlgrey.png")));
		btn_earlgrey.setBackground(Color.WHITE);
		panel_6.add(btn_earlgrey);

		JButton btn_jamong = new JButton("자몽 허니 블랙 티");
		btn_jamong.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_jamong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "jamong";
				new NonCoffee_Inner(btn_jamong.getText());

			}
		});
		btn_jamong.setBounds(427, 135, 211, 188);
		btn_jamong.setIcon(new ImageIcon(menuOrder.class.getResource("/image/jamong.png")));
		btn_jamong.setBackground(Color.WHITE);
		panel_6.add(btn_jamong);
		
		JLabel l_4 = new JLabel("케모마일 티");
		l_4.setForeground(new Color(111, 56, 38));
		l_4.setBounds(79, 336, 61, 16);
		panel_6.add(l_4);
		
		JLabel l_5 = new JLabel("얼그레이 티");
		l_5.setForeground(new Color(111, 56, 38));
		l_5.setBounds(289, 335, 61, 16);
		panel_6.add(l_5);
		
		JLabel l_6 = new JLabel("자몽 허니 블랙 티");
		l_6.setForeground(new Color(111, 56, 38));
		l_6.setBounds(485, 335, 110, 16);
		panel_6.add(l_6);

		JPanel panel_7 = new JPanel();
		tabbedPane_NonCoffee.addTab("New tab", null, panel_7, null);
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(244, 220, 181));

		JButton btn_saenggang = new JButton("생강차");
		btn_saenggang.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_saenggang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "saenggang";
				new NonCoffee_Inner(btn_saenggang.getText());

			}
		});
		btn_saenggang.setBounds(6, 6, 211, 188);
		btn_saenggang.setIcon(new ImageIcon(menuOrder.class.getResource("/image/saenggang.png")));
		btn_saenggang.setBackground(Color.WHITE);
		panel_7.add(btn_saenggang);

		JButton btn_green = new JButton("녹차");
		btn_green.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_green.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "green";
				new NonCoffee_Inner(btn_green.getText());

			}
		});
		btn_green.setBounds(220, 6, 211, 188);
		btn_green.setIcon(new ImageIcon(menuOrder.class.getResource("/image/green.png")));
		btn_green.setBackground(Color.WHITE);
		panel_7.add(btn_green);

		JButton btn_maesil = new JButton("매실차");
		btn_maesil.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_maesil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "maesil";
				new NonCoffee_Inner(btn_maesil.getText());

			}
		});
		btn_maesil.setBounds(433, 6, 211, 188);
		btn_maesil.setIcon(new ImageIcon(menuOrder.class.getResource("/image/maesil.png")));
		btn_maesil.setBackground(Color.WHITE);
		panel_7.add(btn_maesil);

		JButton btn_yuja = new JButton("유자차");
		btn_yuja.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_yuja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "yuja";
				new NonCoffee_Inner(btn_yuja.getText());

			}
		});
		btn_yuja.setBounds(6, 218, 211, 188);
		btn_yuja.setIcon(new ImageIcon(menuOrder.class.getResource("/image/yuja.png")));
		btn_yuja.setBackground(Color.WHITE);
		panel_7.add(btn_yuja);
		
		JLabel l_7 = new JLabel("생강차");
		l_7.setForeground(new Color(111, 56, 38));
		l_7.setBounds(95, 110, 211, 188);
		panel_7.add(l_7);
		
		JLabel l_8 = new JLabel("녹차");
		l_8.setForeground(new Color(111, 56, 38));
		l_8.setBounds(313, 110, 211, 188);
		panel_7.add(l_8);
		
		JLabel l_9 = new JLabel("매실차");
		l_9.setForeground(new Color(111, 56, 38));
		l_9.setBounds(523, 110, 211, 188);
		panel_7.add(l_9);
		
		JLabel l_10 = new JLabel("유자차");
		l_10.setForeground(new Color(111, 56, 38));
		l_10.setBounds(95, 320, 211, 188);
		panel_7.add(l_10);
		
		JButton btn_prev_NonCoffee = new JButton("이전");
		btn_prev_NonCoffee.setForeground(new Color(111, 56, 38));
		btn_prev_NonCoffee.setBackground(new Color(225, 242, 252));
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
		btn_prev_NonCoffee.setBounds(10, 441, 97, 23);
		panel_1.add(btn_prev_NonCoffee);

		JButton btn_next_NonCoffee = new JButton("다음");
		btn_next_NonCoffee.setForeground(new Color(111, 56, 38));
		btn_next_NonCoffee.setBackground(new Color(225, 242, 252));
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
		btn_next_NonCoffee.setBounds(556, 441, 97, 23);
		panel_1.add(btn_next_NonCoffee);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(244, 220, 181));

		tabbedPane_Main.addTab("Food", null, panel_2, null);

		panel_2.setLayout(null);

		JTabbedPane tabbedPane_Food = new JTabbedPane(JTabbedPane.TOP); // Food 내부 Tab
		tabbedPane_Food.setBackground(Color.WHITE);
		tabbedPane_Food.setForeground(Color.WHITE);
		tabbedPane_Food.setBounds(0, -27, 663, 472);
		panel_2.add(tabbedPane_Food);

		JPanel panel_food1 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food1, null);
		panel_food1.setLayout(null);
		panel_food1.setBackground(new Color(244, 220, 181));

		JButton btn_cheese = new JButton("치즈 케이크");
		btn_cheese.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_cheese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "cheese";
				new Food_Inner(btn_cheese.getText());

			}
		});
		btn_cheese.setBounds(66, 135, 211, 188);
		btn_cheese.setIcon(new ImageIcon(menuOrder.class.getResource("/image/cheese.png")));
		btn_cheese.setBackground(Color.WHITE);
		panel_food1.add(btn_cheese);

		JButton btn_tiramisu = new JButton("티라미수 케이크");
		btn_tiramisu.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_tiramisu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "tiramisu";
				new Food_Inner(btn_tiramisu.getText());

			}
		});
		btn_tiramisu.setBounds(370, 135, 211, 188);
		btn_tiramisu.setIcon(new ImageIcon(menuOrder.class.getResource("/image/tiramisu.png")));
		btn_tiramisu.setBackground(Color.WHITE);
		panel_food1.add(btn_tiramisu);
		
		JLabel l_11 = new JLabel("치즈 케이크");
		l_11.setForeground(new Color(111, 56, 38));
		l_11.setBounds(140, 240, 211, 188);
		panel_food1.add(l_11);
		
		JLabel l_12 = new JLabel("티라미수 케이크");
		l_12.setForeground(new Color(111, 56, 38));
		l_12.setBounds(433, 240, 211, 188);
		panel_food1.add(l_12);

		JPanel panel_food2 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food2, null);
		panel_food2.setLayout(null);
		panel_food2.setBackground(new Color(244, 220, 181));

		JButton btn_egg = new JButton("에그 샌드위치");
		btn_egg.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_egg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "egg";
				new Food_Inner(btn_egg.getText());

			}
		});
		btn_egg.setBounds(66, 135, 211, 188);
		btn_egg.setIcon(new ImageIcon(menuOrder.class.getResource("/image/egg.png")));
		btn_egg.setBackground(Color.WHITE);
		panel_food2.add(btn_egg);

		JButton btn_danhobak = new JButton("단호박 샌드위치");
		btn_danhobak.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_danhobak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "danhobak";
				new Food_Inner(btn_danhobak.getText());

			}
		});
		btn_danhobak.setBounds(370, 135, 211, 188);
		btn_danhobak.setIcon(new ImageIcon(menuOrder.class.getResource("/image/danhobak.png")));
		btn_danhobak.setBackground(Color.WHITE);
		panel_food2.add(btn_danhobak);
		

		JLabel l_13 = new JLabel("에그 샌드위치");
		l_13.setForeground(new Color(111, 56, 38));
		l_13.setBounds(135, 240, 211, 188);
		panel_food2.add(l_13);
		
		JLabel l_14 = new JLabel("단호박 샌드위치");
		l_14.setForeground(new Color(111, 56, 38));
		l_14.setBounds(436, 240, 211, 188);
		panel_food2.add(l_14);

		JPanel panel_food3 = new JPanel();
		tabbedPane_Food.addTab("New tab", null, panel_food3, null);
		panel_food3.setLayout(null);
		panel_food3.setBackground(new Color(244, 220, 181));

		JButton btn_scorn = new JButton("스콘");
		btn_scorn.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_scorn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "scorn";
				new Food_Inner(btn_scorn.getText());

			}
		});
		btn_scorn.setBounds(4, 135, 211, 188);
		btn_scorn.setIcon(new ImageIcon(menuOrder.class.getResource("/image/scone.png")));
		btn_scorn.setBackground(Color.WHITE);
		panel_food3.add(btn_scorn);

		JButton btn_macaron = new JButton("마카롱");
		btn_macaron.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_macaron.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "macaron";
				new Food_Inner(btn_macaron.getText());

			}
		});
		btn_macaron.setBounds(216, 135, 211, 188);
		btn_macaron.setBackground(Color.WHITE);
		btn_macaron.setIcon(new ImageIcon(menuOrder.class.getResource("/image/macaron.png")));
		panel_food3.add(btn_macaron);

		JButton btn_waffle = new JButton("와플");
		btn_waffle.setFont(new Font("Arial", Font.PLAIN, 0));
		btn_waffle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				picNum = "waffle";
				new Food_Inner(btn_waffle.getText());

			}
		});
		btn_waffle.setBounds(427, 135, 211, 188);
		btn_waffle.setIcon(new ImageIcon(menuOrder.class.getResource("/image/waffle.png")));
		btn_waffle.setBackground(Color.WHITE);
		panel_food3.add(btn_waffle);
		
		JLabel l_15 = new JLabel("스콘");
		l_15.setForeground(new Color(111, 56, 38));
		l_15.setBounds(100, 325, 61, 16);
		panel_food3.add(l_15);
		
		JLabel l_16 = new JLabel("마카롱");
		l_16.setForeground(new Color(111, 56, 38));
		l_16.setBounds(303, 325, 44, 16);
		panel_food3.add(l_16);
		
		JLabel l_17 = new JLabel("와플");
		l_17.setForeground(new Color(111, 56, 38));
		l_17.setBounds(522, 325, 61, 16);
		panel_food3.add(l_17);

		JButton btn_prev_Food = new JButton("이전");
		btn_prev_Food.setForeground(new Color(111, 56, 38));
		btn_prev_Food.setBackground(new Color(225, 242, 252));
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
		btn_prev_Food.setBounds(10, 441, 97, 23);
		panel_2.add(btn_prev_Food);

		JButton btn_next_Food = new JButton("다음");
		btn_next_Food.setBackground(Color.white);
		btn_next_Food.setForeground(new Color(111, 56, 38));
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
		btn_next_Food.setBounds(556, 441, 97, 23);
		panel_2.add(btn_next_Food);


		scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("결제하기");
		btnNewButton.setForeground(new Color(111, 56, 38));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Static.outer_ArrayList.size() == 0) {
					JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요^-^");
					return;
				}
				setVisible(false);
				new Ex_Payment();

			}
		});
		
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.setForeground(new Color(111, 56, 38));
		btnNewButton_1.addMouseListener(new mainClick()
		{
		@Override
		public void mouseClicked(MouseEvent e) {
			dispose();
			new subMainFrame();
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("전체삭제");
		btnNewButton_1_1.setForeground(new Color(111, 56, 38));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Static.panel_3.removeAll();
				Static.outer_ArrayList.clear();
				menuOrder.scrollPane.setViewportView(Static.panel_3);
			}
		});


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
						.addComponent(tabbedPane_Main, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane_Main, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1_1)
							.addGap(2)
							.addComponent(btnNewButton_1))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
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