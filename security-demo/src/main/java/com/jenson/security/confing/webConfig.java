package com.jenson.security.confing;

import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jenson.security.interceptor.TimeInterceptor;


@Configuration
public class webConfig extends WebMvcConfigurerAdapter{

	
	@Autowired
	private TimeInterceptor time;
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(time);
	}
	
}
