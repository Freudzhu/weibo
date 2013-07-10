package com.zhuhaihuan.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class ModelHelper {
	public static String redirect(String uri) {
	    return "redirect:" + uri;
	}
	public static Model init(Model model, HttpServletRequest request) {
      
    	model.addAttribute("ctxtPath",request.getContextPath());
    	
        model.addAttribute("user", SessionHelper.getCurrentUser(request.getSession()));

        return model;
    }
}
