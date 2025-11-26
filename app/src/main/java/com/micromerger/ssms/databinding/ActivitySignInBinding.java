package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivitySignInBinding implements ViewBinding {
    public final RelativeLayout contentMain;
    public final Button contentMainBtnlogin;
    public final LinearLayout contentMainLinearlayout;
    public final ImageView maincontentLogo;
    private final RelativeLayout rootView;

    private ActivitySignInBinding(RelativeLayout rootView, RelativeLayout contentMain, Button contentMainBtnlogin, LinearLayout contentMainLinearlayout, ImageView maincontentLogo) {
        this.rootView = rootView;
        this.contentMain = contentMain;
        this.contentMainBtnlogin = contentMainBtnlogin;
        this.contentMainLinearlayout = contentMainLinearlayout;
        this.maincontentLogo = maincontentLogo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySignInBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySignInBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sign_in, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySignInBinding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        int i = R.id.content_main_btnlogin;
        Button button = (Button) rootView.findViewById(R.id.content_main_btnlogin);
        if (button != null) {
            i = R.id.content_main_linearlayout;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.content_main_linearlayout);
            if (linearLayout != null) {
                i = R.id.maincontent_logo;
                ImageView imageView = (ImageView) rootView.findViewById(R.id.maincontent_logo);
                if (imageView != null) {
                    return new ActivitySignInBinding(relativeLayout, relativeLayout, button, linearLayout, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
