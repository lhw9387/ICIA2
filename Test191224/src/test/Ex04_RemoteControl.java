package test;

public interface Ex04_RemoteControl {

// 인터페이스(interface)
// 1. 인터페이스는 main 코드와 객체간의 접점 연할을 한다.
	
// 2. 객체의 다형성을 구현하는데 용이하다.
	
// 3. 인터페이스의 구성 요소
	
// 3-1. 상수 필드만 선언이 가능함.	
//      - 일반적인 필드로 선언해도 컴파일 과정에서 	
//        static final이 자동으로 붙는다.
	int MAX_VOLUME = 10; // int number = 10; 변수 선언을 하면
	int MIN_VOLUME = 0;  // 자동으로 상수가 됨
	
// 3.2 추상 메소드를 가짐.
	void turnOn(); // abstract를 붙여도 되고, 안붙여도 된다.
	void turnOff();
	void setVolume(int volume);
	
// 3.3 디폴트 메소드를 가짐	: 기본으로 실행내용까지 가지고 있는 메소드
	default void setMute(boolean mute) {
		if(mute)
			System.out.println("음소거 합니다.");
		else
			System.out.println("음소거 해체 합니다.");
	}
	
// 3.4 정적(static) 메소드 가짐.
//     - 인터페이스를 객체화지 않고도 직접 호출가능
	static void changBattery() {
		System.out.println("건전지를 교환해주세요.");
	}
	
}
