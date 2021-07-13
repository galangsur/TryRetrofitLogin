package com.example.tryretrofitlogin.postresponse.addrequestsignuptoadmin;

import com.google.gson.annotations.SerializedName;

public class AddreqsignuptoadminResponse{

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
			"AddreqsignuptoadminResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}