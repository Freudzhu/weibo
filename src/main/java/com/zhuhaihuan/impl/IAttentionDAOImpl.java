package com.zhuhaihuan.impl;
import org.springframework.stereotype.Repository;
import com.zhuhaihuan.dao.IAttentionDAO;
import com.zhuhaihuan.domain.Attention;
import com.zhuhaihuan.util.BasicSqlSupport;
@Repository
public class IAttentionDAOImpl extends BasicSqlSupport implements IAttentionDAO{
	@Override
	public boolean follow(Attention attention) {
		// TODO Auto-generated method stub
		boolean flag=false; 
	     int count=this.session.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.attention.follow",attention); 
	     if(count>0){ 
	          flag=true; 
	     } 
	     return flag;
	}

	@Override
	public boolean isFollow(Attention attention) {
		// TODO Auto-generated method stub
		 boolean flag=false; 
	     int count=this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.attention.isfollow",attention); 
	     if(count>0){ 
	          flag=true; 
	     } 
	     return flag;
	}

	@Override
	public boolean unFollow(Attention attention) {
		// TODO Auto-generated method stub
		int count=this.session.delete("com.zhuhaihuan.weibo.mybatis.mapper.weibo.attention.unfollow",attention); 
		if(count != 0)
			return true;
		else{
			return false;
		}
	}

	@Override
	public long attentionerCount(String uid) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.attention.attentionerCount",uid); 
	}

	@Override
	public long followerCount(String uid) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.zhuhaihuan.weibo.mybatis.mapper.weibo.attention.followerCount",uid);
	}
}
