package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentStaffPresenceBinding implements ViewBinding {
    public final Button addBtn;
    public final LinearLayout buttonsLayout;
    public final TextView noDataText;
    public final RecyclerView recyclerview;
    private final RelativeLayout rootView;
    public final Button syncBtn;

    private FragmentStaffPresenceBinding(RelativeLayout rootView, Button addBtn, LinearLayout buttonsLayout, TextView noDataText, RecyclerView recyclerview, Button syncBtn) {
        this.rootView = rootView;
        this.addBtn = addBtn;
        this.buttonsLayout = buttonsLayout;
        this.noDataText = noDataText;
        this.recyclerview = recyclerview;
        this.syncBtn = syncBtn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentStaffPresenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentStaffPresenceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_staff_presence, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentStaffPresenceBinding bind(View rootView) {
        int i = R.id.add_btn;
        Button button = (Button) rootView.findViewById(R.id.add_btn);
        if (button != null) {
            i = R.id.buttons_layout;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.buttons_layout);
            if (linearLayout != null) {
                i = R.id.no_data_text;
                TextView textView = (TextView) rootView.findViewById(R.id.no_data_text);
                if (textView != null) {
                    i = R.id.recyclerview;
                    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                    if (recyclerView != null) {
                        i = R.id.sync_btn;
                        Button button2 = (Button) rootView.findViewById(R.id.sync_btn);
                        if (button2 != null) {
                            return new FragmentStaffPresenceBinding((RelativeLayout) rootView, button, linearLayout, textView, recyclerView, button2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
