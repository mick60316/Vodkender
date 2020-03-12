package com.example.vodkender;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VodkenderMainActivity extends Activity implements View.OnClickListener {

    private RecyclerView mDrinkRecycleView;
    private List<Drink> DrinkList;
    private ImageButton mSettingButton;
    private Model md;
    private RelativeLayout mSettingLayout;
    private int MaterialCount = 8;
    private Button[]  mMaterialButton ;
    private MaterialManager mMaterialManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        md=new Model();
        mMaterialManager =new MaterialManager();




        DrinkList =new ArrayList<>();
        mDrinkRecycleView=findViewById(R.id.drink_recycler_view);
        mSettingButton =findViewById(R.id.setting_button);
        mSettingLayout =findViewById(R.id.settingLayout);
        mDrinkRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mDrinkRecycleView.setAdapter(new RecycleViewAdapter(this,md.getDrinks()));

        mSettingLayout.setVisibility(View.GONE);

        //mDrinkRecycleView.setAdapter();
        InitSettingLayout();
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
        Map<String,Integer> materialMap =mMaterialManager.getCurrentMap();


        for (int i =0;i<MaterialCount;i++)
        {
            mMaterialButton[i] =new Button(this);
            mSettingLayout.addView(mMaterialButton[i]);

            mMaterialButton [i].setWidth(ButtonWidth);
            mMaterialButton [i].setHeight(ButtonHeight);
            mMaterialButton[i] .setTextSize(10);
            mMaterialButton[i].setX(i%4*300*ScaleRateX+100*ScaleRateX);
            mMaterialButton[i].setY(i/4*150*ScaleRateY+500*ScaleRateY);
        }

        for (Map.Entry<String, Integer> me : materialMap.entrySet()) {

            mMaterialButton[me.getValue()].setText(me.getKey());

        }


    }


    @Override
    public void onClick ( View view)
    {
        switch (view.getId())
        {
            case R.id.setting_button:
                int visibility = mSettingLayout.getVisibility();
                if (visibility ==View.VISIBLE)mSettingLayout.setVisibility(View.GONE);
                else mSettingLayout.setVisibility(View.VISIBLE);

                break;
            default:
                break;

        }
    }
}
