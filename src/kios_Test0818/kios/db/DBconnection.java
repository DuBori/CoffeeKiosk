package kios_Test0818.kios.db;

import java.sql.*;
import javax.swing.JOptionPane;

public class DBconnection {
	
	public static Connection getConnection() {

		Connection connection = null;

		String driver = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		String user = "web";

		String password = "1234";

		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩 성공");
			connection = DriverManager.getConnection(url, user, password);

			if (connection == null)
				JOptionPane.showMessageDialog(null, "DB연결에 실패했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
