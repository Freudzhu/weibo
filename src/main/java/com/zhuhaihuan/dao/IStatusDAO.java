package com.zhuhaihuan.dao;
import java.util.List;
import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;
public interface IStatusDAO {
	public boolean add(Status status);
	List<Status> getALLStatus(User user);
	List<Status> getMyStatus(User user);
}
