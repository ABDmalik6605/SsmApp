package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.util.Map;

/* loaded from: classes2.dex */
public class BuildingUnderIllegalOccupation extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final String[] REMARKS_LIST = {"Reason 1", "Reason 2", "Reason 3", "other"};
    Button cancel_button;
    EditText editText_comments;
    boolean isOtherRemarks;
    boolean isPreviewVisible;
    View mView;
    ImageView previewImage;
    RadioGroup rg_builiding_illegal;
    RadioGroup rg_school_building;
    RelativeLayout rl_comments;
    LinearLayout rl_illegal_building;
    RelativeLayout rl_ramarks;
    Button save_button;
    MaterialSpinner sp_remarks;
    ImageView uploadImage;
    int buildingId = 0;
    int buildingillegalOcp = 1;
    int remarksId = 0;
    String comments = "";
    String imageBaseUrl64 = "";
    boolean isCapturing = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_building_under_illegal_occupation, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        setCameraActivityCallBack(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.rl_illegal_building);
        this.rl_illegal_building = linearLayout;
        linearLayout.setVisibility(0);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_ramarks);
        this.rl_ramarks = relativeLayout;
        relativeLayout.setVisibility(8);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.rl_comments);
        this.rl_comments = relativeLayout2;
        relativeLayout2.setVisibility(8);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_school_building);
        this.rg_school_building = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_builiding_illegal);
        this.rg_builiding_illegal = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(8);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BuildingUnderIllegalOccupation.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BuildingUnderIllegalOccupation.this.isCapturing = true;
                try {
                    BuildingUnderIllegalOccupation.this.getPhotoFromCamera("");
                } catch (Exception unused) {
                    BuildingUnderIllegalOccupation.this.isCapturing = false;
                    Toast.makeText(BuildingUnderIllegalOccupation.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        MaterialSpinner materialSpinner = (MaterialSpinner) view.findViewById(R.id.sp_remarks);
        this.sp_remarks = materialSpinner;
        materialSpinner.setItems(REMARKS_LIST);
        this.sp_remarks.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() { // from class: com.micromerger.ssms.startmonitoring.fragments.BuildingUnderIllegalOccupation.2
            @Override // com.jaredrummler.materialspinner.MaterialSpinner.OnItemSelectedListener
            public void onItemSelected(MaterialSpinner view2, int position, long id2, String item) {
                BuildingUnderIllegalOccupation.this.remarksId = position;
                if (item.equals(BuildingUnderIllegalOccupation.REMARKS_LIST[3])) {
                    BuildingUnderIllegalOccupation.this.isOtherRemarks = true;
                    BuildingUnderIllegalOccupation.this.rl_comments.setVisibility(0);
                } else {
                    BuildingUnderIllegalOccupation.this.isOtherRemarks = false;
                    BuildingUnderIllegalOccupation.this.rl_comments.setVisibility(8);
                }
            }
        });
        this.sp_remarks.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BuildingUnderIllegalOccupation.3
            @Override // com.jaredrummler.materialspinner.MaterialSpinner.OnNothingSelectedListener
            public void onNothingSelected(MaterialSpinner spinner) {
            }
        });
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.BuildingUnderIllegalOccupation.4
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
        Button button = (Button) this.mView.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.mView.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.building_illegal_fields.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Has_Building)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.buildingId = i;
                    ((RadioButton) this.rg_school_building.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Illegal_Occupation)) {
                    int i2 = Integer.parseInt((String) value.getDataValue());
                    this.buildingillegalOcp = i2;
                    ((RadioButton) this.rg_builiding_illegal.getChildAt(i2)).setChecked(true);
                    if (this.buildingillegalOcp == 0) {
                        this.isPreviewVisible = true;
                        this.previewImage.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Remarks_Id)) {
                    int i3 = Integer.parseInt((String) value.getDataValue());
                    this.remarksId = i3;
                    this.sp_remarks.setSelectedIndex(i3);
                    if (this.remarksId == 3) {
                        this.isOtherRemarks = true;
                        this.rl_comments.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    String str = (String) value.getDataValue();
                    this.comments = str;
                    this.editText_comments.setText(str);
                } else if (value.getKRAName().equals(Constant.Monitoring_Image)) {
                    this.imageBaseUrl64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBaseUrl64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
                    }
                    if (this.imageBaseUrl64.length() > 0) {
                        this.isPreviewVisible = true;
                    }
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        if (this.buildingillegalOcp == 0) {
            if (this.remarksId == 3) {
                this.rl_comments.setVisibility(0);
            } else {
                this.rl_comments.setVisibility(8);
            }
            if (this.imageBaseUrl64.length() > 0) {
                this.previewImage.setVisibility(0);
            } else {
                this.previewImage.setVisibility(8);
            }
        } else {
            this.rl_comments.setVisibility(8);
            this.previewImage.setVisibility(8);
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.rg_school_building.setEnabled(false);
            this.rg_school_building.setFocusable(false);
            for (int i4 = 0; i4 < this.rg_school_building.getChildCount(); i4++) {
                ((RadioButton) this.rg_school_building.getChildAt(i4)).setFocusable(false);
                ((RadioButton) this.rg_school_building.getChildAt(i4)).setEnabled(false);
            }
            this.rg_builiding_illegal.setEnabled(false);
            this.rg_builiding_illegal.setFocusable(false);
            for (int i5 = 0; i5 < this.rg_builiding_illegal.getChildCount(); i5++) {
                ((RadioButton) this.rg_builiding_illegal.getChildAt(i5)).setFocusable(false);
                ((RadioButton) this.rg_builiding_illegal.getChildAt(i5)).setEnabled(false);
            }
            this.uploadImage.setEnabled(false);
            this.uploadImage.setFocusable(false);
            this.uploadImage.setAlpha(0.5f);
            this.sp_remarks.setEnabled(false);
            this.sp_remarks.setFocusable(false);
            this.editText_comments.setEnabled(false);
            this.editText_comments.setFocusable(false);
            this.save_button.setEnabled(false);
            this.save_button.setAlpha(0.5f);
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.cancel_button) {
            checkData();
            return;
        }
        if (id2 != R.id.save_button) {
            return;
        }
        this.comments = this.editText_comments.getText().toString();
        int i = this.buildingId;
        if (i == -1) {
            DialogCustom.showError(getActivityContext(), "Please select the fields.");
            return;
        }
        if (i != 0) {
            if (i == 1) {
                saveFieldsData();
                return;
            }
            return;
        }
        int i2 = this.buildingillegalOcp;
        if (i2 == -1) {
            DialogCustom.showError(getActivityContext(), "Please select the fields.");
            return;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                saveFieldsData();
            }
        } else {
            if (!this.imageBaseUrl64.equals("")) {
                if (this.remarksId == 3) {
                    if (!this.comments.equals("")) {
                        saveFieldsData();
                        return;
                    } else {
                        DialogCustom.showError(getActivityContext(), "Please write other remarks.");
                        return;
                    }
                }
                saveFieldsData();
                return;
            }
            DialogCustom.showError(getActivityContext(), "Please upload image.");
        }
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.BuildingUnderIllegalOccupation.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.building_illegal_fields.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Has_Building)) {
                        value.setDataValue(String.valueOf(BuildingUnderIllegalOccupation.this.buildingId));
                    } else if (value.getKRAName().equals(Constant.Illegal_Occupation)) {
                        if (BuildingUnderIllegalOccupation.this.buildingillegalOcp == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(BuildingUnderIllegalOccupation.this.buildingillegalOcp));
                        }
                    } else if (value.getKRAName().equals(Constant.Remarks_Id)) {
                        if (BuildingUnderIllegalOccupation.this.buildingillegalOcp == 0) {
                            value.setDataValue(String.valueOf(BuildingUnderIllegalOccupation.this.remarksId));
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(BuildingUnderIllegalOccupation.this.comments);
                    } else if (value.getKRAName().equals(Constant.Monitoring_Image)) {
                        value.setDataValue(BuildingUnderIllegalOccupation.this.imageBaseUrl64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (BuildingUnderIllegalOccupation.this.location != null) {
                            value.setDataValue(Double.valueOf(BuildingUnderIllegalOccupation.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude) && BuildingUnderIllegalOccupation.this.location != null) {
                        value.setDataValue(Double.valueOf(BuildingUnderIllegalOccupation.this.location.getLongitude()));
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(BuildingUnderIllegalOccupation.this.getActivityContext()).updateSchoolData(i, CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        try {
            this.fm.popBackStack();
        } catch (Exception e) {
            util.logException(e);
        }
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            try {
                this.fm.popBackStack();
                return;
            } catch (Exception e) {
                util.logException(e);
                return;
            }
        }
        if (this.buildingId > -1 || this.buildingillegalOcp > -1 || this.comments.length() > 0 || this.imageBaseUrl64.length() > 0) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else {
            this.fm.popBackStack();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_does_the_school_have_a_building_no /* 2131231512 */:
                this.buildingId = 1;
                this.buildingillegalOcp = -1;
                this.rl_illegal_building.setVisibility(8);
                break;
            case R.id.rb_does_the_school_have_a_building_yes /* 2131231513 */:
                this.buildingId = 0;
                this.rl_illegal_building.setVisibility(0);
                ((RadioButton) this.mView.findViewById(R.id.rb_no)).setChecked(true);
                break;
            case R.id.rb_no /* 2131231547 */:
                this.buildingillegalOcp = 1;
                this.rl_ramarks.setVisibility(8);
                this.rl_comments.setVisibility(8);
                this.previewImage.setVisibility(8);
                this.uploadImage.setVisibility(8);
                break;
            case R.id.rb_yes /* 2131231607 */:
                this.buildingillegalOcp = 0;
                this.rl_ramarks.setVisibility(0);
                if (this.isOtherRemarks) {
                    this.rl_comments.setVisibility(0);
                } else {
                    this.rl_comments.setVisibility(8);
                }
                if (this.isPreviewVisible) {
                    this.previewImage.setVisibility(0);
                } else {
                    this.previewImage.setVisibility(8);
                }
                this.uploadImage.setVisibility(0);
                break;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath) {
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock) {
            return;
        }
        if (bitmap != null) {
            this.isPreviewVisible = true;
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
        if (bitmapPath != null) {
            this.imageBaseUrl64 = bitmapPath;
        }
    }
}
