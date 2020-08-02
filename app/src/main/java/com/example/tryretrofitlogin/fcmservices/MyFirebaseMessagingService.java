package com.example.tryretrofitlogin.fcmservices;

import android.app.Notification;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.tryretrofitlogin.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);
        String title;
        String message;
        title = remoteMessage.getData().get("title");

        Log.d("title",title);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText("message")
                .setChannelId("channel_id")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.usericon);
}
}
