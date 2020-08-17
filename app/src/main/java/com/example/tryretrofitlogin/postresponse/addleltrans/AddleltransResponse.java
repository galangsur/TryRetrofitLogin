package com.example.tryretrofitlogin.postresponse.addleltrans;

import com.google.gson.annotations.SerializedName;

public class AddleltransResponse{

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
			"AddleltransResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}