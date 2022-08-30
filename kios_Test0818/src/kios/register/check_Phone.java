package kios.register;

import kios.db.DBconnection;
import kios.db.Static;
import kios.mileage.Ex_Payment;
import kios.mileage.checkMileage;
import kios.mileage.receipt;

import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class check_Phone extends JFrame{

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	int check;
	String chkph="";
	int chkmil;
	
	public check_Phone() {

	}
	// TODO 적립시 연락처 확인 메서드
	public check_Phone(String phone) {
		try {
			System.out.println("무결성 체크 시작");
			con= DBconnection.getConnection();
			query="select member_phone,member_mileage from member_option where member_phone=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, phone);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				chkph=rs.getString("member_phone");
				chkmil=rs.getInt("member_mileage");
			}
			if(!chkph.equals(""))
			{
				Static.phone=chkph;
				new checkMileage(chkph,chkmil);
			}else {
				new Selet_Join();
			}
			
		} catch (Exception e) {
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
				if(chkph.equals("") && pw !=null && name!=null)
				{
					JOptionPane.showMessageDialog(null, "가입을 진행합니다.");
					insert(phone,pw,name);
				}else {
					JOptionPane.showMessageDialog(null, "기존의 정보가 존재합니다.");
					new Register();
				}
				
			} catch (Exception e) {
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
					//JOptionPane.showMessageDialog(null, "회원 가입 성공");
					JOptionPane.showMessageDialog(null, "회원 가입 성공");
					new Ex_Payment();
				}
				else
					JOptionPane.showMessageDialog(null, "회원 가입 실패");
			} catch (SQLException e) {
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
				e.printStackTrace();
			}
			
			
		}
}