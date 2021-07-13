package com.example.tryretrofitlogin.responses.getlelpesertabyidlelberjalan;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("peserta_id")
	private String pesertaId;

	@SerializedName("peserta_nama")
	private String pesertaNama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelbrjalan_id")
	private int lelbrjalanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("hewan")
	private String hewan;

	public String getPesertaNama() {
		return pesertaNama;
	}

	public void setPesertaNama(String peserta_nama) {
		this.pesertaNama = peserta_nama;
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

	public void setLelbrjalanId(int lelbrjalanId){
		this.lelbrjalanId = lelbrjalanId;
	}

	public int getLelbrjalanId(){
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
			"peserta_id = '" + pesertaId + '\'' +
			"peserta_nama = '" + pesertaNama + '\'' +
			",updated_at = '" + updatedAt + '\'' + 
			",lelbrjalan_id = '" + lelbrjalanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",hewan = '" + hewan + '\'' + 
			"}";
		}
}