package kr.traingo.reserve.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import kr.traingo.reserve.domain.CSCommand;
import kr.traingo.reserve.domain.CancelCommand;
import kr.traingo.reserve.domain.SeatCommand;
import kr.traingo.reserve.domain.SeatSelectedCommand;
import kr.traingo.reserve.domain.TicketCommand;
import kr.traingo.reserve.domain.TrainCommand;
import kr.traingo.reserve.service.ReserveService;
import kr.traingo.table.domain.ResultTimeTableCommand;
import kr.traingo.table.util.UtilAjax;
import kr.traingo.util.UtilReserve;



@Controller
@SessionAttributes("user_id")
public class ReserveMainController {


	/* private Logger log = Logger.getLogger(this.getClass());    
    @Resource
    private TheService theService;
	 */	


	@Resource
	private ReserveService reserveService;		


	//���� �޴��� �̵�
	@RequestMapping(value="/resv_main.do", method=RequestMethod.GET)
	public ModelAndView trainlist(HttpSession session){
		System.out.println("dd");	

		ModelAndView mav=new ModelAndView();

		int confirm=reserveService.autotrainconfirm();


		System.out.println(confirm);

		mav.setViewName("trainlist");
		mav.addObject("user_id","admin");
		mav.addObject("autoseat",confirm);


		return mav;


	}



	//Ʈ���������� ��Ʈ ������ ������ �� 
	@RequestMapping("trainseat.do")
	@ResponseBody  
	public Map<String,Object> trainseat(@RequestParam(value="startdate") String departdate){
		//������ ������Ʈ�Ķ��� ������, ���߿� �ٸ� ���� �ް� �Ǵϱ�, �װͱ��� �� Ȯ���� �� ���� ������Ʈ �Ķ����� �ٸ� �� �Ẹ�� �͵� �����غ����� ��.

		//Ʈ����Ŀ�ǵ嵵 ���߿� �ٲ㺸��
		TrainCommand command=new TrainCommand();

		command.setDepartsta("����");
		command.setArrivalsta("�λ�");
		command.setDeparttime(departdate);    	        	

		List<TrainCommand> train=reserveService.getTrainList(command);      	
		Map<String,Object> mapJson =new HashMap<String,Object>();    	
		mapJson.put("list",train);

		return mapJson;	 	}   




	@RequestMapping(value="/selectseat.do")
	public ModelAndView SelectSeat(@RequestParam(value="pageNum",defaultValue="1") String pageNum, @RequestParam("trainnum")String trainnum){

		int rowCount=40;	// �� ������ �¼���
		int pageCount=10;	// �� �������� �������� �ִ� ������ 
		int currentPage = Integer.parseInt(pageNum);		
		int count =240; //�ִ� �¼� ��..���߿� �޼ҵ�� �޾Ƽ� ó���� �� �ְԲ� ������ ��.

		UtilReserve page = new UtilReserve(currentPage, count, rowCount, pageCount, "selectseat.do",trainnum);

		TrainCommand train=reserveService.getseatinfo(trainnum);

		SeatCommand command=new SeatCommand();
		command.setTrainnum(trainnum);
		command.setStart(page.getStartCount());
		command.setEnd(page.getEndCount());

		List<SeatCommand> seatlist=null;			

		seatlist=reserveService.getSeats(command);		

		ModelAndView mav=new ModelAndView();
		mav.setViewName("SelectSeatForm");  
		mav.addObject("train",train);      	 	        	
		mav.addObject("seatlist",seatlist);	        	 
		mav.addObject("pagingHtml",page.getPagingHtml());	

		return mav;		    }    


	@RequestMapping(value="/seatselected.do")
	public String seatselected(@RequestParam(value="id") String booker,@RequestParam(value="seatnum") String seatnums, @RequestParam("trainnum")String trainnum){

		StringTokenizer tokens = new StringTokenizer(seatnums,", ");
		SeatSelectedCommand command=new SeatSelectedCommand();
		//�̰� �� ��Ʈ����Ʈ�� �ٲ� �� ������ ���߿� �ٲ㺼 ��.    	

		command.setTrainnum(trainnum);
		command.setBooker(booker);

		while(tokens.hasMoreElements()){
			int seatnum= Integer.parseInt(tokens.nextToken());

			command.setTrainnum(trainnum);

			command.setSeatnum(seatnum);
			reserveService.registerSeat(command);
			reserveService.registerticket(command);	        


		}    	

		return "redirect:/ticketlist.do";

	}


	@RequestMapping(value="/ticketlist.do")
	public ModelAndView ticketlist(@RequestParam(value="user_id") String booker){



		List<TicketCommand> list=null;
		list=reserveService.getTicketList(booker);		

		ModelAndView mav=new ModelAndView();

		mav.setViewName("TicketListForm");  
		mav.addObject("list",list);      	 	        	




		return mav;			
	}



