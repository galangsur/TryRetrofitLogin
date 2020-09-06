package com.example.tryretrofitlogin.responses.getleltransbyid;

import com.google.gson.annotations.SerializedName;

public class GetleltransbyidResponse{

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
			"GetleltransbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}