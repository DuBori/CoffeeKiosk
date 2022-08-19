package kios_Test0818.src.kios.milage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import kios_Test0818.src.kios.register.Register;
import kios_Test0818.src.kios.register.check_Phone;

public class Ex_Patment extends JFrame{

	JTextField inputSpace;
	boolean clearOnNextDigit;
	public Ex_Patment() {

		// 컨테이너 생성.
		JPanel con = new JPanel(); 
		JPanel con1 = new JPanel();            	
		JPanel con2 = new JPanel();                            
		JPanel con3 = new JPanel();        
		JPanel con4 = new JPanel(new BorderLayout());                             
		
		// 버튼을 만들 패널
		JPanel buttonPanel = new JPanel(new BorderLayout());                       
		
		// 컴포넌트 생성.
		JLabel jl1 = new JLabel("적립하시겠습니까?");
		
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
				// TODO Auto-generated method stub
				new check_Phone(inputSpace.getText());
			}
		});
		JButton button3 = new JButton("취소");
		
		//setLayout(null);
		inputSpace = new JTextField(20);
		
        inputSpace.setEditable(false);
		inputSpace.setBackground(Color.WHITE);
		//정렬위치 설정
		inputSpace.setHorizontalAlignment(JTextField.CENTER);
		inputSpace.setFont(new Font("Arial", Font.BOLD, 10));
		
		// 1줄, 1칸짜리 레이아웃을 가진 그리드 레이아웃 & 글씨체 중앙 배치 및 폰드 크기 설정. 
		jl1.setLayout(new GridLayout(1,1));
		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl1.setFont(new Font("Serif", Font.BOLD, 25));
			
		//GridLayout(4, 4, 10, 10) -> 가로 칸수, 세로 칸수, 좌우 간격, 상하 간격
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
		//위치와 크기 설정
		buttonPanel.setBounds(8, 90, 270, 235);
		// 입력 버튼의 글자를 차례대로 배열에 저장
		String button_names[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9","C","0","←"};
		// 버튼들의 배열 
		JButton buttons[] = new JButton[button_names.length];
		
		for(int i=0; i<button_names.length; i++) {
			buttons[i] = new JButton(button_names[i]);
			//글씨체
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			//글자색 지정
			buttons[i].setForeground(Color.GRAY);
			//테두리 없앱
			buttons[i].setBorderPainted(false);
			//밑에서 만든 액션리스너를 버튼에 추가
			buttons[i].addActionListener(new PadActionListener());
			//버튼들을 버튼패널에 추가
			buttonPanel.add(buttons[i]);
		}
		
		// 새로운 컨테이너를 2개 생성.
		JPanel group1 = new JPanel(new BorderLayout(10,10));
		JPanel group2 = new JPanel(new BorderLayout(10,10));
		
		con.add(jl1);                        
		con1.add(inputSpace);               
		con2.add(buttonPanel); 
		
		con3.add(button1,BorderLayout.WEST); 
		con3.add(button2,BorderLayout.CENTER);
		con3.add(button3,BorderLayout.EAST);
		
		con4.add(con, BorderLayout.NORTH);
		con4.add(con2,BorderLayout.NORTH);
		
		group1.add(con, BorderLayout.NORTH);
		group1.add(con1, BorderLayout.SOUTH);
		
		group2.add(con4, BorderLayout.CENTER);
		group2.add(con3, BorderLayout.SOUTH);
		
		//버튼 패널 추가
		add(group1, BorderLayout.CENTER);
		add(group2, BorderLayout.SOUTH);
		//위치와 크기
		setBounds(100, 100, 400, 400);
			
		//화면의 가운데에 띄움
		setLocationRelativeTo(null);
		
		//사이즈조절 불가능
		setResizable(false);
		
		//창을 닫을 때 실행 중인 프로그램도 같이 종료되도록 함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//보이기 여부
		setVisible(true);
		
		// 취소 버튼 이벤트 
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Patment();
				dispose();  // 기존에 있던 창은 없애주는 메서드.
				
			}
		});
		
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