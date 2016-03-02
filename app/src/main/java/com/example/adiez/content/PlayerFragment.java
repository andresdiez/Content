package com.example.adiez.content;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.adiez.content.context.Context;
import com.example.adiez.content.model.AbstractHandler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayerFragment extends Fragment {


    private PlayerFragmentPresenter presenter;
    private int position;


    @SuppressWarnings("PackageVisibleField")
    @Bind(R.id.videoView)VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter= Context.getInstance().providePlayerFragmentPresenter(new Handler());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_layout,container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        presenter.getVideo(position);
    }

    public void setPosition(int position) {
        this.position = position;
    }


    private class Handler extends AbstractHandler{

        @Override
        public void playVideo(String message) {

            MediaController mediaController = new MediaController(getActivity());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(message));
            videoView.start();
        }
    }

}
