package kr.traingo.reserve.domain;

public class SeatSelectedCommand {
int seatnum;
String trainnum;
String cancel;



public String getCancel() {
	return cancel;
}
public void setCancel(String cancel) {
	this.cancel = cancel;
}
public int getSeatnum() {
	return seatnum;
}
public void setSeatnum(int seatnum) {
	this.seatnum = seatnum;
}
public String getTrainnum() {
	return trainnum;
}
public void setTrainnum(String trainnum) {
	this.trainnum = trainnum;
}
public String getBooker() {
	return booker;
}
public void setBooker(String booker) {
	this.booker = booker;
}
String booker;
	
}
