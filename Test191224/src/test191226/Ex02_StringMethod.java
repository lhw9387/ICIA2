package test191226;

public class Ex02_StringMethod {

	public static void main(String[] args) {

// String 메소드 (클래스이기 때문에 대문자)
		String str = "자바는 재밌다"; // 한글이 영어보다 byte가 2~3배 크다
		System.out.println(str); // 결과 값 : 자바는 재밌다
		
// String 변수 글자수 리턴
		System.out.println(str.length()); // 결과 값 : 7 공백도 문자로 인식
		
// 일부 문자 교체 메소드
		String newStr = str.replace("자바", "JAVA"); // str에서 대체할 내용 교체
		System.out.println(newStr); // 결과 값 : JAVA는 재밌다
		System.out.println(str); // 결과 값 : 자바는 재밌다 (변경해도 기존 str은 동일)
	//	(oldChar = 바꿀 대상, newChar = 교체 내용)

// 글자 잘라내기 메소드
		String str2 = str.substring(0, 1); // 0번 인덱스부터 1번 인데스 이후부터 잘라냄
		System.out.println(str2); // 결과 값 : 자
		String str3 = str.substring(2); // 2 이후부터 전부 출력
		System.out.println(str3); // 결과 값 : 는 재밌다
		
// 대소문자 변경 메소드
		String str4 = "abcdEFGHijklMN"; // 대소문자 섞어서 선언
		String str4Lower = str4.toLowerCase();
		System.out.println(str4Lower); // 소문자로 변경
		String str4Upper = str4.toUpperCase();
		System.out.println(str4Upper); // 대문자로 변경

// 공백 제거 메소드
		String str5 = "            자바";
		String str6 = "JAVA           ";
		String str7 = "       이클립스      ";
		String str8 = str5.trim() + str6.trim() + str7.trim();
		System.out.println(str8); // 결과 값 : 자바JAVA이클립스 (공백이 제거된 상태로 출력)
		String str9 = str5 + str6 + str7;
		System.out.println(str9); // 결과 값 :             자바JAVA                  이클립스 (공백이 적용된 상태로 출력)

// String 클래스에서 문자로 입력 된 숫자를 실제 숫자로 변경해주는 클래스
		String num1 = "100";
		String num2 = "a100";
		System.out.println(num1 + num2); // 결과 값 : 100a100
		int num3 = Integer.parseInt(num1);
		int num4 = Integer.parseInt(num1);
		System.out.println(num3 + num4); // 결과 값 : 200 // num1의 100이 숫자로 변환되서 200으로 출력
// num2 를 변환할려고 시도하면 a가 있어서 숫자로 변환이 안됨.
		int num5 = Integer.parseInt(num2); // NumberFormatException 발생
	}

}
