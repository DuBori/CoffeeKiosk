package kios.mileage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import kios.db.Static;
import kios.menu.menuOrder;
import kios.register.Register;
import kios.register.check_Phone;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Ex_Payment extends JFrame{
	static JTextField inputSpace;
	boolean clearOnNextDigit;
	private JTextField textField;
	public Ex_Payment() {
		getContentPane().setBackground(SystemColor.window);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(74, 68, 61));
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Ex_Payment.class.getResource("/image/logo_1.png")));
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><body style='text-align:center;'>적립을 위해"
				+ "<br/>전화번호를 입력해주세요</body></html>");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(74, 68, 61));
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		// 취소& 회원가입 버튼 생성.
		JButton button1 = new JButton("회원가입");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Register();
				dispose();
			}
		});
		
		JButton button2 = new JButton("확인");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new check_Phone(inputSpace.getText().toString());
			}
		});
		
		JButton button3 = new JButton("취소");
		
		JButton button3_1 = new JButton("뒤로");
		button3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new menuOrder();
				menuOrder.scrollPane.setViewportView(Static.panel_3);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(23)
					.addComponent(button1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button3)
					.addGap(6)
					.addComponent(button3_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(button1)
						.addComponent(button2)
						.addComponent(button3)
						.addComponent(button3_1)))
		);
		panel_2.setLayout(gl_panel_2);
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Static.phone="None";
				new Payment();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(74, 68, 61));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		inputSpace = new JTextField();
		inputSpace.setHorizontalAlignment(SwingConstants.CENTER);
		inputSpace.setColumns(20);
		inputSpace.setEditable(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(74, 68, 61));
		
		//GridLayout(4, 4, 10, 10) -> 가로 칸수, 세로 칸수, 좌우 간격, 상하 간격
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
		//위치와 크기 설정
		buttonPanel.setBounds(8, 90, 270, 235);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(35)
							.addComponent(inputSpace, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.CENTER)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(inputSpace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		// 입력 버튼의 글자를 차례대로 배열에 저장
		String button_names[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9","C","0","←"};
		// 버튼들의 배열 
		JButton buttons[] = new JButton[button_names.length];
		
		for(int i=0; i<button_names.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			//글씨체
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			//글자색 지정
			buttons[i].setForeground(Color.gray);
			//테두리 없앱
			buttons[i].setBorderPainted(false);
			//밑에서 만든 액션리스너를 버튼에 추가
			buttons[i].addActionListener(new PadActionListener());
			//버튼들을 버튼패널에 추가
			buttonPanel.add(buttons[i]);
		}
		//위치와 크기
		setBounds(100, 100, 335, 405);
			
		//화면의 가운데에 띄움
		setLocationRelativeTo(null);
		
		//사이즈조절 불가능
		setResizable(false);
		
		//창을 닫을 때 실행 중인 프로그램도 같이 종료되도록 함
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//보이기 여부
		setVisible(true);
		
	}
	
	//만들어놓은 버튼에 액션 리스너 기능 추가
	//액션리스너를 상속시켜주고 actionPerformed(ActionEvent e)메소드로 이벤트 처리
	class PadActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO //어떤 버튼이 눌렸는지를 알아냄
			String operation = e.getActionCommand();
			//C가 눌렸을 경우 위의 입력된 내용을 지워줌
			if(operation.equals("C")) {
				inputSpace.setText("");
			// " <- " 이 눌렸을 경우 오른쪽 부터 하나씩 지워짐. 더이상 지울 숫자 없으면 공백
			}else if(operation.equals("←")) {  
				setBackspace(getBackSpace().substring(0, getBackSpace().length() -1));
				if(getBackSpace().length() < 1) {
					inputSpace.setText(" ");	
				}
			}else {
				inputSpace.setText(inputSpace.getText() + e.getActionCommand());
			}
		}

		private void setBackspace(String substring) {
			inputSpace.setText(substring);
		}

		private String getBackSpace() {
			return inputSpace.getText();
		}
		
	}
}