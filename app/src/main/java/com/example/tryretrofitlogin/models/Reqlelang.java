package com.example.tryretrofitlogin.models;

import java.io.Serializable;

public class Reqlelang implements Serializable {
    private String id;
    private String user_id;
    private String pengirim_id;
    private String lelang_id;

    public Reqlelang(String id, String user_id, String pengirim_id, String lelang_id) {
        this.id = id;
        this.user_id = user_id;
        this.pengirim_id = pengirim_id;
        this.lelang_id = lelang_id;
    }

    public Reqlelang(String user_id, String pengirim_id, String lelang_id) {
        this.user_id = user_id;
        this.pengirim_id = pengirim_id;
        this.lelang_id = lelang_id;
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

    public String getPengirim_id() {
        return pengirim_id;
    }

    public void setPengirim_id(String pengirim_id) {
        this.pengirim_id = pengirim_id;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public void setLelang_id(String lelang_id) {
        this.lelang_id = lelang_id;
    }
}
