package com.example.adiez.content;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveViewFragment extends Fragment{

    private String pathToFileOrUrl= "rtmp://172.16.11.89:1935/live/test";
    @Bind(R.id.surface_view) VideoView mVideoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(getActivity())) {return;}
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.live_view_fragment, container,false);
        ButterKnife.bind(this, root);
        launchVideo();
        return root;
    }


    public void launchVideo() {
        mVideoView.setVideoPath(pathToFileOrUrl);
        mVideoView.setMediaController(new MediaController(getActivity()));
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MyOnPreparedListener());
        mVideoView.setVideoPath(pathToFileOrUrl);

    }


    private static class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {

            mediaPlayer.setPlaybackSpeed(1.0f);
        }
    }
}
