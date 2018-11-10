package com.jenson.security.async;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class DeferredResultHolder {
	
	private Map<String,DeferredResult<String>> map=new HashedMap();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}

	
}
