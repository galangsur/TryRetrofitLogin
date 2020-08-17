package com.example.tryretrofitlogin.responses.detlelbrjalanbyid;

import com.google.gson.annotations.SerializedName;

public class DetlelbrjalanbyidResponse{

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
			"DetlelbrjalanbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}