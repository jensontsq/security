package com.jenson.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jenson.core.exceprion.ValidateCodeException;
import com.jenson.core.validateCode.ImageCode;

//验证imageCode
//在security过滤器链上加入自己得验证码过滤器，在UserNamePasswordAuthenticationFilter在前
//OncePerRequestFilter保证过滤器每次只调用一次
public class ValidateCodeFilter extends OncePerRequestFilter{
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
  private static final String SESSION_KEY="SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//判断登陆请求
		if(StringUtils.equals("/authentication/form", request.getRequestURI())
				//判断请求方式
				&& StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return ;
			}
		}
		
		//调用下一个过滤器
		filterChain.doFilter(request, response);
		
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		//从session中取出验证码
		ImageCode sessionImageCode=(ImageCode) sessionStrategy.getAttribute(request, SESSION_KEY);
		//取出页面用户输入得验证码
		String codeInRequse=ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		if(StringUtils.isBlank(codeInRequse)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}
		
		if(null==sessionImageCode) {
			throw new ValidateCodeException("验证码不存在");
		}
		
		if(sessionImageCode.isExpried()) {
			sessionStrategy.removeAttribute(request, "SESSION_KEY");
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(sessionImageCode.getCode(), codeInRequse)) {
			throw new ValidateCodeException("验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, "SESSION_KEY");
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public static String getSessionKey() {
		return SESSION_KEY;
	}
	
	

}
