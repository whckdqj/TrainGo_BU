package kr.traingo.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
public class MemberLoginController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	//커맨드 객체(자바빈) 초기화
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	@RequestMapping(value="/member/memberLogin.do",method=RequestMethod.GET)
	public String form(){
		return "memberLogin";
	}
	@RequestMapping(value="/member/memberLogin.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
	                     @Valid MemberCommand memberCommand,
	                     BindingResult result,
	                     HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//id 필드만 체크, pattern에서 오류를 위해서 passwd를 주석처리함.
		if(result.hasFieldErrors("id")/* ||
				result.hasFieldErrors("passwd")*/){
			return form();
		}
		
		//로그인 체크(id 또는 비밀번호 일치 여부 체크)
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			boolean check = false;
			
			if(member!=null){
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(memberCommand.getPasswd());
			}
			if(check){
				//인증 성공, 로그인 처리
				session.setAttribute("userId", memberCommand.getId());
				//로그인시 lev값 구분을 위해서 추가함.
				session.setAttribute("userLev", member.getLev());
				
				return "redirect:/home.do";
			}else{
				//인증실패
				throw new Exception();
			}
		}catch(Exception e){
			//에러 메시지 처리
			result.reject("invalidIdOrPassword");
			//인증실패로 폼 호출
			return form();
		}
	}
	//로그 아웃
	@RequestMapping("/member/logout.do")
	public String process(HttpSession session){
		//로그아웃
		session.invalidate();
		
		return "redirect:/home.do";
	}
}
