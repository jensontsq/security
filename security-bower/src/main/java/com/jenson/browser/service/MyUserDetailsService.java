package com.jenson.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MyUserDetailsService implements UserDetailsService{

	private Logger log=LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("登陆用户名"+username);
		//根据查询到的用户判断是否冻结
		System.out.println(passwordEncoder.encode("123456"));
		return new User(username,passwordEncoder.encode("123456"),
				//是否可用
				true,
				//用户是否过期
				true,
				//是否密码过去
				true,
				//是否被锁定
				true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

	}
	
	

}
