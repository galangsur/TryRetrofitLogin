package com.example.tryretrofitlogin.postresponse.addlelang;

import com.google.gson.annotations.SerializedName;

public class AddLelangResponse{

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
			"AddLelangResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}