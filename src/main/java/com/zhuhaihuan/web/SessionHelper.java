package com.zhuhaihuan.web;

import javax.servlet.http.HttpSession;
import com.zhuhaihuan.domain.User;
public class SessionHelper {
	
	    public static final String LOCKER_KEY_USER = "user.locker.user";
	    
	    public static void doLogin( User user, HttpSession session) {
	    	if(session != null) {
	    		session.setAttribute(LOCKER_KEY_USER, user);
	    	}
	    }
	    
	    public static boolean isLogin(HttpSession session) {
	    	if(session == null) {
	    		return false;
	    	}
	    	
	        return session.getAttribute(LOCKER_KEY_USER) != null;
	    }
	    
	    public static User getCurrentUser(HttpSession session){
	    	if(session ==null) {
	    		return null;
	    	}
	        Object admin = session.getAttribute(LOCKER_KEY_USER);
	        
	        if(admin == null) {
	            return null;
	        }
	            
	        return (User)admin;
	    }
	    
	    public static void doLogout(HttpSession session) {
	    	if(session != null) {
	    		session.removeAttribute(LOCKER_KEY_USER);
	    	}
	    }
	}

