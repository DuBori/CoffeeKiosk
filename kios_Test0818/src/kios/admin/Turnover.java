package kios.admin;

import kios.db.DBconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Turnover extends JFrame {

    DefaultTableModel model;
    JLabel jl1;
    String turnoverInfoFont = "맑은고딕";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;
    int orderSumResult = 0;

    public Turnover() {

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
        btn1.setFont(new Font(turnoverInfoFont, Font.BOLD, 13));
        btn2.setFont(new Font(turnoverInfoFont, Font.BOLD, 13));
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

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setResizable(false);
        
//      여기까지 화면 구현

        // 조회 버튼
        btn1.addActionListener(e -> {
            model.setRowCount(0);
            orderSumResult = 0;
            showTurnover();
        });

        // 뒤로가기 버튼
        btn2.addActionListener(e -> {
            dispose();
            new Administrator();
        });
    }

    // 매출 조회하는 메서드
    public void showTurnover() {
        try {
        	connection = DBconnection.getConnection();

            sql = "select bill_id, product_name, bill_count, bill_cost, bill_date from copy_data order by bill_date";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int billId = resultSet.getInt("bill_id");
                String productName = resultSet.getString("product_name");
                int billCount = resultSet.getInt("bill_count");
                int billCost = resultSet.getInt("bill_cost");
                String billDate = resultSet.getString("bill_date");

                orderSumResult += billCost;
                Object[] data = {billId, productName, billCount, billCost, billDate};

                model.addRow(data);

                jl1.setText("합계 : " + orderSumResult + " 원");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}