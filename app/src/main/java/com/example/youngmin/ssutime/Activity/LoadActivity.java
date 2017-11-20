package com.example.youngmin.ssutime.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.youngmin.ssutime.R;
import com.example.youngmin.ssutime.interfaces.AutoLogin;
import com.example.youngmin.ssutime.interfaces.Login;
import com.example.youngmin.ssutime.interfaces.Session;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        Session.init_connect(getApplicationContext());

        AutoLogin service = Session.retrofit.create(AutoLogin.class);
        Call<ResponseBody> session = service.listRepos();
        session.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Autolog(response.body().string());
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void Autolog(String string) {
        if(string.equals("-1")){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else if(string.equals("1")){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }
}
