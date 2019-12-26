package test191226;

import java.util.ArrayList;
import java.util.List;

public class Ex03_TryCatch {

	public static void main(String[] args) {

//		List<String> list = new ArrayList<String>();
//		
//		list.add("aa");
//		
//		for(int i = 0; i<=list.size();i++) {
//			System.out.println(list.get(i));
//		}
		
//	15번 줄 Exception 발생 / Exception(오류) 발생시 try 사용
		
//  try : 오류가 발생될 내용 / catch - () : 발생한 Exception 이름 + e / {} : 오류발생시 어떻게 설정할지 
		try {
			List<String> list = new ArrayList<String>();

			list.add("aa");

			for (int i = 0; i <= list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("예외가 발생했습니다.");
			e.printStackTrace(); // Exception 내용을 보여줌
//	결과 값 : 오류 대신 "예외가 발생했습니다." 가 출력됨
		} finally {
			System.out.println("무조건 나와요");
		}
// 	finally는 예외가  발생하지 않아도 나온다. / 옵션같은 개념임

	}

}
