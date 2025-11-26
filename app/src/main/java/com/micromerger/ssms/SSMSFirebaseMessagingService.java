package com.micromerger.ssms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.micromerger.ssms.main.MainActivity;

/* loaded from: classes2.dex */
public class SSMSFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        sendNotification(remoteMessage.getNotification().getBody());
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(67108864);
        ((NotificationManager) getSystemService("notification")).notify(0, new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Dubai Sports Council").setContentText(messageBody).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).setLights(SupportMenu.CATEGORY_MASK, PathInterpolatorCompat.MAX_NUM_POINTS, PathInterpolatorCompat.MAX_NUM_POINTS).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824)).build());
    }
}
