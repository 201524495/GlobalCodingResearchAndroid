package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button btn1 = (Button) findViewById(R.id.Sub2_home);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        //로그인을 하는데 기존 DB와 비교해 있으면 PASS 없으면 Toast
        Button btn2 = (Button) findViewById(R.id.Sub2_check);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 기존의 DB와 비교를 한 뒤
                EditText id = findViewById(R.id.Sub2_ID);
                EditText psw = findViewById(R.id.Sub2_PSW);

                if(true) { //DB의 정보와 일치하면 다음 페이지에 ID와 PSW 출력

                    Intent intent = new Intent(getApplicationContext(), Check_ID_PSW.class);
                    intent.putExtra("ID", id.getText().toString());
                    intent.putExtra("PSW", psw.getText().toString());

                    startActivity(intent);}

                else {// DB의 정보와 일치하지 않으면
                    Toast.makeText(Login.this, "No ID or PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
