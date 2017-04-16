package kr.traingo.table.domain;

public class ResultTimeTableCommand {
	private String trainGradeName;
	private String depPlandTime;
	private String arrPlandTime;
	private String depPlaceName;
	private String arrPlaceName;
	private String adultCharge;
	
	// String Mangement - YYYYMMDDHHmmSS // 20170211191500
	private String dateManager(String str){
		StringBuffer sb=new StringBuffer();
		
		sb.append(str.substring(0, 4));
		sb.append("-");
		sb.append(str.substring(4, 6));
		sb.append("-");
		sb.append(str.substring(6, 8));
		sb.append(" ");
		sb.append(str.substring(8, 10));
		sb.append(":");
		sb.append(str.substring(10, 12));
		
		return sb.toString();
	}
	
	// String Management - Charge
	private String chargeManager(String str){
		String s=String.format("%,d", Integer.parseInt(str));
		return s;
	}
	
	public String getTrainGradeName() {
		return trainGradeName;
	}
	public void setTrainGradeName(String trainGradeName) {
		this.trainGradeName = trainGradeName;
	}
	public String getDepPlandTime() {
		return dateManager(depPlandTime);
	}
	public void setDepPlandTime(String depPlandTime) {
		this.depPlandTime = depPlandTime;
	}
	public String getArrPlandTime() {
		return dateManager(arrPlandTime);
	}
	/* GetRow-Type Time 2017.04.05. JCB Add For CallbackTable Info */
	public String getRowDepPlandTime() {
        return this.depPlandTime;
    }
	public String getRowArrPlandTime() {
        return this.arrPlandTime;
    }
	/* GetRow-Type Time */
	public void setArrPlandTime(String arrPlandTime) {
		this.arrPlandTime = arrPlandTime;
	}
	public String getDepPlaceName() {
		return depPlaceName;
	}
	public void setDepPlaceName(String depPlaceName) {
		this.depPlaceName = depPlaceName;
	}
	public String getArrPlaceName() {
		return arrPlaceName;
	}
	public void setArrPlaceName(String arrPlaceName) {
		this.arrPlaceName = arrPlaceName;
	}
	public String getAdultCharge() {
		return chargeManager(adultCharge);
	}
	public void setAdultCharge(String adultCharge) {
		this.adultCharge = adultCharge;
	}
}
