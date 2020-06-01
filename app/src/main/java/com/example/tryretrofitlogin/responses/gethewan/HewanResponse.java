package com.example.tryretrofitlogin.responses.gethewan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HewanResponse{

	@SerializedName("success")
	private List<SuccessItem> success;

	public void setSuccess(List<SuccessItem> success){
		this.success = success;
	}

	public List<SuccessItem> getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"HewanResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}