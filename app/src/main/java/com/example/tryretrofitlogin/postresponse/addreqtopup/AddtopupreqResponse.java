package com.example.tryretrofitlogin.postresponse.addreqtopup;

import com.google.gson.annotations.SerializedName;

public class AddtopupreqResponse{

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
			"AddtopupreqResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}