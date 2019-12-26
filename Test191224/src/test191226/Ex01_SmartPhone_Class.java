package test191226;

public class Ex01_SmartPhone_Class extends Ex01_Pda implements Ex01_MobilePhone_Interface, Ex01_MP3_Interface{

//	5. SmartPhone Class 
//	   A. PDA를 상속 받고, MobilePhone, MP3를 구현
//	   B. SmartPhone 클래스를 완성하세요. 
	
	@Override
	public void makeCall() {
		System.out.println("전화를 걸다");
	}

	@Override
	public void getCall() {
		System.out.println("전화를 받다");
	}

	@Override
	public void musicOn() {
		System.out.println("음악을 재생하다");
	}

	@Override
	public void musicOff() {
		System.out.println("음악을 멈추다");
	}

	@Override
	public void sendMessage() {
		System.out.println("문자를 받다");	
	}

	@Override
	public void receiveMessage() {
		System.out.println("문자를 보내다");
	}

}
