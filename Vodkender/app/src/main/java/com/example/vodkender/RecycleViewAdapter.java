package com.example.vodkender;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private Context context;
    private List <Drink> drinks;
    private NavController navController;

    Resources resources;

    public RecycleViewAdapter (Context context , List<Drink> drinks ,NavController navController)
    {

        this.context =context;
        this.drinks =drinks;
        this.navController=navController;
        resources=context.getResources();

    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,food,alcohol;

        View itemView;
        private ViewHolder(View itemView){
            super(itemView);
            this.itemView =itemView;
            name=itemView.findViewById(R.id.chineseName);
            alcohol=itemView.findViewById(R.id.alcohol);
            food =itemView.findViewById(R.id.food);
            iconImage=itemView.findViewById(R.id.iconImage);
        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(context).inflate(R.layout.drink_recycle_item,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final  Drink drink =drinks.get(position);
            holder.name.setText(drink.getName());
            holder.alcohol.setText("酒精濃度 "+drink.getAlcohol());
            holder.food.setText("搭配 : "+drink.getCollocationFood());
            holder.iconImage.setImageResource(resources.getIdentifier(drink.getImageName(),"drawable",context.getPackageName()));
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v)
                {
                    System.out.println(""+ drink.getName()+" "+drink.getStory());
                    System.out.println("Press" + position);
                    // do something
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("drink",drink);
                    navController.navigate(R.id.action_mainFragment_to_actionFragment,bundle);
                    //context.startActivity(intent);
                }
            });



    }
    @Override
    public int getItemCount() {
        return drinks.size();
    }








}
