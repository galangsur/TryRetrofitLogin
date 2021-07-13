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

	@SerializedName("img_lelang")
	private String imgLelang;

	@SerializedName("req_waktuperbid")
	private String reqWaktuperbid;

	@SerializedName("id")
	private int id;

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