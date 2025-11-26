package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class OtherPictureLayoutBinding implements ViewBinding {
    public final ImageView otherPicture;
    public final ImageView otherPictureCameraIcon;
    public final LinearLayout otherPictureLayout;
    private final LinearLayout rootView;

    private OtherPictureLayoutBinding(LinearLayout rootView, ImageView otherPicture, ImageView otherPictureCameraIcon, LinearLayout otherPictureLayout) {
        this.rootView = rootView;
        this.otherPicture = otherPicture;
        this.otherPictureCameraIcon = otherPictureCameraIcon;
        this.otherPictureLayout = otherPictureLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OtherPictureLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static OtherPictureLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.other_picture_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OtherPictureLayoutBinding bind(View rootView) {
        int i = R.id.other_picture;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.other_picture);
        if (imageView != null) {
            i = R.id.other_picture_camera_icon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.other_picture_camera_icon);
            if (imageView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                return new OtherPictureLayoutBinding(linearLayout, imageView, imageView2, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
