package com.example.tryretrofitlogin.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.Call;

import com.example.tryretrofitlogin.models.Lelang;
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
    private static final String KEY_USER_NIKKTP = "keyusernikktp";
    private static final String KEY_USER_REVIEWNRATINGTOKEN = "keyuserrnr";
    private static final String KEY_USER_TLP = "keyuseretlp";
    private static final String KEY_USER_PASSWORD = "keyuserpassword";
    private static final String KEY_USER_CPASSWORD = "keyusercpassword";
    private static final String KEY_REQ_ID = "keyreqid";
    private static final String KEY_ID_PENGIRIM = "keyuserpengirim";
    private static final String KEY_LELANG_ID = "keylelangid";
    private static final String KEY_STARTIMEINMILLIS = "mStartTimeInMillis";
    private static final String KEY_TIMELEFTINMILLIS = "mTimeLeftInMillis";
    private static final String KEY_TIMERRUNNING = "mTimerRunning";
    private static final String KEY_ENDTIME = "mEndTime";

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

    public boolean countdowntimerPref(long mStartTimeInMillis,long mTimeLeftInMillis,boolean mTimerRunning,long mEndTime){
        SharedPreferences timersharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor timereditor = timersharedPreferences.edit();
        timereditor.putLong(KEY_STARTIMEINMILLIS, mStartTimeInMillis);
        timereditor.putLong(KEY_TIMELEFTINMILLIS, mTimeLeftInMillis);
        timereditor.putBoolean(KEY_TIMERRUNNING, mTimerRunning);
        timereditor.putLong(KEY_ENDTIME, mEndTime);
        timereditor.apply();
        return true;
    }

    public boolean sendLelanginfo (int id, int user_id, int pengirim_id, int lelang_id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_REQ_ID,id);
        editor.putInt(KEY_USER_ID,user_id);
        editor.putInt(KEY_ID_PENGIRIM,pengirim_id);
        editor.putInt(KEY_LELANG_ID,lelang_id);
        editor.apply();
        return true;
    }

    public User getUserProfile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USER_ID,null),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
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
                sharedPreferences.getString(KEY_USER_TLP, null),
                sharedPreferences.getString(KEY_USER_PASSWORD, null),
                sharedPreferences.getString(KEY_USER_CPASSWORD,null),
                sharedPreferences.getString(KEY_USER_NIKKTP,null),
                sharedPreferences.getString(KEY_USER_REVIEWNRATINGTOKEN,null)
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
}
