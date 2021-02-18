package com.example.wamp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);

        //객체 정의
        client = new OkHttpClient();

        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userAge = et_age.getText().toString();

                //ID, Password, Name, Age NULL 판단
                if(!userID.replace(" ","").equals("") &&
                        !userPass.replace(" ","").equals("") &&
                        !userName.replace(" ","").equals("") &&
                        !userAge.replace(" ","").equals("") ) {
                    //formBody를 구성
                    RequestBody formBody = new FormBody.Builder()
                            .add("userID", userID)
                            .add("userPassword", userPass)
                            .add("userName", userName)
                            .add("userAge", userAge)
                            .build();
                    //
                    Request request = new Request.Builder()
                            .url("http://annjs0308.dothome.co.kr/Register.php")
                            .post(formBody) //method : post
                            .build();

                    //비동기 처리
                    client.newCall(request).enqueue(callback);
                }
                else{ //입력값 없을 시
                    Toast.makeText(RegisterActivity.this, "Please fill out all Blanks", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("CallbackError", "콜백오류"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("Register", "응답 : " + body);

            if(body.contains("success")) {// 회원등록 성공
                Log.d("success","회원등록 성공");
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "회원등록 성공", Toast.LENGTH_SHORT).show();
                    }

                });
            }
            if(body.contains("fail")) { //회원등록 실패
                Log.d("fail", "Registered ID");
                RegisterActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "ID is already Registered", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    };
}