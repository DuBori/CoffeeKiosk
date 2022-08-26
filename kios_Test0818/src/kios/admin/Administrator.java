package kios.admin;

import kios.db.DBconnection;
import kios.main.mainFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends JFrame {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = null;
    String adminFont = "맑은고딕";

    public Administrator() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(kios.admin.Administrator.class.getResource("/image/logo.png")));
        setTitle("관리자 메뉴");

        JPanel container1 = new JPanel();
        container1.setBackground(new Color(74, 68, 61));
        JPanel container2 = new JPanel();
        container2.setBackground(new Color(74, 68, 61));
        JPanel container3 = new JPanel();
        container3.setBackground(new Color(74, 68, 61));

        JLabel jl1 = new JLabel("관리자 페이지");
        jl1.setForeground(Color.WHITE);

        jl1.setFont(new Font("Dialog", Font.PLAIN, 60));

        JButton btn1 = new JButton("회원정보");
        JButton btn2 = new JButton("매출관리");
        JButton btn3 = new JButton("재고관리");
        JButton btn4 = new JButton("메인으로");
        JButton btn5 = new JButton("종료");

        btn1.setFont(new Font(adminFont, Font.BOLD, 15));
        btn2.setFont(new Font(adminFont, Font.BOLD, 15));
        btn3.setFont(new Font(adminFont, Font.BOLD, 15));
        btn4.setFont(new Font(adminFont, Font.BOLD, 15));
        btn5.setFont(new Font(adminFont, Font.BOLD, 15));

        btn1.setPreferredSize(new Dimension(160,80));
        btn2.setPreferredSize(new Dimension(160,80));
        btn3.setPreferredSize(new Dimension(160,80));
        btn4.setPreferredSize(new Dimension(120,60));
        btn5.setPreferredSize(new Dimension(120,60));

        container1.add(jl1);
        container2.setLayout(new GridLayout(0, 2, 0, 0));

        container2.add(btn1);
        container2.add(btn2);
        container2.add(btn3);

        container3.add(btn4);
        container3.add(btn5);

        getContentPane().add(container1, BorderLayout.NORTH);
        getContentPane().add(container2);
        
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setIcon(new ImageIcon(Administrator.class.getResource("/image/logo__1.png")));
        container2.add(label);
        getContentPane().add(container3, BorderLayout.SOUTH);

        setBounds(200, 200, 500, 450);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

//      여기까지 화면 구현

        // 회원정보 버튼
        btn1.addActionListener(e -> {
            dispose();
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setVisible(true);
        });

        // 매출관리 버튼
        btn2.addActionListener(e -> {
            dispose();
            Turnover turnover = new Turnover();
            turnover.setVisible(true);
        });

        // 재고관리 버튼
        btn3.addActionListener(e -> {
            dispose();
            Stocktaking stocktaking = new Stocktaking();
            stocktaking.setVisible(true);

        });

        // 메인으로 버튼
        btn4.addActionListener(e -> {
            dispose();
            new mainFrame();
        });

        btn5.addActionListener(e -> {
            dataDelete();
            System.exit(0);
        });
    }

    public void dataDelete() {
        try {
            connection = DBconnection.getConnection();

            sql = "delete from menu_product";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            ConClose(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
	private void ConClose(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}