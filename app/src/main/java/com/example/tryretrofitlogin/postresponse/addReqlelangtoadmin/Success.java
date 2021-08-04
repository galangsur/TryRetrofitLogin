package com.example.tryretrofitlogin.postresponse.addReqlelangtoadmin;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("sertifparent_token")
	private String sertifparentToken;

	@SerializedName("req_nominalperbid")
	private String reqNominalperbid;

	@SerializedName("harga")
	private String harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("imgparent_token")
	private String imgparentToken;

	@SerializedName("hewan_id")
	private String hewanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("comment")
	private String comment;

	@SerializedName("umur_hewan")
	private String umur_hewan;

	@SerializedName("jeniskelamin_hewan")
	private String jeniskelamin_hewan;

	@SerializedName("warna_hewan")
	private String warna_hewan;

	@SerializedName("img_lelang")
	private String imgLelang;

	@SerializedName("req_waktuperbid")
	private String reqWaktuperbid;

	@SerializedName("id")
	private int id;

	@SerializedName("pesertaonline_token")
	private String pesertaonline_token;

	public String getPesertaonline_token() {
		return pesertaonline_token;
	}

	public void setPesertaonline_token(String pesertaonline_token) {
		this.pesertaonline_token = pesertaonline_token;
	}

	public String getUmur_hewan() {
		return umur_hewan;
	}

	public void setUmur_hewan(String umur_hewan) {
		this.umur_hewan = umur_hewan;
	}

	public String getJeniskelamin_hewan() {
		return jeniskelamin_hewan;
	}

	public void setJeniskelamin_hewan(String jeniskelamin_hewan) {
		this.jeniskelamin_hewan = jeniskelamin_hewan;
	}

	public String getWarna_hewan() {
		return warna_hewan;
	}

	public void setWarna_hewan(String warna_hewan) {
		this.warna_hewan = warna_hewan;
	}

	public void setSertifparentToken(String sertifparentToken){
		this.sertifparentToken = sertifparentToken;
	}

	public String getSertifparentToken(){
		return sertifparentToken;
	}

	public void setReqNominalperbid(String reqNominalperbid){
		this.reqNominalperbid = reqNominalperbid;
	}

	public String getReqNominalperbid(){
		return reqNominalperbid;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
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

	public void setImgparentToken(String imgparentToken){
		this.imgparentToken = imgparentToken;
	}

	public String getImgparentToken(){
		return imgparentToken;
	}

	public void setHewanId(String hewanId){
		this.hewanId = hewanId;
	}

	public String getHewanId(){
		return hewanId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setImgLelang(String imgLelang){
		this.imgLelang = imgLelang;
	}

	public String getImgLelang(){
		return imgLelang;
	}

	public void setReqWaktuperbid(String reqWaktuperbid){
		this.reqWaktuperbid = reqWaktuperbid;
	}

	public String getReqWaktuperbid(){
		return reqWaktuperbid;
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
			"sertifparent_token = '" + sertifparentToken + '\'' + 
			",req_nominalperbid = '" + reqNominalperbid + '\'' + 
			",harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",imgparent_token = '" + imgparentToken + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",comment = '" + comment + '\'' + 
			",img_lelang = '" + imgLelang + '\'' + 
			",req_waktuperbid = '" + reqWaktuperbid + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}