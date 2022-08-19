package kios_Test0818.kios.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kios.db.DBconnection;
import kios.db.Static;

public class updateMenu {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	
	
	int find;
	public updateMenu() {
		// TODO Auto-generated constructor stub
	}
	public updateMenu(String text,String cupSize,String IceHot,int shot,int count,int cost) {
		try {
			con=DBconnection.getConnection();
			find=findId("select product_id from product where product_name=?",text);
			
			query="insert into menu_product values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Static.count);
			pstmt.setInt(2, find);
			pstmt.setString(3, text);
			pstmt.setString(4, cupSize);
			pstmt.setString(5, IceHot);
			pstmt.setInt(6, shot);
			pstmt.setInt(7, count);
			pstmt.setInt(8, cost);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int findId(String sql,String text)
	{
		try {
			con=DBconnection.getConnection();
			query=sql;
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, text);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				find=rs.getInt("product_id");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(find!=0)
			return find;
		else
			return 0;
	}
}
