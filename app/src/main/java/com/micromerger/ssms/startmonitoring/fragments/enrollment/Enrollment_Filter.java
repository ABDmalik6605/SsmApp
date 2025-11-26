package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.CommonObjectsUtils;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class Enrollment_Filter extends BaseFragment {
    Button btn_back;
    Button btn_next;
    View mView;
    Spinner sp_enrollment_source;
    List<ReferenceDataResponse.ReferenceData> SOURCE_OF_ENROLLMENT = new ArrayList();
    int enrollmentSourcePos = 0;
    String schoolType = "";

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_filter, (ViewGroup) null);
        this.mView = viewInflate;
        this.btn_next = (Button) viewInflate.findViewById(R.id.btn_next);
        this.btn_back = (Button) this.mView.findViewById(R.id.btn_back);
        this.sp_enrollment_source = (Spinner) this.mView.findViewById(R.id.sp_enrollment_source);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter.1
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.ENROLLMENT_SOURCE);
            }
        });
        if (collectionSelect instanceof List) {
            this.SOURCE_OF_ENROLLMENT = (List) collectionSelect;
        } else {
            this.SOURCE_OF_ENROLLMENT = new ArrayList(collectionSelect);
        }
        this.sp_enrollment_source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Enrollment_Filter.this.enrollmentSourcePos = position;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(Enrollment_Filter.this.getActivityContext(), Enrollment_Filter.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_enrollment_source.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.SOURCE_OF_ENROLLMENT));
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (CommonObjects.userObj == null || CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Enrollment_Filter.this.fm.popBackStack();
            }
        });
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CommonActions.hideSoftKeyboard(Enrollment_Filter.this.getActivityContext(), Enrollment_Filter.this.getActivity().getCurrentFocus());
                if (!Enrollment_Filter.this.SOURCE_OF_ENROLLMENT.get(Enrollment_Filter.this.enrollmentSourcePos).getReferencevalue().equals(Constant.Select)) {
                    Enrollment_Filter.this.saveFieldsData();
                } else {
                    DialogCustom.showError(Enrollment_Filter.this.getActivityContext(), "Please select source of enrollment.");
                }
            }
        });
    }

    void changeFragment(Fragment fragment) {
        this.ft = this.fm.beginTransaction();
        this.ft.replace(R.id.container, fragment);
        this.ft.addToBackStack(null);
        this.ft.commit();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (CommonObjects.userObj == null || CommonObjects.school == null || CommonObjects.monitoring == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Enrollment_Source)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.enrollmentSourcePos = i;
                    if (i < this.SOURCE_OF_ENROLLMENT.size()) {
                        this.sp_enrollment_source.setSelection(this.enrollmentSourcePos);
                    }
                } else if (value.getKRAName().equals(Constant.TypeOfSchool_ID)) {
                    this.schoolType = (String) value.getDataValue();
                }
            }
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.sp_enrollment_source.setEnabled(false);
            this.sp_enrollment_source.setFocusable(false);
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Enrollment_Source)) {
                        value.setDataValue(String.valueOf(Enrollment_Filter.this.enrollmentSourcePos));
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(Enrollment_Filter.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        changeFragment(new Elementary(this.schoolType));
    }
}
