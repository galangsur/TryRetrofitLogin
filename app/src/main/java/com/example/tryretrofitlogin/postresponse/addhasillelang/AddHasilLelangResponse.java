package com.example.tryretrofitlogin.postresponse.addhasillelang;

import com.google.gson.annotations.SerializedName;

public class AddHasilLelangResponse{

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
			"AddHasilLelangResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}