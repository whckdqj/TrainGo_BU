package kr.traingo.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.traingo.reserve.domain.CSCommand;
import kr.traingo.reserve.domain.CancelCommand;
import kr.traingo.reserve.domain.Human;
import kr.traingo.reserve.domain.SeatCommand;
import kr.traingo.reserve.domain.SeatSelectedCommand;
import kr.traingo.reserve.domain.TicketCommand;
import kr.traingo.reserve.domain.TrainCommand;
import kr.traingo.table.domain.ResultTimeTableCommand;

public interface ReserveMapper {
	@Select("select d.trainnum, d.trainname, d.departsta,d.departtime,d.arrivalsta,d.arrivaltime,d.charge,e.seats from (select count(*) seats,trainnum from seats where booker is null group by trainnum) "
			+ "e right outer join autotrain d on e.TRAINNUM=d.TRAINNUM where "
			+ "d.departsta=#{departsta} and d.arrivalsta=#{arrivalsta} and d.departtime>=sysdate and to_char(d.departtime,'yyyy-mm-dd')=#{departtime} order by d.departtime asc")
	public List<TrainCommand> getTrainList(TrainCommand command); 

	@Select("select seatnum,booker from seats where trainnum=#{trainnum} and seatnum>=#{start} and seatnum<=#{end} order by seatnum asc")
	public List<SeatCommand> getSeats(SeatCommand command);

	@Select("select * from autotrain where trainnum=#{trainnum}")
	public TrainCommand getseatinfo(String trainnum);	


	@Update("update seats set booker=#{booker} where trainnum=#{trainnum} and seatnum=#{seatnum}")
	public void registerSeat(SeatSelectedCommand command);
	//이 부분 ""으로 바꾸는 거 그냥 없는 값도 받도록 만들자.
	@Insert("insert into ticketlist (ticketnum,id,trainnum,seatnum,cancel) values (ticketlist_seq.nextval,#{booker},#{trainnum},#{seatnum},'cancelP')")
	public void registerticket(SeatSelectedCommand command);


	@Select("select a.*,b.*,to_char(a.departtime,'YYYYMMDDHH24MI') dptme,to_char(sysdate,'YYYYMMDDHH24MI') nowme from autotrain a,ticketlist b where a.trainnum=b.trainnum and b.id=#{id} order by a.departtime desc")
	public List<TicketCommand> getTicketList(String id);

	@Update("update ticketlist set cancel='cancel' where id=#{id} and trainnum=#{trainnum} and seatnum=#{seatnum}")
	public void CancelTicket(TicketCommand command);


	@Update("update seats set booker=#{booker} where trainnum=#{trainnum} and seatnum=#{seatnum}")
	public void CancelSeat(CancelCommand command);


	//이거 잘 작동하는지 확인해봐야함.
	@Delete("delete from seats where trainnum in (select trainnum from autotrain where departtime<sysdate)")
	public void deleteAutoTrain();

	//이거 #뒤에 철자 틀렷을 수도 있음. 
	@Select("select count(*) count from autotrain where trainname like #{trainGradeName} and departtime=to_date(#{depPlandTime},'yyyy-mm-dd hh24:mi')")
	public int ModifyAutoTrain(ResultTimeTableCommand rttc);


	@Select("select count(*) count from seats where trainnum like #{trainnum}")
	public int ModifyAutoSeats(int trainnum);



	/*포 이치쓰려고 만든건데 포이치 안쓰기로 함. 거지 같음 public void insertAutoTrain(List<ResultTimeTableCommand> resultTimeTableCommand);*/


	public void inserttest(List<Human> human);

	@Insert("INSERT Into autotrain (trainnum,trainname,seats,departsta,departtime,arrivalsta,arrivaltime,charge) values (autotrain_seq.nextval,#{trainGradeName},240,#{depPlaceName},to_date(#{depPlandTime},'yyyy-mm-dd hh24:mi'),#{arrPlaceName},to_date(#{arrPlandTime},'yyyy-mm-dd hh24:mi'), #{adultCharge})")
	public void insertAutoTrain(ResultTimeTableCommand resultTimeTableCommand);


	@Select("select trainnum from autotrain where departtime>=sysdate")
	public List<CSCommand> gettnum();


	@Insert("insert Into seats (trainnum,seatnum)  values (#{trainnum},#{seatnum})")
	public void MakeSeat(CSCommand seat); 


	@Update("update autosetting set value=1 where name='autotrain'")
	public void autotrainon();

	@Update("update autosetting set value=0 where name='autotrain'")
	public void autotrainoff();

	@Select("select value from autosetting where name='autotrain'")
	public int autotrainconfirm();
}
