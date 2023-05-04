package com.example.latihan_lks_laundry_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotifActivity extends AppCompatActivity {
    RecyclerView RecyclerNotif;
    Context ctx;
    RequestQueue queue;
    NotifAdapter notifAdapter;
    Session s;
    List<Notif> notifs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        RecyclerNotif = findViewById(R.id.RecycleNotif);
        ctx = this;
        queue = Volley.newRequestQueue(ctx);
        s = new Session(ctx);
        notifs = new ArrayList<>();

        show();
    }

    protected void show(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, RequestApi.getNotifUrl(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        notifs.add(new Notif(obj.getString("name_package"), obj.getString("complete_datetime")));
                    }
                } catch (JSONException e) {
                    Toast.makeText(ctx, "" + e, Toast.LENGTH_SHORT).show();
                }
                RecyclerNotif.setLayoutManager(new LinearLayoutManager(ctx));
                notifAdapter = new NotifAdapter(notifs, ctx);
                RecyclerNotif.setAdapter(notifAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NotifActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}