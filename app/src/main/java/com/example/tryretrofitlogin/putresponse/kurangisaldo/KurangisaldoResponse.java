package com.example.tryretrofitlogin.putresponse.kurangisaldo;

import com.google.gson.annotations.SerializedName;

public class KurangisaldoResponse{

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
			"KurangisaldoResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}