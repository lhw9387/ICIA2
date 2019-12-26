package test191226;

import java.sql.Connection;
import java.util.Scanner;

public class Ex05_JdbcMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
//	Ex05_DBConnection 클래스의 makeConnection 메소드 호출하여	
//	con이 DB 접속 정보를 가져옴
//	con 객체를 가지고 DBsql 클래스의 메소드 호출하여 원하는 쿼리문 실행
		
		Connection con = null;
		con = Ex05_DBConnection.makeConnection();
		
		Ex05_DBsql sql = new Ex05_DBsql();
		
// 1번 선택하면 전체 조회, 2번 선택하면 데이터 저장
		while(true) {
		System.out.println("1.데이터 조회 | 2.데이터 저장1 | 3.데이터 저장2 | 4.데이터 저장3");
		int select = sc.nextInt();
		if (select == 1) {
			sql.selectDB(con);
		} else if (select == 2) {
			sql.insertDB(con);
		} else if (select == 3) {
			sql.insertDB2(con);
		} else if (select == 4) {
			sql.insertDB3(con);
		}
	}
		
	}

}
