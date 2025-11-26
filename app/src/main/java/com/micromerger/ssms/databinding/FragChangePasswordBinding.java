package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragChangePasswordBinding implements ViewBinding {
    public final Button btnDone;
    public final EditText etComfirmPw;
    public final EditText etNewPw;
    public final EditText etOldPw;
    public final RelativeLayout parentView;
    private final RelativeLayout rootView;
    public final TextView tvComfirmPw;
    public final TextView tvCpTitle;
    public final TextView tvNewPw;
    public final TextView tvOldPw;

    private FragChangePasswordBinding(RelativeLayout rootView, Button btnDone, EditText etComfirmPw, EditText etNewPw, EditText etOldPw, RelativeLayout parentView, TextView tvComfirmPw, TextView tvCpTitle, TextView tvNewPw, TextView tvOldPw) {
        this.rootView = rootView;
        this.btnDone = btnDone;
        this.etComfirmPw = etComfirmPw;
        this.etNewPw = etNewPw;
        this.etOldPw = etOldPw;
        this.parentView = parentView;
        this.tvComfirmPw = tvComfirmPw;
        this.tvCpTitle = tvCpTitle;
        this.tvNewPw = tvNewPw;
        this.tvOldPw = tvOldPw;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragChangePasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragChangePasswordBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_change_password, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragChangePasswordBinding bind(View rootView) {
        int i = R.id.btn_done;
        Button button = (Button) rootView.findViewById(R.id.btn_done);
        if (button != null) {
            i = R.id.et_comfirm_pw;
            EditText editText = (EditText) rootView.findViewById(R.id.et_comfirm_pw);
            if (editText != null) {
                i = R.id.et_new_pw;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_new_pw);
                if (editText2 != null) {
                    i = R.id.et_old_pw;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_old_pw);
                    if (editText3 != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) rootView;
                        i = R.id.tv_comfirm_pw;
                        TextView textView = (TextView) rootView.findViewById(R.id.tv_comfirm_pw);
                        if (textView != null) {
                            i = R.id.tv_cp_title;
                            TextView textView2 = (TextView) rootView.findViewById(R.id.tv_cp_title);
                            if (textView2 != null) {
                                i = R.id.tv_new_pw;
                                TextView textView3 = (TextView) rootView.findViewById(R.id.tv_new_pw);
                                if (textView3 != null) {
                                    i = R.id.tv_old_pw;
                                    TextView textView4 = (TextView) rootView.findViewById(R.id.tv_old_pw);
                                    if (textView4 != null) {
                                        return new FragChangePasswordBinding(relativeLayout, button, editText, editText2, editText3, relativeLayout, textView, textView2, textView3, textView4);
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
