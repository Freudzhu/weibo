package com.zhuhaihuan.domain;
import java.util.Date;
public class Status {
	Integer id;
	User user;
	String content;
	Date createtime;
	Date updatetime;
	long commmentCount;
	Integer forwardid;
	Status forward;
	long forwardCount;
	public Integer getForwardid() {
		return forwardid;
	}
	public void setForwardid(Integer forwardid) {
		this.forwardid = forwardid;
	}
	
	public Status getForward() {
		return forward;
	}
	public void setForward(Status forward) {
		this.forward = forward;
	}
	public long getCommmentCount() {
		return commmentCount;
	}
	public void setCommmentCount(long commmentCount) {
		this.commmentCount = commmentCount;
	}
	public long getForwardCount() {
		return forwardCount;
	}
	public void setForwardCount(long forwardCount) {
		this.forwardCount = forwardCount;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
