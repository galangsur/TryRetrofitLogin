package com.example.tryretrofitlogin.responses.getalldatauserbyid;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("tlp")
	private String tlp;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("nikktp")
	private String nikktp;

	@SerializedName("ratingnreview_token")
	private String ratingnreview_token;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setTlp(String tlp){
		this.tlp = tlp;
	}

	public String getTlp(){
		return tlp;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setNikktp(String nikktp) {
		this.nikktp = nikktp;
	}

	public String getNikktp() {
		return nikktp;
	}

	public void setRatingnreview_token(String ratingnreview_token) {
		this.ratingnreview_token = ratingnreview_token;
	}

	public String getRatingnreview_token() {
		return ratingnreview_token;
	}


	@Override
 	public String toString(){
		return 
			"Success{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",tlp = '" + tlp + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' +
			",nikktp = '" + nikktp + '\'' +
			",ratingnreview_token = '" + ratingnreview_token + '\'' +
			"}";
		}
}