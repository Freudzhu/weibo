package com.zhuhaihuan.controller;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.StatusService;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("/status")
public class StatusController {
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;
	
	private Logger log =Logger.getLogger(getClass());
	
	@RequestMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
		User user = userService.getCurrentUser(request.getSession());
		log.info(user.toString());
		String content = request.getParameter("message");
		statusService.addStatus(user,content);
		
		return ModelHelper.redirect("/timeline/" + user.getUid());
	}

}
