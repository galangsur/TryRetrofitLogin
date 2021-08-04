package com.example.tryretrofitlogin.models;

public class PesertaOnlineStatus {
    private String peserta_token,status;

    public PesertaOnlineStatus(String peserta_token, String status) {
        this.peserta_token = peserta_token;
        this.status = status;
    }

    public String getPeserta_token() {
        return peserta_token;
    }

    public void setPeserta_token(String peserta_token) {
        this.peserta_token = peserta_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
