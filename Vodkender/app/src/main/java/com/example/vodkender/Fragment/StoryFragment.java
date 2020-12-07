package com.example.vodkender.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vodkender.DataSrtucture.Drink;
import com.example.vodkender.R;

public class StoryFragment extends Fragment {


    private ImageView mIconImage;
    private TextView mNameText,mStoryText;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.story_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linkUesrInterface (view);
        Bundle bundle=getArguments();
        Drink drinkObj = (Drink) bundle.getSerializable("drink");

        if (drinkObj !=null)
        {
            mIconImage.setImageResource(getResources().getIdentifier(drinkObj.getImageName(),"drawable",context.getPackageName()));
            mNameText.setText(drinkObj.getName());
            mStoryText.setText(drinkObj.getStory());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    void linkUesrInterface (View view)
    {
        mIconImage=view.findViewById(R.id.iconImage);
        mNameText=view.findViewById(R.id.nameText);
        mStoryText=view.findViewById(R.id.storyText);
    }
}
