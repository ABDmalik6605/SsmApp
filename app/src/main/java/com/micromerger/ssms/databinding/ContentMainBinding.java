package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ContentMainBinding implements ViewBinding {
    public final FrameLayout container;
    public final RelativeLayout contentMain;
    private final RelativeLayout rootView;

    private ContentMainBinding(RelativeLayout rootView, FrameLayout container, RelativeLayout contentMain) {
        this.rootView = rootView;
        this.container = container;
        this.contentMain = contentMain;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ContentMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ContentMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.content_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ContentMainBinding bind(View rootView) {
        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.container);
        if (frameLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) rootView;
            return new ContentMainBinding(relativeLayout, frameLayout, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.container)));
    }
}
