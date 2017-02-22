package com.handstudio.model;

import javax.validation.constraints.Size;

public class Admin {
	private Integer no;
	@Size(min=1, max=30)
	private String name;
	@Size(min=1, max=45)
	private String password;
	private String updateDate;
	
	public Integer getNo(){
		return no;
	}
	public void setNo(Integer no){
		this.no = no;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}
}
