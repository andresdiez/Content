package com.example.adiez.content.backgroundprocess;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.adiez.content.MainActivity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class ContentBackService extends Service{


    private final int NOTIFICATION_ID=0;
    private final String ACTION="com.listFragment.action";
    private final String url = "http://172.16.9.254:8080/count";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Class<Count> object = Count.class;


    @Override
    public void onCreate() {
        super.onCreate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Async().execute();

        return Service.START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class Async extends AsyncTask <Void,Boolean,Void>{


        private int oldId=-1;

        @Override
        protected Void doInBackground(Void... params) {
            while (true){

                if (oldId==-1){
                    oldId=restTemplate.getForObject(url, Count.class).getId();
                }
                else {
                    int id=restTemplate.getForObject(url, Count.class).getId();
                    if (id>oldId){
                        oldId=id;
                        publishProgress(true);
                    }
                }


                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);


            if (values[0]){
                boolean active=isBackgroundRunning(getApplicationContext());
                if (active){
                    local();
                }
                else{
                    push();
                }
            }


        }

    }



    private void local(){
        Intent local = new Intent();
        local.putExtra("test", true);
        local.setAction(ACTION);
        sendBroadcast(local);
    }



    private void push(){
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


    private boolean isBackgroundRunning(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.equals(context.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }








}
