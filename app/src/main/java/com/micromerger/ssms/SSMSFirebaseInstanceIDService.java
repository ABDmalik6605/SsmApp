package com.micromerger.ssms;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/* loaded from: classes2.dex */
public class SSMSFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private void sendRegistrationToServer(String token) {
    }

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("Refreshed token: ", token + " ");
        sendRegistrationToServer(token);
    }
}
