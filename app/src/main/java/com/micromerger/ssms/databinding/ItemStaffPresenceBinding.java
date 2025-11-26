package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ItemStaffPresenceBinding implements ViewBinding {
    public final CheckBox cbIsSynced;
    public final TextView cnic;
    public final LinearLayout cnicLayout;
    public final ImageView deleteRecord;
    public final TextView name;
    public final LinearLayout nameLayout;
    public final TextView personalNo;
    public final LinearLayout personalNoLayout;
    private final CardView rootView;
    public final TextView status;
    public final LinearLayout statusLayout;

    private ItemStaffPresenceBinding(CardView rootView, CheckBox cbIsSynced, TextView cnic, LinearLayout cnicLayout, ImageView deleteRecord, TextView name, LinearLayout nameLayout, TextView personalNo, LinearLayout personalNoLayout, TextView status, LinearLayout statusLayout) {
        this.rootView = rootView;
        this.cbIsSynced = cbIsSynced;
        this.cnic = cnic;
        this.cnicLayout = cnicLayout;
        this.deleteRecord = deleteRecord;
        this.name = name;
        this.nameLayout = nameLayout;
        this.personalNo = personalNo;
        this.personalNoLayout = personalNoLayout;
        this.status = status;
        this.statusLayout = statusLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemStaffPresenceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemStaffPresenceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_staff_presence, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemStaffPresenceBinding bind(View rootView) {
        int i = R.id.cb_is_synced;
        CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.cb_is_synced);
        if (checkBox != null) {
            i = R.id.cnic;
            TextView textView = (TextView) rootView.findViewById(R.id.cnic);
            if (textView != null) {
                i = R.id.cnic_layout;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.cnic_layout);
                if (linearLayout != null) {
                    i = R.id.delete_record;
                    ImageView imageView = (ImageView) rootView.findViewById(R.id.delete_record);
                    if (imageView != null) {
                        i = R.id.name;
                        TextView textView2 = (TextView) rootView.findViewById(R.id.name);
                        if (textView2 != null) {
                            i = R.id.name_layout;
                            LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.name_layout);
                            if (linearLayout2 != null) {
                                i = R.id.personal_no;
                                TextView textView3 = (TextView) rootView.findViewById(R.id.personal_no);
                                if (textView3 != null) {
                                    i = R.id.personal_no_layout;
                                    LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.personal_no_layout);
                                    if (linearLayout3 != null) {
                                        i = R.id.status;
                                        TextView textView4 = (TextView) rootView.findViewById(R.id.status);
                                        if (textView4 != null) {
                                            i = R.id.status_layout;
                                            LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.status_layout);
                                            if (linearLayout4 != null) {
                                                return new ItemStaffPresenceBinding((CardView) rootView, checkBox, textView, linearLayout, imageView, textView2, linearLayout2, textView3, linearLayout3, textView4, linearLayout4);
                                            }
                                        }
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
