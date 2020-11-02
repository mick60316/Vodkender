package com.example.vodkender;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;


import com.example.vodkender.DataSrtucture.Formula;

import java.io.Serializable;

public class Drink  implements Serializable {


    private String mEnglishName = "";
    private String mImageName ="";
    private String mStory = "";
    private String mCollocationFood= "";
    private String mMachineCode ="";
    private Formula mFormula;
    private String mAlcohol;
    private int mData;
    public Drink (String EnglishName,String CollocationFood,Formula formula,String imageName,String alcohol,String Story,String machineCode){
        mEnglishName=EnglishName;
        mStory =Story;
        mCollocationFood=CollocationFood;
        mFormula=formula;
        mMachineCode=machineCode;
        mImageName=imageName;
        mAlcohol=alcohol;

    }



    public void sayHello ()
    {
        System.out.println("Hello world" +mEnglishName);

    }
    public String getAlcohol () { return mAlcohol; }
    public String getName(){return mEnglishName;}
    public String getImageName (){return mImageName;}
    public String getStory (){return mStory;}
    public String getCollocationFood (){return mCollocationFood;}
    public Formula getmFormula (){return mFormula;}
    public String getMachineCode(){return mMachineCode;}


}
