package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
public class Washroom extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    List<ReferenceDataResponse.ReferenceData> REASON_LIST;
    RelativeLayout availabilityLayout;
    Button cancel_button;
    RelativeLayout commentsLayout;
    EditText editText_comments;
    EditText et_toilets_available;
    EditText et_toilets_functional;
    EditText et_toilets_non_functional;
    boolean isPreviewVisible;
    View mView;
    ImageView previewImage;
    RadioGroup rg_availability;
    RadioGroup rg_cleanlines;
    RadioGroup rg_handwash;
    RelativeLayout rv_cleanliness;
    RelativeLayout rv_hand_wash_facility_available;
    RelativeLayout rv_toilets_available;
    RelativeLayout rv_toilets_functional;
    RelativeLayout rv_toilets_non_functional;
    Button save_button;
    String title;
    TextView tv_census_widget;
    ImageView uploadImage;
    Spinner washroom_spinner;
    LinearLayout washroom_spinner_layout;
    int availabilityId = -1;
    int cleanlinessId = -1;
    int handwashId = -1;
    String imageBase64 = "";
    int reasonId = 0;
    boolean isCapturing = false;

    public Washroom() {
    }

    public Washroom(String s) {
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
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.rv_toilets_available);
        this.rv_toilets_available = relativeLayout2;
        relativeLayout2.setVisibility(8);
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.rv_toilets_functional);
        this.rv_toilets_functional = relativeLayout3;
        relativeLayout3.setVisibility(8);
        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.rv_toilets_non_functional);
        this.rv_toilets_non_functional = relativeLayout4;
        relativeLayout4.setVisibility(8);
        this.washroom_spinner_layout = (LinearLayout) view.findViewById(R.id.washroom_spinner_layout);
        this.washroom_spinner = (Spinner) view.findViewById(R.id.washroom_spinner);
        RelativeLayout relativeLayout5 = (RelativeLayout) view.findViewById(R.id.rv_cleanliness);
        this.rv_cleanliness = relativeLayout5;
        relativeLayout5.setVisibility(8);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_cleanlines);
        this.rg_cleanlines = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout6 = (RelativeLayout) view.findViewById(R.id.rv_hand_wash_facility_available);
        this.rv_hand_wash_facility_available = relativeLayout6;
        relativeLayout6.setVisibility(8);
        RadioGroup radioGroup3 = (RadioGroup) view.findViewById(R.id.rg_handwash);
        this.rg_handwash = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout7 = (RelativeLayout) view.findViewById(R.id.commentsLayout);
        this.commentsLayout = relativeLayout7;
        relativeLayout7.setVisibility(8);
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.1
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
        EditText editText2 = (EditText) view.findViewById(R.id.et_toilets_available);
        this.et_toilets_available = editText2;
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    Washroom.this.et_toilets_functional.setEnabled(true);
                    Washroom.this.et_toilets_functional.setFocusableInTouchMode(true);
                    Washroom.this.et_toilets_functional.setFocusable(true);
                }
                Washroom.this.updateWashrooms();
            }
        });
        EditText editText3 = (EditText) view.findViewById(R.id.et_toilets_functional);
        this.et_toilets_functional = editText3;
        editText3.setEnabled(false);
        this.et_toilets_functional.setFocusable(false);
        this.et_toilets_functional.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Washroom.this.updateWashrooms();
            }
        });
        EditText editText4 = (EditText) view.findViewById(R.id.et_toilets_non_functional);
        this.et_toilets_non_functional = editText4;
        editText4.setEnabled(false);
        this.et_toilets_non_functional.setFocusable(false);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$Washroom$KlcH-3e3WIzXtJHemlXqT_o-6kI
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("WASHROOM_NON_FUNCTIONAL_REASON");
            }
        });
        if (collectionSelect instanceof List) {
            this.REASON_LIST = (List) collectionSelect;
        } else {
            this.REASON_LIST = new ArrayList(collectionSelect);
        }
        if (!this.REASON_LIST.isEmpty()) {
            this.washroom_spinner.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.REASON_LIST));
            this.washroom_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.4
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view2, int position, long id2) {
                    Washroom.this.reasonId = position;
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                    CommonActions.hideSoftKeyboard(Washroom.this.getActivityContext(), Washroom.this.getActivity().getCurrentFocus());
                }
            });
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(8);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Washroom.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    Washroom.this.getPhotoFromCamera(iIntValue + "_161_" + Constant.Washroom_Monitering_Image);
                } catch (Exception unused) {
                    Washroom.this.isCapturing = false;
                    Toast.makeText(Washroom.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWashrooms() {
        if (this.et_toilets_available.getText().length() <= 0 || this.et_toilets_functional.getText().length() <= 0) {
            return;
        }
        if (Integer.parseInt(this.et_toilets_available.getText().toString()) - Integer.parseInt(this.et_toilets_functional.getText().toString()) > 0) {
            this.washroom_spinner_layout.setVisibility(0);
        } else {
            this.washroom_spinner_layout.setVisibility(8);
        }
        if (Integer.parseInt(this.et_toilets_functional.getText().toString()) <= Integer.parseInt(this.et_toilets_available.getText().toString())) {
            this.et_toilets_non_functional.setText(String.valueOf(Integer.parseInt(this.et_toilets_available.getText().toString()) - Integer.parseInt(this.et_toilets_functional.getText().toString())));
            return;
        }
        DialogCustom.showError(getActivityContext(), "Functional washrooms must be less than or equal total washrooms.");
        this.et_toilets_functional.setText("");
        this.et_toilets_non_functional.setText("");
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.washroom.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Washroom_Facility_Availabile)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.availabilityId = i;
                    ((RadioButton) this.rg_availability.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Total_Available)) {
                    this.et_toilets_available.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Fucntional)) {
                    this.et_toilets_functional.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Non_Fucntional)) {
                    this.et_toilets_non_functional.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Cleanliness)) {
                    int i2 = Integer.parseInt((String) value.getDataValue());
                    this.cleanlinessId = i2;
                    ((RadioButton) this.rg_cleanlines.getChildAt(i2)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.HandWash_Facility_Available)) {
                    int i3 = Integer.parseInt((String) value.getDataValue());
                    this.handwashId = i3;
                    ((RadioButton) this.rg_handwash.getChildAt(i3)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.editText_comments.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Washroom_Monitering_Image)) {
                    this.imageBase64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBase64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
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
                } else if (value.getKRAName().equals("Washroom_Non_Functional_Reason")) {
                    this.washroom_spinner.setSelection(Integer.parseInt(value.getDataValue().toString()));
                }
            }
        }
        if (this.availabilityId == 0) {
            this.rv_toilets_available.setVisibility(0);
            this.rv_toilets_functional.setVisibility(0);
            this.rv_toilets_non_functional.setVisibility(0);
            this.rv_cleanliness.setVisibility(0);
            this.rv_hand_wash_facility_available.setVisibility(0);
            this.commentsLayout.setVisibility(0);
            if (this.isPreviewVisible) {
                this.previewImage.setVisibility(0);
            } else {
                this.previewImage.setVisibility(8);
            }
            this.uploadImage.setVisibility(0);
        } else {
            this.rv_toilets_available.setVisibility(8);
            this.rv_toilets_functional.setVisibility(8);
            this.rv_toilets_non_functional.setVisibility(8);
            this.rv_cleanliness.setVisibility(8);
            this.rv_hand_wash_facility_available.setVisibility(8);
            this.commentsLayout.setVisibility(0);
            this.previewImage.setVisibility(8);
            this.uploadImage.setVisibility(8);
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
        this.et_toilets_available.setEnabled(false);
        this.et_toilets_available.setFocusable(false);
        this.et_toilets_functional.setEnabled(false);
        this.et_toilets_functional.setFocusable(false);
        this.et_toilets_non_functional.setEnabled(false);
        this.et_toilets_non_functional.setFocusable(false);
        this.washroom_spinner.setEnabled(false);
        this.washroom_spinner.setFocusable(false);
        this.rg_cleanlines.setEnabled(false);
        this.rg_cleanlines.setFocusable(false);
        for (int i5 = 0; i5 < this.rg_cleanlines.getChildCount(); i5++) {
            ((RadioButton) this.rg_cleanlines.getChildAt(i5)).setFocusable(false);
            ((RadioButton) this.rg_cleanlines.getChildAt(i5)).setEnabled(false);
        }
        this.rg_handwash.setEnabled(false);
        this.rg_handwash.setFocusable(false);
        for (int i6 = 0; i6 < this.rg_handwash.getChildCount(); i6++) {
            ((RadioButton) this.rg_handwash.getChildAt(i6)).setFocusable(false);
            ((RadioButton) this.rg_handwash.getChildAt(i6)).setEnabled(false);
        }
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
                this.rv_toilets_available.setVisibility(8);
                this.rv_toilets_functional.setVisibility(8);
                this.rv_toilets_non_functional.setVisibility(8);
                this.rv_cleanliness.setVisibility(8);
                this.rv_hand_wash_facility_available.setVisibility(8);
                this.commentsLayout.setVisibility(0);
                this.previewImage.setVisibility(8);
                this.uploadImage.setVisibility(8);
                this.cleanlinessId = -1;
                this.handwashId = -1;
                this.imageBase64 = "";
                this.isPreviewVisible = false;
                this.et_toilets_available.setText("");
                this.et_toilets_functional.setText("");
                this.et_toilets_non_functional.setText("");
                break;
            case R.id.rb_availability_yes /* 2131231487 */:
                this.availabilityId = 0;
                this.rv_toilets_available.setVisibility(0);
                this.rv_toilets_functional.setVisibility(0);
                this.rv_toilets_non_functional.setVisibility(0);
                this.rv_cleanliness.setVisibility(0);
                this.rv_hand_wash_facility_available.setVisibility(0);
                this.commentsLayout.setVisibility(0);
                if (this.isPreviewVisible) {
                    this.previewImage.setVisibility(0);
                } else {
                    this.previewImage.setVisibility(8);
                }
                this.uploadImage.setVisibility(0);
                break;
            case R.id.rb_cleanliness_average /* 2131231501 */:
                this.cleanlinessId = 1;
                break;
            case R.id.rb_cleanliness_good /* 2131231502 */:
                this.cleanlinessId = 2;
                break;
            case R.id.rb_cleanliness_poor /* 2131231503 */:
                this.cleanlinessId = 0;
                break;
            case R.id.rb_hand_wash_facility_available_no /* 2131231526 */:
                this.handwashId = 1;
                break;
            case R.id.rb_hand_wash_facility_available_yes /* 2131231527 */:
                this.handwashId = 0;
                break;
        }
    }

    private void checkValidation() {
        int i = this.availabilityId;
        if (i != 0) {
            if (i == 1) {
                saveFieldsData();
                return;
            }
            DialogCustom.showError(getActivityContext(), "Please select the washroom availability.");
            this.rg_availability.setFocusableInTouchMode(true);
            this.rg_availability.setFocusable(true);
            return;
        }
        if (this.cleanlinessId > -1 && this.handwashId > -1 && this.et_toilets_available.getText().toString().length() > 0 && this.et_toilets_functional.getText().toString().length() > 0 && this.et_toilets_non_functional.getText().toString().length() > 0) {
            if (this.imageBase64.length() > 0) {
                if (Integer.parseInt(this.et_toilets_available.getText().toString()) >= Integer.parseInt(this.et_toilets_functional.getText().toString()) && Integer.parseInt(this.et_toilets_available.getText().toString()) >= Integer.parseInt(this.et_toilets_non_functional.getText().toString()) && Integer.parseInt(this.et_toilets_available.getText().toString()) >= Integer.parseInt(this.et_toilets_non_functional.getText().toString()) + Integer.parseInt(this.et_toilets_functional.getText().toString())) {
                    if (Integer.parseInt(this.et_toilets_non_functional.getText().toString()) > 0) {
                        if (this.reasonId == 0) {
                            DialogCustom.showError(getActivityContext(), "Select reason for Non functional washrooms.");
                            return;
                        } else {
                            saveFieldsData();
                            return;
                        }
                    }
                    saveFieldsData();
                    return;
                }
                DialogCustom.showError(getActivityContext(), "Functional and non functional washrooms must be less than or equal total washrooms.");
                this.rv_toilets_available.setFocusableInTouchMode(true);
                this.rv_toilets_available.setFocusable(true);
                return;
            }
            DialogCustom.showError(getActivityContext(), "Please upload image.");
            this.uploadImage.setFocusableInTouchMode(true);
            this.uploadImage.setFocusable(true);
            return;
        }
        if (this.et_toilets_available.getText().toString().equals("")) {
            this.et_toilets_available.setError("Please fill the field.");
            this.rv_toilets_available.setFocusableInTouchMode(true);
            this.rv_toilets_available.setFocusable(true);
            DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
            return;
        }
        if (this.et_toilets_functional.getText().toString().equals("")) {
            this.et_toilets_functional.setError("Please fill the field.");
            this.rv_toilets_available.setFocusableInTouchMode(true);
            this.rv_toilets_available.setFocusable(true);
            DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
            return;
        }
        if (this.cleanlinessId < 0) {
            DialogCustom.showError(getActivityContext(), "Please select cleanliness.");
            this.rg_cleanlines.setFocusableInTouchMode(true);
            this.rg_cleanlines.setFocusable(true);
        } else {
            if (this.handwashId < 0) {
                this.rg_handwash.setFocusableInTouchMode(true);
                this.rg_handwash.setFocusable(true);
                DialogCustom.showError(getActivityContext(), "Please select hand wash facility.");
                return;
            }
            DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
        }
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
            return;
        }
        if (this.availabilityId > -1 || this.cleanlinessId > -1 || this.handwashId > -1 || this.imageBase64.length() > 0 || this.editText_comments.getText().toString().length() > 0 || this.et_toilets_available.getText().toString().length() > 0 || this.et_toilets_functional.getText().toString().length() > 0 || this.et_toilets_non_functional.getText().toString().length() > 0) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else {
            this.fm.popBackStack();
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.Washroom.6
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.washroom.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Washroom_Facility_Availabile)) {
                        if (Washroom.this.availabilityId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Washroom.this.availabilityId));
                        }
                    } else if (value.getKRAName().equals(Constant.Total_Available)) {
                        value.setDataValue(Washroom.this.et_toilets_available.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Fucntional)) {
                        value.setDataValue(Washroom.this.et_toilets_functional.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Non_Fucntional)) {
                        value.setDataValue(Washroom.this.et_toilets_non_functional.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Cleanliness)) {
                        if (Washroom.this.cleanlinessId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Washroom.this.cleanlinessId));
                        }
                    } else if (value.getKRAName().equals(Constant.HandWash_Facility_Available)) {
                        if (Washroom.this.handwashId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(Washroom.this.handwashId));
                        }
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        value.setDataValue(Washroom.this.editText_comments.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Washroom_Monitering_Image)) {
                        value.setDataValue(Washroom.this.imageBase64);
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (Washroom.this.location != null) {
                            value.setDataValue(Double.valueOf(Washroom.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (Washroom.this.location != null) {
                            value.setDataValue(Double.valueOf(Washroom.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date)) {
                        if (!Washroom.this.imageBase64.equals("")) {
                            value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                        }
                    } else if (value.getKRAName().equals("Washroom_Non_Functional_Reason")) {
                        if (Washroom.this.reasonId != 0) {
                            value.setDataValue(String.valueOf(Washroom.this.REASON_LIST.get(Washroom.this.reasonId).getReferencekey()));
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
                        CommonActions.getDbHandler(Washroom.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isWashroomDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
