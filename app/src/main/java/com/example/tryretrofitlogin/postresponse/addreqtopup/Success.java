package com.example.tryretrofitlogin.postresponse.addreqtopup;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("wallet_id")
	private String walletId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("id")
	private String id;

	@SerializedName("reqnominal")
	private String reqnominal;

	public void setWalletId(String walletId){
		this.walletId = walletId;
	}

	public String getWalletId(){
		return walletId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setReqnominal(String reqnominal){
		this.reqnominal = reqnominal;
	}

	public String getReqnominal(){
		return reqnominal;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"wallet_id = '" + walletId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",id = '" + id + '\'' + 
			",reqnominal = '" + reqnominal + '\'' + 
			"}";
		}
}