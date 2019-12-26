package test;

public class Ex01_Manager extends Ex01_Employee {

//    A.	관리자 수당 있음. 
//    B.	급여 인상율 20%	
	
	int comm;

	public Ex01_Manager(String name, int pay, int comm) {
		super(name, pay);
		this.comm = comm;
	}
	
	@Override
	int raisepay(String name, int pay) {
		System.out.println(name + " Manager 급여 인상률 : 20%");
		return (int) (pay * 1.2);
	}

	@Override
	public String toString() {
		return "Ex01_Manager [수당=" + comm + ", 이름=" + name + ", 월급=" + pay + "]";
	}
}
