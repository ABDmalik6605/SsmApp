package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.widgets.ClearableEditText;

/* loaded from: classes2.dex */
public final class SnackbarBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnSend;
    public final ClearableEditText etEmail;
    public final ClearableEditText etName;
    private final LinearLayout rootView;

    private SnackbarBinding(LinearLayout rootView, Button btnCancel, Button btnSend, ClearableEditText etEmail, ClearableEditText etName) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.btnSend = btnSend;
        this.etEmail = etEmail;
        this.etName = etName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SnackbarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SnackbarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.snackbar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SnackbarBinding bind(View rootView) {
        int i = R.id.btn_cancel;
        Button button = (Button) rootView.findViewById(R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_send;
            Button button2 = (Button) rootView.findViewById(R.id.btn_send);
            if (button2 != null) {
                i = R.id.et_email;
                ClearableEditText clearableEditText = (ClearableEditText) rootView.findViewById(R.id.et_email);
                if (clearableEditText != null) {
                    i = R.id.et_name;
                    ClearableEditText clearableEditText2 = (ClearableEditText) rootView.findViewById(R.id.et_name);
                    if (clearableEditText2 != null) {
                        return new SnackbarBinding((LinearLayout) rootView, button, button2, clearableEditText, clearableEditText2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
