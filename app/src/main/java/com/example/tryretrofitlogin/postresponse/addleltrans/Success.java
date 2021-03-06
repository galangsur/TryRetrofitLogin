package com.example.tryretrofitlogin.postresponse.addleltrans;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("statushasil_id")
	private String statushasilId;

	@SerializedName("peserta_id")
	private String pesertaId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("nilai_akhir")
	private String nilaiAkhir;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("hasillelang_token")
	private String hasillelangToken;

	@SerializedName("statushasil")
	private String statushasil;

	@SerializedName("pelelang_id")
	private String pelelangId;

	@SerializedName("harga_lelang")
	private String hargaLelang;

	@SerializedName("hewan")
	private String hewan;

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

	public void setHasillelangToken(String hasillelangToken){
		this.hasillelangToken = hasillelangToken;
	}

	public String getHasillelangToken(){
		return hasillelangToken;
	}

	public void setStatushasil(String statushasil){
		this.statushasil = statushasil;
	}

	public String getStatushasil(){
		return statushasil;
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

	public void setHewan(String hewan){
		this.hewan = hewan;
	}

	public String getHewan(){
		return hewan;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"statushasil_id = '" + statushasilId + '\'' + 
			",peserta_id = '" + pesertaId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",nilai_akhir = '" + nilaiAkhir + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",hasillelang_token = '" + hasillelangToken + '\'' + 
			",statushasil = '" + statushasil + '\'' + 
			",pelelang_id = '" + pelelangId + '\'' + 
			",harga_lelang = '" + hargaLelang + '\'' + 
			",hewan = '" + hewan + '\'' + 
			"}";
		}
}