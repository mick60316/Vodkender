package com.example.vodkender.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vodkender.Component.ExtraTools;
import com.example.vodkender.Component.MaterialManager;
import com.example.vodkender.R;
import com.example.vodkender.Component.RecycleViewAdapter;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MainFragment extends Fragment {
    private final static String TAG="MainFragment";
    private RecyclerView recyclerView;
    private NavController navController;
    private MaterialManager mMaterialManager ;
    private Context context;
    MainFragmentCallback mainFragmentCallback;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"onViewCreated");
        mainFragmentCallback.getStaus(0);
        linkUserInterface(view);
        mMaterialManager =new MaterialManager();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RecycleViewAdapter(context,mMaterialManager.getDrinkList(),navController));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        mainFragmentCallback= (MainFragmentCallback) context;

    }

    void linkUserInterface (View view)
    {
        recyclerView =view.findViewById(R.id.drink_recycler_view);
        navController =findNavController (this);

    }

    @Override
    public void onPause() {
        mainFragmentCallback.getStaus(ExtraTools.ONPAUSE_STATUS);
        super.onPause();
    }
    public interface MainFragmentCallback {
        void getStaus (int status);


    }

    @Override
    public void onStart() {
        mainFragmentCallback.getStaus(ExtraTools.ONSTART_STATUS);
        super.onStart();
    }
}
