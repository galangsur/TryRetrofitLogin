package com.example.tryretrofitlogin.models;

import java.io.Serializable;

public class Hewan implements Serializable {
    private String id, jenis;

    public Hewan(String id, String jenis) {
        this.id = id;
        this.jenis = jenis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public String toString(){
        return
                "Hewan{" +
                        "id = '" + id + '\'' +
                        ",jenis = '" + jenis + '\'' +
                        "}";
    }
}
