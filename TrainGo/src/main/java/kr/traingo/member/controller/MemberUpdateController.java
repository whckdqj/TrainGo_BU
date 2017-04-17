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
public class MemberUpdateController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/memberModify.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model){
		String id = (String)session.getAttribute("userId");
		
		MemberCommand member = memberService.selectMember(id);
		model.addAttribute("command", member);
		
		return "memberModify";
	}
	@RequestMapping(value="/member/memberModify.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
			             @Valid MemberCommand memberCommand,
			             BindingResult result,
			             SessionStatus status){
		
		if(log.isDebugEnabled()){
			log.debug("memberCommand : " + memberCommand);
		}
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "memberModify";
		}
		
		//ȸ������ ����
		memberService.update(memberCommand);
		//session�� ����� model�� �����ϴ� �̺�Ʈ �߻�
		status.setComplete();
		
		return "redirect:/member/detail.do";
	}
	
	
}
