package kios.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import kios.db.DBconnection;

public class MemberInfo extends JFrame {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	DefaultTableModel model;
	JTable table;
	String name,phone,pw;
	int pay,milage;
	public JScrollPane jsp;
	String memberInfoFont = "맑은고딕";
	
    public MemberInfo(JTable jTable, DefaultTableModel defaultTableModel) {
        this.table = jTable;
        this.model = defaultTableModel;
    }
	public MemberInfo() throws Exception {
		setTitle("회원정보");
		
		
		JPanel container = new JPanel();
		
		// TODO 회원 테이블 속성
		 String[] header = {"휴대폰 번호", "생년월일", "이름", "스탬프", "소비금액"};
		
		// TODO 회원 테이블 생성
		model = new DefaultTableModel(header,0);
		table=new JTable(model);
		//TODO 회원 정보 넣기
		jsp = new JScrollPane
				(table, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("수정");
        JButton btn3 = new JButton("삭제");
        JButton btn4 = new JButton("뒤로가기");

        btn1.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn2.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn3.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn4.setFont(new Font(memberInfoFont, Font.BOLD, 12));

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
        
        setVisible(true);
        // TODO 
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					model.setRowCount(0);
					memberShow();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	

            }
        });
        btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MemberUpdateFrame(new MemberInfo(table, model));
				
			}
		});
        btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					memberDelete();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Administrator();
			}
		});

	}
	
	void memberShow() throws Exception
	{
		
		try {
			con=DBconnection.getConnection();
			System.out.println("회원 조회");
			query="select *  from member_option";
			pstmt=con.prepareStatement(query);
			rs=pstmt.executeQuery();
			MemberDTO dto = new MemberDTO();
			while(rs.next())
			{
				dto.setMember_phone(rs.getString("member_phone"));
				dto.setMember_pw(rs.getString("member_pw"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_pay(rs.getInt("member_pay"));
				dto.setMember_milage(rs.getInt("member_milage"));
				
				phone=dto.getMember_phone();
				pw = dto.getMember_pw();
				name =dto.getMember_name();
				pay = dto.getMember_pay();
				milage=dto.getMember_milage();
				System.out.println(phone+"\t"+pw+"\t"+name+"\t"+pay+"\t"+milage+"\t");
				Object[] data = {phone,pw,name,pay,milage};
				model.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RsPreClose(rs, pstmt);
		ConClose(con);
		
	}
    public void memberDelete() throws Exception {
        try {
        	con=DBconnection.getConnection();
            query = "delete from member_option where member_phone = ?";
            pstmt = con.prepareStatement(query);

            int row = table.getSelectedRow();

            pstmt.setString(1, model.getValueAt(row, 0).toString());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "회원 삭제 성공");
            }else {
                JOptionPane.showMessageDialog(null, "회원 삭제 실패");
            }

            model.removeRow(row);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pstmt.close();
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
		try {
			if(rs!=null) {
				rs.close();
				pstmt.close();
			}else {
				pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
