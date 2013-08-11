package com.zhuhaihuan.service;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
import com.zhuhaihuan.impl.IMessageDAOImpl;
@Service
public class MessageService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IMessageDAOImpl messageDAO;
	public void addMessage(Status forwardStatus,String uid,String reminderId,String content,int statuesId){
		Message message=new Message();
		User u = new User();
		u.setUid(Integer.parseInt(uid));
		message.setUser(u);
		message.setId(statuesId);
		User reminder = new User();
		reminder.setUid(Integer.parseInt(reminderId));
		message.setForward(forwardStatus);
		message.setContent(content);
		message.setUpdatetime(new Date());
		message.setCreatetime(new Date());
		message.setReminder(reminder);
		messageDAO.add(message);
	}
	public List<Message> getAllMessage(Page<Message> page,User user){
		return messageDAO.getALLMessage(page, user);	
	}
	public int getNewMessageCount(String uid){
		return messageDAO.getNewMessageCount(uid);
	}
}
