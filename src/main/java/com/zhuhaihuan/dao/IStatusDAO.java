package com.zhuhaihuan.dao;
import java.util.List;
import com.zhuhaihuan.domain.Comments;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
public interface IStatusDAO {
	public int add(Status status);
	public List<Status> getALLStatus(Page<Status> page,User user);
	public List<Status> getMyStatus(User user);
	public Status getStatusById(String statusId);
	public void postComment(Comments comment);
	public List<Comments> getCommnets(String statusId);
	public long getCommentsCount(String statusId);
	public void updateForwardCount(String statusId);
}
