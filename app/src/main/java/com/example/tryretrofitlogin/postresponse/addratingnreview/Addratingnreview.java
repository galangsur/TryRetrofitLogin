package com.example.tryretrofitlogin.postresponse.addratingnreview;

import com.google.gson.annotations.SerializedName;

public class Addratingnreview{

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
			"Addratingnreview{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}