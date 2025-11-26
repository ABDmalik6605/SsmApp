package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragChangepassBinding implements ViewBinding {
    public final Button btnChangePw;
    private final LinearLayout rootView;
    public final TextView tvAppVer;

    private FragChangepassBinding(LinearLayout rootView, Button btnChangePw, TextView tvAppVer) {
        this.rootView = rootView;
        this.btnChangePw = btnChangePw;
        this.tvAppVer = tvAppVer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragChangepassBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragChangepassBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_changepass, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragChangepassBinding bind(View rootView) {
        int i = R.id.btn_change_pw;
        Button button = (Button) rootView.findViewById(R.id.btn_change_pw);
        if (button != null) {
            i = R.id.tv_app_ver;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_app_ver);
            if (textView != null) {
                return new FragChangepassBinding((LinearLayout) rootView, button, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
