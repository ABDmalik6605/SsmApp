package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentStartMonitoringDetailsBinding implements ViewBinding {
    public final ImageView attendanceArrow;
    public final ImageView attendanceIcon;
    public final CardView attendanceLayout;
    public final Button btnEndMonitoring;
    public final ImageView censusArrow;
    public final ImageView censusIcon;
    public final CardView censusLayout;
    public final ImageView enrollmentArrow;
    public final ImageView enrollmentIcon;
    public final CardView enrollmentLayout;
    public final EditText etFinalRemarks;
    public final ImageView floodArrow;
    public final ImageView floodIcon;
    public final CardView floodLayout;
    private final RelativeLayout rootView;
    public final ImageView smcArrow;
    public final ImageView smcIcon;
    public final CardView smcLayout;
    public final TextView tvComments;

    private FragmentStartMonitoringDetailsBinding(RelativeLayout rootView, ImageView attendanceArrow, ImageView attendanceIcon, CardView attendanceLayout, Button btnEndMonitoring, ImageView censusArrow, ImageView censusIcon, CardView censusLayout, ImageView enrollmentArrow, ImageView enrollmentIcon, CardView enrollmentLayout, EditText etFinalRemarks, ImageView floodArrow, ImageView floodIcon, CardView floodLayout, ImageView smcArrow, ImageView smcIcon, CardView smcLayout, TextView tvComments) {
        this.rootView = rootView;
        this.attendanceArrow = attendanceArrow;
        this.attendanceIcon = attendanceIcon;
        this.attendanceLayout = attendanceLayout;
        this.btnEndMonitoring = btnEndMonitoring;
        this.censusArrow = censusArrow;
        this.censusIcon = censusIcon;
        this.censusLayout = censusLayout;
        this.enrollmentArrow = enrollmentArrow;
        this.enrollmentIcon = enrollmentIcon;
        this.enrollmentLayout = enrollmentLayout;
        this.etFinalRemarks = etFinalRemarks;
        this.floodArrow = floodArrow;
        this.floodIcon = floodIcon;
        this.floodLayout = floodLayout;
        this.smcArrow = smcArrow;
        this.smcIcon = smcIcon;
        this.smcLayout = smcLayout;
        this.tvComments = tvComments;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentStartMonitoringDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentStartMonitoringDetailsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_start_monitoring_details, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentStartMonitoringDetailsBinding bind(View rootView) {
        int i = R.id.attendanceArrow;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.attendanceArrow);
        if (imageView != null) {
            i = R.id.attendanceIcon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.attendanceIcon);
            if (imageView2 != null) {
                i = R.id.attendanceLayout;
                CardView cardView = (CardView) rootView.findViewById(R.id.attendanceLayout);
                if (cardView != null) {
                    i = R.id.btn_end_monitoring;
                    Button button = (Button) rootView.findViewById(R.id.btn_end_monitoring);
                    if (button != null) {
                        i = R.id.censusArrow;
                        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.censusArrow);
                        if (imageView3 != null) {
                            i = R.id.censusIcon;
                            ImageView imageView4 = (ImageView) rootView.findViewById(R.id.censusIcon);
                            if (imageView4 != null) {
                                i = R.id.censusLayout;
                                CardView cardView2 = (CardView) rootView.findViewById(R.id.censusLayout);
                                if (cardView2 != null) {
                                    i = R.id.enrollmentArrow;
                                    ImageView imageView5 = (ImageView) rootView.findViewById(R.id.enrollmentArrow);
                                    if (imageView5 != null) {
                                        i = R.id.enrollmentIcon;
                                        ImageView imageView6 = (ImageView) rootView.findViewById(R.id.enrollmentIcon);
                                        if (imageView6 != null) {
                                            i = R.id.enrollmentLayout;
                                            CardView cardView3 = (CardView) rootView.findViewById(R.id.enrollmentLayout);
                                            if (cardView3 != null) {
                                                i = R.id.et_final_remarks;
                                                EditText editText = (EditText) rootView.findViewById(R.id.et_final_remarks);
                                                if (editText != null) {
                                                    i = R.id.floodArrow;
                                                    ImageView imageView7 = (ImageView) rootView.findViewById(R.id.floodArrow);
                                                    if (imageView7 != null) {
                                                        i = R.id.floodIcon;
                                                        ImageView imageView8 = (ImageView) rootView.findViewById(R.id.floodIcon);
                                                        if (imageView8 != null) {
                                                            i = R.id.floodLayout;
                                                            CardView cardView4 = (CardView) rootView.findViewById(R.id.floodLayout);
                                                            if (cardView4 != null) {
                                                                i = R.id.smcArrow;
                                                                ImageView imageView9 = (ImageView) rootView.findViewById(R.id.smcArrow);
                                                                if (imageView9 != null) {
                                                                    i = R.id.smcIcon;
                                                                    ImageView imageView10 = (ImageView) rootView.findViewById(R.id.smcIcon);
                                                                    if (imageView10 != null) {
                                                                        i = R.id.smcLayout;
                                                                        CardView cardView5 = (CardView) rootView.findViewById(R.id.smcLayout);
                                                                        if (cardView5 != null) {
                                                                            i = R.id.tv_comments;
                                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                            if (textView != null) {
                                                                                return new FragmentStartMonitoringDetailsBinding((RelativeLayout) rootView, imageView, imageView2, cardView, button, imageView3, imageView4, cardView2, imageView5, imageView6, cardView3, editText, imageView7, imageView8, cardView4, imageView9, imageView10, cardView5, textView);
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
