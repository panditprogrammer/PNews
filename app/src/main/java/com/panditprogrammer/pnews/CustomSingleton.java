package com.panditprogrammer.pnews;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomSingleton {
    private static CustomSingleton customSingletonInstance;
    private RequestQueue requestQueue;

    private CustomSingleton(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized CustomSingleton getInstance(Context context) {
        if (customSingletonInstance == null)
            customSingletonInstance = new CustomSingleton(context);
        return customSingletonInstance;
    }

    // return the request queue instance
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

}
