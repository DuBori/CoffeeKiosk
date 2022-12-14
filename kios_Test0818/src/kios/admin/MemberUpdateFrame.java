package kios.admin;

import kios.db.DBconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberUpdateFrame extends JFrame {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = null;
    JTextField jtf1, jtf2;
    String memberUpdateFont = "맑은고딕";

    public MemberUpdateFrame(MemberInfo memberInfo) {

        setTitle("회원정보 수정");

        setLayout(null);

        JLabel jl1 = new JLabel("생년월일 :    ");
        jl1.setBounds(50, 70, 100, 25);
        jtf1 = new JTextField(10);
        jtf1.setBounds(120, 70, 100, 25);
        jl1.setFont(new Font(memberUpdateFont, Font.BOLD, 12));

        JLabel jl2 = new JLabel("이름 :    ");
        jl2.setBounds(75, 120, 100, 25);
        jtf2 = new JTextField(15);
        jtf2.setBounds(120, 120, 100, 25);
        jl2.setFont(new Font(memberUpdateFont, Font.BOLD, 12));

        JButton btn1 = new JButton("확인");
        btn1.setBounds(78, 210, 60, 25);
        JButton btn2 = new JButton("취소");
        btn2.setBounds(148, 210, 60, 25);

        add(jl1);
        add(jtf1);
        add(jl2);
        add(jtf2);
        add(btn1);
        add(btn2);

        setBounds(300, 300, 300, 300);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);

        setResizable(false);

        // 확인 버튼
        btn1.addActionListener(e -> {
            try {
                if (!(jtf1.getText().isEmpty()) && !(jtf2.getText().isEmpty())) {
                    memberUpdate(memberInfo.table, memberInfo.model);
                    dispose();
                    memberInfo.model.setRowCount(0);
                    memberInfo.memberShow();
                } else {
                    JOptionPane.showMessageDialog(null, "수정할 내용을 입력하세요.", "오류", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // 취소 버튼
        btn2.addActionListener(e ->
				dispose());
    }

    public void memberUpdate(JTable jTable, DefaultTableModel defaultTableModel) {
        try {
        	connection = DBconnection.getConnection();

            sql = "update member_option set member_pw = ?, member_name = ? where member_phone = ?";
            preparedStatement = connection.prepareStatement(sql);

            int row = jTable.getSelectedRow();

            preparedStatement.setString(1, jtf1.getText());
            preparedStatement.setString(2, jtf2.getText());
            preparedStatement.setString(3, defaultTableModel.getValueAt(row, 0).toString());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "회원 정보가 수정되었습니다.");
            }else {
                JOptionPane.showMessageDialog(null, "다시 시도하세요.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null){
                	preparedStatement.close();
                	connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}