package com.example.tryretrofitlogin.responses.getusernamebyid;

import com.google.gson.annotations.SerializedName;

public class GetusernamebyidResponse{

	@SerializedName("success")
	private String success;

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"GetusernamebyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}