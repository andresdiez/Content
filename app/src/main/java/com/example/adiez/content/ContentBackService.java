package com.example.adiez.content;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;


public class ContentBackService extends Service{

    private final int NOTIFICATION_ID=0;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //startForeground();
        new Async().execute();
        return Service.START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public void loadData(){
        Intent local = new Intent();
        local.putExtra("test",true);
        local.setAction("com.hello.action");
        this.sendBroadcast(local);
    }

    public void pushNotification(){
        Intent intent3 = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent3, 0);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification n = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")

                .setContentText("Subject")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(uri)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, n);
    }


    private class Async extends AsyncTask <Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            Model m=new Model();
            m.loadMessages();
            List<Message> old=m.getMessages();

            while (true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m.loadMessages();
                if (old.size()<m.getMessages().size())
                {
                    old=m.getMessages();
                    SharedPreferences sp = getSharedPreferences("OURINFO", MODE_PRIVATE);
                    boolean ma = sp.getBoolean("active", false);
                    Log.e("andres", ma + "");
                    if (ma) {
                        loadData();
                    } else {
                        pushNotification();
                    }
                }



            }

        }

    }











}
