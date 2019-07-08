package com.example.coba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import java.util.HashMap;

public class SessionManager {


    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    public static final String NAMA_USER ="user" ;
    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ID_USER1 = "id_user";
    public static final String LEVEL = "level";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    public void createSession(String username, String nomor,String no, String tingkat){
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, nomor);
        editor.putString(ID_USER1, no);
        editor.putString(LEVEL, tingkat);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
            ((Home)context).finish();
        }
    }
    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(ID_USER1, sharedPreferences.getString(ID_USER1, null));
        user.put(LEVEL, sharedPreferences.getString(LEVEL, null));
        return user;
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        ((MainActivity)context).finish();
    }
}