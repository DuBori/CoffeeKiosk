package kios.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	BufferedImage backgroundImage;
	ImageIcon icon= new ImageIcon("src/image/coffee.png");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new mainFrame();
		System.out.println("임시푸시입니다.");
	}

	// 임시 푸시입니다.
	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setTitle("커피 키오스크");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.paintComponents(getGraphics());
		panel.setLayout(new BorderLayout(0, 0));
		

		button_action();
		panel_action();
		
		setVisible(true);
		
	}

	private void button_action() {
		JButton btnNewButton = new JButton("관리자 모드");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new checkAdmin();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	
		panel.add(btnNewButton, BorderLayout.EAST);
		
	}

	private void panel_action() {
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.addMouseListener(new mainClick()
				{
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new subMainFrame();
			}
				});
		
	}

}
