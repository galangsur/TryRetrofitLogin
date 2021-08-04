package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.fcmservices.Constant;
import com.example.tryretrofitlogin.fcmservices.IFCMServices;
import com.example.tryretrofitlogin.fcmservices.MyFirebaseMessagingService;
import com.example.tryretrofitlogin.fcmservices.Notification;
import com.example.tryretrofitlogin.fcmservices.PushNotification;
import com.example.tryretrofitlogin.fcmservices.ResponseMsg;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TryNotificationActivity extends AppCompatActivity {
    private Button btnnotification,publish,unpublish;
    private RequestQueue mRequest;
    private String notifURL = "https://fcm.googleapis.com/fcm/send";
    private String messageBody = "Ayo Segera Ikut Serta Dalam Lelang";
    private TextView txtToken;
    private EditText edttxtTittle,edttxtContent;
    private static final String TAG = "Try";
    private static final String TOPIC = "/topics/deals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_notification);
        mRequest = Volley.newRequestQueue(this);
        txtToken = findViewById(R.id.tokentext);
        edttxtTittle = findViewById(R.id.tittlemsg);
        edttxtContent = findViewById(R.id.contentmsg);
        btnnotification = findViewById(R.id.btnnotif);
        publish = findViewById(R.id.subcribepublish);
        unpublish = findViewById(R.id.unsubpublish);

        Intent intentbackgroundservice = new Intent(TryNotificationActivity.this, MyFirebaseMessagingService.class);
        startService(intentbackgroundservice);

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/"+"deal")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = ("Subscribed");
                        if (!task.isSuccessful()) {
                            msg = "Couldn't subscribe to topic";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(TryNotificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        btnnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendNotif();
                returnfcmtoken();
                edttxtContent.setText(returnfcmtoken());
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfcmtoken2();
//                FirebaseMessaging.getInstance().subscribeToTopic("alucrod").
//                        addOnCompleteListener(task -> {
//                            if (task.isSuccessful())
//                                Toast.makeText(TryNotificationActivity.this, "publish", Toast.LENGTH_SHORT).show();
//                            else
//                                Toast.makeText(TryNotificationActivity.this, "unpub", Toast.LENGTH_SHORT).show();
//                        });
            }
        });

        unpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("alucrod").
                        addOnCompleteListener(task -> {
                            if (task.isSuccessful())
                                Toast.makeText(TryNotificationActivity.this, "unpub", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(TryNotificationActivity.this, "pub", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }

    private void sendNotif(){
        String title = edttxtTittle.getText().toString();
        String content = edttxtContent.getText().toString();
        String topic = "deal";
        String toRedmi3 = "dpH-AgQyTbGuDRGH2qZMQT:APA91bGVeENY_lPBPh2BTYVlAcAiRcyJr2eYx7g-5JyZr64i7ZpkggpFjWOiB1Mo-UzL4g23X3KPBbroDvgnJU_CaeAy9YnTRqGyMrnoihMvtvIhKA3zz5Pl8WAe_ZL-YChA1VJWKjiM";
        //jadi kalau untuk ke 1 device, value ke dua dari PushNotification diganti FCMtoken device seperti toRedmi3 itu

        if(!title.isEmpty() && !content.isEmpty()) {
            Notification message = new Notification(title, content);
            PushNotification data = new PushNotification(message, (toRedmi3));//Topic ini di ganti ke toRedmi3 kalau ingin ke 1 device tertentu
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.FCMBASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IFCMServices apiService =
                    retrofit.create(IFCMServices.class);
            Call<ResponseMsg> call = apiService.postNotification(data);
            call.enqueue(new Callback<ResponseMsg>() {
                @Override
                public void onResponse(Call<ResponseMsg> call, retrofit2.Response<ResponseMsg> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, String.valueOf(response.code()));
                        return;
                    }
                    edttxtTittle.setText("");
                    edttxtContent.setText("");
                    Toast.makeText(TryNotificationActivity.this, "Message Pushed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseMsg> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                }
            });
        }
    }

    public static String returnfcmtoken(){
        final String[] token = {""};
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isComplete()){
                    token[0] = task.getResult();
                    Log.i("AppConstants", "onComplete: new Token got: TOKEN here "+token[0] );
                }
            }
        });
        return token[0];
    }

    private void getfcmtoken(){
        String token = MyFirebaseMessagingService.getToken(TryNotificationActivity.this);
        edttxtContent.setText(token);
        Log.i("AppConstants", "onComplete: new Token got: TOKEN here "+token );
    }

    private void getfcmtoken2(){
        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(task -> {
            String token = task.getResult().getToken();
            edttxtContent.setText(token);
            Log.i("AppConstants", "onComplete: new Token got: TOKEN here "+token );
        });

    }

    private void notification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
    }

//    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.project_id);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.usericon)
//                        .setContentTitle("Lelang Baru Telah Dibuka")
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
}