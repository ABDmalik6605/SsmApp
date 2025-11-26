package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.camera.view.PreviewView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCameraBinding implements ViewBinding {
    public final RelativeLayout cameraContainer;
    private final RelativeLayout rootView;
    public final PreviewView viewFinder;

    private FragmentCameraBinding(RelativeLayout rootView, RelativeLayout cameraContainer, PreviewView viewFinder) {
        this.rootView = rootView;
        this.cameraContainer = cameraContainer;
        this.viewFinder = viewFinder;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCameraBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_camera, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCameraBinding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        PreviewView previewView = (PreviewView) rootView.findViewById(R.id.view_finder);
        if (previewView != null) {
            return new FragmentCameraBinding(relativeLayout, relativeLayout, previewView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.view_finder)));
    }
}
