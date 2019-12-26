package test;

public class Ex01_Executive extends Ex01_Manager {

//    A.	보너스 있음. 
//    B.	급여 인상율은 30%	
	
	int bonus;

	public Ex01_Executive(String name, int pay, int comm, int bonus) {
		super(name, pay, comm);
		this.bonus = bonus;
	}
	
	@Override
	int raisepay(String name, int pay) {
		System.out.println(name + " Executive 급여 인상률 : 30%");
		System.out.print("급여 : ");
		return (int) (pay * 1.3);
	}

	@Override
	public String toString() {
		return "Ex01_Executive [뽀나스=" + bonus + ", 수당=" + comm + ", 이름=" + name + ", 월급=" + pay + "]";
	}
}
