package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);



        Button btn1 = (Button) findViewById(R.id.Sub2_home);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.Sub2_login);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = findViewById(R.id.Sub2_ID);
                EditText psw = findViewById(R.id.Sub2_PSW);
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("ID", id.getText().toString());
                intent.putExtra("PSW", psw.getText().toString());

                startActivity(intent);
            }
        });
    }
}
