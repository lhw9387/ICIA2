package miniProject;

import java.sql.*;
import java.util.Scanner;

import chamgo.Account;
import chamgo.User;

public class DBsql {

	Scanner sc = new Scanner(System.in);

	Connection con = null;

	PreparedStatement pstmt;

	ResultSet rs = null;

	Member mm = new Member();
	Reservation res = new Reservation();
	int select2 = 0;

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
			System.out.println("회원가입 실패 ID가 중복되었습니다.");
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
				System.out.println("ID 또는 비밀번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 	회원 탈퇴
	public void deleteDB() {
		String sql = "DELETE FROM MEMBER WHERE PW = ? AND ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("탈퇴할 ID : ");
			pstmt.setString(2, sc.next());
			System.out.print("비밀번호 확인 : ");
			pstmt.setString(1, sc.next());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("회원 탈퇴가 완료되었습니다.");
			} else if(result == 0) {
				System.out.println("ID 또는 비밀번호가 틀렸습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// 로그인	
	public void loginDB() {
		boolean run = true;
		String sql = "SELECT ID, PW FROM MEMBER WHERE PW = ? AND ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID : ");
			String ID = sc.next();
			pstmt.setString(2, ID);
			System.out.print("PW : ");
			String PW = sc.next();
			pstmt.setString(1, PW);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("ID") + "님 환영합니다.");
				while (run) {
					System.out.println("-----------------------------------------------------");
					System.out.println(" 1. 예약진행 | 2. 예약확인 | 3. 예약변경 | 4. 예약취소 | 5. 로그아웃");
					System.out.println("-----------------------------------------------------");
					System.out.print("선택 : ");
					select2 = sc.nextInt();
					switch (select2) {
					case 1:
						reservationDB();
						break;
					case 2:
						reselectDB();
						break;
					case 3:
						changeDB();
						break;
					case 4:
						cancellDB();
						break;
					case 5:
						run = false;
						System.out.println("로그아웃 되었습니다.");
						break;
					}
				}
			} else {
				System.out.println("ID 또는 비밀번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 예약 진행 (상품가 계산식 있어야 됨.)
	public void reservationDB() {
		String sql = "INSERT INTO RESERVATION VALUES(RESERVATION_seq.NextVal, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID 확인 : ");
			String id = sc.next();
			pstmt.setString(8, id);
			if(id.equals(rs.getString("ID"))) {
				System.out.print("출발일 : ");
				pstmt.setString(1, sc.next());
				System.out.print("도착일 : ");
				pstmt.setString(2, sc.next());
				System.out.print("성인(명) : ");
				pstmt.setString(3, sc.next());
				System.out.print("소아(명) : ");
				pstmt.setString(4, sc.next());			
				System.out.print("유아(명) : ");
				pstmt.setString(5, sc.next());
				System.out.print("나라 : ");
				pstmt.setString(6, sc.next());
				System.out.print("항공사(KE/OZ) : ");
				pstmt.setString(7, sc.next());
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("예약이 완료되었습니다.");	
				} else {
					System.out.println("진행 된 예약이 존재합니다.");
				}
			} else {
				System.out.println("ID가 틀렸습니다.");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예약 확인 (SDATE / FDATE 표시형식 수정해야 됨 + 상품가 총액 추가해야 됨)
	public void reselectDB() {
		String sql = "SELECT * FROM RESERVATION WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID 확인 : ");
			String select = sc.next();
			pstmt.setString(1, select);
			rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.print("예약번호 : " + rs.getInt("RESERVATIONNO") + "\t");
					System.out.print("출발일 : " +  rs.getString("SDATE") + "\t");
					System.out.print("도착일 : " + rs.getString("FDATE") + "\t");
					System.out.print("성인 : " + rs.getInt("ANUMBER") + "명" + "\t");
					System.out.print("소아 : " + rs.getInt("CNUMBER") + "명" + "\t");
					System.out.print("유아 : " + rs.getInt("INUMBER") + "명" + "\t");
					System.out.print("나라 : " + rs.getString("COUNTRY") + "\t");
					System.out.println("항공사 : " + rs.getString("AIRLINE") + "\t");
				} else {
					System.out.println("ID가 틀리거나 진행된 예약이 없습니다.");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	// 예약 취소 (패널티 계산식 넣어야 됨.)
	public void cancellDB() {
		String sql = "DELETE FROM RESERVATION WHERE RESERVATIONNO = ? AND ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID 확인 : ");
			pstmt.setString(2, sc.next());
			System.out.print("예약번호 확인 : ");
			pstmt.setString(1, sc.next());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("예약 취소가 완료되었습니다.");
			} else if(result == 0) {
				System.out.println("ID 또는 예약번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 예약 변경	
	public void changeDB() {
		String sql = "UPDATE RESERVATION SET SDATE = ?, FDATE = ?, ANUMBER = ?, CNUMBER = ?, INUMBER = ?, COUNTRY = ?, AIRLINE = ? WHERE RESERVATIONNO = ? AND ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.print("ID 확인 : ");
			pstmt.setString(9, sc.next());
			System.out.print("예약번호 확인 : ");
			pstmt.setString(8, sc.next());
			System.out.print("변경 출발일 : ");
			pstmt.setString(1, sc.next());
			System.out.print("변경 도착일 : ");
			pstmt.setString(2, sc.next());
			System.out.print("성인(명) : ");
			pstmt.setString(3, sc.next());
			System.out.print("소아(명) : ");
			pstmt.setString(4, sc.next());
			System.out.print("유아(명) : ");
			pstmt.setString(5, sc.next());
			System.out.print("나라 : ");
			pstmt.setString(6, sc.next());
			System.out.print("항공사(KE/OZ) : ");
			pstmt.setString(7, sc.next());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("예약 변경이 완료되었습니다.");
			} else if(result == 0) {
				System.out.println("ID 또는 예약번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}