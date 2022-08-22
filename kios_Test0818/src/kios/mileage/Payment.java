package kios.mileage;

import kios.db.Static;
import kios.main.mainFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Payment extends JFrame implements ActionListener{
	
	ImageIcon card = new ImageIcon("kios_Test0818/src/image/icon_card.jpg");
	ImageIcon money = new ImageIcon("kios_Test0818/src/image/money.jpg");
	JButton jbt1 = new JButton("카드",card);
	JButton jbt2 = new JButton("현금",money);
	test_Frame2 tf2;

	public Payment() {
		setTitle("결제창");
		this.setLayout(new GridLayout(0,2));
		getContentPane().add(jbt1);
		getContentPane().add(jbt2);
	       
		setResizable(false);
		this.setSize(400,250);
		this.setVisible(true);
	       
		jbt1.addActionListener(this);
		jbt2.addActionListener(this);

		setLocationRelativeTo(null);
	        
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		if(e.getSource() == jbt1 || e.getSource() == jbt2){
			tf2 = new test_Frame2(e.getActionCommand() + " 결제했습니다.");
			System.out.println(Static.count);
			new receipt(new receipt().select());
			Static.count++;
		 }
	}
}

class test_Frame2 extends JDialog{
	   
	JLabel jlb = new JLabel("");
	JLabel jlb2 = new JLabel("주문번호 : "+ Static.count);
	JPanel group = new JPanel();
	JPanel group2 = new JPanel(new BorderLayout());
	JButton button = new JButton("돌아가기");
		
	public test_Frame2(String str){
	          
		getContentPane().add(jlb);
			
		jlb.setText(str.toString());
		jlb.setHorizontalAlignment(JLabel.CENTER);
		jlb.setFont(jlb.getFont().deriveFont(15.0f));
		jlb2.setFont(jlb.getFont().deriveFont(18.0f));
			
			
		button.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new mainFrame();
					
			}
		});
		group.add(jlb2,BorderLayout.NORTH);
		group2.add(button,BorderLayout.SOUTH);
		add(group,BorderLayout.NORTH);
		add(group2,BorderLayout.SOUTH);
		this.setSize(300,300);
       
		setLocationRelativeTo(null);
			
		this.setModal(true);
 
		this.setVisible(true);

	}
}
