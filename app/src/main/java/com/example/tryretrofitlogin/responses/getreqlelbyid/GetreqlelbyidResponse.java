package com.example.tryretrofitlogin.responses.getreqlelbyid;

import com.google.gson.annotations.SerializedName;

public class GetreqlelbyidResponse{

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
			"GetreqlelbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}