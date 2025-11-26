package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class DialogUnsyncSchoolsBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final RecyclerView rvSchoolsUnSyncDataDetails;
    public final TextView tvListUnsynsSchools;
    public final View viewLine;

    private DialogUnsyncSchoolsBinding(RelativeLayout rootView, RecyclerView rvSchoolsUnSyncDataDetails, TextView tvListUnsynsSchools, View viewLine) {
        this.rootView = rootView;
        this.rvSchoolsUnSyncDataDetails = rvSchoolsUnSyncDataDetails;
        this.tvListUnsynsSchools = tvListUnsynsSchools;
        this.viewLine = viewLine;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogUnsyncSchoolsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogUnsyncSchoolsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_unsync_schools, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogUnsyncSchoolsBinding bind(View rootView) {
        int i = R.id.rv_schools_unSyncData_details;
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_schools_unSyncData_details);
        if (recyclerView != null) {
            i = R.id.tv_list_unsynsSchools;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_list_unsynsSchools);
            if (textView != null) {
                i = R.id.view_line;
                View viewFindViewById = rootView.findViewById(R.id.view_line);
                if (viewFindViewById != null) {
                    return new DialogUnsyncSchoolsBinding((RelativeLayout) rootView, recyclerView, textView, viewFindViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
