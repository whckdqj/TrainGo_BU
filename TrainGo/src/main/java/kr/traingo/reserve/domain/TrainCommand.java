package kr.traingo.reserve.domain;

public class TrainCommand {
	private String trainnum;
	private int seats;
	private String departsta;
	private String arrivalsta;
	private String departtime;
	private String arrivaltime;
	private String trainname;
	private String charge;
	
	//새로 만들면서 추가
	private String rpossible;		
	public String getRpossible() {
		return rpossible;	}
	public void setRpossible(String rpossible) {
		this.rpossible = rpossible;	}
		
	public String getTrainname() {
		return trainname;
	}
	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
		
	public String getDeparttime() {
		return departtime;
	}
	public void setDeparttime(String departtime) {
		this.departtime = departtime;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public String getTrainnum() {
		return trainnum;
	}
	public void setTrainnum(String trainnum) {
		this.trainnum = trainnum;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
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
	
	
	
}
