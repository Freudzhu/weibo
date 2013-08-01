package com.zhuhaihuan.domain;
import java.util.Date;
public class Message {
	Integer mid;
	User user;
	String content;
	Date createtime;
	Date updatetime;
	Status forward;
	public Status getForward() {
		return forward;
	}
	public void setForward(Status forward) {
		this.forward = forward;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
