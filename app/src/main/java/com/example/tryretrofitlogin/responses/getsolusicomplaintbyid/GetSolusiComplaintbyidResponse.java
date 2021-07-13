package com.example.tryretrofitlogin.responses.getsolusicomplaintbyid;

import com.google.gson.annotations.SerializedName;

public class GetSolusiComplaintbyidResponse{

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
			"GetSolusiComplaintbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}