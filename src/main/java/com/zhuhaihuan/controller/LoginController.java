package com.zhuhaihuan.controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.service.UserService;
@Controller 
public class LoginController {
	@Autowired
	private UserService userService; 
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model){
		return "index";
	}
	@RequestMapping("/login.do")
    public String login(HttpServletRequest request, Model model) {
		Map<String, String[]> params = request.getParameterMap();
		boolean flag = userService.verifyUser(params,request.getSession());
		if(!flag){
			model.addAttribute("errMsg","用户名和密码验证错误");
			return "index";
		}
		
		return "home";
		
	}
}
