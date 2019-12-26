package test191226;

public class Ex01_SmartPhoneMain {

	public static void main(String[] args) {
		Ex01_SmartPhone_Class sp = new Ex01_SmartPhone_Class();
// 	폰 인터페이스 기능
		System.out.println(sp.BUTTON);
		sp.makeCall();
		sp.getCall();
System.out.println("-------------"); 		
//	모바일 폰 인터페이스 기능
		sp.sendMessage();
		sp.receiveMessage();
System.out.println("-------------");
//	MP3 인터페이스 기능		
		sp.musicOff();
		sp.musicOn();
System.out.println("-------------");		
//	Pda 클래스 기능		
		sp.sum(1, 3);

	}

}
