package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.Map;

/* loaded from: classes2.dex */
public class GirlsStipend extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button cancel_button;
    EditText et_total_number_of_eligible;
    EditText et_total_number_of_enrollment;
    EditText et_total_number_of_received_candidates;
    View mView;
    int receivedGirlsStipendId = -1;
    RadioGroup rg_received_girls_stipend;
    Button save_button;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_girls_stipend, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_received_girls_stipend);
        this.rg_received_girls_stipend = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.et_total_number_of_enrollment = (EditText) view.findViewById(R.id.et_total_number_of_enrollment);
        this.et_total_number_of_eligible = (EditText) view.findViewById(R.id.et_total_number_of_eligible);
        this.et_total_number_of_received_candidates = (EditText) view.findViewById(R.id.et_total_number_of_received_candidates);
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.girls_stipend.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.GirlsStipend_Recieved)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.receivedGirlsStipendId = i;
                    ((RadioButton) this.rg_received_girls_stipend.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Total_Enrollments)) {
                    this.et_total_number_of_enrollment.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Eligible)) {
                    this.et_total_number_of_eligible.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Total_Students_Recieved)) {
                    this.et_total_number_of_received_candidates.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        int i2 = this.receivedGirlsStipendId;
        if (i2 == 0) {
            this.et_total_number_of_enrollment.setBackground(null);
            this.et_total_number_of_enrollment.setEnabled(true);
            this.et_total_number_of_eligible.setBackground(null);
            this.et_total_number_of_eligible.setEnabled(true);
            this.et_total_number_of_received_candidates.setBackground(null);
            this.et_total_number_of_received_candidates.setEnabled(true);
        } else if (i2 == 1 || i2 == 2) {
            this.et_total_number_of_enrollment.setBackgroundResource(R.color.light_gray);
            this.et_total_number_of_enrollment.setText("");
            this.et_total_number_of_enrollment.setEnabled(false);
            this.et_total_number_of_eligible.setBackgroundResource(R.color.light_gray);
            this.et_total_number_of_eligible.setText("");
            this.et_total_number_of_eligible.setEnabled(false);
            this.et_total_number_of_received_candidates.setBackgroundResource(R.color.light_gray);
            this.et_total_number_of_received_candidates.setText("");
            this.et_total_number_of_received_candidates.setEnabled(false);
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_received_girls_stipend.setEnabled(false);
        this.rg_received_girls_stipend.setFocusable(false);
        for (int i3 = 0; i3 < this.rg_received_girls_stipend.getChildCount(); i3++) {
            ((RadioButton) this.rg_received_girls_stipend.getChildAt(i3)).setFocusable(false);
            ((RadioButton) this.rg_received_girls_stipend.getChildAt(i3)).setEnabled(false);
        }
        this.et_total_number_of_enrollment.setEnabled(false);
        this.et_total_number_of_enrollment.setFocusable(false);
        this.et_total_number_of_eligible.setEnabled(false);
        this.et_total_number_of_eligible.setFocusable(false);
        this.et_total_number_of_received_candidates.setEnabled(false);
        this.et_total_number_of_received_candidates.setFocusable(false);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
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

    private void checkValidation() {
        int i = this.receivedGirlsStipendId;
        if (i != 0) {
            if (i == 1) {
                saveFieldsData();
                return;
            } else if (i == 2) {
                saveFieldsData();
                return;
            } else {
                DialogCustom.showError(getActivityContext(), "Please select the field.");
                return;
            }
        }
        if (this.et_total_number_of_enrollment.getText().toString().length() > 0 && this.et_total_number_of_eligible.getText().toString().length() > 0 && this.et_total_number_of_received_candidates.getText().toString().length() > 0) {
            if (Integer.parseInt(this.et_total_number_of_eligible.getText().toString()) <= Integer.parseInt(this.et_total_number_of_enrollment.getText().toString())) {
                if (Integer.parseInt(this.et_total_number_of_received_candidates.getText().toString()) <= Integer.parseInt(this.et_total_number_of_enrollment.getText().toString())) {
                    saveFieldsData();
                    return;
                } else {
                    DialogCustom.showError(getActivityContext(), "Received candidates can not be greater than Total number of Enrollment");
                    return;
                }
            }
            DialogCustom.showError(getActivityContext(), "Eligible students can not be greater than Total number of Enrollment");
            return;
        }
        DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
        if (this.et_total_number_of_enrollment.getText().toString().equals("")) {
            this.et_total_number_of_enrollment.setError("Please fill the field.");
            this.et_total_number_of_enrollment.setFocusableInTouchMode(true);
            this.et_total_number_of_enrollment.setFocusable(true);
        } else if (this.et_total_number_of_eligible.getText().toString().equals("")) {
            this.et_total_number_of_eligible.setError("Please fill the field.");
            this.et_total_number_of_eligible.setFocusableInTouchMode(true);
            this.et_total_number_of_eligible.setFocusable(true);
        } else if (this.et_total_number_of_received_candidates.getText().toString().equals("")) {
            this.et_total_number_of_received_candidates.setError("Please fill the field.");
            this.et_total_number_of_received_candidates.setFocusableInTouchMode(true);
            this.et_total_number_of_received_candidates.setFocusable(true);
        }
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            if (getActivity() != null) {
                this.fm.popBackStack();
            }
        } else if (this.et_total_number_of_enrollment.getText().toString().length() > 0 || this.et_total_number_of_eligible.getText().toString().length() > 0 || this.et_total_number_of_received_candidates.getText().toString().length() > 0 || this.receivedGirlsStipendId > -1) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_received_girls_stipend_na /* 2131231570 */:
                this.receivedGirlsStipendId = 2;
                this.et_total_number_of_enrollment.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_enrollment.setText("");
                this.et_total_number_of_enrollment.setEnabled(false);
                this.et_total_number_of_eligible.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_eligible.setText("");
                this.et_total_number_of_eligible.setEnabled(false);
                this.et_total_number_of_received_candidates.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_received_candidates.setText("");
                this.et_total_number_of_received_candidates.setEnabled(false);
                break;
            case R.id.rb_received_girls_stipend_no /* 2131231571 */:
                this.receivedGirlsStipendId = 1;
                this.et_total_number_of_enrollment.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_enrollment.setText("");
                this.et_total_number_of_enrollment.setEnabled(false);
                this.et_total_number_of_eligible.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_eligible.setText("");
                this.et_total_number_of_eligible.setEnabled(false);
                this.et_total_number_of_received_candidates.setBackgroundResource(R.color.light_gray);
                this.et_total_number_of_received_candidates.setText("");
                this.et_total_number_of_received_candidates.setEnabled(false);
                break;
            case R.id.rb_received_girls_stipend_yes /* 2131231572 */:
                this.receivedGirlsStipendId = 0;
                this.et_total_number_of_enrollment.setBackground(null);
                this.et_total_number_of_enrollment.setEnabled(true);
                this.et_total_number_of_eligible.setBackground(null);
                this.et_total_number_of_eligible.setEnabled(true);
                this.et_total_number_of_received_candidates.setBackground(null);
                this.et_total_number_of_received_candidates.setEnabled(true);
                break;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.GirlsStipend.1
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.girls_stipend.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.GirlsStipend_Recieved)) {
                        if (GirlsStipend.this.receivedGirlsStipendId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(GirlsStipend.this.receivedGirlsStipendId));
                        }
                    } else if (value.getKRAName().equals(Constant.Total_Enrollments)) {
                        value.setDataValue(GirlsStipend.this.et_total_number_of_enrollment.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Eligible)) {
                        value.setDataValue(GirlsStipend.this.et_total_number_of_eligible.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Total_Students_Recieved)) {
                        value.setDataValue(GirlsStipend.this.et_total_number_of_received_candidates.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
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
                        CommonActions.getDbHandler(GirlsStipend.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.iscv_girls_stipendDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
