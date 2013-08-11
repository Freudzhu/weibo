package com.zhuhaihuan.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IMessageDAO;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IMessageDAOImpl extends BasicSqlSupport implements IMessageDAO{
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
	@Override
	public List<Message> getALLMessage(Page<Message> page,User user) {
		// TODO Auto-generated method stub
		Map<String, Object> param=new HashMap<String, Object>();  
        param.put("reminderid", user.getUid());  
        param.put("page", page); 
		List<Message> allMessage = this.session.selectList("com.zhuhaihuan.weibo.mybatis.mapper.weibo.message.getAllMessage",param); 
	    page.setResults(allMessage);
	    return allMessage;
	}
	public int getNewMessageCount(String uid){
		return  this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.message.newMessageCount", uid);
	}

}
