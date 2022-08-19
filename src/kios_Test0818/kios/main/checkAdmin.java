package kios_Test0818.kios.main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kios_Test0818.kios.admin.Administrator;



public class checkAdmin extends JFrame{

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	JButton btn;
	private String id;
	private String pwd;
	int count;
	
	public checkAdmin() throws Exception {
	
		//TODO 아이디 비번 넣는 창
		checkIdPwd();

	}

	private void checkIdPwd(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("관리자 모드");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		tf1 = new JTextField();
		tf1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(tf1);
		tf1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		tf2 = new JTextField();
		tf2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(tf2);
		tf2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btn = new JButton("로그인");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					if((!tf1.getText().equals("admin") || !tf2.getText().equals("1234")) && count <3)
					{
						JOptionPane.showMessageDialog(null, "틀리셨습니다."+"남은 기회 : "+(2-count));
						count++;
					}else if(tf1.getText().equals("admin") && tf2.getText().equals("1234") && count<3){
							dispose();
							new Administrator();
						
					}else if(count>=3){
						dispose();
						JOptionPane.showMessageDialog(null, "메인페이지로 돌아갑니다.");
						new mainFrame();
					}else {
						dispose();
						new mainFrame();
					}
					
				
			}
		});
		panel_2.add(btn);
		setVisible(true);
	}
	
	
}
