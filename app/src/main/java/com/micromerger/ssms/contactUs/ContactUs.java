package com.micromerger.ssms.contactUs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class ContactUs extends Fragment {
    View mView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_contact_us, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }
}
