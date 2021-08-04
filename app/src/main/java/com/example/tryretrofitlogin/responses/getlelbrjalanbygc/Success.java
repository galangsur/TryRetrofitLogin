package com.example.tryretrofitlogin.responses.getlelbrjalanbygc;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("req_nominalperbid")
	private int reqNominalperbid;

	@SerializedName("harga")
	private int harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("hewan_id")
	private String hewanId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private String id;

	@SerializedName("req_waktuperbid")
	private String reqWaktuperbid;

	@SerializedName("gchat_id")
	private String gchatId;

	@SerializedName("pesertaonline_token")
	private String pesertaonline_token;

	public void setPesertaonline_token(String pesertaonline_token) {
		this.pesertaonline_token = pesertaonline_token;
	}

	public String getPesertaonline_token() {
		return pesertaonline_token;
	}

	public void setReqNominalperbid(int reqNominalperbid){
		this.reqNominalperbid = reqNominalperbid;
	}

	public int getReqNominalperbid(){
		return reqNominalperbid;
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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setReqWaktuperbid(String reqWaktuperbid){
		this.reqWaktuperbid = reqWaktuperbid;
	}

	public String getReqWaktuperbid(){
		return reqWaktuperbid;
	}

	public void setGchatId(String gchatId){
		this.gchatId = gchatId;
	}

	public String getGchatId(){
		return gchatId;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"req_nominalperbid = '" + reqNominalperbid + '\'' + 
			",harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",hewan_id = '" + hewanId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",req_waktuperbid = '" + reqWaktuperbid + '\'' + 
			",gchat_id = '" + gchatId + '\'' + 
			"}";
		}
}