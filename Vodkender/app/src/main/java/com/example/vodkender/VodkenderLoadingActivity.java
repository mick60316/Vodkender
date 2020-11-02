package com.example.vodkender;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.vodkender.BleService.BleService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VodkenderLoadingActivity extends Activity  {
    private BleService mBleService;
    private Intent BLEIntent;
    private Handler BleHander;
    public List<String> mDeviceName=new ArrayList<String>(Arrays.asList("hahahahahaha"));

    private MaterialManager materialManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);
        setActivityInfo();
        DatabaseSync databaseSync =new DatabaseSync();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                Intent intent = new Intent(getApplicationContext(), VodkenderMainActivity.class);
                // If you just use this that is not a valid context. Use ActivityName.this
               startActivity(intent);
            }
        }, 5000);


    }





    private void setActivityInfo()// Lock screen orientation and full screen
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }



}
