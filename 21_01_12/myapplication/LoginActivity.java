package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText id = findViewById(R.id.Login_ID);
        EditText psw = findViewById(R.id.Login_PSW);

// home 버튼 눌렀을 때
        Button btn1 = (Button) findViewById(R.id.Login_home);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

// Login 버튼
        //로그인을 하는데 기존 DB와 비교해 있으면 PASS 없으면 Toast
        Button btn2 = (Button) findViewById(R.id.Login_Login);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //클릭 시 Edit Text 값 String으로 받아
                String userID = id.getText().toString();
                String userPass = psw.getText().toString();
                // 기존의 DB와 비교를 한 뒤
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);
                            boolean success = jasonObject.getBoolean("success");
                            if(success) {
                                String userID = jasonObject.getString("userID");
                                String userPass = jasonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(LoginActivity.this, Check_ID_PSW.class);
                                intent.putExtra("userPass", userPass);
                                intent.putExtra("userID", userID);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "No ID or PASSWORD", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
