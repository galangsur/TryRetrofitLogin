package com.example.tryretrofitlogin.postresponse.addrequestlelangtoadmin;

import com.google.gson.annotations.SerializedName;

public class AddreqleltoadminResponse{

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
			"AddreqleltoadminResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}