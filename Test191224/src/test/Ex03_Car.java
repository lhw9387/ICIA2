package test;

public abstract class Ex03_Car {

	String tire;

//  메소드 선언
	void run() {
		System.out.println("자동차가 굴러갑니다.");
	}
	
//	추상 메소드	선언
	abstract void people();
}
