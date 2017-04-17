package kr.traingo.table.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.table.service.CtyCodeService;

@Controller
public class TableController {
    private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    CtyCodeService ctyCodeService;
    
    @RequestMapping(value="/table/callbackTable.do", method=RequestMethod.GET)
    public ModelAndView viewTable(){
        if(log.isDebugEnabled()){
            log.debug("[[CallbackTrain]] : Main-Page Routine Starts");
        }
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewTable");
        // Departure / Arrival Station Info Merge
        
        return mav;
    }
}
