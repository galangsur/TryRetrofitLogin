package com.example.tryretrofitlogin.responses.getuserbyid;

import com.google.gson.annotations.SerializedName;

public class GetUserbyidResponse{

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
			"GetUserbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}