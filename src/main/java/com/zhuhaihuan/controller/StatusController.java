package com.zhuhaihuan.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhuhaihuan.domain.Comments;
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
	
	@RequestMapping(value="/publish",method=RequestMethod.POST)
    public @ResponseBody String publish(HttpServletRequest request, Model model) {
		ModelHelper.init(model, request);
		User user = userService.getCurrentUser(request.getSession());
		String content = request.getParameter("message");
		String forwardId = request.getParameter("fowardid");
		statusService.addStatus(user,content,forwardId);		
		return "success";
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
	@RequestMapping(value="/commment",method=RequestMethod.POST)
	public @ResponseBody String postComment(HttpServletRequest request, Model model) {
		User user = userService.getCurrentUser(request.getSession());
		String statuesId = request.getParameter("statues_id");
		String content = request.getParameter("comment_content");
		statusService.postComments(user, statuesId,content);
		return "success";
	}
	@RequestMapping(value="/commment",method=RequestMethod.GET,produces = "application/json")
	public @ResponseBody List<Comments>  getComment(HttpServletRequest request, Model model) {
		String statuesId = request.getParameter("statuesid");
		List<Comments> comments = statusService.getComments(statuesId);
		return comments;
	}

	

}
