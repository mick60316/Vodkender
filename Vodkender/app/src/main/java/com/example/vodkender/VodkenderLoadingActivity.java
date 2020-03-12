package com.example.vodkender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vodkender.BleService.BleService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VodkenderLoadingActivity extends Activity {
    private BleService mBleService;
    private Intent BLEIntent;
    private Handler BleHander;
    public List<String> mDeviceName=new ArrayList<String>(Arrays.asList("hahahahahaha"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                Intent intent = new Intent(getApplicationContext(), VodkenderMainActivity.class);
                // If you just use this that is not a valid context. Use ActivityName.this
                startActivity(intent);
            }
        }, 3000);


    }




}
