package kr.traingo.reserve.service;

import java.util.List;

import kr.traingo.reserve.domain.AutoSettingCommand;
import kr.traingo.reserve.domain.CSCommand;
import kr.traingo.reserve.domain.CancelCommand;
import kr.traingo.reserve.domain.Human;
import kr.traingo.reserve.domain.SeatCommand;
import kr.traingo.reserve.domain.SeatSelectedCommand;
import kr.traingo.reserve.domain.TicketCommand;
import kr.traingo.reserve.domain.TrainCommand;
import kr.traingo.table.domain.ResultTimeTableCommand;


public interface ReserveService {
	
	public List<TrainCommand> getTrainList(TrainCommand command);	
	public List<SeatCommand> getSeats(SeatCommand command);
	public TrainCommand getseatinfo(String trainnum);
	public void registerSeat(SeatSelectedCommand command);
	public void registerticket(SeatSelectedCommand command);
	public List<TicketCommand> getTicketList(String id);
	public void CancelTicket(TicketCommand command);
	public void CancelSeat(CancelCommand command);
	public void deleteAutoTrain();	
	public int ModifyAutoTrain(ResultTimeTableCommand rttc);
	
	/*public void insertAutoTrain(List<ResultTimeTableCommand> resultTimeTableCommand);*/
	
	public void insertAutoTrain(ResultTimeTableCommand resultTimeTableCommand);
	
	public int ModifyAutoSeats(int trainnum);
	
	public void inserttest(List<Human> human);
	public List<CSCommand> gettnum();
	public void MakeSeat(CSCommand seat); 
	public void autotrainon();
	public void autotrainoff();
	public int autotrainconfirm();
	
	
	
	/*public void autotrainon(AutoSettingCommand asc);*/
	 
	
	
	
}
