package kios.main;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	ImageIcon icon = new ImageIcon("kios_Test0818/src/image/main.png");
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	public static void main(String[] args) {
		new mainFrame();
	}

	public mainFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
        
        //Frame 아이콘 변경
        Image img = kit.getImage("logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(mainFrame.class.getResource("/image/logo.png")));
		setTitle("커피 키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 700);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(74, 68, 61));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(74, 68, 61));
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel.setLayout(new BorderLayout(0, 0));
		
		button_action();
		panel_action();
		
		setVisible(true);	
	}

	private void button_action() {
		{
			lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(mainFrame.class.getResource("/image/logo_1.png")));
			panel.add(lblNewLabel, BorderLayout.CENTER);
		}
		{
			lblNewLabel_1 = new JLabel("   ");
			lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						dispose();
						new checkAdmin();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
			lblNewLabel_1.setIcon(new ImageIcon(mainFrame.class.getResource("/image/manager.png")));
			panel.add(lblNewLabel_1, BorderLayout.EAST);
		}
		{
			lblNewLabel_2 = new JLabel("              ");
			panel.add(lblNewLabel_2, BorderLayout.WEST);
		}
		
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