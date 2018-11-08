package com.jenson.security.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HandlerController {
	
	@ExceptionHandler(ErrorMsg.class)
	@ResponseBody
	public Map<String,Object> handlerException(ErrorMsg msg){
		Map<String,Object> map=new HashMap<>();
		map.put("code", msg.getCode());
		map.put("msg", msg.getMsg());
		return map;
	}

}
