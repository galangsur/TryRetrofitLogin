package com.example.tryretrofitlogin.responses.getlelbrjalanbygc;

import com.google.gson.annotations.SerializedName;

public class GetlelbrjalanbygcResponse{

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
			"GetlelbrjalanbygcResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}