package kios.mileage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kios.db.DBconnection;
import kios.db.Static;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class receipt extends JFrame {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	
	String proName,billSize;

	int costDefault,shot,billCount,billCost;
	static int total=0;
	private JPanel contentPane;
	
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd | HH:mm:ss z");
	Date date = new Date(System.currentTimeMillis());
	String sysday=formatter.format(date);
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public receipt() {

	}
	public receipt(JTextArea jta)
	{
		setBounds(100, 100, 500, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("영수증");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("<html><p>===================================================================</p>"
				+ "<p>[매장명] KH커피(종로점)</p>"
				+ "<p>[주소] 서울특별시 중구 남대문로 120</p>"
				+ "<p>[대표자]000</p>"
				+ "<p>[매출일]"+sysday+"</p>"
				+ "<p>[TEL]02-000-0000</p>"
				+ "<p>===================================================================</p>"
				+ "<p>&emsp;상품&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+ "단가&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;	"
				+ "수량&emsp;&emsp;&emsp;	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+ "금액</p></html>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JTextArea jArea = jta;
		panel_1.add(jArea, BorderLayout.CENTER);
		
		jArea.append("===================================================================\n");
		jArea.append("************************************이용해주셔서 감사합니다.************************************\n");
		jArea.append("총액\t\t\t\t\t"+total+"원\n\n");
		jArea.append("===================================================================\n");
		jArea.append("KH카드\n카드번호: *******************\n거래일시: "+sysday+"\n승인번호 : "+000000+"\n일시불");
		total=0;
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		setVisible(true);
	}
	
	JTextArea select()
	{
		JTextArea textArea = new JTextArea();
		try {
			System.out.println("들어옴");
			con=DBconnection.getConnection();
			query="select * from menu_product where bill_id=?";
			pstmt=con.prepareStatement(query);
			System.out.println(Static.count);
			pstmt.setInt(1, Static.count);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				proName = rs.getString("product_name");
				billSize = rs.getString("bill_size");
				shot=rs.getInt("bill_shot");
				billCount=rs.getInt("bill_count");
				costDefault = rs.getInt("bill_defaultsize");
				billCost = rs.getInt("bill_cost");
				total+=billCost;
				//System.out.println(proName+","+billSize+","+shot+","+billCount+","+costDefault+","+billCost);
				if(shot>0)
				{
					textArea.append("("+billSize+")"+proName+"\t"+costDefault+"\t\t"+billCount+"\t"+billCost+"\n");
					textArea.append("샷 추가 ->"+shot+"번\t\t"+shot*500+"\n");
				}else {
					textArea.append("("+billSize+")"+proName+"\t"+costDefault+"\t\t"+billCount+"\t"+billCost+"\n");
				}
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return textArea;
		
	}
	
}
