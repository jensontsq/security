package com.jenson.security.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor{

	//调用之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		request.setAttribute("startTime",new Date().getTime());
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		return true; //注意，控制能不能访问postHandle 方法
	}

	//controller运行时调用，异常不调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		long start=new Date().getTime();
		System.out.println("time Interceptor 耗时："+(new Date().getTime()-start));
		
	}

	//总会调用
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		long start=new Date().getTime();
		System.out.println("time Interceptor 耗时："+(new Date().getTime()-start));
		
		//如果异常为空，那就说明自己已经定义的@ControllerAdvice 给处理了 ,他在afterCompletion 前执行
		System.out.println("ex is:"+ex);
	}

}
