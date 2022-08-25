package kios.mileage;

import kios.db.DBconnection;
import kios.db.Static;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class checkMileage extends JFrame{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	int ckMileage;
	int check;
	public checkMileage() {

	}
	public checkMileage(String text, int count) {
		try {
			con= DBconnection.getConnection();
			query="update member_option set member_mileage=? where member_phone=?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,++count); 
			pstmt.setString(2, text);
			check=pstmt.executeUpdate();
			if(check>0) {	
				JOptionPane.showMessageDialog(null, "적립 완료");
				//billaddPhone(text);
				billCopyPhone(text);
				accumulatedPay(text);
				new Payment();
			}else
				JOptionPane.showMessageDialog(null, "적립 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void billaddPhone(String text) {

		 try {
			 con=DBconnection.getConnection();
			 query="update menu_product set member_phone=? where bill_id=?";
			 pstmt=con.prepareStatement(query);
			 pstmt.setString(1,Static.phone);
			 pstmt.setInt(2, Static.count);
			 pstmt.executeUpdate();
		} catch (SQLException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void billCopyPhone(String text) {
		try {
			con=DBconnection.getConnection();
			query="update copy_data set member_phone = ? where bill_id = ?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,Static.phone);
			pstmt.setInt(2, Static.count);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void accumulatedPay(String text) {
		try {
			con = DBconnection.getConnection();
			query = "update member_option set member_pay = (select sum(bill_cost) from copy_data where member_phone = ?) where member_phone = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, text);
			pstmt.setString(2, text);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
