package com.example.tryretrofitlogin.responses.getsolusicomplaint;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetSolusiComplaintResponse{

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
			"GetSolusiComplaintResponse{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}