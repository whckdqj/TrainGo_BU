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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.traingo.table.domain.ResultTimeTableCommand;
import kr.traingo.table.service.CtyCodeService;
import kr.traingo.table.service.TrainSttnListService;
import kr.traingo.table.util.UtilAjax;

@Controller
public class ResultTimeTableController {
    private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private TrainSttnListService trainSttnListService;
    @Resource
    private CtyCodeService ctyCodeService;
    
    // Not Allowed "GET" Method
    
    @RequestMapping(value="/table/getStationInfoTableAdmin.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCode(){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        List<String> ctyCodes = null;
        // Using UtilAjax
        UtilAjax utilAjax = new UtilAjax();
        
        // Data Check
        List<ResultTimeTableCommand> oldTableList = new ArrayList<ResultTimeTableCommand>();
        List<ResultTimeTableCommand> newTableList = new ArrayList<ResultTimeTableCommand>();
        List<ResultTimeTableCommand> readTableList = new ArrayList<ResultTimeTableCommand>();
        // ----- Initialize
        
        // ----- Station Code Loading Sequence
        /*oldTrainSttnList = trainSttnListService.selectSttnCodeListAll();
        ctyCodes = ctyCodeService.selectCtyCodeOnly();
        readTrainSttnList = utilAjax.getSttnCodeFromServer(ctyCodes);
        
        // Filter new Column
        for(TrainSttnListCommand ctyCommand1:oldTrainSttnList){
            for(TrainSttnListCommand ctyCommand2:readTrainSttnList){
                if(ctyCommand1.getNodeName().equals(ctyCommand2.getNodeName()) 
                   && ctyCommand1.getNodeId().equals(ctyCommand2.getNodeId())
                   && ctyCommand1.getCityCode().equals(ctyCommand2.getCityCode())){
                    readTrainSttnList.remove(ctyCommand2);
                    break;
                }
                else{
                    newTrainSttnList.add(ctyCommand2);
                }
            }
        }
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[OldVhcleKnd]] : " + oldTrainSttnList);
            log.debug("[[ReadVhcleKnd]] : " + readTrainSttnList);
            log.debug("[[NewVhcleKnd]] : " + newTrainSttnList);
            log.debug("[[New isEmpty?]] : " + newTrainSttnList.isEmpty());
        }
        // ----- CtyCode Loading Sequence
        
        // ----- Update City Code Sequence
        if(newTrainSttnList.isEmpty()){
            newTrainSttnList = oldTrainSttnList;
        }
        else{
            // Change Apply
            for(TrainSttnListCommand sttnCommand:newTrainSttnList){
                trainSttnListService.insertSttnCodeList(sttnCommand);
            }
        }
        // ----- Update City Code Sequence
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[=== After Work ===]]");
            log.debug("[[OldCtyCode]] : " + oldTrainSttnList);
            log.debug("[[NewCtyCode]] : " + newTrainSttnList);
        }*/
        
        // ----- Making Return Json
        jsonMap.put("list", newTableList);
        // ----- Making Return Json
        
        return jsonMap;
    }
    
    @RequestMapping(value="/table/getStationInfoTable.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCode(@RequestParam("dep_citycode")String depCtyCode
                                       , @RequestParam("depplaceid")String depId
                                       , @RequestParam("arr_citycode")String arrCtyCode
                                       , @RequestParam("arrplaceid")String arrId
                                       , @RequestParam("depplandtime")String depTime
                                       , @RequestParam("vehicle")String[] vhcleKnd
                                       ){
        // ----- Initialize
        Map<String, Object> jsonMap = new Hashtable<String, Object>();
        UtilAjax utilAjax = new UtilAjax();
        
        // Data Check
        List<ResultTimeTableCommand> trainTable = new ArrayList<ResultTimeTableCommand>();
        // ----- Initialize
        
        // ----- Time Table Loading Sequence
        trainTable = utilAjax.getTimeTableFromServer(depId, arrId, depTime, vhcleKnd);
        
        // For Debug
        if(log.isDebugEnabled()){
            log.debug("[[FullTimeTable]] : " + trainTable);
        }
        // ----- Time Table Loading Sequence
        
        // ----- Making Return Json
        jsonMap.put("list", trainTable);
        // ----- Making Return Json
        
        if(log.isDebugEnabled()){
            log.debug("[[AjaxReturn]] : "+jsonMap);
        }
        
        return jsonMap;
    }
}
