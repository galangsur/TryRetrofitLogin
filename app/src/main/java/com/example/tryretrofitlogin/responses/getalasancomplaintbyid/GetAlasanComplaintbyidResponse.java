package com.example.tryretrofitlogin.responses.getalasancomplaintbyid;

import com.google.gson.annotations.SerializedName;

public class GetAlasanComplaintbyidResponse{

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
			"GetAlasanComplaintbyidResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}