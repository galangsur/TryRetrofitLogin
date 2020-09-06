package com.example.tryretrofitlogin.responses.getprdktransbyid;

import com.google.gson.annotations.SerializedName;

public class GetprdktransbyidResponse{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"GetprdktransbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}