package com.project.local.fragment;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;



import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.project.R;


public class YoutubeFragment extends Fragment {

    // API キー
    private static final String API_KEY = "AIzaSyCtDbHj5kiEaoZFKa0jpi62j9D6E-i2ML4";

    // YouTubeのビデオID
    private static String VIDEO_ID = "vI_4irMaZrM";


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.localfragment_youtube, container, false);
//
//
//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.youtube_layout, youTubePlayerFragment).commit();
//        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//                if (!wasRestored) {
//                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    player.loadVideo(VIDEO_ID);
//                    player.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
//                // YouTube error
//                String errorMessage = error.toString();
//                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
//                Log.d("errorMessage:", errorMessage);
//            }
//        });

        return rootView;
    }
}
