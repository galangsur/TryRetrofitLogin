package com.example.tryretrofitlogin.putresponse.putgchatid;

import com.google.gson.annotations.SerializedName;

public class UpdategchatidResponse {

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
			"ResponseUpdategchatid{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}