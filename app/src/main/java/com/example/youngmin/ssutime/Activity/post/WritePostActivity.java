package com.example.youngmin.ssutime.Activity.post;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.youngmin.ssutime.Activity.LoginActivity;
import com.example.youngmin.ssutime.R;
import com.example.youngmin.ssutime.interfaces.Session;
import com.example.youngmin.ssutime.interfaces.json;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
    }

    public void submit(View v){
        EditText contents = (EditText)findViewById(R.id.contents);
        EditText title = (EditText)findViewById(R.id.title);

        json service = Session.retrofit.create(json.class);
        Call<ResponseBody> session = service.listRepos(contents.getText().toString(),title.getText().toString());
        session.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("sm",response.body().string());
                    check_write(response.body().string());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "서버와 접속이 끊어졌습니다.",    Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void check_write(String string) {
        if(string.equals("1")){
            Toast.makeText(getApplicationContext(), "글 등록이 완료되었습니다.",    Toast.LENGTH_LONG).show();
            finish();
        }
        else if(string.equals("-1")){
            Toast.makeText(getApplicationContext(), "로그인이 만료되었습니다.",    Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else if(string.equals("0")){
            Toast.makeText(getApplicationContext(), "서버에 오류가 발생했습니다.",    Toast.LENGTH_LONG).show();
        }
    }
}
