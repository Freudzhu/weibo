package com.zhuhaihuan.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IStatusDAO;
import com.zhuhaihuan.domain.Comments;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IStatusDAOImpl  extends BasicSqlSupport implements IStatusDAO{
	@Override
	public int add(Status status) {
		// TODO Auto-generated method stub
	     this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.add",status); 
	     return status.getId();
	}
	@Override
	public List<Status> getALLStatus(Page<Status> page,User user) {
		// TODO Auto-generated method stub
		Map<String, Object> param=new HashMap<String, Object>();  
        param.put("uid", user.getUid());  
        param.put("page", page);  
        List<Status> allStatus = this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.findStatusesByUser",param); 
        page.setResults(allStatus);
        return allStatus;

	}
	@Override
	public List<Status> getMyStatus(User user) {
		// TODO Auto-generated method stub
		return this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.findMyStatus",user); 
	}
	@Override
	public void postComment(Comments comment) {
		// TODO Auto-generated method stub
		this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.addComment",comment);
		
	}
	@Override
	public List<Comments> getCommnets(String statuesId) {
		List<Comments> comments = this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.getComments",statuesId);
		return comments;
	}
	@Override
	public long getCommentsCount(String statusId) {
		// TODO Auto-generated method stub
		long commentsCount  = this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.getCommentsCount",statusId);
		return commentsCount;
	}
	@Override
	public void updateForwardCount(String statusId) {
		// TODO Auto-generated method stub
		this.session.update("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.updateForwardCount",statusId);
	}
	@Override
	public Status getStatusById(String statusId) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.findStatusesById",statusId);
	}
}
