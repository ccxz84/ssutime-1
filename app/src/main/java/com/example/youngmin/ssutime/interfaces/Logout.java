package com.example.youngmin.ssutime.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jiwon on 2017-11-18.
 */

public interface Logout {
    @GET("account/logout.jsp")
    Call<ResponseBody> listRepos();
}
