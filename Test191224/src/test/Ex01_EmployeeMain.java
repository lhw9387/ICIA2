package test;

public class Ex01_EmployeeMain {

	public static void main(String[] args) {

		Ex01_Employee EmMoney = new Ex01_Employee("김성우", 100);
		System.out.println(EmMoney);
		System.out.println(EmMoney.raisepay("김성우", 100));
		System.out.println("총 지급받은 금액 : " + (int)(EmMoney.pay * 1.1));
		
		System.out.println("-------------------------------------");
		
		Ex01_Manager MaMoney = new Ex01_Manager("김선재", 200, 10);
		System.out.println(MaMoney);
		System.out.println(MaMoney.raisepay("김선재", 200));
		System.out.println("총 지급받은 금액 : " + ((MaMoney.pay * 1.2) + MaMoney.comm));
		
		System.out.println("-------------------------------------");
		
		Ex01_Executive ExMoney = new Ex01_Executive("임현우", 500, 50, 50);
		System.out.println(ExMoney);
		System.out.println(ExMoney.raisepay("임현우", 500));
		System.out.println("총 지급받은 금액 : " + ((ExMoney.pay * 1.3) + ExMoney.bonus + ExMoney.comm));

	}

}
