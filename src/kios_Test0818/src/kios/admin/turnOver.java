package kios_Test0818.src.kios.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kios.db.DBconnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class turnOver extends JFrame {

    DefaultTableModel model;
    JLabel jl1;
    String turnoverInfoFont = "맑은고딕";
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    int orderSumResult = 0;

    public turnOver() {

        setTitle("매출관리");
        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel();

        String[] header = {"주문번호", "메뉴명", "수량", "금액", "주문일시"};
        model = new DefaultTableModel(header, 0);
        JTable table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jl1 = new JLabel();
        jl1.setFont(new Font(turnoverInfoFont, Font.BOLD, 20));

        JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("뒤로가기");
        btn1.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));
        btn2.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));
        btn1.setPreferredSize(new Dimension(100,40));
        btn2.setPreferredSize(new Dimension(100,40));

        container1.add(jl1);

        container2.add(btn1);
        container2.add(btn2);

        add(jsp, BorderLayout.NORTH);
        add(container1);
        container1.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 50));
        add(container2, BorderLayout.SOUTH);

        setBounds(150, 150, 700, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//      여기까지 화면 구현

        // 조회 버튼
        btn1.addActionListener(e -> {
           
            try {
            	 model.setRowCount(0);
                 orderSumResult = 0;
				turnover();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        // 뒤로가기 버튼
        btn2.addActionListener(e -> {
            dispose();
            new Administrator();
        });
        
        setVisible(true);
    }

    // 매출 조회하는 메서드
    void turnover() throws Exception {
        try {
        	con = DBconnection.getConnection();
            sql = "select bill_id, sale_name, sale_count, sale_cost * sale_count as \"sale_sum\", sale_date from sales_management";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int billId = rs.getInt("bill_id");
                String saleName = rs.getString("sale_name");
                int saleCount = rs.getInt("sale_count");
                int saleSum = rs.getInt("sale_sum");
                String saleDate = rs.getString("sale_date");

                orderSumResult += saleSum;
                Object[] data = {billId, saleName, saleCount, saleSum, saleDate};

                model.addRow(data);

                jl1.setText("합계 : " + orderSumResult + " 원");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RsPreClose(rs, pstmt);
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
}
}