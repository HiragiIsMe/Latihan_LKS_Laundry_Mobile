package com.example.latihan_lks_laundry_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;
    Context ctx;
    RequestQueue queue;
    Session s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        btnLogin = findViewById(R.id.btnLogin);
        ctx = this;
        queue = Volley.newRequestQueue(ctx);
        s = new Session(ctx);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().length() < 1 || password.getText().length() < 1){
                    Toast.makeText(getApplicationContext(), "Email And Password Must Be Filled", Toast.LENGTH_LONG).show();
                }else{
                    StringRequest request = new StringRequest(Request.Method.POST, RequestApi.getLoginUrl(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           if(response != null) {
                               try {
                                   JSONObject object = new JSONObject(response);
                                   s.setEmployee(object.getInt("Id"), object.getString("Name"), object.getString("Email"));
                                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                   startActivity(intent);
                                   finish();
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }else{
                               Toast.makeText(getApplicationContext(), "Can't Find The User", Toast.LENGTH_LONG).show();
                           }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            AlertDialog dialog = new AlertDialog.Builder(ctx).create();
                            dialog.setTitle("Error");
                            dialog.setMessage(""+error);
                            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            String emailValue = email.getText().toString();
                            String passwordValue = password.getText().toString();
                            Map<String, String> params = new HashMap<>();
                            params.put("email_employee", emailValue);
                            params.put("password_employee", passwordValue);

                            return params;
                        }
                    };
                    queue.add(request);
                }
            }
        });
    }
}