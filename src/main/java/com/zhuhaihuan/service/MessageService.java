package com.zhuhaihuan.service;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.impl.IMessageDAOImpl;
@Service
public class MessageService {
	private Logger log =Logger.getLogger(getClass());
	@Autowired
	private IMessageDAOImpl messageDAO;
	public void addMessage(Integer uid,String content){
		Message message =new Message();
		message.setUid(uid);
		message.setContent(content);
		message.setCreatetime(new Date());
		message.setUpdatetime(new Date());
		messageDAO.add(message);
	}

}
