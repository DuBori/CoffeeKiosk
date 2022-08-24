package kios.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	ImageIcon icon = new ImageIcon("kios_Test0818/src/image/coffee.png");
	public static void main(String[] args) {
		new mainFrame();
	}

	public mainFrame() {
		setTitle("커피 키오스크");
	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);

		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		add(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
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
		JPanel panel_1 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
			};		
		panel_1.addMouseListener(new mainClick()
				{
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new subMainFrame();
			}
				});
		contentPane.add(panel_1, BorderLayout.CENTER);
	}
}

