package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragAboutBinding implements ViewBinding {
    private final ScrollView rootView;
    public final TextView tvAbout;

    private FragAboutBinding(ScrollView rootView, TextView tvAbout) {
        this.rootView = rootView;
        this.tvAbout = tvAbout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static FragAboutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragAboutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_about, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragAboutBinding bind(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tvAbout);
        if (textView != null) {
            return new FragAboutBinding((ScrollView) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.tvAbout)));
    }
}
