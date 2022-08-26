package kios.main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import kios.admin.Administrator;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class checkAdmin extends JFrame{

   private JPanel contentPane;
   private JTextField tf1;
   private JTextField tf2;
   JButton btn;
   private String id;
   private String pwd;
   int count;
   
   public checkAdmin() throws Exception {
   
      //TODO 아이디 비번 넣는 창.
      checkIdPwd();

   }

   private void checkIdPwd(){
      
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setBounds(200, 200, 500,450);
      contentPane = new JPanel();
      contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(74, 68, 61));
      contentPane.add(panel, BorderLayout.NORTH);
      panel.setLayout(new BorderLayout(0, 0));
      
      JLabel lblNewLabel_2 = new JLabel("관리자 로그인");
      lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
      lblNewLabel_2.setForeground(Color.WHITE);
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(lblNewLabel_2, BorderLayout.SOUTH);
      
      JLabel lblNewLabel_3 = new JLabel("");
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setIcon(new ImageIcon(checkAdmin.class.getResource("/image/logo_1.png")));
      panel.add(lblNewLabel_3, BorderLayout.CENTER);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(new Color(74, 68, 61));
      contentPane.add(panel_1, BorderLayout.CENTER);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBackground(Color.WHITE);
      
      JLabel lblNewLabel_1 = new JLabel("비밀번호");
      lblNewLabel_1.setBackground(Color.BLACK);
      lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
      lblNewLabel_1.setForeground(Color.BLACK);
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      
      tf2 = new JTextField();
      tf2.setBorder(new LineBorder(new Color(74, 68, 61)));
      tf2.setHorizontalAlignment(SwingConstants.LEFT);
      
      tf2 = new JPasswordField();
      tf2.setBorder(new LineBorder(new Color(74, 68, 61)));
      tf2.setHorizontalAlignment(SwingConstants.LEFT);
      tf2.setColumns(10);
      
      JLabel lblNewLabel = new JLabel("아이디");
      lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
      lblNewLabel.setForeground(Color.BLACK);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      tf1 = new JTextField();
      tf1.setBorder(new LineBorder(new Color(74, 68, 61)));
      tf1.setHorizontalAlignment(SwingConstants.LEFT);
      tf1.setColumns(10);
      GroupLayout gl_panel_1 = new GroupLayout(panel_1);
      gl_panel_1.setHorizontalGroup(
         gl_panel_1.createParallelGroup(Alignment.TRAILING)
            .addGroup(gl_panel_1.createSequentialGroup()
               .addContainerGap(36, Short.MAX_VALUE)
               .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
               .addGap(30))
      );
      gl_panel_1.setVerticalGroup(
         gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel_1.createSequentialGroup()
               .addGap(33)
               .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
               .addContainerGap(37, Short.MAX_VALUE))
      );
      
      JButton btn_1 = new JButton("로그인");
      btn_1.setBorder(new LineBorder(new Color(74, 68, 61)));
      btn_1.setBackground(new Color(74, 68, 61));
      btn_1.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            
               if((!tf1.getText().equals("admin") || !tf2.getText().equals("1234")) && count <3)
               {
                  JOptionPane.showMessageDialog(null, "틀리셨습니다."+"남은 기회 : "+(2-count));
                  count++;
               }else if(tf1.getText().equals("admin") && tf2.getText().equals("1234") && count<3){
                     dispose();
                     new Administrator();
                  
               }else if(count>=3){
                  dispose();
                  JOptionPane.showMessageDialog(null, "메인페이지로 돌아갑니다.");
                  new mainFrame();
               }else {
                  dispose();
                  new mainFrame();
               }


         }
      });
      GroupLayout gl_panel_3 = new GroupLayout(panel_3);
      gl_panel_3.setHorizontalGroup(
         gl_panel_3.createParallelGroup(Alignment.TRAILING)
            .addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
               .addGap(63)
               .addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                  .addComponent(btn_1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
                  .addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
                     .addComponent(lblNewLabel)
                     .addComponent(lblNewLabel_1)
                     .addComponent(tf2, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                     .addComponent(tf1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)))
               .addContainerGap(63, Short.MAX_VALUE))
      );
      gl_panel_3.setVerticalGroup(
         gl_panel_3.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
               .addGap(24)
               .addComponent(lblNewLabel)
               .addPreferredGap(ComponentPlacement.UNRELATED)
               .addComponent(tf1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addGap(19)
               .addComponent(lblNewLabel_1)
               .addGap(18)
               .addComponent(tf2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
               .addComponent(btn_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
               .addGap(22))
      );
      panel_3.setLayout(gl_panel_3);
      panel_1.setLayout(gl_panel_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(new Color(74, 68, 61));
      contentPane.add(panel_2, BorderLayout.SOUTH);
      panel_2.setLayout(new BorderLayout(0, 0));
      setVisible(true);
   }
}