package com.example.vodkender;

import android.util.Log;

import com.example.vodkender.DataSrtucture.Formula;
import com.example.vodkender.Fragment.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialManager implements Serializable {

    private static final String TAG = "MaterialManager";
    private List<Drink> drinkList;
    private static final String MANU_PATH = "/storage/emulated/0/Documents/Menu.txt";

    private Map<Integer, String> mMaterialMap = new HashMap<Integer, String>();
    private Map<String, Integer> mCurrentMap = new HashMap<String, Integer>();


    private static final String MATERIAL_MAP_FILE_URL = "/storage/emulated/0/Documents/VodkenderMap.txt";
    private static final String CURRENT_MAP_FILE_URL = "/storage/emulated/0/Documents/VodkenderMaterial.txt";
    private static final String FAIL_TO_READ_STRING = "fail";




    public MaterialManager() {
        initMaterialMap();
        initCurrentMap();
        initDrinksList();
    }

    void initDrinksList() {
        Log.i(TAG, "initDrinksList");
        String drinksJsonStr = ExtraTools.getStringFromFile(MANU_PATH);
        drinkList = convertJsonStrToDrinkList(drinksJsonStr);

    }

    public List<Drink> getDrinkList() {
        return drinkList;
    }


    private List<Drink> convertJsonStrToDrinkList(String jsonStr) {

        List<Drink> result = new ArrayList<>();
        try {
            JSONObject reader = new JSONObject(jsonStr);
            JSONArray menus = reader.getJSONArray("Menu");
            for (int i = 0; i < menus.length(); i++) {
                JSONObject jsonObject = new JSONObject(menus.getString(i));
                String objName = jsonObject.getString("Name");
                String objFood = jsonObject.getString("Food");
                String material = jsonObject.getString("Material");
                Map<String, Integer> materials = new HashMap<String, Integer>();
                for (int j = 0; j < material.length(); j++) {
                    String[] strSplit = material.split(",");
                    for (int materialIndex =0 ;materialIndex<strSplit.length/2 ;materialIndex++)
                    {
                        materials.put(strSplit[materialIndex*2+0], Integer.parseInt(strSplit[materialIndex*2+1]) / 100);
                    }
                }

                String imageSource = jsonObject.getString("ImageSource");
                String alcohol = jsonObject.getString("Alcohol");
                String story = jsonObject.getString("Story");
                Formula formula = new Formula(materials);
                String machineCode = getMachineCodeFromFormula(formula);
                Drink drink = new Drink(objName, objFood, formula, imageSource, alcohol, story, machineCode);
                result.add(drink);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    void initCurrentMap() {
        Log.d(TAG, "Initialization Current Map");
        String MaterialStr = ExtraTools.getStringFromFile(CURRENT_MAP_FILE_URL);
        if (MaterialStr == FAIL_TO_READ_STRING) {
            Log.e(TAG, " Can not Initialization because read file fail");
            return;
        }
        String[] MaterialCode = MaterialStr.split(",");
        System.out.println(MaterialCode.length);
        for (int i = 0; i < MaterialCode.length; i++) {
            try {
                int Code = Integer.parseInt(MaterialCode[i]);
                mCurrentMap.put(mMaterialMap.get(Code), i);
                System.out.println(mMaterialMap.get(Code));
            } catch (NumberFormatException e) {

                Log.e(TAG, "Check Current Map file format");
            }
        }


    }

    public Map<String, Integer> getCurrentMap() {
        return mCurrentMap;
    }

    void initMaterialMap() {
        Log.d(TAG, "Initialization Material Map");
        String MapStr = ExtraTools.getStringFromFile(MATERIAL_MAP_FILE_URL);
        if (MapStr == FAIL_TO_READ_STRING) {
            Log.e(TAG, " Can not Initialization because read file fail");
            return;
        }
        String[] lines = MapStr.split("\n");

        for (int i = 0; i < lines.length; i++) {

            String[] lineSplit = lines[i].split(",");

            if (lineSplit.length >= 3) {
                int id = Integer.parseInt(lineSplit[0]);
                String engName = lineSplit[1];
                String chiName = lineSplit[2];
                mMaterialMap.put(id, engName);
            } else {

                Log.e(TAG, "Read file error , please check out file format to \"id ,english name ,chinese name \"");
            }


        }

    }

    public String getMachineCodeFromFormula(Formula formula) {
        String machineCode = "";
        char[] machineCodeCharArray = new char[8];

        for (int i = 0; i < machineCodeCharArray.length; i++) {
            machineCodeCharArray[i] = '0';
        }
        for (Map.Entry<String, Integer> mFormula : formula.getIngredient().entrySet()) {
            if (mCurrentMap.containsKey(mFormula.getKey()))
                machineCodeCharArray[mCurrentMap.get(mFormula.getKey())] = (char) (mFormula.getValue() + '0');
            //System.out.println( mCurrentMap.get(mFormula.getKey()) +" "+(char)(mFormula.getValue()+'0'));

        }
        return new String(machineCodeCharArray);

    }




}