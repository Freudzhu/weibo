package com.zhuhaihuan.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.service.StatusService;
import com.zhuhaihuan.service.UserService;
@Controller
@RequestMapping("/timeline")
public class TimeLineController {
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;
	@RequestMapping("/{uid}")
    public String showTimeline(HttpServletRequest request, Model model) {
		User user = userService.getCurrentUser(request.getSession());
		List<Status> statuses = statusService.getStatus(user);
		model.addAttribute("statuses", statuses);
		return "home";
	}

}
