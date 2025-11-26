package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragBiometricRegistrationBinding implements ViewBinding {
    public final TextView actionState;
    public final Spinner actionType;
    public final Button btnSearch;
    public final Spinner captureType;
    public final Button closeScannerBtn;
    public final CardView cvMaProfile;
    public final CardView cvNoDataRL;
    public final TextView description;
    public final TextView deviceCount;
    public final TextView deviceCountTitle;
    public final Button emptyBtn;
    public final EditText etCnic;
    public final LinearLayout footer;
    public final TextView frameTime;
    public final TextView frameTimeTitle;
    public final LinearLayout llCnicSearch;
    public final LinearLayout llMaProfile;
    public final LinearLayout llThumbRegister;
    public final ImageView maProfileImage;
    public final Button openScannerBtn;
    public final ImageView previewImage;
    public final Button refreshBtn;
    public final Button resetButton;
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
    public final TextView tvMaId;
    public final TextView tvMaName;
    public final TextView tvNic;
    public final Button viewDatabaseBtn;

    private FragBiometricRegistrationBinding(NestedScrollView rootView, TextView actionState, Spinner actionType, Button btnSearch, Spinner captureType, Button closeScannerBtn, CardView cvMaProfile, CardView cvNoDataRL, TextView description, TextView deviceCount, TextView deviceCountTitle, Button emptyBtn, EditText etCnic, LinearLayout footer, TextView frameTime, TextView frameTimeTitle, LinearLayout llCnicSearch, LinearLayout llMaProfile, LinearLayout llThumbRegister, ImageView maProfileImage, Button openScannerBtn, ImageView previewImage, Button refreshBtn, Button resetButton, Button saveButton, TextView scanStatesColor1, TextView scanStatesColor2, TextView scanStatesColor3, TextView scanStatesColor4, Button startCaptureBtn, TextView status, TextView statusTitle, Button stopCaptureBtn, TextView tvAttendance, TextView tvBiometric, TextView tvMaId, TextView tvMaName, TextView tvNic, Button viewDatabaseBtn) {
        this.rootView = rootView;
        this.actionState = actionState;
        this.actionType = actionType;
        this.btnSearch = btnSearch;
        this.captureType = captureType;
        this.closeScannerBtn = closeScannerBtn;
        this.cvMaProfile = cvMaProfile;
        this.cvNoDataRL = cvNoDataRL;
        this.description = description;
        this.deviceCount = deviceCount;
        this.deviceCountTitle = deviceCountTitle;
        this.emptyBtn = emptyBtn;
        this.etCnic = etCnic;
        this.footer = footer;
        this.frameTime = frameTime;
        this.frameTimeTitle = frameTimeTitle;
        this.llCnicSearch = llCnicSearch;
        this.llMaProfile = llMaProfile;
        this.llThumbRegister = llThumbRegister;
        this.maProfileImage = maProfileImage;
        this.openScannerBtn = openScannerBtn;
        this.previewImage = previewImage;
        this.refreshBtn = refreshBtn;
        this.resetButton = resetButton;
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
        this.tvMaId = tvMaId;
        this.tvMaName = tvMaName;
        this.tvNic = tvNic;
        this.viewDatabaseBtn = viewDatabaseBtn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragBiometricRegistrationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragBiometricRegistrationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_biometric_registration, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragBiometricRegistrationBinding bind(View rootView) {
        int i = R.id.action_state;
        TextView textView = (TextView) rootView.findViewById(R.id.action_state);
        if (textView != null) {
            i = R.id.action_type;
            Spinner spinner = (Spinner) rootView.findViewById(R.id.action_type);
            if (spinner != null) {
                i = R.id.btn_search;
                Button button = (Button) rootView.findViewById(R.id.btn_search);
                if (button != null) {
                    i = R.id.capture_type;
                    Spinner spinner2 = (Spinner) rootView.findViewById(R.id.capture_type);
                    if (spinner2 != null) {
                        i = R.id.close_scanner_btn;
                        Button button2 = (Button) rootView.findViewById(R.id.close_scanner_btn);
                        if (button2 != null) {
                            i = R.id.cv_ma_profile;
                            CardView cardView = (CardView) rootView.findViewById(R.id.cv_ma_profile);
                            if (cardView != null) {
                                i = R.id.cv_noData_RL;
                                CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_noData_RL);
                                if (cardView2 != null) {
                                    i = R.id.description;
                                    TextView textView2 = (TextView) rootView.findViewById(R.id.description);
                                    if (textView2 != null) {
                                        i = R.id.device_count;
                                        TextView textView3 = (TextView) rootView.findViewById(R.id.device_count);
                                        if (textView3 != null) {
                                            i = R.id.device_count_title;
                                            TextView textView4 = (TextView) rootView.findViewById(R.id.device_count_title);
                                            if (textView4 != null) {
                                                i = R.id.empty_btn;
                                                Button button3 = (Button) rootView.findViewById(R.id.empty_btn);
                                                if (button3 != null) {
                                                    i = R.id.et_cnic;
                                                    EditText editText = (EditText) rootView.findViewById(R.id.et_cnic);
                                                    if (editText != null) {
                                                        i = R.id.footer;
                                                        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.footer);
                                                        if (linearLayout != null) {
                                                            i = R.id.frame_time;
                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.frame_time);
                                                            if (textView5 != null) {
                                                                i = R.id.frame_time_title;
                                                                TextView textView6 = (TextView) rootView.findViewById(R.id.frame_time_title);
                                                                if (textView6 != null) {
                                                                    i = R.id.ll_cnicSearch;
                                                                    LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.ll_cnicSearch);
                                                                    if (linearLayout2 != null) {
                                                                        i = R.id.ll_ma_profile;
                                                                        LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.ll_ma_profile);
                                                                        if (linearLayout3 != null) {
                                                                            i = R.id.ll_thumb_register;
                                                                            LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.ll_thumb_register);
                                                                            if (linearLayout4 != null) {
                                                                                i = R.id.ma_profile_image;
                                                                                ImageView imageView = (ImageView) rootView.findViewById(R.id.ma_profile_image);
                                                                                if (imageView != null) {
                                                                                    i = R.id.open_scanner_btn;
                                                                                    Button button4 = (Button) rootView.findViewById(R.id.open_scanner_btn);
                                                                                    if (button4 != null) {
                                                                                        i = R.id.preview_image;
                                                                                        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.preview_image);
                                                                                        if (imageView2 != null) {
                                                                                            i = R.id.refresh_btn;
                                                                                            Button button5 = (Button) rootView.findViewById(R.id.refresh_btn);
                                                                                            if (button5 != null) {
                                                                                                i = R.id.reset_button;
                                                                                                Button button6 = (Button) rootView.findViewById(R.id.reset_button);
                                                                                                if (button6 != null) {
                                                                                                    i = R.id.save_button;
                                                                                                    Button button7 = (Button) rootView.findViewById(R.id.save_button);
                                                                                                    if (button7 != null) {
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
                                                                                                                        Button button8 = (Button) rootView.findViewById(R.id.start_capture_btn);
                                                                                                                        if (button8 != null) {
                                                                                                                            i = R.id.status;
                                                                                                                            TextView textView11 = (TextView) rootView.findViewById(R.id.status);
                                                                                                                            if (textView11 != null) {
                                                                                                                                i = R.id.status_title;
                                                                                                                                TextView textView12 = (TextView) rootView.findViewById(R.id.status_title);
                                                                                                                                if (textView12 != null) {
                                                                                                                                    i = R.id.stop_capture_btn;
                                                                                                                                    Button button9 = (Button) rootView.findViewById(R.id.stop_capture_btn);
                                                                                                                                    if (button9 != null) {
                                                                                                                                        i = R.id.tv_attendance;
                                                                                                                                        TextView textView13 = (TextView) rootView.findViewById(R.id.tv_attendance);
                                                                                                                                        if (textView13 != null) {
                                                                                                                                            i = R.id.tv_biometric;
                                                                                                                                            TextView textView14 = (TextView) rootView.findViewById(R.id.tv_biometric);
                                                                                                                                            if (textView14 != null) {
                                                                                                                                                i = R.id.tv_ma_id;
                                                                                                                                                TextView textView15 = (TextView) rootView.findViewById(R.id.tv_ma_id);
                                                                                                                                                if (textView15 != null) {
                                                                                                                                                    i = R.id.tv_ma_name;
                                                                                                                                                    TextView textView16 = (TextView) rootView.findViewById(R.id.tv_ma_name);
                                                                                                                                                    if (textView16 != null) {
                                                                                                                                                        i = R.id.tv_nic;
                                                                                                                                                        TextView textView17 = (TextView) rootView.findViewById(R.id.tv_nic);
                                                                                                                                                        if (textView17 != null) {
                                                                                                                                                            i = R.id.view_database_btn;
                                                                                                                                                            Button button10 = (Button) rootView.findViewById(R.id.view_database_btn);
                                                                                                                                                            if (button10 != null) {
                                                                                                                                                                return new FragBiometricRegistrationBinding((NestedScrollView) rootView, textView, spinner, button, spinner2, button2, cardView, cardView2, textView2, textView3, textView4, button3, editText, linearLayout, textView5, textView6, linearLayout2, linearLayout3, linearLayout4, imageView, button4, imageView2, button5, button6, button7, textView7, textView8, textView9, textView10, button8, textView11, textView12, button9, textView13, textView14, textView15, textView16, textView17, button10);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
