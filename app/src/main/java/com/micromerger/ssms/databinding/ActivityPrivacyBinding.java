package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivityPrivacyBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final ProgressBar progress;
    private final ConstraintLayout rootView;
    public final WebView webView;

    private ActivityPrivacyBinding(ConstraintLayout rootView, AppBarLayout appBar, ProgressBar progress, WebView webView) {
        this.rootView = rootView;
        this.appBar = appBar;
        this.progress = progress;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPrivacyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPrivacyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_privacy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPrivacyBinding bind(View rootView) {
        int i = R.id.appBar;
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.appBar);
        if (appBarLayout != null) {
            i = R.id.progress;
            ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
            if (progressBar != null) {
                i = R.id.webView;
                WebView webView = (WebView) rootView.findViewById(R.id.webView);
                if (webView != null) {
                    return new ActivityPrivacyBinding((ConstraintLayout) rootView, appBarLayout, progressBar, webView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
