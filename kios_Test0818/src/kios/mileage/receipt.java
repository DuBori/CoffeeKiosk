package kios.mileage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kios.db.DBconnection;
import kios.db.Static;
import kios.main.subMainFrame;

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
	
	String proName,billSize,memPh;

	int costDefault,shot,billCount,billCost,proId;
	public static int total=0;
	public static String phone="";
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
	public receipt(String jta)
	{
		setBounds(100, 100, 500, 630);
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
				+ "금액</p><p>===================================================================</p></html>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		jta+="<p>===================================================================</p>";
		jta+="<p>**********************************이용해주셔서 감사합니다.**********************************</p>";
		jta+="<p>결제금액&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+ "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+total+"원</p>";
		jta+="<p>(부가세포함)&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+ "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
				+ "("+(total/11)+")</p>";
		jta+="<p>===================================================================</p>";
		jta+="<p>KH카드</p><p>카드번호: *******************</p><p>거래일시: "+sysday+"</p><p>승인번호 : "+000000+"</p><p>일시불</p></html>";
		JLabel jArea =new JLabel(jta);
		panel_1.add(jArea, BorderLayout.CENTER);
		
		updateTotal(total);
		total=0;
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		setVisible(true);
	}
	
	private void updateTotal(int cost) {
	
		try {	
			con = DBconnection.getConnection();
			query="update member_option set member_pay = member_pay +? where member_phone ="
				+ "(select DISTINCT member_phone  from menu_product where (bill_id=?) and (member_phone=?) )";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, cost);
			pstmt.setInt(2,Static.count);
			pstmt.setString(3, phone);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	String select()
	{
		String addString="<html>";
		try {
			System.out.println("들어옴");
			con=DBconnection.getConnection();
			query="select * from menu_product where (bill_id=?) and (member_phone=?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Static.count);
			System.out.println(Static.count);
			pstmt.setString(2, phone);
			rs=pstmt.executeQuery();
			String addShot="샷 추가";
			String.format("%-"+9+"s", addShot);
			while(rs.next())
			{
				memPh = rs.getString("member_phone");
				proId = rs.getInt("product_id");
				proName = String.format("%-"+9+"s",  rs.getString("product_name")); 
				billSize = rs.getString("bill_size");
				shot=rs.getInt("bill_shot");
				billCount=rs.getInt("bill_count");
				costDefault = rs.getInt("bill_defaultsize");
				billCost = rs.getInt("bill_cost");
				total+=billCost;
				//System.out.println(proName+","+billSize+","+shot+","+billCount+","+costDefault+","+billCost);
				if(proId!=0)
				{
					if(proId <50) {
						if(shot>0)
						{
							addString+="<p>("+billSize.substring(0,1)+")"+proName+"&emsp;&emsp;&emsp;"+costDefault+
									"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
									+billCount+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+billCost+"원</p>";
							addString+="<p>"+addShot+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
									+ "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;"
									+shot+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
									+shot*500+"원</p>";
						}else {
							addString+="<p>("+billSize.substring(0,1)+")"+proName+"&emsp;&emsp;&emsp;"+costDefault
									+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
									+billCount+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
									+billCost+"</p>";
						}
					}else {
						addString+="<p>"+proName+"&emsp;&emsp;&emsp;"
								+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
								+billCount+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
										+ "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"
								+billCost+"</p>";
					}	
				}
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addString;
		
	}

	

}
