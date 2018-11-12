package com.jenson.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jenson.core.properties.SecurityProperties;

@Configuration
public class BrowserConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private SecurityProperties securityProperties;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")
		  .and()
		  //请求授权
		  .authorizeRequests()
		  //当前这个页面
		  .antMatchers("/authentication/require",securityProperties.getBp().getLoginPage())
		  //不需要身份认证
		  .permitAll()
		  //任何请求
		  .anyRequest()
		  //都需要身份认证
		  .authenticated()
		  .and().csrf().disable();
	}

}
