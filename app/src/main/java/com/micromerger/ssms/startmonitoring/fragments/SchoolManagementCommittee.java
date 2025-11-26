package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class SchoolManagementCommittee extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {
    Button cancel_button;
    EditText et_ac_number;
    EditText et_ac_title;
    EditText et_balance_available_from_previous_year;
    EditText et_bank_name;
    EditText et_branch_code;
    EditText et_comments;
    EditText et_funds_received_current_year;
    EditText et_funds_utilized_expenditure_ineligible_items;
    EditText et_funds_utilized_maintenance_of_infrastructure;
    EditText et_funds_utilized_purchase_health_disinfection_items;
    EditText et_funds_utilized_purchase_learning_teaching_material;
    EditText et_name_of_branch;
    EditText et_total_funds_utilized_current_year;
    View mView;
    RadioGroup rg_functional;
    RadioGroup rg_funds;
    Button save_button;
    TextView tv_funds;
    TextView tv_smc_balance_previous_year;
    TextView tv_smc_funds_current_year;
    int functionalId = -1;
    int fundsId = -1;
    String acTitle = "";
    String bankName = "";
    String nameOfBranch = "";
    String branchCode = "";
    String fundsYear = "";

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    Object getCurrentYear() {
        return Integer.valueOf(Calendar.getInstance().get(1));
    }

    int getPreviousYear() {
        return Calendar.getInstance().get(1) - 1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_smc, (ViewGroup) null);
        Iterator<ReferenceDataResponse.ReferenceData> it = this.preferenceHelper.getReferenceData().getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ReferenceDataResponse.ReferenceData next = it.next();
            if (next.getReferencecode().equals(Constant.FUNDS_YEAR)) {
                this.fundsYear = next.getReferencevalue();
                break;
            }
        }
        TextView textView = (TextView) this.mView.findViewById(R.id.tv_funds);
        this.tv_funds = textView;
        textView.setText("Has this school got SMC Funds in " + getCurrentYear() + "? *");
        TextView textView2 = (TextView) this.mView.findViewById(R.id.tv_smc_balance_previous_year);
        this.tv_smc_balance_previous_year = textView2;
        textView2.setText("SMC balance available from previous years (" + getPreviousYear() + ")");
        TextView textView3 = (TextView) this.mView.findViewById(R.id.tv_smc_funds_current_year);
        this.tv_smc_funds_current_year = textView3;
        textView3.setText("How much Funds received in " + getCurrentYear() + "? *");
        this.et_balance_available_from_previous_year = (EditText) this.mView.findViewById(R.id.et_balance_available_from_previous_year);
        this.et_funds_received_current_year = (EditText) this.mView.findViewById(R.id.et_funds_received_current_year);
        this.et_funds_utilized_maintenance_of_infrastructure = (EditText) this.mView.findViewById(R.id.et_funds_utilized_maintenance_of_infrastructure);
        this.et_funds_utilized_purchase_learning_teaching_material = (EditText) this.mView.findViewById(R.id.et_funds_utilized_purchase_learning_teaching_material);
        this.et_funds_utilized_purchase_health_disinfection_items = (EditText) this.mView.findViewById(R.id.et_funds_utilized_purchase_health_disinfection_items);
        this.et_funds_utilized_expenditure_ineligible_items = (EditText) this.mView.findViewById(R.id.et_funds_utilized_expenditure_ineligible_items);
        this.et_total_funds_utilized_current_year = (EditText) this.mView.findViewById(R.id.et_total_funds_utilized_current_year);
        this.et_comments = (EditText) this.mView.findViewById(R.id.et_comments);
        RadioGroup radioGroup = (RadioGroup) this.mView.findViewById(R.id.rg_functional);
        this.rg_functional = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = (RadioGroup) this.mView.findViewById(R.id.rg_funds);
        this.rg_funds = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        this.et_ac_title = (EditText) this.mView.findViewById(R.id.et_ac_title);
        this.et_ac_number = (EditText) this.mView.findViewById(R.id.et_ac_number);
        this.et_bank_name = (EditText) this.mView.findViewById(R.id.et_bank_name);
        this.et_name_of_branch = (EditText) this.mView.findViewById(R.id.et_name_of_branch);
        this.et_branch_code = (EditText) this.mView.findViewById(R.id.et_branch_code);
        Button button = (Button) this.mView.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.mView.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        this.et_balance_available_from_previous_year.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolManagementCommittee.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                SchoolManagementCommittee schoolManagementCommittee = SchoolManagementCommittee.this;
                if (schoolManagementCommittee.isEmptyOrZero(schoolManagementCommittee.et_balance_available_from_previous_year) && SchoolManagementCommittee.this.fundsId == 1) {
                    SchoolManagementCommittee.this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(false);
                    SchoolManagementCommittee.this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(false);
                    SchoolManagementCommittee.this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(false);
                    SchoolManagementCommittee.this.et_funds_utilized_expenditure_ineligible_items.setEnabled(false);
                    SchoolManagementCommittee.this.et_total_funds_utilized_current_year.setEnabled(false);
                    SchoolManagementCommittee.this.et_funds_utilized_maintenance_of_infrastructure.setBackgroundResource(R.color.light_gray);
                    SchoolManagementCommittee.this.et_funds_utilized_purchase_learning_teaching_material.setBackgroundResource(R.color.light_gray);
                    SchoolManagementCommittee.this.et_funds_utilized_purchase_health_disinfection_items.setBackgroundResource(R.color.light_gray);
                    SchoolManagementCommittee.this.et_funds_utilized_expenditure_ineligible_items.setBackgroundResource(R.color.light_gray);
                    SchoolManagementCommittee.this.et_total_funds_utilized_current_year.setBackgroundResource(R.color.light_gray);
                    return;
                }
                SchoolManagementCommittee.this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(true);
                SchoolManagementCommittee.this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(true);
                SchoolManagementCommittee.this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(true);
                SchoolManagementCommittee.this.et_funds_utilized_expenditure_ineligible_items.setEnabled(true);
                SchoolManagementCommittee.this.et_total_funds_utilized_current_year.setEnabled(true);
                SchoolManagementCommittee.this.et_funds_utilized_maintenance_of_infrastructure.setBackground(null);
                SchoolManagementCommittee.this.et_funds_utilized_purchase_learning_teaching_material.setBackground(null);
                SchoolManagementCommittee.this.et_funds_utilized_purchase_health_disinfection_items.setBackground(null);
                SchoolManagementCommittee.this.et_funds_utilized_expenditure_ineligible_items.setBackground(null);
                SchoolManagementCommittee.this.et_total_funds_utilized_current_year.setBackground(null);
            }
        });
        this.et_comments.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolManagementCommittee$t2QHLnKbQpT0XAyClwZ_VVmvJM0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$onCreateView$0$SchoolManagementCommittee(view, motionEvent);
            }
        });
        return this.mView;
    }

    public /* synthetic */ boolean lambda$onCreateView$0$SchoolManagementCommittee(View view, MotionEvent motionEvent) {
        if (this.et_comments.hasFocus()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if ((motionEvent.getAction() & 255) == 8) {
                view.getParent().requestDisallowInterceptTouchEvent(false);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.et_funds_utilized_maintenance_of_infrastructure.addTextChangedListener(this);
        this.et_funds_utilized_purchase_learning_teaching_material.addTextChangedListener(this);
        this.et_funds_utilized_purchase_health_disinfection_items.addTextChangedListener(this);
        this.et_funds_utilized_expenditure_ineligible_items.addTextChangedListener(this);
    }

    private void calculateTotal() {
        boolean zIsEmpty = this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().isEmpty();
        String string = Constant.ECE_Katchi;
        int i = Integer.parseInt(zIsEmpty ? Constant.ECE_Katchi : this.et_funds_utilized_maintenance_of_infrastructure.getText().toString()) + Integer.parseInt(this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().isEmpty() ? Constant.ECE_Katchi : this.et_funds_utilized_purchase_learning_teaching_material.getText().toString()) + Integer.parseInt(this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().isEmpty() ? Constant.ECE_Katchi : this.et_funds_utilized_purchase_health_disinfection_items.getText().toString());
        if (!this.et_funds_utilized_expenditure_ineligible_items.getText().toString().isEmpty()) {
            string = this.et_funds_utilized_expenditure_ineligible_items.getText().toString();
        }
        this.et_total_funds_utilized_current_year.setText(String.valueOf(i + Integer.parseInt(string)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTotalFundsForCurrentYear() throws NumberFormatException {
        boolean zIsEmpty = this.et_balance_available_from_previous_year.getText().toString().isEmpty();
        String string = Constant.ECE_Katchi;
        int i = Integer.parseInt(zIsEmpty ? Constant.ECE_Katchi : this.et_balance_available_from_previous_year.getText().toString());
        if (!this.et_funds_received_current_year.getText().toString().isEmpty()) {
            string = this.et_funds_received_current_year.getText().toString();
        }
        int i2 = i + Integer.parseInt(string);
        return i2 == 0 ? "" : String.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTotalFundsAvailableCurrentYear() {
        boolean zIsEmpty = this.et_balance_available_from_previous_year.getText().toString().isEmpty();
        String string = Constant.ECE_Katchi;
        int i = Integer.parseInt(zIsEmpty ? Constant.ECE_Katchi : this.et_balance_available_from_previous_year.getText().toString()) + Integer.parseInt(this.et_funds_received_current_year.getText().toString().isEmpty() ? Constant.ECE_Katchi : this.et_funds_received_current_year.getText().toString());
        if (!this.et_total_funds_utilized_current_year.getText().toString().isEmpty()) {
            string = this.et_total_funds_utilized_current_year.getText().toString();
        }
        int i2 = i - Integer.parseInt(string);
        return i2 == 0 ? "" : String.valueOf(i2);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.smc_fields.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Is_Functional)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.functionalId = i;
                    ((RadioButton) this.rg_functional.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Got_Funds)) {
                    int i2 = Integer.parseInt((String) value.getDataValue());
                    this.fundsId = i2;
                    ((RadioButton) this.rg_funds.getChildAt(i2)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.SMC_Account_Title)) {
                    this.et_ac_title.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.SMC_Account_Number)) {
                    this.et_ac_number.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Bank_Name)) {
                    this.et_bank_name.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Branch_Name)) {
                    this.et_name_of_branch.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Branch_Code)) {
                    this.et_branch_code.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equalsIgnoreCase("Balance_Available_From_Previous_Year")) {
                    this.et_balance_available_from_previous_year.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Funds_Received_Current_Year")) {
                    this.et_funds_received_current_year.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Maintenance_of_Infrastructure")) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Learning_Teaching_Material")) {
                    this.et_funds_utilized_purchase_learning_teaching_material.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Health_Disinfection_Items")) {
                    this.et_funds_utilized_purchase_health_disinfection_items.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Expenditure_Ineligible_Items")) {
                    this.et_funds_utilized_expenditure_ineligible_items.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase("Total_Funds_Utilized_Current_Year")) {
                    this.et_total_funds_utilized_current_year.setText((String) value.getDataValue());
                } else if (value.getKRAName().equalsIgnoreCase(Constant.Comments)) {
                    this.et_comments.setText((String) value.getDataValue());
                }
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.smc_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value2 = it.next().getValue();
            if (value2.getDataValue() != null && !value2.getDataValue().equals("")) {
                if (value2.getKRAName().equalsIgnoreCase("Balance_Available_From_Previous_Year")) {
                    this.et_balance_available_from_previous_year.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Funds_Received_Current_Year")) {
                    this.et_funds_received_current_year.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Funds_Utilized_Maintenance_of_Infrastructure")) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Learning_Teaching_Material")) {
                    this.et_funds_utilized_purchase_learning_teaching_material.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Health_Disinfection_Items")) {
                    this.et_funds_utilized_purchase_health_disinfection_items.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Funds_Utilized_Expenditure_Ineligible_Items")) {
                    this.et_funds_utilized_expenditure_ineligible_items.setText((String) value2.getDataValue());
                } else if (value2.getKRAName().equalsIgnoreCase("Total_Funds_Utilized_Current_Year")) {
                    this.et_total_funds_utilized_current_year.setText((String) value2.getDataValue());
                }
            }
        }
        if (this.fundsId == 1) {
            this.et_ac_title.setEnabled(false);
            this.et_ac_number.setEnabled(false);
            this.et_bank_name.setEnabled(false);
            this.et_name_of_branch.setEnabled(false);
            this.et_branch_code.setEnabled(false);
            this.et_funds_received_current_year.setEnabled(false);
            if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(false);
                this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(false);
                this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(false);
                this.et_funds_utilized_expenditure_ineligible_items.setEnabled(false);
            }
            this.et_ac_title.setBackgroundResource(R.color.light_gray);
            this.et_ac_number.setBackgroundResource(R.color.light_gray);
            this.et_bank_name.setBackgroundResource(R.color.light_gray);
            this.et_name_of_branch.setBackgroundResource(R.color.light_gray);
            this.et_branch_code.setBackgroundResource(R.color.light_gray);
            this.et_funds_received_current_year.setBackgroundResource(R.color.light_gray);
            if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                this.et_funds_utilized_maintenance_of_infrastructure.setBackgroundResource(R.color.light_gray);
                this.et_funds_utilized_purchase_learning_teaching_material.setBackgroundResource(R.color.light_gray);
                this.et_funds_utilized_purchase_health_disinfection_items.setBackgroundResource(R.color.light_gray);
                this.et_funds_utilized_expenditure_ineligible_items.setBackgroundResource(R.color.light_gray);
            }
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.rg_functional.setEnabled(false);
            this.rg_functional.setFocusable(false);
            for (int i3 = 0; i3 < this.rg_functional.getChildCount(); i3++) {
                ((RadioButton) this.rg_functional.getChildAt(i3)).setFocusable(false);
                ((RadioButton) this.rg_functional.getChildAt(i3)).setEnabled(false);
            }
            this.rg_funds.setEnabled(false);
            this.rg_funds.setFocusable(false);
            for (int i4 = 0; i4 < this.rg_funds.getChildCount(); i4++) {
                ((RadioButton) this.rg_funds.getChildAt(i4)).setFocusable(false);
                ((RadioButton) this.rg_funds.getChildAt(i4)).setEnabled(false);
            }
            this.et_ac_title.setEnabled(false);
            this.et_ac_title.setFocusable(false);
            this.et_ac_number.setEnabled(false);
            this.et_ac_number.setFocusable(false);
            this.et_bank_name.setEnabled(false);
            this.et_bank_name.setFocusable(false);
            this.et_name_of_branch.setEnabled(false);
            this.et_name_of_branch.setFocusable(false);
            this.et_branch_code.setEnabled(false);
            this.et_branch_code.setFocusable(false);
            this.et_balance_available_from_previous_year.setEnabled(false);
            this.et_funds_received_current_year.setEnabled(false);
            this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(false);
            this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(false);
            this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(false);
            this.et_funds_utilized_expenditure_ineligible_items.setEnabled(false);
            this.et_total_funds_utilized_current_year.setEnabled(false);
            this.et_comments.setEnabled(false);
            this.save_button.setEnabled(false);
            this.save_button.setAlpha(0.5f);
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_functional_no /* 2131231520 */:
                this.functionalId = 1;
                break;
            case R.id.rb_functional_yes /* 2131231521 */:
                this.functionalId = 0;
                break;
            case R.id.rb_funds_no /* 2131231524 */:
                this.fundsId = 1;
                this.et_ac_title.setText("");
                this.et_ac_number.setText("");
                this.et_bank_name.setText("");
                this.et_name_of_branch.setText("");
                this.et_branch_code.setText("");
                this.et_funds_received_current_year.setText("");
                if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setText("");
                    this.et_funds_utilized_purchase_learning_teaching_material.setText("");
                    this.et_funds_utilized_purchase_health_disinfection_items.setText("");
                    this.et_funds_utilized_expenditure_ineligible_items.setText("");
                    this.et_total_funds_utilized_current_year.setText("");
                }
                this.et_ac_title.setEnabled(false);
                this.et_ac_number.setEnabled(false);
                this.et_bank_name.setEnabled(false);
                this.et_name_of_branch.setEnabled(false);
                this.et_branch_code.setEnabled(false);
                this.et_funds_received_current_year.setEnabled(false);
                if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(false);
                    this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(false);
                    this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(false);
                    this.et_funds_utilized_expenditure_ineligible_items.setEnabled(false);
                } else {
                    this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(true);
                    this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(true);
                    this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(true);
                    this.et_funds_utilized_expenditure_ineligible_items.setEnabled(true);
                }
                this.et_ac_title.setBackgroundResource(R.color.light_gray);
                this.et_ac_number.setBackgroundResource(R.color.light_gray);
                this.et_bank_name.setBackgroundResource(R.color.light_gray);
                this.et_name_of_branch.setBackgroundResource(R.color.light_gray);
                this.et_branch_code.setBackgroundResource(R.color.light_gray);
                this.et_funds_received_current_year.setBackgroundResource(R.color.light_gray);
                if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setBackgroundResource(R.color.light_gray);
                    this.et_funds_utilized_purchase_learning_teaching_material.setBackgroundResource(R.color.light_gray);
                    this.et_funds_utilized_purchase_health_disinfection_items.setBackgroundResource(R.color.light_gray);
                    this.et_funds_utilized_expenditure_ineligible_items.setBackgroundResource(R.color.light_gray);
                    this.et_total_funds_utilized_current_year.setBackgroundResource(R.color.light_gray);
                    break;
                } else {
                    this.et_funds_utilized_maintenance_of_infrastructure.setBackground(null);
                    this.et_funds_utilized_purchase_learning_teaching_material.setBackground(null);
                    this.et_funds_utilized_purchase_health_disinfection_items.setBackground(null);
                    this.et_funds_utilized_expenditure_ineligible_items.setBackground(null);
                    this.et_total_funds_utilized_current_year.setBackground(null);
                    break;
                }
            case R.id.rb_funds_yes /* 2131231525 */:
                this.fundsId = 0;
                this.et_ac_title.setEnabled(true);
                this.et_ac_number.setEnabled(true);
                this.et_bank_name.setEnabled(true);
                this.et_name_of_branch.setEnabled(true);
                this.et_branch_code.setEnabled(true);
                this.et_funds_received_current_year.setEnabled(true);
                this.et_funds_utilized_maintenance_of_infrastructure.setEnabled(true);
                this.et_funds_utilized_purchase_learning_teaching_material.setEnabled(true);
                this.et_funds_utilized_purchase_health_disinfection_items.setEnabled(true);
                this.et_funds_utilized_expenditure_ineligible_items.setEnabled(true);
                this.et_ac_title.setBackground(null);
                this.et_ac_number.setBackground(null);
                this.et_bank_name.setBackground(null);
                this.et_name_of_branch.setBackground(null);
                this.et_branch_code.setBackground(null);
                this.et_funds_received_current_year.setBackground(null);
                this.et_funds_utilized_maintenance_of_infrastructure.setBackground(null);
                this.et_funds_utilized_purchase_learning_teaching_material.setBackground(null);
                this.et_funds_utilized_purchase_health_disinfection_items.setBackground(null);
                this.et_funds_utilized_expenditure_ineligible_items.setBackground(null);
                this.et_total_funds_utilized_current_year.setBackground(null);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEmptyOrZero(EditText editText) {
        return editText.getText().toString().trim().isEmpty() || Integer.parseInt(editText.getText().toString().trim()) == 0;
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
        if (this.functionalId == -1) {
            DialogCustom.showError(getActivityContext(), "Please select is SMC functional or not.");
            return;
        }
        int i = this.fundsId;
        if (i == -1) {
            DialogCustom.showError(getActivityContext(), "Please select SMC funds option.");
            return;
        }
        String totalFundsAvailableCurrentYear = Constant.ECE_Katchi;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            if (isEmptyOrZero(this.et_balance_available_from_previous_year)) {
                if (!getTotalFundsAvailableCurrentYear().isEmpty()) {
                    totalFundsAvailableCurrentYear = getTotalFundsAvailableCurrentYear();
                }
                if (Integer.parseInt(totalFundsAvailableCurrentYear) >= 0) {
                    saveData();
                    return;
                } else {
                    DialogCustom.showError(getActivityContext(), "Total funds utilized can not be greater than Total available funds");
                    return;
                }
            }
            if (this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().equals("") || this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().equals("") || this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().equals("") || this.et_funds_utilized_expenditure_ineligible_items.getText().toString().equals("")) {
                if (this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().equals("")) {
                    this.et_funds_utilized_maintenance_of_infrastructure.setError("Please fill the fields");
                }
                if (this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().equals("")) {
                    this.et_funds_utilized_purchase_learning_teaching_material.setError("Please fill the fields");
                }
                if (this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().equals("")) {
                    this.et_funds_utilized_purchase_health_disinfection_items.setError("Please fill the fields");
                }
                if (this.et_funds_utilized_expenditure_ineligible_items.getText().toString().equals("")) {
                    this.et_funds_utilized_expenditure_ineligible_items.setError("Please fill the fields");
                }
                DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
                return;
            }
            if (!getTotalFundsAvailableCurrentYear().isEmpty()) {
                totalFundsAvailableCurrentYear = getTotalFundsAvailableCurrentYear();
            }
            if (Integer.parseInt(totalFundsAvailableCurrentYear) >= 0) {
                saveData();
                return;
            } else {
                DialogCustom.showError(getActivityContext(), "Total funds utilized can not be greater than Total available funds");
                return;
            }
        }
        if (this.et_ac_title.getText().toString().equals("") || this.et_ac_number.getText().toString().equals("") || this.et_bank_name.getText().toString().equals("") || this.et_name_of_branch.getText().toString().equals("") || this.et_branch_code.getText().toString().equals("") || this.et_funds_received_current_year.getText().toString().equals("") || this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().equals("") || this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().equals("") || this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().equals("") || this.et_funds_utilized_expenditure_ineligible_items.getText().toString().equals("")) {
            if (this.et_ac_title.getText().toString().equals("")) {
                this.et_ac_title.setError("Please fill the fields");
            }
            if (this.et_ac_number.getText().toString().equals("")) {
                this.et_ac_number.setError("Please fill the fields");
            }
            if (this.et_bank_name.getText().toString().equals("")) {
                this.et_bank_name.setError("Please fill the fields");
            }
            if (this.et_name_of_branch.getText().toString().equals("")) {
                this.et_name_of_branch.setError("Please fill the fields");
            }
            if (this.et_branch_code.getText().toString().equals("")) {
                this.et_branch_code.setError("Please fill the fields");
            }
            if (this.et_funds_received_current_year.getText().toString().equals("")) {
                this.et_funds_received_current_year.setError("Please fill the fields");
            }
            if (this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().equals("")) {
                this.et_funds_utilized_maintenance_of_infrastructure.setError("Please fill the fields");
            }
            if (this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().equals("")) {
                this.et_funds_utilized_purchase_learning_teaching_material.setError("Please fill the fields");
            }
            if (this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().equals("")) {
                this.et_funds_utilized_purchase_health_disinfection_items.setError("Please fill the fields");
            }
            if (this.et_funds_utilized_expenditure_ineligible_items.getText().toString().equals("")) {
                this.et_funds_utilized_expenditure_ineligible_items.setError("Please fill the fields");
            }
            DialogCustom.showError(getActivityContext(), "Please fill all the fields.");
            return;
        }
        if (!getTotalFundsAvailableCurrentYear().isEmpty()) {
            totalFundsAvailableCurrentYear = getTotalFundsAvailableCurrentYear();
        }
        if (Integer.parseInt(totalFundsAvailableCurrentYear) >= 0) {
            saveData();
        } else {
            DialogCustom.showError(getActivityContext(), "Total funds utilized can not be greater than Total available funds");
        }
    }

    private void saveData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolManagementCommittee.2
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.smc_fields.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Is_Functional)) {
                        value.setDataValue(String.valueOf(SchoolManagementCommittee.this.functionalId));
                    } else if (value.getKRAName().equals(Constant.Got_Funds)) {
                        value.setDataValue(String.valueOf(SchoolManagementCommittee.this.fundsId));
                    } else if (value.getKRAName().equals(Constant.SMC_Account_Title)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_ac_title.getText().toString());
                    } else if (value.getKRAName().equals(Constant.SMC_Account_Number)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_ac_number.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Bank_Name)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_bank_name.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Branch_Name)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_name_of_branch.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Branch_Code)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_branch_code.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equalsIgnoreCase("Balance_Available_From_Previous_Year")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_balance_available_from_previous_year.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Funds_Received_Current_Year")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_funds_received_current_year.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Maintenance_of_Infrastructure")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_funds_utilized_maintenance_of_infrastructure.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Learning_Teaching_Material")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_funds_utilized_purchase_learning_teaching_material.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Purchase_Health_Disinfection_Items")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_funds_utilized_purchase_health_disinfection_items.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Funds_Utilized_Expenditure_Ineligible_Items")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_funds_utilized_expenditure_ineligible_items.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Total_Funds_Utilized_Current_Year")) {
                        value.setDataValue(SchoolManagementCommittee.this.et_total_funds_utilized_current_year.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase(Constant.Comments)) {
                        value.setDataValue(SchoolManagementCommittee.this.et_comments.getText().toString());
                    } else if (value.getKRAName().equalsIgnoreCase("Total_Funds_Current_Year")) {
                        value.setDataValue(SchoolManagementCommittee.this.getTotalFundsForCurrentYear());
                    } else if (value.getKRAName().equalsIgnoreCase("Total_Funds_Available_Current_Year")) {
                        value.setDataValue(SchoolManagementCommittee.this.getTotalFundsAvailableCurrentYear());
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(SchoolManagementCommittee.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        this.fm.popBackStack();
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
            return;
        }
        if (this.functionalId > -1 || this.fundsId > -1 || this.et_ac_title.getText().toString().length() > 0 || this.et_ac_number.getText().toString().length() > 0 || this.et_bank_name.getText().toString().length() > 0 || this.et_name_of_branch.getText().toString().length() > 0 || this.et_branch_code.getText().toString().length() > 0 || this.et_balance_available_from_previous_year.getText().toString().length() > 0 || this.et_funds_received_current_year.getText().toString().length() > 0 || this.et_funds_utilized_maintenance_of_infrastructure.getText().toString().length() > 0 || this.et_funds_utilized_purchase_learning_teaching_material.getText().toString().length() > 0 || this.et_funds_utilized_purchase_health_disinfection_items.getText().toString().length() > 0 || this.et_funds_utilized_expenditure_ineligible_items.getText().toString().length() > 0 || this.et_total_funds_utilized_current_year.getText().toString().length() > 0 || this.et_comments.getText().toString().length() > 0) {
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

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        calculateTotal();
    }
}
