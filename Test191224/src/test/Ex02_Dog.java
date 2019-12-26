package test;

// Ex02_Animal 클래스의 실체 클래스(자식 클래스)
public class Ex02_Dog extends Ex02_Animal {
// Ex02_Dog에 빨간 줄이 나옴 : 추상 메소드를 구체화 시켜라.

// 추상 클래스에서 정의한 추상메소드를 구체화
	
	@Override
	void sound() {
	System.out.println("멍멍");	
	}
	
}
