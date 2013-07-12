package com.zhuhaihuan.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.AttentionService;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService; 
	@Autowired
	private AttentionService attentionService;
	@RequestMapping("/search")
    public String search(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		String query = request.getParameter("query");
		User currentUser = userService.getCurrentUser(request.getSession());
		List<User> resultUser = userService.searchUser(query);
		Map<Object,Boolean> isFollower = new HashMap<Object,Boolean>();
		for(User user:resultUser){
			Boolean flag = attentionService.isFollow(currentUser.getUid().toString(),user.getUid().toString());
			System.out.println(flag);
			isFollower.put(user, flag);
		}	
		model.addAttribute("resultUser",resultUser);
		model.addAttribute("followRelation",isFollower);
		model.addAttribute("query",query);
		return "users";
	}
}
