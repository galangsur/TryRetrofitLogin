package com.example.tryretrofitlogin.postresponse.addphoto;

import com.google.gson.annotations.SerializedName;

public class PostphotoResponse{

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
			"PostphotoResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}