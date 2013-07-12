package com.zhuhaihuan.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zhuhaihuan.service.AttentionService;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("{uid}/attentions")
public class AttentionController {
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private UserService userService;
	@RequestMapping(value="/follow",method=RequestMethod.GET)
    public String follow(HttpServletRequest request, Model model) {
		String uuid = request.getParameter("uuid");
		String query = request.getParameter("query");
		String uid = userService.getCurrentUser(request.getSession()).getUid().toString();
		attentionService.follow(userService.getCurrentUser(request.getSession()).getUid().toString(), uuid);
		return ModelHelper.redirect("/users/search?query="+query);
	}
	@RequestMapping(value="/unfollow",method=RequestMethod.GET)
	public String unfollow(HttpServletRequest request, Model model) {
		String uuid = request.getParameter("uuid");
		String query = request.getParameter("query");
		String uid = userService.getCurrentUser(request.getSession()).getUid().toString();
		attentionService.unFollow(userService.getCurrentUser(request.getSession()).getUid().toString(), uuid);
		return ModelHelper.redirect("/users/search?query="+query);
	}
}
