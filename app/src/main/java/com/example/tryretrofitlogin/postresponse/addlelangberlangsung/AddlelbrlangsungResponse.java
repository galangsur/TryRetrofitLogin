package com.example.tryretrofitlogin.postresponse.addlelangberlangsung;

import com.google.gson.annotations.SerializedName;

public class AddlelbrlangsungResponse{

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
			"AddlelbrlangsungResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}