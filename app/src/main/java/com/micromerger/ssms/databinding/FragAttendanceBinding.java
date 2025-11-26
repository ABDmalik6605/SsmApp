package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.widgets.ClearableEditText;

/* loaded from: classes2.dex */
public final class FragAttendanceBinding implements ViewBinding {
    public final TextView actionState;
    public final Spinner actionType;
    public final Button cancelButton;
    public final Spinner captureType;
    public final Button closeScannerBtn;
    public final CardView cvNoDataRL;
    public final TextView description;
    public final TextView deviceCount;
    public final TextView deviceCountTitle;
    public final ClearableEditText etAttendenceSearch;
    public final LinearLayout footer;
    public final TextView frameTime;
    public final TextView frameTimeTitle;
    public final LinearLayout infoLayout;
    public final LinearLayout llAttendenceSearch;
    public final LinearLayout llMusterRoll;
    public final Button openScannerBtn;
    public final ImageView previewImage;
    public final ImageView previewImage1;
    public final RecyclerView recyclerview;
    public final Button refreshBtn;
    private final NestedScrollView rootView;
    public final Button saveButton;
    public final TextView scanStatesColor1;
    public final TextView scanStatesColor2;
    public final TextView scanStatesColor3;
    public final TextView scanStatesColor4;
    public final Button startCaptureBtn;
    public final TextView status;
    public final TextView statusTitle;
    public final Button stopCaptureBtn;
    public final TextView tvAttendance;
    public final TextView tvBiometric;
    public final TextView tvNonTeachingStaffCountId;
    public final TextView tvTeachingStaffCountId;
    public final ImageView uploadImage;
    public final Button viewDatabaseBtn;

