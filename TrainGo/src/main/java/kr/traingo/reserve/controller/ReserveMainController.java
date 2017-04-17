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


	//예매 메뉴로 이동
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



	//트레인정보와 시트 정보를 가져와 줌 
	@RequestMapping("trainseat.do")
	@ResponseBody  
	public Map<String,Object> trainseat(@RequestParam(value="startdate") String departdate){
		//지금은 리퀘스트파람만 받지만, 나중에 다른 것을 받게 되니까, 그것까지 다 확인할 수 없는 리퀘스트 파람말고 다른 걸 써보는 것도 생각해봐야할 듯.

		//트레인커맨드도 나중에 바꿔보자
		TrainCommand command=new TrainCommand();

		command.setDepartsta("서울");
		command.setArrivalsta("부산");
		command.setDeparttime(departdate);    	        	

		List<TrainCommand> train=reserveService.getTrainList(command);      	
		Map<String,Object> mapJson =new HashMap<String,Object>();    	
		mapJson.put("list",train);

		return mapJson;	 	}   




	@RequestMapping(value="/selectseat.do")
	public ModelAndView SelectSeat(@RequestParam(value="pageNum",defaultValue="1") String pageNum, @RequestParam("trainnum")String trainnum){

		int rowCount=40;	// 한 차량당 좌석수
		int pageCount=10;	// 한 페이지에 보여지는 최대 차량수 
		int currentPage = Integer.parseInt(pageNum);		
		int count =240; //최대 좌석 수..나중에 메소드로 받아서 처리할 수 있게끔 만들어야 함.

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
		//이거 모델 아트리뷰트로 바꿀 수 있을듯 나중에 바꿔볼 것.    	

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

		//숫자, 스트링 구분해서 들어가는 것 같아서 이렇게 했는데
		//실제로도 구분해서 커맨드가 받는지 확인하고 싶다.


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
					System.out.println("같은 차량이 있어 인덱스 "+z+"번을 포함시키지 않습니다.");

				}else{
					System.out.println(z+"번을 추가합니다");

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
				System.out.println("시트가 있어 인덱스 "+y+"번을 삭제합니다");
				tlist.remove(y);				
			}else{
				System.out.println("시트가 없어"+y+"y번 삭제 안합니다.");
			}

		}




		CSCommand seat2=null;
		//List<CSCommand> tlist3=new ArrayList<CSCommand>();	

		System.out.println("받은"+tlist.size());

		for(int x=0;x<tlist.size();x++){

			seat2=tlist.get(x);   			


			for(int y=1;y<=240;y++){


				seat2.setSeatnum(y);    				


				System.out.println("추가될 시트넘버"+seat2.getSeatnum());
				System.out.println("추가될 시트 트래인넘버"+seat2.getTrainnum());


				//tlist3.add(seat2);

				reserveService.MakeSeat(seat2);


			}

		}

		//위에까지는 잘 돼 아래만 잘되면 됨
		//안되면 이거 문제임

		// reserveService.MakeSeat(tlist3);




		ModelAndView mav=new ModelAndView();

		//그리고 이건 티켓리스트 폼이 아니지.
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

		int rowCount=40;	// 한 차량당 좌석수
		int pageCount=10;	// 한 페이지에 보여지는 최대 차량수 
		int currentPage = Integer.parseInt(pageNum);		
		int count =240; //최대 좌석 수..나중에 메소드로 받아서 처리할 수 있게끔 만들어야 함.

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

