package com.zhuhaihuan.service;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.dao.IUserDAO;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.web.SessionHelper;
@Service
public class UserService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IUserDAO userDao;
	public void addUser(Map<String, String[]> params){
		User user =new User();
		try {
			BeanUtils.populate(user, params);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug(user.toString());
		userDao.add(user);
	}
	public boolean verifyUser(Map<String, String[]> params,HttpSession httpSession){
		User user =new User();
		try {
			BeanUtils.populate(user, params);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User usrValid = userDao.getUser(user);
		if(usrValid != null){
			SessionHelper.doLogin(usrValid, httpSession);
			return true;
		}
		else{
			return false;
		}
	}
	public List<User> searchUser(String username){
		List<User> resultUsers = userDao.searchUser("%" + username + "%");
		return resultUsers;
	}
	public User getCurrentUser(HttpSession session){
		return SessionHelper.getCurrentUser(session);
	}
}
