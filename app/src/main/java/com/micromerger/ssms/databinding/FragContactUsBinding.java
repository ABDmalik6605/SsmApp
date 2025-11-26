package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class FragContactUsBinding implements ViewBinding {
    private final LinearLayout rootView;

    private FragContactUsBinding(LinearLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragContactUsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragContactUsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_contact_us, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragContactUsBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new FragContactUsBinding((LinearLayout) rootView);
    }
}
