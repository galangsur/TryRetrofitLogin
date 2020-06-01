package com.example.tryretrofitlogin.responses.gethewan;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id")
	private int id;

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
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

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}