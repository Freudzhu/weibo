package com.zhuhaihuan.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.MessageService;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("{uid}/message")
public class MessageController {
	@Autowired 
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getMessage(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		User user = userService.getCurrentUser(request.getSession());
		String pageNo = request.getParameter("pageNo");
		Page<Message> page = new Page<Message>();
		page.setPageNo(Integer.parseInt(pageNo));
		page.setPageSize(6);
		List<Message> messages =  messageService.getAllMessage(page, user);
		model.addAttribute("messages",messages);
		model.addAttribute("user", user);
		return "message";
	}
	@RequestMapping(value="/newMessageCount",method=RequestMethod.GET)
	public @ResponseBody String newMessageCount(HttpServletRequest request, Model model,@PathVariable String uid) {
		int count = messageService.getNewMessageCount(uid);
		return String.valueOf(count);
		
	}
}
