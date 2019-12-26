package test191226;

public class Ex04_TryCatch2 {

	public static void main(String[] args) {
// String 클래스에서 문자로 입력 된 숫자를 실제 숫자로 변경해주는 클래스
		String num1 = "100";
		String num2 = "a100";
		System.out.println(num1 + num2); // 결과 값 : 100a100
		int num3 = Integer.parseInt(num1);
		int num4 = Integer.parseInt(num1);
		System.out.println(num3 + num4); // 결과 값 : 200 // num1의 100이 숫자로 변환되서 200으로 출력

// 	num2 를 변환할려고 시도하면 a가 있어서 숫자로 변환이 안됨.
//	int num5 = Integer.parseInt(num2); // NumberFormatException 발생

// try catch 처리		
		try {
			int num5 = Integer.parseInt(num2);
		} catch(NumberFormatException e) {
			System.out.println("문자는 숫자로 변환되지 않습니다.");
		}
// catch에서 Exception으로 작성해도 결과는 똑같이 나온다.
		
		
		
		
		
	}

}
