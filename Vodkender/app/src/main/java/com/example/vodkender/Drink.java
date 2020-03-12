package com.example.vodkender;

import android.media.Image;


import com.example.vodkender.DataSrtucture.Formula;

public class Drink {

    private String mChineseName="";
    private String mEnglishName = "";
    private Image mIcon= null;
    private String mStory = "";
    private String mCollocationFood= "";

    private Formula mFormula;

    public Drink (String ChineseName ,String EnglishName,String Story,String CollocationFood,Formula formula){
        mChineseName =ChineseName;
        mEnglishName=EnglishName;
        mStory =Story;
        mCollocationFood=CollocationFood;
        mFormula=formula;
    }
    public String getChineseName (){return mChineseName;}
    public String getmEnglishName(){return mEnglishName;}
    public Image getIcon(){return mIcon;}
    public String getStory (){return mStory;}
    public String getCollocationFood (){return mCollocationFood;}
    public Formula getmFormula (){return mFormula;}
}
