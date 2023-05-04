package com.example.latihan_lks_laundry_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestPickUpActivity extends AppCompatActivity {
    TextView address;
    Spinner namee ,pack;
    Button save;
    Context ctx;
    RequestQueue queue;
    int id_employee, id_customer, id_package;

    List<Customer> Cust;
    List<String> customer_list;
    List<String> package_name;
    List<Integer> package_id;
    Session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_pick_up);

        namee = findViewById(R.id.name);
        address = findViewById(R.id.address);
        pack = findViewById(R.id.pack);
        save = findViewById(R.id.save);
        ctx = this;
        queue = Volley.newRequestQueue(ctx);

        Cust = new ArrayList<>();
        customer_list = new ArrayList<>();
        package_name = new ArrayList<>();
        package_id = new ArrayList<>();
        s = new Session(ctx);

        package_name.clear();
        package_id.clear();

        namee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Customer customer = Cust.get(i);
                id_customer = customer.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_package = Integer.valueOf(package_id.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject obj = new JSONObject();

                    obj.put("id_employee", s.getId());
                    obj.put("id_customer", id_customer);
                    obj.put("id_package", id_package);

                    JsonObjectRequest checkOut = new JsonObjectRequest(Request.Method.POST, RequestApi.getCheckoutUrl(), obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                           if(Boolean.valueOf(String.valueOf(response)) == true){
                               Toast.makeText(ctx, "Success", Toast.LENGTH_LONG).show();
                           }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ctx, "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(checkOut);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getCustomer();
        getPackage();
    }

    void getCustomer(){
        JsonArrayRequest custRequest = new JsonArrayRequest(Request.Method.GET, RequestApi.getCustomerUrl(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Cust.add(new Customer(obj.getInt("id"), obj.getString("name"), obj.getString("address")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(Customer customer : Cust){
                    customer_list.add(customer.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, customer_list);
                namee.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(custRequest);
    }

    void getPackage(){
        JsonArrayRequest packRequest = new JsonArrayRequest(Request.Method.GET, RequestApi.getPackageUrl(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        package_name.add(obj.getString("name_package"));
                        package_id.add(obj.getInt("id"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, package_name);
                pack.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(packRequest);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(RequestPickUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
