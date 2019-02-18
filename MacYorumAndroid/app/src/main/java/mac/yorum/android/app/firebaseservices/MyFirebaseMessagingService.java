package mac.yorum.android.app.firebaseservices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import mac.yorum.android.app.activities.LauncherActivity;
import yorum.mac.com.macyorumandroid.R;

/**
 * Created by gkhngngr on 27.2.2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        /*
        if (remoteMessage.getData().size() > 0 || remoteMessage.getNotification() != null)
        {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
        */

        if (remoteMessage.getData().size() > 0)
        {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
        if (remoteMessage.getNotification() != null)
        {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        }

    private void sendNotification(String messageTitle, String messageBody)
    {


        Intent notificationIntent = new Intent(this, LauncherActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setSmallIcon(R.drawable.logo);
        notificationBuilder.setContentTitle(messageTitle);
        notificationBuilder.setContentText(messageBody);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(resultPendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        try {

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationManager.notify(632623,notification );


    }
}
