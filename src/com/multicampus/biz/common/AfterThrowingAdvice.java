package com.multicampus.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service("afterThrowing")
@Aspect
public class AfterThrowingAdvice {

	@AfterThrowing(pointcut="PointcutCommon.allPointcut()", throwing="exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		
		String method = jp.getSignature().getName();
		
		System.out.println("[���� ó��] �����Ͻ� �޼ҵ�" + method +"() ������ ���ܸ޽��� : " + exceptObj.getMessage());
	}
}