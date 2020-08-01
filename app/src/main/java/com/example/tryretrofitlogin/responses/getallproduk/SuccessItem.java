package com.example.tryretrofitlogin.responses.getallproduk;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("comentcht_id")
	private String comentchtId;

	@SerializedName("harga_produk")
	private String hargaProduk;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("jumlah_produk")
	private int jumlahProduk;

	@SerializedName("hewan_id")
	private int hewanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("tentang_produk")
	private String tentangProduk;

	@SerializedName("img_produk")
	private String imgProduk;

	public void setComentchtId(String comentchtId){
		this.comentchtId = comentchtId;
	}

	public String getComentchtId(){
		return comentchtId;
	}

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

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setJumlahProduk(int jumlahProduk){
		this.jumlahProduk = jumlahProduk;
	}

	public int getJumlahProduk(){
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTentangProduk(String tentangProduk){
		this.tentangProduk = tentangProduk;
	}

	public String getTentangProduk(){
		return tentangProduk;
	}

	public void setImgProduk(String imgProduk){
		this.imgProduk = imgProduk;
	}

	public String getImgProduk(){
		return imgProduk;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"comentcht_id = '" + comentchtId + '\'' + 
			",harga_produk = '" + hargaProduk + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",jumlah_produk = '" + jumlahProduk + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",tentang_produk = '" + tentangProduk + '\'' + 
			",img_produk = '" + imgProduk + '\'' + 
			"}";
		}
}