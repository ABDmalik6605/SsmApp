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
public final class FragUserLocationBinding implements ViewBinding {
    public final Button btnUpdateLoc;
    private final LinearLayout rootView;
    public final TextView tvLat;
    public final TextView tvLong;
    public final TextView tvNote;

    private FragUserLocationBinding(LinearLayout rootView, Button btnUpdateLoc, TextView tvLat, TextView tvLong, TextView tvNote) {
        this.rootView = rootView;
        this.btnUpdateLoc = btnUpdateLoc;
        this.tvLat = tvLat;
        this.tvLong = tvLong;
        this.tvNote = tvNote;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragUserLocationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragUserLocationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_user_location, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragUserLocationBinding bind(View rootView) {
        int i = R.id.btn_update_loc;
        Button button = (Button) rootView.findViewById(R.id.btn_update_loc);
        if (button != null) {
            i = R.id.tv_lat;
            TextView textView = (TextView) rootView.findViewById(R.id.tv_lat);
            if (textView != null) {
                i = R.id.tv_long;
                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_long);
                if (textView2 != null) {
                    i = R.id.tv_note;
                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_note);
                    if (textView3 != null) {
                        return new FragUserLocationBinding((LinearLayout) rootView, button, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
