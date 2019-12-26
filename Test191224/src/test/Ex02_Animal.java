package test;

public abstract class Ex02_Animal {
//	abstract class(추상 클래스)
	
//	- 자식 클래스(실체 클래스)의 규격을 정의
//	     메소드를 어떻게 구체화를 할지 틀을 제공(예를 들어 동물은 소리를 낸다.)
	
//	- 메소드 정의(자식 클래스에서 재정의 가능)
//	  추상 메소드 정의(자식 클래스에서 반드시 재정의 해야 함)
	
//	필드 선언
	String kind;
	
// 	메소드 선언
	void breath() {
		System.out.println("숨을 쉽니다.");
	}

// 	추상 메소드 선언 - 앞에 abstract 추가 / 내용이 없다. - {} 불필요 
	abstract void sound();

}
