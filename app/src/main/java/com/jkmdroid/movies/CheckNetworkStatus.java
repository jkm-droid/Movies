package com.jkmdroid.movies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * this class checks whether the device has internet connection
 * and returns true or false
 * **/
public class CheckNetworkStatus {
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    public boolean isNetworkAvailable(Context context){

        connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();

    }
}
