package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class NavHeaderMainBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvUserEmail;
    public final TextView tvUserName;
    public final TextView tvUserPhone;

    private NavHeaderMainBinding(LinearLayout rootView, TextView tvUserEmail, TextView tvUserName, TextView tvUserPhone) {
        this.rootView = rootView;
        this.tvUserEmail = tvUserEmail;
        this.tvUserName = tvUserName;
        this.tvUserPhone = tvUserPhone;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static NavHeaderMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NavHeaderMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.nav_header_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static NavHeaderMainBinding bind(View rootView) {
        int i = R.id.tv_user_email;
        TextView textView = (TextView) rootView.findViewById(R.id.tv_user_email);
        if (textView != null) {
            i = R.id.tv_user_name;
            TextView textView2 = (TextView) rootView.findViewById(R.id.tv_user_name);
            if (textView2 != null) {
                i = R.id.tv_user_phone;
                TextView textView3 = (TextView) rootView.findViewById(R.id.tv_user_phone);
                if (textView3 != null) {
                    return new NavHeaderMainBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
