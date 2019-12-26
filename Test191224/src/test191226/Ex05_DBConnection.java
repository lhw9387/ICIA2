package test191226;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex05_DBConnection {

//	static 메소드 선언
	
	public static Connection makeConnection() { // → DB 접속정보 저장을 위한 Connection 변수 선언
// 	Import 클릭 'Connection' (java sql) 클릭 → 리턴문 작성
		Connection con = null;
		
// 	접속할 DB의 계정정보 > sql 에서 만들었던 계정정보
	    String user = "LHW191226";
		String password = "1234";
		
// 	접속할 DB의 주소 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//                    해당 주소 기준은 oracle express edition기준임 -나중에ip주소로씀- // sql에 접속 새로만들기에 접속 세부정보가 나옴	
		
//	ojdbc6 파일은 현재 소스에 적용
//	첫번째 빨간 밑줄 나오면 try catch 적용
//	두번째 빨간 밑줄 나오면 Add catch clause to surrounding try 적용		
	
		try {
//	ojdbc6 파일은 현재 소스에 적용			
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, user, password);

			System.out.println("DB 접속 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}

// 	DB 접속이 정상적으로 되면 접속상태를 리턴해줌
		return con;

	}
}
