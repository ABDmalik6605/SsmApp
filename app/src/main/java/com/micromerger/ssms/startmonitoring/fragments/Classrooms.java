package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/* loaded from: classes2.dex */
public class Classrooms extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener {
    Button cancel_button;
    EditText et_community_centre;
    EditText et_district_administration_offices;
    EditText et_ngo;
    EditText et_no_of_classrooms_storing_old_furniture_ftbs_etc;
    EditText et_no_of_classrooms_used_for_teaching;
    EditText et_occupied_by_villager;
    EditText et_other_specify;
    EditText et_total_no_of_classrooms;
    EditText et_vocational_training_centre;
    String imageBase64 = "";
    boolean isCapturing = false;
    View mView;
    ImageView previewImage;
    Button save_button;
    Button skip_button;
    ImageView uploadImage;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_classrooms, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        setCameraActivityCallBack(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.et_total_no_of_classrooms = (EditText) view.findViewById(R.id.et_total_no_of_classrooms);
        this.et_no_of_classrooms_used_for_teaching = (EditText) view.findViewById(R.id.et_no_of_classrooms_used_for_teaching);
        this.et_no_of_classrooms_storing_old_furniture_ftbs_etc = (EditText) view.findViewById(R.id.et_no_of_classrooms_storing_old_furniture_ftbs_etc);
        this.et_ngo = (EditText) view.findViewById(R.id.et_ngo);
        this.et_district_administration_offices = (EditText) view.findViewById(R.id.et_district_administration_offices);
        this.et_occupied_by_villager = (EditText) view.findViewById(R.id.et_occupied_by_villager);
        this.et_community_centre = (EditText) view.findViewById(R.id.et_community_centre);
        this.et_vocational_training_centre = (EditText) view.findViewById(R.id.et_vocational_training_centre);
        this.et_other_specify = (EditText) view.findViewById(R.id.et_other_specify);
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(0);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Classrooms.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Classrooms.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    Classrooms.this.getPhotoFromCamera(iIntValue + "_254_" + Constant.Room_Image);
                } catch (Exception unused) {
                    Classrooms.this.isCapturing = false;
                    Toast.makeText(Classrooms.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.skip_button);
        this.skip_button = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Classrooms$4xeRuuL-ORWOJZbebeMrAaUIfl8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$Classrooms(view2);
            }
        });
    }

    public /* synthetic */ void lambda$onViewCreated$0$Classrooms(View view) {
        skipFields();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.classrooms.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Total_Classrooms)) {
                    this.et_total_no_of_classrooms.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Teaching_Classrooms)) {
                    this.et_no_of_classrooms_used_for_teaching.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Non_Teaching_Rooms)) {
                    this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.NGO)) {
                    this.et_ngo.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.DAO)) {
                    this.et_district_administration_offices.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Occupied_by_Villager)) {
                    this.et_occupied_by_villager.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Community_Centre)) {
                    this.et_community_centre.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Vocational_Training_Centre)) {
                    this.et_vocational_training_centre.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Other_Specify)) {
                    this.et_other_specify.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Room_Image)) {
                    this.imageBase64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBase64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
                    }
                    if (this.imageBase64.length() > 0) {
                        this.previewImage.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.et_total_no_of_classrooms.setEnabled(false);
        this.et_total_no_of_classrooms.setFocusable(false);
        this.et_no_of_classrooms_used_for_teaching.setEnabled(false);
        this.et_no_of_classrooms_used_for_teaching.setFocusable(false);
        this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.setEnabled(false);
        this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.setFocusable(false);
        this.et_ngo.setEnabled(false);
        this.et_ngo.setFocusable(false);
        this.et_district_administration_offices.setEnabled(false);
        this.et_district_administration_offices.setFocusable(false);
        this.et_occupied_by_villager.setEnabled(false);
        this.et_occupied_by_villager.setFocusable(false);
        this.et_community_centre.setEnabled(false);
        this.et_community_centre.setFocusable(false);
        this.et_vocational_training_centre.setEnabled(false);
        this.et_vocational_training_centre.setFocusable(false);
        this.et_other_specify.setEnabled(false);
        this.et_other_specify.setFocusable(false);
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
        this.skip_button.setEnabled(false);
        this.skip_button.setAlpha(0.5f);
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
        } else {
            if (id2 != R.id.save_button) {
                return;
            }
            checkValidation();
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void skipFields() {
        this.et_total_no_of_classrooms.setText(Constant.ECE_Katchi);
        this.et_no_of_classrooms_used_for_teaching.setText(Constant.ECE_Katchi);
        this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.setText(Constant.ECE_Katchi);
        this.et_ngo.setText(Constant.ECE_Katchi);
        this.et_district_administration_offices.setText(Constant.ECE_Katchi);
        this.et_occupied_by_villager.setText(Constant.ECE_Katchi);
        this.et_community_centre.setText(Constant.ECE_Katchi);
        this.et_vocational_training_centre.setText(Constant.ECE_Katchi);
        this.et_other_specify.setText(Constant.ECE_Katchi);
        checkValidation();
    }

    private void checkValidation() {
        if (this.et_total_no_of_classrooms.getText().toString().length() > 0 && this.et_no_of_classrooms_used_for_teaching.getText().toString().length() > 0 && this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString().length() > 0 && this.et_ngo.getText().toString().length() > 0 && this.et_district_administration_offices.getText().toString().length() > 0 && this.et_occupied_by_villager.getText().toString().length() > 0 && this.et_community_centre.getText().toString().length() > 0 && this.et_vocational_training_centre.getText().toString().length() > 0 && this.et_other_specify.getText().toString().length() > 0) {
            if (Integer.parseInt(this.et_no_of_classrooms_used_for_teaching.getText().toString()) + Integer.parseInt(this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString()) <= Integer.parseInt(this.et_total_no_of_classrooms.getText().toString())) {
                if (Integer.parseInt(this.et_ngo.getText().toString()) + Integer.parseInt(this.et_district_administration_offices.getText().toString()) + Integer.parseInt(this.et_occupied_by_villager.getText().toString()) + Integer.parseInt(this.et_community_centre.getText().toString()) + Integer.parseInt(this.et_vocational_training_centre.getText().toString()) + Integer.parseInt(this.et_other_specify.getText().toString()) == Integer.parseInt(this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString())) {
                    saveFieldsData();
                    return;
                } else {
                    DialogCustom.showError(getActivityContext(), "Rooms utilized for other than learning/teaching purpose should be equal to No. of rooms used for other than teaching purpose.");
                    return;
                }
            }
            DialogCustom.showError(getActivityContext(), "Sum of No. of classrooms used for teaching and No. of rooms used for other than teaching purpose should not be greater than Total No. of rooms.");
            return;
        }
        DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
        if (this.et_total_no_of_classrooms.getText().toString().equals("")) {
            showError(this.et_total_no_of_classrooms);
            return;
        }
        if (this.et_no_of_classrooms_used_for_teaching.getText().toString().equals("")) {
            showError(this.et_no_of_classrooms_used_for_teaching);
            return;
        }
        if (this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString().equals("")) {
            showError(this.et_no_of_classrooms_storing_old_furniture_ftbs_etc);
            return;
        }
        if (this.et_ngo.getText().toString().equals("")) {
            showError(this.et_ngo);
            return;
        }
        if (this.et_district_administration_offices.getText().toString().equals("")) {
            showError(this.et_district_administration_offices);
            return;
        }
        if (this.et_occupied_by_villager.getText().toString().equals("")) {
            showError(this.et_occupied_by_villager);
            return;
        }
        if (this.et_community_centre.getText().toString().equals("")) {
            showError(this.et_community_centre);
        } else if (this.et_vocational_training_centre.getText().toString().equals("")) {
            showError(this.et_vocational_training_centre);
        } else if (this.et_other_specify.getText().toString().equals("")) {
            showError(this.et_other_specify);
        }
    }

    private void showError(EditText et) {
        et.setError("Please fill the field.");
        et.setFocusableInTouchMode(true);
        et.setFocusable(true);
        et.getParent().requestChildFocus(et, et);
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            if (getActivity() != null) {
                this.fm.popBackStack();
                return;
            }
            return;
        }
        if (this.et_total_no_of_classrooms.getText().toString().length() > 0 || this.et_no_of_classrooms_used_for_teaching.getText().toString().length() > 0 || this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString().length() > 0 || this.et_ngo.getText().toString().length() > 0 || this.et_district_administration_offices.getText().toString().length() > 0 || this.et_occupied_by_villager.getText().toString().length() > 0 || this.et_community_centre.getText().toString().length() > 0 || this.et_vocational_training_centre.getText().toString().length() > 0 || this.et_other_specify.getText().toString().length() > 0 || this.imageBase64.length() > 0) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.Classrooms.2
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.classrooms.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Total_Classrooms)) {
                        value.setDataValue(Classrooms.this.et_total_no_of_classrooms.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Teaching_Classrooms)) {
                        value.setDataValue(Classrooms.this.et_no_of_classrooms_used_for_teaching.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Non_Teaching_Rooms)) {
                        value.setDataValue(Classrooms.this.et_no_of_classrooms_storing_old_furniture_ftbs_etc.getText().toString());
                    } else if (value.getKRAName().equals(Constant.NGO)) {
                        value.setDataValue(Classrooms.this.et_ngo.getText().toString());
                    } else if (value.getKRAName().equals(Constant.DAO)) {
                        value.setDataValue(Classrooms.this.et_district_administration_offices.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Occupied_by_Villager)) {
                        value.setDataValue(Classrooms.this.et_occupied_by_villager.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Community_Centre)) {
                        value.setDataValue(Classrooms.this.et_community_centre.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Vocational_Training_Centre)) {
                        value.setDataValue(Classrooms.this.et_vocational_training_centre.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Other_Specify)) {
                        value.setDataValue(Classrooms.this.et_other_specify.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Room_Image)) {
                        value.setDataValue(Classrooms.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (Classrooms.this.location != null) {
                            value.setDataValue(Double.valueOf(Classrooms.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (Classrooms.this.location != null) {
                            value.setDataValue(Double.valueOf(Classrooms.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date) && !Classrooms.this.imageBase64.equals("")) {
                        value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
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
                        CommonActions.getDbHandler(Classrooms.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isClassroomsDATA = true;
            }
        }).start();
        if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }
}
