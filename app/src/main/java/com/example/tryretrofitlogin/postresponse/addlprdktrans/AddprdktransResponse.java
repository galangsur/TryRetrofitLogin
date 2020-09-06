package com.example.tryretrofitlogin.postresponse.addlprdktrans;

import com.google.gson.annotations.SerializedName;

public class AddprdktransResponse{

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
			"AddprdktransResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}