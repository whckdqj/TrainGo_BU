package kr.traingo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.traingo.domain.FileCommand;
import kr.traingo.domain.FmemberCommand;
import kr.traingo.domain.TableAdminCommand;
import kr.traingo.domain.TheCommand;
import kr.traingo.service.TheService;
import kr.traingo.table.util.UtilAjax;
import kr.traingo.table.util.UtilDate;

@Controller
@SessionAttributes("command")
public class MainController {
    private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private TheService theService;
    
    @RequestMapping(value="/insertDB.do", method=RequestMethod.GET)
    public ModelAndView callbackDBForm(){
        TheCommand command = new TheCommand();
        
        return new ModelAndView("insertDB", "command", command);
    }
    
    @RequestMapping(value="/insertDB.do", method=RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute("command")@Valid TheCommand command
                             , BindingResult result
                             , SessionStatus status){
        
        if(log.isDebugEnabled()){
            log.debug("<<TheCommand:>>"+command);
        }
        
        // Validation Check
        if(result.hasErrors()){
            return callbackDBForm();
        }
        
        theService.insert(command);
        status.setComplete();
        
        return new ModelAndView("redirect:/home.do");
    }
    
    @RequestMapping(value="/fileDB.do", method=RequestMethod.GET)
    public ModelAndView callbackFileForm(){
        FileCommand command = new FileCommand();
        return new ModelAndView("fileDB", "command", command);
    }
    
    @RequestMapping(value="/fileDB.do", method=RequestMethod.POST)
    public ModelAndView submitDBForm(@ModelAttribute("command") FileCommand command
                                    , SessionStatus status){
        command.setId("apple");
        if(log.isDebugEnabled()){
            log.debug("<<FileCommand:>>"+command);
        }
        
        theService.insertFile(command);
        status.setComplete();
        return new ModelAndView("redirect:/home.do");
    }
    
    
    @RequestMapping(value="/theView.do", method=RequestMethod.GET)
    public ModelAndView viewDetail(){
        // test seq = 62
        int seq = 62;
        if(log.isDebugEnabled()){
            log.debug("<<Sample Sequence No.>> : "+seq);
        }
        
        FileCommand fic = theService.selectFile(seq);
        
        return new ModelAndView("theView", "files", fic);
    }
    
    // fileDown
    @RequestMapping(value="/fileView.do", method=RequestMethod.GET)
    public ModelAndView downloadView(){
        FileCommand fic = theService.selectFile(62);
        
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("fileDownView");
        mav.addObject("file", fic.getUploadfile());
        mav.addObject("filename", fic.getFilename());
        
        return mav;
    }
    
    // Image View
    @RequestMapping(value="/pictureView.do", method=RequestMethod.GET)
    public ModelAndView pictureView(){
        FileCommand fic = theService.selectFile(62);
        
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("pictureView");
        mav.addObject("picture", fic.getUploadfile());
        mav.addObject("filename", fic.getFilename());
        
        return mav;
    }
    
    // Admin Function
    @RequestMapping(value="/pageAdmin.do", method=RequestMethod.GET)
    public ModelAndView adminAccess(){
        Map<String, TableAdminCommand> adminMap = new Hashtable<String, TableAdminCommand>();
        List<TableAdminCommand> adminList = new ArrayList<TableAdminCommand>();
        TableAdminCommand tmpAdminCommand=null, updateAdminCommand=null;
        UtilDate utilDate = new UtilDate();
        String today = utilDate.getServerTime();
        
        for(int i=0; i<=2; i++){
            tmpAdminCommand = new TableAdminCommand();
            switch (i) {
            case 0:
                tmpAdminCommand.setTableName(UtilAjax.CTYCODE_TABLE);
                break;
            case 1:
                tmpAdminCommand.setTableName(UtilAjax.VHCLE_KND_TABLE);
                break;
            case 2:
                tmpAdminCommand.setTableName(UtilAjax.STTNCODE_TABLE);
                break;
            default:
                break;
            }
            adminList.add(tmpAdminCommand);
        }
        
        
        ModelAndView mav = new ModelAndView();
        
        for(TableAdminCommand command:adminList){
            // get
            tmpAdminCommand = theService.selectHistory(command.getTableName());
            
            adminMap.put(tmpAdminCommand.getTableName(), tmpAdminCommand);
            
            updateAdminCommand = new TableAdminCommand();
            
            // updateBean Build
            updateAdminCommand.setTableName(tmpAdminCommand.getTableName());
            updateAdminCommand.setCreated(tmpAdminCommand.getCreated());
            updateAdminCommand.setModified("");
            updateAdminCommand.setLast_called(today);
            updateAdminCommand.setAccess_object(this.toString());
            
            // Update
            theService.updateHistory(updateAdminCommand);
            
        }
        
        
        mav.setViewName("DBAController");
        mav.addObject("adminInfos", adminMap);
        
        return mav;
    }
    
 // Admin Function
    @RequestMapping(value="/pageAdmin.do", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> adminUpdate(@RequestParam("table_update")String tableUpdate){
        TableAdminCommand adminCommand = new TableAdminCommand();
        
        adminCommand = theService.selectHistory(tableUpdate);
        
        
        // if not null, update last called
        System.out.println("[[Admin Complete]]"+adminCommand.getCreated()+" / "+adminCommand.getTableName());
        
        Map<String, Object> dbaTable = new HashMap<String, Object>();
        
        dbaTable.put("adminInfo", adminCommand);
        
        return dbaTable;
    }
    
    @RequestMapping(value="/sessionControl.do", method=RequestMethod.GET)
    public String setAdmin(HttpSession session){
        FmemberCommand fmemberCommand = new FmemberCommand();
        
        if(log.isDebugEnabled()){
            log.debug("[[SessionControl Routine]]");
        }
        
        fmemberCommand = theService.selectAdminLevel("admin");
        System.out.println("** Current Level(DB) : "+String.valueOf(fmemberCommand.getLev())+" **");
        
        String currLev = (String)session.getAttribute("adminLev");
        System.out.println("** Current Level(Session) : "+currLev+" **");
        
        if(currLev==null || currLev.equals("")){
            System.out.println("** Level Miss : Set to 0 **");
            session.setAttribute("adminLev", "0");
            return "redirect:/home.do";
        }
        else if(currLev.equals("0")){
            session.setAttribute("adminLev", String.valueOf(fmemberCommand.getLev()));
            System.out.println("** Reverse Session 0 to 1 **");
            return "redirect:/home.do";
        }
        else if(currLev.equals("1")){
            session.setAttribute("adminLev", "0");
            System.out.println("** Reverse Session 1 to 0 **");
            return "redirect:/home.do";
        }
        else{
            System.out.println("** Do Nothing **");
            return "redirect:/home.do";
        }
    }
}
