package kios.menu;
 
import kios.db.DBconnection;
import kios.db.Static;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class updateMenu {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	
	
	int find;
	public updateMenu() {

	}
	public updateMenu(String text,String cupSize,String IceHot,int defaultSizeCost,int shot,int count,int cost) {
		try {
			con= DBconnection.getConnection();
			find=findId("select product_id from product where product_name=?",text);
			
			query="insert into menu_product(bill_id,product_id,product_name,bill_size,bill_temper,"
					+ "bill_defaultsize,bill_shot,bill_count,bill_cost)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Static.count);
			pstmt.setInt(2, find);
			pstmt.setString(3, text);
			pstmt.setString(4, cupSize);
			pstmt.setString(5, IceHot);
			pstmt.setInt(6, defaultSizeCost);
			pstmt.setInt(7, shot);
			pstmt.setInt(8, count);
			pstmt.setInt(9, cost);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public updateMenu(String text,int cost,int count) {
		try {
			con= DBconnection.getConnection();
			find=findId("select product_id from product where product_name=?",text);
			
			query="insert into menu_product(bill_id,product_id,product_name,"
					+ "bill_count,bill_cost) "
					+ "values(?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Static.count); // BILL_ID
			pstmt.setInt(2, find); // PRODUCT_ID
			pstmt.setString(3, text); // PRODUCT_NAME
			pstmt.setInt(4, count); // BILL_COUNT
			pstmt.setInt(5, cost); // BILL_COST
			pstmt.executeUpdate();
		
		} catch (Exception e) {
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
			e.printStackTrace();
		}
		if(find!=0)
			return find;
		else
			return 0;
	}
}
