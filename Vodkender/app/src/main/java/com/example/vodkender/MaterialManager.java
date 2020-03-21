package com.example.vodkender;

import android.util.Log;

import com.example.vodkender.DataSrtucture.Formula;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MaterialManager {

    private static final String TAG = "MaterialManager";


    private Map< Integer,String > mMaterialMap =new HashMap<Integer,String>();
    private Map<String,Integer> mCurrentMap =new HashMap<String, Integer>();


    private static final String MATERIAL_MAP_FILE_URL ="/storage/emulated/0/Documents/VodkenderMap.txt";
    private static final String CURRENT_MAP_FILE_URL ="/storage/emulated/0/Documents/VodkenderMaterial.txt";
    private static final String FAIL_TO_READ_STRING ="fail";

    public MaterialManager()
    {
        initMaterialMap();
        initCurrentMap();
    }
    void initCurrentMap ()
    {
        Log.d (TAG , "Initialization Current Map");
        String MaterialStr = getStringFromFile(CURRENT_MAP_FILE_URL);
        if (MaterialStr == FAIL_TO_READ_STRING)
        {
            Log.e(TAG," Can not Initialization because read file fail");
            return ;
        }
        String [] MaterialCode =MaterialStr.split( ",");
        System.out.println(MaterialCode.length);
        for (int i =0;i<MaterialCode.length ;i++)
        {
            try {
                int Code = Integer.parseInt(MaterialCode[i]);
                mCurrentMap.put(mMaterialMap.get(Code),i);
                System.out.println(mMaterialMap.get(Code));
            }
            catch (NumberFormatException e)
            {

                Log.e(TAG,"Check Current Map file format");
            }
        }


    }

    public Map<String,Integer>  getCurrentMap ()
    {
        return mCurrentMap;
    }

    void initMaterialMap ()
    {
        Log.d (TAG , "Initialization Material Map");
        String MapStr = getStringFromFile(MATERIAL_MAP_FILE_URL);
        if (MapStr ==FAIL_TO_READ_STRING )
        {
            Log.e(TAG," Can not Initialization because read file fail");
            return ;
        }
        String  []  lines =MapStr.split( "\n");

        for (int i =0;i<lines.length ;i++)
        {

            String   [] lineSplit =lines[i].split(",");

            if (lineSplit.length >=3) {
                int id =Integer.parseInt(lineSplit[0]);
                String engName = lineSplit [1];
                String chiName =lineSplit [2] ;
                mMaterialMap.put(id,engName);
            }
            else
            {

                Log.e(TAG,"Read file error , please check out file format to \"id ,english name ,chinese name \"");
            }


        }


        /*

       for (Map.Entry<Integer, String> me : mMaterialMap.entrySet()) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }

        */

    }

    private String getStringFromFile (String filePath)
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
    public String getMachineCodeFromFormula (Formula formula)
    {
        String machineCode = "";
        char [] machineCodeCharArray  = new char[8];

        for (int i =0;i<machineCodeCharArray.length ; i++)
        {
            machineCodeCharArray[i] = '0';
        }
        for (Map.Entry<String,Integer> mFormula:formula.getIngredient().entrySet()) {
            machineCodeCharArray[mCurrentMap.get(mFormula.getKey())]=(char)(mFormula.getValue()+'0');
        }
        return new String(machineCodeCharArray);

    }

}