    private FragAttendanceBinding(NestedScrollView rootView, TextView actionState, Spinner actionType, Button cancelButton, Spinner captureType, Button closeScannerBtn, CardView cvNoDataRL, TextView description, TextView deviceCount, TextView deviceCountTitle, ClearableEditText etAttendenceSearch, LinearLayout footer, TextView frameTime, TextView frameTimeTitle, LinearLayout infoLayout, LinearLayout llAttendenceSearch, LinearLayout llMusterRoll, Button openScannerBtn, ImageView previewImage, ImageView previewImage1, RecyclerView recyclerview, Button refreshBtn, Button saveButton, TextView scanStatesColor1, TextView scanStatesColor2, TextView scanStatesColor3, TextView scanStatesColor4, Button startCaptureBtn, TextView status, TextView statusTitle, Button stopCaptureBtn, TextView tvAttendance, TextView tvBiometric, TextView tvNonTeachingStaffCountId, TextView tvTeachingStaffCountId, ImageView uploadImage, Button viewDatabaseBtn) {
        this.rootView = rootView;
        this.actionState = actionState;
        this.actionType = actionType;
        this.cancelButton = cancelButton;
        this.captureType = captureType;
        this.closeScannerBtn = closeScannerBtn;
        this.cvNoDataRL = cvNoDataRL;
        this.description = description;
        this.deviceCount = deviceCount;
        this.deviceCountTitle = deviceCountTitle;
        this.etAttendenceSearch = etAttendenceSearch;
        this.footer = footer;
        this.frameTime = frameTime;
        this.frameTimeTitle = frameTimeTitle;
        this.infoLayout = infoLayout;
        this.llAttendenceSearch = llAttendenceSearch;
        this.llMusterRoll = llMusterRoll;
        this.openScannerBtn = openScannerBtn;
        this.previewImage = previewImage;
        this.previewImage1 = previewImage1;
        this.recyclerview = recyclerview;
        this.refreshBtn = refreshBtn;
        this.saveButton = saveButton;
        this.scanStatesColor1 = scanStatesColor1;
        this.scanStatesColor2 = scanStatesColor2;
        this.scanStatesColor3 = scanStatesColor3;
        this.scanStatesColor4 = scanStatesColor4;
        this.startCaptureBtn = startCaptureBtn;
        this.status = status;
        this.statusTitle = statusTitle;
        this.stopCaptureBtn = stopCaptureBtn;
        this.tvAttendance = tvAttendance;
        this.tvBiometric = tvBiometric;
        this.tvNonTeachingStaffCountId = tvNonTeachingStaffCountId;
        this.tvTeachingStaffCountId = tvTeachingStaffCountId;
        this.uploadImage = uploadImage;
        this.viewDatabaseBtn = viewDatabaseBtn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragAttendanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragAttendanceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_attendance, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragAttendanceBinding bind(View rootView) {
        int i = R.id.action_state;
        TextView textView = (TextView) rootView.findViewById(R.id.action_state);
        if (textView != null) {
            i = R.id.action_type;
            Spinner spinner = (Spinner) rootView.findViewById(R.id.action_type);
            if (spinner != null) {
                i = R.id.cancel_button;
                Button button = (Button) rootView.findViewById(R.id.cancel_button);
                if (button != null) {
                    i = R.id.capture_type;
                    Spinner spinner2 = (Spinner) rootView.findViewById(R.id.capture_type);
                    if (spinner2 != null) {
                        i = R.id.close_scanner_btn;
                        Button button2 = (Button) rootView.findViewById(R.id.close_scanner_btn);
                        if (button2 != null) {
                            i = R.id.cv_noData_RL;
                            CardView cardView = (CardView) rootView.findViewById(R.id.cv_noData_RL);
                            if (cardView != null) {
                                i = R.id.description;
                                TextView textView2 = (TextView) rootView.findViewById(R.id.description);
                                if (textView2 != null) {
                                    i = R.id.device_count;
                                    TextView textView3 = (TextView) rootView.findViewById(R.id.device_count);
                                    if (textView3 != null) {
                                        i = R.id.device_count_title;
                                        TextView textView4 = (TextView) rootView.findViewById(R.id.device_count_title);
                                        if (textView4 != null) {
                                            i = R.id.et_attendenceSearch;
                                            ClearableEditText clearableEditText = (ClearableEditText) rootView.findViewById(R.id.et_attendenceSearch);
                                            if (clearableEditText != null) {
                                                i = R.id.footer;
                                                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.footer);
                                                if (linearLayout != null) {
                                                    i = R.id.frame_time;
                                                    TextView textView5 = (TextView) rootView.findViewById(R.id.frame_time);
                                                    if (textView5 != null) {
                                                        i = R.id.frame_time_title;
                                                        TextView textView6 = (TextView) rootView.findViewById(R.id.frame_time_title);
                                                        if (textView6 != null) {
                                                            i = R.id.infoLayout;
                                                            LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.infoLayout);
                                                            if (linearLayout2 != null) {
                                                                i = R.id.ll_attendenceSearch;
                                                                LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.ll_attendenceSearch);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.ll_muster_roll;
                                                                    LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.ll_muster_roll);
                                                                    if (linearLayout4 != null) {
                                                                        i = R.id.open_scanner_btn;
                                                                        Button button3 = (Button) rootView.findViewById(R.id.open_scanner_btn);
                                                                        if (button3 != null) {
                                                                            i = R.id.preview_image;
                                                                            ImageView imageView = (ImageView) rootView.findViewById(R.id.preview_image);
                                                                            if (imageView != null) {
                                                                                i = R.id.previewImage;
                                                                                ImageView imageView2 = (ImageView) rootView.findViewById(R.id.previewImage);
                                                                                if (imageView2 != null) {
                                                                                    i = R.id.recyclerview;
                                                                                    RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                                                                                    if (recyclerView != null) {
                                                                                        i = R.id.refresh_btn;
                                                                                        Button button4 = (Button) rootView.findViewById(R.id.refresh_btn);
                                                                                        if (button4 != null) {
                                                                                            i = R.id.save_button;
                                                                                            Button button5 = (Button) rootView.findViewById(R.id.save_button);
                                                                                            if (button5 != null) {
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
                                                                                                                Button button6 = (Button) rootView.findViewById(R.id.start_capture_btn);
                                                                                                                if (button6 != null) {
                                                                                                                    i = R.id.status;
                                                                                                                    TextView textView11 = (TextView) rootView.findViewById(R.id.status);
                                                                                                                    if (textView11 != null) {
                                                                                                                        i = R.id.status_title;
                                                                                                                        TextView textView12 = (TextView) rootView.findViewById(R.id.status_title);
                                                                                                                        if (textView12 != null) {
                                                                                                                            i = R.id.stop_capture_btn;
                                                                                                                            Button button7 = (Button) rootView.findViewById(R.id.stop_capture_btn);
                                                                                                                            if (button7 != null) {
                                                                                                                                i = R.id.tv_attendance;
                                                                                                                                TextView textView13 = (TextView) rootView.findViewById(R.id.tv_attendance);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.tv_biometric;
                                                                                                                                    TextView textView14 = (TextView) rootView.findViewById(R.id.tv_biometric);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.tv_non_teaching_staff_count_id;
                                                                                                                                        TextView textView15 = (TextView) rootView.findViewById(R.id.tv_non_teaching_staff_count_id);
                                                                                                                                        if (textView15 != null) {
                                                                                                                                            i = R.id.tv_teaching_staff_count_id;
                                                                                                                                            TextView textView16 = (TextView) rootView.findViewById(R.id.tv_teaching_staff_count_id);
                                                                                                                                            if (textView16 != null) {
                                                                                                                                                i = R.id.uploadImage;
                                                                                                                                                ImageView imageView3 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                                                                if (imageView3 != null) {
                                                                                                                                                    i = R.id.view_database_btn;
                                                                                                                                                    Button button8 = (Button) rootView.findViewById(R.id.view_database_btn);
                                                                                                                                                    if (button8 != null) {
                                                                                                                                                        return new FragAttendanceBinding((NestedScrollView) rootView, textView, spinner, button, spinner2, button2, cardView, textView2, textView3, textView4, clearableEditText, linearLayout, textView5, textView6, linearLayout2, linearLayout3, linearLayout4, button3, imageView, imageView2, recyclerView, button4, button5, textView7, textView8, textView9, textView10, button6, textView11, textView12, button7, textView13, textView14, textView15, textView16, imageView3, button8);
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
