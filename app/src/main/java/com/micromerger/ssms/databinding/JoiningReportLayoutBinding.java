package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class JoiningReportLayoutBinding implements ViewBinding {
    public final LinearLayout joiningReportLayout;
    public final ImageView joiningReportLayoutCameraIcon;
    public final ImageView joiningReportPicture;
    private final LinearLayout rootView;
    public final TextView tvJoiningReport;

    private JoiningReportLayoutBinding(LinearLayout rootView, LinearLayout joiningReportLayout, ImageView joiningReportLayoutCameraIcon, ImageView joiningReportPicture, TextView tvJoiningReport) {
        this.rootView = rootView;
        this.joiningReportLayout = joiningReportLayout;
        this.joiningReportLayoutCameraIcon = joiningReportLayoutCameraIcon;
        this.joiningReportPicture = joiningReportPicture;
        this.tvJoiningReport = tvJoiningReport;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static JoiningReportLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static JoiningReportLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.joining_report_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static JoiningReportLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.joining_report_layout_camera_icon;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.joining_report_layout_camera_icon);
        if (imageView != null) {
            i = R.id.joining_report_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.joining_report_picture);
            if (imageView2 != null) {
                i = R.id.tv_joining_report;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_joining_report);
                if (textView != null) {
                    return new JoiningReportLayoutBinding(linearLayout, linearLayout, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
