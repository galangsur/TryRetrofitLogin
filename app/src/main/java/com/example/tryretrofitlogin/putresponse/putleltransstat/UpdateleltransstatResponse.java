package com.example.tryretrofitlogin.putresponse.putleltransstat;

import com.google.gson.annotations.SerializedName;

public class UpdateleltransstatResponse{

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
			"UpdateleltransstatResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}