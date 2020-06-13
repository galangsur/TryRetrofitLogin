package com.example.tryretrofitlogin.responses.getreqlelbysender;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetreqlelbysenderResponse{

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
			"GetreqlelbysenderResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}