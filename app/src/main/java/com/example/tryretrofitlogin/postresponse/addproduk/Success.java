package com.example.tryretrofitlogin.postresponse.addproduk;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("harga_produk")
	private String hargaProduk;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("jumlah_produk")
	private String jumlahProduk;

	@SerializedName("hewan_id")
	private int hewanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("tentang_produk")
	private String tentangProduk;

	public void setHargaProduk(String hargaProduk){
		this.hargaProduk = hargaProduk;
	}

	public String getHargaProduk(){
		return hargaProduk;
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

	public void setJumlahProduk(String jumlahProduk){
		this.jumlahProduk = jumlahProduk;
	}

	public String getJumlahProduk(){
		return jumlahProduk;
	}

	public void setHewanId(int hewanId){
		this.hewanId = hewanId;
	}

	public int getHewanId(){
		return hewanId;
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

	public void setTentangProduk(String tentangProduk){
		this.tentangProduk = tentangProduk;
	}

	public String getTentangProduk(){
		return tentangProduk;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"harga_produk = '" + hargaProduk + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",jumlah_produk = '" + jumlahProduk + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",tentang_produk = '" + tentangProduk + '\'' + 
			"}";
		}
}