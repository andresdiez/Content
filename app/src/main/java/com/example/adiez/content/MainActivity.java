package com.example.adiez.content;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Communicator{


    public DetailFragment fr;
    ListFragment hello = new ListFragment();
    private BroadcastReceiver receiver;
    private final int NOTIFICATION_ID=0;
    IntentFilter filter = new IntentFilter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){ changeFragment(hello,false); }


        //background service filter

        filter.addAction("com.hello.action");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean t=intent.getBooleanExtra("test",false);
                if(t){reloadFragment();}

            }
        };
        registerReceiver(receiver,filter);





        //background service
        Intent intent = new Intent(this, ContentBackService.class);
        startService(intent);






    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            if (fr.getStatus()){hello.reloadData();}
            fr=null;
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void launchFragment(int i) {
        fr = new DetailFragment();
        fr.setPosition(i);
        changeFragment(fr, true);
    }

    @Override
    public void reloadFragment() {
        hello.reloadData();
        fr=null;
    }


    public void changeFragment(final Fragment fragment,boolean addToBackStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment);
        if (addToBackStack){fragmentTransaction.addToBackStack(null);}
        fragmentTransaction.commit();


    }





    @Override
    protected void onStart(){
        super.onStart();
        // Store our shared preference
        SharedPreferences sp = getSharedPreferences("OURINFO", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", true);
        ed.commit();
    }


    @Override
    protected void onStop() {

        super.onStop();

        // Store our shared preference
        SharedPreferences sp = getSharedPreferences("OURINFO", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", false);
        ed.commit();
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
