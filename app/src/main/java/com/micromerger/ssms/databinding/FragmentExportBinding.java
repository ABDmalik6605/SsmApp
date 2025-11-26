package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentExportBinding implements ViewBinding {
    public final Button downloadBtn;
    public final TextView fromDate;
    public final LinearLayout llFromToDate;
    public final Button openBtn;
    public final ProgressBar progressBar;
    public final TextView progressText;
    private final FrameLayout rootView;
    public final TextView toDate;
    public final TextView tvFromDate;
    public final TextView tvToDate;

    private FragmentExportBinding(FrameLayout rootView, Button downloadBtn, TextView fromDate, LinearLayout llFromToDate, Button openBtn, ProgressBar progressBar, TextView progressText, TextView toDate, TextView tvFromDate, TextView tvToDate) {
        this.rootView = rootView;
        this.downloadBtn = downloadBtn;
        this.fromDate = fromDate;
        this.llFromToDate = llFromToDate;
        this.openBtn = openBtn;
        this.progressBar = progressBar;
        this.progressText = progressText;
        this.toDate = toDate;
        this.tvFromDate = tvFromDate;
        this.tvToDate = tvToDate;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentExportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentExportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_export, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentExportBinding bind(View rootView) {
        int i = R.id.downloadBtn;
        Button button = (Button) rootView.findViewById(R.id.downloadBtn);
        if (button != null) {
            i = R.id.from_date;
            TextView textView = (TextView) rootView.findViewById(R.id.from_date);
            if (textView != null) {
                i = R.id.ll_from_to_date;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_from_to_date);
                if (linearLayout != null) {
                    i = R.id.openBtn;
                    Button button2 = (Button) rootView.findViewById(R.id.openBtn);
                    if (button2 != null) {
                        i = R.id.progressBar;
                        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                        if (progressBar != null) {
                            i = R.id.progressText;
                            TextView textView2 = (TextView) rootView.findViewById(R.id.progressText);
                            if (textView2 != null) {
                                i = R.id.to_date;
                                TextView textView3 = (TextView) rootView.findViewById(R.id.to_date);
                                if (textView3 != null) {
                                    i = R.id.tv_from_date;
                                    TextView textView4 = (TextView) rootView.findViewById(R.id.tv_from_date);
                                    if (textView4 != null) {
                                        i = R.id.tv_to_date;
                                        TextView textView5 = (TextView) rootView.findViewById(R.id.tv_to_date);
                                        if (textView5 != null) {
                                            return new FragmentExportBinding((FrameLayout) rootView, button, textView, linearLayout, button2, progressBar, textView2, textView3, textView4, textView5);
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
