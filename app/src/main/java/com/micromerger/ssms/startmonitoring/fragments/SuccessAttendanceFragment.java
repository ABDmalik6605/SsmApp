package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class SuccessAttendanceFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, BaseFragment.CameraActivityCallBack {
    List<ReferenceDataResponse.ReferenceData> VACCINE_LIST;
    Button btn_cancel;
    Button btn_save;
    String employeeId;
    int firstDoseId;
    LinearLayout first_dose_layout;
    String imageBase64;
    LinearLayout image_layout;
    boolean isCapturing;
    ImageView previewImage;
    RadioButton radioButton;
    boolean resumed;
    RadioGroup rg_first_dose;
    RadioGroup rg_second_dose;
    int secondDoseId;
    LinearLayout second_dose_layout;
    LinearLayout spinner_layout;
    String successMessage;
    TextView success_text;
    boolean thumbNotMatch;
    ImageView uploadImage;
    int vaccineId;
    Spinner vaccine_spinner;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public SuccessAttendanceFragment() {
        this.thumbNotMatch = false;
        this.vaccineId = 0;
        this.firstDoseId = -1;
        this.secondDoseId = -1;
        this.resumed = true;
        this.isCapturing = false;
        this.imageBase64 = "";
    }

    public SuccessAttendanceFragment(String employeeId, String successMessage) {
        this.thumbNotMatch = false;
        this.vaccineId = 0;
        this.firstDoseId = -1;
        this.secondDoseId = -1;
        this.resumed = true;
        this.isCapturing = false;
        this.imageBase64 = "";
        this.employeeId = employeeId;
        this.successMessage = successMessage;
    }

    public SuccessAttendanceFragment(String employeeId, String successMessage, Boolean thumbNotMatch) {
        this.thumbNotMatch = false;
        this.vaccineId = 0;
        this.firstDoseId = -1;
        this.secondDoseId = -1;
        this.resumed = true;
        this.isCapturing = false;
        this.imageBase64 = "";
        this.employeeId = employeeId;
        this.successMessage = successMessage;
        this.thumbNotMatch = thumbNotMatch.booleanValue();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_success_attendance, container, false);
        this.spinner_layout = (LinearLayout) viewInflate.findViewById(R.id.spinner_layout);
        this.first_dose_layout = (LinearLayout) viewInflate.findViewById(R.id.first_dose_layout);
        this.second_dose_layout = (LinearLayout) viewInflate.findViewById(R.id.second_dose_layout);
        this.image_layout = (LinearLayout) viewInflate.findViewById(R.id.image_layout);
        this.success_text = (TextView) viewInflate.findViewById(R.id.success_text);
        this.vaccine_spinner = (Spinner) viewInflate.findViewById(R.id.vaccine_spinner);
        this.rg_first_dose = (RadioGroup) viewInflate.findViewById(R.id.rg_first_dose);
        this.radioButton = (RadioButton) viewInflate.findViewById(R.id.rb_first_yes);
        this.rg_second_dose = (RadioGroup) viewInflate.findViewById(R.id.rg_second_dose);
        this.previewImage = (ImageView) viewInflate.findViewById(R.id.previewImage);
        this.uploadImage = (ImageView) viewInflate.findViewById(R.id.uploadImage);
        this.btn_save = (Button) viewInflate.findViewById(R.id.btn_save);
        this.btn_cancel = (Button) viewInflate.findViewById(R.id.btn_cancel);
        setCameraActivityCallBack(this);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().equals("Covid19_Vaccine_Type_" + this.employeeId) && value.getActiveind().booleanValue()) {
                this.spinner_layout.setVisibility(0);
            }
        }
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.success_text.setText(this.successMessage);
        this.rg_first_dose.setOnCheckedChangeListener(this);
        this.rg_second_dose.setOnCheckedChangeListener(this);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SuccessAttendanceFragment$C7MahUR0UnfD7dIB-4u4HsHmFGY
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("COVID19_VACCINATION_TYPE");
            }
        });
        if (collectionSelect instanceof List) {
            this.VACCINE_LIST = (List) collectionSelect;
        } else {
            this.VACCINE_LIST = new ArrayList(collectionSelect);
        }
        if (!this.VACCINE_LIST.isEmpty()) {
            this.vaccine_spinner.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.VACCINE_LIST));
            this.vaccine_spinner.setOnItemSelectedListener(new AnonymousClass1());
        }
        this.btn_save.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SuccessAttendanceFragment$_EW9o-2WzegyFbtxEPRsV2vT2SQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$1$SuccessAttendanceFragment(view2);
            }
        });
        this.btn_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SuccessAttendanceFragment$U0YOql42-Uct0n9zsrNqUnyqVH8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$2$SuccessAttendanceFragment(view2);
            }
        });
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.SuccessAttendanceFragment$1, reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemSelectedListener {
        AnonymousClass1() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id2) {
            try {
                SuccessAttendanceFragment successAttendanceFragment = SuccessAttendanceFragment.this;
                successAttendanceFragment.vaccineId = successAttendanceFragment.VACCINE_LIST.get(position).getReferencekey().intValue();
                if (SuccessAttendanceFragment.this.vaccineId == 11) {
                    SuccessAttendanceFragment.this.first_dose_layout.setVisibility(8);
                    SuccessAttendanceFragment.this.second_dose_layout.setVisibility(8);
                    SuccessAttendanceFragment.this.image_layout.setVisibility(0);
                    SuccessAttendanceFragment.this.firstDoseId = -1;
                    SuccessAttendanceFragment.this.secondDoseId = -1;
                    SuccessAttendanceFragment.this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SuccessAttendanceFragment$1$O4IBkPi4rvLygsol5XX-aZexpQ0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f$0.lambda$onItemSelected$0$SuccessAttendanceFragment$1(view2);
                        }
                    });
                    return;
                }
                if (SuccessAttendanceFragment.this.vaccineId != 0) {
                    SuccessAttendanceFragment.this.image_layout.setVisibility(8);
                    SuccessAttendanceFragment.this.previewImage.setVisibility(8);
                    SuccessAttendanceFragment.this.imageBase64 = "";
                    SuccessAttendanceFragment.this.previewImage.setImageBitmap(null);
                    SuccessAttendanceFragment.this.firstDoseId = 0;
                    SuccessAttendanceFragment.this.radioButton.setChecked(true);
                    Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
                    while (it.hasNext()) {
                        KRAData value = it.next().getValue();
                        if (value.getKRAName().equals("1st_Dose_of_Covid19_Vaccine_" + SuccessAttendanceFragment.this.employeeId) && value.getActiveind().booleanValue()) {
                            SuccessAttendanceFragment.this.first_dose_layout.setVisibility(0);
                        }
                        if (value.getKRAName().equals("2nd_Dose_of_Covid19_Vaccine_" + SuccessAttendanceFragment.this.employeeId) && value.getActiveind().booleanValue()) {
                            SuccessAttendanceFragment.this.second_dose_layout.setVisibility(0);
                            if (value.getDataValue() != null && !value.getDataValue().toString().isEmpty()) {
                                SuccessAttendanceFragment.this.secondDoseId = Integer.parseInt((String) value.getDataValue());
                                ((RadioButton) SuccessAttendanceFragment.this.rg_second_dose.getChildAt(SuccessAttendanceFragment.this.secondDoseId)).setChecked(true);
                            }
                        }
                    }
                    if (SuccessAttendanceFragment.this.vaccineId == 2 || SuccessAttendanceFragment.this.vaccineId == 3) {
                        SuccessAttendanceFragment.this.second_dose_layout.setVisibility(8);
                        SuccessAttendanceFragment.this.secondDoseId = -1;
                        return;
                    }
                    return;
                }
                SuccessAttendanceFragment.this.first_dose_layout.setVisibility(8);
                SuccessAttendanceFragment.this.second_dose_layout.setVisibility(8);
                SuccessAttendanceFragment.this.image_layout.setVisibility(8);
                SuccessAttendanceFragment.this.previewImage.setVisibility(8);
                SuccessAttendanceFragment.this.firstDoseId = -1;
                SuccessAttendanceFragment.this.secondDoseId = -1;
                SuccessAttendanceFragment.this.imageBase64 = "";
                SuccessAttendanceFragment.this.previewImage.setImageBitmap(null);
            } catch (Exception e) {
                util.logException(new Exception("COVID Vaccine Id Index Error: " + e));
            }
        }

        public /* synthetic */ void lambda$onItemSelected$0$SuccessAttendanceFragment$1(View view) {
            SuccessAttendanceFragment.this.isCapturing = true;
            try {
                int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                String str = "Covid19_Medical_Reason_Image_" + SuccessAttendanceFragment.this.employeeId;
                SuccessAttendanceFragment.this.getPhotoFromCamera(iIntValue + "_328_" + str);
            } catch (Exception unused) {
                SuccessAttendanceFragment.this.isCapturing = false;
                Toast.makeText(SuccessAttendanceFragment.this.getContext(), "Error getting Photo From Camera", 0).show();
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> parent) {
            CommonActions.hideSoftKeyboard(SuccessAttendanceFragment.this.getActivityContext(), SuccessAttendanceFragment.this.getActivity().getCurrentFocus());
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$SuccessAttendanceFragment(View view) {
        validateData();
    }

    public /* synthetic */ void lambda$onViewCreated$2$SuccessAttendanceFragment(View view) {
        if (this.thumbNotMatch) {
            this.fm.popBackStack();
            this.fm.popBackStack();
        } else {
            this.fm.popBackStack();
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.resumed) {
            try {
                Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.attendance_fields.entrySet().iterator();
                while (it.hasNext()) {
                    KRAData value = it.next().getValue();
                    if (value.getKRAName().equals("Covid19_Vaccine_Type_" + this.employeeId) && value.getDataValue() != null && !value.getDataValue().toString().isEmpty()) {
                        this.vaccineId = Integer.parseInt((String) value.getDataValue());
                        Iterator<ReferenceDataResponse.ReferenceData> it2 = this.VACCINE_LIST.iterator();
                        int i = 0;
                        while (it2.hasNext()) {
                            if (this.vaccineId == it2.next().getReferencekey().intValue()) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        this.vaccine_spinner.setSelection(i);
                    }
                    if (value.getKRAName().equals("Covid19_Medical_Reason_Image_" + this.employeeId)) {
                        if (value.getDataValue() == null || ((String) value.getDataValue()).isEmpty()) {
                            this.previewImage.setVisibility(8);
                        } else {
                            this.previewImage.setVisibility(0);
                            try {
                                this.imageBase64 = String.valueOf(value.getDataValue());
                                this.previewImage.setImageURI(Uri.fromFile(new File(this.imageBase64)));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
                if (CommonObjects.monitoring != null && CommonObjects.monitoring.getMonitoring() != null && CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                    this.vaccine_spinner.setEnabled(false);
                    this.vaccine_spinner.setFocusable(false);
                    this.rg_first_dose.setEnabled(false);
                    this.rg_first_dose.setFocusable(false);
                    for (int i2 = 0; i2 < this.rg_first_dose.getChildCount(); i2++) {
                        ((RadioButton) this.rg_first_dose.getChildAt(i2)).setFocusable(false);
                        ((RadioButton) this.rg_first_dose.getChildAt(i2)).setEnabled(false);
                    }
                    this.rg_second_dose.setEnabled(false);
                    this.rg_second_dose.setFocusable(false);
                    for (int i3 = 0; i3 < this.rg_second_dose.getChildCount(); i3++) {
                        ((RadioButton) this.rg_second_dose.getChildAt(i3)).setFocusable(false);
                        ((RadioButton) this.rg_second_dose.getChildAt(i3)).setEnabled(false);
                    }
                    this.btn_save.setEnabled(false);
                    this.btn_save.setAlpha(0.5f);
                }
                this.resumed = false;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void validateData() {
        int i = this.vaccineId;
        if (i > 0 && this.firstDoseId != 0 && i != 11) {
            DialogCustom.showError(getActivityContext(), "Please select (Yes) 1st dose of vaccine");
        } else if ((this.firstDoseId != -1 || this.secondDoseId != -1) && i == 0) {
            DialogCustom.showError(getActivityContext(), "Please select vaccine type");
        } else {
            saveData();
        }
    }

    private void saveData() {
        try {
            new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SuccessAttendanceFragment$CTcF6ptSFQuvI1gHYjORWSs7U0U
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$saveData$3$SuccessAttendanceFragment();
                }
            }).start();
            if (this.thumbNotMatch) {
                this.fm.popBackStack();
                this.fm.popBackStack();
            } else {
                this.fm.popBackStack();
            }
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Data Not Saved. Try Again", 0).show();
        }
    }

    public /* synthetic */ void lambda$saveData$3$SuccessAttendanceFragment() {
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
            Integer key = entry.getKey();
            KRAData value = entry.getValue();
            if (value.getKRAName().contains(this.employeeId)) {
                if (value.getKRAName().equals("Covid19_Vaccine_Type_" + this.employeeId)) {
                    int i = this.vaccineId;
                    if (i == 0) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(String.valueOf(i));
                    }
                }
                if (value.getKRAName().equals("1st_Dose_of_Covid19_Vaccine_" + this.employeeId)) {
                    int i2 = this.firstDoseId;
                    if (i2 == -1) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(String.valueOf(i2));
                    }
                }
                if (value.getKRAName().equals("2nd_Dose_of_Covid19_Vaccine_" + this.employeeId)) {
                    int i3 = this.secondDoseId;
                    if (i3 == -1) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(String.valueOf(i3));
                    }
                }
                if (value.getKRAName().equals("Covid19_Medical_Reason_Image_" + this.employeeId)) {
                    String str = this.imageBase64;
                    if (str == null || str.isEmpty()) {
                        value.setDataValue("");
                    } else {
                        value.setDataValue(this.imageBase64);
                    }
                }
                CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
            }
        }
        for (int i4 = 0; i4 < CommonObjects.employeeData.size(); i4++) {
            if (CommonObjects.employeeData.get(i4).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i4, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i4).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_second_yes) {
            this.secondDoseId = 0;
        } else if (checkedId == R.id.rb_second_no) {
            this.secondDoseId = 1;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath) {
        if (bitmap != null) {
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
        if (bitmapPath != null) {
            this.imageBase64 = bitmapPath;
        }
    }
}
