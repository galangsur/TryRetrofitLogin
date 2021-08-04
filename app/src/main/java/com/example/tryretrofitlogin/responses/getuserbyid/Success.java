package com.example.tryretrofitlogin.responses.getuserbyid;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("ratingnreview_token")
	private String ratingnreviewToken;

	@SerializedName("name")
	private String name;

	@SerializedName("tlp")
	private String tlp;

	@SerializedName("nikktp")
	private String nikktp;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setRatingnreviewToken(String ratingnreviewToken){
		this.ratingnreviewToken = ratingnreviewToken;
	}

	public String getRatingnreviewToken(){
		return ratingnreviewToken;
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

	public void setNikktp(String nikktp){
		this.nikktp = nikktp;
	}

	public String getNikktp(){
		return nikktp;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",ratingnreview_token = '" + ratingnreviewToken + '\'' + 
			",name = '" + name + '\'' + 
			",tlp = '" + tlp + '\'' + 
			",nikktp = '" + nikktp + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}