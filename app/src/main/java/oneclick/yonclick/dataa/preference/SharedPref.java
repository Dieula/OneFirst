package oneclick.yonclick.dataa.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import oneclick.yonclick.activity.MainActivity;

public class SharedPref {
    //Storage File
    public static final String SHARED_PREF_NAME = "yonclick";

    //Username
    public static final String USER_NAME = "username";

    public static SharedPref mInstance;

    public static Context mCtx;


    public SharedPref(Context context) {
        mCtx = context;
    }


    public static synchronized SharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPref(context);
        }
        return mInstance;
    }


    //method to store user data
    public void storeUserName(String names,String email,String imei) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, names);
        editor.putString("SESSION_ID","SESSION_ID");
        editor.putString("nom_client",names);
        editor.putString("email_client", email);
        editor.putString("imei", imei);
        editor.putString("imei", imei);
        editor.commit();
    }

    //check if user is logged in
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null) != null;
    }


    //find logged in user
    public String LoggedInUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null);

    }


    //Logout user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }


}
