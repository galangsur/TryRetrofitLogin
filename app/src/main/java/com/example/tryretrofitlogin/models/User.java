package com.example.tryretrofitlogin.models;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String tlp;
    private String password;
    private String c_password;
    private String nikktp;
    private String ratingnreview_token;

    public User(String name, String email,String tlp, String password, String c_password,String nikktp, String ratingnreview_token) {
        this.name = name;
        this.email = email;
        this.tlp = tlp;
        this.password = password;
        this.c_password = c_password;
        this.nikktp = nikktp;
        this.ratingnreview_token = ratingnreview_token;
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getNikktp() {
        return nikktp;
    }

    public void setNikktp(String nikktp) {
        this.nikktp = nikktp;
    }

    public String getRatingnreviewtoken() {
        return ratingnreview_token;
    }

    public void setRatingnreviewtoken(String ratingnreview_token) {
        this.ratingnreview_token = ratingnreview_token;
    }
}
