package com.example.vodkender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class VodkenderActionActivity extends Activity {



    private ImageButton mBackButton;
    private TextView mTitleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_page);

        mBackButton =findViewById(R.id.back_button);
        Intent intent =getIntent();
        Object userAccountObj = intent.getSerializableExtra("drink");
        Drink drink = (Drink) userAccountObj;
        mTitleName.setText(drink.getChineseName());




        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
