package com.jenson.security.error;

public class Result<T> {

	
	private T data;
	private String code;
	private String msg;
	
	public static <T> Result<T> success(T data){
		return new Result<>(data);
		
	}
	
	public static <T> Result<T> error(String code,String msg){
		return new Result<>(code, msg);
	}
	
	public Result(ErrorMsg em){
		if(null==em) {
			return ;
		}
		this.code=em.getCode();
		this.msg=em.getMsg();
		
	}
	
	public Result(T data){
		this.data=data;
		this.code="200";
		this.msg="³É¹¦";
		
	}
	
	public Result(String code,String msg){
		this.code=code;
		this.msg=msg;
		
	}
	
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
