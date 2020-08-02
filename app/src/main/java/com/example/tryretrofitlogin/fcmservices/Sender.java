package com.example.tryretrofitlogin.fcmservices;

public class Sender {
    public String to;
    public String teks;
    public String title;
    public String notifbody;

    public Sender(String to, String teks, String title, String notifbody) {
        this.to = to;
        this.teks = teks;
        this.title = title;
        this.notifbody = notifbody;
    }

    public Sender() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTeks() {
        return teks;
    }

    public void setTeks(String teks) {
        this.teks = teks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotifbody() {
        return notifbody;
    }

    public void setNotifbody(String notifbody) {
        this.notifbody = notifbody;
    }
}
