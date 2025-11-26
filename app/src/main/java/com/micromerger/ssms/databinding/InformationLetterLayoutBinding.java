package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class InformationLetterLayoutBinding implements ViewBinding {
    public final ImageView informationLetterCameraIcon;
    public final LinearLayout informationLetterLayout;
    public final ImageView informationLetterPicture;
    private final LinearLayout rootView;
    public final TextView tvInformationLetter;

    private InformationLetterLayoutBinding(LinearLayout rootView, ImageView informationLetterCameraIcon, LinearLayout informationLetterLayout, ImageView informationLetterPicture, TextView tvInformationLetter) {
        this.rootView = rootView;
        this.informationLetterCameraIcon = informationLetterCameraIcon;
        this.informationLetterLayout = informationLetterLayout;
        this.informationLetterPicture = informationLetterPicture;
        this.tvInformationLetter = tvInformationLetter;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static InformationLetterLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static InformationLetterLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.information_letter_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static InformationLetterLayoutBinding bind(View rootView) {
        int i = R.id.information_letter_camera_icon;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.information_letter_camera_icon);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.information_letter_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.information_letter_picture);
            if (imageView2 != null) {
                i = R.id.tv_information_letter;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_information_letter);
                if (textView != null) {
                    return new InformationLetterLayoutBinding(linearLayout, imageView, linearLayout, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
