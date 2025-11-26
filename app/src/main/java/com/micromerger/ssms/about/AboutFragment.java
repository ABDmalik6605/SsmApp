package com.micromerger.ssms.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class AboutFragment extends Fragment {
    TextView tvAbout;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.frag_about, container, false);
        this.tvAbout = (TextView) viewInflate.findViewById(R.id.tvAbout);
        return viewInflate;
    }
}
