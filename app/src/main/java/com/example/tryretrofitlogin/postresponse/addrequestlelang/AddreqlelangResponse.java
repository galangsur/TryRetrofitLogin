package com.example.tryretrofitlogin.postresponse.addrequestlelang;

import com.google.gson.annotations.SerializedName;

public class AddreqlelangResponse{

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
			"AddreqlelangResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}