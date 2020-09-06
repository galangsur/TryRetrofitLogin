package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.example.tryretrofitlogin.models.Harga;
import com.example.tryretrofitlogin.models.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private EditText inputEdtxt,hargaInpt;
    private Button bidsatuBid, bidduaBid, bidtigaBid;
    private TextView timeTxt,gcidTxt,hargaOutpt;
    private String currentDate,currentTime,currentUserid,currentnameUser,gcid,harga,hrgbid;
    private int hargaAwallelang, bidInput;
    private DatabaseReference groupRef,groupmsgKeyref,hargaRef,lelhargaKeyref;

    private final List<Message> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Messageadapt messageadapt;
    private RecyclerView userMessagelist;
    //    private SwipeRefreshLayout swipeRefreshLayout;

    private CountDownTimer countDownTimer;
    private long timeleftinmilliSeconds = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 30000;
    private boolean timerRunning;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        timeTxt = (TextView) findViewById(R.id.timer_txt);
        bidsatuBid = (Button) findViewById(R.id.bid_satu);
        gcidTxt = (TextView)findViewById(R.id.txtgcid);
        hargaOutpt = (TextView)findViewById(R.id.txthargacounter);
        hargaInpt = (EditText)findViewById(R.id.hargainput);

        //bidduaBid = (Button) findViewById(R.id.bid_dua);
        //bidtigaBid = (Button) findViewById(R.id.bid_tiga);


        Intent intenthewan = getIntent();
        gcid = intenthewan.getStringExtra("gchattoken");
        gcidTxt.setText(gcid);
//        Toast.makeText(this, gcid, Toast.LENGTH_SHORT).show();

        groupRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(gcid);
        hargaRef = FirebaseDatabase.getInstance().getReference().child("LelHargaMng").child(gcid);
        currentUserid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        currentnameUser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

//        sendImgbtn = (ImageButton) findViewById(R.id.send_imgbtn);
//        inputEdtxt = (EditText) findViewById(R.id.input_edtxt);


        messageadapt = new Messageadapt(messageList);
        userMessagelist = (RecyclerView) findViewById(R.id.rv_messagelist);
        userMessagelist.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(this);
        userMessagelist.setLayoutManager(linearLayoutManager);
        userMessagelist.setAdapter(messageadapt);

        displayMsg();
        startTime();
        displayHrga();
//        gethargaawal();

    }

    @Override
    protected void onStart() {
        super.onStart();

//        displayMsg();

//        sendImgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        harga = hargaOutpt.getText().toString().trim();

        bidsatuBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeBid1K();
                saveBid();
//                resetTimer();
            }
        });

    }

    private void saveMsg(){
        String msg = inputEdtxt.getText().toString().trim();
        String msgKey = groupRef.push().getKey();
        String fromId = currentUserid.trim();
        String fromName = currentnameUser.trim();
        String gcId = gcidTxt.getText().toString().trim();

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
                msgInfomap.put("gcId",gcId);
                msgInfomap.put("date",currentDate);
                msgInfomap.put("time",currentTime);
            groupmsgKeyref.updateChildren(msgInfomap);
        }
    }

    private void saveBid(){
        bidInput = hargaAwallelang;
        String bidstring = Integer.toString(bidInput);
        String bidmsgKey = groupRef.push().getKey();
        String bidfromId = currentUserid.trim();
        String bidfromName= currentnameUser.trim();
        String bidgcId = gcidTxt.getText().toString().trim();

            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDformat = new SimpleDateFormat("MMM dd,yyyy");
            currentDate = currentDformat.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTformat = new SimpleDateFormat("hh:mm");
            currentTime = currentTformat.format(calForTime.getTime());

            //untukchildmsg
            HashMap<String, Object> groupmsgKey = new HashMap<>();
            groupRef.updateChildren(groupmsgKey);

            groupmsgKeyref = groupRef.child(bidmsgKey);
            //untukisivaluemsg
            HashMap<String, Object> msgInfomap = new HashMap<>();
            msgInfomap.put("fromName",bidfromName);
            msgInfomap.put("fromId",bidfromId);
            msgInfomap.put("gcId",gcid);
            msgInfomap.put("message", bidInput + "");
            msgInfomap.put("date",currentDate);
            msgInfomap.put("time",currentTime);
            groupmsgKeyref.updateChildren(msgInfomap);

        String valuetoSend = Integer.toString(bidInput);
        hargaRef.child("harga").setValue(valuetoSend);

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
                    resetTimer();
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

    private void displayHrga(){
        DatabaseReference hargal = hargaRef.child("harga");

        hargal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //nampilin di textview
                hargaInpt.setText(snapshot.getValue(String.class));
                String inpt = hargaInpt.getText().toString();
                hargaOutpt.setText(inpt);

                //ubah ke int biar bisa dihitung
                hrgbid = snapshot.getValue(String.class);
                hargaAwallelang = Integer.parseInt(hrgbid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getlastItem(){
        DatabaseReference itemref = FirebaseDatabase.getInstance().getReference().child("Groups");
        Query lastQuery = itemref.child(gcid).orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.getKey();
//                    Toast.makeText(GroupchatActivity.this, "name" + name, Toast.LENGTH_SHORT).show();
                    Intent resLel = new Intent(GroupchatActivity.this,LelangResultActivity.class);
                    resLel.putExtra("messageId", name);
                    resLel.putExtra("groupId",gcid);
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
//                bidduaBid.setVisibility(View.INVISIBLE);
//                bidtigaBid.setVisibility(View.INVISIBLE);
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

    private void computeBid1K(){
//        final String s = hargatxt.getText().toString().trim();
//        final int har = Integer.parseInt(s);

        int hargatambah500 = 500000;
        hargaAwallelang = hargaAwallelang + hargatambah500;
        Toast.makeText(this, String.valueOf(hargaAwallelang), Toast.LENGTH_SHORT).show();
    }

//    private void gethargaawal(){
//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference getharga = dbref.child("LelHargaMng").child(gcid);
//
//        getharga.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                hargatxt.setText(dataSnapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void swipeRefresh(){
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                    Messageadapt messageadapt = new Messageadapt(messageList);
//                    userMessagelist.setAdapter(messageadapt);
//            }
//        });
//    }


}
