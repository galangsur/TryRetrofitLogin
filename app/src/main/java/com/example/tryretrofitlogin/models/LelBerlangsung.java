package com.example.tryretrofitlogin.models;

import java.io.Serializable;

public class LelBerlangsung implements Serializable {

        private String id;
        private String user_id;
        private int hewan_id;
        private String harga;
        private String comment;
        private String gchat_id;



    public LelBerlangsung(String id, String user_id, int hewan_id, String harga, String comment, String gchat) {
            this.id = id;
            this.user_id = user_id;
            this.hewan_id = hewan_id;
            this.harga = harga;
            this.comment = comment;
            this.gchat_id = gchat;
        }

        public LelBerlangsung(String user_id, int hewan_id, String harga, String comment, String gchat) {
            this.user_id = user_id;
            this.hewan_id = hewan_id;
            this.harga = harga;
            this.comment = comment;
            this.gchat_id = gchat;
        }

        public LelBerlangsung(String harga, String comment) {
            this.harga = harga;
            this.comment = comment;
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

        public int getHewan_id() {
            return hewan_id;
        }

        public void setHewan_id(int hewan_id) {
            this.hewan_id = hewan_id;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getGchat() {
        return gchat_id;
    }

        public void setGchat(String gchat) {
        this.gchat_id = gchat;
    }
}

