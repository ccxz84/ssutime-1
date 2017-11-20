package com.example.youngmin.ssutime.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jiwon on 2017-11-18.
 */

public interface json {
    @POST("post/write_text.jsp")
    Call<ResponseBody> listRepos(@Query("title") String title, @Query("text") String text);
}
