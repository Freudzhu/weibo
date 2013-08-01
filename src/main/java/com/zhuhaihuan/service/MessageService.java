package com.zhuhaihuan.service;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.impl.IMessageDAOImpl;
@Service
public class MessageService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IMessageDAOImpl messageDAO;
	public void addMessage(Status forwardStatus,String uid,String content){
		Message message=new Message();
		User u = new User();
		u.setUid(Integer.parseInt(uid));
		message.setUser(u);
		message.setForward(forwardStatus);
		message.setContent(content);
		message.setUpdatetime(new Date());
		message.setCreatetime(new Date());
		messageDAO.add(message);
	}
}
