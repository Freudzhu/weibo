package com.zhuhaihuan.domain;
import java.util.Date;
public class Message {
	Integer mid;
	Integer id;
	User reminder;
	User publiser;
	String content;
	Date createtime;
	Date updatetime;
	long commmentCount;
	long forwardCount;
	Status forward;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Status getForward() {
		return forward;
	}
	public void setForward(Status forward) {
		this.forward = forward;
	}
	public User getUser() {
		return publiser;
	}
	public void setUser(User user) {
		this.publiser = user;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer id) {
		this.mid = id;
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
