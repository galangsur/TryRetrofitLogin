package com.example.tryretrofitlogin.responses.gethlelangbypeserta;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("statushasil_id")
	private int statushasilId;

	@SerializedName("peserta_id")
	private int pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelbrjalan_id")
	private int lelbrjalanId;

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

	@SerializedName("statustrans")
	private String statustrans;

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

	public void setLelbrjalanId(int lelbrjalanId){
		this.lelbrjalanId = lelbrjalanId;
	}

	public int getLelbrjalanId(){
		return lelbrjalanId;
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

	public void setStatustrans(String statustrans){
		this.statustrans = statustrans;
	}

	public String getStatustrans(){
		return statustrans;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"statushasil_id = '" + statushasilId + '\'' + 
			",peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelbrjalan_id = '" + lelbrjalanId + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",nilai_akhir = '" + nilaiAkhir + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",pelelang_id = '" + pelelangId + '\'' + 
			",harga_lelang = '" + hargaLelang + '\'' + 
			",hasillelangs_token = '" + hasillelangsToken + '\'' + 
			",statustrans = '" + statustrans + '\'' + 
			"}";
		}
}