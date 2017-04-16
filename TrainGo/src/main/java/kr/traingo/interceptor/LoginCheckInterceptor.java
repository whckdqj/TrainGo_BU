package kr.traingo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
    
    private Logger log = Logger.getLogger(this.getClass());
    
    /* 
     * Enter the Page Call this Handle for Pre-Handle
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Login Check Pre-Handle
    	if(log.isDebugEnabled()){
            log.debug("**TrainGo[DEBUG] : "+this.toString()+"**");
        }
    	
    	HttpSession session = request.getSession();
    	String lev = (String)session.getAttribute("adminLev");
    	System.out.println("**This Routine is Executed!!(Out)** : "+lev);
    	
    	if(lev==null || lev.equals("")){
    		session.setAttribute("adminLev", "0");
    		System.out.println("**This Routine is Executed!!(If-1)**");
    		response.sendRedirect(request.getContextPath()+"/home.do");
    		return false;
    		
    	}
    	else if(lev.equals("0")){
    		System.out.println("**This Routine is Executed!!(If-2)**");
    		response.sendRedirect(request.getContextPath()+"/home.do");
    		return false;
    	}
    	else if(lev.equals("1")){
    		return true;
    	}
    	else{
            return true;
    	}
    }
}
