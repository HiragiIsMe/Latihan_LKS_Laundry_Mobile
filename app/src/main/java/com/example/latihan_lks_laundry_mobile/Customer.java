package com.example.latihan_lks_laundry_mobile;

import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class Customer {
    private int id;
    private String name, address;

    public Customer(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
}
