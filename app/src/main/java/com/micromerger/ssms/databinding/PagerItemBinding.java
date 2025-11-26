package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class PagerItemBinding implements ViewBinding {
    public final ImageView imgPagerItem;
    private final LinearLayout rootView;

    private PagerItemBinding(LinearLayout rootView, ImageView imgPagerItem) {
        this.rootView = rootView;
        this.imgPagerItem = imgPagerItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PagerItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PagerItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.pager_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PagerItemBinding bind(View rootView) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.img_pager_item);
        if (imageView != null) {
            return new PagerItemBinding((LinearLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.img_pager_item)));
    }
}
