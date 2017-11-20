package com.example.youngmin.ssutime.interfaces;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by jiwon on 2017-11-17.
 */

public class Session {;
    static public Retrofit retrofit;
    static AddCookiesInterceptor in1;
    static ReceivedCookiesInterceptor in2;
    static OkHttpClient  httpClient;

    public static void init_connect(Context context){
        in1 = new AddCookiesInterceptor(context);
        in2 = new ReceivedCookiesInterceptor(context);

        httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(in1)
                .addInterceptor(in2)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://13.124.129.71:8080/fitserver/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
