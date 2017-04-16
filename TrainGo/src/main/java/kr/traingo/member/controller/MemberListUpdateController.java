package kr.traingo.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;

@Controller
@SessionAttributes("memberCommand")
public class MemberListUpdateController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	MemberService memberService;
	
	@RequestMapping(value="/member/updateList.do",method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("id")String id){
		
		System.out.println(id + "*******"); //아이디를 받아오는지 체크
		
		MemberCommand memberCommand = memberService.getMember(id);
		
		return new ModelAndView("updateForm","memberCommand",memberCommand);
	}
	@RequestMapping(value="/member/updateList.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, 
						 BindingResult result, 
						 SessionStatus status){
		if(log.isDebugEnabled()){
			log.debug("memberCommand : " + memberCommand);
		}
		
		//유효성 체크
		if(result.hasErrors()){
			return "updateForm";
		}
		
		memberService.updateMember(memberCommand);
		//session에 저장된 model을 삭제하는 이벤트 발생
		status.setComplete();
		
		return "redirect:/home.do";
	}
}
