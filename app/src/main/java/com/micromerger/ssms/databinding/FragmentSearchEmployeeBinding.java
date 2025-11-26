package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentSearchEmployeeBinding implements ViewBinding {
    public final Button btnSearch;
    public final EditText editId;
    public final EditText editName;
    public final EditText editNic;
    public final RelativeLayout finger;
    private final LinearLayout rootView;
    public final TextView tvId;
    public final TextView tvName;
    public final TextView tvNic;
    public final TextView tvScan;

    private FragmentSearchEmployeeBinding(LinearLayout rootView, Button btnSearch, EditText editId, EditText editName, EditText editNic, RelativeLayout finger, TextView tvId, TextView tvName, TextView tvNic, TextView tvScan) {
        this.rootView = rootView;
        this.btnSearch = btnSearch;
        this.editId = editId;
        this.editName = editName;
        this.editNic = editNic;
        this.finger = finger;
        this.tvId = tvId;
        this.tvName = tvName;
        this.tvNic = tvNic;
        this.tvScan = tvScan;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchEmployeeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSearchEmployeeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_search_employee, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSearchEmployeeBinding bind(View rootView) {
        int i = R.id.btn_search;
        Button button = (Button) rootView.findViewById(R.id.btn_search);
        if (button != null) {
            i = R.id.edit_id;
            EditText editText = (EditText) rootView.findViewById(R.id.edit_id);
            if (editText != null) {
                i = R.id.edit_name;
                EditText editText2 = (EditText) rootView.findViewById(R.id.edit_name);
                if (editText2 != null) {
                    i = R.id.edit_nic;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.edit_nic);
                    if (editText3 != null) {
                        i = R.id.finger;
                        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.finger);
                        if (relativeLayout != null) {
                            i = R.id.tv_id;
                            TextView textView = (TextView) rootView.findViewById(R.id.tv_id);
                            if (textView != null) {
                                i = R.id.tv_name;
                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_name);
                                if (textView2 != null) {
                                    i = R.id.tv_nic;
                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_nic);
                                    if (textView3 != null) {
                                        i = R.id.tv_scan;
                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_scan);
                                        if (textView4 != null) {
                                            return new FragmentSearchEmployeeBinding((LinearLayout) rootView, button, editText, editText2, editText3, relativeLayout, textView, textView2, textView3, textView4);
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
