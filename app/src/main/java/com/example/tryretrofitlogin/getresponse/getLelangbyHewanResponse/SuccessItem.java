package com.example.tryretrofitlogin.getresponse.getLelangbyHewanResponse;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("waktuperbid")
	private int waktuperbid;

	@SerializedName("harga")
	private int harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("hewan_id")
	private int hewanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private String id;

	@SerializedName("img_lelang")
	private String imgLelang;

	@SerializedName("nominalperbid")
	private int nominalperbid;

	public void setWaktuperbid(int waktuperbid){
		this.waktuperbid = waktuperbid;
	}

	public int getWaktuperbid(){
		return waktuperbid;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
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

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setImgLelang(String imgLelang){
		this.imgLelang = imgLelang;
	}

	public String getImgLelang(){
		return imgLelang;
	}

	public void setNominalperbid(int nominalperbid){
		this.nominalperbid = nominalperbid;
	}

	public int getNominalperbid(){
		return nominalperbid;
	}

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"waktuperbid = '" + waktuperbid + '\'' + 
			",harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",img_lelang = '" + imgLelang + '\'' + 
			",nominalperbid = '" + nominalperbid + '\'' + 
			"}";
		}
}