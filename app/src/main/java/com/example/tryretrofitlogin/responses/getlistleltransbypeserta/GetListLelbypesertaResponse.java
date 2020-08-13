package com.example.tryretrofitlogin.responses.getlistleltransbypeserta;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetListLelbypesertaResponse {

	@SerializedName("success")
	private ArrayList<SuccessItem> success;

	public void setSuccess(ArrayList<SuccessItem> success){
		this.success = success;
	}

	public ArrayList<SuccessItem> getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"GetListLelResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}