package miniProject;

public class Reservation {

	private String sdate;
	private String fdate;
	private String aNumber;
	private String cNumber;
	private String iNumber;
	private String country;
	private String airLine;
	
	@Override
	public String toString() {
		return "Reservation [sdate=" + sdate + ", fdate=" + fdate + ", aNumber=" + aNumber + ", cNumber=" + cNumber
				+ ", iNumber=" + iNumber + ", country=" + country + ", airLine=" + airLine + "]";
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getcNumber() {
		return cNumber;
	}

	public void setcNumber(String cNumber) {
		this.cNumber = cNumber;
	}

	public String getiNumber() {
		return iNumber;
	}

	public void setiNumber(String iNumber) {
		this.iNumber = iNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}
	
	
}