	@RequestMapping(value="/cancelticket.do")
	public String cancel(@RequestParam(value="id") String id,@RequestParam(value="seatnum") int seatnum, @RequestParam("trainnum")String trainnum){



		CancelCommand command1=new CancelCommand();	    
		TicketCommand command=new TicketCommand();

		command.setId(id);
		command.setSeatnum(seatnum);
		command.setTrainnum(trainnum);       

		//����, ��Ʈ�� �����ؼ� ���� �� ���Ƽ� �̷��� �ߴµ�
		//�����ε� �����ؼ� Ŀ�ǵ尡 �޴��� Ȯ���ϰ� �ʹ�.


		command1.setBooker(id);
		command1.setSeatnum(seatnum);
		command1.setTrainnum(Integer.parseInt(trainnum));



		reserveService.CancelTicket(command);

		reserveService.CancelSeat(command1);



		return "redirect:/ticketlist.do";


	}


	@RequestMapping(value="/autotrain.do")
	public ModelAndView autotrain(){    	

		List<ResultTimeTableCommand> list2 = null;
		UtilAjax util2 = new UtilAjax();
		Calendar cal=Calendar.getInstance();
		ResultTimeTableCommand tt=null;
		int count=0;
		int count1=0;

		reserveService.deleteAutoTrain();


		for(int i=0;i<3;i++){			


			int year = cal.get(Calendar.YEAR);
			int mon = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH)+i;          
			String ymd=year+"-"+String.format("%02d", mon)+"-"+String.format("%02d", day);   

			list2 = util2.getTimeTableFromServer("NAT010000","NAT014445", ymd);

			System.out.println(list2.size());

			for(int z=list2.size()-1;z>=0;z--){
				tt=list2.get(z);	


				count=reserveService.ModifyAutoTrain(tt);


				if(count!=0){
					System.out.println("���� ������ �־� �ε��� "+z+"���� ���Խ�Ű�� �ʽ��ϴ�.");

				}else{
					System.out.println(z+"���� �߰��մϴ�");

					reserveService.insertAutoTrain(tt);
				}

			}	




			/*if(list2.size()!=0){			

			reserveService.insertAutoTrain(list2);


			}*/



		}



		List<CSCommand> tlist=new ArrayList<CSCommand>();	
		CSCommand train=null;


		tlist=reserveService.gettnum();



		for(int y=tlist.size()-1;y>=0;y--){	
			train=tlist.get(y);
			System.out.println(train.getTrainnum());

			count1=reserveService.ModifyAutoSeats(train.getTrainnum());


			if(count1!=0){
				System.out.println("��Ʈ�� �־� �ε��� "+y+"���� �����մϴ�");
				tlist.remove(y);				
			}else{
				System.out.println("��Ʈ�� ����"+y+"y�� ���� ���մϴ�.");
			}

		}




		CSCommand seat2=null;
		//List<CSCommand> tlist3=new ArrayList<CSCommand>();	

		System.out.println("����"+tlist.size());

		for(int x=0;x<tlist.size();x++){

			seat2=tlist.get(x);   			


			for(int y=1;y<=240;y++){


				seat2.setSeatnum(y);    				


				System.out.println("�߰��� ��Ʈ�ѹ�"+seat2.getSeatnum());
				System.out.println("�߰��� ��Ʈ Ʈ���γѹ�"+seat2.getTrainnum());


				//tlist3.add(seat2);

				reserveService.MakeSeat(seat2);


			}

		}

		//���������� �� �� �Ʒ��� �ߵǸ� ��
		//�ȵǸ� �̰� ������

		// reserveService.MakeSeat(tlist3);




		ModelAndView mav=new ModelAndView();

		//�׸��� �̰� Ƽ�ϸ���Ʈ ���� �ƴ���.
		mav.setViewName("trainlist");




