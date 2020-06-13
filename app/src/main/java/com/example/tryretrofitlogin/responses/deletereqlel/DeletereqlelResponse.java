package com.example.tryretrofitlogin.responses.deletereqlel;

import com.google.gson.annotations.SerializedName;

public class DeletereqlelResponse{

	@SerializedName("success")
	private boolean success;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"DeletereqlelResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}