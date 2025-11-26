package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class CameraPdfOptionSheetBinding implements ViewBinding {
    public final Button btnCancel;
    public final LinearLayout optionCamera;
    public final ImageView optionPdf;
    public final LinearLayout optionsLayout;
    private final RelativeLayout rootView;
    public final TextView txtTitle;

    private CameraPdfOptionSheetBinding(RelativeLayout rootView, Button btnCancel, LinearLayout optionCamera, ImageView optionPdf, LinearLayout optionsLayout, TextView txtTitle) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.optionCamera = optionCamera;
        this.optionPdf = optionPdf;
        this.optionsLayout = optionsLayout;
        this.txtTitle = txtTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static CameraPdfOptionSheetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CameraPdfOptionSheetBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.camera_pdf_option_sheet, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CameraPdfOptionSheetBinding bind(View rootView) {
        int i = R.id.btn_cancel;
        Button button = (Button) rootView.findViewById(R.id.btn_cancel);
        if (button != null) {
            i = R.id.option_camera;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.option_camera);
            if (linearLayout != null) {
                i = R.id.option_pdf;
                ImageView imageView = (ImageView) rootView.findViewById(R.id.option_pdf);
                if (imageView != null) {
                    i = R.id.options_layout;
                    LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.options_layout);
                    if (linearLayout2 != null) {
                        i = R.id.txt_title;
                        TextView textView = (TextView) rootView.findViewById(R.id.txt_title);
                        if (textView != null) {
                            return new CameraPdfOptionSheetBinding((RelativeLayout) rootView, button, linearLayout, imageView, linearLayout2, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
