package com.example.adiez.content;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.adiez.content.backgroundprocess.ContentBackService;
import com.squareup.leakcanary.LeakCanary;


public class MainActivity extends AppCompatActivity implements Communicator{


    private DetailFragment detailFragment = new DetailFragment();
    private ListFragment listFragment = new ListFragment();
    private PlayerFragment playerFragment=new PlayerFragment();
    private LiveViewFragment liveViewFragment=new LiveViewFragment();
    private BroadcastReceiver receiver;
    private IntentFilter filter = new IntentFilter();


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakCanary.install(this.getApplication());

        //background process
        Intent intent = new Intent(this, ContentBackService.class);
        startService(intent);
        //background service filter
        filter.addAction("com.listFragment.action");
        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                boolean t=intent.getBooleanExtra("test",false);
                if(t){reloadFragment();}
            }

        };
        registerReceiver(receiver, filter);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);

        changeFragment(listFragment, false);
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void launchFragment(int i) {
        playerFragment.setPosition(i);
        changeFragment(playerFragment, true);
    }

    @Override
    public void reloadFragment() {
        listFragment.reload();
    }

    @Override
    public void launchLiveView() {
        changeFragment(liveViewFragment,true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void changeFragment(final Fragment fragment,boolean addToBackStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment);
        if (addToBackStack){fragmentTransaction.addToBackStack(null);}
        fragmentTransaction.commit();
    }


}
