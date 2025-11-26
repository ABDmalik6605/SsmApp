package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class UserCreateDialogBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final EditText userDescription;
    public final TextView userDescriptionText;
    public final EditText userName;
    public final TextView userNameText;

    private UserCreateDialogBinding(LinearLayout rootView, EditText userDescription, TextView userDescriptionText, EditText userName, TextView userNameText) {
        this.rootView = rootView;
        this.userDescription = userDescription;
        this.userDescriptionText = userDescriptionText;
        this.userName = userName;
        this.userNameText = userNameText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static UserCreateDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static UserCreateDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.user_create_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static UserCreateDialogBinding bind(View rootView) {
        int i = R.id.user_description;
        EditText editText = (EditText) rootView.findViewById(R.id.user_description);
        if (editText != null) {
            i = R.id.user_description_text;
            TextView textView = (TextView) rootView.findViewById(R.id.user_description_text);
            if (textView != null) {
                i = R.id.user_name;
                EditText editText2 = (EditText) rootView.findViewById(R.id.user_name);
                if (editText2 != null) {
                    i = R.id.user_name_text;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.user_name_text);
                    if (textView2 != null) {
                        return new UserCreateDialogBinding((LinearLayout) rootView, editText, textView, editText2, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
