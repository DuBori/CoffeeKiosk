package kios.admin;
 
import kios.db.DBconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Enumeration;

public class Stocktaking extends JFrame {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;
    DefaultTableModel model;
    String stocktakingFont = "맑은고딕";
    JTextField jtf1;
    ButtonGroup bg;

    public Stocktaking() {
    	getContentPane().setBackground(new Color(74, 68, 61));

        setTitle("재고관리");

        getContentPane().setLayout(null);

        JLabel jl1 = new JLabel("재고현황");
        jl1.setForeground(Color.WHITE);
        jl1.setBounds(40, 45, 100, 25);
        jl1.setFont(new Font(stocktakingFont, Font.BOLD, 18));

        String[] header = {"상품ID", "상품명", "재고"};
        model = new DefaultTableModel(header, 0);
        JTable table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(40, 75, 520, 230);

        JButton btn1 = new JButton("조회");
        btn1.setBounds(253, 320, 100, 25);
        btn1.setFont(new Font(stocktakingFont, Font.BOLD, 12));

        JLabel jl2 = new JLabel("재고추가");
        jl2.setForeground(Color.WHITE);
        jl2.setBounds(40, 370, 100, 25);
        jl2.setFont(new Font(stocktakingFont, Font.BOLD, 18));

        //메뉴 종류 픽스되면 거기에 맞춰서 수정
        JRadioButton jrb1 = new JRadioButton("아메리카노");
        jrb1.setForeground(Color.WHITE);
        JRadioButton jrb2 = new JRadioButton("카페라떼");
        jrb2.setForeground(Color.WHITE);
        JRadioButton jrb3 = new JRadioButton("카페모카");
        jrb3.setForeground(Color.WHITE);
        JRadioButton jrb4 = new JRadioButton("카푸치노");
        jrb4.setForeground(Color.WHITE);
        JRadioButton jrb5 = new JRadioButton("카라멜마키아또");
        jrb5.setForeground(Color.WHITE);
        JRadioButton jrb6 = new JRadioButton("에스프레소");
        jrb6.setForeground(Color.WHITE);
        JRadioButton jrb7 = new JRadioButton("케모마일 티");
        jrb7.setForeground(Color.WHITE);
        JRadioButton jrb8 = new JRadioButton("얼그레이 티");
        jrb8.setForeground(Color.WHITE);
        JRadioButton jrb9 = new JRadioButton("자몽 허니 블랙 티");
        jrb9.setForeground(Color.WHITE);
        JRadioButton jrb10 = new JRadioButton("생강차");
        jrb10.setForeground(Color.WHITE);
        JRadioButton jrb11 = new JRadioButton("녹차");
        jrb11.setForeground(Color.WHITE);
        JRadioButton jrb12 = new JRadioButton("매실차");
        jrb12.setForeground(Color.WHITE);
        JRadioButton jrb13 = new JRadioButton("유자차");
        jrb13.setForeground(Color.WHITE);
        JRadioButton jrb14 = new JRadioButton("치즈 케이크");
        jrb14.setForeground(Color.WHITE);
        JRadioButton jrb15 = new JRadioButton("티라미수 케이크");
        jrb15.setForeground(Color.WHITE);
        JRadioButton jrb16 = new JRadioButton("에그 샌드위치");
        jrb16.setForeground(Color.WHITE);
        JRadioButton jrb17 = new JRadioButton("단호박 샌드위치");
        jrb17.setForeground(Color.WHITE);
        JRadioButton jrb18 = new JRadioButton("스콘");
        jrb18.setForeground(Color.WHITE);
        JRadioButton jrb19 = new JRadioButton("마카롱");
        jrb19.setForeground(Color.WHITE);
        JRadioButton jrb20 = new JRadioButton("와플");
        jrb20.setForeground(Color.WHITE);
        jrb1.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb2.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb3.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb4.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb5.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb6.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb7.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb8.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb9.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb10.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb11.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb12.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb13.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb14.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb15.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb16.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb17.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb18.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb19.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        jrb20.setFont(new Font(stocktakingFont, Font.BOLD, 11));
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        bg.add(jrb4);
        bg.add(jrb5);
        bg.add(jrb6);
        bg.add(jrb7);
        bg.add(jrb8);
        bg.add(jrb9);
        bg.add(jrb10);
        bg.add(jrb11);
        bg.add(jrb12);
        bg.add(jrb13);
        bg.add(jrb14);
        bg.add(jrb15);
        bg.add(jrb16);
        bg.add(jrb17);
        bg.add(jrb18);
        bg.add(jrb19);
        bg.add(jrb20);
        jrb1.setBounds(40, 400, 120, 25);
        jrb2.setBounds(185, 400, 120, 25);
        jrb3.setBounds(315, 400, 120, 25);
        jrb4.setBounds(445, 400, 120, 25);
        jrb5.setBounds(40, 430, 120, 25);
        jrb6.setBounds(185, 430, 120, 25);
        jrb7.setBounds(315, 430, 120, 25);
        jrb8.setBounds(445, 430, 120, 25);
        jrb9.setBounds(40, 460, 120, 25);
        jrb10.setBounds(185, 460, 120, 25);
        jrb11.setBounds(315, 460, 120, 25);
        jrb12.setBounds(445, 460, 120, 25);
        jrb13.setBounds(40, 490, 120, 25);
        jrb14.setBounds(185, 490, 120, 25);
        jrb15.setBounds(315, 490, 120, 25);
        jrb16.setBounds(445, 490, 120, 25);
        jrb17.setBounds(40, 520, 120, 25);
        jrb18.setBounds(185, 520, 120, 25);
        jrb19.setBounds(315, 520, 120, 25);
        jrb20.setBounds(445, 520, 120, 25);

        JLabel jl3 = new JLabel("수량 : ");
        jl3.setForeground(Color.WHITE);
        jl3.setFont(new Font(stocktakingFont, Font.BOLD, 12));
        jtf1 = new JTextField(5);
        jl3.setBounds(185, 565, 100, 25);
        jtf1.setBounds(235, 565, 100, 25);

        JButton btn2 = new JButton("추가");
        btn2.setBounds(345, 565, 60, 25);
        btn2.setFont(new Font(stocktakingFont, Font.BOLD, 12));

        JButton btn3 = new JButton("뒤로가기");
        btn3.setBounds(253, 645, 100, 25);
        btn3.setFont(new Font(stocktakingFont, Font.BOLD, 13));

        getContentPane().add(jl1);
        getContentPane().add(jsp);
        getContentPane().add(jl2);
        getContentPane().add(jrb1);
        getContentPane().add(jrb2);
        getContentPane().add(jrb3);
        getContentPane().add(jrb4);
        getContentPane().add(jrb5);
        getContentPane().add(jrb6);
        getContentPane().add(jrb7);
        getContentPane().add(jrb8);
        getContentPane().add(jrb9);
        getContentPane().add(jrb10);
        getContentPane().add(jrb11);
        getContentPane().add(jrb12);
        getContentPane().add(jrb13);
        getContentPane().add(jrb14);
        getContentPane().add(jrb15);
        getContentPane().add(jrb16);
        getContentPane().add(jrb17);
        getContentPane().add(jrb18);
        getContentPane().add(jrb19);
        getContentPane().add(jrb20);
        getContentPane().add(jl3);
        getContentPane().add(jtf1);
        getContentPane().add(btn1);
        getContentPane().add(btn2);
        getContentPane().add(btn3);

        setBounds(150, 150, 615, 740);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setResizable(false);

//      여기까지 화면구현
        
        // 재고 조회 버튼
        btn1.addActionListener(e -> {
            model.setRowCount(0);
            stocktaking();
        });

        // 재고 추가 버튼
        btn2.addActionListener(e -> {
            try {
                if (bg.getSelection() != null && !(jtf1.getText().isEmpty())) {
                    addStock();
                } else {
                    JOptionPane.showMessageDialog(null, "추가할 상품을 선택하고 수량을 입력하세요.", "오류", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            model.setRowCount(0);
            jtf1.setText(null);
            bg.clearSelection();
            stocktaking();
        });

        // 뒤로 가기 버튼
        btn3.addActionListener(e -> {
            dispose();
            new Administrator();
        });
    }

    public void stocktaking() {
		try {
            connection = DBconnection.getConnection();

            sql = "select product_id, product_name, product_count from product order by product_id";
            preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				int product_id = resultSet.getInt("product_id");
				String product_name = resultSet.getString("product_name");
				int product_count = resultSet.getInt("product_count");
				
				Object[] obj = {product_id, product_name, product_count};

				model.addRow(obj);
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

    public void addStock() {
        try {
        	connection = DBconnection.getConnection();

            sql = "update product set product_count = product_count + ? where product_name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(jtf1.getText()));

            Enumeration<AbstractButton> elements = bg.getElements();
            while (elements.hasMoreElements()) {
                AbstractButton abstractButton = elements.nextElement();
            
                if (abstractButton.isSelected()) {
                    preparedStatement.setString(2, abstractButton.getText());
                }
            }

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "재고가 추가되었습니다.");
            }else {
                JOptionPane.showMessageDialog(null, "다시 시도하세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}