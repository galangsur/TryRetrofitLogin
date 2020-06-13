package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GroupchatActivity extends AppCompatActivity {

    private ImageButton sendImgbtn;
    private EditText inputEdtxt;
    private ScrollView myScrollview;
    private TextView displayTxt;
    private String currentDate,currentGroupid,currentTime,msgKey, currentUserid,currentnameUser;
    private DatabaseReference groupRef,groupmsgKeyref;

    private final List<Message> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Messageadapt messageadapt;
    private RecyclerView userMessagelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        groupRef = FirebaseDatabase.getInstance().getReference().child("Groups").child("yeye");
        currentUserid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        currentnameUser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

        sendImgbtn = (ImageButton) findViewById(R.id.send_imgbtn);
        inputEdtxt = (EditText) findViewById(R.id.input_edtxt);

        messageadapt = new Messageadapt(messageList);
        userMessagelist = (RecyclerView) findViewById(R.id.rv_messagelist);
        linearLayoutManager = new LinearLayoutManager(this);
        userMessagelist.setLayoutManager(linearLayoutManager);
        userMessagelist.setAdapter(messageadapt);



    }

    @Override
    protected void onStart() {
        super.onStart();

        groupRef.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                    messageadapt.notifyDataSetChanged();
                    userMessagelist.smoothScrollToPosition(userMessagelist.getAdapter().getItemCount());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
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

        sendImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMsg();
            }
        });
    }

    private void Displaymessage(DataSnapshot dataSnapshot) {
        Iterator iterator = dataSnapshot.getChildren().iterator();

        while (iterator.hasNext()){
            String chtFromid = (String) ((DataSnapshot)iterator.next()).getValue();
            String chtDate = (String) ((DataSnapshot)iterator.next()).getValue();
            String chtMessage = (String) ((DataSnapshot)iterator.next()).getValue();
            String chtTime = (String) ((DataSnapshot)iterator.next()).getValue();
            String chtFromname = (String) ((DataSnapshot)iterator.next()).getValue();


            displayTxt.append("From :"+chtDate + chtMessage +"\n"
                    + chtTime +"\n"
                    + chtFromname + chtFromid +"\n");

            myScrollview.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }

    private void saveMsg(){
        String msg = inputEdtxt.getText().toString().trim();
        String msgKey = groupRef.push().getKey();
        String fromId = currentUserid.trim();
        String fromName= currentnameUser.trim();

        if (TextUtils.isEmpty(msg)){
            sendImgbtn.setEnabled(false);
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

    private void saveMessage(){
        String msgTxt = inputEdtxt.getText().toString();

        if (TextUtils.isEmpty(msgTxt)){
            Toast.makeText(this, "ketik pesan bor", Toast.LENGTH_SHORT).show();
        }else {
        }
    }
}
