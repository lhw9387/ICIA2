package test;

public class Ex01_Employee {
	String name;
	int pay;
	
//    A. 이름, 급여 정보를 가지고 있음. 
//    B. 급여  인상율 10% 
    
	Ex01_Employee(String name, int pay) {
		this.name = name;
		this.pay = pay;
	}
	
	int raisepay(String name, int pay) {
		System.out.println(name + " Employee 급여 인상률 : 10%");
		System.out.print("급여 : ");
		return (int) (pay * 1.1);
	}

	@Override
	public String toString() {
		return "Ex01_Employee [이름=" + name + ", 월급=" + pay + "]";
	}
	
}
