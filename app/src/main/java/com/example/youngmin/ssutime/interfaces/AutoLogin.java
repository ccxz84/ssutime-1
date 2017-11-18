package com.example.youngmin.ssutime.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jiwon on 2017-11-18.
 */

public interface AutoLogin {
    @GET("account/autologin.jsp")
    Call<ResponseBody> listRepos();
}
