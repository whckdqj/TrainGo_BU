package kr.traingo.member.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;

@Controller
public class MemberListDetailController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/detailList.do")
	public ModelAndView process (@RequestParam("id")String id){
		if(log.isDebugEnabled()){
			log.debug("id : " + id);
		}
		
		MemberCommand member = memberService.selectMember(id);
		return new ModelAndView("selectDetail","member",member);
		
	}
	
}
