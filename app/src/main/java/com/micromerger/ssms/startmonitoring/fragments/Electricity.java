package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class Electricity extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    List<ReferenceDataResponse.ReferenceData> SOURCE_LIST;
    RelativeLayout availabilityLayout;
    Button cancel_button;
    RelativeLayout commentsLayout;
    EditText editText_comments;
    EditText et_source_other_comments;
    boolean isOtherSourceSelected;
    boolean isPreviewVisible;
    View mView;
    LinearLayout no_electricity_layout;
    ImageView previewImage;
    RadioGroup rg_availability;
    RadioGroup rg_no_electricity_reason;
    RadioGroup rg_source;
    RelativeLayout rv_source;
    Button save_button;
    RelativeLayout source_other_comment_Layout;
    String title;
    TextView tv_census_widget;
    ImageView uploadImage;
    int availabilityId = -1;
    int sourceId = -1;
    int noElectricityId = -1;
    String imageBase64 = "";
    boolean isCapturing = false;

    public Electricity() {
    }

    public Electricity(String s) {
        this.title = s;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_widget, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        setCameraActivityCallBack(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.tv_census_widget);
        this.tv_census_widget = textView;
        textView.setText(this.title);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.availabilityLayout);
        this.availabilityLayout = relativeLayout;
        relativeLayout.setVisibility(0);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_availability);
        this.rg_availability = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.no_electricity_layout = (LinearLayout) view.findViewById(R.id.no_electricity_layout);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.rv_source);
        this.rv_source = relativeLayout2;
        relativeLayout2.setVisibility(8);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_source);
        this.rg_source = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        RadioGroup radioGroup3 = (RadioGroup) view.findViewById(R.id.rg_no_electricity_reason);
        this.rg_no_electricity_reason = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.source_other_comment_Layout);
        this.source_other_comment_Layout = relativeLayout3;
        relativeLayout3.setVisibility(8);
        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.commentsLayout);
        this.commentsLayout = relativeLayout4;
        relativeLayout4.setVisibility(8);
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Electricity.1
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
        EditText editText2 = (EditText) view.findViewById(R.id.et_source_other_comments);
        this.et_source_other_comments = editText2;
        editText2.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Electricity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_source_other_comments) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Electricity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Electricity.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    Electricity.this.getPhotoFromCamera(iIntValue + "_173_" + Constant.Electricity_Monitering_Image);
                } catch (Exception unused) {
                    Electricity.this.isCapturing = false;
                    Toast.makeText(Electricity.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Electricity$sAf4hoFOtTzWhF2F7L1XlcrW4DM
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("ELECTRICITY_SOURCE");
            }
        });
        if (collectionSelect instanceof List) {
            this.SOURCE_LIST = (List) collectionSelect;
        } else {
            this.SOURCE_LIST = new ArrayList(collectionSelect);
        }
        if (this.SOURCE_LIST.isEmpty()) {
            return;
        }
        this.rg_source.setOrientation(1);
        for (int i = 0; i < this.SOURCE_LIST.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(this.SOURCE_LIST.get(i).getReferencedataid().intValue());
            radioButton.setText(this.SOURCE_LIST.get(i).getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Electricity$xOdgsCCUwec7Y4n4KGgeRdG8uzQ
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$onViewCreated$1$Electricity(compoundButton, z);
                }
            });
            this.rg_source.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$Electricity(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.SOURCE_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.sourceId = referenceData.getReferencekey().intValue();
                    if (referenceData.getReferencevalue().equals("Other")) {
                        this.isOtherSourceSelected = true;
                        this.source_other_comment_Layout.setVisibility(0);
                    } else {
                        this.isOtherSourceSelected = false;
                        this.source_other_comment_Layout.setVisibility(8);
                    }
                }
            }
            Log.d("TAG", "onCheckedChanged: " + compoundButton.getId());
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.electricity.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Electricity_Facility_Availabile)) {
                    try {
                        int i = Integer.parseInt((String) value.getDataValue());
                        this.availabilityId = i;
                        ((RadioButton) this.rg_availability.getChildAt(i)).setChecked(true);
                    } catch (Exception e) {
                        util.logException(new Exception("Electricity Availability: " + e));
                    }
                } else if (value.getKRAName().equals(Constant.Electricity_Source)) {
                    try {
                        this.sourceId = Integer.parseInt((String) value.getDataValue());
                        if (!this.SOURCE_LIST.isEmpty()) {
                            int i2 = 0;
                            while (true) {
                                if (i2 >= this.SOURCE_LIST.size()) {
                                    break;
                                }
                                if (this.SOURCE_LIST.get(i2).getReferencekey().intValue() == this.sourceId) {
                                    ((RadioButton) this.rg_source.getChildAt(i2)).setChecked(true);
                                    break;
                                }
                                i2++;
                            }
                        }
                        if (this.sourceId == 2) {
                            this.isOtherSourceSelected = true;
                        } else {
                            this.isOtherSourceSelected = false;
                        }
                    } catch (Exception e2) {
                        util.logException(new Exception("Electricity Source: " + e2));
                    }
                } else if (value.getKRAName().equals(Constant.Other_Comments)) {
                    this.et_source_other_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.editText_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Electricity_Monitering_Image)) {
                    this.imageBase64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBase64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
                    } else {
                        String str = this.imageBase64;
                        if (str != null) {
                            byte[] bArrDecode = Base64.decode(str, 0);
                            try {
                                this.previewImage.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
                                this.previewImage.setVisibility(0);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    if (this.imageBase64.length() > 0) {
                        this.isPreviewVisible = true;
                    } else {
                        this.isPreviewVisible = false;
                    }
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals("No_Electricity_Reason")) {
                    try {
                        int i3 = Integer.parseInt((String) value.getDataValue());
                        this.noElectricityId = i3;
                        ((RadioButton) this.rg_no_electricity_reason.getChildAt(i3 - 1)).setChecked(true);
                    } catch (Exception e4) {
                        util.logException(new Exception("Electricity: " + e4));
                    }
                }
            }
        }
        if (this.availabilityId == 0) {
            this.rv_source.setVisibility(0);
            this.commentsLayout.setVisibility(0);
            if (this.isOtherSourceSelected) {
                this.source_other_comment_Layout.setVisibility(0);
            } else {
                this.source_other_comment_Layout.setVisibility(8);
            }
            if (this.isPreviewVisible) {
                this.previewImage.setVisibility(0);
            } else {
                this.previewImage.setVisibility(8);
            }
            this.uploadImage.setVisibility(0);
        } else {
            this.rv_source.setVisibility(8);
            this.source_other_comment_Layout.setVisibility(8);
            this.commentsLayout.setVisibility(8);
            if (this.isPreviewVisible) {
                this.previewImage.setVisibility(0);
            } else {
                this.previewImage.setVisibility(8);
            }
            this.uploadImage.setVisibility(0);
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_availability.setEnabled(false);
        this.rg_availability.setFocusable(false);
        for (int i4 = 0; i4 < this.rg_availability.getChildCount(); i4++) {
            ((RadioButton) this.rg_availability.getChildAt(i4)).setFocusable(false);
            ((RadioButton) this.rg_availability.getChildAt(i4)).setEnabled(false);
        }
        this.rg_source.setEnabled(false);
        this.rg_source.setFocusable(false);
        for (int i5 = 0; i5 < this.rg_source.getChildCount(); i5++) {
            ((RadioButton) this.rg_source.getChildAt(i5)).setFocusable(false);
            ((RadioButton) this.rg_source.getChildAt(i5)).setEnabled(false);
        }
        this.rg_no_electricity_reason.setEnabled(false);
        this.rg_no_electricity_reason.setFocusable(false);
        for (int i6 = 0; i6 < this.rg_no_electricity_reason.getChildCount(); i6++) {
            ((RadioButton) this.rg_no_electricity_reason.getChildAt(i6)).setFocusable(false);
            ((RadioButton) this.rg_no_electricity_reason.getChildAt(i6)).setEnabled(false);
        }
        this.et_source_other_comments.setEnabled(false);
        this.et_source_other_comments.setFocusable(false);
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.editText_comments.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
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
            this.imageBase64 = bitmapPath;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.cancel_button) {
            checkData();
        } else {
            if (id2 != R.id.save_button) {
                return;
            }
            checkValidation();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_availability_no /* 2131231486 */:
                this.availabilityId = 1;
                this.rv_source.setVisibility(8);
                this.no_electricity_layout.setVisibility(0);
                this.source_other_comment_Layout.setVisibility(8);
                this.commentsLayout.setVisibility(8);
                this.et_source_other_comments.setText("");
                this.sourceId = -1;
                if (this.isPreviewVisible) {
                    this.previewImage.setVisibility(0);
                    break;
                } else {
                    this.previewImage.setVisibility(8);
                    break;
                }
            case R.id.rb_availability_yes /* 2131231487 */:
                this.availabilityId = 0;
                this.rv_source.setVisibility(0);
                this.no_electricity_layout.setVisibility(8);
                this.commentsLayout.setVisibility(0);
                if (this.isOtherSourceSelected) {
                    this.source_other_comment_Layout.setVisibility(0);
                } else {
                    this.source_other_comment_Layout.setVisibility(8);
                }
                if (this.isPreviewVisible) {
                    this.previewImage.setVisibility(0);
                    break;
                } else {
                    this.previewImage.setVisibility(8);
                    break;
                }
            case R.id.rb_no_electricity_area /* 2131231548 */:
                this.noElectricityId = 2;
                break;
            case R.id.rb_no_electricity_disconnected /* 2131231549 */:
                this.noElectricityId = 1;
                break;
        }
    }

    private void checkValidation() {
        int i = this.availabilityId;
        if (i != 0) {
            if (i == 1) {
                if (this.noElectricityId == -1) {
                    DialogCustom.showError(getActivityContext(), "Please select reason of no electricity.");
                    return;
                } else {
                    saveFieldsData();
                    return;
                }
            }
            DialogCustom.showError(getActivityContext(), "Please select electricity availability.");
            return;
        }
        int i2 = this.sourceId;
        if (i2 != 0 && i2 != 1) {
            if (i2 == 2) {
                if (this.et_source_other_comments.getText().toString().length() > 0) {
                    if (this.imageBase64.length() > 0) {
                        saveFieldsData();
                        return;
                    } else {
                        DialogCustom.showError(getActivityContext(), "Please upload image.");
                        return;
                    }
                }
                this.et_source_other_comments.setError("Please fill the field.");
                DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
                return;
            }
            if (i2 != 3 && i2 != 4 && i2 != 5) {
                DialogCustom.showError(getActivityContext(), "Please select source of electricity.");
                return;
            }
        }
        if (this.imageBase64.length() > 0) {
            saveFieldsData();
        } else {
            DialogCustom.showError(getActivityContext(), "Please upload image.");
        }
    }

    private String bitmapToBasr64String(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            if (getActivity() != null) {
                this.fm.popBackStack();
            }
        } else if (this.availabilityId > -1 || this.sourceId > -1 || this.imageBase64.length() > 0 || this.editText_comments.getText().toString().length() > 0 || this.et_source_other_comments.getText().toString().length() > 0) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.Electricity.4
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.electricity.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Electricity_Facility_Availabile)) {
                        if (Electricity.this.availabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Electricity.this.availabilityId));
                        }
                    } else if (value.getKRAName().equals(Constant.Electricity_Source)) {
                        if (Electricity.this.sourceId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Electricity.this.sourceId));
                        }
                    } else if (value.getKRAName().equals(Constant.Other_Comments)) {
                        if (Electricity.this.sourceId == 2) {
                            value.setDataValue(Electricity.this.et_source_other_comments.getText().toString());
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(Electricity.this.editText_comments.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Electricity_Monitering_Image)) {
                        value.setDataValue(Electricity.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (Electricity.this.location != null) {
                            value.setDataValue(Double.valueOf(Electricity.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (Electricity.this.location != null) {
                            value.setDataValue(Double.valueOf(Electricity.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date)) {
                        if (!Electricity.this.imageBase64.equals("")) {
                            value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                        }
                    } else if (value.getKRAName().equals("No_Electricity_Reason")) {
                        if (Electricity.this.noElectricityId != -1 && Electricity.this.availabilityId == 1) {
                            value.setDataValue(String.valueOf(Electricity.this.noElectricityId));
                        } else {
                            value.setDataValue(null);
                        }
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                int i = 0;
                while (true) {
                    if (i >= CommonObjects.employeeData.size()) {
                        break;
                    }
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(Electricity.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isElectricityDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
