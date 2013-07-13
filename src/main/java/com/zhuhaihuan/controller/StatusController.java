package com.zhuhaihuan.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.AttentionService;
import com.zhuhaihuan.service.StatusService;
import com.zhuhaihuan.service.UserService;
import com.zhuhaihuan.web.ModelHelper;
@Controller 
@RequestMapping("{uid}/statuses")
public class StatusController {
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;
	@Autowired 
	AttentionService attentionService;
	
	
	private Logger log =Logger.getLogger(getClass());
	
	@RequestMapping("/publish")
    public String publish(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		User user = userService.getCurrentUser(request.getSession());
		log.info(user.toString());
		String content = request.getParameter("message");
		statusService.addStatus(user,content);
		
		return ModelHelper.redirect("timeline");
	}
	@RequestMapping("/timeline")
    public String showTimeline(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		User user = userService.getCurrentUser(request.getSession());
		String pageNo = request.getParameter("pageNo");
		Page<Status> page = new Page<Status>();
		page.setPageNo(Integer.parseInt(pageNo));
		page.setPageSize(6);
		List<Status> statuses = statusService.getStatus(page,user);
		List<Status> myStatus = statusService.getMyStatus(user);
		long followerCount = attentionService.followerCount(user.getUid().toString());
		long attentionCount = attentionService.attentionerCount(user.getUid().toString());
		model.addAttribute("statusCount", myStatus.size());
		model.addAttribute("followerCount",followerCount);
		model.addAttribute("attentionCount",attentionCount);
		model.addAttribute("statuses", statuses);
		model.addAttribute("user", user);
		model.addAttribute("pagedStatus", page);
		return "home";
	}

}
