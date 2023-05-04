package com.example.latihan_lks_laundry_mobile;

public class Notif {
    private String pack;
    private String datetime;

    public Notif(String name, String date){
        this.pack = name;
        this.datetime = date;
    }

    public String getPack(){
        return pack;
    }

    public String getDatetime(){
        return  datetime;
    }
}
