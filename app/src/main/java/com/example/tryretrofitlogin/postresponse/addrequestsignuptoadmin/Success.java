package com.example.tryretrofitlogin.postresponse.addrequestsignuptoadmin;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("password")
	private String password;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("tlp")
	private String tlp;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("nikktp")
	private String nikktp;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

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

	public void setNikktp(String nikktp){
		this.nikktp = nikktp;
	}

	public String getNikktp(){
		return nikktp;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"password = '" + password + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",tlp = '" + tlp + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' +
			",nikktp = '" + nikktp + '\'' +
			"}";
		}
}