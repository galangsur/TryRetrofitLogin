package com.example.tryretrofitlogin.responses.newwallet;

import com.google.gson.annotations.SerializedName;

public class WalletResponse{

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
			"WalletResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}