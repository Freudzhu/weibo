package com.zhuhaihuan.service;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Comments;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.impl.IStatusDAOImpl;
@Service
public class StatusService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IStatusDAOImpl statusDAO;
	public int addStatus(User user,String content,String forwardId){
		Status status =new Status();
		status.setUser(user);
		status.setContent(content);
		status.setCreatetime(new Date());
		status.setUpdatetime(new Date());
		if(!StringUtils.isEmpty(forwardId)){
			status.setForwardid(Integer.parseInt(forwardId));
			statusDAO.updateForwardCount(forwardId);
		}
			
		return  statusDAO.add(status);
		
	}
	public List<Status> getStatus(Page<Status> page,User user){
		List<Status> statuses = statusDAO.getALLStatus(page,user);
		for(Status s : statuses){
			s.setCommmentCount(statusDAO.getCommentsCount(s.getId().toString()));
		}
		return statuses;

	}
	public List<Status> getMyStatus(User user){
		List<Status> statuss = statusDAO.getMyStatus(user);
		return statuss;

	}
	public void postComments(User user,String statuesId,String content){
		Comments comment= new Comments();
		comment.setUser(user);
		comment.setStatusId(statuesId);
		comment.setContent(content);
		statusDAO.postComment(comment);
	}
	public List<Comments> getComments(String statuesId){
		return statusDAO.getCommnets(statuesId);
	}
	public long getCommnetsCount(String statuesId){
		return statusDAO.getCommentsCount(statuesId);
	}
	public Status getStatusById(String statusId){
		return statusDAO.getStatusById(statusId);
	}
}
