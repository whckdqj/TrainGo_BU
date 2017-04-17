package kr.traingo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;

@Controller
public class MemeberConfirmIdAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("id")String id){
		
		
		if(log.isDebugEnabled()){
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		try{
			MemberCommand member = memberService.selectMember(id);
			
			//������� �������� ���� ����� ��������� �������� ���� �۵���.
			//MemberCommand member = new MemberCommand();
			//member = memberService.selectMember(id);
			//System.out.println(member);//null ����.
			
			if(member != null){
				//���̵� �ߺ�
				map.put("result", "idDuplicated");
			}else{
				//���̵� ���ߺ�
				map.put("result", "idNotFound");
			}
		}catch(Exception e){
			log.error(e);
			map.put("result", "failure");
		}
		return map;
	}
}
