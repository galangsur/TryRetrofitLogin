package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.adapter.Messageadapt;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GroupchatActivity extends AppCompatActivity {

    private ImageButton sendImgbtn;
    private EditText inputEdtxt;
    private Button bidsatuBid, bidduaBid, bidtigaBid;
    private TextView timeTxt;
    private String currentDate,currentTime,currentUserid,currentnameUser;
    private int hargaAwallelang, bidInput;
    private DatabaseReference groupRef,groupmsgKeyref;

    private final List<Message> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Messageadapt messageadapt;
    private RecyclerView userMessagelist;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CountDownTimer countDownTimer;
    private long timeleftinmilliSeconds = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 30000;
    private boolean timerRunning;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        groupRef = FirebaseDatabase.getInstance().getReference().child("Groups").child("yeye");
        currentUserid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        currentnameUser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

//        sendImgbtn = (ImageButton) findViewById(R.id.send_imgbtn);
//        inputEdtxt = (EditText) findViewById(R.id.input_edtxt);
        timeTxt = (TextView) findViewById(R.id.timer_txt);
        bidsatuBid = (Button) findViewById(R.id.bid_satu);
        bidduaBid = (Button) findViewById(R.id.bid_dua);
        bidtigaBid = (Button) findViewById(R.id.bid_tiga);

        messageadapt = new Messageadapt(messageList);
        userMessagelist = (RecyclerView) findViewById(R.id.rv_messagelist);
        userMessagelist.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(this);
        userMessagelist.setLayoutManager(linearLayoutManager);
        userMessagelist.setAdapter(messageadapt);

        hargaAwallelang = 1000;

        displayMsg();
        startTime();
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayMsg();

        sendImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bidsatuBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeBid1K();
                resetTimer();
            }
        });

        bidduaBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeBid2K();
            }
        });

        bidtigaBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeBid3K();
            }
        });



    }

    private void saveMsg(){
        String msg = inputEdtxt.getText().toString().trim();
        String msgKey = groupRef.push().getKey();
        String fromId = currentUserid.trim();
        String fromName= currentnameUser.trim();

        if (TextUtils.isEmpty(msg)){
            inputEdtxt.setVisibility(View.INVISIBLE);
        }else {
            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDformat = new SimpleDateFormat("MMM dd,yyyy");
            currentDate = currentDformat.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTformat = new SimpleDateFormat("hh:mm");
            currentTime = currentTformat.format(calForTime.getTime());

            HashMap<String, Object> groupmsgKey = new HashMap<>();
            groupRef.updateChildren(groupmsgKey);

            groupmsgKeyref = groupRef.child(msgKey);

            HashMap<String, Object> msgInfomap = new HashMap<>();
                msgInfomap.put("fromName",fromName);
                msgInfomap.put("fromId",fromId);
                msgInfomap.put("message", msg);
                msgInfomap.put("date",currentDate);
                msgInfomap.put("time",currentTime);
            groupmsgKeyref.updateChildren(msgInfomap);
        }
    }

    private void saveBid(){
        bidInput = hargaAwallelang;
        String msgKey = groupRef.push().getKey();
        String fromId = currentUserid.trim();
        String fromName= currentnameUser.trim();

            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDformat = new SimpleDateFormat("MMM dd,yyyy");
            currentDate = currentDformat.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTformat = new SimpleDateFormat("hh:mm");
            currentTime = currentTformat.format(calForTime.getTime());

            HashMap<String, Object> groupmsgKey = new HashMap<>();
            groupRef.updateChildren(groupmsgKey);

            groupmsgKeyref = groupRef.child(msgKey);

            HashMap<String, Object> msgInfomap = new HashMap<>();
            msgInfomap.put("fromName",fromName);
            msgInfomap.put("fromId",fromId);
            msgInfomap.put("value", bidInput + "");
            msgInfomap.put("date",currentDate);
            msgInfomap.put("time",currentTime);
            groupmsgKeyref.updateChildren(msgInfomap);
    }

    private void displayMsg(){
        groupRef.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                    userMessagelist.smoothScrollToPosition(userMessagelist.getAdapter().getItemCount());
                    Messageadapt messageadapt = new Messageadapt(messageList);
                    userMessagelist.setAdapter(messageadapt);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getValue() !=null){
                    messageadapt.notifyDataSetChanged();
                    Messageadapt messageadapt = new Messageadapt(messageList);
                    userMessagelist.setAdapter(messageadapt);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getlastItem(){
        DatabaseReference itemref = FirebaseDatabase.getInstance().getReference().child("Groups");
        Query lastQuery = itemref.child("yeye").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.getKey();
                    Toast.makeText(GroupchatActivity.this, "name" + name, Toast.LENGTH_SHORT).show();
                    Intent resLel = new Intent(GroupchatActivity.this,LelangResultActivity.class);
                    resLel.putExtra("messageId", name);
                    startActivity(resLel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void startTime(){
        countDownTimer = new CountDownTimer(timeleftinmilliSeconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinmilliSeconds = millisUntilFinished;
                updateCountdowntxt();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                Toast.makeText(GroupchatActivity.this, "waktu habis", Toast.LENGTH_SHORT).show();
                getlastItem();
                bidsatuBid.setVisibility(View.INVISIBLE);
                bidduaBid.setVisibility(View.INVISIBLE);
                bidtigaBid.setVisibility(View.INVISIBLE);
            }
        }.start();
        timerRunning = true;
    }

    private void resetTimer(){
        countDownTimer.cancel();
        timeleftinmilliSeconds = START_TIME_IN_MILLIS;
        updateCountdowntxt();
        startTime();
    }

    private void updateCountdowntxt(){
        int minutes = (int) (timeleftinmilliSeconds/1000) / 60;
        int seconds = (int) (timeleftinmilliSeconds/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        timeTxt.setText(timeLeftFormatted);
    }

    private void swipeRefresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    Messageadapt messageadapt = new Messageadapt(messageList);
                    userMessagelist.setAdapter(messageadapt);
            }
        });
    }

    private void computeBid1K(){
        int hargatambah500 = 500000;
        hargaAwallelang = hargaAwallelang + hargatambah500;
        Toast.makeText(this, String.valueOf(hargaAwallelang), Toast.LENGTH_SHORT).show();
    }

    private void computeBid2K(){
        int hargatambah500 = 500000;
        hargaAwallelang = hargaAwallelang + hargatambah500;
        Toast.makeText(this, String.valueOf(hargaAwallelang), Toast.LENGTH_SHORT).show();
    }

    private void computeBid3K(){
        int hargatambah500 = 500000;
        hargaAwallelang = hargaAwallelang + hargatambah500;
        Toast.makeText(this, String.valueOf(hargaAwallelang), Toast.LENGTH_SHORT).show();
    }

}
