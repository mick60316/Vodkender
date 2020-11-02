package com.example.vodkender.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.vodkender.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class WorkingFragment extends Fragment implements View.OnTouchListener{
    Timer timer=new Timer();
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.working_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=findNavController(this);


    }

    @Override
    public void onStart() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                navController.navigate(R.id.action_workingFragment_to_finishFragment);
            }
        },5000);
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        System.out.println("Touch");


        return false;
    }
}
