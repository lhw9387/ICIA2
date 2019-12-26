package test;

public class Ex04_Television implements Ex04_RemoteControl {
// 구현클래스 : 인터페이스를 구현한 메소드이다.(상속과 비슷한 개념)
// 클래스(Ex04_Television) + implements + 인터페이스(Ex04_RemoteControl)
// Ex04_Television 빨간줄이 뜨면, 추상메소드를 ~~~~
	
// 필드 선언
	private int volume;

// 추상 메소드를 실체화 해줘야된다.	
	@Override
	public void turnOn() {
		System.out.println("TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("TV를 끕니다.");
	}

	@Override
	public void setVolume(int volume) {
		if(volume > Ex04_RemoteControl.MAX_VOLUME) {
			this.volume = Ex04_RemoteControl.MAX_VOLUME;
		} else if (volume < Ex04_RemoteControl.MIN_VOLUME) {
			this.volume = Ex04_RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("현재 TV 볼륨 : " + volume);
	}

// 디폴트 메소드를 재정의할 수 있지만, 접근제한자 범위를 더 넓게해줘야된다. public > default	
	@Override
	public void setMute(boolean mute) {
		System.out.println("TV 음소거");
	}
	
}
