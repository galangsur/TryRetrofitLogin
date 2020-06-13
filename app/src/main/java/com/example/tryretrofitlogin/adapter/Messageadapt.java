package com.example.tryretrofitlogin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryretrofitlogin.R;
import com.example.tryretrofitlogin.helper.SharedPrefManager;
import com.example.tryretrofitlogin.models.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Messageadapt extends RecyclerView.Adapter<Messageadapt.MessageViewHolder> {
    private List<Message> userMessageList;
    private String userRef, currentUserId, currentUserName, senderUserId;
    private Context mCtx;
    private DatabaseReference adptuserRef;

    public Messageadapt (List<Message> userMessageList){
        this.userMessageList = userMessageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_chat_layout,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, int position) {
        Message message = userMessageList.get(position);

        String fromUserid = message.getFromId();
        Log.d("fromfirebase", "id" + fromUserid);

        currentUserId = SharedPrefManager.getInstance(mCtx).getUserProfile().getId();
        String userRef = currentUserId.trim();
        Log.d("fromshared", "id" + userRef);


        if (fromUserid.equals(userRef)){
            holder.recieverMessagetxt.setVisibility(View.INVISIBLE);
            holder.msgBody.setVisibility(View.INVISIBLE);
            holder.senderMessagetxt.setBackgroundResource(R.drawable.sender_message_layout);
            holder.senderMessagetxt.setText(message.getMessage());
        }else {
            holder.senderMessagetxt.setVisibility(View.INVISIBLE);
            holder.recieverMessagetxt.setBackgroundResource(R.drawable.reciever_message_layout);
            holder.recieverMessagetxt.setText(message.getMessage());
            holder.fromMessagetxt.setText(message.getFromName());
            holder.dateMessagetxt.setText(message.getDate());
            holder.timeMessagetxt.setText(message.getTime());
        }

    }

    @Override
    public int getItemCount() {
        return userMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public TextView senderMessagetxt,recieverMessagetxt,fromMessagetxt,dateMessagetxt,timeMessagetxt;
        public LinearLayout msgBody;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMessagetxt = (TextView) itemView.findViewById(R.id.txt_sendermsg);
            recieverMessagetxt = (TextView) itemView.findViewById(R.id.txt_recievermsg);
            fromMessagetxt = (TextView) itemView.findViewById(R.id.txt_fromname);
            dateMessagetxt = (TextView) itemView.findViewById(R.id.txt_date);
            timeMessagetxt = (TextView) itemView.findViewById(R.id.txt_sendtime);
            msgBody = (LinearLayout) itemView.findViewById(R.id.msg_body);
        }
    }
}
