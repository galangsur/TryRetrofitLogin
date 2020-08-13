package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.models.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LelangResultActivity extends AppCompatActivity {
    private TextView resltMsgid, resltUserid, resltUsername, resltPelelangid, resltPelelangname;
    private Button resltBtntohome;
    private String messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang_result);

        resltMsgid = (TextView) findViewById(R.id.reslt_messageId);
        resltUserid = (TextView) findViewById(R.id.reslt_userId);
        resltUsername = (TextView) findViewById(R.id.reslt_userName);
        resltBtntohome = (Button) findViewById(R.id.reslt_tohome);

        Intent gcIntent = getIntent();
        messageId = gcIntent.getStringExtra("messageId");
        Toast.makeText(this, "messageid"+ messageId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getresltdetail();

    }

    private void getresltdetail(){
        DatabaseReference resltRef = FirebaseDatabase.getInstance().getReference()
                .child("Groups").child("yeye").child(messageId);
        resltRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String winName = dataSnapshot.getValue(Message.class).getFromName();
                resltUsername.setText(winName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}