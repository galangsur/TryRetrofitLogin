package com.example.tryretrofitlogin.postresponse.addhasillelang;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("statushasil_id")
	private String statushasilId;

	@SerializedName("peserta_id")
	private String pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelang_id")
	private String lelangId;

	@SerializedName("hewan_id")
	private String hewanId;

	@SerializedName("nilai_akhir")
	private String nilaiAkhir;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pelelang_id")
	private String pelelangId;

	@SerializedName("harga_lelang")
	private String hargaLelang;

	@SerializedName("hasillelangs_token")
	private String hasillelangsToken;

	@SerializedName("statustrans")
	private String statustrans;

	public void setStatushasilId(String statushasilId){
		this.statushasilId = statushasilId;
	}

	public String getStatushasilId(){
		return statushasilId;
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

	public void setLelangId(String lelangId){
		this.lelangId = lelangId;
	}

	public String getLelangId(){
		return lelangId;
	}

	public void setHewanId(String hewanId){
		this.hewanId = hewanId;
	}

	public String getHewanId(){
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

	public void setPelelangId(String pelelangId){
		this.pelelangId = pelelangId;
	}

	public String getPelelangId(){
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
			"Success{" + 
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
			",statustrans = '" + statustrans + '\'' + 
			"}";
		}
}