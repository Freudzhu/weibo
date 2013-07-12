package com.zhuhaihuan.dao;
import com.zhuhaihuan.domain.Attention;
public interface IAttentionDAO{
	public boolean follow(Attention attention);
	public boolean isFollow(Attention attention);
	public boolean unFollow(Attention attention);
	public long attentionerCount(String uid);
	public long followerCount(String uid);
}
