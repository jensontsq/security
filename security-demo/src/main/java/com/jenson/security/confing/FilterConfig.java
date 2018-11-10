package com.jenson.security.confing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.jenson.security.filter.TimeFilter;


@Configuration
public class FilterConfig {
	
/* @Bean
 public FilterRegistrationBean timeFilter() {
	 FilterRegistrationBean frb=new FilterRegistrationBean();
	 TimeFilter timeFilter=new TimeFilter();
	 frb.setFilter(timeFilter);
	 List<String> urls=new ArrayList<>();
	 urls.add("/*");
	 frb.setUrlPatterns(urls);
	 return frb;
	 
 }*/

}
