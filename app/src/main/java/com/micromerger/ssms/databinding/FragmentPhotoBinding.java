package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public abstract class FragmentPhotoBinding extends ViewDataBinding {
    public final ImageButton doneButton;
    public final LinearLayout linearLayout;
    public final ImageView photo;
    public final ProgressBar progress;
    public final ImageButton retakeButton;

    protected FragmentPhotoBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageButton doneButton, LinearLayout linearLayout, ImageView photo, ProgressBar progress, ImageButton retakeButton) {
        super(_bindingComponent, _root, _localFieldCount);
        this.doneButton = doneButton;
        this.linearLayout = linearLayout;
        this.photo = photo;
        this.progress = progress;
        this.retakeButton = retakeButton;
    }

    public static FragmentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPhotoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_photo, root, attachToRoot, component);
    }

    public static FragmentPhotoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPhotoBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPhotoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_photo, null, false, component);
    }

    public static FragmentPhotoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPhotoBinding bind(View view, Object component) {
        return (FragmentPhotoBinding) bind(component, view, R.layout.fragment_photo);
    }
}
