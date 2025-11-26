package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class SplashBinding implements ViewBinding {
    public final ImageView imgLogo;
    public final ProgressBar progressWheel;
    private final RelativeLayout rootView;

    private SplashBinding(RelativeLayout rootView, ImageView imgLogo, ProgressBar progressWheel) {
        this.rootView = rootView;
        this.imgLogo = imgLogo;
        this.progressWheel = progressWheel;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static SplashBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SplashBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.splash, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SplashBinding bind(View rootView) {
        int i = R.id.img_logo;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.img_logo);
        if (imageView != null) {
            i = R.id.progress_wheel;
            ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progress_wheel);
            if (progressBar != null) {
                return new SplashBinding((RelativeLayout) rootView, imageView, progressBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
