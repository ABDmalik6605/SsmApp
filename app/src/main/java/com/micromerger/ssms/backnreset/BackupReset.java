package com.micromerger.ssms.backnreset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;

/* loaded from: classes2.dex */
public class BackupReset extends BaseFragment {
    CardView card_backup;
    CardView card_reset;
    CardView card_restore;
    CardView card_sync;
    View mView;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.frag_backupnreset, (ViewGroup) null);
        loadingFinished();
        this.card_sync = (CardView) this.mView.findViewById(R.id.card_sync);
        this.card_reset = (CardView) this.mView.findViewById(R.id.card_reset);
        this.card_backup = (CardView) this.mView.findViewById(R.id.card_backup);
        this.card_restore = (CardView) this.mView.findViewById(R.id.card_restore);
        this.card_sync.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.BackupReset.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackupReset backupReset = BackupReset.this;
                backupReset.ft = backupReset.fm.beginTransaction();
                BackupReset.this.ft.replace(R.id.container, new SyncData());
                BackupReset.this.ft.addToBackStack(null);
                BackupReset.this.ft.commitAllowingStateLoss();
            }
        });
        this.card_reset.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.BackupReset.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackupReset backupReset = BackupReset.this;
                backupReset.ft = backupReset.fm.beginTransaction();
                BackupReset.this.ft.replace(R.id.container, new Reset());
                BackupReset.this.ft.addToBackStack(null);
                BackupReset.this.ft.commitAllowingStateLoss();
            }
        });
        this.card_backup.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.BackupReset.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackupReset backupReset = BackupReset.this;
                backupReset.ft = backupReset.fm.beginTransaction();
                BackupReset.this.ft.replace(R.id.container, new Backup());
                BackupReset.this.ft.addToBackStack(null);
                BackupReset.this.ft.commitAllowingStateLoss();
            }
        });
        this.card_restore.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.BackupReset.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackupReset backupReset = BackupReset.this;
                backupReset.ft = backupReset.fm.beginTransaction();
                BackupReset.this.ft.replace(R.id.container, new Restore());
                BackupReset.this.ft.addToBackStack(null);
                BackupReset.this.ft.commitAllowingStateLoss();
            }
        });
        return this.mView;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }
}
