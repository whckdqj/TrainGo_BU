package kr.traingo.table.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.traingo.table.domain.CtyCodeListCommand;
import kr.traingo.table.service.CtyCodeService;
import kr.traingo.table.util.UtilAjax;

@Controller
public class CtyCodeController {
    private static final String CTY_CODE="CTYCODELIST";
    private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private CtyCodeService ctyCodeService;
    
    @RequestMapping(value="/table/getCtyCodeAdmin.do", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateCode(){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        // Using UtilAjax
        UtilAjax utilAjax = new UtilAjax();
        
        // Data Check
        List<CtyCodeListCommand> oldCtyCodeList = new ArrayList<CtyCodeListCommand>();
        List<CtyCodeListCommand> newCtyCodeList = new ArrayList<CtyCodeListCommand>();
        List<CtyCodeListCommand> readCtyCodeList = new ArrayList<CtyCodeListCommand>();
        // ----- Initialize
        
        // ----- CtyCode Loading Sequence
        oldCtyCodeList = ctyCodeService.selectCtyCodeListAll();
        readCtyCodeList = utilAjax.getCityCodeFromServer();
        
        // Filter new Column
        for(CtyCodeListCommand ctyCommand1:oldCtyCodeList){
            for(CtyCodeListCommand ctyCommand2:readCtyCodeList){
                if(ctyCommand1.getCityCode().equals(ctyCommand2.getCityCode()) && ctyCommand1.getCityName().equals(ctyCommand2.getCityName())){
                    readCtyCodeList.remove(ctyCommand2);
                    break;
                }
            }
        }
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[OldCtyCode]] : " + oldCtyCodeList);
            log.debug("[[ReadCtyCode]] : " + readCtyCodeList);
            log.debug("[[NewCtyCode]] : " + newCtyCodeList);
            log.debug("[[New isEmpty?]] : " + newCtyCodeList.isEmpty());
        }
        // ----- CtyCode Loading Sequence
        
        // ----- Update City Code Sequence
        if(readCtyCodeList.isEmpty()){
            newCtyCodeList = oldCtyCodeList;
        }
        else{
            // Change Apply
            for(CtyCodeListCommand ctyCommand:readCtyCodeList){
                ctyCodeService.insertCtyCodeList(ctyCommand);
            }
            newCtyCodeList = ctyCodeService.selectCtyCodeListAll();
        }
        // ----- Update City Code Sequence
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[=== After Work ===]]");
            log.debug("[[OldCtyCode]] : " + oldCtyCodeList);
            log.debug("[[NewCtyCode]] : " + newCtyCodeList);
        }
        
        // ----- Making Return Json
        jsonMap.put("list", newCtyCodeList);
        // ----- Making Return Json
        
        return jsonMap;
    }
    
    @RequestMapping(value="/table/getCtyCode.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCode(){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        
        // Data Check
        List<CtyCodeListCommand> CtyCodeList = new ArrayList<CtyCodeListCommand>();
        // ----- Initialize
        
        // ----- CtyCode Loading Sequence
        CtyCodeList = ctyCodeService.selectCtyCodeListAll();
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[ReadCtyCode]] : " + CtyCodeList);
        }
        // ----- CtyCode Loading Sequence
        
        // ----- Making Return Json
        jsonMap.put("list", CtyCodeList);
        // ----- Making Return Json
        
        if(log.isDebugEnabled()){
            log.debug("[[AjaxReturn]] : "+jsonMap);
        }
        
        return jsonMap;
    }
}
