package miniProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection makeConnection() {
		Connection con = null;

		String user = "LHW200102";
		String password = "1234";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, user, password);

			System.out.println("DB 접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패");
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

}