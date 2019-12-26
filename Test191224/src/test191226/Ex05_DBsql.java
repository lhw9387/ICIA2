package test191226;

import java.sql.*;
import java.util.Scanner;

// 쿼리문을 모아놓는 클래스
public class Ex05_DBsql {

// DB 접속을 위한 변수 선언
	Connection con = null;
	
// 쿼리문 전송을 위한 변수 선언
	PreparedStatement pstmt = null;

// 조회(SELECT) 결과를 저장하기 위한 변수 선언
	ResultSet rs = null;
	
// STUDENT 테이블 전체 조회 메소드
	public void selectDB(Connection con) {
// 실행하고자 하는 쿼리문은 String 변수로 지정

// 빨간 줄 나오면 try catch 설정		
		String sql = "SELECT * FROM STUDENT";
		
		try {
// 접속한 DB에 쿼리문을 전송할 준비
			pstmt = con.prepareStatement(sql); // ← 여기에 쿼리문을 그냥 써도 되지만, 별도로 sql을 만들어서 하는게 가독성이 좋음
// 쿼리문을 실행하고 실행결과를 rs에 저장
			rs = pstmt.executeQuery();
			while(rs.next()) {
// sql 컬럼에 있는 타입에 맞게 print문으로 출력 				
				System.out.print(rs.getInt(1));
				System.out.print(rs.getString(2));
//				System.out.print(rs.getInt(2)); // 타입이 안 맞으면 오류난다. 
				System.out.print(rs.getInt(3));
				System.out.print(rs.getString(4));
				System.out.print(rs.getString(5));
				System.out.println(rs.getString(6));
				
//				System.out.print(rs.getInt("studentno")); // 컬럼 내용을 입력해도 나옴
//				System.out.print(rs.getString("name"));
//				System.out.print(rs.getInt("age"));
//				System.out.print(rs.getString("address"));
//				System.out.print(rs.getString("gender"));
//				System.out.println(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	STUDENT 테이블에 데이터 추가1
	public void insertDB(Connection con) {
		String sql = "INSERT INTO STUDENT VALUES(6, '박누리', 28, '인천시', '여자', '010-2222-3333')";

		try {
			pstmt = con.prepareStatement(sql);

			int result = pstmt.executeUpdate();

			System.out.println("insert 결과" + result);
//	pstmt.executeUpdate(); 1의 값을 가진다.
			
			System.out.println("DB에 저장 성공!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

// STUDENT 테이블에 데이터 추가2 (? 사용)
	public void insertDB2(Connection con) {
		String sql = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 10);
			pstmt.setString(2, "임현우우");
			pstmt.setInt(3, 22);
			pstmt.setString(4, "광명시");
			pstmt.setString(5, "남자");
			pstmt.setString(6, "010-3456-7894");
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// STUDENT 테이블에 데이터 추가3 (스캐너 사용)	
	public void insertDB3(Connection con) {
		String sql = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?)";
		try {
			Scanner sc = new Scanner(System.in);
			pstmt = con.prepareStatement(sql);
			System.out.print("학생 번호 : "); 
			pstmt.setInt(1, sc.nextInt());
			System.out.print("이름 : ");
			pstmt.setString(2, sc.next());
			System.out.print("나이 : ");
			pstmt.setInt(3, sc.nextInt());
			System.out.print("주소 : ");
			pstmt.setString(4, sc.next());
			System.out.print("성별 : ");
			pstmt.setString(5, sc.next());
			System.out.print("전화번호 : ");
			pstmt.setString(6, sc.next());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
