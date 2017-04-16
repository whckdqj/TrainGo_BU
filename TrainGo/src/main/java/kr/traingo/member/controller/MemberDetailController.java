package kr.traingo.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;

@Controller
public class MemberDetailController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/memberDetail.do")
	public ModelAndView process(HttpSession session){
		String id = (String)session.getAttribute("userId");
		int lev = (Integer)session.getAttribute("userLev");
		//System.out.println((Integer)session.getAttribute("userLev"));
		
		MemberCommand member = memberService.selectMember(id);
		
		return new ModelAndView("memberView","member",member);
		
	}
	
}
