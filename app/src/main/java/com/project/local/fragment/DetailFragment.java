package com.project.local.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.project.R;
import com.project.local.dto.LocalMainData;

public class DetailFragment extends Fragment {

    ImageView iv;
    TextView tvName, tvIntroduce, tvTime, tvLevel, tvReceipt;
    String foodName;
    Integer foodImg;

    YoutubeFragment youtubeFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    LocalMainData localMainData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.localfragemt_detail, container, false);
        tvName = rootView.findViewById(R.id.tvName);
        iv = rootView.findViewById(R.id.ivItem);

        String foodName;

        Bundle bundle = getArguments();

        if(bundle !=null){
            foodName = bundle.getString("name");
            foodImg = bundle.getInt("img");
            tvName.setText(foodName);
            iv.setImageResource(foodImg);

        youtubeFragment = new YoutubeFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

//        fragmentTransaction.replace(R.id.youtubeLinear, youtubeFragment);
//        fragmentTransaction.commit();
        }

        return rootView;
    }
}
