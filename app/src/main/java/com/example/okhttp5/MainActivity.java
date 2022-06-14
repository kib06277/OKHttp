package com.example.okhttp5;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    // 建立OkHttpClient
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("EE","aaaaa");
        getUrl("http://122.146.250.130/SGNR/Login/ConsignLogin?market=1&consign_no=0301");
        postUrl("http://122.146.250.130/SGNR/Price/pricelobbyData");
    }

    public void getUrl(String url){
        // 建立Request，設置連線資訊
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 建立Call
        Call call = client.newCall(request);

        // 執行Call連線到網址
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 連線成功，自response取得連線結果
                String result = response.body().string();
                Log.i("OkHttp","get = " + result);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // 連線失敗
                Log.i("OkHttp","get error = " + e);
            }
        });
    }

    public void postUrl(String url){
        // FormBody放要傳的參數和值
        FormBody formBody = new FormBody.Builder()
                .add("market", "1")
                .add("saler_code", "L999")
                .add("goods_code", "")
                .add("goods_fv", "")
                .build();

        // 建立Request，設置連線資訊
        Request request = new Request.Builder()
                .url(url)
                .post(formBody) // 使用post連線
                .build();

        // 建立Call
        Call call = client.newCall(request);

        // 執行Call連線到網址
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 連線成功
                String result = response.body().string();
                Log.i("OkHttp", "post = " + result);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // 連線失敗
                Log.i("OkHttp","post error = " + e);
            }
        });
    }
}