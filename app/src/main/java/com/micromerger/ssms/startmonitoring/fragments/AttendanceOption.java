package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class AttendanceOption extends BaseFragment implements View.OnClickListener {
    CardView musterRollImages;
    CardView takeAttendance;
    View view;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_attendance_option, (ViewGroup) null);
        this.view = viewInflate;
        CardView cardView = (CardView) viewInflate.findViewById(R.id.takeAttendance);
        this.takeAttendance = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.view.findViewById(R.id.musterRollImages);
        this.musterRollImages = cardView2;
        cardView2.setOnClickListener(this);
        return this.view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id2 = v.getId();
        if (id2 == R.id.musterRollImages) {
            changeFragment(new MusterRollImages());
        } else {
            if (id2 != R.id.takeAttendance) {
                return;
            }
            changeFragment(new TakeAttendance());
        }
    }

    void changeFragment(Fragment fragment) {
        this.ft = this.fm.beginTransaction();
        this.ft.replace(R.id.container, fragment);
        this.ft.addToBackStack(null);
        this.ft.commit();
    }
}
