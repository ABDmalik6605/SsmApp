package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class IbScanLandBinding implements ViewBinding {
    public final TextView actionState;
    public final Spinner actionType;
    public final Spinner captureType;
    public final Button closeScannerBtn;
    public final TextView description;
    public final TextView deviceCount;
    public final TextView deviceCountTitle;
    public final TextView frameTime;
    public final TextView frameTimeTitle;
    public final LinearLayout infoLayout;
    public final ImageView logo;
    public final Button openScannerBtn;
    public final ImageView previewImage;
    public final Button refreshBtn;
    private final LinearLayout rootView;
    public final TextView scanStatesColor1;
    public final TextView scanStatesColor2;
    public final TextView scanStatesColor3;
    public final TextView scanStatesColor4;
    public final Button startCaptureBtn;
    public final TextView status;
    public final TextView statusTitle;
    public final Button stopCaptureBtn;
    public final TextView title;
    public final TextView version;
    public final Button viewDatabaseBtn;

    private IbScanLandBinding(LinearLayout rootView, TextView actionState, Spinner actionType, Spinner captureType, Button closeScannerBtn, TextView description, TextView deviceCount, TextView deviceCountTitle, TextView frameTime, TextView frameTimeTitle, LinearLayout infoLayout, ImageView logo, Button openScannerBtn, ImageView previewImage, Button refreshBtn, TextView scanStatesColor1, TextView scanStatesColor2, TextView scanStatesColor3, TextView scanStatesColor4, Button startCaptureBtn, TextView status, TextView statusTitle, Button stopCaptureBtn, TextView title, TextView version, Button viewDatabaseBtn) {
        this.rootView = rootView;
        this.actionState = actionState;
        this.actionType = actionType;
        this.captureType = captureType;
        this.closeScannerBtn = closeScannerBtn;
        this.description = description;
        this.deviceCount = deviceCount;
        this.deviceCountTitle = deviceCountTitle;
        this.frameTime = frameTime;
        this.frameTimeTitle = frameTimeTitle;
        this.infoLayout = infoLayout;
        this.logo = logo;
        this.openScannerBtn = openScannerBtn;
        this.previewImage = previewImage;
        this.refreshBtn = refreshBtn;
        this.scanStatesColor1 = scanStatesColor1;
        this.scanStatesColor2 = scanStatesColor2;
        this.scanStatesColor3 = scanStatesColor3;
        this.scanStatesColor4 = scanStatesColor4;
        this.startCaptureBtn = startCaptureBtn;
        this.status = status;
        this.statusTitle = statusTitle;
        this.stopCaptureBtn = stopCaptureBtn;
        this.title = title;
        this.version = version;
        this.viewDatabaseBtn = viewDatabaseBtn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static IbScanLandBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static IbScanLandBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.ib_scan_land, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static IbScanLandBinding bind(View rootView) {
        int i = R.id.action_state;
        TextView textView = (TextView) rootView.findViewById(R.id.action_state);
        if (textView != null) {
            i = R.id.action_type;
            Spinner spinner = (Spinner) rootView.findViewById(R.id.action_type);
            if (spinner != null) {
                i = R.id.capture_type;
                Spinner spinner2 = (Spinner) rootView.findViewById(R.id.capture_type);
                if (spinner2 != null) {
                    i = R.id.close_scanner_btn;
                    Button button = (Button) rootView.findViewById(R.id.close_scanner_btn);
                    if (button != null) {
                        i = R.id.description;
                        TextView textView2 = (TextView) rootView.findViewById(R.id.description);
                        if (textView2 != null) {
                            i = R.id.device_count;
                            TextView textView3 = (TextView) rootView.findViewById(R.id.device_count);
                            if (textView3 != null) {
                                i = R.id.device_count_title;
                                TextView textView4 = (TextView) rootView.findViewById(R.id.device_count_title);
                                if (textView4 != null) {
                                    i = R.id.frame_time;
                                    TextView textView5 = (TextView) rootView.findViewById(R.id.frame_time);
                                    if (textView5 != null) {
                                        i = R.id.frame_time_title;
                                        TextView textView6 = (TextView) rootView.findViewById(R.id.frame_time_title);
                                        if (textView6 != null) {
                                            LinearLayout linearLayout = (LinearLayout) rootView;
                                            i = R.id.logo;
                                            ImageView imageView = (ImageView) rootView.findViewById(R.id.logo);
                                            if (imageView != null) {
                                                i = R.id.open_scanner_btn;
                                                Button button2 = (Button) rootView.findViewById(R.id.open_scanner_btn);
                                                if (button2 != null) {
                                                    i = R.id.preview_image;
                                                    ImageView imageView2 = (ImageView) rootView.findViewById(R.id.preview_image);
                                                    if (imageView2 != null) {
                                                        i = R.id.refresh_btn;
                                                        Button button3 = (Button) rootView.findViewById(R.id.refresh_btn);
                                                        if (button3 != null) {
                                                            i = R.id.scan_states_color1;
                                                            TextView textView7 = (TextView) rootView.findViewById(R.id.scan_states_color1);
                                                            if (textView7 != null) {
                                                                i = R.id.scan_states_color2;
                                                                TextView textView8 = (TextView) rootView.findViewById(R.id.scan_states_color2);
                                                                if (textView8 != null) {
                                                                    i = R.id.scan_states_color3;
                                                                    TextView textView9 = (TextView) rootView.findViewById(R.id.scan_states_color3);
                                                                    if (textView9 != null) {
                                                                        i = R.id.scan_states_color4;
                                                                        TextView textView10 = (TextView) rootView.findViewById(R.id.scan_states_color4);
                                                                        if (textView10 != null) {
                                                                            i = R.id.start_capture_btn;
                                                                            Button button4 = (Button) rootView.findViewById(R.id.start_capture_btn);
                                                                            if (button4 != null) {
                                                                                i = R.id.status;
                                                                                TextView textView11 = (TextView) rootView.findViewById(R.id.status);
                                                                                if (textView11 != null) {
                                                                                    i = R.id.status_title;
                                                                                    TextView textView12 = (TextView) rootView.findViewById(R.id.status_title);
                                                                                    if (textView12 != null) {
                                                                                        i = R.id.stop_capture_btn;
                                                                                        Button button5 = (Button) rootView.findViewById(R.id.stop_capture_btn);
                                                                                        if (button5 != null) {
                                                                                            i = R.id.title;
                                                                                            TextView textView13 = (TextView) rootView.findViewById(R.id.title);
                                                                                            if (textView13 != null) {
                                                                                                i = R.id.version;
                                                                                                TextView textView14 = (TextView) rootView.findViewById(R.id.version);
                                                                                                if (textView14 != null) {
                                                                                                    i = R.id.view_database_btn;
                                                                                                    Button button6 = (Button) rootView.findViewById(R.id.view_database_btn);
                                                                                                    if (button6 != null) {
                                                                                                        return new IbScanLandBinding(linearLayout, textView, spinner, spinner2, button, textView2, textView3, textView4, textView5, textView6, linearLayout, imageView, button2, imageView2, button3, textView7, textView8, textView9, textView10, button4, textView11, textView12, button5, textView13, textView14, button6);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
