package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class ActivityGeofencingBinding implements ViewBinding {
    public final Button btnTryAgainLocation;
    public final AppBarLayout geofenceAppBarId;
    public final Toolbar geofenceToolBarId;
    public final ImageView gifImage;
    public final LinearLayout parentView;
    public final ProgressBar progressBarId;
    private final LinearLayout rootView;
    public final TextView tvAccuracy;
    public final TextView tvCountDownTimer;
    public final TextView tvLocationMessage;
    public final TextView tvOutsideFence;
    public final TextView tvWait;

    private ActivityGeofencingBinding(LinearLayout rootView, Button btnTryAgainLocation, AppBarLayout geofenceAppBarId, Toolbar geofenceToolBarId, ImageView gifImage, LinearLayout parentView, ProgressBar progressBarId, TextView tvAccuracy, TextView tvCountDownTimer, TextView tvLocationMessage, TextView tvOutsideFence, TextView tvWait) {
        this.rootView = rootView;
        this.btnTryAgainLocation = btnTryAgainLocation;
        this.geofenceAppBarId = geofenceAppBarId;
        this.geofenceToolBarId = geofenceToolBarId;
        this.gifImage = gifImage;
        this.parentView = parentView;
        this.progressBarId = progressBarId;
        this.tvAccuracy = tvAccuracy;
        this.tvCountDownTimer = tvCountDownTimer;
        this.tvLocationMessage = tvLocationMessage;
        this.tvOutsideFence = tvOutsideFence;
        this.tvWait = tvWait;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGeofencingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGeofencingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_geofencing, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGeofencingBinding bind(View rootView) {
        int i = R.id.btn_try_again_location;
        Button button = (Button) rootView.findViewById(R.id.btn_try_again_location);
        if (button != null) {
            i = R.id.geofenceAppBarId;
            AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.geofenceAppBarId);
            if (appBarLayout != null) {
                i = R.id.geofenceToolBarId;
                Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.geofenceToolBarId);
                if (toolbar != null) {
                    i = R.id.gif_image;
                    ImageView imageView = (ImageView) rootView.findViewById(R.id.gif_image);
                    if (imageView != null) {
                        LinearLayout linearLayout = (LinearLayout) rootView;
                        i = R.id.progressBarId;
                        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBarId);
                        if (progressBar != null) {
                            i = R.id.tv_accuracy;
                            TextView textView = (TextView) rootView.findViewById(R.id.tv_accuracy);
                            if (textView != null) {
                                i = R.id.tv_count_down_timer;
                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_count_down_timer);
                                if (textView2 != null) {
                                    i = R.id.tv_location_message;
                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_location_message);
                                    if (textView3 != null) {
                                        i = R.id.tv_outside_fence;
                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_outside_fence);
                                        if (textView4 != null) {
                                            i = R.id.tv_wait;
                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_wait);
                                            if (textView5 != null) {
                                                return new ActivityGeofencingBinding(linearLayout, button, appBarLayout, toolbar, imageView, linearLayout, progressBar, textView, textView2, textView3, textView4, textView5);
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
