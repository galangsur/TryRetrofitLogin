package com.example.tryretrofitlogin.postresponse.addReqlelangtoadmin;

import com.google.gson.annotations.SerializedName;

public class AddReqlelangtoadminResponse{

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
			"AddReqlelangtoadminResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}