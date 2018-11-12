package com.jenson.browser.support;

public class SimpleResponse {
	
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	public SimpleResponse(Object content) {
		this.content=content;
	}

}
