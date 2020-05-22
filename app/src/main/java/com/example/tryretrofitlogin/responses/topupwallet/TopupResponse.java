package com.example.tryretrofitlogin.responses.topupwallet;

import com.google.gson.annotations.SerializedName;

public class TopupResponse{

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
			"TopupResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}