package com.example.tryretrofitlogin.responses.gethewanbyid;

import com.google.gson.annotations.SerializedName;

public class GethewanbyidResponse{

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
			"GethewanbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}