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

import kr.traingo.table.domain.VhcleKndListCommand;
import kr.traingo.table.service.VhcleKndService;
import kr.traingo.table.util.UtilAjax;

@Controller
public class VhcleKndController {
    private static final String VHCLE_CODE="VHCLEKNDLIST";
    private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private VhcleKndService vhcleKndService;
    
    @RequestMapping(value="/table/getVhcleKndAdmin.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCode(){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        // Using UtilAjax
        UtilAjax utilAjax = new UtilAjax();
        
        // Data Check
        List<VhcleKndListCommand> oldVhcleKndList = new ArrayList<VhcleKndListCommand>();
        List<VhcleKndListCommand> newVhcleKndList = new ArrayList<VhcleKndListCommand>();
        List<VhcleKndListCommand> readVhcleKndList = new ArrayList<VhcleKndListCommand>();
        // ----- Initialize
        
        // ----- CtyCode Loading Sequence
        oldVhcleKndList = vhcleKndService.selectVhcleKndListAll();
        readVhcleKndList = utilAjax.getVhcleCodeFromServer();
        
        // Filter new Column
        for(VhcleKndListCommand ctyCommand1:oldVhcleKndList){
            for(VhcleKndListCommand ctyCommand2:readVhcleKndList){
                if(ctyCommand1.getVehicleKndId().equals(ctyCommand2.getVehicleKndId()) && ctyCommand1.getVehicleKndNm().equals(ctyCommand2.getVehicleKndNm())){
                    readVhcleKndList.remove(ctyCommand2);
                    break;
                }
                else{
                    newVhcleKndList.add(ctyCommand2);
                }
            }
        }
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[OldVhcleKnd]] : " + oldVhcleKndList);
            log.debug("[[ReadVhcleKnd]] : " + readVhcleKndList);
            log.debug("[[NewVhcleKnd]] : " + newVhcleKndList);
            log.debug("[[New isEmpty?]] : " + newVhcleKndList.isEmpty());
        }
        // ----- CtyCode Loading Sequence
        
        // ----- Update City Code Sequence
        if(newVhcleKndList.isEmpty()){
            newVhcleKndList = oldVhcleKndList;
        }
        else{
            // Change Apply
            for(VhcleKndListCommand vhcleCommand:newVhcleKndList){
                vhcleKndService.insertVhcleKndList(vhcleCommand);;
            }
        }
        // ----- Update City Code Sequence
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[=== After Work ===]]");
            log.debug("[[OldCtyCode]] : " + oldVhcleKndList);
            log.debug("[[NewCtyCode]] : " + newVhcleKndList);
        }
        
        // ----- Making Return Json
        jsonMap.put("list", newVhcleKndList);
        // ----- Making Return Json
        
        return jsonMap;
    }
    
    @RequestMapping(value="/table/getVhcleKnd.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCode(){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        
        // Data Check
        List<VhcleKndListCommand> vhcleKndList = new ArrayList<VhcleKndListCommand>();
        // ----- Initialize
        
        // ----- CtyCode Loading Sequence
        vhcleKndList = vhcleKndService.selectVhcleKndListAll();
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[ReadVhcleKnd]] : " + vhcleKndList);
        }
        // ----- CtyCode Loading Sequence
        
        // ----- Making Return Json
        jsonMap.put("list", vhcleKndList);
        // ----- Making Return Json
        
        if(log.isDebugEnabled()){
            log.debug("[[AjaxReturn]] : "+jsonMap);
        }
        
        return jsonMap;
    }
}
