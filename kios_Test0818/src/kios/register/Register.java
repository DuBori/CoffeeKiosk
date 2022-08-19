package kios.register;

import kios_Test0818.kios.milage.Ex_Patment;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField tf1, tf2, tf3;
	
	Connection con;
	PreparedStatement preparedstatement;
	ResultSet rs;
	String query;
	int check;
	String phonecheck;
	
	JLabel label1,label2,label3;
	String phone,pw,name;
	String chkph=null;
	
	/**
	 * Create the frame.
	 */
	public Register() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(150, 50, 150, 50));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new TitledBorder(new LineBorder(Color.black,1)));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.black,1));
		
		
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel label1 = new JLabel("휴대폰 번호");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBorder(new LineBorder(Color.black,1));
		panel.add(label1);
		
		tf1 = new JTextField();
		panel.add(tf1);
		tf1.setColumns(10);
		
		label2 = new JLabel("생년월일(6자리)");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBorder(new EmptyBorder(4, 4, 4, 4));
		label2.setBorder(new LineBorder(Color.black,1));
		panel.add(label2);
		
		tf2 = new JTextField();
		panel.add(tf2);
		tf2.setColumns(10);
		
		label3 = new JLabel("이 름");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBorder(new EmptyBorder(4, 4, 4, 4));
		label3.setBorder(new LineBorder(Color.black,1));
		panel.add(label3);
		
		tf3 = new JTextField();
		panel.add(tf3);
		tf3.setColumns(10);
		 
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btn1 = new JButton("취소");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ex_Patment();
				dispose();
			}
		});
		panel_1.add(btn1);
		JButton btn2 = new JButton("가입하기");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(tf1.getText().equals("") && tf2.getText().equals("")  && tf3.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "가입 정보가 부족합니다.");	
					}else {
							new check_Phone(tf1.getText(),tf2.getText(),tf3.getText());	
					}
					
				}catch(Exception a){
					a.printStackTrace();
					JOptionPane.showMessageDialog(null, "가입 정보가 부족합니다.");
				}

			}
		});
		
		panel_1.add(btn2);
		setVisible(true);
	}

	

}
