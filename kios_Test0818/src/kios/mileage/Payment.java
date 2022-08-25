package kios.mileage;

import kios.db.DBconnection;
import kios.db.Static;
import kios.main.mainFrame;

import kios.menu.menuOrder;
import kios.menu.updateMenu;
import kios.register.check_Phone;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import static kios.mileage.Ex_Payment.inputSpace;


public class Payment extends JFrame implements ActionListener{

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = null;
	int down, result, bill_count, product_id;

	ImageIcon card = new ImageIcon("kios_Test0818/src/image/icon_card.jpg");
	ImageIcon money = new ImageIcon("kios_Test0818/src/image/money.jpg");
	JButton jbt1 = new JButton("카드",card);
	JButton jbt2 = new JButton("현금",money);
	test_Frame2 tf2;

	public Payment() {
		setTitle("결제창");
		this.setLayout(new GridLayout(0,2));
		getContentPane().add(jbt1);
		getContentPane().add(jbt2);

		setResizable(false);
		this.setSize(400,250);
		this.setVisible(true);

		jbt1.addActionListener(this);
		jbt2.addActionListener(this);

		setLocationRelativeTo(null);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				downId(down);
			}
		});

		jbt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				downId(down);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		if(e.getSource() == jbt1 || e.getSource() == jbt2){
			tf2 = new test_Frame2(e.getActionCommand() + " 결제했습니다.");
			new updateMenu();
			copyData();

			billAddPhone();
			billCopyPhone();
			accumulatedPay();
			new receipt(new receipt().select());
			Static.count++;
			for(int i=0; i<menuOrder.outer_ArrayList.size();i++) {
				menuOrder.outer_ArrayList.remove(i);
			}
		 }
	}

	private void downId(int count) {
		try {
			System.out.println("downId 들어옴");
			con=DBconnection.getConnection();

			query = "select DISTINCT * from menu_product where bill_id = ?";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, Static.count);
			System.out.println(Static.count);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				bill_count = rs.getInt("bill_count");
				product_id = rs.getInt("product_id");
				query = "update product set product_count = product_count - ? where product_id = ? and product_count > 0";
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, bill_count);
				pstmt.setInt(2, product_id);
				result = pstmt.executeUpdate();
				if(result > 0) {
					System.out.println("성공");
				}else {
					System.out.println("실패");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyData() {
		try {
			con = DBconnection.getConnection();

			for (int i = 0; i < menuOrder.outer_ArrayList.size(); i++) {
				query = "insert into copy_data(bill_id, product_name, bill_defaultsize, bill_count, bill_cost, bill_date) values(?, ?, ?, ?, ?, sysdate)";

				pstmt=con.prepareStatement(query);

				pstmt.setInt(1, Static.count);
				pstmt.setString(2, menuOrder.outer_ArrayList.get(i).get(0).toString());
				pstmt.setInt(3, (int) menuOrder.outer_ArrayList.get(i).get(3));
				pstmt.setInt(4, (int) menuOrder.outer_ArrayList.get(i).get(5));
				pstmt.setInt(5, (int) menuOrder.outer_ArrayList.get(i).get(6));

				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void billAddPhone() {

		try {
			con=DBconnection.getConnection();

			query="update menu_product set member_phone=? where bill_id=?";

			pstmt=con.prepareStatement(query);
			pstmt.setString(1,inputSpace.getText());
			pstmt.setInt(2, Static.count);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void billCopyPhone() {
		try {
			con=DBconnection.getConnection();

			query="update copy_data set member_phone = ? where bill_id = ?";

			pstmt=con.prepareStatement(query);
			pstmt.setString(1, inputSpace.getText());
			pstmt.setInt(2, Static.count);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void accumulatedPay() {
		try {
			con = DBconnection.getConnection();
			query = "update member_option set member_pay = (select sum(bill_cost) from copy_data where member_phone = ?) where member_phone = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, inputSpace.getText());
			pstmt.setString(2, inputSpace.getText());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

class test_Frame2 extends JDialog{
	   
	JLabel jlb = new JLabel("");
	JLabel jlb2 = new JLabel("주문번호 : "+ Static.count);
	JPanel group = new JPanel();
	JPanel group2 = new JPanel(new BorderLayout());
	JButton button = new JButton("돌아가기");
		
	public test_Frame2(String str) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		getContentPane().add(jlb);
			
		jlb.setText(str.toString());
		jlb.setHorizontalAlignment(JLabel.CENTER);
		jlb.setFont(jlb.getFont().deriveFont(15.0f));
		jlb2.setFont(jlb.getFont().deriveFont(18.0f));

		button.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new mainFrame();
			}
		});

		group.add(jlb2,BorderLayout.NORTH);
		group2.add(button,BorderLayout.SOUTH);
		add(group,BorderLayout.NORTH);
		add(group2,BorderLayout.SOUTH);
		this.setSize(300,300);
       
		setLocationRelativeTo(null);
			
		this.setModal(true);
 
		this.setVisible(true);
	}
}