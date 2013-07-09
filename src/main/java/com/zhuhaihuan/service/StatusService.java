package com.zhuhaihuan.service;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.impl.IStatusDAOImpl;
@Service
public class StatusService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IStatusDAOImpl statusDAO;
	public void addStatus(User user,String content){
		Status status =new Status();
		status.setUser(user);
		status.setContent(content);
		status.setCreatetime(new Date());
		status.setUpdatetime(new Date());
		statusDAO.add(status);
	}
	public List<Status> getStatus(User user){
		List<Status> statuss = statusDAO.getALLStatus(user);
		return statuss;

	}
}