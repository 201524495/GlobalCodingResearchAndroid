package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.Main_ID);
        tv.setText("Main Layout");

        Button button = (Button) findViewById(R.id.Main_btn1);
        button.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;
            @Override
            public void onClick(View view) { //버튼1 클릭 시 
                Toast.makeText(getBaseContext(), "Main Layout", Toast.LENGTH_SHORT).show();
                if(flag) { //클릭하면 바뀌고
                    tv.setText("Change ID");
                    flag = false;
                }
                else { //한번 더 클릭하면 바뀌고
                    tv.setText("Main Layout");
                    flag = true;
                }
            }
        });

        Button button1 = (Button) findViewById(R.id.Main_btn2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼2 클릭 시 Sub_Layout으로 이동
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        });
    }

}