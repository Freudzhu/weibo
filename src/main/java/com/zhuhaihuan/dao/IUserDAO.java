package com.zhuhaihuan.dao;
import java.util.List;
import com.zhuhaihuan.domain.User;
public interface IUserDAO {
	public boolean add(User user);
	public User getUser(User user);
	public List<User> searchUser(String username);
	public String findUidByUsername(String username);
}
