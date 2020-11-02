package com.example.vodkender;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.icu.lang.UScript;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vodkender.BleService.BleService;
import com.example.vodkender.Fragment.MainFragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VodkenderMainActivity extends AppCompatActivity implements View.OnClickListener , MainFragment.MainFragmentCallback {

    private  final static String TAG ="VodkenderMainActivity";
    private BleService bleService;
    private Intent BLEServerIntent;
    private List<String> DEVICE_NAMES = new ArrayList<>(Arrays.asList("hahahahahaha"));
    private List<Drink> DrinkList;
    private ImageButton mSettingButton;
    private RelativeLayout mSettingLayout;
    private int MaterialCount = 8;
    private Button[]  mMaterialButton ;
    private NavController navController;
    private Button testButton ;

    private void prepareBLE() {
        //region 請求權限 android 6.0+
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
            //判断是否需要 向用户解释，为什么要申请该权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

            }
        }
        //endregion

        //region 綁定service
        BLEServerIntent = new Intent(this, BleService.class);
        bindService(BLEServerIntent, BLEConnection, this.BIND_AUTO_CREATE);
        //endregion
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
        getSupportActionBar().hide();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setActivityInfo();

        //mDrinkRecycleView=findViewById(R.id.drink_recycler_view);
        mSettingButton =findViewById(R.id.setting_button);
        mSettingLayout =findViewById(R.id.settingLayout);

        mSettingLayout.setVisibility(View.GONE);


        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);


        InitSettingLayout();
        navController = Navigation.findNavController(this, R.id.main_fragment);
       // statusTextview =findViewById(R.id.status);



    }

    public void InitSettingLayout ()
    {
        mMaterialButton = new Button[MaterialCount];
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int ScreenWidth =size.x ;
        int ScreenHeight =size.y;

        float ScaleRateX = 1440/ScreenWidth;
        float ScaleRateY  =2560 /ScreenHeight;
        int ButtonWidth =(int)(300*ScaleRateX);
        int ButtonHeight = (int) (120*ScaleRateY);
//        Map<String,Integer> materialMap =mMaterialManager.getCurrentMap();
//
//
//        for (int i =0;i<MaterialCount;i++)
//        {
//            mMaterialButton[i] =new Button(this);
//            mSettingLayout.addView(mMaterialButton[i]);
//
//            mMaterialButton [i].setWidth(ButtonWidth);
//            mMaterialButton [i].setHeight(ButtonHeight);
//            mMaterialButton[i] .setTextSize(10);
//            mMaterialButton[i].setX(i%4*300*ScaleRateX+100*ScaleRateX);
//            mMaterialButton[i].setY(i/4*150*ScaleRateY+500*ScaleRateY);
//        }
//
//        for (Map.Entry<String, Integer> me : materialMap.entrySet()) {
//
//            mMaterialButton[me.getValue()].setText(me.getKey());
//
//        }


    }


    @Override
    public void onClick ( View view)
    {
        switch (view.getId())
        {
            case R.id.setting_button:
                if (navController.getCurrentDestination().getId() != R.id.mainFragment)
                {
                    this.onBackPressed();
                }
                else
                {
                    navController.navigate(R.id.action_mainFragment_to_settingFragment);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void getStaus(int status) {
        if (status==ExtraTools.ONPAUSE_STATUS)
        {
            mSettingButton.setImageResource(R.mipmap.ic_back);
        }
        else
        {
            mSettingButton.setImageResource(R.mipmap.ic_setting);

        }
    }

    public ServiceConnection BLEConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "onServiceConnected");
            //取得service的實體
            bleService = ((BleService.LocalBinder) iBinder).getService();
            //設定BLE Device name
            bleService.setBleDeviceNames(DEVICE_NAMES);
            //取得service的callback，在這邊是顯示接收BLE的資訊
            //bleService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void sendMachineCode(String machineCode)
    {
        SendBleMsg(DEVICE_NAMES.get(0)+",a"+machineCode);
    }

    private void SendBleMsg(String _msg){
        String[] msgs = _msg.split(",");
        if(bleService!=null)
            bleService.writeCharacteristic(msgs[0],msgs[1]);
    }

}
