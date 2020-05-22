package com.example.tryretrofitlogin.responses.getlelangbyid;

import com.google.gson.annotations.SerializedName;

public class GetlelangbyidResponse{

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
			"GetlelangbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}