package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.micromerger.ssms.utils.widgets.DialogCustom;
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
public class Furniture extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    List<ReferenceDataResponse.ReferenceData> FURNITURE_LIST;
    Button cancel_button;
    RelativeLayout commentsLayout;
    EditText editText_comments;
    EditText et_sitting_capacity_students;
    EditText et_sitting_capacity_teachers;
    RelativeLayout furnitureAvailabilityLayout;
    View mView;
    ImageView previewImage;
    RadioGroup rg_availability;
    Button save_button;
    LinearLayout sitting_capacity_layout;
    String title;
    TextView tv_census_widget;
    ImageView uploadImage;
    int availabilityId = -1;
    String imageBase64 = "";
    boolean isCapturing = false;

    public Furniture() {
    }

    public Furniture(String s) {
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
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.furnitureAvailabilityLayout);
        this.furnitureAvailabilityLayout = relativeLayout;
        relativeLayout.setVisibility(0);
        this.sitting_capacity_layout = (LinearLayout) view.findViewById(R.id.sitting_capacity_layout);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_furniture_availability);
        this.rg_availability = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.commentsLayout);
        this.commentsLayout = relativeLayout2;
        relativeLayout2.setVisibility(0);
        this.et_sitting_capacity_teachers = (EditText) view.findViewById(R.id.et_sitting_capacity_teachers);
        this.et_sitting_capacity_students = (EditText) view.findViewById(R.id.et_sitting_capacity_students);
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Furniture.1
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
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(0);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Furniture.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Furniture.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    Furniture.this.getPhotoFromCamera(iIntValue + "_146_" + Constant.Furniture_Monitering_Image);
                } catch (Exception unused) {
                    Furniture.this.isCapturing = false;
                    Toast.makeText(Furniture.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Furniture$CJcHdY4Mnpxa545Aml90VrHSHq4
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("FURNITURE_AVAILABILITY");
            }
        });
        if (collectionSelect instanceof List) {
            this.FURNITURE_LIST = (List) collectionSelect;
        } else {
            this.FURNITURE_LIST = new ArrayList(collectionSelect);
        }
        if (this.FURNITURE_LIST.isEmpty()) {
            return;
        }
        this.rg_availability.setOrientation(1);
        for (int i = 0; i < this.FURNITURE_LIST.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(this.FURNITURE_LIST.get(i).getReferencedataid().intValue());
            radioButton.setText(this.FURNITURE_LIST.get(i).getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Furniture$KtiMXEcfsMo-BPtiKzgDH-QzQSE
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$onViewCreated$1$Furniture(compoundButton, z);
                }
            });
            this.rg_availability.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$Furniture(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.FURNITURE_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    int iIntValue = referenceData.getReferencekey().intValue();
                    this.availabilityId = iIntValue;
                    if (iIntValue == 1) {
                        this.sitting_capacity_layout.setVisibility(8);
                    } else {
                        this.sitting_capacity_layout.setVisibility(0);
                    }
                }
            }
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
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.furniture.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Is_Availabile)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.availabilityId = i;
                    ((RadioButton) this.rg_availability.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.editText_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Furniture_Monitering_Image)) {
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
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (this.imageBase64.length() > 0) {
                        this.previewImage.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals("Sitting_Capacity_Available_For_Teachers")) {
                    this.et_sitting_capacity_teachers.setText(String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals("Sitting_Capacity_Available_For_Students")) {
                    this.et_sitting_capacity_students.setText(String.valueOf(value.getDataValue()));
                }
            }
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_availability.setEnabled(false);
        this.rg_availability.setFocusable(false);
        for (int i2 = 0; i2 < this.rg_availability.getChildCount(); i2++) {
            ((RadioButton) this.rg_availability.getChildAt(i2)).setFocusable(false);
            ((RadioButton) this.rg_availability.getChildAt(i2)).setEnabled(false);
        }
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.editText_comments.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.et_sitting_capacity_teachers.setEnabled(false);
        this.et_sitting_capacity_teachers.setFocusable(false);
        this.et_sitting_capacity_students.setEnabled(false);
        this.et_sitting_capacity_students.setFocusable(false);
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
            return;
        }
        if (id2 != R.id.save_button) {
            return;
        }
        int i = this.availabilityId;
        if (i <= -1) {
            DialogCustom.showError(getActivityContext(), "Please select the furniture availability.");
            this.rg_availability.setFocusableInTouchMode(true);
            this.rg_availability.setFocusable(true);
            return;
        }
        if (i == 0 || i == 2) {
            if (TextUtils.isEmpty(this.et_sitting_capacity_teachers.getText().toString().trim())) {
                DialogCustom.showError(getActivityContext(), "Please enter sitting capacity for teachers");
                this.et_sitting_capacity_teachers.setError("Please enter sitting capacity for teachers");
                return;
            } else if (TextUtils.isEmpty(this.et_sitting_capacity_students.getText().toString().trim())) {
                DialogCustom.showError(getActivityContext(), "Please enter sitting capacity for students");
                this.et_sitting_capacity_students.setError("Please enter sitting capacity for students");
                return;
            } else {
                if (!this.imageBase64.equals("")) {
                    saveFieldsData();
                    return;
                }
                DialogCustom.showError(getActivityContext(), "Please upload image.");
                this.uploadImage.setFocusableInTouchMode(true);
                this.uploadImage.setFocusable(true);
                return;
            }
        }
        saveFieldsData();
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
        } else if (this.imageBase64.length() > 0 || this.editText_comments.getText().toString().length() > 0 || this.availabilityId > -1) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else {
            this.fm.popBackStack();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_availability_no /* 2131231486 */:
                this.availabilityId = 1;
                break;
            case R.id.rb_availability_yes /* 2131231487 */:
                this.availabilityId = 0;
                break;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.Furniture.3
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.furniture.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Is_Availabile)) {
                        if (Furniture.this.availabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Furniture.this.availabilityId));
                        }
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(Furniture.this.editText_comments.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Furniture_Monitering_Image)) {
                        value.setDataValue(Furniture.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (Furniture.this.location != null) {
                            value.setDataValue(Double.valueOf(Furniture.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (Furniture.this.location != null) {
                            value.setDataValue(Double.valueOf(Furniture.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date)) {
                        if (!Furniture.this.imageBase64.equals("")) {
                            value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                        }
                    } else if (value.getKRAName().equals("Sitting_Capacity_Available_For_Teachers")) {
                        if (Furniture.this.availabilityId == 0 || Furniture.this.availabilityId == 2) {
                            value.setDataValue(Furniture.this.et_sitting_capacity_teachers.getText().toString().trim());
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals("Sitting_Capacity_Available_For_Students")) {
                        if (Furniture.this.availabilityId == 0 || Furniture.this.availabilityId == 2) {
                            value.setDataValue(Furniture.this.et_sitting_capacity_students.getText().toString().trim());
                        } else {
                            value.setDataValue("");
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
                        CommonActions.getDbHandler(Furniture.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isFurnitureDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
