package com.jenson.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jenson.browser.authentication.JensonAuthenctiationFailureHandler;
import com.jenson.browser.authentication.JensonAuthenticationSuccessHandler;
import com.jenson.core.filter.ValidateCodeFilter;
import com.jenson.core.properties.SecurityProperties;


@Configuration
public class BrowserConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private  JensonAuthenticationSuccessHandler jensonAuthenticationSuccessHandler;
	
	@Autowired
	private JensonAuthenctiationFailureHandler jesnonAuthenctiationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter filter=new ValidateCodeFilter();
		filter.setAuthenticationFailureHandler(jesnonAuthenctiationFailureHandler);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).formLogin()
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")
		.successHandler(jensonAuthenticationSuccessHandler)
		.failureHandler(jesnonAuthenctiationFailureHandler)
		  .and()
		  //请求授权
		  .authorizeRequests()
		  //当前这个页面
		  .antMatchers("/authentication/require",securityProperties.getBp().getLoginPage(),"/code/image")
		  //不需要身份认证
		  .permitAll()
		  //任何请求
		  .anyRequest()
		  //都需要身份认证
		  .authenticated()
		  .and().csrf().disable();
	}

}