		return mav;

	}


	@RequestMapping(value="/autotrainon.do")
	public ModelAndView timeautotrainon(){

		reserveService.autotrainon();       	   

		ModelAndView mav=new ModelAndView();

		int confirm=reserveService.autotrainconfirm();
		mav.setViewName("trainlist");
		mav.addObject("autoseat",confirm);




		return mav;	        	 

	}



	@RequestMapping(value="/autotrainoff.do")
	public ModelAndView timeautotrainoff(){

		reserveService.autotrainoff();       	   

		ModelAndView mav=new ModelAndView();

		int confirm=reserveService.autotrainconfirm();
		mav.setViewName("trainlist");  
		mav.addObject("autoseat",confirm);  		 




		return mav;	        	 

	}










	/*	





	    for(int i=0;i<tlist.size();i++){	    	
	        train=new train();
	        train=tlist.get(i);	      
	    	dao.maketrain(train.getTrainnum());	    	
	    }

		return "/contents/reserve/resv_main.do";*/   	

	/*train=new train();				
		conn=getConnection();
		conn.setAutoCommit(false);
		sql="select trainnum,seats from autotrain where trainnum=? and departtime>=sysdate";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(++cnt,trainnum);			
		rs=pstmt.executeQuery();

		while(rs.next()){						
			train.setTrainnum(rs.getString("trainnum"));
			train.setSeats(rs.getInt("seats"));					
		}
		seats=train.getSeats();			
		sql="insert into seats (trainnum,seatnum) values (?,?)";
		pstmt2=conn.prepareStatement(sql);	

		for(int i=1;i<=seats;i++){
			int cnt2=0;		
			pstmt2.setString(++cnt2, train.getTrainnum());			
			pstmt2.setString(++cnt2, i+"");	
			pstmt2.addBatch();
			if(i==1000){
				pstmt2.executeBatch();		}			
		}
		pstmt2.executeBatch();
		conn.commit();
	 */
































	/*  @RequestMapping(value="/seatselected.do")
    public ModelAndView SeatSelected(@RequestParam()){

    	StringTokenizer tokens=new StringTokenizer




    }    */


	/*   String booker=request.getParameter("id");
	    String trainnum=request.getParameter("trainnum");
	    String seatnums=request.getParameter("seatnum");

	    StringTokenizer tokens = new StringTokenizer(seatnums,", ");

	    TrainDao dao=TrainDao.getInstance();

	    while(tokens.hasMoreElements()){
	    	int seatnum= Integer.parseInt(tokens.nextToken());
	    	dao.registerSeat(trainnum, seatnum, booker);
	  	    dao.registerticket(trainnum, seatnum, booker);
	    }

		return "/contents/reserve/ticketlist.do";
	}
	 */





	/*
    @RequestMapping(value="/selectseat.do")
    public ModelAndView SelectSeat(@RequestParam(value="pageNum",defaultValue="1") String pageNum, @RequestParam("trainnum")String trainnum){

		int rowCount=40;	// �� ������ �¼���
		int pageCount=10;	// �� �������� �������� �ִ� ������ 
		int currentPage = Integer.parseInt(pageNum);		
		int count =240; //�ִ� �¼� ��..���߿� �޼ҵ�� �޾Ƽ� ó���� �� �ְԲ� ������ ��.

		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "selectseat.do",trainnum);

		TrainCommand train=reserveService.getseatinfo(trainnum);

		SeatCommand command=new SeatCommand();
		command.setTrainnum(trainnum);
		command.setStart(page.getStartCount());
		command.setEnd(page.getEndCount());

		List<SeatCommand> seatlist=null;			

		seatlist=reserveService.getSeats(command);		

				 ModelAndView mav=new ModelAndView();
	        	 mav.setViewName("SelectSeatForm");  
	        	 mav.addObject("train",train);
	        	 mav.addObject("seatlist",seatlist);
	        	 mav.addObject("pagingHtml",page.getPagingHtml());	

				return mav;		

    }
	 */







}










/*
    @RequestMapping(value="/insertDB.do", method=RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute("command")@Valid TheCommand command
                             , BindingResult result
                             , SessionStatus status){

        if(log.isDebugEnabled()){
            log.debug("<<TheCommand:>>"+command);
        }

        // Validation Check
        if(result.hasErrors()){
            return callbackDBForm();
        }

        theService.insert(command);
        status.setComplete();

        return new ModelAndView("redirect:/home.do");
    }

    @RequestMapping(value="/fileDB.do", method=RequestMethod.GET)
    public ModelAndView callbackFileForm(){
        FileCommand command = new FileCommand();
        return new ModelAndView("fileDB", "command", command);
    }

    @RequestMapping(value="/fileDB.do", method=RequestMethod.POST)
    public ModelAndView submitDBForm(@ModelAttribute("command") FileCommand command
                                    , SessionStatus status){
        command.setId("apple");
        if(log.isDebugEnabled()){
            log.debug("<<FileCommand:>>"+command);
        }

        theService.insertFile(command);
        status.setComplete();
        return new ModelAndView("redirect:/home.do");
    }


    @RequestMapping(value="/theView.do", method=RequestMethod.GET)
    public ModelAndView viewDetail(){
        // test seq = 62
        int seq = 62;
        if(log.isDebugEnabled()){
            log.debug("<<Sample Sequence No.>> : "+seq);
        }

        FileCommand fic = theService.selectFile(seq);

        return new ModelAndView("theView", "files", fic);
    }

    // fileDown
    @RequestMapping(value="/fileView.do", method=RequestMethod.GET)
    public ModelAndView downloadView(){
        FileCommand fic = theService.selectFile(62);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("fileDownView");
        mav.addObject("file", fic.getUploadfile());
        mav.addObject("filename", fic.getFilename());

        return mav;
    }

    // Image View
    @RequestMapping(value="/pictureView.do", method=RequestMethod.GET)
    public ModelAndView pictureView(){
        FileCommand fic = theService.selectFile(62);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("pictureView");
        mav.addObject("picture", fic.getUploadfile());
        mav.addObject("filename", fic.getFilename());

        return mav;
    }*/

