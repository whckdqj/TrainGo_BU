package kr.traingo.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;

@Controller
@SessionAttributes("command")
public class MemberDeleteController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/memberDelete.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model){
		String id = (String)session.getAttribute("userId");
		
		MemberCommand member = new MemberCommand();
		member.setId(id);
		
		model.addAttribute("command", member);
		
		return "memberDelete";
	}
	@RequestMapping(value="/member/memberDelete.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 @Valid MemberCommand memberCommand,
						 BindingResult result,
						 SessionStatus status,
						 HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//passwd 필드만 에러체크
		if(result.hasFieldErrors("passwd")){
			return "memberDelete";
		}
		
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			boolean check = false;
			if(member!=null){
				//비밀번호 체크
				check = member.isCheckedPasswd(memberCommand.getPasswd());
			}
			if(check){
				//인증성공, 회원정보 삭제
				memberService.delete(memberCommand.getId());
				status.setComplete();
				//로그아웃 처리
				session.invalidate();
				return "redirect:/main/main.do";
			}else{
				//인증 실패
				throw new Exception();
			}
		}catch(Exception e){
			//에러 메시지 처리
			result.rejectValue("passwd", "invalidPassword");
			return "memberDelete";
		}
		
	}
}
