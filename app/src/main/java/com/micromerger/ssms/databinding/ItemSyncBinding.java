package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ItemSyncBinding implements ViewBinding {
    public final CardView cardView;
    public final CheckBox checked;
    private final LinearLayout rootView;
    public final TextView tvDate;
    public final TextView tvEnd;
    public final TextView tvName;
    public final TextView tvSemes;
    public final TextView tvStart;

    private ItemSyncBinding(LinearLayout rootView, CardView cardView, CheckBox checked, TextView tvDate, TextView tvEnd, TextView tvName, TextView tvSemes, TextView tvStart) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.checked = checked;
        this.tvDate = tvDate;
        this.tvEnd = tvEnd;
        this.tvName = tvName;
        this.tvSemes = tvSemes;
        this.tvStart = tvStart;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemSyncBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSyncBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_sync, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSyncBinding bind(View rootView) {
        int i = R.id.card_view;
        CardView cardView = (CardView) rootView.findViewById(R.id.card_view);
        if (cardView != null) {
            i = R.id.checked;
            CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checked);
            if (checkBox != null) {
                i = R.id.tv_date;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_date);
                if (textView != null) {
                    i = R.id.tv_end;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_end);
                    if (textView2 != null) {
                        i = R.id.tv_name;
                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_name);
                        if (textView3 != null) {
                            i = R.id.tv_semes;
                            TextView textView4 = (TextView) rootView.findViewById(R.id.tv_semes);
                            if (textView4 != null) {
                                i = R.id.tv_start;
                                TextView textView5 = (TextView) rootView.findViewById(R.id.tv_start);
                                if (textView5 != null) {
                                    return new ItemSyncBinding((LinearLayout) rootView, cardView, checkBox, textView, textView2, textView3, textView4, textView5);
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
