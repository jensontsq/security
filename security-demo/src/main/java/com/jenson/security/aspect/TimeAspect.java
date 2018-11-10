package com.jenson.security.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//切片  那不到原始信息HTTP请求，能拿到到请求的方法数据（参数）和相应得具体方法信息
/*@Aspect
@Component*/
public class TimeAspect {
	
	@Around("execution(* com.jenson.security.controller.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time sapect start");
		
		//获取所有参数
		Object[] args=pjp.getArgs();
		for (Object object : args) {
			System.out.println("args="+object);
		}
		long start=new Date().getTime();
		//获取所拦截方法得返回值
		Object obj=pjp.proceed();
		System.out.println("obj"+obj);
		System.out.println("time sapect 耗时："+(new Date().getTime()-start));
		System.out.println("time sapect end");
		return obj;
	}

}