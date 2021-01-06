package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String ID = getIntent().getStringExtra("ID");
        String PSW = getIntent().getStringExtra("PSW");

        TextView tv1 = findViewById(R.id.Sub3_ID);
        tv1.setText(ID);
        TextView tv2 = findViewById(R.id.Sub3_PSW);
        tv2.setText(PSW);


        Button btn1 = (Button) findViewById(R.id.Sub3_Home);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}