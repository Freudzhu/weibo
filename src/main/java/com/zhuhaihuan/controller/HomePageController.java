package com.zhuhaihuan.controller;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.MessageService;
import com.zhuhaihuan.service.UserService;
@Controller 
public class HomePageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	private Logger log =Logger.getLogger(getClass());
	
	@RequestMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
		User user = userService.getCurrentUser(request.getSession());
		log.info(user.toString());
		String content = request.getParameter("message");
		messageService.addMessage(user.getUid(), content);
		return "home";
	}

}
