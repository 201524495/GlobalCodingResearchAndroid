package com.example.wamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass, tv_name, tv_age;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);

        client = new OkHttpClient();

        Intent intent = getIntent(); // 로그인에서 put 한 내용을 get 해왔음
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        RequestBody formBody = new FormBody.Builder()
                .add("userID", userID)
                .add("userPass", userPass)
                .build();

        Request request = new Request.Builder()
                .url("http://annjs0308.dothome.co.kr/test.php")
                .post(formBody)
                .build();


        client.newCall(request).enqueue(callback);

    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("CallbackError","콜백오류"+e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("Information", "정보 : " + body);

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_id.setText(body);
//                    tv_pass.setText(body);
//                    tv_name.setText(body);
//                    tv_age.setText(body);
                }
            });


        }
    };
}
