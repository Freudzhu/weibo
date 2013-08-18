package com.zhuhaihuan.test;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhuhaihuan.domain.Status;
import com.zhuhaihuan.domain.User;

public class PrepareStatusData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/com/zhuhaihuan/weibo/config/ApplicationContext.xml");
		SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
		
			
			for(int i=0;i<10000;i++){
				
				Status status =new Status();
				User user =new User();
				if(i % 2 == 0 ){
					user.setUid(1);
				}else{
					user.setUid(2);
				}
				
				status.setUser(user);
				status.setContent("test performace " + i);
				status.setCreatetime(new Date());
				status.setUpdatetime(new Date());
				sqlSession.insert("com.zhuhaihuan.weibo.mybatis.mapper.weibo.status.add",status); 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		System.out.println("insert finish!");
		

	}

}
