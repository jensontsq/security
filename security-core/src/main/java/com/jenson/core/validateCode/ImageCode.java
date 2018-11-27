package com.jenson.core.validateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {
	
	//图片
	private BufferedImage image;
	//随机数CODE
	private String code;
	
	//过期时间
	private LocalDateTime expireTime;
	
	public boolean isExpried() {
		System.out.println(LocalDateTime.now()+"&&&"+expireTime);
		return LocalDateTime.now().isAfter(expireTime);
	}


	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super();
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
	}
	
	//expireIn过期时间，秒
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super();
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	

}
