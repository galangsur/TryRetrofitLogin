package com.example.tryretrofitlogin.postresponse.addratingnreview;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Success{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("review")
	private String review;

	@SerializedName("nama_reviewer")
	private String nama_reviewer;

	@SerializedName("ratingnreview_token")
	private String ratingnreviewToken;

	@SerializedName("rating")
	private String rating;

	@SerializedName("created_at")
	private Date createdAt;

	@SerializedName("id")
	private int id;

	public void setNama_reviewer(String nama_reviewer) {
		this.nama_reviewer = nama_reviewer;
	}

	public String getNama_reviewer() {
		return nama_reviewer;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setReview(String review){
		this.review = review;
	}

	public String getReview(){
		return review;
	}

	public void setRatingnreviewToken(String ratingnreviewToken){
		this.ratingnreviewToken = ratingnreviewToken;
	}

	public String getRatingnreviewToken(){
		return ratingnreviewToken;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getCreatedAt(){
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
			"Success{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",review = '" + review + '\'' +
			",nama_reviewer = '" + nama_reviewer + '\'' +
			",ratingnreview_token = '" + ratingnreviewToken + '\'' + 
			",rating = '" + rating + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}