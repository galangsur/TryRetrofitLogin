package com.example.tryretrofitlogin.fcmservices;

import com.google.gson.annotations.SerializedName;

public class ResponseMsg {
        @SerializedName("message_id")
        String message_id;

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }
}

