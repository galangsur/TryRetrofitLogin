package com.example.tryretrofitlogin.adapter;

import android.content.Context;

import com.example.tryretrofitlogin.responses.getlistleltransbypeserta.SuccessItem;

import java.util.ArrayList;

public class ListleltransAdapt {
    ArrayList<SuccessItem> listlelbypeseraresult;
    Context listleltransContext;

    public ListleltransAdapt(Context listleltransContext, ArrayList<SuccessItem> listlelbypeseraresult){
        super();
        this.listleltransContext = listleltransContext;
        this.listlelbypeseraresult = listlelbypeseraresult;
    }
}
