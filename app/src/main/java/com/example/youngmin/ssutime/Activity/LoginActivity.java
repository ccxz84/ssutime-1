package com.example.youngmin.ssutime.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.youngmin.ssutime.R;
import com.example.youngmin.ssutime.interfaces.AddCookiesInterceptor;
import com.example.youngmin.ssutime.interfaces.Login;
import com.example.youngmin.ssutime.interfaces.ReceivedCookiesInterceptor;
import com.example.youngmin.ssutime.interfaces.Session;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginbutton(View v){
        EditText id = (EditText) findViewById(R.id.ID);
        EditText password = (EditText) findViewById(R.id.password);

        String username = id.getText().toString();
        String pw = password.getText().toString();

        Login service = Session.retrofit.create(Login.class);
        Call<ResponseBody> session = service.listRepos(username, pw);

        session.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Create_Session(response.body().string());
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void Create_Session(String arg)
    {
        Log.d("sm", arg);
        int val = Integer.parseInt(arg);
        if(val==1){
            startActivity(new Intent(this,MainActivity.class));
        }
        else if(val==-1){
            Toast.makeText(getApplicationContext(), "현재 로그인 상태입니다. 재접속 해주세요",    Toast.LENGTH_LONG).show();
        }
        else if(val==2){
            Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 잘못되었습니다.",    Toast.LENGTH_LONG).show();
        }
        else if(arg == null){
            Toast.makeText(getApplicationContext(), "인터넷 연결 상태를 확인해주세요.",    Toast.LENGTH_LONG).show();
        }

    }
}
