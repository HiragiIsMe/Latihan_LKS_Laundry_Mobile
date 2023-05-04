package com.example.latihan_lks_laundry_mobile;

public class RequestApi {

    private static final String base_url = "http://192.168.0.35/";
    private static final String loginUrl = "api/employee";
    private static final String checkoutUrl = "api/checkout";
    private static final String packageUrl = "api/package";
    private static final String customerUrl = "api/customer";
    private static final String notifUrl = "api/notif";

    public static String getBaseUrl(){
        return  base_url;
    }
    public static String getLoginUrl(){
        return getBaseUrl() + loginUrl;
    }
    public static String getCheckoutUrl(){
        return getBaseUrl() + checkoutUrl;
    }
    public static String getPackageUrl(){
        return getBaseUrl() + packageUrl;
    }
    public static String getCustomerUrl(){
        return getBaseUrl() + customerUrl;
    }
    public static String getNotifUrl(){
        return getBaseUrl() + notifUrl;
    }
}
