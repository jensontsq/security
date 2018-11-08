package com.jenson.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

//¹ýÂËÆ÷
/*@Component*/
public class TimeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init this filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long start =new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter ="+(new Date().getTime()-start));
		System.out.println("time filter end");
	}

	@Override
	public void destroy() {
		System.out.println("destroy this filter");
		
	}

}
