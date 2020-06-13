package com.example.tryretrofitlogin.responses.getreqlelbyid;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("pengirim_id")
	private String pengirimId;

	@SerializedName("lelang_id")
	private String lelangId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

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

	public void setPengirimId(String pengirimId){
		this.pengirimId = pengirimId;
	}

	public String getPengirimId(){
		return pengirimId;
	}

	public void setLelangId(String lelangId){
		this.lelangId = lelangId;
	}

	public String getLelangId(){
		return lelangId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",pengirim_id = '" + pengirimId + '\'' + 
			",lelang_id = '" + lelangId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}