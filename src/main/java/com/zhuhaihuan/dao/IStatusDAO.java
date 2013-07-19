package com.zhuhaihuan.dao;
import java.util.List;

import com.zhuhaihuan.domain.Comments;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
public interface IStatusDAO {
	public boolean add(Status status);
	public List<Status> getALLStatus(Page<Status> page,User user);
	public List<Status> getMyStatus(User user);
	public void postComment(Comments comment);
}
