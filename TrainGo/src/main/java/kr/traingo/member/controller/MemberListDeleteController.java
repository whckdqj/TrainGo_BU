package kr.traingo.member.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.traingo.member.service.MemberService;

@Controller
public class MemberListDeleteController {
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/deleteList.do")
	public String form(){
		return "deleteForm";
	}
	
	@RequestMapping("/member/deleteListPro.do")
	public String process(@RequestParam("id")String id){
		
		memberService.deleteMember(id);
		
		return "redirect:/main/main.do";
	}
}
