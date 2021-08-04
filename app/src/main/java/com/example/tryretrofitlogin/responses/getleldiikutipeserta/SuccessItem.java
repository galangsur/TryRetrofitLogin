package com.example.tryretrofitlogin.responses.getleldiikutipeserta;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("peserta_nama")
	private String pesertaNama;

	@SerializedName("peserta_id")
	private String pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelbrjalan_id")
	private String lelbrjalanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("hewan")
	private String hewan;

	public void setPesertaNama(String pesertaNama){
		this.pesertaNama = pesertaNama;
	}

	public String getPesertaNama(){
		return pesertaNama;
	}

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHewan(String hewan){
		this.hewan = hewan;
	}

	public String getHewan(){
		return hewan;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"peserta_nama = '" + pesertaNama + '\'' + 
			",peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelbrjalan_id = '" + lelbrjalanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",hewan = '" + hewan + '\'' + 
			"}";
		}
}