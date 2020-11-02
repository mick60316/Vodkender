package com.example.vodkender.DataSrtucture;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Formula implements Serializable {
    Map<String ,Integer> mIngredient =new HashMap<String,Integer>();
    public  Formula ()
    {

    }
    public Formula (Map <String,Integer> mIngredient)
    {
        this.mIngredient=mIngredient;
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
    public Map<String ,Integer> getIngredient (){
        System.out.println("Size "+mIngredient.size());
        return mIngredient;

    }
}
