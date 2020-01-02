package miniProject;

import java.util.Scanner;
import java.sql.Connection;
import java.util.*;

public class TravelMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean run = true;

		DBsql sql = new DBsql();

		int select = 0;
		while (run) {
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println(" 0. DB 접속 | 1. 회원가입  | 2. 로그인 | 3. 예약진행 | 4. 예약확인 | 5. 예약변경 | 6. 예약취소 | 7. 회원탈퇴 | 8. 종료");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.print("선택 : ");
			select = sc.nextInt();
			switch (select) {
			case 0 : 
				sql.dbConnection();
				break;
			case 1 : 
				sql.joinDB();
				break;
			case 8 :
				run = false;
				System.out.println("프로그램이 종료되었습니다.");
				break;
			case 9 :
				sql.selectDB();
				break;				
			}
		}

	}
}