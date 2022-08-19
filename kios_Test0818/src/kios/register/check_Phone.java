package kios.register;

import kios.db.DBconnection;
import kios.mileage.Ex_Payment;
import kios.mileage.checkMileage;

import java.sql.*;
import javax.swing.JOptionPane;



public class check_Phone {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	int check;
	String chkph;
	int chkmil;
	
	public check_Phone() {
		// TODO Auto-generated constructor stub
	}
	// TODO 적립시 연락처 확인 메서드
	public check_Phone(String phone) {
		try {
			System.out.println("무결성 체크 시작");
			con= DBconnection.getConnection();
			query="select member_phone,member_milage from member_option where member_phone=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, phone);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				chkph=rs.getString("member_phone");
				chkmil=rs.getInt("member_milage");
			}
			if(chkph==null)
			{
				//TODO  연락처가 확인되어 마일리지 도장 1개 적립 메서드
				new checkMileage(chkph,chkmil);
			}else {
				JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다.");
				new Ex_Payment();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		RsPreClose(rs, pstmt);
		ConClose(con);
	}
	//TODO 회원가입시 존재 유무 확인 메서드
	public check_Phone(String phone,String pw, String name) {
		
			try {
				System.out.println("무결성 체크 시작");
				con=DBconnection.getConnection();
				query="select member_phone from member_option where member_phone=?";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, phone);
				rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					chkph=rs.getString("member_phone");
				}
				if(chkph==null && pw !=null && name!=null)
				{
					System.out.println("가입 진행");
					insert(phone,pw,name);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			RsPreClose(rs, pstmt);
			ConClose(con);
	
}
		public void insert(String phone,String pw, String name) throws Exception
		{
			try {
				con=DBconnection.getConnection();
				query="insert into member_option(member_phone,member_pw,member_name,member_mileage,member_pay) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, phone);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				
				check=pstmt.executeUpdate();
				
				if(check>0) {
					JOptionPane.showMessageDialog(null, "회원 가입 성공");
					new Ex_Payment();
				}
				else
					JOptionPane.showMessageDialog(null, "회원 가입 실패");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt.close();
			ConClose(con);
	
		}
		
		private void ConClose(Connection con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void RsPreClose(ResultSet rs,PreparedStatement pstmt) {
			try {
				if(rs!=null) {
					rs.close();
					pstmt.close();
				}else {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
