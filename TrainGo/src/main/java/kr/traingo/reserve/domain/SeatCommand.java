package kr.traingo.reserve.domain;

public class SeatCommand {
	
	private String seatnum;
	private String booker;	
	private int start;
	private int end;
	private String trs;
	private String tre;
	private String tonglo;
	private String trainnum;	
	private String departsta;
	private String arrivalsta;	
	
	
			
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	
	

	public String getTonglo() {
		return tonglo;
	}
	public void setTonglo(String tonglo) {
		this.tonglo = tonglo;
	}
	public String getTrs() {
		return trs;
	}
	public void setTrs(String trs) {
		this.trs = trs;
	}
	public String getTre() {
		return tre;
	}
	public void setTre(String tre) {
		this.tre = tre;
	}
	public String getTrainnum() {
		return trainnum;
	}
	public void setTrainnum(String trainnum) {
		this.trainnum = trainnum;
	}
	public String getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}
	public String getDepartsta() {
		return departsta;
	}
	public void setDepartsta(String departsta) {
		this.departsta = departsta;
	}
	public String getArrivalsta() {
		return arrivalsta;
	}
	public void setArrivalsta(String arrivalsta) {
		this.arrivalsta = arrivalsta;
	}
	public String getBooker() {
		return booker;
	}
	public void setBooker(String booker) {
		this.booker = booker;
	}
	
	
	
	

}
