package app.alcaldiaitagui;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import app.alcaldiaitagui.Fragments.ListaNoticias;
import app.alcaldiaitagui.Fragments.Menu;

/**
 * Created by alejandroduquecorrea on 5/12/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
         String mensaje=(remoteMessage.getNotification().getBody());
        String title=(remoteMessage.getNotification().getTitle());

        ShowNotification(mensaje,title);

    }

    private void ShowNotification(String mensaje,String Title) {

        Intent i =new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        long[] patern = new long[]{ 1000, 1000, 1000, 1000, 1000};
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(Title)
                .setContentText(mensaje)
                .setLights(Color.BLUE, 500, 500)
                .setSmallIcon(R.drawable.splash_img)
                .setVibrate(patern)
                .setSound(alarmSound)
                .setContentIntent(pendingIntent);
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
