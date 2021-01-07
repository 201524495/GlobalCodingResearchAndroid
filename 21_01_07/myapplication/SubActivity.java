package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;


public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView tv = (TextView) findViewById(R.id.Sub_ID); //타입이 TextView 인 tv 생성
        tv.setText("Sub PAge"); //tv에 입력

        Button btn1 = (Button) findViewById(R.id.Sub_btn1); //타입이 Button 인 btn 생성
        btn1.setOnClickListener(new View.OnClickListener() { //클릭 시 이벤트 발생
            @Override
            public void onClick(View v) { //어떤 이벤트인지 // 버튼 클릭 시 Main_Layout 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.Sub_log);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.Sub_Sign);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        }
}
