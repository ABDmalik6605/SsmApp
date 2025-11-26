package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FileNameDialogBinding implements ViewBinding {
    public final EditText fileName;
    private final LinearLayout rootView;

    private FileNameDialogBinding(LinearLayout rootView, EditText fileName) {
        this.rootView = rootView;
        this.fileName = fileName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FileNameDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FileNameDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.file_name_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FileNameDialogBinding bind(View rootView) {
        EditText editText = (EditText) rootView.findViewById(R.id.file_name);
        if (editText != null) {
            return new FileNameDialogBinding((LinearLayout) rootView, editText);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.file_name)));
    }
}
