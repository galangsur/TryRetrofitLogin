package com.example.tryretrofitlogin.postresponse.addtranscomp;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("solusi_complaint")
	private String solusiComplaint;

	@SerializedName("transaksi_id")
	private String transaksiId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("idpengaju_complaint")
	private String idpengajuComplaint;

	@SerializedName("alasan_complaint")
	private String alasanComplaint;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("namapengaju_complaint")
	private String namapengajuComplaint;

	@SerializedName("img_complaint")
	private String imgComplaint;

	@SerializedName("id")
	private int id;

	public void setSolusiComplaint(String solusiComplaint){
		this.solusiComplaint = solusiComplaint;
	}

	public String getSolusiComplaint(){
		return solusiComplaint;
	}

	public void setTransaksiId(String transaksiId){
		this.transaksiId = transaksiId;
	}

	public String getTransaksiId(){
		return transaksiId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdpengajuComplaint(String idpengajuComplaint){
		this.idpengajuComplaint = idpengajuComplaint;
	}

	public String getIdpengajuComplaint(){
		return idpengajuComplaint;
	}

	public void setAlasanComplaint(String alasanComplaint){
		this.alasanComplaint = alasanComplaint;
	}

	public String getAlasanComplaint(){
		return alasanComplaint;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamapengajuComplaint(String namapengajuComplaint){
		this.namapengajuComplaint = namapengajuComplaint;
	}

	public String getNamapengajuComplaint(){
		return namapengajuComplaint;
	}

	public void setImgComplaint(String imgComplaint){
		this.imgComplaint = imgComplaint;
	}

	public String getImgComplaint(){
		return imgComplaint;
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
			"Success{" + 
			"solusi_complaint = '" + solusiComplaint + '\'' + 
			",transaksi_id = '" + transaksiId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",idpengaju_complaint = '" + idpengajuComplaint + '\'' + 
			",alasan_complaint = '" + alasanComplaint + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",namapengaju_complaint = '" + namapengajuComplaint + '\'' + 
			",img_complaint = '" + imgComplaint + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}