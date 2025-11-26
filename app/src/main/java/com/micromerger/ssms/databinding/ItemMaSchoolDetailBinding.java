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
public final class ItemMaSchoolDetailBinding implements ViewBinding {
    public final CardView cardView;
    public final CheckBox checked;
    private final LinearLayout rootView;
    public final TextView tvLblSemisCodeScheoolDetail;
    public final TextView tvMEndDate;
    public final TextView tvMStartTime;
    public final TextView tvSemisCodeScheoolDetail;
    public final TextView tvShoolName;

    private ItemMaSchoolDetailBinding(LinearLayout rootView, CardView cardView, CheckBox checked, TextView tvLblSemisCodeScheoolDetail, TextView tvMEndDate, TextView tvMStartTime, TextView tvSemisCodeScheoolDetail, TextView tvShoolName) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.checked = checked;
        this.tvLblSemisCodeScheoolDetail = tvLblSemisCodeScheoolDetail;
        this.tvMEndDate = tvMEndDate;
        this.tvMStartTime = tvMStartTime;
        this.tvSemisCodeScheoolDetail = tvSemisCodeScheoolDetail;
        this.tvShoolName = tvShoolName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemMaSchoolDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMaSchoolDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_ma_school_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMaSchoolDetailBinding bind(View rootView) {
        int i = R.id.card_view;
        CardView cardView = (CardView) rootView.findViewById(R.id.card_view);
        if (cardView != null) {
            i = R.id.checked;
            CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checked);
            if (checkBox != null) {
                i = R.id.tv_lbl_semisCodeScheoolDetail;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_lbl_semisCodeScheoolDetail);
                if (textView != null) {
                    i = R.id.tv_m_end_date;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_m_end_date);
                    if (textView2 != null) {
                        i = R.id.tv_m_start_time;
                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_m_start_time);
                        if (textView3 != null) {
                            i = R.id.tv_semisCodeScheoolDetail;
                            TextView textView4 = (TextView) rootView.findViewById(R.id.tv_semisCodeScheoolDetail);
                            if (textView4 != null) {
                                i = R.id.tv_shool_name;
                                TextView textView5 = (TextView) rootView.findViewById(R.id.tv_shool_name);
                                if (textView5 != null) {
                                    return new ItemMaSchoolDetailBinding((LinearLayout) rootView, cardView, checkBox, textView, textView2, textView3, textView4, textView5);
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
