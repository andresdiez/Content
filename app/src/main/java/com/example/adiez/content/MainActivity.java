package com.example.adiez.content;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adiez.content.model.Model;
import com.squareup.leakcanary.LeakCanary;


public class MainActivity extends AppCompatActivity implements Communicator{


    DetailFragment detailFragment = new DetailFragment();
    ListFragment listFragment = new ListFragment();
    BroadcastReceiver receiver;
    IntentFilter filter = new IntentFilter();
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeakCanary.install(this.getApplication());

        Model model=new Model();
        listFragment.registerModel(model);
        detailFragment.registerModel(model);


        sp = getSharedPreferences("OURINFO", MODE_PRIVATE);
        if (savedInstanceState==null){ changeFragment(listFragment,false); }


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

        //background service
        Intent intent = new Intent(this, ContentBackService.class);
        startService(intent);






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

        detailFragment.setPosition(i);
        changeFragment(detailFragment, true);
    }

    @Override
    public void reloadFragment() {

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
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", true);
        ed.apply();
    }


    @Override
    protected void onStop() {
        // Store our shared preference
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("active", false);
        ed.apply();
        super.onStop();



    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
