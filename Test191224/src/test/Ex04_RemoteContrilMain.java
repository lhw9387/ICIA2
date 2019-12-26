package test;

public class Ex04_RemoteContrilMain {

	public static void main(String[] args) {
//	Audio 클래스를 Ex04_RemoteControl의 구현클래스로 정의하여
//	메소드를 정의하고
//  main 클래스에서 다형성을 적용하여
//	TV, Audio 객체를 만들어 메소드를 호출해보세요.
		
	Ex04_Television tv = new Ex04_Television();
	tv.turnOn();
	tv.turnOff();
	tv.setVolume(5);
	tv.setMute(true);
// ↑ 11 ~ 15번 인터페이스를 제대로 사용하는 방법은 아님.
System.out.println("----------------------");	
//	TV, Audio 객체를 만들어 메소드를 호출해보세요.	
	Ex04_Audio audio = new Ex04_Audio();
	audio.turnOn();
	audio.turnOff();
	audio.setVolume(10);
	tv.setMute(true); // 디폴트 메소드
System.out.println("----------------------");	
	Ex04_RemoteControl rc = new Ex04_Television();
	rc.turnOn();
	rc.setVolume(5);	
	rc.setMute(true);
	rc.turnOff();
System.out.println("----------------------");	
	rc = new Ex04_Audio();
	rc.turnOn();
	rc.setVolume(5);	
	rc.setMute(true);
	rc.turnOff();
// 	Ex04_RemoteControl 클래스의 static void changBattery() 메소드는 직접 접근해서 사용
	Ex04_RemoteControl.changBattery();
	System.out.println(Ex04_RemoteControl.MAX_VOLUME);
System.out.println("----------------------");	
	rc = new Ex05_SmartTelevision(); 
	rc.turnOn();
	rc.turnOff();
	rc.setVolume(5);
System.out.println("----------------------");	
//	rc.search("www.naver.com"); // Ex04_RemoteControl
	Ex05_InternetSearch is = new Ex05_SmartTelevision();
	is.search("구글");
	
	}

}
