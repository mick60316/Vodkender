package com.example.vodkender.DataSrtucture;

import java.util.HashMap;
import java.util.Map;

public class Formula {
    Map<String ,Integer> mIngredient =new HashMap<String,Integer>();
    public  Formula ()
    {

    }

    public  void addIngredient (String name,int unit)
    {
        if (!mIngredient.containsKey(name)) mIngredient.put(name,unit);
    }
    public void  removeIngredient (String name)
    {
        if (mIngredient.containsKey(name))mIngredient.remove(name);
    }
    public void clearIngredient ()
    {
        mIngredient.clear();
    }
    public Map<String ,Integer> getIngredient (){return mIngredient;}
}
