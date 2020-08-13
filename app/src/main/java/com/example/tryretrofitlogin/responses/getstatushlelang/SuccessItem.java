package com.example.tryretrofitlogin.responses.getstatushlelang;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("lelangstatus")
	private String lelangstatus;

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLelangstatus(String lelangstatus){
		this.lelangstatus = lelangstatus;
	}

	public String getLelangstatus(){
		return lelangstatus;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",lelangstatus = '" + lelangstatus + '\'' + 
			"}";
		}
}