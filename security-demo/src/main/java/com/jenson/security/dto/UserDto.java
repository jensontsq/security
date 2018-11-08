package com.jenson.security.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

public class UserDto {
	
	private String id;
	
	public interface UserDtoSimpleView{};
	
	public interface UserDtoDetailView extends UserDtoSimpleView{};
	
	private String userName;
	
	
	@NotBlank
	private String passowrd;
	
	private Date date;
	
	
	@JsonView(UserDtoSimpleView.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonView(UserDtoSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserDtoSimpleView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonView(UserDtoDetailView.class)
	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	

}
