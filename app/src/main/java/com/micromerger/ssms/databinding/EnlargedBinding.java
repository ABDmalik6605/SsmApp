package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class EnlargedBinding implements ViewBinding {
    public final ImageView enlargedImage;
    public final LinearLayout enlargedLayout;
    private final LinearLayout rootView;

    private EnlargedBinding(LinearLayout rootView, ImageView enlargedImage, LinearLayout enlargedLayout) {
        this.rootView = rootView;
        this.enlargedImage = enlargedImage;
        this.enlargedLayout = enlargedLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EnlargedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EnlargedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.enlarged, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static EnlargedBinding bind(View rootView) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.enlarged_image);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            return new EnlargedBinding(linearLayout, imageView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.enlarged_image)));
    }
}
