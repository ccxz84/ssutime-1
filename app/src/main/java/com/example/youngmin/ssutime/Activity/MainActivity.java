package com.example.youngmin.ssutime.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.youngmin.ssutime.Activity.post.WritePostActivity;
import com.example.youngmin.ssutime.R;
import com.example.youngmin.ssutime.interfaces.Logout;
import com.example.youngmin.ssutime.interfaces.Session;
import com.example.youngmin.ssutime.interfaces.json;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logout(View v){
        Logout service = Session.retrofit.create(Logout.class);
        Call<ResponseBody> session = service.listRepos();
        session.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("sm",response.body().string());
                    start();
                    finish();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void start() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void submit(View v){
        startActivity(new Intent(this,WritePostActivity.class));
    }

}
