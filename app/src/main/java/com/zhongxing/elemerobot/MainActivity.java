package com.zhongxing.elemerobot;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhongxing.task.BuyTask;


public class MainActivity extends Activity  {
    private static final String TAG = "MainActivity";
    private Button btnStart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startService();
            }
        });


    }

    private void startService(){
        BuyTask buyTask = BuyTask.getInstance();
        buyTask.setRemarks("test");
        Log.d(TAG, "123111startService: " + buyTask.hashCode() +buyTask.getRemarks());
        Intent it = new Intent(MainActivity.this,SchedulerService.class);
        startService(it);
    }


}
