package com.example.tryretrofitlogin.responses.getimgbyparent;

import com.google.gson.annotations.SerializedName;

public class SuccessItem{

	@SerializedName("imgparent")
	private String imgparent;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("photo")
	private String photo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setImgparent(String imgparent){
		this.imgparent = imgparent;
	}

	public String getImgparent(){
		return imgparent;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
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

	@Override
 	public String toString(){
		return 
			"SuccessItem{" + 
			"imgparent = '" + imgparent + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",photo = '" + photo + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}