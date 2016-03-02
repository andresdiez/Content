package com.example.adiez.content.backgroundprocess;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.adiez.content.MainActivity;

public class PushNotifications extends Service {

    private final int NOTIFICATION_ID=0;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return Service.START_STICKY;

    }

    public boolean pushNotification(){
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
        return true;
    }





}
