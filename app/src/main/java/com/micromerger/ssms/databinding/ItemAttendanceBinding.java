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
import com.micromerger.ssms.utils.widgets.MaskedEditText;

/* loaded from: classes2.dex */
public final class ItemAttendanceBinding implements ViewBinding {
    public final CardView cardView;
    public final CheckBox checked;
    private final LinearLayout rootView;
    public final MaskedEditText tvCnic;
    public final TextView tvEmployeeId;
    public final TextView tvJoiningDate;
    public final TextView tvName;
    public final TextView tvPresentAbsent;
    public final TextView tvRoleInOrganization;

    private ItemAttendanceBinding(LinearLayout rootView, CardView cardView, CheckBox checked, MaskedEditText tvCnic, TextView tvEmployeeId, TextView tvJoiningDate, TextView tvName, TextView tvPresentAbsent, TextView tvRoleInOrganization) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.checked = checked;
        this.tvCnic = tvCnic;
        this.tvEmployeeId = tvEmployeeId;
        this.tvJoiningDate = tvJoiningDate;
        this.tvName = tvName;
        this.tvPresentAbsent = tvPresentAbsent;
        this.tvRoleInOrganization = tvRoleInOrganization;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemAttendanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAttendanceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_attendance, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemAttendanceBinding bind(View rootView) {
        int i = R.id.card_view;
        CardView cardView = (CardView) rootView.findViewById(R.id.card_view);
        if (cardView != null) {
            i = R.id.checked;
            CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checked);
            if (checkBox != null) {
                i = R.id.tv_cnic;
                MaskedEditText maskedEditText = (MaskedEditText) rootView.findViewById(R.id.tv_cnic);
                if (maskedEditText != null) {
                    i = R.id.tv_employee_id;
                    TextView textView = (TextView) rootView.findViewById(R.id.tv_employee_id);
                    if (textView != null) {
                        i = R.id.tv_joining_date;
                        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_joining_date);
                        if (textView2 != null) {
                            i = R.id.tv_name;
                            TextView textView3 = (TextView) rootView.findViewById(R.id.tv_name);
                            if (textView3 != null) {
                                i = R.id.tv_presentAbsent;
                                TextView textView4 = (TextView) rootView.findViewById(R.id.tv_presentAbsent);
                                if (textView4 != null) {
                                    i = R.id.tv_RoleInOrganization;
                                    TextView textView5 = (TextView) rootView.findViewById(R.id.tv_RoleInOrganization);
                                    if (textView5 != null) {
                                        return new ItemAttendanceBinding((LinearLayout) rootView, cardView, checkBox, maskedEditText, textView, textView2, textView3, textView4, textView5);
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
