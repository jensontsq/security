package com.jenson.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jenson.browser.support.SimpleResponse;
import com.jenson.core.properties.SecurityProperties;

@RestController
public class BrowserSecurityController {
	
	Logger log=LoggerFactory.getLogger(BrowserSecurityController.class);
	
	//从springsession缓存获取
    private RequestCache requestCache=new HttpSessionRequestCache();
    
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    
    @Autowired
    private SecurityProperties securityProperties;
	
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		SavedRequest savedRequest=requestCache.getRequest(request, response);
		if(null!=savedRequest) {
			//从springsession缓存中获取请求的url
			String target=savedRequest.getRedirectUrl();
			log.info("引发跳转的请求是："+target);
			//判断时候是html结尾得
			if(StringUtils.endsWithIgnoreCase(target,".html")) {
				//跳转页面
				redirectStrategy.sendRedirect(request, response, securityProperties.getBp().getLoginPage());
			}
			
		}
		return new SimpleResponse("访问得服务需要身份认证青岛登录页面");
		
	}

}
