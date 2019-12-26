package test;

public class Ex03_CarMain {

	public static void main(String[] args) {

	Ex03_Suv suv = new Ex03_Suv();
	Ex03_Sedan sedan = new Ex03_Sedan();
	
	suv.run();
	suv.people();
	System.out.println("----------");
	sedan.run();
	sedan.people();
	System.out.println("----------");
//	변수만 사용해서 사용	
	Ex03_Car car = null;
	car = new Ex03_Suv();
	car.people();
	car = new Ex03_Sedan();
	car.people();
	
	}

}
