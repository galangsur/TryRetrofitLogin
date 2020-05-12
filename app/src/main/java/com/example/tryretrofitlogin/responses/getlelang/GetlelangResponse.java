package com.example.tryretrofitlogin.responses.getlelang;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetlelangResponse{

	@SerializedName("success")
	private ArrayList<SuccessItem> success;

	public ArrayList<SuccessItem> getSuccess(){
		return success;
	}

	public void setSuccess(ArrayList<SuccessItem> success){
		this.success = success;
	}



	@Override
 	public String toString(){
		return 
			"GetlelangResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}