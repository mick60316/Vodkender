package com.example.vodkender;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private Context context;
    private List <Drink> drinks;

    public RecycleViewAdapter (Context context , List<Drink> drinks)
    {
        this.context =context;
        this.drinks =drinks;
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        View itemView;
        private ViewHolder(View itemView){
            super(itemView);
            this.itemView =itemView;
            name=itemView.findViewById(R.id.chineseName);
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
            holder.name.setText(drink.getChineseName());
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v)
                {
                    System.out.println(""+ drink .getChineseName()+" "+drink.getStory());
                    System.out.println("Press" + position);

                    // do something
                    Intent intent = new Intent(context, VodkenderActionActivity.class);
                    intent.putExtra("drink",drink);
                 //   bundle.putSerializable("drink",drink);

                    // If you just use this that is not a valid context. Use ActivityName.this
                    context.startActivity(intent);
                }
            });



    }
    @Override
    public int getItemCount() {
        return drinks.size();
    }








}
