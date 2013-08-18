package com.zhuhaihuan.util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
public class PerformanceMonitor {
		private Logger logger =Logger.getLogger(getClass());
	     public Object performaceTest(ProceedingJoinPoint pjp) throws Throwable {
	    	 StopWatch clock = new StopWatch();
	         clock.start(); //��ʱ��ʼ
	         Object result = pjp.proceed();
	         clock.stop();  //��ʱ����
	         //�����������ͣ�ת���ɼ�����
	         Object[] params = pjp.getArgs();
	         String[] simpleParams = new String[params.length];
	         for (int i = 0; i < params.length; i++) {
	             simpleParams[i] = params[i].toString();
	         }
	         logger.debug("Takes:" + clock.getTime() + " ms ["
	                 + pjp.getTarget().getClass().getName() + "."
	                 + pjp.getSignature().getName() + "("+StringUtils.join(simpleParams,",")+")] ");
	         return result;
	      }
}

