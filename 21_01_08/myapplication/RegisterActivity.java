package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_ID,et_PSW,et_NAME,et_MAIL, et_PSW_CK;
    private AlertDialog dialog;
    private boolean validation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디 값 찾아주기
        et_ID = findViewById(R.id.Register_ID);
        et_PSW = findViewById(R.id.Register_PSW);
        et_PSW_CK = findViewById(R.id.Register_PSW_CK);
        et_NAME = findViewById(R.id.Register_Name);
        et_MAIL = findViewById(R.id.Register_MAIL);

/// home 버튼
        Button btn1 = (Button) findViewById(R.id.Register_home);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

// ID 중복 체크 버튼        
        Button btn2 = (Button) findViewById(R.id.Register_Check);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_ID.getText().toString();
                if (validation) {
                    return;
                }
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Not NULL")
                            .setPositiveButton("Check", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Can use ID")
                                        .setPositiveButton("Check", null)
                                        .create();
                                dialog.show();
                                et_ID.setEnabled(false);
                                validation = true;
                                btn2.setText("OK");
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Can Use This ID")
                                        .setNegativeButton("Check", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

/// Register 버튼
        Button btn3 = (Button) findViewById(R.id.Register_Register);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //register 키
                //Edit Text에 입력되어 있는 값을 get 해온다.
                String userID = et_ID.getText().toString();
                final String userPSW = et_PSW.getText().toString();
                String userNAME = et_NAME.getText().toString();
                String userMAIL = et_MAIL.getText().toString();
                final String PSW_CK = et_PSW_CK.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {//Volley
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response); //Register2 php에 response
                            boolean success = jsonObject.getBoolean("success");//Register2 php에 sucess
                            if(userPSW.equals(PSW_CK)) { // PSW 와 PSW_CK가 같을 경우
                                if (success) { //회원 등록에 성공한 경우
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else { //회원 등록에 실패한 경우 toast 띄움
                                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 volley를 이용해서 요청을 함
                RegisterRequest registerRequest = new RegisterRequest(userID, userPSW, userNAME, userMAIL, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}