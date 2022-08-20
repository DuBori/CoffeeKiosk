package kios.admin;

import kios.db.DBconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MemberInfo extends JFrame {

	DefaultTableModel model;
	JTable table;
	JScrollPane jsp;
	String memberInfoFont = "맑은고딕";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String sql = null;
	
    public MemberInfo(JTable jTable, DefaultTableModel defaultTableModel) {
        this.table = jTable;
        this.model = defaultTableModel;
		DBconnection.getConnection();
    }

	public MemberInfo() {

		setTitle("회원정보");
		
		JPanel container = new JPanel();
		
		// 회원 테이블 속성
		 String[] header = {"휴대폰 번호", "생년월일", "이름", "스탬프", "소비금액"};
		
		// 회원 테이블 생성
		model = new DefaultTableModel(header,0);
		table = new JTable(model);
		table.setModel(model);
		// 회원 정보 넣기
		jsp = new JScrollPane
				(table, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("수정");
        JButton btn3 = new JButton("삭제");
        JButton btn4 = new JButton("뒤로가기");

        btn1.setFont(new Font(memberInfoFont, Font.BOLD, 13));
        btn2.setFont(new Font(memberInfoFont, Font.BOLD, 13));
        btn3.setFont(new Font(memberInfoFont, Font.BOLD, 13));
        btn4.setFont(new Font(memberInfoFont, Font.BOLD, 13));

        btn1.setPreferredSize(new Dimension(90,40));
        btn2.setPreferredSize(new Dimension(90,40));
        btn3.setPreferredSize(new Dimension(90,40));
        btn4.setPreferredSize(new Dimension(90,40));
        
        container.add(btn1);
        container.add(btn2);
        container.add(btn3);
        container.add(btn4);

        add(jsp, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

        setBounds(200, 200, 500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);

//      여기까지 화면 구현

		// 조회 버튼
        btn1.addActionListener(e -> {
			model.setRowCount(0);
			memberShow();
		});

		// 수정 버튼
        btn2.addActionListener(e -> {
			if (table.isRowSelected(table.getSelectedRow())) {
				new MemberUpdateFrame(new MemberInfo(table, model));
			} else {
				JOptionPane.showMessageDialog(null, "수정할 회원을 선택하세요.", "오류", JOptionPane.WARNING_MESSAGE);
			}
		});

		// 삭제 버튼
        btn3.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(null, "실행 취소");
			} else if (result == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "회원 정보가 삭제되지 않았습니다.");
			} else {
				if (table.isRowSelected(table.getSelectedRow())) {
					memberDelete();
				} else {
					JOptionPane.showMessageDialog(null, "삭제할 회원을 선택하세요.", "오류", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// 뒤로가기 버튼
        btn4.addActionListener(e -> {
			dispose();
			new Administrator();
		});
	}
	
	public void memberShow() {
		try {
			connection = DBconnection.getConnection();

			sql = "select * from member_option order by member_phone";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			MemberDTO dto = new MemberDTO();

			while(resultSet.next()) {
				dto.setMember_phone(resultSet.getString("member_phone"));
				dto.setMember_pw(resultSet.getString("member_pw"));
				dto.setMember_name(resultSet.getString("member_name"));
				dto.setMember_mileage(resultSet.getInt("member_mileage"));
				dto.setMember_pay(resultSet.getInt("member_pay"));
				
				String phone = dto.getMember_phone();
				String pw = dto.getMember_pw();
				String name = dto.getMember_name();
				int pay = dto.getMember_pay();
				int mileage = dto.getMember_mileage();
				System.out.println(phone + "\t" + pw + "\t" + name + "\t" + mileage + "\t" + pay + "\t");

				Object[] data = {phone, pw, name, mileage, pay};

				model.addRow(data);
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
    public void memberDelete() {
        try {
        	connection = DBconnection.getConnection();

            sql = "delete from member_option where member_phone = ?";
            preparedStatement = connection.prepareStatement(sql);

            int row = table.getSelectedRow();

            preparedStatement.setString(1, model.getValueAt(row, 0).toString());

            int result = preparedStatement.executeUpdate();

			if (result > 0) {
				JOptionPane.showMessageDialog(null, "회원 정보가 삭제되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "다시 시도하세요.");
			}

            model.removeRow(row);
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
}
