package com.zhuhaihuan.dao;
import java.util.List;
import com.zhuhaihuan.domain.Message;
public interface IMessageDAO {
	public boolean add(Message message);

	List<Message> getMessages();
}
