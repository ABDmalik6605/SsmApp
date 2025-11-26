package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentSlidingImageBinding implements ViewBinding {
    public final ImageView ivTutorial;
    public final RelativeLayout llTutorial;
    private final LinearLayout rootView;

    private FragmentSlidingImageBinding(LinearLayout rootView, ImageView ivTutorial, RelativeLayout llTutorial) {
        this.rootView = rootView;
        this.ivTutorial = ivTutorial;
        this.llTutorial = llTutorial;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSlidingImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSlidingImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_sliding_image, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSlidingImageBinding bind(View rootView) {
        int i = R.id.iv_tutorial;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_tutorial);
        if (imageView != null) {
            i = R.id.ll_tutorial;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.ll_tutorial);
            if (relativeLayout != null) {
                return new FragmentSlidingImageBinding((LinearLayout) rootView, imageView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
