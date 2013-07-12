package com.zhuhaihuan.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IStatusDAO;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IStatusDAOImpl  extends BasicSqlSupport implements IStatusDAO{
	@Override
	public boolean add(Status status) {
		// TODO Auto-generated method stub
		 boolean flag=false; 
	     int count=this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.add",status); 
	     if(count>0){ 
	          flag=true; 
	     } 
	     return flag;
	}
	@Override
	public List<Status> getALLStatus(User user) {
		// TODO Auto-generated method stub
		return this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.findStatusesByUser",user); 

	}
	@Override
	public List<Status> getMyStatus(User user) {
		// TODO Auto-generated method stub
		return this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.findMyStatus",user); 
	}
}
