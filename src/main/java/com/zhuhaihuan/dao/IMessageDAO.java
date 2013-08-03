package com.zhuhaihuan.dao;
import java.util.List;
import com.zhuhaihuan.domain.Message;
import com.zhuhaihuan.domain.Page;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
public interface IMessageDAO {
	public boolean add(Message message);
	public List<Message> getALLMessage(Page<Message> page,User user);
}
