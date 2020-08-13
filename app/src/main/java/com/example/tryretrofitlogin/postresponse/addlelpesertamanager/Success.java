package com.example.tryretrofitlogin.postresponse.addlelpesertamanager;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("peserta_id")
	private String pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelbrjalan_id")
	private String lelbrjalanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setPesertaId(String pesertaId){
		this.pesertaId = pesertaId;
	}

	public String getPesertaId(){
		return pesertaId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setLelbrjalanId(String lelbrjalanId){
		this.lelbrjalanId = lelbrjalanId;
	}

	public String getLelbrjalanId(){
		return lelbrjalanId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelbrjalan_id = '" + lelbrjalanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}