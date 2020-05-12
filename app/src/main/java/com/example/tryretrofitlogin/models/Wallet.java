package com.example.tryretrofitlogin.models;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Wallet implements Serializable {
    private String id, user_id;
    private String saldo;

    public Wallet(String saldo) {
        this.saldo = saldo;
    }

    public Wallet(String id, String user_id, String saldo) {
        this.id = id;
        this.user_id = user_id;
        this.saldo = saldo;
    }

    public Wallet(String user_id, String saldo) {
        this.user_id = user_id;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
