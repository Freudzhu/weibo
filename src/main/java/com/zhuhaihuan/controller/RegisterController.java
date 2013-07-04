package com.zhuhaihuan.controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.service.UserService;
@Controller 
@RequestMapping("/register.do")
public class RegisterController {
	@Autowired
	private UserService userService; 
	@RequestMapping
    public String regster(HttpServletRequest request, Model model) {
		Map<String, String[]> params = request.getParameterMap();
		userService.addUser(params);
		return "regster";
		
	}
}
