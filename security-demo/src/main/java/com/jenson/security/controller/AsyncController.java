package com.jenson.security.controller;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.jenson.security.async.DeferredResultHolder;
import com.jenson.security.async.MockQueue;


@RestController
public class AsyncController {

	Logger logger=LoggerFactory.getLogger(AsyncController.class);
	
	@Autowired
	private MockQueue MockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@GetMapping("/order")
	public DeferredResult<String> order() throws InterruptedException {
		
		logger.info("主线程开始");
		String orderNum=RandomStringUtils.randomNumeric(8);
		MockQueue.setPlaceOrder(orderNum);
		
		DeferredResult<String> dr=new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNum, dr);
	/*	Callable<String> result=new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				Thread.sleep(1000);
				logger.info("副线程返回");
				return "success";
			}
		};*/
		
		logger.info("主线程结束");
		return dr;
	}
}
