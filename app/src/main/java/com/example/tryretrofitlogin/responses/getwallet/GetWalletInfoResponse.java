package com.example.tryretrofitlogin.responses.getwallet;

import com.google.gson.annotations.SerializedName;

public class GetWalletInfoResponse{

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	@SerializedName("saldo")
	private int saldo;

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSaldo(int saldo){
		this.saldo = saldo;
	}

	public int getSaldo(){
		return saldo;
	}

	@Override
 	public String toString(){
		return 
			"GetWalletInfoResponse{" + 
			"user_id = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",saldo = '" + saldo + '\'' + 
			"}";
		}
}