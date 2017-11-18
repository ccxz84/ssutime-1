package com.example.youngmin.ssutime.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by young min on 2017-11-17.
 */

public interface Login {
    @GET("account/login.jsp")
    Call<ResponseBody> listRepos(@Query("id") String id, @Query("password") String pw);
}
