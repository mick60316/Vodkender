package com.example.vodkender;



import com.example.vodkender.DataSrtucture.Formula;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Drink> drinks;

    public Model()
    {
        drinks = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            Formula formula =new Formula();
            Drink drink =new Drink("酒"+i,"Wind"+i,"Story"+i,"Food"+i,formula);
            drinks.add(drink);
        }

    }

    public List<Drink> getDrinks()
    {
        return drinks;
    }



}
