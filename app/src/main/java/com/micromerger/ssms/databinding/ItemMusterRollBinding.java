package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ItemMusterRollBinding implements ViewBinding {
    public final ImageView deleteImage;
    public final ImageView image;
    private final LinearLayout rootView;

    private ItemMusterRollBinding(LinearLayout rootView, ImageView deleteImage, ImageView image) {
        this.rootView = rootView;
        this.deleteImage = deleteImage;
        this.image = image;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemMusterRollBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMusterRollBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_muster_roll, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMusterRollBinding bind(View rootView) {
        int i = R.id.deleteImage;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.deleteImage);
        if (imageView != null) {
            i = R.id.image;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.image);
            if (imageView2 != null) {
                return new ItemMusterRollBinding((LinearLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
