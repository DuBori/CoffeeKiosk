package kios_Test0818.kios.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import kios.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberUpdateFrame extends JFrame {

    Connection con;
    PreparedStatement pstmt;
    String query;
    JTextField jtf1, jtf2;

    public MemberUpdateFrame(MemberInfo memberInfo) {

        setTitle("회원정보 수정");

        setLayout(null);

        JLabel jl1 = new JLabel("생년월일 :    ");
        jl1.setBounds(50, 70, 100, 25);
        jtf1 = new JTextField(10);
        jtf1.setBounds(120, 70, 100, 25);

        JLabel jl2 = new JLabel("이름 :    ");
        jl2.setBounds(75, 120, 100, 25);
        jtf2 = new JTextField(15);
        jtf2.setBounds(120, 120, 100, 25);

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        // 확인 버튼
        btn1.addActionListener(e -> {
            try {
				memberUpdate(memberInfo.table, memberInfo.model);
				dispose();
	            memberInfo.model.setRowCount(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
           // memberInfo.memberShow();
        });

        // 취소 버튼
        btn2.addActionListener(e ->{
           
				dispose();
	            new Administrator();
        });
                
      
    }



    public void memberUpdate(JTable jTable, DefaultTableModel defaultTableModel) throws Exception {
        try {
        	con=DBconnection.getConnection();
            query = "update member_option set member_pw = ?, member_name = ? where member_phone = ?";
            pstmt = con.prepareStatement(query);

            int row = jTable.getSelectedRow();

            pstmt.setString(1, jtf1.getText());
            pstmt.setString(2, jtf2.getText());
            pstmt.setString(3, defaultTableModel.getValueAt(row, 0).toString());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "정보가 수정되었습니다.");
                new Administrator();
            }else {
                JOptionPane.showMessageDialog(null, "다시 시도하세요.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}