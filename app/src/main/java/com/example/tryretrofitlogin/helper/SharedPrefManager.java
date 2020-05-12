package com.example.tryretrofitlogin.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.Call;

import com.example.tryretrofitlogin.models.User;
import com.example.tryretrofitlogin.models.Wallet;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mySPref";

    private static final String KEY_WALLET_ID = "keywalletid";
    private static final String KEY_WALLET_USER_ID = "keywalletuserid";
    private static final String KEY_WALLET_SALDO = "keywalletsaldo";
    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_TOKEN = "keyusertoken";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_PASSWORD = "keyuserpassword";
    private static final String KEY_USER_CPASSWORD = "keyusercpassword";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String id,String token, String name, String email) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, id);
        editor.putString(KEY_USER_TOKEN, token);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
        return true;
    }

    public boolean walletInfo(int id, int userid, String saldo) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_WALLET_ID, id);
        editor.putInt(KEY_WALLET_USER_ID,userid);
        editor.putString(KEY_WALLET_SALDO,saldo);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EMAIL, null) != null)
            return true;
        return false;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null),
                sharedPreferences.getString(KEY_USER_PASSWORD, null),
                sharedPreferences.getString(KEY_USER_CPASSWORD,null)
        );
    }

    public User getUserProfile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USER_ID,null),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null)
        );
    }

    public Wallet getWalletInfo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Wallet(
                sharedPreferences.getString(KEY_WALLET_SALDO, null)
        );
    }

    public Wallet sendwalletinfo (int user_id, String saldo){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(KEY_WALLET_USER_ID,user_id);
                editor.putString(KEY_WALLET_SALDO,saldo);
                editor.apply();
        return sendwalletinfo(user_id,saldo);
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
