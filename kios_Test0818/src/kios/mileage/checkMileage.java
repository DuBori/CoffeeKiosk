package kios.mileage;

import kios.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class checkMileage {
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
			if(check>0)
				JOptionPane.showMessageDialog(null, "적립 완료");
			else
				JOptionPane.showMessageDialog(null, "적립 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
