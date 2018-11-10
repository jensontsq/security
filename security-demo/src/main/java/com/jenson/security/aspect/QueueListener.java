package com.jenson.security.aspect;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jenson.security.async.DeferredResultHolder;
import com.jenson.security.async.MockQueue;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	Logger log=LoggerFactory.getLogger(QueueListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(()->{
			while (true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNum=mockQueue.getCompleteOrder();
					log.info("返回订单处理"+orderNum);
					deferredResultHolder.getMap().get(orderNum).setResult("place order success ");
					mockQueue.setCompleteOrder(null);
				}else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
				
				).start();
		
	}

}
