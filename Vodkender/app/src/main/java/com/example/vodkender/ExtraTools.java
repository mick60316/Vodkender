package com.example.vodkender;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExtraTools {

    private final static String TAG ="ExtraTools";
    private static final String FAIL_TO_READ_STRING ="fail";
    public static int ONPAUSE_STATUS=0;
    public static int ONSTART_STATUS=1;


    public static String getStringFromFile (String filePath)
    {
        try {
            FileInputStream mFis = new FileInputStream(filePath);
            DataInputStream mDis = new DataInputStream(mFis);
            BufferedReader mBr=new BufferedReader( new InputStreamReader(mDis));
            String strLine ;
            String allLine ="";
            while ((strLine = mBr.readLine()) != null) {
                allLine +=strLine+"\n";

            }
            return allLine ;
        }
        catch (IOException e)
        {
            Log.e(TAG,"Fail to open file" + filePath  +" e = "+e.toString());

        }
        return FAIL_TO_READ_STRING;
    }

}
