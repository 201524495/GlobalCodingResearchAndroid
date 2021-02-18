package com.example.wamp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_test;
    private OkHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_test = findViewById(R.id.btn_test);

        //객체 정의
        client = new OkHttpClient();

        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //로그인 버튼을 클릭 시 수행
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
    
                // 공백 검사
                if(!userID.replace(" ","").equals("") &&
                        !userPass.replace(" ","").equals("")) { //
                    //formBody를 구성
                    RequestBody formBody = new FormBody.Builder()
                            .add("userID", userID)
                            .add("userPassword", userPass)
                            .build();
                    //
                    Request request = new Request.Builder()
                            .url("http://annjs0308.dothome.co.kr/Login.php")
                            .post(formBody) //method : post
                            .build();
                    //비동기 처리
                    client.newCall(request).enqueue(callback);
                }
                else { //입력값 없을 시
                    Toast.makeText(LoginActivity.this,"Please fill out all Blanks", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //테스트 버튼을 클릭 시 수행
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Request request = new Request.Builder()
                        .url("http://annjs0308.dothome.co.kr/find.php")
                        .build();
                //비동기 처리
                client.newCall(request).enqueue(callback);
            }
        });
    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("DDDD", "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string(); //응답받은 body (echo)
            Log.d("Login", "응답:"+body);

            if(body.contains("success")){ // equals : 처음부터 끝까지 같아야함
                Log.d("success", "로그인 성공");

                //입력받은 ID와 Password를 넘겨준다.
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userID", et_id.getText().toString());
                intent.putExtra("userPass", et_pass.getText().toString());
                startActivity(intent);
            }
            if(body.contains("fail")) { //로그인 실패
                Log.d("fail", "Wrong ID Or Password");

                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, " Please Check again ID Or Password ", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            if(body.contains("Test")) { // contains : 문자열 중 같은 "부분"이 있으면 참
                Log.d("Test", "test 버튼");
                LoginActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LoginActivity.this, " Test Button "+body, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };
}
//https://thereclub.tistory.com/46