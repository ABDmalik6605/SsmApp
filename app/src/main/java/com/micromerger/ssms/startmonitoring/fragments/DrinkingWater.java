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
public class DrinkingWater extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    List<ReferenceDataResponse.ReferenceData> PROVISIONS_LIST;
    RelativeLayout availabilityLayout;
    LinearLayout bore_availability_layout;
    LinearLayout bore_drink_layout;
    Button cancel_button;
    RelativeLayout commentsLayout;
    EditText editText_comments;
    View mView;
    LinearLayout pipe_availability_layout;
    LinearLayout pipe_drink_layout;
    ImageView previewImage;
    LinearLayout pump_availability_layout;
    LinearLayout pump_drink_layout;
    RadioGroup rg_availability;
    RadioGroup rg_bore_availability;
    RadioGroup rg_bore_drink;
    RadioGroup rg_pipe_availability;
    RadioGroup rg_pipe_drink;
    RadioGroup rg_pump_availability;
    RadioGroup rg_pump_drink;
    RadioGroup rg_tanker_availability;
    RadioGroup rg_tanker_drink;
    RadioGroup rg_water_provision;
    RadioGroup rg_well_availability;
    RadioGroup rg_well_drink;
    Button save_button;
    LinearLayout tanker_availability_layout;
    LinearLayout tanker_drink_layout;
    String title;
    TextView tv_census_widget;
    ImageView uploadImage;
    LinearLayout water_provision_layout;
    LinearLayout well_availability_layout;
    LinearLayout well_drink_layout;
    int availabilityId = -1;
    int boreAvailabilityId = -1;
    int boreDrinkId = -1;
    int pumpAvailabilityId = -1;
    int pumpDrinkId = -1;
    int pipeAvailabilityId = -1;
    int pipeDrinkId = -1;
    int wellAvailabilityId = -1;
    int wellDrinkId = -1;
    int tankerAvailabilityId = -1;
    int tankerDrinkId = -1;
    int waterProvisionId = -1;
    String imageBase64 = "";
    boolean isCapturing = false;

    public DrinkingWater() {
    }

    public DrinkingWater(String s) {
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
        this.bore_availability_layout = (LinearLayout) view.findViewById(R.id.bore_availability_layout);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_bore_availability);
        this.rg_bore_availability = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        this.bore_drink_layout = (LinearLayout) view.findViewById(R.id.bore_drink_layout);
        RadioGroup radioGroup3 = (RadioGroup) view.findViewById(R.id.rg_bore_drink);
        this.rg_bore_drink = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(this);
        this.pump_availability_layout = (LinearLayout) view.findViewById(R.id.pump_availability_layout);
        RadioGroup radioGroup4 = (RadioGroup) view.findViewById(R.id.rg_pump_availability);
        this.rg_pump_availability = radioGroup4;
        radioGroup4.setOnCheckedChangeListener(this);
        this.pump_drink_layout = (LinearLayout) view.findViewById(R.id.pump_drink_layout);
        RadioGroup radioGroup5 = (RadioGroup) view.findViewById(R.id.rg_pump_drink);
        this.rg_pump_drink = radioGroup5;
        radioGroup5.setOnCheckedChangeListener(this);
        this.pipe_availability_layout = (LinearLayout) view.findViewById(R.id.pipe_availability_layout);
        RadioGroup radioGroup6 = (RadioGroup) view.findViewById(R.id.rg_pipe_availability);
        this.rg_pipe_availability = radioGroup6;
        radioGroup6.setOnCheckedChangeListener(this);
        this.pipe_drink_layout = (LinearLayout) view.findViewById(R.id.pipe_drink_layout);
        RadioGroup radioGroup7 = (RadioGroup) view.findViewById(R.id.rg_pipe_drink);
        this.rg_pipe_drink = radioGroup7;
        radioGroup7.setOnCheckedChangeListener(this);
        this.well_availability_layout = (LinearLayout) view.findViewById(R.id.well_availability_layout);
        RadioGroup radioGroup8 = (RadioGroup) view.findViewById(R.id.rg_well_availability);
        this.rg_well_availability = radioGroup8;
        radioGroup8.setOnCheckedChangeListener(this);
        this.well_drink_layout = (LinearLayout) view.findViewById(R.id.well_drink_layout);
        RadioGroup radioGroup9 = (RadioGroup) view.findViewById(R.id.rg_well_drink);
        this.rg_well_drink = radioGroup9;
        radioGroup9.setOnCheckedChangeListener(this);
        this.tanker_availability_layout = (LinearLayout) view.findViewById(R.id.tanker_availability_layout);
        RadioGroup radioGroup10 = (RadioGroup) view.findViewById(R.id.rg_tanker_availability);
        this.rg_tanker_availability = radioGroup10;
        radioGroup10.setOnCheckedChangeListener(this);
        this.tanker_drink_layout = (LinearLayout) view.findViewById(R.id.tanker_drink_layout);
        RadioGroup radioGroup11 = (RadioGroup) view.findViewById(R.id.rg_tanker_drink);
        this.rg_tanker_drink = radioGroup11;
        radioGroup11.setOnCheckedChangeListener(this);
        this.water_provision_layout = (LinearLayout) view.findViewById(R.id.water_provision_layout);
        RadioGroup radioGroup12 = (RadioGroup) view.findViewById(R.id.rg_water_provision);
        this.rg_water_provision = radioGroup12;
        radioGroup12.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.commentsLayout);
        this.commentsLayout = relativeLayout2;
        relativeLayout2.setVisibility(0);
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.DrinkingWater.1
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
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.DrinkingWater.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DrinkingWater.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    DrinkingWater.this.getPhotoFromCamera(iIntValue + "_134_" + Constant.DrinkingWater_Monitering_Image);
                } catch (Exception unused) {
                    DrinkingWater.this.isCapturing = false;
                    Toast.makeText(DrinkingWater.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$DrinkingWater$qWkFQlHVrJVMjZhJdMi3Be1Nq0k
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("DRINKING_WATER_PROVISION");
            }
        });
        if (collectionSelect instanceof List) {
            this.PROVISIONS_LIST = (List) collectionSelect;
        } else {
            this.PROVISIONS_LIST = new ArrayList(collectionSelect);
        }
        Iterator<ReferenceDataResponse.ReferenceData> it = this.PROVISIONS_LIST.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ReferenceDataResponse.ReferenceData next = it.next();
            if (next.getReferencevalue().equalsIgnoreCase(Constant.Select)) {
                this.PROVISIONS_LIST.remove(next);
                break;
            }
        }
        if (this.PROVISIONS_LIST.isEmpty()) {
            return;
        }
        this.rg_water_provision.setOrientation(1);
        for (int i = 0; i < this.PROVISIONS_LIST.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(this.PROVISIONS_LIST.get(i).getReferencedataid().intValue());
            radioButton.setText(this.PROVISIONS_LIST.get(i).getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$DrinkingWater$NRRNf58OJaS-acfv0IkX0HgI15I
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$onViewCreated$1$DrinkingWater(compoundButton, z);
                }
            });
            this.rg_water_provision.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$DrinkingWater(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.PROVISIONS_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.waterProvisionId = referenceData.getReferencekey().intValue();
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
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.drinking_water.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Is_Availabile)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.availabilityId = i;
                    ((RadioButton) this.rg_availability.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.editText_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.DrinkingWater_Monitering_Image)) {
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
                } else {
                    try {
                        switch (value.getKRAName()) {
                            case "Availability_Bore_Water":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i2 = Integer.parseInt((String) value.getDataValue());
                                    this.boreAvailabilityId = i2;
                                    ((RadioButton) this.rg_bore_availability.getChildAt(i2)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Is_Bore_Water_Drinkable":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i3 = Integer.parseInt((String) value.getDataValue());
                                    this.boreDrinkId = i3;
                                    ((RadioButton) this.rg_bore_drink.getChildAt(i3)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Availability_Hand_Pump":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i4 = Integer.parseInt((String) value.getDataValue());
                                    this.pumpAvailabilityId = i4;
                                    ((RadioButton) this.rg_pump_availability.getChildAt(i4)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Is_Hand_Pump_Water_Drinkable":
                                if (value.getDataValue() != null) {
                                    if (value.getDataValue().toString().equals("")) {
                                        break;
                                    } else {
                                        int i5 = Integer.parseInt((String) value.getDataValue());
                                        this.pumpDrinkId = i5;
                                        ((RadioButton) this.rg_pump_drink.getChildAt(i5)).setChecked(true);
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            case "Availability_Piped_Supply":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i6 = Integer.parseInt((String) value.getDataValue());
                                    this.pipeAvailabilityId = i6;
                                    ((RadioButton) this.rg_pipe_availability.getChildAt(i6)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Is_Piped_Supply_Water_Drinkable":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i7 = Integer.parseInt((String) value.getDataValue());
                                    this.pipeDrinkId = i7;
                                    ((RadioButton) this.rg_pipe_drink.getChildAt(i7)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Availability_Well":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i8 = Integer.parseInt((String) value.getDataValue());
                                    this.wellAvailabilityId = i8;
                                    ((RadioButton) this.rg_well_availability.getChildAt(i8)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Is_Well_Water_Drinkable":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i9 = Integer.parseInt((String) value.getDataValue());
                                    this.wellDrinkId = i9;
                                    ((RadioButton) this.rg_well_drink.getChildAt(i9)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Availability_Water_Tanker":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i10 = Integer.parseInt((String) value.getDataValue());
                                    this.tankerAvailabilityId = i10;
                                    ((RadioButton) this.rg_tanker_availability.getChildAt(i10)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Is_Tanker_Water_Drinkable":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    int i11 = Integer.parseInt((String) value.getDataValue());
                                    this.tankerDrinkId = i11;
                                    ((RadioButton) this.rg_tanker_drink.getChildAt(i11)).setChecked(true);
                                    break;
                                } else {
                                    continue;
                                }
                            case "Drinking_Water_Provision":
                                if (!value.getDataValue().toString().isEmpty()) {
                                    this.waterProvisionId = Integer.parseInt((String) value.getDataValue());
                                    if (this.PROVISIONS_LIST.isEmpty()) {
                                        break;
                                    } else {
                                        int i12 = 0;
                                        while (true) {
                                            if (i12 >= this.PROVISIONS_LIST.size()) {
                                                break;
                                            }
                                            if (this.PROVISIONS_LIST.get(i12).getReferencekey().intValue() == this.waterProvisionId) {
                                                ((RadioButton) this.rg_water_provision.getChildAt(i12)).setChecked(true);
                                                break;
                                            } else {
                                                i12++;
                                            }
                                        }
                                    }
                                } else {
                                    continue;
                                }
                                break;
                            default:
                                continue;
                        }
                    } catch (Exception e2) {
                        util.logException(new Exception("Drinking water: " + e2));
                    }
                    util.logException(new Exception("Drinking water: " + e2));
                }
            }
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.rg_availability.setEnabled(false);
            this.rg_availability.setFocusable(false);
            for (int i13 = 0; i13 < this.rg_availability.getChildCount(); i13++) {
                ((RadioButton) this.rg_availability.getChildAt(i13)).setFocusable(false);
                ((RadioButton) this.rg_availability.getChildAt(i13)).setEnabled(false);
            }
            this.uploadImage.setEnabled(false);
            this.uploadImage.setFocusable(false);
            this.uploadImage.setAlpha(0.5f);
            this.editText_comments.setEnabled(false);
            this.editText_comments.setFocusable(false);
            this.save_button.setEnabled(false);
            this.save_button.setAlpha(0.5f);
            this.rg_bore_availability.setEnabled(false);
            this.rg_bore_availability.setFocusable(false);
            for (int i14 = 0; i14 < this.rg_bore_availability.getChildCount(); i14++) {
                ((RadioButton) this.rg_bore_availability.getChildAt(i14)).setFocusable(false);
                ((RadioButton) this.rg_bore_availability.getChildAt(i14)).setEnabled(false);
            }
            this.rg_bore_drink.setEnabled(false);
            this.rg_bore_drink.setFocusable(false);
            for (int i15 = 0; i15 < this.rg_bore_drink.getChildCount(); i15++) {
                ((RadioButton) this.rg_bore_drink.getChildAt(i15)).setFocusable(false);
                ((RadioButton) this.rg_bore_drink.getChildAt(i15)).setEnabled(false);
            }
            this.rg_pump_availability.setEnabled(false);
            this.rg_pump_availability.setFocusable(false);
            for (int i16 = 0; i16 < this.rg_pump_availability.getChildCount(); i16++) {
                ((RadioButton) this.rg_pump_availability.getChildAt(i16)).setFocusable(false);
                ((RadioButton) this.rg_pump_availability.getChildAt(i16)).setEnabled(false);
            }
            this.rg_pump_drink.setEnabled(false);
            this.rg_pump_drink.setFocusable(false);
            for (int i17 = 0; i17 < this.rg_pump_drink.getChildCount(); i17++) {
                ((RadioButton) this.rg_pump_drink.getChildAt(i17)).setFocusable(false);
                ((RadioButton) this.rg_pump_drink.getChildAt(i17)).setEnabled(false);
            }
            this.rg_pipe_availability.setEnabled(false);
            this.rg_pipe_availability.setFocusable(false);
            for (int i18 = 0; i18 < this.rg_pipe_availability.getChildCount(); i18++) {
                ((RadioButton) this.rg_pipe_availability.getChildAt(i18)).setFocusable(false);
                ((RadioButton) this.rg_pipe_availability.getChildAt(i18)).setEnabled(false);
            }
            this.rg_pipe_drink.setEnabled(false);
            this.rg_pipe_drink.setFocusable(false);
            for (int i19 = 0; i19 < this.rg_pipe_drink.getChildCount(); i19++) {
                ((RadioButton) this.rg_pipe_drink.getChildAt(i19)).setFocusable(false);
                ((RadioButton) this.rg_pipe_drink.getChildAt(i19)).setEnabled(false);
            }
            this.rg_well_availability.setEnabled(false);
            this.rg_well_availability.setFocusable(false);
            for (int i20 = 0; i20 < this.rg_well_availability.getChildCount(); i20++) {
                ((RadioButton) this.rg_well_availability.getChildAt(i20)).setFocusable(false);
                ((RadioButton) this.rg_well_availability.getChildAt(i20)).setEnabled(false);
            }
            this.rg_well_drink.setEnabled(false);
            this.rg_well_drink.setFocusable(false);
            for (int i21 = 0; i21 < this.rg_well_drink.getChildCount(); i21++) {
                ((RadioButton) this.rg_well_drink.getChildAt(i21)).setFocusable(false);
                ((RadioButton) this.rg_well_drink.getChildAt(i21)).setEnabled(false);
            }
            this.rg_tanker_availability.setEnabled(false);
            this.rg_tanker_availability.setFocusable(false);
            for (int i22 = 0; i22 < this.rg_tanker_availability.getChildCount(); i22++) {
                ((RadioButton) this.rg_tanker_availability.getChildAt(i22)).setFocusable(false);
                ((RadioButton) this.rg_tanker_availability.getChildAt(i22)).setEnabled(false);
            }
            this.rg_tanker_drink.setEnabled(false);
            this.rg_tanker_drink.setFocusable(false);
            for (int i23 = 0; i23 < this.rg_tanker_drink.getChildCount(); i23++) {
                ((RadioButton) this.rg_tanker_drink.getChildAt(i23)).setFocusable(false);
                ((RadioButton) this.rg_tanker_drink.getChildAt(i23)).setEnabled(false);
            }
            this.rg_water_provision.setEnabled(false);
            this.rg_water_provision.setFocusable(false);
            for (int i24 = 0; i24 < this.rg_water_provision.getChildCount(); i24++) {
                ((RadioButton) this.rg_water_provision.getChildAt(i24)).setFocusable(false);
                ((RadioButton) this.rg_water_provision.getChildAt(i24)).setEnabled(false);
            }
        }
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
            DialogCustom.showError(getActivityContext(), "Please select the drinking water availability.");
            this.rg_availability.setFocusableInTouchMode(true);
            this.rg_availability.setFocusable(true);
            return;
        }
        if (i == 0) {
            int i2 = this.boreAvailabilityId;
            if (i2 == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Availability of Bore / Machine.");
                return;
            }
            int i3 = this.pumpAvailabilityId;
            if (i3 == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Availability of Hand Pump.");
                return;
            }
            int i4 = this.pipeAvailabilityId;
            if (i4 == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Availability of Piped Supply.");
                return;
            }
            int i5 = this.wellAvailabilityId;
            if (i5 == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Availability of Well.");
                return;
            }
            int i6 = this.tankerAvailabilityId;
            if (i6 == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Availability of Water Tanker.");
                return;
            }
            if (this.waterProvisionId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Provision of Drinking Water.");
                return;
            }
            if (i2 == 0 && this.boreDrinkId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select whether Bore / Machine water is good for drinking.");
                return;
            }
            if (i3 == 0 && this.pumpDrinkId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select whether Hand Pump water is good for drinking.");
                return;
            }
            if (i4 == 0 && this.pipeDrinkId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select whether Piped Supply water is good for drinking.");
                return;
            }
            if (i5 == 0 && this.wellDrinkId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select whether Well water is good for drinking.");
                return;
            }
            if (i6 == 0 && this.tankerDrinkId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select whether Tanker water is good for drinking.");
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
            if (getActivity() != null) {
                this.fm.popBackStack();
            }
        } else if (this.imageBase64.length() > 0 || this.editText_comments.getText().toString().length() > 0 || this.availabilityId > -1) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_availability_no /* 2131231486 */:
                this.availabilityId = 1;
                this.bore_availability_layout.setVisibility(8);
                this.bore_drink_layout.setVisibility(8);
                this.pump_availability_layout.setVisibility(8);
                this.pump_drink_layout.setVisibility(8);
                this.pipe_availability_layout.setVisibility(8);
                this.pipe_drink_layout.setVisibility(8);
                this.well_availability_layout.setVisibility(8);
                this.well_drink_layout.setVisibility(8);
                this.tanker_availability_layout.setVisibility(8);
                this.tanker_drink_layout.setVisibility(8);
                this.water_provision_layout.setVisibility(8);
                break;
            case R.id.rb_availability_yes /* 2131231487 */:
                this.availabilityId = 0;
                this.bore_availability_layout.setVisibility(0);
                this.pump_availability_layout.setVisibility(0);
                this.pipe_availability_layout.setVisibility(0);
                this.well_availability_layout.setVisibility(0);
                this.tanker_availability_layout.setVisibility(0);
                this.water_provision_layout.setVisibility(0);
                break;
            default:
                switch (i) {
                    case R.id.rb_bore_availability_no /* 2131231491 */:
                        this.boreAvailabilityId = 1;
                        this.bore_drink_layout.setVisibility(8);
                        break;
                    case R.id.rb_bore_availability_yes /* 2131231492 */:
                        this.boreAvailabilityId = 0;
                        this.bore_drink_layout.setVisibility(0);
                        break;
                    case R.id.rb_bore_drink_no /* 2131231493 */:
                        this.boreDrinkId = 1;
                        break;
                    case R.id.rb_bore_drink_yes /* 2131231494 */:
                        this.boreDrinkId = 0;
                        break;
                    default:
                        switch (i) {
                            case R.id.rb_pipe_availability_no /* 2131231557 */:
                                this.pipeAvailabilityId = 1;
                                this.pipe_drink_layout.setVisibility(8);
                                break;
                            case R.id.rb_pipe_availability_yes /* 2131231558 */:
                                this.pipeAvailabilityId = 0;
                                this.pipe_drink_layout.setVisibility(0);
                                break;
                            case R.id.rb_pipe_drink_no /* 2131231559 */:
                                this.pipeDrinkId = 1;
                                break;
                            case R.id.rb_pipe_drink_yes /* 2131231560 */:
                                this.pipeDrinkId = 0;
                                break;
                            default:
                                switch (i) {
                                    case R.id.rb_pump_availability_no /* 2131231564 */:
                                        this.pumpAvailabilityId = 1;
                                        this.pump_drink_layout.setVisibility(8);
                                        break;
                                    case R.id.rb_pump_availability_yes /* 2131231565 */:
                                        this.pumpAvailabilityId = 0;
                                        this.pump_drink_layout.setVisibility(0);
                                        break;
                                    case R.id.rb_pump_drink_no /* 2131231566 */:
                                        this.pumpDrinkId = 1;
                                        break;
                                    case R.id.rb_pump_drink_yes /* 2131231567 */:
                                        this.pumpDrinkId = 0;
                                        break;
                                    default:
                                        switch (i) {
                                            case R.id.rb_tanker_availability_no /* 2131231590 */:
                                                this.tankerAvailabilityId = 1;
                                                this.tanker_drink_layout.setVisibility(8);
                                                break;
                                            case R.id.rb_tanker_availability_yes /* 2131231591 */:
                                                this.tankerAvailabilityId = 0;
                                                this.tanker_drink_layout.setVisibility(0);
                                                break;
                                            case R.id.rb_tanker_drink_no /* 2131231592 */:
                                                this.tankerDrinkId = 1;
                                                break;
                                            case R.id.rb_tanker_drink_yes /* 2131231593 */:
                                                this.tankerDrinkId = 0;
                                                break;
                                            default:
                                                switch (i) {
                                                    case R.id.rb_well_availability_no /* 2131231603 */:
                                                        this.wellAvailabilityId = 1;
                                                        this.well_drink_layout.setVisibility(8);
                                                        break;
                                                    case R.id.rb_well_availability_yes /* 2131231604 */:
                                                        this.wellAvailabilityId = 0;
                                                        this.well_drink_layout.setVisibility(0);
                                                        break;
                                                    case R.id.rb_well_drink_no /* 2131231605 */:
                                                        this.wellDrinkId = 1;
                                                        break;
                                                    case R.id.rb_well_drink_yes /* 2131231606 */:
                                                        this.wellDrinkId = 0;
                                                        break;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.DrinkingWater.3
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.drinking_water.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Is_Availabile)) {
                        if (DrinkingWater.this.availabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.availabilityId));
                        }
                    } else if (value.getKRAName().equals("Availability_Bore_Water")) {
                        if (DrinkingWater.this.boreAvailabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.boreAvailabilityId));
                        }
                    } else if (value.getKRAName().equals("Is_Bore_Water_Drinkable")) {
                        if (DrinkingWater.this.boreDrinkId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.boreDrinkId));
                        }
                    } else if (value.getKRAName().equals("Availability_Hand_Pump")) {
                        if (DrinkingWater.this.pumpAvailabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.pumpAvailabilityId));
                        }
                    } else if (value.getKRAName().equals("Is_Hand_Pump_Water_Drinkable")) {
                        if (DrinkingWater.this.pumpDrinkId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.pumpDrinkId));
                        }
                    } else if (value.getKRAName().equals("Availability_Piped_Supply")) {
                        if (DrinkingWater.this.pipeAvailabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.pipeAvailabilityId));
                        }
                    } else if (value.getKRAName().equals("Is_Piped_Supply_Water_Drinkable")) {
                        if (DrinkingWater.this.pipeDrinkId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.pipeDrinkId));
                        }
                    } else if (value.getKRAName().equals("Availability_Well")) {
                        if (DrinkingWater.this.wellAvailabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.wellAvailabilityId));
                        }
                    } else if (value.getKRAName().equals("Is_Well_Water_Drinkable")) {
                        if (DrinkingWater.this.wellDrinkId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.wellDrinkId));
                        }
                    } else if (value.getKRAName().equals("Availability_Water_Tanker")) {
                        if (DrinkingWater.this.tankerAvailabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.tankerAvailabilityId));
                        }
                    } else if (value.getKRAName().equals("Is_Tanker_Water_Drinkable")) {
                        if (DrinkingWater.this.tankerDrinkId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.tankerDrinkId));
                        }
                    } else if (value.getKRAName().equals("Drinking_Water_Provision")) {
                        if (DrinkingWater.this.waterProvisionId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(DrinkingWater.this.waterProvisionId));
                        }
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(DrinkingWater.this.editText_comments.getText().toString());
                    } else if (value.getKRAName().equals(Constant.DrinkingWater_Monitering_Image)) {
                        value.setDataValue(DrinkingWater.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (DrinkingWater.this.location != null) {
                            value.setDataValue(Double.valueOf(DrinkingWater.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (DrinkingWater.this.location != null) {
                            value.setDataValue(Double.valueOf(DrinkingWater.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date) && !DrinkingWater.this.imageBase64.equals("")) {
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
                        CommonActions.getDbHandler(DrinkingWater.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isDrinkingWaterDATA = true;
            }
        }).start();
        if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }
}
