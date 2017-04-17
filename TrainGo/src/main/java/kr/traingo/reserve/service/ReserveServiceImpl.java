package kr.traingo.reserve.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.traingo.reserve.dao.ReserveMapper;
import kr.traingo.reserve.domain.CSCommand;
import kr.traingo.reserve.domain.CancelCommand;
import kr.traingo.reserve.domain.Human;
import kr.traingo.reserve.domain.SeatCommand;
import kr.traingo.reserve.domain.SeatSelectedCommand;
import kr.traingo.reserve.domain.TicketCommand;
import kr.traingo.reserve.domain.TrainCommand;
import kr.traingo.table.domain.ResultTimeTableCommand;



@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

	@Resource
	private ReserveMapper reserveMapper;



	@Override
	public List<TrainCommand> getTrainList(TrainCommand command) {		
		return reserveMapper.getTrainList(command) ;
	}




	@Override
	public TrainCommand getseatinfo(String trainnum) {
		return reserveMapper.getseatinfo(trainnum);
	}




	@Override
	public List<SeatCommand> getSeats(SeatCommand command) {
		return reserveMapper.getSeats(command);
	}


	@Override
	public void registerSeat(SeatSelectedCommand command) {
		reserveMapper.registerSeat(command);

	}




	@Override
	public void registerticket(SeatSelectedCommand command) {
		reserveMapper.registerticket(command);

	}


	@Override
	public List<TicketCommand> getTicketList(String id) {
		return reserveMapper.getTicketList(id);
	}




	@Override
	public void CancelTicket(TicketCommand command) {
		reserveMapper.CancelTicket(command);

	}




	@Override
	public void CancelSeat(CancelCommand command) {
		reserveMapper.CancelSeat(command);


	}



	@Override
	public void deleteAutoTrain() {
		reserveMapper.deleteAutoTrain();

	}




	@Override
	public int ModifyAutoTrain(ResultTimeTableCommand rttc) {
		return reserveMapper.ModifyAutoTrain(rttc);
	}




	@Override
	public void inserttest(List<Human> human) {
		System.out.println("¿©±â±îÁø µÊ");
		reserveMapper.inserttest(human);

	}




	/*@Override
	public void insertAutoTrain(List<ResultTimeTableCommand> resultTimeTableCommand) {
		reserveMapper.insertAutoTrain(resultTimeTableCommand);		
	}*/

	@Override
	public List<CSCommand> gettnum() {
		return  reserveMapper.gettnum();		
	}


	@Override
	public int ModifyAutoSeats(int trainnum) {
		return reserveMapper.ModifyAutoSeats(trainnum);
	}



	@Override
	public void MakeSeat(CSCommand seat) {
		reserveMapper.MakeSeat(seat);		
	}




	@Override
	public void insertAutoTrain(ResultTimeTableCommand resultTimeTableCommand) {
		reserveMapper.insertAutoTrain(resultTimeTableCommand);


	}




	@Override
	public void autotrainon() {
		reserveMapper.autotrainon();

	}




	@Override
	public void autotrainoff() {
		reserveMapper.autotrainoff();		
	}




	@Override
	public int autotrainconfirm() {

		return reserveMapper.autotrainconfirm();
	}








	/*@Override
	public void autotrainon(AutoSettingCommand asc) {
		reserveMapper.autotrainon(asc);		
	}

	 */











}
