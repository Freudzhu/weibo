package com.zhuhaihuan.service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Attention;
import com.zhuhaihuan.impl.IAttentionDAOImpl;
@Service
public class AttentionService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IAttentionDAOImpl attentionDAO;
	public boolean follow(String uid,String uuid){
		Attention attention =new Attention();
		attention.setUid(uid);
		attention.setUuid(uuid);
		boolean flag = attentionDAO.follow(attention);
		return flag;
	}
	public boolean isFollow(String uid,String uuid){
		Attention attention =new Attention();
		attention.setUid(uid);
		attention.setUuid(uuid);
		boolean flag = attentionDAO.isFollow(attention);
		return flag;
	}
	public boolean unFollow(String uid,String uuid){
		Attention attention =new Attention();
		attention.setUid(uid);
		attention.setUuid(uuid);
		boolean flag = attentionDAO.unFollow(attention);
		return flag;
	}
	public long attentionerCount(String uid){
		return attentionDAO.followerCount(uid);
	}
	public long followerCount(String uid){
		return attentionDAO.attentionerCount(uid);
	}

}
