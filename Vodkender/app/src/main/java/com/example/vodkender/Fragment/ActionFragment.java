package com.example.vodkender.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.vodkender.Drink;
import com.example.vodkender.R;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class ActionFragment extends Fragment  implements View.OnClickListener{

    private Context context;
    private final static String TAG = "ActionFragment";
    private TextView mTitleName, mNameText,mStoryText;
    private ImageView mIconImage;
    private NavController navController;
    private ImageButton mStartButton;
    private Button mReadmoreButton;
    private Drink drinkObj;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController =findNavController (this);
        return inflater.inflate(R.layout.action_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("onViewCreated");
        linkUserInterface(view);

        Bundle bundle =getArguments();
        drinkObj= (Drink) bundle.getSerializable("drink");
        if(drinkObj !=null)
        {
            mIconImage.setImageResource(getResources().getIdentifier(drinkObj.getImageName(),"drawable",context.getPackageName()));
            mNameText.setText(drinkObj.getName());
            String story =drinkObj.getStory();
            if (story.length()>40)
            {

                story = story.substring(0,40);

            }


            mStoryText.setText(story+"......");
        }
        else
        {
            Log.i(TAG, "Can not get drink obj from main fragment");

        }


    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;

    }
    void linkUserInterface (View view)
    {
        mIconImage =view.findViewById(R.id.iconImage);
        mNameText =view.findViewById(R.id.infoNameText);
        mStoryText  =view.findViewById(R.id.storyText);
        mStartButton =view.findViewById(R.id.start_button);
        mReadmoreButton =view.findViewById(R.id.readmoreButton);
        mReadmoreButton.setOnClickListener(this);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.start_button:
                navController.navigate(R.id.action_actionFragment_to_workingFragment);
                break;
            case R.id.readmoreButton:
                Bundle bundle =new Bundle();
                bundle.putSerializable("drink",drinkObj);
                navController.navigate(R.id.action_actionFragment_to_storyFragment,bundle);
                break;

        }
    }
}
