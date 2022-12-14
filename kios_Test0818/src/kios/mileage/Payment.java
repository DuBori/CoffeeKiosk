package kios.mileage;

import kios.db.DBconnection;
import kios.db.Static;
import kios.deco.TextDeco;
import kios.main.mainFrame;
import kios.menu.menuOrder;
import kios.menu.updateMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Payment extends JFrame implements MouseListener{

   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String query = null;
   int down, result, bill_count, product_id;
   test_Frame2 tf2;
   JLabel card,cash;
   String find;
   public Payment() {
	  getContentPane().setBackground(Color.white);
	  setIconImage(Toolkit.getDefaultToolkit().getImage(kios.mileage.Payment.class.getResource("/image/logo.png")));
	  setTitle("결제");
      this.setLayout(new GridLayout(0,2));
      card = new JLabel("");
      cash = new JLabel("");
      
      card.addMouseListener(this);
	  cash.addMouseListener(this);
	  card.setIcon(new ImageIcon(Payment.class.getResource("/image/card.png")));
	  cash.setIcon(new ImageIcon(Payment.class.getResource("/image/cash.png")));
	  
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(card)
					.addGap(18)
					.addComponent(cash, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cash, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addComponent(card, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);


		getContentPane().setLayout(groupLayout);
	       
		setResizable(false);
		this.setSize(400,250);
		this.setVisible(true);

		setLocationRelativeTo(null);
	        
		setVisible(true);
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

	         for (int i = 0; i < Static.outer_ArrayList.size(); i++) {
				 find = findName("select member_name from member_option where member_phone = ?", Static.phone);
	            query = "insert into copy_data(bill_id, member_name, product_name, bill_count, bill_cost, bill_date) values(?, ?, ?, ?, ?, sysdate)";

	            pstmt=con.prepareStatement(query);

	            pstmt.setInt(1, Static.count);
	            pstmt.setString(2, find);
	            pstmt.setString(3, Static.outer_ArrayList.get(i).get(0).toString());
	            pstmt.setInt(4, (int) Static.outer_ArrayList.get(i).get(5));
	            pstmt.setInt(5, (int) Static.outer_ArrayList.get(i).get(6));

	            pstmt.executeUpdate();
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }


@Override
public void mouseClicked(MouseEvent e) {
	if(e.getSource() ==card) { 
		dispose();
	     new updateMenu();
	     new checkMileage().billaddPhone(Static.phone);

	     downId(down);
	     copyData();

	     new checkMileage().billCopyPhone();
	     new checkMileage().accumulatedPay(Static.phone);
		 new receipt(new receipt().select());

	     tf2 = new test_Frame2();
	     Static.count++;
	     Static.panel_3= new JPanel(new GridLayout(20, 1, 80, 0));

	     System.out.println(Static.outer_ArrayList.size());
	     while(Static.outer_ArrayList.size()>0) {
	    	 System.out.println("들어옴");
	    	 Static.outer_ArrayList.remove(0);
	     }
	}else if( e.getSource() ==cash){
	    	 dispose();
		     new updateMenu();
		     new checkMileage().billaddPhone(Static.phone);

		     downId(down);
		     copyData();
		     
		     new checkMileage().billCopyPhone();
		     new checkMileage().accumulatedPay(Static.phone);
			 new receipt(new receipt().select());

		     tf2 = new test_Frame2();
		     Static.count++;
		     Static.panel_3= new JPanel(new GridLayout(20, 1, 80, 0));
		     

		     System.out.println(Static.outer_ArrayList.size());
		     Static.outer_ArrayList.clear();
	}
    Static.str="";
	
}

	public String findName(String sql, String text) {
		try {
			con = DBconnection.getConnection();
			query = sql;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				find = rs.getString("member_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return find;
	}



@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}


class test_Frame2 extends JDialog{

	   JLabel jlb = new JLabel("");
	   JLabel jlb1 = new JLabel("결제가 완료되었습니다", SwingConstants.CENTER);
	   
	   JLabel jlb2 = new JLabel("주문번호 : "+ Static.count);
	   JPanel group = new JPanel();
	   JPanel group2 = new JPanel(new BorderLayout());
	   JButton button = new JButton("돌아가기");

	   public test_Frame2() {
	      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	     // jlb.setIcon(new ImageIcon(Payment.class.getResource("/image/logo__1.png")));
	      // 글씨가 들어가 있는데 판넬색깔이 잡아먹어서 글씨가 안보임
	      jlb1.setForeground(Color.white);
	      jlb2.setForeground(Color.white);
	      //getContentPane().add(jlb);
	      getContentPane().add(jlb1);
      
      // 여기가 배경 판넬
      getContentPane().setBackground(new Color(74, 68, 61));
      jlb2.setBackground(new Color(74, 68, 61));
      group.setBackground(new Color(74, 68, 61));
      group2.setBackground(new Color(74, 68, 61));
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