package com.jenson.core.exceprion;

import org.springframework.security.core.AuthenticationException;

//AuthenticationException 是security定义得抽象异常，是身份认证过程中抛出异常得基类
public class ValidateCodeException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
