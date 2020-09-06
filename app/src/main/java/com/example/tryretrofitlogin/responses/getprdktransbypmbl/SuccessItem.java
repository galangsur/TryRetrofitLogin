package com.example.tryretrofitlogin.responses.getprdktransbypmbl;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("statushasil_id")
	private String statushasilId;

	@SerializedName("pembeli_id")
	private String pembeliId;

	@SerializedName("harga_produk")
	private String hargaProduk;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("produk_id")
	private String produkId;

	@SerializedName("prdktrans_token")
	private String prdktransToken;

	@SerializedName("statushasil")
	private String statushasil;

	@SerializedName("penjual_id")
	private String penjualId;

	@SerializedName("hewan")
	private String hewan;

	public void setStatushasilId(String statushasilId){
		this.statushasilId = statushasilId;
	}

	public String getStatushasilId(){
		return statushasilId;
	}

	public void setPembeliId(String pembeliId){
		this.pembeliId = pembeliId;
	}

	public String getPembeliId(){
		return pembeliId;
	}

	public void setHargaProduk(String hargaProduk){
		this.hargaProduk = hargaProduk;
	}

	public String getHargaProduk(){
		return hargaProduk;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public void setProdukId(String produkId){
		this.produkId = produkId;
	}

	public String getProdukId(){
		return produkId;
	}

	public void setPrdktransToken(String prdktransToken){
		this.prdktransToken = prdktransToken;
	}

	public String getPrdktransToken(){
		return prdktransToken;
	}

	public void setStatushasil(String statushasil){
		this.statushasil = statushasil;
	}

	public String getStatushasil(){
		return statushasil;
	}

	public void setPenjualId(String penjualId){
		this.penjualId = penjualId;
	}

	public String getPenjualId(){
		return penjualId;
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
			"statushasil_id = '" + statushasilId + '\'' + 
			",pembeli_id = '" + pembeliId + '\'' + 
			",harga_produk = '" + hargaProduk + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",produk_id = '" + produkId + '\'' + 
			",prdktrans_token = '" + prdktransToken + '\'' + 
			",statushasil = '" + statushasil + '\'' + 
			",penjual_id = '" + penjualId + '\'' + 
			",hewan = '" + hewan + '\'' + 
			"}";
		}
}