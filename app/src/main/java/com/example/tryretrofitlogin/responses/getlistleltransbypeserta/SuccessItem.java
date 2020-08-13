package com.example.tryretrofitlogin.responses.getlistleltransbypeserta;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("statushasil_id")
	private int statushasilId;

	@SerializedName("peserta_id")
	private int pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelang_id")
	private int lelangId;

	@SerializedName("hewan_id")
	private int hewanId;

	@SerializedName("nilai_akhir")
	private String nilaiAkhir;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pelelang_id")
	private int pelelangId;

	@SerializedName("harga_lelang")
	private String hargaLelang;

	@SerializedName("hasillelangs_token")
	private String hasillelangsToken;

	public void setStatushasilId(int statushasilId){
		this.statushasilId = statushasilId;
	}

	public int getStatushasilId(){
		return statushasilId;
	}

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

	public void setHewanId(int hewanId){
		this.hewanId = hewanId;
	}

	public int getHewanId(){
		return hewanId;
	}

	public void setNilaiAkhir(String nilaiAkhir){
		this.nilaiAkhir = nilaiAkhir;
	}

	public String getNilaiAkhir(){
		return nilaiAkhir;
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

	public void setPelelangId(int pelelangId){
		this.pelelangId = pelelangId;
	}

	public int getPelelangId(){
		return pelelangId;
	}

	public void setHargaLelang(String hargaLelang){
		this.hargaLelang = hargaLelang;
	}

	public String getHargaLelang(){
		return hargaLelang;
	}

	public void setHasillelangsToken(String hasillelangsToken){
		this.hasillelangsToken = hasillelangsToken;
	}

	public String getHasillelangsToken(){
		return hasillelangsToken;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"statushasil_id = '" + statushasilId + '\'' + 
			",peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelang_id = '" + lelangId + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",nilai_akhir = '" + nilaiAkhir + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",pelelang_id = '" + pelelangId + '\'' + 
			",harga_lelang = '" + hargaLelang + '\'' + 
			",hasillelangs_token = '" + hasillelangsToken + '\'' + 
			"}";
		}
}