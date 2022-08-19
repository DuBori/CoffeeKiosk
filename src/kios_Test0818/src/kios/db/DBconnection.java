package kios_Test0818.src.kios.db;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBconnection {
	
	public static Connection getConnection() throws Exception
	{
		Connection con;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##kios";
		String pwd= "1234";
		Class.forName(driver);
		System.out.println("드라이버 로딩 성공");
		con=DriverManager.getConnection(url,user,pwd);
		if(con==null)
			JOptionPane.showMessageDialog(null, "DB연결 실패했습니다.");
		return con;	
	}

}
