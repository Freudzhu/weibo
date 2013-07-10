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
}
