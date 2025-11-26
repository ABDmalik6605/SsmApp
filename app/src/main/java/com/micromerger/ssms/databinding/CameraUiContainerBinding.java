package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class CameraUiContainerBinding implements ViewBinding {
    public final ImageButton cameraCaptureButton;
    public final ImageButton cameraCaptureButton1;
    public final ImageButton cameraCloseButton;
    public final ImageButton cameraFlashButton;
    public final RelativeLayout cameraUiContainer;
    private final RelativeLayout rootView;

    private CameraUiContainerBinding(RelativeLayout rootView, ImageButton cameraCaptureButton, ImageButton cameraCaptureButton1, ImageButton cameraCloseButton, ImageButton cameraFlashButton, RelativeLayout cameraUiContainer) {
        this.rootView = rootView;
        this.cameraCaptureButton = cameraCaptureButton;
        this.cameraCaptureButton1 = cameraCaptureButton1;
        this.cameraCloseButton = cameraCloseButton;
        this.cameraFlashButton = cameraFlashButton;
        this.cameraUiContainer = cameraUiContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static CameraUiContainerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CameraUiContainerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.camera_ui_container, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CameraUiContainerBinding bind(View rootView) {
        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.camera_capture_button);
        ImageButton imageButton2 = (ImageButton) rootView.findViewById(R.id.camera_capture_button);
        ImageButton imageButton3 = (ImageButton) rootView.findViewById(R.id.camera_close_button);
        if (imageButton3 != null) {
            RelativeLayout relativeLayout = (RelativeLayout) rootView;
            return new CameraUiContainerBinding(relativeLayout, imageButton, imageButton2, imageButton3, (ImageButton) rootView.findViewById(R.id.camera_flash_button), relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.camera_close_button)));
    }
}
