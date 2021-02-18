package com.example._0To100;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView01;
    ProgressBar progress1, progress2;
    BackgroundTask task;
    int value1, value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView01 = (TextView) findViewById(R.id.textView01);
        progress1 = (ProgressBar) findViewById(R.id.progress1);
        progress2 = (ProgressBar) findViewById(R.id.progress2);
        
        //실행 버튼 이벤트 처리
        Button executeBtn = (Button) findViewById(R.id.executeBtn);
        executeBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //새로운 Task 객체를 만들고 실행
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        //취소 버튼 이벤트 처리
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { //댓글 수정 사항
               // if (task != null && task.getStatus() == AsyncTask.Status.RUNNING)
                    task.cancel(true);
            }
        });
    }

    //새로운 Task 객체를 정의
    class BackgroundTask extends AsyncTask<Integer , Integer , Integer> {
        protected void onPreExecute() {
            value1 = 0;
            value2 = 100-value1;
            progress1.setProgress(value1);
            progress2.setProgress(value2);
        }

        protected Integer doInBackground(Integer ... values) {
            while(isCancelled() == false) {
                value1++;
                if(value1 >= 100) {
                    break;
                } else {
                    publishProgress(value1);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
            return value1;
        }

        protected void onProgressUpdate(Integer ... values) {
            progress1.setProgress(values[0].intValue());
            progress2.setProgress(100-values[0].intValue());
            textView01.setText("Current Value : " + values[0].toString());
        }

        protected void onPostExecute(Integer result) {
            progress1.setProgress(0);
            progress2.setProgress(100);
            textView01.setText("Finished.");
        }

        protected void onCancelled() {
            progress1.setProgress(0);
            progress2.setProgress(100);
            textView01.setText("Cancelled.");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}