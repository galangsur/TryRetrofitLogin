package com.example.tryretrofitlogin.postresponse.addnewuserreviewtoken;

import com.google.gson.annotations.SerializedName;

public class Addnewuserreviewtoken{

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
			"Addnewuserreviewtoken{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}