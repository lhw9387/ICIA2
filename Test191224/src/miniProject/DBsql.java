package miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBsql {

 Scanner sc = new Scanner(System.in);

 Connection con = null;

 PreparedStatement pstmt;

 ResultSet rs = null;
 ResultSet loginRs = null;

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

 // 관리자 조회
 public void selectDB() {
  boolean run = true;
  String sql = "SELECT CUSTOMERNO, ID, PW, NAME, TO_CHAR(BIRTH,'YYYY/MM/DD') AS BIRTH, GENDER, PASSPORTNO, PHONE FROM MEMBER";
  try {
   pstmt = con.prepareStatement(sql);
   rs = pstmt.executeQuery();
   System.out.print("관리자 ID : ");
   String select1 = sc.next();
   System.out.print("관리자 PW : ");
   String select2 = sc.next();
   if (select1.equals("관리자") && select2.equals("0000")) {
    while (run) {
     System.out.println("---------------------------------------------------------------------------");
     System.out.println(" 1. 회원 목록 조회 | 2. 상품 생성 | 3. 상품 목록 조회 | 4. 상품가 수정  | 5. 상품 삭제 | 6. 로그아웃");
     System.out.println("---------------------------------------------------------------------------");
     System.out.print("선택 : ");
     int select = sc.nextInt();
     switch (select) {
     case 1:
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
      break;
     case 2:
      tourDB();
      break;
     case 3:
      tourSelectDB();
      break;
     case 4:
      tourchangeDB();
      break;
     case 5:
      tourDeleteDB();
      break;
     case 6:
      run = false;
      System.out.println("관리자 계정이 로그아웃 되었습니다.");
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

 // 회원 탈퇴
 public void deleteDB() {
  String sql = "DELETE FROM MEMBER WHERE PW = ? AND ID = ?";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("탈퇴할 ID : ");
   pstmt.setString(2, sc.next());
   System.out.print("비밀번호 확인 : ");
   pstmt.setString(1, sc.next());
   int result = pstmt.executeUpdate();
   if (result > 0) {
    System.out.println("회원 탈퇴가 완료되었습니다.");
   } else if (result == 0) {
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
   // 로그인 당시 rs대신 loginRs로 로그인 데이터를 저장하여 예약확인 후 예약진행 시 생기는 ID공백을 해결
   loginRs = pstmt.executeQuery();
   if (loginRs.next()) {
    System.out.println(loginRs.getString("ID") + "님 환영합니다.");
    while (run) {
     System.out.println("----------------------------------------------------------------------------");
     System.out.println(" 1. 예약진행(FIT) | 2. 예약진행(패키지) | 3. 예약확인 | 4. 예약변경 | 5. 예약취소 | 6. 로그아웃");
     System.out.println("----------------------------------------------------------------------------");
     System.out.print("선택 : ");
     select2 = sc.nextInt();
     switch (select2) {
     case 1:
      reservationDB();
      break;
     case 2:
      tourReservationDB();
      break;
     case 3:
      reselectDB();
      break;
     case 4:
      changeDB();
      break;
     case 5:
      cancellDB();
      break;
     case 6:
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

 // 예약 진행 (총 가격 소수점 제거 완료 >> 그냥 int로 써봤는데 됐음.)
 public void reservationDB() {
  String sql = "INSERT INTO RESERVATION VALUES(RESERVATION_seq.NextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("ID 확인 : ");
   String id = sc.next();
   pstmt.setString(8, id);
   int price = 0;
   if (id.equals(loginRs.getString("ID"))) {
    System.out.println("-------------------------------");
    System.out.println(" 1. 미국    | 상품가 : 1,000,000원/1인");
    System.out.println(" 2. 유럽    | 상품가 : 1,500,000원/1인");
    System.out.println(" 3. 동남아 | 상품가 : 500,000원/1인");
    System.out.println(" 4. 중국    | 상품가 : 200,000원/1인");
    System.out.println(" 5. 일본    | 상품가 : 300,000원/1인");
    System.out.println("-------------------------------");
    System.out.print("선택 : ");
    int country = sc.nextInt();
    switch (country) {
    case 1:
     price = 1000000;
     pstmt.setString(1, "미국");
     break;
    case 2:
     price = 1500000;
     pstmt.setString(1, "유럽");
     break;
    case 3:
     price = 500000;
     pstmt.setString(1, "동남아");
     break;
    case 4:
     price = 200000;
     pstmt.setString(1, "중국");
     break;
    case 5:
     price = 300000;
     pstmt.setString(1, "일본");
     break;
    }
    System.out.println("---------------");
    System.out.println(" 1. KE | 2. OZ");
    System.out.println("---------------");
    System.out.print("선택 : ");
    int airLine = sc.nextInt();
    switch (airLine) {
    case 1:
     price = (int) ((double) price * 1.2);
     pstmt.setString(2, "KE");
     break;
    case 2:
     price = price;
     pstmt.setString(2, "OZ");
     break;
    }
    System.out.print("출발일 : ");
    pstmt.setString(3, sc.next());
    System.out.print("도착일 : ");
    pstmt.setString(4, sc.next());
    System.out.print("성인(명) : ");
    int anumber = sc.nextInt();
    pstmt.setInt(5, anumber);
    System.out.print("소아(명) : ");
    int cnumber = sc.nextInt();
    pstmt.setInt(6, cnumber);
    System.out.print("유아(명) : ");
    int inumber = sc.nextInt();
    pstmt.setInt(7, inumber);
    int sal = (int) ((anumber * price) + (cnumber * price * 0.7) + (inumber * price * 0.2));
    System.out.println("총 가격은 " + sal + "원 입니다.");
    pstmt.setInt(9, sal);
    int result = pstmt.executeUpdate();
    if (result == 1) {
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

 // 예약 확인 (SDATE / FDATE 표시형식 수정함 >> 출발 날짜에 TO_CHAR AS 도착 날짜에 TO_CHAR를 써주면 됐음 나머지
 // 조회할 것들은 그대로 하나씩 입력)
 public void reselectDB() {
  String sql = "SELECT  RESERVATIONNO, COUNTRY, AIRLINE, TO_CHAR(FDATE,'YYYY/MM/DD') AS FDATE, "
    + "TO_CHAR(SDATE,'YYYY/MM/DD') AS SDATE, ANUMBER, CNUMBER, INUMBER, ID, SAL FROM RESERVATION WHERE ID = ?";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("ID 확인 : ");
   String select = sc.next();
   pstmt.setString(1, select);
   rs = pstmt.executeQuery();
   int next = 0;
   while (true) {
    if (rs.next()) {
     System.out.print("예약번호 : " + rs.getInt("RESERVATIONNO") + "\t");
     System.out.print("출발일 : " + rs.getString("SDATE") + "\t");
     System.out.print("도착일 : " + rs.getString("FDATE") + "\t");
     System.out.print("성인 : " + rs.getInt("ANUMBER") + "명" + "\t");
     System.out.print("소아 : " + rs.getInt("CNUMBER") + "명" + "\t");
     System.out.print("유아 : " + rs.getInt("INUMBER") + "명" + "\t");
     System.out.print("나라 : " + rs.getString("COUNTRY") + "\t");
     System.out.print("항공사 : " + rs.getString("AIRLINE") + "\t");
     System.out.println("총 가격 : " + rs.getInt("SAL") + "원" + "\t");
     next = 1;
    } else {
     break;
    }
   }
   if (next == 0) {
    System.out.println("진행된 예약이 없거나 ID가 일치하지 않습니다.");
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

 }

 // 예약 취소 (패널티 계산식 넣음. >> 예약된 출발 날짜와 수정하는 당일의 현재 날짜를 비교하여 계산식 넣음.)
 public void cancellDB() {
  String sql2 = "SELECT SAL, ROUND(SDATE - SYSDATE) AS BOOKINGDAY FROM RESERVATION WHERE RESERVATIONNO = ? AND ID = ?";
  String sql = "DELETE FROM RESERVATION WHERE RESERVATIONNO = ? AND ID = ?";
  double price = 0;
  int sal = 0;
  int bookingDay = 0;
  try {
   pstmt = con.prepareStatement(sql2);
   System.out.println("취소 패널티 확인");
   System.out.print("ID 확인 : ");
   pstmt.setString(2, sc.next());
   System.out.print("예약번호 확인 : ");
   pstmt.setString(1, sc.next());
   //
   rs = pstmt.executeQuery();
   if (rs.next()) {
    bookingDay = rs.getInt("BOOKINGDAY");
    price = rs.getInt("SAL");
    sal = rs.getInt("SAL");
   }

   if (bookingDay >= 30) {
    price = price * 0;
   } else if (bookingDay >= 15) {
    price = price * 0.2;
   } else if (bookingDay >= 10) {
    price = price * 0.3;
   } else if (bookingDay >= 1) {
    price = price * 0.5;
   } else {
    System.out.println("진행된 예약이 없거나 ID 또는 예약번호가 틀렸습니다.");
    return;
   }

   System.out.println("취소 패널티 " + bookingDay + "일 전 : " + (int) price + "원");

   pstmt = con.prepareStatement(sql);
   System.out.println("---------------------------");
   System.out.println("정말 취소하시겠습니까? 1. Y | 2. N");
   System.out.println("---------------------------");
   System.out.print("선택 : ");
   int select = sc.nextInt();
   switch (select) {
   case 1:
    System.out.print("ID 재확인 : ");
    pstmt.setString(2, sc.next());
    System.out.print("예약번호 재확인 : ");
    pstmt.setString(1, sc.next());
    int result = pstmt.executeUpdate();
    if (result > 0) {
     System.out.println("예약 취소가 완료되었습니다.");
     System.out.println("환불 금액 : " + (int) (sal - price) + "원 입니다.");
    } else if (result == 0) {
     System.out.println("ID 또는 예약번호가 틀렸습니다.");
    }
    break;
   case 2:
    System.out.println("예약 취소를 취소하였습니다.");
    return;
   }

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 // 예약 변경 (총 가격 소수점 제거 완료)
 public void changeDB() {
  String sql = "UPDATE RESERVATION SET COUNTRY = ?, AIRLINE = ?, SDATE = ?, FDATE = ?, ANUMBER = ?, CNUMBER = ?, INUMBER = ?, SAL = ? WHERE RESERVATIONNO = ? AND ID = ?";
  try {
   pstmt = con.prepareStatement(sql);
   int price = 0;
   System.out.print("ID 확인 : ");
   pstmt.setString(10, sc.next());
   System.out.print("예약번호 확인 : ");
   pstmt.setInt(9, sc.nextInt());
   System.out.println("------------------------------");
   System.out.println(" 1. 미국    | 상품가 : 1,000,000원/1인");
   System.out.println(" 2. 유럽    | 상품가 : 1,500,000원/1인");
   System.out.println(" 3. 동남아 | 상품가 : 500,000원/1인");
   System.out.println(" 4. 중국    | 상품가 : 200,000원/1인");
   System.out.println(" 5. 일본    | 상품가 : 300,000원/1인");
   System.out.println("------------------------------");
   System.out.print("선택 : ");
   int country = sc.nextInt();
   switch (country) {
   case 1:
    price = 1000000;
    pstmt.setString(1, "미국");
    break;
   case 2:
    price = 1500000;
    pstmt.setString(1, "유럽");
    break;
   case 3:
    price = 500000;
    pstmt.setString(1, "동남아");
    break;
   case 4:
    price = 200000;
    pstmt.setString(1, "중국");
    break;
   case 5:
    price = 300000;
    pstmt.setString(1, "일본");
    break;
   }
   System.out.println("---------------");
   System.out.println(" 1. KE | 2. OZ");
   System.out.println("---------------");
   System.out.print("선택 : ");
   int airLine = sc.nextInt();
   switch (airLine) {
   case 1:
    price = (int) ((double) price * 1.2);
    pstmt.setString(2, "KE");
    break;
   case 2:
    price = price;
    pstmt.setString(2, "OZ");
    break;
   }
   System.out.print("변경 출발일 : ");
   pstmt.setString(3, sc.next());
   System.out.print("변경 도착일 : ");
   pstmt.setString(4, sc.next());
   System.out.print("성인(명) : ");
   int anumber = sc.nextInt();
   pstmt.setInt(5, anumber);
   System.out.print("소아(명) : ");
   int cnumber = sc.nextInt();
   pstmt.setInt(6, cnumber);
   System.out.print("유아(명) : ");
   int inumber = sc.nextInt();
   pstmt.setInt(7, inumber);
   int sal = (int) ((anumber * price) + (cnumber * price * 0.7) + (inumber * price * 0.2));
   System.out.println("총 가격은 " + sal + "원 입니다.");
   pstmt.setInt(8, sal);
   int result = pstmt.executeUpdate();
   if (result > 0) {
    System.out.println("예약 변경이 완료되었습니다.");
   } else if (result == 0) {
    System.out.println("ID 또는 예약번호가 틀렸습니다.");
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 // 관리자 - 상품 생성
 public void tourDB() {
  String sql = "INSERT INTO TOURPRODUCT VALUES(TOURPRODUCT_seq.NextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("출발일 : ");
   pstmt.setString(1, sc.next());
   System.out.print("도착일 : ");
   pstmt.setString(2, sc.next());
   System.out.print("상품명 : ");
   pstmt.setString(3, sc.next());
   System.out.print("노선 : ");
   pstmt.setString(4, sc.next());
   System.out.print("항공사 : ");
   pstmt.setString(5, sc.next());
   System.out.print("성인 : ");
   pstmt.setInt(6, sc.nextInt());
   System.out.print("소아 : ");
   pstmt.setInt(7, sc.nextInt());
   System.out.print("유아 : ");
   pstmt.setInt(8, sc.nextInt());
   System.out.print("예약인원 : ");
   pstmt.setInt(9, sc.nextInt());
   System.out.print("총 좌석 : ");
   pstmt.setInt(10, sc.nextInt());
   System.out.print("상품가 : ");
   pstmt.setInt(11, sc.nextInt());
   System.out.print("출발여부 : ");
   pstmt.setString(12, sc.next());
   System.out.println("상품 생성 성공");
   pstmt.executeUpdate();
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("상품 생성 실패");
  }
 }

 // 상품 조회
 public void tourSelectDB() {
  String sql = "SELECT TOURNO, TO_CHAR(SDATE,'YYYY/MM/DD') AS SDATE, TO_CHAR(FDATE,'YYYY/MM/DD') AS FDATE, TOURNAME, COUNTRY, AIRLINE, PNUMBER, FNUMBER, PRICE, NOTE FROM TOURPRODUCT";
  try {
   pstmt = con.prepareStatement(sql);
   rs = pstmt.executeQuery();
   while (rs.next()) {
    System.out.print("상품번호 : " + rs.getInt("TOURNO") + "\t");
    System.out.print("출발일 : " + rs.getString("SDATE") + "\t");
    System.out.print("도착일 : " + rs.getString("FDATE") + "\t");
    System.out.print("상품명 : " + rs.getString("TOURNAME") + "\t");
    System.out.print("노선 : " + rs.getString("COUNTRY") + "\t");
    System.out.print("항공사 : " + rs.getString("AIRLINE") + "\t");
    System.out.print("예약 인원 : " + rs.getInt("PNUMBER") + "\t");
    System.out.print("총 좌석 : " + rs.getInt("FNUMBER") + "\t");
    System.out.print("상품가 : " + rs.getInt("PRICE") + "\t");
    System.out.println("출발여부 : " + rs.getString("NOTE") + "\t");
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

 }

 // 상품 삭제
 public void tourDeleteDB() {
  String sql = "DELETE FROM TOURPRODUCT WHERE TOURNO = ?";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("삭제 할 상품번호 : ");
   pstmt.setInt(1, sc.nextInt());
   int result = pstmt.executeUpdate();
   if (result > 0) {
    System.out.println("상품 삭제가 완료되었습니다.");
   } else if (result == 0) {
    System.out.println("존재하지 않는 상품 번호입니다.");
   }

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 // 상품가 수정
 public void tourchangeDB() {
  String sql = "UPDATE TOURPRODUCT SET PRICE = ?, NOTE = ? WHERE TOURNO = ?";
  try {
   pstmt = con.prepareStatement(sql);
   System.out.print("변경 할 상품 번호 : ");
   pstmt.setInt(3, sc.nextInt());
   System.out.print("변경 상품가 : ");
   pstmt.setInt(1, sc.nextInt());
   System.out.print("출발여부 : ");
   pstmt.setString(2, sc.next());
   int result = pstmt.executeUpdate();
   if (result > 0) {
    System.out.println("상품 변경이 완료되었습니다.");
   } else if (result == 0) {
    System.out.println("존재하지 않는 상품번호입니다.");
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 // 여행 예약
 public void tourReservationDB() {
  String sql = "SELECT TOURNO, TO_CHAR(SDATE,'YYYY/MM/DD') AS SDATE, TO_CHAR(FDATE,'YYYY/MM/DD') AS FDATE, TOURNAME, COUNTRY, AIRLINE, PNUMBER, FNUMBER, PRICE, NOTE FROM TOURPRODUCT";
  try {
   pstmt = con.prepareStatement(sql);
   loginRs = pstmt.executeQuery();
   Reservation reservation = new Reservation();
   List<Reservation> reservationList = new ArrayList<Reservation>();
   
   while (loginRs.next()) {
    reservation.setTourNumber(loginRs.getInt("TOURNO"));
    reservation.setAirLine(loginRs.getString("AIRLINE"));
    reservation.setCountry(loginRs.getString("COUNTRY"));
    reservation.setSdate(loginRs.getString("SDATE"));
    reservation.setFdate(loginRs.getString("FDATE"));
    reservation.setPrice(loginRs.getInt("PRICE"));
    
    System.out.print("상품번호 : " + loginRs.getInt("TOURNO") + "\t");
    System.out.print("출발일 : " + loginRs.getString("SDATE") + "\t");
    System.out.print("도착일 : " + loginRs.getString("FDATE") + "\t");
    System.out.print("상품명 : " + loginRs.getString("TOURNAME") + "\t");
    System.out.print("노선 : " + loginRs.getString("COUNTRY") + "\t");
    System.out.print("항공사 : " + loginRs.getString("AIRLINE") + "\t");
    System.out.print("예약 인원 : " + loginRs.getInt("PNUMBER") + "\t");
    System.out.print("총 좌석 : " + loginRs.getInt("FNUMBER") + "\t");
    System.out.print("상품가 : " + loginRs.getInt("PRICE") + "\t");
    System.out.println("출발여부 : " + loginRs.getString("NOTE") + "\t");
    
    reservationList.add(reservation);

   }

//   sql = "SELECT TOURNO, TO_CHAR(SDATE,'YYYY/MM/DD') AS SDATE, TO_CHAR(FDATE,'YYYY/MM/DD') "
//     + "AS FDATE, TOURNAME, COUNTRY, AIRLINE, PNUMBER, FNUMBER, PRICE, NOTE FROM TOURPRODUCT WHERE TOURNO = ?";
//   pstmt = con.prepareStatement(sql);
   
   System.out.print("상품번호 선택 : ");
   int select =sc.nextInt();
//   pstmt.setInt(1, select);
//   rs = pstmt.executeQuery();
   String sql2 = "INSERT INTO RESERVATION VALUES(RESERVATION_seq.NextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   pstmt = con.prepareStatement(sql2);
   Reservation selected = null;
   for (Reservation reservation2 : reservationList) {
    if(reservation2.getTourNumber() == select) {
     selected = reservation2;
     break;
    }
   }
   
   if (selected != null) {
    
//    String country = rs.getString("COUNTRY");
//    String airLine = rs.getString("AIRLINE");
//    String sdate = rs.getString("SDATE");
//    String fdate = rs.getString("FDATE");
    pstmt.setString(1, selected.getCountry());
    pstmt.setString(2, selected.getAirLine());
    pstmt.setString(3, selected.getSdate());
    pstmt.setString(4, selected.getFdate());
    
    
//    pstmt.setString(1, country);
//    pstmt.setString(2, airLine);
//    pstmt.setString(3, sdate);
//    pstmt.setString(4, fdate);
    
    int price = reservation.getPrice();
    System.out.print("성인(명) : ");
    int anumber = sc.nextInt();
    pstmt.setInt(5, anumber);
    System.out.print("소아(명) : ");
    int cnumber = sc.nextInt();
    pstmt.setInt(6, cnumber);
    System.out.print("유아(명) : ");
    int inumber = sc.nextInt();
    pstmt.setInt(7, inumber);
    System.out.print("ID 확인 : ");
    String id = sc.next();
    pstmt.setString(8, id);
    int sal = (int) ((anumber * price) + (cnumber * price * 0.7) + (inumber * price * 0.2));
    System.out.println("총 가격은 " + sal + "원 입니다.");
    pstmt.setInt(9, sal);
    int result = pstmt.executeUpdate();
    if (result == 1) {
     System.out.println("예약이 완료되었습니다.");
    } else {
     System.out.println("진행 된 예약이 존재합니다.");
    }
   } else {
    System.out.println("존재하지 않는 상품입니다.");
   }

  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}