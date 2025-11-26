package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class GovtLetterPictureLayoutBinding implements ViewBinding {
    public final ImageView govtLetterPicture;
    public final ImageView govtLetterPictureCameraIcon;
    public final LinearLayout govtLetterPictureLayout;
    private final LinearLayout rootView;

    private GovtLetterPictureLayoutBinding(LinearLayout rootView, ImageView govtLetterPicture, ImageView govtLetterPictureCameraIcon, LinearLayout govtLetterPictureLayout) {
        this.rootView = rootView;
        this.govtLetterPicture = govtLetterPicture;
        this.govtLetterPictureCameraIcon = govtLetterPictureCameraIcon;
        this.govtLetterPictureLayout = govtLetterPictureLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static GovtLetterPictureLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static GovtLetterPictureLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.govt_letter_picture_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static GovtLetterPictureLayoutBinding bind(View rootView) {
        int i = R.id.govt_letter_picture;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.govt_letter_picture);
        if (imageView != null) {
            i = R.id.govt_letter_picture_camera_icon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.govt_letter_picture_camera_icon);
            if (imageView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                return new GovtLetterPictureLayoutBinding(linearLayout, imageView, imageView2, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
