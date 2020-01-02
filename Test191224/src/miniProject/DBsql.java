package miniProject;

import java.sql.*;
import java.util.Scanner;

public class DBsql {

	Scanner sc = new Scanner(System.in);
	
	Connection con = null;

	PreparedStatement pstmt;

	ResultSet rs = null;

	Member mm = new Member();

	public void dbConnection() {
		con = DBConnection.makeConnection();
	}
	
// 회원가입	
	public void joinDB() {
		String sql = "INSERT INTO MEMBER VALUES(MEMBER_seq.NextVal, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID : ");
			pstmt.setString(1, sc.next());
			System.out.print("PW : ");
			pstmt.setString(2, sc.next());
			System.out.print("이름 : ");
			pstmt.setString(3, sc.next());
			System.out.print("생년월일 : ");
			pstmt.setString(4, sc.next());
			System.out.print("성별 : ");
			pstmt.setString(5, sc.next());
			System.out.print("여권번호 : ");
			pstmt.setString(6, sc.next());
			System.out.print("휴대폰번호 : ");
			pstmt.setString(7, sc.next());
			pstmt.executeUpdate();
			System.out.println("회원가입 성공");
		} catch (Exception e) {
			System.out.println("ID가 중복되었습니다.");
		}
	}
	
// 	관리자 조회
	public void selectDB() {
		String sql = "SELECT * FROM MEMBER";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.print("관리자 ID : ");
			String select1 = sc.next();
			System.out.print("관리자 PW : ");
			String select2 = sc.next();
			if (select1.equals("관리자") && select2.equals("0000")) {
				while (rs.next()) {
					System.out.print("고객번호 : " + rs.getInt("CUSTOMERNO") + "\t");
					System.out.print("ID : " + rs.getString("ID") + "\t");
					System.out.print("PW : " + rs.getString("PW") + "\t");
					System.out.print("이름 : " + rs.getString("NAME") + "\t");
					System.out.print("생년월일 : " + rs.getString("BIRTH") + "\t");
					System.out.print("성별 : " + rs.getString("GENDER") + "\t");
					System.out.print("여권 번호 : " + rs.getString("PASSPORTNO") + "\t");
					System.out.println("휴대폰 번호 : " + rs.getString("PHONE") + "\t");
				}
			} else {
				System.out.println("계좌번호 또는 비밀번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
