package com.zhuhaihuan.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService; 
	@RequestMapping("/search")
    public String search(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		String query = request.getParameter("query");
		if(StringUtils.isEmpty(query)){
			query = "";
		}
		List<User> resultUser = userService.searchUser(query);
		model.addAttribute("resultUser",resultUser);
		return "users";
	}
}
