package com.jenson.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result=	mockMvc.perform(get("/user")
				.param("userName", "ÕÅÈý").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(3)).
		andReturn().getResponse().getContentAsString();
		System.out.println(result.toString());
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
	String result=	mockMvc.perform(get("/user/1").
				contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).
		andExpect(jsonPath("$.userName")
				.value("Tom"))
		.andReturn().getResponse().getContentAsString();
		System.out.println(result.toString());
	}
	
	@Test
	public void whenGenInfoFail() throws Exception {
		mockMvc.perform(get("/user/a").
				contentType(MediaType.APPLICATION_JSON_UTF8)).
		andExpect(status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		Date date=new Date();
		System.out.println(date.getTime());
		String content="{\"userName\":\"Tom\",\"passowrd\":null,\"date\":"+date.getTime()+"}";
		String result=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		Date date=new Date();
		System.out.println(date.getTime());
		String content="{\"id\":\"1\",\"userName\":\"Tom\",\"passowrd\":null,\"date\":"+date.getTime()+"}";
		String result=mockMvc.perform(put("/user/1").
				contentType(MediaType.APPLICATION_JSON_UTF8).
				content(content)).andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value(1)).
		        andReturn().getResponse()
		        .getContentAsString();
		System.out.println(result);
	}

}
