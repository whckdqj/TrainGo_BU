package kr.traingo.reserve.domain;

public class CancelCommand {
	private int trainnum;
	private String booker;
	private int seatnum;
	
	
	
	public int getTrainnum() {
		return trainnum;
	}
	public void setTrainnum(int trainnum) {
		this.trainnum = trainnum;
	}
	public String getBooker() {
		return booker;
	}
	public void setBooker(String booker) {
		this.booker = booker;
	}
	public int getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}

}
