package com.example.tryretrofitlogin.responses.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

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
			"LoginResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}