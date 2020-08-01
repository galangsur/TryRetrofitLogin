package com.example.tryretrofitlogin.models;

import java.io.Serializable;

public class Photo implements Serializable {
    private String id;
    private String photouri;

    public Photo(String id, String photouri) {
        this.id = id;
        this.photouri = photouri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotouri() {
        return photouri;
    }

    public void setPhotouri(String photouri) {
        this.photouri = photouri;
    }
}
