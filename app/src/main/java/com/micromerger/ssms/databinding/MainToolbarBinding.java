package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class MainToolbarBinding implements ViewBinding {
    public final RelativeLayout rlTitle;
    private final Toolbar rootView;
    public final ImageView sliderBtn;
    public final LinearLayout sliderLl;
    public final LinearLayout sliderLl2;
    public final Toolbar toolbar;
    public final TextView tvTitle;
    public final TextView tvVersionNumber;

    private MainToolbarBinding(Toolbar rootView, RelativeLayout rlTitle, ImageView sliderBtn, LinearLayout sliderLl, LinearLayout sliderLl2, Toolbar toolbar, TextView tvTitle, TextView tvVersionNumber) {
        this.rootView = rootView;
        this.rlTitle = rlTitle;
        this.sliderBtn = sliderBtn;
        this.sliderLl = sliderLl;
        this.sliderLl2 = sliderLl2;
        this.toolbar = toolbar;
        this.tvTitle = tvTitle;
        this.tvVersionNumber = tvVersionNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public Toolbar getRoot() {
        return this.rootView;
    }

    public static MainToolbarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MainToolbarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.main_toolbar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MainToolbarBinding bind(View rootView) {
        int i = R.id.rl_title;
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rl_title);
        if (relativeLayout != null) {
            i = R.id.sliderBtn;
            ImageView imageView = (ImageView) rootView.findViewById(R.id.sliderBtn);
            if (imageView != null) {
                i = R.id.slider_ll;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.slider_ll);
                if (linearLayout != null) {
                    i = R.id.slider_ll2;
                    LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.slider_ll2);
                    if (linearLayout2 != null) {
                        Toolbar toolbar = (Toolbar) rootView;
                        i = R.id.tv_title;
                        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
                        if (textView != null) {
                            return new MainToolbarBinding(toolbar, relativeLayout, imageView, linearLayout, linearLayout2, toolbar, textView, (TextView) rootView.findViewById(R.id.tv_versionNumber));
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
