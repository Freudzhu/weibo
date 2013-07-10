package com.zhuhaihuan.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IUserDAO;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IUserDAOImpl extends BasicSqlSupport implements IUserDAO{
	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		 boolean flag=false; 
	        int count=this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.user.add",user); 
	        if(count>0){ 
	            flag=true; 
	        } 
	        return flag; 
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		User userVali = this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.user.verify",user);
		return userVali;
	}

	@Override
	public List<User> searchUser(String username) {
		// TODO Auto-generated method stub
		List<User> resultUsers = this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.user.searchUser",username);
		return resultUsers;
	}
}
