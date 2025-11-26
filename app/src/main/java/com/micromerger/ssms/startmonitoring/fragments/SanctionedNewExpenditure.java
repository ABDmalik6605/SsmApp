package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
public class SanctionedNewExpenditure extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button cancel_button;
    EditText et_filled;
    EditText et_non_teaching_filled;
    EditText et_non_teaching_sanctioned;
    EditText et_non_teaching_vacant;
    EditText et_sanctioned;
    EditText et_vacant;
    View mView;
    RadioGroup rg_sne_approved;
    Button save_button;
    LinearLayout school_has_approved_sne_yes_layout;
    int sneApprovedId = -1;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_sne, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_sne_approved);
        this.rg_sne_approved = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.school_has_approved_sne_yes_layout = (LinearLayout) view.findViewById(R.id.school_has_approved_sne_yes_layout);
        EditText editText = (EditText) view.findViewById(R.id.et_sanctioned);
        this.et_sanctioned = editText;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.SanctionedNewExpenditure.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    SanctionedNewExpenditure.this.et_filled.setEnabled(true);
                    SanctionedNewExpenditure.this.et_filled.setText("");
                } else {
                    SanctionedNewExpenditure.this.et_filled.setEnabled(false);
                    SanctionedNewExpenditure.this.et_filled.setText("");
                }
            }
        });
        EditText editText2 = (EditText) view.findViewById(R.id.et_filled);
        this.et_filled = editText2;
        editText2.setEnabled(false);
        this.et_filled.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.SanctionedNewExpenditure.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (SanctionedNewExpenditure.this.et_sanctioned.getText().toString().length() > 0 && editable.toString().length() > 0) {
                    if (Integer.parseInt(editable.toString()) <= Integer.parseInt(SanctionedNewExpenditure.this.et_sanctioned.getText().toString())) {
                        SanctionedNewExpenditure.this.et_vacant.setText(String.valueOf(Integer.parseInt(SanctionedNewExpenditure.this.et_sanctioned.getText().toString()) - Integer.parseInt(editable.toString())));
                        return;
                    } else {
                        DialogCustom.showError(SanctionedNewExpenditure.this.getActivityContext(), "Teaching filled value should not be greater than sanctioned value.");
                        SanctionedNewExpenditure.this.et_filled.setText("");
                        return;
                    }
                }
                SanctionedNewExpenditure.this.et_vacant.setText("");
            }
        });
        EditText editText3 = (EditText) view.findViewById(R.id.et_vacant);
        this.et_vacant = editText3;
        editText3.setEnabled(false);
        EditText editText4 = (EditText) view.findViewById(R.id.et_non_teaching_sanctioned);
        this.et_non_teaching_sanctioned = editText4;
        editText4.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.SanctionedNewExpenditure.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    SanctionedNewExpenditure.this.et_non_teaching_filled.setEnabled(true);
                    SanctionedNewExpenditure.this.et_non_teaching_filled.setText("");
                } else {
                    SanctionedNewExpenditure.this.et_non_teaching_filled.setEnabled(false);
                    SanctionedNewExpenditure.this.et_non_teaching_filled.setText("");
                }
            }
        });
        EditText editText5 = (EditText) view.findViewById(R.id.et_non_teaching_filled);
        this.et_non_teaching_filled = editText5;
        editText5.setEnabled(false);
        this.et_non_teaching_filled.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.SanctionedNewExpenditure.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (SanctionedNewExpenditure.this.et_non_teaching_sanctioned.getText().toString().length() > 0 && editable.toString().length() > 0) {
                    if (Integer.parseInt(editable.toString()) <= Integer.parseInt(SanctionedNewExpenditure.this.et_non_teaching_sanctioned.getText().toString())) {
                        SanctionedNewExpenditure.this.et_non_teaching_vacant.setText(String.valueOf(Integer.parseInt(SanctionedNewExpenditure.this.et_non_teaching_sanctioned.getText().toString()) - Integer.parseInt(editable.toString())));
                        return;
                    } else {
                        DialogCustom.showError(SanctionedNewExpenditure.this.getActivityContext(), "Non teaching filled value should not be greater than sanctioned value.");
                        SanctionedNewExpenditure.this.et_non_teaching_filled.setText("");
                        return;
                    }
                }
                SanctionedNewExpenditure.this.et_non_teaching_vacant.setText("");
            }
        });
        EditText editText6 = (EditText) view.findViewById(R.id.et_non_teaching_vacant);
        this.et_non_teaching_vacant = editText6;
        editText6.setEnabled(false);
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
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.sanctioned_new_expenditure.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Has_Approved_SNE)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.sneApprovedId = i;
                    ((RadioButton) this.rg_sne_approved.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.SanctionedTeaching)) {
                    this.et_sanctioned.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.FilledTeaching)) {
                    this.et_filled.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.VacantTeaching)) {
                    this.et_vacant.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.SanctionedNonTeaching)) {
                    this.et_non_teaching_sanctioned.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.FilledNonTeaching)) {
                    this.et_non_teaching_filled.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.VacantNonTeaching)) {
                    this.et_non_teaching_vacant.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        if (this.sneApprovedId == 0) {
            this.school_has_approved_sne_yes_layout.setVisibility(0);
        } else {
            this.school_has_approved_sne_yes_layout.setVisibility(8);
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_sne_approved.setEnabled(false);
        this.rg_sne_approved.setFocusable(false);
        for (int i2 = 0; i2 < this.rg_sne_approved.getChildCount(); i2++) {
            ((RadioButton) this.rg_sne_approved.getChildAt(i2)).setFocusable(false);
            ((RadioButton) this.rg_sne_approved.getChildAt(i2)).setEnabled(false);
        }
        this.et_sanctioned.setEnabled(false);
        this.et_sanctioned.setFocusable(false);
        this.et_filled.setEnabled(false);
        this.et_filled.setFocusable(false);
        this.et_vacant.setEnabled(false);
        this.et_vacant.setFocusable(false);
        this.et_non_teaching_sanctioned.setEnabled(false);
        this.et_non_teaching_sanctioned.setFocusable(false);
        this.et_non_teaching_filled.setEnabled(false);
        this.et_non_teaching_filled.setFocusable(false);
        this.et_non_teaching_vacant.setEnabled(false);
        this.et_non_teaching_vacant.setFocusable(false);
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

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        for (int i2 = 0; i2 < radioGroup.getChildCount(); i2++) {
            ((RadioButton) radioGroup.getChildAt(i2)).setError(null);
        }
        switch (i) {
            case R.id.rb_school_has_approved_sne_no /* 2131231580 */:
                this.sneApprovedId = 1;
                this.school_has_approved_sne_yes_layout.setVisibility(8);
                this.et_sanctioned.setText("");
                this.et_filled.setText("");
                this.et_vacant.setText("");
                this.et_non_teaching_sanctioned.setText("");
                this.et_non_teaching_filled.setText("");
                this.et_non_teaching_vacant.setText("");
                break;
            case R.id.rb_school_has_approved_sne_yes /* 2131231581 */:
                this.sneApprovedId = 0;
                this.school_has_approved_sne_yes_layout.setVisibility(0);
                break;
        }
    }

    private void checkValidation() {
        int i = this.sneApprovedId;
        if (i != 0) {
            if (i == 1) {
                saveFieldsData();
                return;
            }
            ((RadioButton) this.rg_sne_approved.getChildAt(0)).setError("Please fill the field.");
            ViewParent parent = this.rg_sne_approved.getParent();
            RadioGroup radioGroup = this.rg_sne_approved;
            parent.requestChildFocus(radioGroup, radioGroup);
            DialogCustom.showError(getActivityContext(), "Please select school has approved SNE or not.");
            return;
        }
        if (this.et_sanctioned.getText().toString().length() > 0 && this.et_filled.getText().toString().length() > 0 && this.et_vacant.getText().toString().length() > 0 && this.et_non_teaching_sanctioned.getText().toString().length() > 0 && this.et_non_teaching_filled.getText().toString().length() > 0 && this.et_non_teaching_vacant.getText().toString().length() > 0) {
            saveFieldsData();
            return;
        }
        DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
        if (this.et_sanctioned.getText().toString().equals("")) {
            showError(this.et_sanctioned);
            return;
        }
        if (this.et_filled.getText().toString().equals("")) {
            showError(this.et_filled);
            return;
        }
        if (this.et_vacant.getText().toString().equals("")) {
            showError(this.et_vacant);
            return;
        }
        if (this.et_non_teaching_sanctioned.getText().toString().equals("")) {
            showError(this.et_non_teaching_sanctioned);
        } else if (this.et_non_teaching_filled.getText().toString().equals("")) {
            showError(this.et_non_teaching_filled);
        } else if (this.et_non_teaching_vacant.getText().toString().equals("")) {
            showError(this.et_non_teaching_vacant);
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
            this.fm.popBackStack();
            return;
        }
        if (this.sneApprovedId > -1 || this.et_sanctioned.getText().toString().length() > 0 || this.et_filled.getText().toString().length() > 0 || this.et_vacant.getText().toString().length() > 0 || this.et_non_teaching_sanctioned.getText().toString().length() > 0 || this.et_non_teaching_filled.getText().toString().length() > 0 || this.et_non_teaching_vacant.getText().toString().length() > 0) {
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
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.SanctionedNewExpenditure.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.sanctioned_new_expenditure.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Has_Approved_SNE)) {
                        if (SanctionedNewExpenditure.this.sneApprovedId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(SanctionedNewExpenditure.this.sneApprovedId));
                        }
                    } else if (value.getKRAName().equals(Constant.SanctionedTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_sanctioned.getText().toString());
                    } else if (value.getKRAName().equals(Constant.FilledTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_filled.getText().toString());
                    } else if (value.getKRAName().equals(Constant.VacantTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_vacant.getText().toString());
                    } else if (value.getKRAName().equals(Constant.SanctionedNonTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_non_teaching_sanctioned.getText().toString());
                    } else if (value.getKRAName().equals(Constant.FilledNonTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_non_teaching_filled.getText().toString());
                    } else if (value.getKRAName().equals(Constant.VacantNonTeaching)) {
                        value.setDataValue(SanctionedNewExpenditure.this.et_non_teaching_vacant.getText().toString());
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
                        CommonActions.getDbHandler(SanctionedNewExpenditure.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.iscv_sanctioned_new_expenditureDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
