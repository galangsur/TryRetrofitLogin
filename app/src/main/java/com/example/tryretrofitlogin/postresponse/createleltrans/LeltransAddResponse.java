package com.example.tryretrofitlogin.postresponse.createleltrans;

import com.google.gson.annotations.SerializedName;

public class LeltransAddResponse{

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
			"LeltransAddResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}