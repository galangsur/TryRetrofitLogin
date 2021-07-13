package com.example.tryretrofitlogin.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import com.example.tryretrofitlogin.adapter.EndlessScrolladapt;
import com.example.tryretrofitlogin.adapter.Messageadapt;
import com.example.tryretrofitlogin.api.APIService;
import com.example.tryretrofitlogin.api.APIUrl;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Harga;
import com.example.tryretrofitlogin.models.Message;
import com.example.tryretrofitlogin.responses.getlelbrjalanbygc.GetlelbrjalanbygcResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GroupchatActivity extends AppCompatActivity {

    private ImageButton sendImgbtn;
    private EditText inputEdtxt,hargaInpt;
    private Button bidsatuBid, bidduaBid, bidtigaBid, btnListpeserta;
    private TextView timeTxt,gcidTxt,hargaOutpt;
    private String currentDate,currentTime,currentUserid,currentnameUser,gcid,harga,hrgbid;
    private String lberkey,lberuserid, lberhewanid, lberharga, lbercomment;
    private int hargaAwallelang, bidInput,lberreqwaktu, lberreqnominal;
    private DatabaseReference groupRef,groupmsgKeyref,hargaRef,lelhargaKeyref;

    private final List<Message> messageList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Messageadapt messageadapt;
    private RecyclerView userMessagelist;
    private SwipeRefreshLayout mRefreshlay;

    private static final int TOTAL_ITEM_EACH_LOAD = 9;
    private int currentPage = 1;
    private int hargatampil,hargaperbid,waktuperbid;

    private CountDownTimer countDownTimer;
    private static long timeleftinmilliSeconds;
//    private long timeleftinmilliSeconds = START_TIME_IN_MILLIS;
//    private static final long START_TIME_IN_MILLIS = 7200000; //nanti pilihanny ada 3 : 30detik = 30000, 45 detik = 45000, 15detik = 15000
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
        mRefreshlay = (SwipeRefreshLayout)findViewById(R.id.messageswipe);
        btnListpeserta = (Button) findViewById(R.id.btn_listpeserta);

        //bidduaBid = (Button) findViewById(R.id.bid_dua);
        //bidtigaBid = (Button) findViewById(R.id.bid_tiga);


        Intent intenthewan = getIntent();
        gcid = intenthewan.getStringExtra("gchattoken");
        gcidTxt.setText(gcid);
//        Toast.makeText(this, gcid, Toast.LENGTH_SHORT).show();

        gcwaktudannominal();

        groupRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(gcid);
        hargaRef = FirebaseDatabase.getInstance().getReference().child("LelHargaMng").child(gcid);
        currentUserid = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getId();
        currentnameUser = SharedPrefManager.getInstance(getApplicationContext()).getUserProfile().getName();

//        sendImgbtn = (ImageButton) findViewById(R.id.send_imgbtn);
//        inputEdtxt = (EditText) findViewById(R.id.input_edtxt);



        linearLayoutManager = new LinearLayoutManager(this);
        userMessagelist = (RecyclerView) findViewById(R.id.rv_messagelist);
        userMessagelist.setLayoutManager(linearLayoutManager);
        userMessagelist.setNestedScrollingEnabled(false);

        messageadapt = new Messageadapt(messageList);
        userMessagelist.setAdapter(messageadapt);

        displayMsg();
//        displayMsgPagination();
        startTime();
        displayHrga();
//        gethargaawal();

//        mRefreshlay.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                currentPage++;
//
//                displayMsg();
//            }
//        });

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
                computeBid();
                saveBid();
//                resetTimer();
            }
        });

        btnListpeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

