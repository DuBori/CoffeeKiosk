package kios.menu;

import kios.db.DBconnection;
import kios.db.Static;

import java.sql.*;

public class updateMenu {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;

	int find;

	public updateMenu() {
		try {
			con = DBconnection.getConnection();

			for (int i = 0; i < menuOrder.outer_ArrayList.size(); i++) {
				find = findId("select product_id from product where product_name=?",menuOrder.outer_ArrayList.get(i).get(0).toString());

				query = "insert into menu_product(bill_id,product_id,product_name,bill_size,bill_temper,"
						+ "bill_defaultsize,bill_shot,bill_count,bill_cost)"
						+ "values(?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Static.count);
				pstmt.setInt(2, find);
				pstmt.setString(3, menuOrder.outer_ArrayList.get(i).get(0).toString());
				pstmt.setString(4, menuOrder.outer_ArrayList.get(i).get(1).toString());
				pstmt.setString(5, menuOrder.outer_ArrayList.get(i).get(2).toString());
				pstmt.setInt(6, (int) menuOrder.outer_ArrayList.get(i).get(3));
				pstmt.setInt(7, (int) menuOrder.outer_ArrayList.get(i).get(4));
				pstmt.setInt(8, (int) menuOrder.outer_ArrayList.get(i).get(5));
				pstmt.setInt(9, (int) menuOrder.outer_ArrayList.get(i).get(6));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public updateMenu(String text, int cost, int count) {
		try {
			con = DBconnection.getConnection();
			find = findId("select product_id from product where product_name=?", text);

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

	public int findId(String sql, String text) {
		try {
			con = DBconnection.getConnection();
			query = sql;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				find = rs.getInt("product_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (find != 0)
			return find;
		else
			return 0;
	}

	public void copyData(String text, int defaultSizeCost, int count, int cost) {
		try {
			con = DBconnection.getConnection();

			query = "insert into copy_data(bill_id, product_name, bill_defaultsize, bill_count, bill_cost, bill_date) values(?, ?, ?, ?, ?, sysdate)";

			pstmt=con.prepareStatement(query);

			pstmt.setInt(1, Static.count);
			pstmt.setString(2, text);
			pstmt.setInt(3, defaultSizeCost);
			pstmt.setInt(4, count);
			pstmt.setInt(5, cost);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCopyData(String text) {
		try {
			con = DBconnection.getConnection();

			query = "delete from copy_data where bill_id = ? and product_name = ?";

			pstmt=con.prepareStatement(query);

			pstmt.setInt(1, Static.count);
			pstmt.setString(2, text);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}