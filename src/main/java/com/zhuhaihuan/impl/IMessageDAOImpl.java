package com.zhuhaihuan.impl;
import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IMessageDAO;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IMessageDAOImpl  extends BasicSqlSupport implements IMessageDAO{
	@Override
	public boolean add(Message message) {
		// TODO Auto-generated method stub
		 boolean flag=false; 
	     int count=this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.message.add",message); 
	     if(count>0){ 
	          flag=true; 
	     } 
	     return flag;
	}
}
