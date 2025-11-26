package com.micromerger.ssms.startmonitoring.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.stats.CodePackage;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.utils.DeviceLocation;
import com.micromerger.ssms.camerax.utils.OnGpsListener;
import com.micromerger.ssms.camerax.utils.PostLocation;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.CommonObjectsUtils;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.Utils;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class SchoolStatus extends BaseFragment implements BaseFragment.CameraActivityCallBackMultiImages {
    List<ReferenceDataResponse.ReferenceData> GENDER_LIST;
    Button btn_next;
    Button btn_start_location;
    CountDownTimer countDownTimer;
    DeviceLocation deviceLocation;
    EditText editText_comments;
    EditText editText_reason;
    LinearLayout elementary_classes_layout;
    Location fetchedLocation;
    ImageView gif_image;
    LinearLayout hi_secondary_classes_layout;
    LinearLayout ll_comments;
    boolean locationCapturing;
    View mView;
    LinearLayout middle_classes_layout;
    ImageView previewImage;
    boolean requestingPermission;
    RadioGroup rg;
    RadioGroup rg_elementary_classes;
    RadioGroup rg_hi_secondary_classes;
    RadioGroup rg_middle_classes;
    RadioGroup rg_school_gender;
    RadioGroup rg_secondary_classes;
    RadioGroup rg_semis_code;
    RelativeLayout rl_spinner;
    FrameLayout save_progress;
    LinearLayout school_closed_duration_layout;
    ImageView school_status_image;
    ImageView school_status_image_upload;
    LinearLayout secondary_classes_layout;
    Spinner sp_closed_time;
    Spinner sp_reason1;
    Spinner sp_school_lvl;
    boolean startLocationClicked;
    TextView tv_accuracy;
    TextView tv_comments;
    TextView tv_count_down_timer;
    TextView tv_latitude;
    TextView tv_location_message;
    TextView tv_longitude;
    ImageView uploadImage;
    List<ReferenceDataResponse.ReferenceData> REASON_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> SCHOOL_LEVEL_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> SCHOOL_CLOSED_DURATION_LIST = new ArrayList();
    int semisCodeId = -1;
    int statusId = -1;
    String imageBaseUrl64 = "";
    String schoolStatusImage = "";
    int reasonId = 0;
    int schoolLvl = 0;
    int closedDurationId = 0;
    int middleClasses = -1;
    int elementaryClasses = -1;
    int secondaryClasses = -1;
    int hiSecondaryClasses = -1;
    String otherReasonComments = "";
    String comments = "";
    boolean isCapturing = false;
    boolean locationCaptured = false;
    String negativeValue = Constant.ECE_Katchi;
    int school_monitoring_image_request_code = 111;
    int school_status_image_request_code = Constant.DATE_TO;
    int pos = -1;
    int genderId = -1;
    int previousSchoolLvl = -1;
    int previousMiddleClasses = -1;
    int previousElementaryClasses = -1;
    int previousSecondaryClasses = -1;
    int previousHiSecondaryClasses = -1;
    final String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.requestGPSPermission = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$rk3pj_TVtR1MxCdcYEnuOq55Iss
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$0$SchoolStatus((ActivityResult) obj);
            }
        });
        MainActivity.intentSender = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$i3oxDnQPoP0FvRs5J1QcN0SXQiM
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$1$SchoolStatus((ActivityResult) obj);
            }
        });
        MainActivity.requestMultiplePermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$TKbbfqVs2-9veWe8iZGKFcRo3bM
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$2$SchoolStatus((Map) obj);
            }
        });
        MainActivity.openSettings = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$Ftc-JsxmRybFQeagsFWK10ugR6U
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.lambda$onCreate$3$SchoolStatus((ActivityResult) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$SchoolStatus(ActivityResult activityResult) {
        this.deviceLocation.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.1
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                SchoolStatus.this.requestLocationPermissions();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                SchoolStatus.this.normalState();
                SchoolStatus.this.deviceLocation.showGPSAlert(SchoolStatus.this.getActivityContext(), MainActivity.requestGPSPermission, SchoolStatus.this.getResources().getString(R.string.gps_required), SchoolStatus.this.getResources().getString(R.string.turn_on_gps_for_camera), SchoolStatus.this.getResources().getString(R.string.ok));
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$1$SchoolStatus(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            requestLocationPermissions();
        } else {
            normalState();
            this.deviceLocation.showGPSAlert(getActivityContext(), MainActivity.requestGPSPermission, getString(R.string.gpssetting), getString(R.string.enablegps), getString(R.string.ok));
        }
    }

    public /* synthetic */ void lambda$onCreate$2$SchoolStatus(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            str.hashCode();
            if (str.equals("android.permission.ACCESS_FINE_LOCATION") || str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                if (((Boolean) entry.getValue()).booleanValue()) {
                    permissionsGranted();
                } else {
                    Toast.makeText(getActivityContext(), "Location Permission denied", 0).show();
                    requestLocationPermissions();
                    this.save_progress.setVisibility(8);
                    this.btn_start_location.setVisibility(0);
                }
            }
        }
    }

    public /* synthetic */ void lambda$onCreate$3$SchoolStatus(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            if (activityResult.getData() != null) {
                requestLocationPermissions();
            } else {
                CommonActions.snackMsgs(this.parentView, "Error, please try again later!");
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_schoolstatus, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        if (CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        Log.d("SchoolID", "onCreateView: " + CommonObjects.school.getSchoolId());
        Log.d("School", "onCreateView: " + CommonObjects.school.toString());
        setCameraActivityCallBackMultiImages(this);
        TextView textView = (TextView) ((Toolbar) getActivity().findViewById(R.id.toolbar)).findViewById(R.id.tv_title);
        textView.setTextSize(2, 16.0f);
        if (CommonObjects.monitoring != null && CommonObjects.monitoring.getSchoolSemisCode() != null) {
            textView.setText(getResources().getString(R.string.monitoring_details) + " (" + CommonObjects.monitoring.getSchoolSemisCode() + ")");
        }
        this.rg_semis_code = (RadioGroup) this.mView.findViewById(R.id.rg_semis_code);
        this.rg = (RadioGroup) this.mView.findViewById(R.id.rg);
        this.rg_middle_classes = (RadioGroup) this.mView.findViewById(R.id.rg_middle_classes);
        this.rg_elementary_classes = (RadioGroup) this.mView.findViewById(R.id.rg_elementary_classes);
        this.rg_secondary_classes = (RadioGroup) this.mView.findViewById(R.id.rg_secondary_classes);
        this.rg_hi_secondary_classes = (RadioGroup) this.mView.findViewById(R.id.rg_hi_secondary_classes);
        this.rg_school_gender = (RadioGroup) this.mView.findViewById(R.id.rg_school_gender);
        Button button = (Button) this.mView.findViewById(R.id.btn_next);
        this.btn_next = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SchoolStatus schoolStatus = SchoolStatus.this;
                schoolStatus.otherReasonComments = schoolStatus.editText_reason.getText().toString();
                SchoolStatus schoolStatus2 = SchoolStatus.this;
                schoolStatus2.comments = schoolStatus2.editText_comments.getText().toString();
                if (SchoolStatus.this.semisCodeId > -1) {
                    if (SchoolStatus.this.schoolLvl == 0) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select School Level");
                        return;
                    }
                    if (SchoolStatus.this.schoolLvl == 4 && SchoolStatus.this.secondaryClasses == -1) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select Secondary School Classes");
                        return;
                    }
                    if (SchoolStatus.this.schoolLvl == 5 && SchoolStatus.this.hiSecondaryClasses == -1) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select Hi.Secondary School Classes");
                        return;
                    }
                    if (SchoolStatus.this.schoolLvl == 2 && SchoolStatus.this.middleClasses == -1) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select Middle School Classes");
                        return;
                    }
                    if (SchoolStatus.this.schoolLvl == 3 && SchoolStatus.this.elementaryClasses == -1) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select Elementary School Classes");
                        return;
                    }
                    if (SchoolStatus.this.genderId == -1 || SchoolStatus.this.genderId == 0) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select School Gender");
                        return;
                    }
                    if (SchoolStatus.this.tv_latitude.getText() == null || SchoolStatus.this.tv_latitude.getText().toString().isEmpty()) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Start Capturing Location");
                        return;
                    }
                    if (SchoolStatus.this.tv_longitude.getText() == null || SchoolStatus.this.tv_longitude.getText().toString().isEmpty()) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Start Capturing Location");
                        return;
                    }
                    if ((SchoolStatus.this.statusId == 1 || SchoolStatus.this.statusId == 2) && SchoolStatus.this.closedDurationId == 0) {
                        DialogCustom.showError(SchoolStatus.this.getContext(), "Please Select School closed duration");
                        return;
                    }
                    int i = SchoolStatus.this.statusId;
                    if (i == 0) {
                        CommonObjects.isSchoolOpen = true;
                        if (!SchoolStatus.this.imageBaseUrl64.equals("")) {
                            if (!SchoolStatus.this.schoolStatusImage.equals("")) {
                                if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                    SchoolStatus schoolStatus3 = SchoolStatus.this;
                                    schoolStatus3.ft = schoolStatus3.fm.beginTransaction();
                                    SchoolStatus.this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                                    SchoolStatus.this.ft.addToBackStack(null);
                                    SchoolStatus.this.ft.commit();
                                    return;
                                }
                                SchoolStatus schoolStatus4 = SchoolStatus.this;
                                schoolStatus4.onRadioButtonClicked(schoolStatus4.rg.getCheckedRadioButtonId());
                                return;
                            }
                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school image");
                            return;
                        }
                        DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school status image");
                        return;
                    }
                    if (i != 1) {
                        if (i == 2) {
                            CommonObjects.isSchoolOpen = false;
                            if (!SchoolStatus.this.REASON_LIST.get(SchoolStatus.this.reasonId).getReferencevalue().equals(Constant.Select)) {
                                if (SchoolStatus.this.REASON_LIST.get(SchoolStatus.this.reasonId).getReferencevalue().equals(Constant.Any_other_specify)) {
                                    if (!SchoolStatus.this.otherReasonComments.equals("")) {
                                        if (!SchoolStatus.this.imageBaseUrl64.equals("")) {
                                            if (!SchoolStatus.this.schoolStatusImage.equals("")) {
                                                if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                                    SchoolStatus schoolStatus5 = SchoolStatus.this;
                                                    schoolStatus5.ft = schoolStatus5.fm.beginTransaction();
                                                    SchoolStatus.this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                                                    SchoolStatus.this.ft.addToBackStack(null);
                                                    SchoolStatus.this.ft.commit();
                                                    return;
                                                }
                                                SchoolStatus schoolStatus6 = SchoolStatus.this;
                                                schoolStatus6.onRadioButtonClicked(schoolStatus6.rg.getCheckedRadioButtonId());
                                                return;
                                            }
                                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school status image");
                                            return;
                                        }
                                        DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload image.");
                                        return;
                                    }
                                    SchoolStatus.this.editText_reason.setError("Please write other reason.");
                                    DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please write other reason.");
                                    return;
                                }
                                if (!SchoolStatus.this.imageBaseUrl64.equals("")) {
                                    if (!SchoolStatus.this.schoolStatusImage.equals("")) {
                                        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                            SchoolStatus schoolStatus7 = SchoolStatus.this;
                                            schoolStatus7.ft = schoolStatus7.fm.beginTransaction();
                                            SchoolStatus.this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                                            SchoolStatus.this.ft.addToBackStack(null);
                                            SchoolStatus.this.ft.commit();
                                            return;
                                        }
                                        SchoolStatus schoolStatus8 = SchoolStatus.this;
                                        schoolStatus8.onRadioButtonClicked(schoolStatus8.rg.getCheckedRadioButtonId());
                                        return;
                                    }
                                    DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school status image");
                                    return;
                                }
                                DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload image.");
                                return;
                            }
                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please select the reason.");
                            return;
                        }
                        if (i != 3) {
                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please select school status.");
                            return;
                        }
                    }
                    CommonObjects.isSchoolOpen = false;
                    if (!SchoolStatus.this.REASON_LIST.get(SchoolStatus.this.reasonId).getReferencevalue().equals(Constant.Select)) {
                        if (SchoolStatus.this.REASON_LIST.get(SchoolStatus.this.reasonId).getReferencevalue().equals(Constant.Any_other_specify)) {
                            if (!SchoolStatus.this.otherReasonComments.equals("")) {
                                if (!SchoolStatus.this.comments.equals("")) {
                                    if (!SchoolStatus.this.imageBaseUrl64.equals("")) {
                                        if (!SchoolStatus.this.schoolStatusImage.equals("")) {
                                            if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                                SchoolStatus schoolStatus9 = SchoolStatus.this;
                                                schoolStatus9.ft = schoolStatus9.fm.beginTransaction();
                                                SchoolStatus.this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                                                SchoolStatus.this.ft.addToBackStack(null);
                                                SchoolStatus.this.ft.commit();
                                                return;
                                            }
                                            SchoolStatus schoolStatus10 = SchoolStatus.this;
                                            schoolStatus10.onRadioButtonClicked(schoolStatus10.rg.getCheckedRadioButtonId());
                                            return;
                                        }
                                        DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school status image");
                                        return;
                                    }
                                    DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload image.");
                                    return;
                                }
                                SchoolStatus.this.editText_comments.setError("Please write reason.");
                                DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please write comments.");
                                return;
                            }
                            SchoolStatus.this.editText_reason.setError("Please write other reason.");
                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please write other reason.");
                            return;
                        }
                        if (!SchoolStatus.this.comments.equals("")) {
                            if (!SchoolStatus.this.imageBaseUrl64.equals("")) {
                                if (!SchoolStatus.this.schoolStatusImage.equals("")) {
                                    if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                        SchoolStatus schoolStatus11 = SchoolStatus.this;
                                        schoolStatus11.ft = schoolStatus11.fm.beginTransaction();
                                        SchoolStatus.this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                                        SchoolStatus.this.ft.addToBackStack(null);
                                        SchoolStatus.this.ft.commit();
                                        return;
                                    }
                                    SchoolStatus schoolStatus12 = SchoolStatus.this;
                                    schoolStatus12.onRadioButtonClicked(schoolStatus12.rg.getCheckedRadioButtonId());
                                    return;
                                }
                                DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload school status image");
                                return;
                            }
                            DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please upload image.");
                            return;
                        }
                        SchoolStatus.this.editText_comments.setError("Please write reason.");
                        DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please write comments.");
                        return;
                    }
                    DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please select the reason.");
                    return;
                }
                DialogCustom.showError(SchoolStatus.this.getActivityContext(), "Please select SEMIS code displayed.");
            }
        });
        this.previewImage = (ImageView) this.mView.findViewById(R.id.previewImage);
        this.uploadImage = (ImageView) this.mView.findViewById(R.id.uploadImage);
        this.school_status_image = (ImageView) this.mView.findViewById(R.id.school_status_image);
        this.school_status_image_upload = (ImageView) this.mView.findViewById(R.id.school_status_image_upload);
        this.ll_comments = (LinearLayout) this.mView.findViewById(R.id.ll_comments);
        this.tv_comments = (TextView) this.mView.findViewById(R.id.tv_comments);
        this.rl_spinner = (RelativeLayout) this.mView.findViewById(R.id.rl_spinner);
        this.gif_image = (ImageView) this.mView.findViewById(R.id.gif_image);
        this.previewImage.setVisibility(8);
        this.uploadImage.setVisibility(0);
        this.school_status_image.setVisibility(8);
        this.school_status_image_upload.setVisibility(0);
        this.ll_comments.setVisibility(8);
        this.tv_comments.setVisibility(8);
        this.rl_spinner.setVisibility(8);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SchoolStatus.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    SchoolStatus schoolStatus = SchoolStatus.this;
                    schoolStatus.getPhotoFromCamera(schoolStatus.school_monitoring_image_request_code, iIntValue + "_6_" + Constant.Monitoring_Image);
                } catch (Exception unused) {
                    SchoolStatus.this.isCapturing = false;
                    Toast.makeText(SchoolStatus.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        this.school_status_image_upload.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$S6ewrNUAONqGDw7l5OtAgMy8pcE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$4$SchoolStatus(view);
            }
        });
        EditText editText = (EditText) this.mView.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editText_comments) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        EditText editText2 = (EditText) this.mView.findViewById(R.id.editText_reason);
        this.editText_reason = editText2;
        editText2.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editText_reason) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        this.editText_reason.setVisibility(8);
        this.rg_semis_code.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.6
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_no) {
                    SchoolStatus.this.semisCodeId = 1;
                } else {
                    if (i != R.id.rb_yes) {
                        return;
                    }
                    SchoolStatus.this.semisCodeId = 0;
                }
            }
        });
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.7
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_close_during_visit /* 2131231504 */:
                        SchoolStatus.this.statusId = 3;
                        SchoolStatus.this.uploadImage.setVisibility(0);
                        SchoolStatus.this.ll_comments.setVisibility(0);
                        SchoolStatus.this.tv_comments.setVisibility(0);
                        SchoolStatus.this.rl_spinner.setVisibility(0);
                        SchoolStatus.this.school_closed_duration_layout.setVisibility(8);
                        SchoolStatus.this.closedDurationId = 0;
                        SchoolStatus.this.sp_closed_time.setSelection(0);
                        break;
                    case R.id.rb_open /* 2131231552 */:
                        SchoolStatus.this.statusId = 0;
                        SchoolStatus.this.uploadImage.setVisibility(0);
                        SchoolStatus.this.ll_comments.setVisibility(0);
                        SchoolStatus.this.tv_comments.setVisibility(0);
                        SchoolStatus.this.rl_spinner.setVisibility(8);
                        SchoolStatus.this.school_closed_duration_layout.setVisibility(8);
                        SchoolStatus.this.closedDurationId = 0;
                        SchoolStatus.this.sp_closed_time.setSelection(0);
                        break;
                    case R.id.rb_pclose /* 2131231553 */:
                        SchoolStatus.this.statusId = 2;
                        SchoolStatus.this.uploadImage.setVisibility(0);
                        SchoolStatus.this.ll_comments.setVisibility(8);
                        SchoolStatus.this.tv_comments.setVisibility(8);
                        SchoolStatus.this.rl_spinner.setVisibility(0);
                        SchoolStatus.this.school_closed_duration_layout.setVisibility(0);
                        break;
                    case R.id.rb_tclose /* 2131231594 */:
                        SchoolStatus.this.statusId = 1;
                        SchoolStatus.this.uploadImage.setVisibility(0);
                        SchoolStatus.this.ll_comments.setVisibility(0);
                        SchoolStatus.this.tv_comments.setVisibility(0);
                        SchoolStatus.this.rl_spinner.setVisibility(0);
                        SchoolStatus.this.school_closed_duration_layout.setVisibility(0);
                        break;
                }
            }
        });
        this.rg_secondary_classes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$Oesavp0IJqnNE83jCiliG7ZmzmA
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onCreateView$5$SchoolStatus(radioGroup, i);
            }
        });
        this.rg_hi_secondary_classes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$ZEyCJlNccMXMeRwGKdTsuPzgij0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onCreateView$6$SchoolStatus(radioGroup, i);
            }
        });
        this.rg_middle_classes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$NGzpSQmvEBh076O27UUDZiK-DAw
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onCreateView$7$SchoolStatus(radioGroup, i);
            }
        });
        this.rg_elementary_classes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$luflAZ9bHryCjdL_a-EOjvCWUGQ
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onCreateView$8$SchoolStatus(radioGroup, i);
            }
        });
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$4AgFQHOAVP6IRiMxnKLZVbSsDZE
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("SCHOOL_GENDER_TYPE");
            }
        });
        if (collectionSelect instanceof List) {
            this.GENDER_LIST = (List) collectionSelect;
        } else {
            this.GENDER_LIST = new ArrayList(collectionSelect);
        }
        if (!this.GENDER_LIST.isEmpty()) {
            this.GENDER_LIST.remove(0);
            this.rg_school_gender.setOrientation(1);
            for (int i = 0; i < this.GENDER_LIST.size(); i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setId(this.GENDER_LIST.get(i).getReferencedataid().intValue());
                radioButton.setText(this.GENDER_LIST.get(i).getReferencevalue());
                radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
                radioButton.setTextSize(16.0f);
                radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$-CO0quuhCKTXujNZ8_EUXz6EHVY
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        this.f$0.lambda$onCreateView$10$SchoolStatus(compoundButton, z);
                    }
                });
                this.rg_school_gender.addView(radioButton);
            }
        }
        Collection collectionSelect2 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.8
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.REASON_TYPE);
            }
        });
        if (collectionSelect2 instanceof List) {
            this.REASON_LIST = (List) collectionSelect2;
        } else {
            this.REASON_LIST = new ArrayList(collectionSelect2);
        }
        Spinner spinner = (Spinner) this.mView.findViewById(R.id.sp_reason);
        this.sp_reason1 = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SchoolStatus.this.reasonId = position;
                SchoolStatus.this.editText_reason.setVisibility(8);
                if (SchoolStatus.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.Any_other_specify)) {
                    SchoolStatus.this.otherReasonComments = "";
                    SchoolStatus.this.editText_reason.setVisibility(0);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(SchoolStatus.this.getActivityContext(), SchoolStatus.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_reason1.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.REASON_LIST));
        this.middle_classes_layout = (LinearLayout) this.mView.findViewById(R.id.middle_classes_layout);
        this.elementary_classes_layout = (LinearLayout) this.mView.findViewById(R.id.elementary_classes_layout);
        this.secondary_classes_layout = (LinearLayout) this.mView.findViewById(R.id.secondary_classes_layout);
        this.hi_secondary_classes_layout = (LinearLayout) this.mView.findViewById(R.id.hi_secondary_classes_layout);
        this.sp_school_lvl = (Spinner) this.mView.findViewById(R.id.sp_school_lvl);
        this.school_closed_duration_layout = (LinearLayout) this.mView.findViewById(R.id.closed_time_layout);
        this.sp_closed_time = (Spinner) this.mView.findViewById(R.id.sp_closed_time);
        Collection collectionSelect3 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$xiXmqfvzKc4qFo30-V1KvoeL3DE
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("SCHOOL_LEVEL");
            }
        });
        if (collectionSelect3 instanceof List) {
            this.SCHOOL_LEVEL_LIST = (List) collectionSelect3;
        } else {
            this.SCHOOL_LEVEL_LIST = new ArrayList(collectionSelect3);
        }
        Iterator<ReferenceDataResponse.ReferenceData> it = this.SCHOOL_LEVEL_LIST.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ReferenceDataResponse.ReferenceData next = it.next();
            if (next.getReferencevalue().equalsIgnoreCase("Office")) {
                this.SCHOOL_LEVEL_LIST.remove(next);
                break;
            }
        }
        this.sp_school_lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.10
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id2) {
                if (position == 4) {
                    SchoolStatus.this.hi_secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.secondary_classes_layout.setVisibility(0);
                    SchoolStatus.this.middle_classes_layout.setVisibility(8);
                    SchoolStatus.this.elementary_classes_layout.setVisibility(8);
                } else if (position == 5) {
                    SchoolStatus.this.secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.hi_secondary_classes_layout.setVisibility(0);
                    SchoolStatus.this.middle_classes_layout.setVisibility(8);
                    SchoolStatus.this.elementary_classes_layout.setVisibility(8);
                } else if (position == 2) {
                    SchoolStatus.this.elementary_classes_layout.setVisibility(8);
                    SchoolStatus.this.secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.hi_secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.middle_classes_layout.setVisibility(0);
                } else if (position == 3) {
                    SchoolStatus.this.middle_classes_layout.setVisibility(8);
                    SchoolStatus.this.secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.hi_secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.elementary_classes_layout.setVisibility(0);
                } else {
                    SchoolStatus.this.secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.hi_secondary_classes_layout.setVisibility(8);
                    SchoolStatus.this.middle_classes_layout.setVisibility(8);
                    SchoolStatus.this.elementary_classes_layout.setVisibility(8);
                }
                SchoolStatus schoolStatus = SchoolStatus.this;
                schoolStatus.schoolLvl = schoolStatus.SCHOOL_LEVEL_LIST.get(position).getReferencekey().intValue();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                CommonActions.hideSoftKeyboard(SchoolStatus.this.getActivityContext(), SchoolStatus.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_school_lvl.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.SCHOOL_LEVEL_LIST));
        renderSchoolClosedDuration();
        return this.mView;
    }

    public /* synthetic */ void lambda$onCreateView$4$SchoolStatus(View view) {
        this.isCapturing = true;
        try {
            int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
            getPhotoFromCamera(this.school_status_image_request_code, iIntValue + "_351_" + Constant.School_Status_Image, true);
        } catch (Exception unused) {
            this.isCapturing = false;
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onCreateView$5$SchoolStatus(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_6_10) {
            this.secondaryClasses = 1;
        } else {
            if (i != R.id.rb_k_10) {
                return;
            }
            this.secondaryClasses = 0;
        }
    }

    public /* synthetic */ void lambda$onCreateView$6$SchoolStatus(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_6_12) {
            this.hiSecondaryClasses = 1;
        } else {
            if (i != R.id.rb_k_12) {
                return;
            }
            this.hiSecondaryClasses = 0;
        }
    }

    public /* synthetic */ void lambda$onCreateView$7$SchoolStatus(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_middle_6_8 /* 2131231545 */:
                this.middleClasses = 1;
                break;
            case R.id.rb_middle_k_8 /* 2131231546 */:
                this.middleClasses = 0;
                break;
        }
    }

    public /* synthetic */ void lambda$onCreateView$8$SchoolStatus(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_elementary_6_8 /* 2131231514 */:
                this.elementaryClasses = 1;
                break;
            case R.id.rb_elementary_k_8 /* 2131231515 */:
                this.elementaryClasses = 0;
                break;
        }
    }

    public /* synthetic */ void lambda$onCreateView$10$SchoolStatus(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.GENDER_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.genderId = referenceData.getReferencekey().intValue();
                }
            }
        }
    }

    private void renderSchoolClosedDuration() {
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$hPmVEsCxrfk6EohXWuL6XMxxCkc
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("SCHOOL_STATUS_CLOSED_DURATION");
            }
        });
        if (collectionSelect instanceof List) {
            this.SCHOOL_CLOSED_DURATION_LIST = (List) collectionSelect;
        } else {
            this.SCHOOL_CLOSED_DURATION_LIST = new ArrayList(collectionSelect);
        }
        this.sp_closed_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.11
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id2) {
                SchoolStatus schoolStatus = SchoolStatus.this;
                schoolStatus.closedDurationId = schoolStatus.SCHOOL_CLOSED_DURATION_LIST.get(position).getReferencekey().intValue();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                CommonActions.hideSoftKeyboard(SchoolStatus.this.getActivityContext(), SchoolStatus.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_closed_time.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.SCHOOL_CLOSED_DURATION_LIST));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_start_location = (Button) view.findViewById(R.id.btn_start_location);
        this.tv_accuracy = (TextView) view.findViewById(R.id.tv_accuracy);
        this.tv_latitude = (TextView) view.findViewById(R.id.tv_latitude);
        this.tv_longitude = (TextView) view.findViewById(R.id.tv_longitude);
        this.tv_location_message = (TextView) view.findViewById(R.id.tv_location_message);
        this.save_progress = (FrameLayout) view.findViewById(R.id.save_progress);
        this.tv_count_down_timer = (TextView) view.findViewById(R.id.tv_count_down_timer);
        this.btn_start_location.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$vp9TzTBeDbGb1LsLoiUc6TgwhxU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$13$SchoolStatus(view2);
            }
        });
        this.deviceLocation = new DeviceLocation(getMainActivity(), getActivityContext(), new AnonymousClass13());
        this.tv_latitude.setBackgroundResource(R.color.light_gray);
        this.tv_longitude.setBackgroundResource(R.color.light_gray);
        Glide.with(view).load(Integer.valueOf(R.drawable.location_gif)).into(this.gif_image);
    }

    public /* synthetic */ void lambda$onViewCreated$13$SchoolStatus(View view) {
        this.startLocationClicked = true;
        loadingState();
        Log.d(CodePackage.LOCATION, "btn_start_location: " + new Date());
        if (this.deviceLocation.isGPSEnabled(getActivityContext())) {
            requestLocationPermissions();
        } else {
            this.deviceLocation.turnOnGPS(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.12
                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void gpsStatus(boolean status) {
                    SchoolStatus.this.requestLocationPermissions();
                }

                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                    MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
                }

                @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
                public void showGPSAlert() {
                    SchoolStatus.this.normalState();
                    SchoolStatus.this.deviceLocation.showGPSAlert(SchoolStatus.this.getActivityContext(), MainActivity.requestGPSPermission, SchoolStatus.this.getResources().getString(R.string.gps_required), SchoolStatus.this.getResources().getString(R.string.turn_on_gps_to_get_location), SchoolStatus.this.getResources().getString(R.string.ok));
                }
            });
        }
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus$13, reason: invalid class name */
    class AnonymousClass13 implements PostLocation {
        AnonymousClass13() {
        }

        @Override // com.micromerger.ssms.camerax.utils.PostLocation
        public void message(String message) {
            if (message != null && !message.isEmpty()) {
                SchoolStatus.this.tv_location_message.setVisibility(0);
                SchoolStatus.this.gif_image.setVisibility(0);
            } else {
                SchoolStatus.this.tv_location_message.setVisibility(8);
                SchoolStatus.this.gif_image.setVisibility(8);
            }
            SchoolStatus.this.tv_location_message.setText(message);
        }

        @Override // com.micromerger.ssms.camerax.utils.PostLocation
        public void locationCallback(final Location location) {
            if (location != null) {
                SchoolStatus.this.fetchedLocation = location;
                SchoolStatus.this.startLocationClicked = false;
                Log.d(CodePackage.LOCATION, "locationCallback: " + location.getLatitude() + ", " + location.getLongitude() + ", " + new Date());
                SchoolStatus.this.getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$13$f0bzEO5gRumsBSaX2jCQnpPWz0c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$locationCallback$0$SchoolStatus$13(location);
                    }
                });
                return;
            }
            if (SchoolStatus.this.fetchedLocation == null) {
                SchoolStatus.this.save_progress.setVisibility(0);
            }
            SchoolStatus.this.locationCapturing = false;
            SchoolStatus.this.startLocationClicked = false;
        }

        public /* synthetic */ void lambda$locationCallback$0$SchoolStatus$13(Location location) {
            if (SchoolStatus.this.countDownTimer != null) {
                SchoolStatus.this.countDownTimer.cancel();
            }
            SchoolStatus.this.tv_count_down_timer.setVisibility(8);
            SchoolStatus.this.save_progress.setVisibility(8);
            SchoolStatus.this.tv_accuracy.setVisibility(0);
            SchoolStatus.this.tv_latitude.setText(String.valueOf(location.getLatitude()));
            SchoolStatus.this.tv_longitude.setText(String.valueOf(location.getLongitude()));
            SchoolStatus.this.tv_accuracy.setText(SchoolStatus.this.getResources().getString(R.string.accuracy, String.valueOf(location.getAccuracy())));
            if (location.getAccuracy() <= 30.0f) {
                SchoolStatus.this.tv_accuracy.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.colorPrimaryGreenDark));
                SchoolStatus.this.tv_location_message.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.dark_grey_color));
                SchoolStatus.this.tv_location_message.setVisibility(8);
                SchoolStatus.this.gif_image.setVisibility(8);
                SchoolStatus.this.deviceLocation.stopLocation(true);
                SchoolStatus.this.locationCaptured = true;
                return;
            }
            if (location.getAccuracy() > 30.0f && location.getAccuracy() <= 50.0f) {
                SchoolStatus.this.tv_accuracy.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.orange));
                SchoolStatus.this.tv_location_message.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.orange));
                SchoolStatus.this.gif_image.setVisibility(0);
            } else {
                SchoolStatus.this.tv_accuracy.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.red));
                SchoolStatus.this.tv_location_message.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.red));
                SchoolStatus.this.gif_image.setVisibility(0);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.micromerger.ssms.startmonitoring.fragments.SchoolStatus$14] */
    private void startTimer() {
        this.tv_accuracy.setVisibility(8);
        this.countDownTimer = new CountDownTimer(15000L, 1000L) { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.14
            @Override // android.os.CountDownTimer
            public void onTick(long millis) {
                SchoolStatus.this.tv_count_down_timer.setText(SchoolStatus.this.getResources().getString(R.string.please_wait_s, String.valueOf(((int) millis) / 1000)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                SchoolStatus.this.tv_count_down_timer.setVisibility(8);
                SchoolStatus.this.tv_accuracy.setVisibility(0);
                SchoolStatus.this.tv_location_message.setVisibility(0);
                SchoolStatus.this.gif_image.setVisibility(0);
                SchoolStatus.this.tv_latitude.setText(SchoolStatus.this.negativeValue);
                SchoolStatus.this.tv_longitude.setText(SchoolStatus.this.negativeValue);
                SchoolStatus.this.tv_accuracy.setText(SchoolStatus.this.getResources().getString(R.string.accuracy, SchoolStatus.this.negativeValue));
                SchoolStatus.this.tv_location_message.setText(SchoolStatus.this.getResources().getString(R.string.location_seems_not_working));
                SchoolStatus.this.tv_location_message.setTextColor(ContextCompat.getColor(SchoolStatus.this.getActivityContext(), R.color.red));
                SchoolStatus.this.countDownTimer.cancel();
                SchoolStatus.this.locationCapturing = false;
                SchoolStatus.this.startLocationClicked = false;
            }
        }.start();
    }

    private void loadingState() {
        this.btn_start_location.setVisibility(8);
        this.save_progress.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void normalState() {
        this.btn_start_location.setVisibility(0);
        this.save_progress.setVisibility(8);
        this.tv_count_down_timer.setVisibility(8);
        this.tv_location_message.setVisibility(8);
        this.gif_image.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void permissionsGranted() {
        if (this.deviceLocation.canStreamLocation()) {
            loadingState();
            this.tv_location_message.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.dark_grey_color));
            this.tv_location_message.setVisibility(8);
            this.gif_image.setVisibility(8);
            startStreamLocation();
            return;
        }
        normalState();
        this.tv_location_message.setVisibility(0);
        this.gif_image.setVisibility(0);
        this.tv_location_message.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.red));
    }

    private void startStreamLocation() {
        this.fetchedLocation = null;
        loadingState();
        if (this.fetchedLocation == null) {
            this.tv_count_down_timer.setVisibility(0);
            startTimer();
        }
        this.locationCapturing = true;
        this.tv_latitude.setText("");
        this.tv_longitude.setText("");
        this.deviceLocation.streamLocation(new OnGpsListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.15
            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void gpsStatus(boolean status) {
                SchoolStatus.this.permissionsGranted();
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void startResolutionForResult(ResolvableApiException resolvableApiException) {
                MainActivity.intentSender.launch(new IntentSenderRequest.Builder(resolvableApiException.getResolution().getIntentSender()).build());
            }

            @Override // com.micromerger.ssms.camerax.utils.OnGpsListener
            public void showGPSAlert() {
                SchoolStatus.this.deviceLocation.showGPSAlert(SchoolStatus.this.getActivityContext(), MainActivity.requestGPSPermission, SchoolStatus.this.getResources().getString(R.string.gps_required), SchoolStatus.this.getResources().getString(R.string.turn_on_gps_for_camera), SchoolStatus.this.getResources().getString(R.string.ok));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLocationPermissions() {
        if (!hasPermissions(getActivityContext()).booleanValue()) {
            MainActivity.requestMultiplePermissions.launch(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
            this.requestingPermission = true;
            if (ActivityCompat.shouldShowRequestPermissionRationale(getMainActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                return;
            }
            this.requestingPermission = false;
            getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$3lErgoHmlf57FP6MqwhEt4mqjQc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestLocationPermissions$15$SchoolStatus();
                }
            });
            return;
        }
        permissionsGranted();
        this.requestingPermission = false;
    }

    public /* synthetic */ void lambda$requestLocationPermissions$15$SchoolStatus() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), 1);
        sweetAlertDialog.setTitleText("Permission Denied").setContentText("In order to perform functionality, SSMS requires Location access. Please enable Location access in Settings").setConfirmText("Setting").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$yrFcXuWZURlfs9bK30HA153inNE
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                this.f$0.lambda$requestLocationPermissions$14$SchoolStatus(sweetAlertDialog2);
            }
        }).setCancelText("Cancel").setCancelClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).setCancelable(false);
        sweetAlertDialog.show();
    }

    public /* synthetic */ void lambda$requestLocationPermissions$14$SchoolStatus(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismiss();
        openSettings();
    }

    private void openSettings() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getMainActivity().getPackageName(), null));
            MainActivity.openSettings.launch(intent);
        } catch (Exception unused) {
            getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolStatus$LnC5AhDi9LcSo4BwdqLdKVeQTUY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$openSettings$16$SchoolStatus();
                }
            });
        }
    }

    public /* synthetic */ void lambda$openSettings$16$SchoolStatus() {
        Toast.makeText(getActivityContext(), "SSMS is unable to open app settings. Open the app settings yourself to allow Permissions", 0).show();
    }

    private Boolean hasPermissions(Context context) {
        for (String str : this.PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        Log.e("onResume", "SUCCESS");
        if (CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_status_fields.entrySet().iterator();
            while (it.hasNext()) {
                KRAData value = it.next().getValue();
                if (value.getKRAName().equalsIgnoreCase(Constant.Latitude) && (value.getDataValue() == null || ((String) value.getDataValue()).isEmpty())) {
                    nullLatLongState();
                }
            }
        } else {
            ((RadioButton) this.rg_school_gender.getChildAt(CommonObjects.monitoring.getSchoolGenderTypeId().intValue() - 1)).setChecked(true);
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_status_fields.entrySet()) {
                entry.getKey();
                KRAData value2 = entry.getValue();
                if (!this.locationCapturing && value2.getKRAName().equalsIgnoreCase(Constant.Latitude) && (value2.getDataValue() == null || ((String) value2.getDataValue()).isEmpty())) {
                    nullLatLongState();
                }
                if (value2.getDataValue() != null && !value2.getDataValue().equals("")) {
                    if (value2.getKRAName().equals(Constant.Comments)) {
                        String str = (String) value2.getDataValue();
                        this.comments = str;
                        this.editText_comments.setText(str);
                    } else if (!value2.getKRAName().equals(Constant.Active_Ind)) {
                        if (value2.getKRAName().equals(Constant.Other_Reason)) {
                            String str2 = (String) value2.getDataValue();
                            this.otherReasonComments = str2;
                            this.editText_reason.setText(str2);
                        } else if (value2.getKRAName().equals(Constant.Reason_id)) {
                            int i = Integer.parseInt((String) value2.getDataValue());
                            this.reasonId = i;
                            if (i < this.REASON_LIST.size()) {
                                this.sp_reason1.setSelection(this.reasonId);
                            }
                            if (this.REASON_LIST.get(this.reasonId).getReferencevalue().equals(Constant.Any_other_specify)) {
                                this.editText_reason.setVisibility(0);
                            }
                        } else if (value2.getKRAName().equals(Constant.Monitoring_Image)) {
                            this.imageBaseUrl64 = (String) value2.getDataValue();
                            Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBaseUrl64));
                            if (bitmapDecodeFile != null) {
                                this.previewImage.setImageBitmap(bitmapDecodeFile);
                                this.previewImage.setVisibility(0);
                            } else {
                                String str3 = this.imageBaseUrl64;
                                if (str3 != null) {
                                    byte[] bArrDecode = Base64.decode(str3, 0);
                                    try {
                                        this.previewImage.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
                                        this.previewImage.setVisibility(0);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else if (value2.getKRAName().equals(Constant.SEMIS_Code_Displayed)) {
                            int i2 = Integer.parseInt((String) value2.getDataValue());
                            this.semisCodeId = i2;
                            ((RadioButton) this.rg_semis_code.getChildAt(i2)).setChecked(true);
                        } else if (value2.getKRAName().equals(Constant.Status_Id)) {
                            int i3 = Integer.parseInt((String) value2.getDataValue());
                            this.statusId = i3;
                            ((RadioButton) this.rg.getChildAt(i3)).setChecked(true);
                            int i4 = this.statusId;
                            if (i4 == 1 || i4 == 2) {
                                this.school_closed_duration_layout.setVisibility(0);
                            } else {
                                this.school_closed_duration_layout.setVisibility(8);
                            }
                        } else if (value2.getKRAName().equals(Constant.Image_Date)) {
                            Log.e(Constant.Image_Date, String.valueOf(value2.getDataValue()));
                        } else if (value2.getKRAName().equals(Constant.Is_Completed)) {
                            Log.e(Constant.Is_Completed, String.valueOf(value2.getDataValue()));
                        } else if (value2.getKRAName().equalsIgnoreCase("Monitoring_School_Level")) {
                            int i5 = Integer.parseInt((String) value2.getDataValue());
                            this.sp_school_lvl.setSelection(i5);
                            this.schoolLvl = i5;
                            this.previousSchoolLvl = i5;
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_10")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.secondaryClasses = 0;
                                this.previousSecondaryClasses = 0;
                                ((RadioButton) this.rg_secondary_classes.getChildAt(0)).setChecked(true);
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_10")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.secondaryClasses = 1;
                                this.previousSecondaryClasses = 1;
                                ((RadioButton) this.rg_secondary_classes.getChildAt(1)).setChecked(true);
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_12")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.hiSecondaryClasses = 0;
                                this.previousHiSecondaryClasses = 0;
                                ((RadioButton) this.rg_hi_secondary_classes.getChildAt(0)).setChecked(true);
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_12")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.hiSecondaryClasses = 1;
                                this.previousHiSecondaryClasses = 1;
                                ((RadioButton) this.rg_hi_secondary_classes.getChildAt(1)).setChecked(true);
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_08")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.middleClasses = 0;
                                this.elementaryClasses = 0;
                                this.previousMiddleClasses = 0;
                                this.previousElementaryClasses = 0;
                                if (this.rg_middle_classes.getVisibility() == 0) {
                                    ((RadioButton) this.rg_middle_classes.getChildAt(this.middleClasses)).setChecked(true);
                                }
                                if (this.rg_elementary_classes.getVisibility() == 0) {
                                    ((RadioButton) this.rg_elementary_classes.getChildAt(this.elementaryClasses)).setChecked(true);
                                }
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_08")) {
                            if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                                this.middleClasses = 1;
                                this.elementaryClasses = 1;
                                this.previousMiddleClasses = 1;
                                this.previousElementaryClasses = 1;
                                if (this.rg_middle_classes.getVisibility() == 0) {
                                    ((RadioButton) this.rg_middle_classes.getChildAt(this.middleClasses)).setChecked(true);
                                }
                                if (this.rg_elementary_classes.getVisibility() == 0) {
                                    ((RadioButton) this.rg_elementary_classes.getChildAt(this.elementaryClasses)).setChecked(true);
                                }
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase(Constant.Latitude)) {
                            if (value2.getDataValue() != null && !((String) value2.getDataValue()).isEmpty()) {
                                this.tv_latitude.setText((String) value2.getDataValue());
                                if (value2.getDataValue().equals(this.negativeValue)) {
                                    this.btn_start_location.setVisibility(0);
                                    this.save_progress.setVisibility(8);
                                } else {
                                    startSaveButtonsGone();
                                }
                            } else {
                                nullLatLongState();
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase(Constant.Longitude)) {
                            if (value2.getDataValue() != null && !((String) value2.getDataValue()).isEmpty()) {
                                this.tv_longitude.setText((String) value2.getDataValue());
                                if (value2.getDataValue().equals(this.negativeValue)) {
                                    this.btn_start_location.setVisibility(0);
                                    this.save_progress.setVisibility(8);
                                } else {
                                    startSaveButtonsGone();
                                }
                            } else {
                                nullLatLongState();
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase(Constant.GPS_Accuracy)) {
                            if (value2.getDataValue() != null && !((String) value2.getDataValue()).isEmpty()) {
                                this.tv_accuracy.setVisibility(0);
                                this.tv_accuracy.setText(getResources().getString(R.string.accuracy, value2.getDataValue()));
                            }
                        } else if (value2.getKRAName().equals(Constant.School_Status_Image)) {
                            this.schoolStatusImage = (String) value2.getDataValue();
                            Bitmap bitmapDecodeFile2 = CommonActions.decodeFile(new File(this.schoolStatusImage));
                            if (bitmapDecodeFile2 != null) {
                                this.school_status_image.setImageBitmap(bitmapDecodeFile2);
                                this.school_status_image.setVisibility(0);
                            } else {
                                String str4 = this.schoolStatusImage;
                                if (str4 != null) {
                                    byte[] bArrDecode2 = Base64.decode(str4, 0);
                                    try {
                                        this.school_status_image.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode2, 0, bArrDecode2.length));
                                        this.school_status_image.setVisibility(0);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        } else if (value2.getKRAName().equalsIgnoreCase("School_Status_Closed_Duration")) {
                            this.closedDurationId = Integer.parseInt((String) value2.getDataValue());
                            List<ReferenceDataResponse.ReferenceData> list = this.SCHOOL_CLOSED_DURATION_LIST;
                            if (list == null || list.isEmpty()) {
                                renderSchoolClosedDuration();
                            }
                            int i6 = 0;
                            while (true) {
                                if (i6 >= this.SCHOOL_CLOSED_DURATION_LIST.size()) {
                                    break;
                                }
                                if (this.closedDurationId == this.SCHOOL_CLOSED_DURATION_LIST.get(i6).getReferencekey().intValue()) {
                                    this.sp_closed_time.setSelection(i6);
                                    break;
                                }
                                i6++;
                            }
                        }
                    }
                }
            }
            if (CommonObjects.monitoring != null && CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                this.rg_semis_code.setEnabled(false);
                this.rg_semis_code.setFocusable(false);
                for (int i7 = 0; i7 < this.rg_semis_code.getChildCount(); i7++) {
                    ((RadioButton) this.rg_semis_code.getChildAt(i7)).setFocusable(false);
                    ((RadioButton) this.rg_semis_code.getChildAt(i7)).setEnabled(false);
                }
                this.rg.setEnabled(false);
                this.rg.setFocusable(false);
                for (int i8 = 0; i8 < this.rg.getChildCount(); i8++) {
                    ((RadioButton) this.rg.getChildAt(i8)).setFocusable(false);
                    ((RadioButton) this.rg.getChildAt(i8)).setEnabled(false);
                }
                this.rg_secondary_classes.setEnabled(false);
                this.rg_secondary_classes.setFocusable(false);
                for (int i9 = 0; i9 < this.rg_secondary_classes.getChildCount(); i9++) {
                    ((RadioButton) this.rg_secondary_classes.getChildAt(i9)).setFocusable(false);
                    ((RadioButton) this.rg_secondary_classes.getChildAt(i9)).setEnabled(false);
                }
                this.rg_hi_secondary_classes.setEnabled(false);
                this.rg_hi_secondary_classes.setFocusable(false);
                for (int i10 = 0; i10 < this.rg_hi_secondary_classes.getChildCount(); i10++) {
                    ((RadioButton) this.rg_hi_secondary_classes.getChildAt(i10)).setFocusable(false);
                    ((RadioButton) this.rg_hi_secondary_classes.getChildAt(i10)).setEnabled(false);
                }
                this.rg_middle_classes.setEnabled(false);
                this.rg_middle_classes.setFocusable(false);
                for (int i11 = 0; i11 < this.rg_middle_classes.getChildCount(); i11++) {
                    ((RadioButton) this.rg_middle_classes.getChildAt(i11)).setFocusable(false);
                    ((RadioButton) this.rg_middle_classes.getChildAt(i11)).setEnabled(false);
                }
                this.rg_elementary_classes.setEnabled(false);
                this.rg_elementary_classes.setFocusable(false);
                for (int i12 = 0; i12 < this.rg_elementary_classes.getChildCount(); i12++) {
                    ((RadioButton) this.rg_elementary_classes.getChildAt(i12)).setFocusable(false);
                    ((RadioButton) this.rg_elementary_classes.getChildAt(i12)).setEnabled(false);
                }
                this.rg_school_gender.setEnabled(false);
                this.rg_school_gender.setFocusable(false);
                for (int i13 = 0; i13 < this.rg_school_gender.getChildCount(); i13++) {
                    ((RadioButton) this.rg_school_gender.getChildAt(i13)).setFocusable(false);
                    ((RadioButton) this.rg_school_gender.getChildAt(i13)).setEnabled(false);
                }
                this.uploadImage.setEnabled(false);
                this.uploadImage.setFocusable(false);
                this.school_status_image_upload.setEnabled(false);
                this.school_status_image_upload.setFocusable(false);
                this.sp_reason1.setEnabled(false);
                this.sp_reason1.setFocusable(false);
                this.sp_school_lvl.setEnabled(false);
                this.sp_school_lvl.setFocusable(false);
                this.editText_reason.setEnabled(false);
                this.editText_reason.setFocusable(false);
                this.editText_comments.setEnabled(false);
                this.editText_comments.setFocusable(false);
            }
        }
        if (this.locationCapturing) {
            this.locationCapturing = false;
            this.startLocationClicked = false;
        }
        if (this.requestingPermission) {
            requestLocationPermissions();
            this.requestingPermission = false;
        }
        if (this.locationCaptured) {
            this.btn_start_location.setVisibility(8);
        }
    }

    private void nullLatLongState() {
        this.tv_count_down_timer.setVisibility(8);
        this.tv_location_message.setVisibility(8);
        this.gif_image.setVisibility(8);
        this.save_progress.setVisibility(8);
        this.btn_start_location.setVisibility(0);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startSaveButtonsGone() {
        this.btn_start_location.setVisibility(8);
        this.save_progress.setVisibility(8);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        boolean z = this.startLocationClicked;
        if (z && this.locationCapturing) {
            this.startLocationClicked = false;
            this.locationCapturing = false;
            DeviceLocation deviceLocation = this.deviceLocation;
            if (deviceLocation != null) {
                deviceLocation.stopLocation(false);
            }
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            normalState();
        } else if (!z && !this.locationCapturing) {
            SSMS.setCurrentFragment(null);
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        DeviceLocation deviceLocation = this.deviceLocation;
        if (deviceLocation != null) {
            deviceLocation.stopLocation(false);
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        DeviceLocation deviceLocation = this.deviceLocation;
        if (deviceLocation != null) {
            deviceLocation.stopLocation(false);
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }

    public void onRadioButtonClicked(int id2) {
        switch (id2) {
            case R.id.rb_close_during_visit /* 2131231504 */:
            case R.id.rb_pclose /* 2131231553 */:
            case R.id.rb_tclose /* 2131231594 */:
                updateSchoolStatus();
                this.ft = this.fm.beginTransaction();
                this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                this.ft.addToBackStack(null);
                this.ft.commit();
                break;
            case R.id.rb_open /* 2131231552 */:
                updateSchoolStatus();
                this.ft = this.fm.beginTransaction();
                this.ft.replace(R.id.container, StartMonitoringDetailsFragment.newInstance());
                this.ft.addToBackStack(null);
                this.ft.commit();
                break;
        }
    }

    private void updateSchoolStatus() {
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_status_fields.entrySet()) {
            Integer key = entry.getKey();
            KRAData value = entry.getValue();
            if (value.getKRAName().equals(Constant.Comments)) {
                value.setDataValue(this.comments);
            } else if (!value.getKRAName().equals(Constant.Active_Ind)) {
                if (value.getKRAName().equals(Constant.Other_Reason)) {
                    if (this.statusId == 0) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(this.otherReasonComments);
                    }
                } else if (value.getKRAName().equals(Constant.Reason_id)) {
                    if (this.statusId == 0) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(String.valueOf(this.reasonId));
                    }
                } else if (value.getKRAName().equals(Constant.Monitoring_Image)) {
                    value.setDataValue(this.imageBaseUrl64);
                } else if (value.getKRAName().equals(Constant.School_Status_Image)) {
                    value.setDataValue(this.schoolStatusImage);
                } else if (value.getKRAName().equals(Constant.SEMIS_Code_Displayed)) {
                    value.setDataValue(String.valueOf(this.semisCodeId));
                } else if (value.getKRAName().equals(Constant.Status_Id)) {
                    value.setDataValue(String.valueOf(this.statusId));
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    value.setDataValue(true);
                } else if (value.getKRAName().equals(Constant.Latitude)) {
                    value.setDataValue(this.tv_latitude.getText());
                } else if (value.getKRAName().equals(Constant.Longitude)) {
                    value.setDataValue(this.tv_longitude.getText());
                } else if (value.getKRAName().equals(Constant.GPS_Accuracy)) {
                    String string = this.tv_accuracy.getText().toString();
                    if (string.contains("Accuracy: ")) {
                        string = string.replace("Accuracy: ", "");
                    }
                    value.setDataValue(string);
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    if (!this.imageBaseUrl64.equals("")) {
                        value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Monitoring_School_Level")) {
                    int i = this.schoolLvl;
                    if (i != 0) {
                        value.setDataValue(String.valueOf(i));
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_Katchi_to_10")) {
                    if (this.schoolLvl == 4) {
                        if (this.secondaryClasses == 0) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_06_to_10")) {
                    if (this.schoolLvl == 4) {
                        if (this.secondaryClasses == 1) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_Katchi_to_12")) {
                    if (this.schoolLvl == 5) {
                        if (this.hiSecondaryClasses == 0) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_06_to_12")) {
                    if (this.schoolLvl == 5) {
                        if (this.hiSecondaryClasses == 1) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_Katchi_to_08")) {
                    int i2 = this.schoolLvl;
                    if (i2 == 2) {
                        if (this.middleClasses == 0) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else if (i2 == 3) {
                        if (this.elementaryClasses == 0) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Class_06_to_08")) {
                    int i3 = this.schoolLvl;
                    if (i3 == 2) {
                        if (this.middleClasses == 1) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else if (i3 == 3) {
                        if (this.elementaryClasses == 1) {
                            value.setDataValue(Constant.ECE_Katchi);
                        } else {
                            value.setDataValue("1");
                        }
                    } else {
                        value.setDataValue("1");
                    }
                } else if (value.getKRAName().equalsIgnoreCase("Monitoring_School_Gender_Type")) {
                    value.setDataValue(String.valueOf(this.genderId));
                } else if (value.getKRAName().equalsIgnoreCase("School_Status_Closed_Duration")) {
                    value.setDataValue(String.valueOf(this.closedDurationId));
                }
            }
            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
        }
        saveData();
    }

    private void saveData() {
        int i;
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                this.pos = i2;
                if (this.genderId == 1) {
                    Utils.clearGirlsStipendData(getActivityContext());
                }
                try {
                    if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() != this.genderId || (i = this.schoolLvl) != this.previousSchoolLvl) {
                        Utils.clearEnrollments(getActivityContext());
                        Utils.patchPreviousMonitoringData(getActivityContext(), true);
                    } else if (i == 2 && this.middleClasses != this.previousMiddleClasses) {
                        Utils.clearEnrollments(getActivityContext());
                        Utils.patchPreviousMonitoringData(getActivityContext(), true);
                    } else if (i == 3 && this.elementaryClasses != this.previousElementaryClasses) {
                        Utils.clearEnrollments(getActivityContext());
                        Utils.patchPreviousMonitoringData(getActivityContext(), true);
                    } else if (i == 4 && this.secondaryClasses != this.previousSecondaryClasses) {
                        Utils.clearEnrollments(getActivityContext());
                        Utils.patchPreviousMonitoringData(getActivityContext(), true);
                    } else if (i == 5 && this.hiSecondaryClasses != this.previousHiSecondaryClasses) {
                        Utils.clearEnrollments(getActivityContext());
                        Utils.patchPreviousMonitoringData(getActivityContext(), true);
                    }
                } catch (Exception e) {
                    util.logException(new Exception("Clearing and Patching Enrollment Exception when the school level changed: " + e));
                }
                CommonObjects.monitoring.setSchoolGenderTypeId(Integer.valueOf(this.genderId));
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBackMultiImages
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath, int requestCode) {
        Log.e("onCameraActivityResult", "SUCCESS");
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock || bitmap == null || bitmapPath == null) {
            return;
        }
        if (requestCode == this.school_monitoring_image_request_code) {
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
            this.imageBaseUrl64 = bitmapPath;
        }
        if (requestCode == this.school_status_image_request_code) {
            this.school_status_image.setVisibility(0);
            this.school_status_image.setImageBitmap(bitmap);
            this.schoolStatusImage = bitmapPath;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        new SweetAlertDialog(getActivity(), 3).setTitleText("Are you sure?").setContentText("Do you want to exit from monitoring?").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.17
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismiss();
                SchoolStatus.this.fm.popBackStack();
            }
        }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolStatus.16
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.cancel();
            }
        }).show();
        return true;
    }
}
