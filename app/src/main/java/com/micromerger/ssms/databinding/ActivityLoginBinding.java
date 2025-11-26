package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivityLoginBinding implements ViewBinding {
    public final Button btnPrivacy;
    public final Button btnforgot;
    public final Button btnlogin;
    public final LinearLayout contentMainLinearlayout;
    public final EditText editPass;
    public final EditText editUser;
    public final ImageView maincontentLogo;
    public final RelativeLayout parent;
    private final RelativeLayout rootView;
    public final ScrollView scv;

    private ActivityLoginBinding(RelativeLayout rootView, Button btnPrivacy, Button btnforgot, Button btnlogin, LinearLayout contentMainLinearlayout, EditText editPass, EditText editUser, ImageView maincontentLogo, RelativeLayout parent, ScrollView scv) {
        this.rootView = rootView;
        this.btnPrivacy = btnPrivacy;
        this.btnforgot = btnforgot;
        this.btnlogin = btnlogin;
        this.contentMainLinearlayout = contentMainLinearlayout;
        this.editPass = editPass;
        this.editUser = editUser;
        this.maincontentLogo = maincontentLogo;
        this.parent = parent;
        this.scv = scv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLoginBinding bind(View rootView) {
        int i = R.id.btn_privacy;
        Button button = (Button) rootView.findViewById(R.id.btn_privacy);
        if (button != null) {
            i = R.id.btnforgot;
            Button button2 = (Button) rootView.findViewById(R.id.btnforgot);
            if (button2 != null) {
                i = R.id.btnlogin;
                Button button3 = (Button) rootView.findViewById(R.id.btnlogin);
                if (button3 != null) {
                    i = R.id.content_main_linearlayout;
                    LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.content_main_linearlayout);
                    if (linearLayout != null) {
                        i = R.id.edit_pass;
                        EditText editText = (EditText) rootView.findViewById(R.id.edit_pass);
                        if (editText != null) {
                            i = R.id.edit_user;
                            EditText editText2 = (EditText) rootView.findViewById(R.id.edit_user);
                            if (editText2 != null) {
                                i = R.id.maincontent_logo;
                                ImageView imageView = (ImageView) rootView.findViewById(R.id.maincontent_logo);
                                if (imageView != null) {
                                    RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                    i = R.id.scv;
                                    ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scv);
                                    if (scrollView != null) {
                                        return new ActivityLoginBinding(relativeLayout, button, button2, button3, linearLayout, editText, editText2, imageView, relativeLayout, scrollView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
