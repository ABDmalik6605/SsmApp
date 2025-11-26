package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class ActivityCameraBinding implements ViewBinding {
    public final FragmentContainerView fragmentContainer;
    private final FragmentContainerView rootView;

    private ActivityCameraBinding(FragmentContainerView rootView, FragmentContainerView fragmentContainer) {
        this.rootView = rootView;
        this.fragmentContainer = fragmentContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    public static ActivityCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCameraBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_camera, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCameraBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        FragmentContainerView fragmentContainerView = (FragmentContainerView) rootView;
        return new ActivityCameraBinding(fragmentContainerView, fragmentContainerView);
    }
}
