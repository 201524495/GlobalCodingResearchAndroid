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
    EditText id = findViewById(R.id.Login_ID);
    EditText psw = findViewById(R.id.Login_PSW);
    Button btn1 = (Button) findViewById(R.id.Login_home);
    Button btn2 = (Button) findViewById(R.id.Login_check);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// home 버튼 눌렀을 때
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
// Login 버튼
        //로그인을 하는데 기존 DB와 비교해 있으면 PASS 없으면 Toast
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = id.getText().toString();
                String userPSW = id.getText().toString();
                // 기존의 DB와 비교를 한 뒤
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);
                            boolean success = jasonObject.getBoolean("success");
                            if(success) {
                                String userID = jasonObject.getString("userID");
                                String userPSW = jasonObject.getString("userPSW");
                                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(getApplicationContext(), Check_ID_PSW.class);
                                intent.putExtra("ID", id.getText().toString());
                                intent.putExtra("PSW", psw.getText().toString());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "No ID or PASSWORD", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPSW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
