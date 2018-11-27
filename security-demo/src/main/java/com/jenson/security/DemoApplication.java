package com.jenson.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.jenson.security","com.jenson.browser","com.jenson.core"})
public class DemoApplication {

	public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args); 
	}
} 
