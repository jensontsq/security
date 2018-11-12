package com.jenson.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="jenson.security")
public class SecurityProperties {
   BrowserProperties bp=new BrowserProperties();

public BrowserProperties getBp() {
	return bp;
}

public void setBp(BrowserProperties bp) {
	this.bp = bp;
}
   
   
}
