package com.micromerger.ssms;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes2.dex */
public class EmptyActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.empty_progress_bar);
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.-$$Lambda$EmptyActivity$U5WHpcobrexpD7y-nz5rnRZdoyE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreate$0$EmptyActivity(progressBar);
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$onCreate$0$EmptyActivity(ProgressBar progressBar) {
        progressBar.setVisibility(8);
        finish();
    }
}
