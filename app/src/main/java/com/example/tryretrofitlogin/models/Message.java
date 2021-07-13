package com.example.tryretrofitlogin.models;

public class Message {
    private String date, fromId,fromName,message, time, gcId, msgId;
//    private int message;

    public Message(String date, String fromId, String fromName, String message, String time) {
        this.date = date;
        this.fromId = fromId;
        this.fromName = fromName;
        this.message = message;
        this.time = time;
    }

    public Message(String date, String fromId, String fromName, String message, String time, String gcId) {
        this.date = date;
        this.fromId = fromId;
        this.fromName = fromName;
        this.message = message;
        this.time = time;
        this.gcId = gcId;
    }

    public Message(String date, String fromId, String fromName, String message, String time, String gcId, String msgId) {
        this.date = date;
        this.fromId = fromId;
        this.fromName = fromName;
        this.message = message;
        this.time = time;
        this.gcId = gcId;
        this.msgId = msgId;
    }

    public Message() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGcId() {
        return gcId;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
