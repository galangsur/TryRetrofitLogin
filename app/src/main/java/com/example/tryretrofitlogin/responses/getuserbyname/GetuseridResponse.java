package com.example.tryretrofitlogin.responses.getuserbyname;

import com.google.gson.annotations.SerializedName;

public class GetuseridResponse{

	@SerializedName("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"GetuseridResponse{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}