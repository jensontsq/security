package com.jenson.security.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	Logger logger=LoggerFactory.getLogger(MockQueue.class);
	//下单
	private String placeOrder;
	//完成
	private String completeOrder;
	public String getPlaceOrder() {
		return placeOrder;
	}
	public void setPlaceOrder(String placeOrder) throws InterruptedException {
		new Thread(() ->  {
			logger.info("接到下单请求="+placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.completeOrder=placeOrder;
			logger.info("下单处理完毕="+placeOrder);
		}) .start();
		
		
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
	
	

	
}
