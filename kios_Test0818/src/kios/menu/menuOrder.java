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

import jdk.nashorn.internal.ir.ContinueNode;
import kios.db.DBconnection;
import kios.db.Static;
import kios.mileage.Ex_Payment;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;
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
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

 
public class menuOrder extends JFrame {

	DefaultTableModel model;
	private JPanel contentPane;
	public static JTextArea textArea;
	public static JTextArea textArea1;
	public static JTextArea textArea2;
	public static JButton btnNewButton_7;
	public static JPanel panel_3;
	
	static String picNum;

	public static void main(String[] args) {
		new menuOrder();
	}


	/**

	 * Create the frame.

	 */

	int total=0; int col=0; int row=0; String contents = ""; int bill_id;
	 
	public menuOrder() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Coffee", null, panel, null);
		panel.setLayout(null);

		JButton btnNewButton_1_1 = new JButton("아메리카노");
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "ameri";
				new Coffee_Inner(btnNewButton_1_1.getText());
			}

		});
		btnNewButton_1_1.setBounds(12, 36, 211, 188);
		panel.add(btnNewButton_1_1);
		
		
		JButton btnNewButton_1 = new JButton("카페라떼");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "latte";
				new Coffee_Inner(btnNewButton_1.getText());
			}

		});
		btnNewButton_1.setBounds(235, 36, 211, 188);
		panel.add(btnNewButton_1);

		
		JButton btnNewButton_2 = new JButton("카페모카");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "mocha";
				new Coffee_Inner(btnNewButton_2.getText());
			}

		});
		btnNewButton_2.setBounds(458, 36, 211, 188);
		panel.add(btnNewButton_2);

		

		JButton btnNewButton_3 = new JButton("카푸치노");

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "cappu";
				new Coffee_Inner(btnNewButton_3.getText());
			}
		});
		btnNewButton_3.setBounds(12, 258, 211, 188);
		panel.add(btnNewButton_3);


		JButton btnNewButton_4 = new JButton("카라멜마키아또");

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "caramel";
				new Coffee_Inner(btnNewButton_4.getText());
			}
		});
		btnNewButton_4.setBounds(235, 258, 211, 188);
		panel.add(btnNewButton_4);

		

		JButton btnNewButton_5 = new JButton("에스프레소");

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picNum = "espresso";
				new Coffee_Inner(btnNewButton_5.getText());
			}
		});
		btnNewButton_5.setBounds(458, 258, 211, 188);
		panel.add(btnNewButton_5);

		

		JPanel panel_1 = new JPanel();

		tabbedPane.addTab("Non-Coffee", null, panel_1, null);

		panel_1.setLayout(null);

		

		JButton btnNewButton_6 = new JButton("카모마일티");

		btnNewButton_6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				

			}

		});

		btnNewButton_6.setBounds(12, 36, 211, 188);

		panel_1.add(btnNewButton_6);

		

		JButton btnNewButton_6_1 = new JButton("얼그레이 티");

		btnNewButton_6_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


			}

		});

		btnNewButton_6_1.setBounds(235, 36, 211, 188);

		panel_1.add(btnNewButton_6_1);

		

		JButton btnNewButton_6_2 = new JButton("매실차");

		btnNewButton_6_2.setBounds(458, 36, 211, 188);

		panel_1.add(btnNewButton_6_2);

		

		JButton btnNewButton_6_3 = new JButton("유자차");

		btnNewButton_6_3.setBounds(12, 258, 211, 188);

		panel_1.add(btnNewButton_6_3);

		

		JButton btnNewButton_6_3_1 = new JButton("녹차");

		btnNewButton_6_3_1.setBounds(235, 258, 211, 188);

		panel_1.add(btnNewButton_6_3_1);

		

		JButton btnNewButton_6_3_2 = new JButton("대추차");

		btnNewButton_6_3_2.setBounds(458, 258, 211, 188);

		panel_1.add(btnNewButton_6_3_2);

		

		JPanel panel_2 = new JPanel();

		tabbedPane.addTab("Food", null, panel_2, null);

		panel_2.setLayout(null);

		

		JButton btnNewButton_6_4 = new JButton("케이크(1조각)");

		btnNewButton_6_4.setBounds(12, 129, 211, 188);

		panel_2.add(btnNewButton_6_4);

		

		JButton btnNewButton_6_1_1 = new JButton("샌드위치");

		btnNewButton_6_1_1.setBounds(235, 129, 211, 188);

		panel_2.add(btnNewButton_6_1_1);

		

		JButton btnNewButton_6_2_1 = new JButton("스콘");

		btnNewButton_6_2_1.setBounds(458, 129, 211, 188);

		panel_2.add(btnNewButton_6_2_1);

		

		JScrollPane scrollPane = new JScrollPane();

		

		JButton btnNewButton = new JButton("결제하기");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Ex_Payment();
				Static.count++;
				
			}
		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
					.addContainerGap())
		);
				
				JPanel panel_3 = new JPanel();
				scrollPane.setViewportView(panel_3);
				
				//수량 - 버튼
				JButton btnNewButton_7 = new JButton("-");
				btnNewButton_7.setVisible(false);
						textArea = new JTextArea();
						textArea.addCaretListener(new CaretListener() {
							public void caretUpdate(CaretEvent arg0) {
								btnNewButton_7.setVisible(true);
					}
				});
				textArea.setBackground(SystemColor.window);
				
				//수량 + 버튼
				JButton btnNewButton_8 = new JButton("+");
				btnNewButton_8.setVisible(false);
				textArea1 = new JTextArea();
				textArea1.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						btnNewButton_8.setVisible(true);
					}
				});
				textArea1.setBackground(SystemColor.window);
				
				//지우기 버튼
				JButton btnNewButton_9 = new JButton("X");
				btnNewButton_9.setVisible(false);
				textArea2 = new JTextArea();
				textArea2.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						btnNewButton_9.setVisible(true);
					}
				});
				textArea2.setBackground(SystemColor.window);
				btnNewButton_9.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						 textArea.setText("");
						 textArea1.setText("");
						 textArea2.setText("");
						 btnNewButton_7.setVisible(false);
						 btnNewButton_8.setVisible(false);
						 btnNewButton_9.setVisible(false);
						 
					} 
				});
				
				//버튼 클릭시 수량 변동(+)
				ActionListener btnNewButton_8_action = new ActionListener(){			
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub	
						int count = Integer.parseInt(textArea1.getText());
						count += 1;
						textArea1.setText(String.valueOf(count));				
					}
				};
				btnNewButton_8.addActionListener(btnNewButton_8_action);

				//버튼 클릭시 수량 변동(-)
				ActionListener btnNewButton_7_action = new ActionListener(){			
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub	
						int count = Integer.parseInt(textArea1.getText());
						count -= 1;
						textArea1.setText("0");				
					}
				};
				btnNewButton_7.addActionListener(btnNewButton_7_action);
				
				panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				panel_3.add(textArea);
				panel_3.add(btnNewButton_7);
				panel_3.add(textArea1);
				panel_3.add(btnNewButton_8);
				panel_3.add(textArea2);
				panel_3.add(btnNewButton_9);
				contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}