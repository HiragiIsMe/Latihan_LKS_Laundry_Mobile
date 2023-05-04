package com.example.latihan_lks_laundry_mobile;

import android.content.Context;
import android.content.SharedPreferences;
public class Session {

    private SharedPreferences sharedPreferences;

    public Session(Context ctx){
        sharedPreferences = ctx.getSharedPreferences("my-data", Context.MODE_PRIVATE);
    }

    public void setEmployee(int id, String name, String email){
        sharedPreferences.edit().putInt("id", id).commit();
        sharedPreferences.edit().putString("name", name).commit();
        sharedPreferences.edit().putString("email", email).commit();
    }

    public int getId() {
        return sharedPreferences.getInt("id", 0);
    }
    public String getName() {
        return  sharedPreferences.getString("name", "");
    }
    public String getEmail() {
        return sharedPreferences.getString("email", "");
    }
}