//    private void displayMsgPagination() {
//        if(!isMaxData){
//            Query query;
//            if (TextUtils.isEmpty(last_node))
//                query = FirebaseDatabase.getInstance().getReference()
//                        .child(gcid)
//                        .orderByKey()
//                        .limitToFirst(ITEM_LOAD_COUNT);
//            else
//                query = FirebaseDatabase.getInstance().getReference()
//                        .child(gcid)
//                        .orderByKey()
//                        .startAt(last_node)
//                        .limitToFirst(ITEM_LOAD_COUNT);
//
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.hasChildren()){
//                        for(DataSnapshot msgsnpsht:snapshot.getChildren()){
//                            messageList.add(msgsnpsht.getValue(Message.class));
//                        }
//                        last_node = messageList.get(messageList.size() -1).getMsgId();
//
//                        if (!last_node.equals(last_key))
//                            messageList.remove(messageList.size() -1);
//                        else last_node = "end";
//
//                        messageadapt.addAll(messageList);
//                        isLoading = false;
//                    } else {
//                        isLoading = false;
//                        isMaxData = true;
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    isLoading = false;
//                }
//            });
//        }
//    }

    private void loadData() {
        // example
        // at first load : currentPage = 0 -> we startAt(0 * 10 = 0)
        // at second load (first loadmore) : currentPage = 1 -> we startAt(1 * 10 = 10)
        groupRef.limitToFirst(TOTAL_ITEM_EACH_LOAD)
                .startAt(currentPage*TOTAL_ITEM_EACH_LOAD)
                .orderByChild("time")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.hasChildren()){
                            Toast.makeText(GroupchatActivity.this, "No more questions", Toast.LENGTH_SHORT).show();
                            currentPage--;
                        }
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Message question = data.getValue(Message.class);
                            messageList.add(question);
                            messageadapt.notifyDataSetChanged();
                        }
                    }

                    @Override public void onCancelled(DatabaseError databaseError) {}});
    }

//    private void loadMoreData(){
//        currentPage++;
//        loadData();
//    }

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
            msgInfomap.put("msgId",bidmsgKey);
            groupmsgKeyref.updateChildren(msgInfomap);

        String valuetoSend = Integer.toString(bidInput);
        hargaRef.child("harga").setValue(valuetoSend);

    }

    private void displayMsg(){

        Query bidQuery = groupRef.limitToLast(TOTAL_ITEM_EACH_LOAD);

        bidQuery.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    Message message = dataSnapshot.getValue(Message.class);

                    messageList.add(message);
                    messageadapt.notifyDataSetChanged();

                    userMessagelist.scrollToPosition(messageList.size() -1);
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
                hargatampil = Integer.parseInt(inpt);

                //nampilin di textview isi Rp.
                Locale localID = new Locale("in","ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
                hargaOutpt.setText(formatRupiah.format((double)hargatampil));
//                hargaOutpt.setText(inpt);



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
//                getlastItem();
                bidsatuBid.setVisibility(View.INVISIBLE);
//                bidduaBid.setVisibility(View.INVISIBLE);
//                bidtigaBid.setVisibility(View.INVISIBLE);
            }
        }.start();
        timerRunning = true;
    }

    private void resetTimer(){
        countDownTimer.cancel();
        timeleftinmilliSeconds = waktuperbid;
        updateCountdowntxt();
        startTime();
    }

    private void updateCountdowntxt(){
        int hour = (int) (timeleftinmilliSeconds/100000) / 60;
        int minutes = (int) (timeleftinmilliSeconds/600) / 200;
        int seconds = (int) (timeleftinmilliSeconds/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hour,minutes,seconds);
        timeTxt.setText(timeLeftFormatted);
    }

    private void computeBid(){
//        final String s = hargatxt.getText().toString().trim();
//        final int har = Integer.parseInt(s);

        int hargatambah = hargaperbid; //nanti ini ada pilihan. 500k , 250k, 100k
        hargaAwallelang = hargaAwallelang + hargatambah;
        Toast.makeText(this, String.valueOf(hargaAwallelang), Toast.LENGTH_SHORT).show();
    }

    private void gcwaktudannominal(){
        lberkey = gcidTxt.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<GetlelbrjalanbygcResponse> call = service.getlelbrjalanbygc(
                lberkey, lberuserid, lberhewanid, lberharga, lbercomment, lberreqwaktu, lberreqnominal);

        call.enqueue(new Callback<GetlelbrjalanbygcResponse>() {
            @Override
            public void onResponse(Call<GetlelbrjalanbygcResponse> call, Response<GetlelbrjalanbygcResponse> response) {
                if (response.isSuccessful()){
                    hargaperbid = response.body().getSuccess().getReqNominalperbid();
                    waktuperbid = response.body().getSuccess().getReqWaktuperbid();
                }
            }
            @Override
            public void onFailure(Call<GetlelbrjalanbygcResponse> call, Throwable t) {

            }
        });
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
