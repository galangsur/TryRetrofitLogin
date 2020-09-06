package com.example.tryretrofitlogin.postresponse.addproduk;

import com.google.gson.annotations.SerializedName;

public class AddprodukResponse{

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
			"AddprodukResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}