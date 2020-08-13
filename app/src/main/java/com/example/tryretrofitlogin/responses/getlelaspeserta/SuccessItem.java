package com.example.tryretrofitlogin.responses.getlelaspeserta;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("peserta_id")
	private int pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelang_id")
	private int lelangId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setPesertaId(int pesertaId){
		this.pesertaId = pesertaId;
	}

	public int getPesertaId(){
		return pesertaId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setLelangId(int lelangId){
		this.lelangId = lelangId;
	}

	public int getLelangId(){
		return lelangId;
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
			"SuccessItem{" + 
			"peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelang_id = '" + lelangId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}