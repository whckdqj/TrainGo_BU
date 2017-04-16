package kr.traingo.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.member.domain.MemberCommand;
import kr.traingo.member.service.MemberService;
import kr.traingo.util.PagingUtil;

@Controller
public class MemberListSelectController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/list.do")
	public ModelAndView process(@RequestParam (value="pageNum",defaultValue="1")int currentPage){
		int count = memberService.getMemberCount();
		
		if(log.isDebugEnabled()){
			log.debug("pageNum : " + currentPage);
			log.debug("count : " + count);
		}
		
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"list.do");
		
		List<MemberCommand> list = null;
		if(count > 0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			list = memberService.getMemberList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
}
