package com.jenson.core.properties;

import com.jenson.core.enums.LoginResponseType;

public class BrowserProperties {
	private String loginPage="/login.html";
	
	private LoginResponseType loginType = LoginResponseType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}
	
	
	
	

}
