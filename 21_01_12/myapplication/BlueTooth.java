package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class BlueTooth extends AppCompatActivity {

    private BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        bt = new BluetoothSPP(this); //initialization

        //home button
        Button btnHome = (Button) findViewById(R.id.Blue_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 사용 불가 시
        if(!bt.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext() // 사용 불가라고 토스트 띄워줌
            ,"Bluetooth is not available"
            , Toast.LENGTH_SHORT).show();
            finish(); //화면 종료
        }


        //데이터 수신 //데이터 수신
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            /*TextView temp = (TextView)findViewById(R.id.Blue_temp);
            TextView humid = (TextView)findViewById(R.id.Blue_humid);
            TextView light = (TextView)findViewById(R.id.Blue_light);*/

            public void onDataReceived(byte[] data, String message) {
              Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();

               /* String[] array = message.split(",");
                temp.setText(array[0].concat(" C"));
                humid.setText(array[1].concat(" %"));
                light.setText(array[2].concat(" LUX"));*/
            }
        });
        // 데이터 수신    // 연결 시
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
             public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        ,"Connected to" + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

             // 연결 해제
            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext()
                        , "Connected lost", Toast.LENGTH_SHORT).show();
            }

             // 연결 실패
            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });
        //연결 하는 기능 버튼
        Button btnConnect = (Button) findViewById(R.id.Blue_Connect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            //현재 연결이 되어 있으면 끊고, 반대면 연결
            public void onClick(View v) {
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService();// 블루투스 정지
    }

    public void onStart() { // 앱이 시작하면
        super.onStart();
        if(!bt.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if(!bt.isServiceAvailable()) {
                bt.setupService(); // DEVICE_OTHER 은 아두이노나 기타 블루투스 모듈
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();// DEVICE_ANDROID 는 안드로이드 기기 끼리 연결
            }
        }
    }

    private void setup() { // 데이터 전송
        Button btnSend = (Button)findViewById(R.id.Blue_Send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // "Text" 전송
                bt.send("Text", true);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (requestCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}