package com.example.tryretrofitlogin.models;

public class Group {
    private String gcid, gcharga;

    public Group(String gcid, String gcharga) {
        this.gcid = gcid;
        this.gcharga = gcharga;
    }

    public Group(String gcharga) {
    }

    public String getGcid() {
        return gcid;
    }

    public void setGcid(String gcid) {
        this.gcid = gcid;
    }

    public String getGcharga() {
        return gcharga;
    }

    public void setGcharga(String gcharga) {
        this.gcharga = gcharga;
    }
}
