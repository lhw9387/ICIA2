package test;

public class Ex02_AnimalMain {

	public static void main(String[] args) {
//  추상 클래스(Animal)는 객체로 만들어서 사용 할 수 없다.
//	Ex02_Animal ani = new Ex02_Animal();

		Ex02_Dog dog = new Ex02_Dog ();
		dog.sound();
		dog.breath();

		Ex02_Cat cat = new Ex02_Cat ();
		cat.sound();
		cat.breath();
		
//		cat = new Ex02_Dog(); // cat에 Ex02_Dog 클래스를 대입할 순 없다.
		
//	다형성 (polymorphism)		
		Ex02_Animal ani = new Ex02_Dog (); // Ex02_Animal 타입의 ani 변수 선언 / Ex02_Dog () 클래스 대입
		ani.sound(); // 결과 값 : 멍멍
		ani = new Ex02_Cat(); // ani 변수에 Ex02_Cat 클래스 대입
		ani.sound(); // 결과 값 : 냐옹냐옹
		
		
		
		
	}

}
