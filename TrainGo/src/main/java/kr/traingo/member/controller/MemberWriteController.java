package kr.traingo.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;
  
@Controller
public class MemberWriteController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	//커맨드 객체(자바빈) 초기화
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	@RequestMapping(value="/member/memberWrite.do",method=RequestMethod.GET)
	public String form(){
		return "memberWrite";
	}
	@RequestMapping(value="/member/memberWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
	                     @Valid MemberCommand memberCommand,
	                     BindingResult result){
		
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//유효성 체크
		if(result.hasErrors()){
			return form();
		}
		
		//회원가입
		memberService.insert(memberCommand);
		
		return "redirect:/home.do";
	}
}












