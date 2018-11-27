package com.jenson.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="jenson.security")
public class SecurityProperties {
	private BrowserProperties bp=new BrowserProperties();
   
   private ValidateCodeProperties vcp=new ValidateCodeProperties();

public BrowserProperties getBp() {
	return bp;
}

public void setBp(BrowserProperties bp) {
	this.bp = bp;
}

public ValidateCodeProperties getVcp() {
	return vcp;
}

public void setVcp(ValidateCodeProperties vcp) {
	this.vcp = vcp;
}


   
   
}
