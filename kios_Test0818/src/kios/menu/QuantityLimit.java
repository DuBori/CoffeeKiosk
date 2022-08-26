package kios.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kios.db.DBconnection;
import kios.db.Static;

public class QuantityLimit {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String query = null;
    int product_id, product_count, bill_count;

    public QuantityLimit() {
        try {
            con = DBconnection.getConnection();
            query = "select ? from product where ? < (select ? from menu_product)";
            pstmt=con.prepareStatement(query);
            pstmt.setInt(1, product_id);
            pstmt.setInt(2, product_count);
            pstmt.setInt(3, bill_count);
            System.out.println(Static.count);
            pstmt.executeQuery();
	       /* if() {
	        	JOptionPane.showMessageDialog(null, "선택하신 제품의 재고 수량 제한", "오류", JOptionPane.WARNING_MESSAGE);
	        	return;
	        }*/
        } catch (Exception e) {
        }
    }

    public QuantityLimit(String sql, String p_id) {
        try {
            con = DBconnection.getConnection();
            query = sql;
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, p_id);
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}