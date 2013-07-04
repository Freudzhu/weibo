package com.zhuhaihuan.dao;
import com.zhuhaihuan.domain.User;
public interface IUserDAO {
	public boolean add(User user);
	public User getUser(User user);
}
