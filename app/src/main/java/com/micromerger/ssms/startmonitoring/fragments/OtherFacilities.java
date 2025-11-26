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
import android.widget.RelativeLayout;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class OtherFacilities extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RelativeLayout biology_lab_layout;
    Button cancel_button;
    RelativeLayout chemistry_lab_layout;
    EditText et_blackboards_repairable;
    EditText et_blackboards_working;
    EditText et_charts_repairable;
    EditText et_charts_working;
    EditText et_computers_repairable;
    EditText et_computers_working;
    EditText et_cupboards_repairable;
    EditText et_cupboards_working;
    EditText et_electric_fans_repairable;
    EditText et_electric_fans_working;
    EditText et_electric_motors_repairable;
    EditText et_electric_motors_working;
    EditText et_student_chairs_repairable;
    EditText et_student_chairs_working;
    EditText et_student_desks_student_benches_repairable;
    EditText et_student_desks_student_benches_working;
    EditText et_teacher_chairs_repairable;
    EditText et_teacher_chairs_working;
    EditText et_teacher_tables_repairable;
    EditText et_teacher_tables_working;
    EditText et_whiteboards_repairable;
    EditText et_whiteboards_working;
    RelativeLayout home_economic_lab_layout;
    View mView;
    RelativeLayout physics_lab_layout;
    RadioGroup rg_biology_lab;
    RadioGroup rg_chemistry_lab;
    RadioGroup rg_computer_lab;
    RadioGroup rg_home_economic_lab;
    RadioGroup rg_library;
    RadioGroup rg_medical_first_aid_box;
    RadioGroup rg_physics_lab;
    RadioGroup rg_play_ground;
    RadioGroup rg_solar_energy_system;
    RadioGroup rg_sports_equipment;
    Button save_button;
    Button skip_button;
    int computerLabId = -1;
    int physicsLabId = -1;
    int chemistryLabId = -1;
    int biologyLabId = -1;
    int homeEconomicLabId = -1;
    int libraryId = -1;
    int playGroundId = -1;
    int medicalFirstAidBoxId = -1;
    int sportsEquipmentId = -1;
    int solarEnergySystemId = -1;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_other_facilities, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws NumberFormatException {
        super.onViewCreated(view, savedInstanceState);
        this.physics_lab_layout = (RelativeLayout) view.findViewById(R.id.physics_lab_layout);
        this.chemistry_lab_layout = (RelativeLayout) view.findViewById(R.id.chemistry_lab_layout);
        this.biology_lab_layout = (RelativeLayout) view.findViewById(R.id.biology_lab_layout);
        this.home_economic_lab_layout = (RelativeLayout) view.findViewById(R.id.home_economic_lab_layout);
        this.et_blackboards_working = (EditText) view.findViewById(R.id.et_blackboards_working);
        this.et_blackboards_repairable = (EditText) view.findViewById(R.id.et_blackboards_repairable);
        this.et_student_chairs_working = (EditText) view.findViewById(R.id.et_student_chairs_working);
        this.et_student_chairs_repairable = (EditText) view.findViewById(R.id.et_student_chairs_repairable);
        this.et_student_desks_student_benches_working = (EditText) view.findViewById(R.id.et_student_desks_student_benches_working);
        this.et_student_desks_student_benches_repairable = (EditText) view.findViewById(R.id.et_student_desks_student_benches_repairable);
        this.et_teacher_chairs_working = (EditText) view.findViewById(R.id.et_teacher_chairs_working);
        this.et_teacher_chairs_repairable = (EditText) view.findViewById(R.id.et_teacher_chairs_repairable);
        this.et_teacher_tables_working = (EditText) view.findViewById(R.id.et_teacher_tables_working);
        this.et_teacher_tables_repairable = (EditText) view.findViewById(R.id.et_teacher_tables_repairable);
        this.et_electric_fans_working = (EditText) view.findViewById(R.id.et_electric_fans_working);
        this.et_electric_fans_repairable = (EditText) view.findViewById(R.id.et_electric_fans_repairable);
        this.et_cupboards_working = (EditText) view.findViewById(R.id.et_cupboards_working);
        this.et_cupboards_repairable = (EditText) view.findViewById(R.id.et_cupboards_repairable);
        this.et_charts_working = (EditText) view.findViewById(R.id.et_charts_working);
        this.et_charts_repairable = (EditText) view.findViewById(R.id.et_charts_repairable);
        this.et_computers_working = (EditText) view.findViewById(R.id.et_computers_working);
        this.et_computers_repairable = (EditText) view.findViewById(R.id.et_computers_repairable);
        this.et_electric_motors_working = (EditText) view.findViewById(R.id.et_electric_motors_working);
        this.et_electric_motors_repairable = (EditText) view.findViewById(R.id.et_electric_motors_repairable);
        this.et_whiteboards_working = (EditText) view.findViewById(R.id.et_whiteboards_working);
        this.et_whiteboards_repairable = (EditText) view.findViewById(R.id.et_whiteboards_repairable);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_computer_lab);
        this.rg_computer_lab = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_physics_lab);
        this.rg_physics_lab = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        RadioGroup radioGroup3 = (RadioGroup) view.findViewById(R.id.rg_chemistry_lab);
        this.rg_chemistry_lab = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(this);
        RadioGroup radioGroup4 = (RadioGroup) view.findViewById(R.id.rg_biology_lab);
        this.rg_biology_lab = radioGroup4;
        radioGroup4.setOnCheckedChangeListener(this);
        RadioGroup radioGroup5 = (RadioGroup) view.findViewById(R.id.rg_home_economic_lab);
        this.rg_home_economic_lab = radioGroup5;
        radioGroup5.setOnCheckedChangeListener(this);
        RadioGroup radioGroup6 = (RadioGroup) view.findViewById(R.id.rg_library);
        this.rg_library = radioGroup6;
        radioGroup6.setOnCheckedChangeListener(this);
        RadioGroup radioGroup7 = (RadioGroup) view.findViewById(R.id.rg_play_ground);
        this.rg_play_ground = radioGroup7;
        radioGroup7.setOnCheckedChangeListener(this);
        RadioGroup radioGroup8 = (RadioGroup) view.findViewById(R.id.rg_medical_first_aid_box);
        this.rg_medical_first_aid_box = radioGroup8;
        radioGroup8.setOnCheckedChangeListener(this);
        RadioGroup radioGroup9 = (RadioGroup) view.findViewById(R.id.rg_sports_equipment);
        this.rg_sports_equipment = radioGroup9;
        radioGroup9.setOnCheckedChangeListener(this);
        RadioGroup radioGroup10 = (RadioGroup) view.findViewById(R.id.rg_solar_energy_system);
        this.rg_solar_energy_system = radioGroup10;
        radioGroup10.setOnCheckedChangeListener(this);
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.skip_button);
        this.skip_button = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$OtherFacilities$8cNgGQa4S75e4YH5LIrinp0xD4Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$OtherFacilities(view2);
            }
        });
        hideLabsForPrimarySchool();
    }

    public /* synthetic */ void lambda$onViewCreated$0$OtherFacilities(View view) {
        skipAllFields();
    }

    private void hideLabsForPrimarySchool() throws NumberFormatException {
        List arrayList;
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$OtherFacilities$f90_ug8bBPd8UScnmzc0sqnsw28
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("SCHOOL_LEVEL");
            }
        });
        if (collectionSelect instanceof List) {
            arrayList = (List) collectionSelect;
        } else {
            arrayList = new ArrayList(collectionSelect);
        }
        int i = 0;
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_status_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().equalsIgnoreCase("Monitoring_School_Level")) {
                i = Integer.parseInt((String) value.getDataValue());
            }
        }
        if (String.valueOf(((ReferenceDataResponse.ReferenceData) arrayList.get(i)).getReferencekey().intValue()).equals("1")) {
            this.physics_lab_layout.setVisibility(8);
            this.chemistry_lab_layout.setVisibility(8);
            this.biology_lab_layout.setVisibility(8);
            this.home_economic_lab_layout.setVisibility(8);
            this.physicsLabId = 2;
            this.chemistryLabId = 2;
            this.biologyLabId = 2;
            this.homeEconomicLabId = 2;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.other_facilities.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Blackboards_Working)) {
                    this.et_blackboards_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Blackboards_Repairable)) {
                    this.et_blackboards_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Whiteboards_Working)) {
                    this.et_whiteboards_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Whiteboards_Repairable)) {
                    this.et_whiteboards_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Student_Chairs_Working)) {
                    this.et_student_chairs_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Student_Chairs_Repairable)) {
                    this.et_student_chairs_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Student_Desk_Working)) {
                    this.et_student_desks_student_benches_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Student_Desk_Repairable)) {
                    this.et_student_desks_student_benches_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Teacher_Chairs_Working)) {
                    this.et_teacher_chairs_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Teacher_Chairs_Repairable)) {
                    this.et_teacher_chairs_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Teacher_Tables_Working)) {
                    this.et_teacher_tables_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Teacher_Tables_Repairable)) {
                    this.et_teacher_tables_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Electric_Fans_Working)) {
                    this.et_electric_fans_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Electric_Fans_Repairable)) {
                    this.et_electric_fans_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Cupboards_Working)) {
                    this.et_cupboards_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Cupboards_Repairable)) {
                    this.et_cupboards_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Charts_Working)) {
                    this.et_charts_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Charts_Repairable)) {
                    this.et_charts_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Computers_Working)) {
                    this.et_computers_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Computers_Repairable)) {
                    this.et_computers_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Electric_Motors_Working)) {
                    this.et_electric_motors_working.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.Electric_Motors_Repairable)) {
                    this.et_electric_motors_repairable.setText((String) value.getDataValue());
                } else if (value.getKRAName().equals(Constant.ComputerLab)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.computerLabId = i;
                    ((RadioButton) this.rg_computer_lab.getChildAt(i)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.PhysicsLab)) {
                    int i2 = Integer.parseInt((String) value.getDataValue());
                    this.physicsLabId = i2;
                    ((RadioButton) this.rg_physics_lab.getChildAt(i2)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.ChemistryLab)) {
                    int i3 = Integer.parseInt((String) value.getDataValue());
                    this.chemistryLabId = i3;
                    ((RadioButton) this.rg_chemistry_lab.getChildAt(i3)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.BiologyLab)) {
                    int i4 = Integer.parseInt((String) value.getDataValue());
                    this.biologyLabId = i4;
                    ((RadioButton) this.rg_biology_lab.getChildAt(i4)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.HomeEconomicLab)) {
                    int i5 = Integer.parseInt((String) value.getDataValue());
                    this.homeEconomicLabId = i5;
                    ((RadioButton) this.rg_home_economic_lab.getChildAt(i5)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Library)) {
                    int i6 = Integer.parseInt((String) value.getDataValue());
                    this.libraryId = i6;
                    ((RadioButton) this.rg_library.getChildAt(i6)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.PlayGround)) {
                    int i7 = Integer.parseInt((String) value.getDataValue());
                    this.playGroundId = i7;
                    ((RadioButton) this.rg_play_ground.getChildAt(i7)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.MedicalFirstAidBox)) {
                    int i8 = Integer.parseInt((String) value.getDataValue());
                    this.medicalFirstAidBoxId = i8;
                    ((RadioButton) this.rg_medical_first_aid_box.getChildAt(i8)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.SportsEquipment)) {
                    int i9 = Integer.parseInt((String) value.getDataValue());
                    this.sportsEquipmentId = i9;
                    ((RadioButton) this.rg_sports_equipment.getChildAt(i9)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.SolarEnergySystem)) {
                    int i10 = Integer.parseInt((String) value.getDataValue());
                    this.solarEnergySystemId = i10;
                    ((RadioButton) this.rg_solar_energy_system.getChildAt(i10)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                }
            }
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.et_blackboards_working.setEnabled(false);
        this.et_blackboards_working.setFocusable(false);
        this.et_whiteboards_working.setEnabled(false);
        this.et_whiteboards_working.setFocusable(false);
        this.et_blackboards_repairable.setEnabled(false);
        this.et_blackboards_repairable.setFocusable(false);
        this.et_whiteboards_repairable.setEnabled(false);
        this.et_whiteboards_repairable.setFocusable(false);
        this.et_student_chairs_working.setEnabled(false);
        this.et_student_chairs_working.setFocusable(false);
        this.et_student_chairs_repairable.setEnabled(false);
        this.et_student_chairs_repairable.setFocusable(false);
        this.et_student_desks_student_benches_working.setEnabled(false);
        this.et_student_desks_student_benches_working.setFocusable(false);
        this.et_student_desks_student_benches_repairable.setEnabled(false);
        this.et_student_desks_student_benches_repairable.setFocusable(false);
        this.et_teacher_chairs_working.setEnabled(false);
        this.et_teacher_chairs_working.setFocusable(false);
        this.et_teacher_chairs_repairable.setEnabled(false);
        this.et_teacher_chairs_repairable.setFocusable(false);
        this.et_teacher_tables_working.setEnabled(false);
        this.et_teacher_tables_working.setFocusable(false);
        this.et_teacher_tables_repairable.setEnabled(false);
        this.et_teacher_tables_repairable.setFocusable(false);
        this.et_electric_fans_working.setEnabled(false);
        this.et_electric_fans_working.setFocusable(false);
        this.et_electric_fans_repairable.setEnabled(false);
        this.et_electric_fans_repairable.setFocusable(false);
        this.et_cupboards_working.setEnabled(false);
        this.et_cupboards_working.setFocusable(false);
        this.et_cupboards_repairable.setEnabled(false);
        this.et_cupboards_repairable.setFocusable(false);
        this.et_charts_working.setEnabled(false);
        this.et_charts_working.setFocusable(false);
        this.et_charts_repairable.setEnabled(false);
        this.et_charts_repairable.setFocusable(false);
        this.et_computers_working.setEnabled(false);
        this.et_computers_working.setFocusable(false);
        this.et_computers_repairable.setEnabled(false);
        this.et_computers_repairable.setFocusable(false);
        this.et_electric_motors_working.setEnabled(false);
        this.et_electric_motors_working.setFocusable(false);
        this.et_electric_motors_repairable.setEnabled(false);
        this.et_electric_motors_repairable.setFocusable(false);
        this.rg_computer_lab.setEnabled(false);
        this.rg_computer_lab.setFocusable(false);
        for (int i11 = 0; i11 < this.rg_computer_lab.getChildCount(); i11++) {
            ((RadioButton) this.rg_computer_lab.getChildAt(i11)).setFocusable(false);
            ((RadioButton) this.rg_computer_lab.getChildAt(i11)).setEnabled(false);
        }
        this.rg_physics_lab.setEnabled(false);
        this.rg_physics_lab.setFocusable(false);
        for (int i12 = 0; i12 < this.rg_physics_lab.getChildCount(); i12++) {
            ((RadioButton) this.rg_physics_lab.getChildAt(i12)).setFocusable(false);
            ((RadioButton) this.rg_physics_lab.getChildAt(i12)).setEnabled(false);
        }
        this.rg_chemistry_lab.setEnabled(false);
        this.rg_chemistry_lab.setFocusable(false);
        for (int i13 = 0; i13 < this.rg_chemistry_lab.getChildCount(); i13++) {
            ((RadioButton) this.rg_chemistry_lab.getChildAt(i13)).setFocusable(false);
            ((RadioButton) this.rg_chemistry_lab.getChildAt(i13)).setEnabled(false);
        }
        this.rg_biology_lab.setEnabled(false);
        this.rg_biology_lab.setFocusable(false);
        for (int i14 = 0; i14 < this.rg_biology_lab.getChildCount(); i14++) {
            ((RadioButton) this.rg_biology_lab.getChildAt(i14)).setFocusable(false);
            ((RadioButton) this.rg_biology_lab.getChildAt(i14)).setEnabled(false);
        }
        this.rg_home_economic_lab.setEnabled(false);
        this.rg_home_economic_lab.setFocusable(false);
        for (int i15 = 0; i15 < this.rg_home_economic_lab.getChildCount(); i15++) {
            ((RadioButton) this.rg_home_economic_lab.getChildAt(i15)).setFocusable(false);
            ((RadioButton) this.rg_home_economic_lab.getChildAt(i15)).setEnabled(false);
        }
        this.rg_library.setEnabled(false);
        this.rg_library.setFocusable(false);
        for (int i16 = 0; i16 < this.rg_library.getChildCount(); i16++) {
            ((RadioButton) this.rg_library.getChildAt(i16)).setFocusable(false);
            ((RadioButton) this.rg_library.getChildAt(i16)).setEnabled(false);
        }
        this.rg_play_ground.setEnabled(false);
        this.rg_play_ground.setFocusable(false);
        for (int i17 = 0; i17 < this.rg_play_ground.getChildCount(); i17++) {
            ((RadioButton) this.rg_play_ground.getChildAt(i17)).setFocusable(false);
            ((RadioButton) this.rg_play_ground.getChildAt(i17)).setEnabled(false);
        }
        this.rg_medical_first_aid_box.setEnabled(false);
        this.rg_medical_first_aid_box.setFocusable(false);
        for (int i18 = 0; i18 < this.rg_medical_first_aid_box.getChildCount(); i18++) {
            ((RadioButton) this.rg_medical_first_aid_box.getChildAt(i18)).setFocusable(false);
            ((RadioButton) this.rg_medical_first_aid_box.getChildAt(i18)).setEnabled(false);
        }
        this.rg_sports_equipment.setEnabled(false);
        this.rg_sports_equipment.setFocusable(false);
        for (int i19 = 0; i19 < this.rg_sports_equipment.getChildCount(); i19++) {
            ((RadioButton) this.rg_sports_equipment.getChildAt(i19)).setFocusable(false);
            ((RadioButton) this.rg_sports_equipment.getChildAt(i19)).setEnabled(false);
        }
        this.rg_solar_energy_system.setEnabled(false);
        this.rg_solar_energy_system.setFocusable(false);
        for (int i20 = 0; i20 < this.rg_solar_energy_system.getChildCount(); i20++) {
            ((RadioButton) this.rg_solar_energy_system.getChildAt(i20)).setFocusable(false);
            ((RadioButton) this.rg_solar_energy_system.getChildAt(i20)).setEnabled(false);
        }
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
            case R.id.rb_biology_lab_af /* 2131231488 */:
                this.biologyLabId = 0;
                break;
            case R.id.rb_biology_lab_anf /* 2131231489 */:
                this.biologyLabId = 1;
                break;
            case R.id.rb_biology_lab_na /* 2131231490 */:
                this.biologyLabId = 2;
                break;
            default:
                switch (i) {
                    case R.id.rb_chemistry_lab_af /* 2131231498 */:
                        this.chemistryLabId = 0;
                        break;
                    case R.id.rb_chemistry_lab_anf /* 2131231499 */:
                        this.chemistryLabId = 1;
                        break;
                    case R.id.rb_chemistry_lab_na /* 2131231500 */:
                        this.chemistryLabId = 2;
                        break;
                    default:
                        switch (i) {
                            case R.id.rb_computer_lab_af /* 2131231505 */:
                                this.computerLabId = 0;
                                break;
                            case R.id.rb_computer_lab_anf /* 2131231506 */:
                                this.computerLabId = 1;
                                break;
                            case R.id.rb_computer_lab_na /* 2131231507 */:
                                this.computerLabId = 2;
                                break;
                            default:
                                switch (i) {
                                    case R.id.rb_home_economic_lab_af /* 2131231528 */:
                                        this.homeEconomicLabId = 0;
                                        break;
                                    case R.id.rb_home_economic_lab_anf /* 2131231529 */:
                                        this.homeEconomicLabId = 1;
                                        break;
                                    case R.id.rb_home_economic_lab_na /* 2131231530 */:
                                        this.homeEconomicLabId = 2;
                                        break;
                                    default:
                                        switch (i) {
                                            case R.id.rb_library_af /* 2131231539 */:
                                                this.libraryId = 0;
                                                break;
                                            case R.id.rb_library_anf /* 2131231540 */:
                                                this.libraryId = 1;
                                                break;
                                            case R.id.rb_library_na /* 2131231541 */:
                                                this.libraryId = 2;
                                                break;
                                            case R.id.rb_medical_first_aid_box_af /* 2131231542 */:
                                                this.medicalFirstAidBoxId = 0;
                                                break;
                                            case R.id.rb_medical_first_aid_box_anf /* 2131231543 */:
                                                this.medicalFirstAidBoxId = 1;
                                                break;
                                            case R.id.rb_medical_first_aid_box_na /* 2131231544 */:
                                                this.medicalFirstAidBoxId = 2;
                                                break;
                                            default:
                                                switch (i) {
                                                    case R.id.rb_physics_lab_af /* 2131231554 */:
                                                        this.physicsLabId = 0;
                                                        break;
                                                    case R.id.rb_physics_lab_anf /* 2131231555 */:
                                                        this.physicsLabId = 1;
                                                        break;
                                                    case R.id.rb_physics_lab_na /* 2131231556 */:
                                                        this.physicsLabId = 2;
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case R.id.rb_play_ground_af /* 2131231561 */:
                                                                this.playGroundId = 0;
                                                                break;
                                                            case R.id.rb_play_ground_anf /* 2131231562 */:
                                                                this.playGroundId = 1;
                                                                break;
                                                            case R.id.rb_play_ground_na /* 2131231563 */:
                                                                this.playGroundId = 2;
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case R.id.rb_solar_energy_system_af /* 2131231584 */:
                                                                        this.solarEnergySystemId = 0;
                                                                        break;
                                                                    case R.id.rb_solar_energy_system_anf /* 2131231585 */:
                                                                        this.solarEnergySystemId = 1;
                                                                        break;
                                                                    case R.id.rb_solar_energy_system_na /* 2131231586 */:
                                                                        this.solarEnergySystemId = 2;
                                                                        break;
                                                                    case R.id.rb_sports_equipment_af /* 2131231587 */:
                                                                        this.sportsEquipmentId = 0;
                                                                        break;
                                                                    case R.id.rb_sports_equipment_anf /* 2131231588 */:
                                                                        this.sportsEquipmentId = 1;
                                                                        break;
                                                                    case R.id.rb_sports_equipment_na /* 2131231589 */:
                                                                        this.sportsEquipmentId = 2;
                                                                        break;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    private void skipAllFields() {
        if (this.et_blackboards_working.getText().toString().isEmpty()) {
            this.et_blackboards_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_blackboards_repairable.getText().toString().isEmpty()) {
            this.et_blackboards_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_whiteboards_working.getText().toString().isEmpty()) {
            this.et_whiteboards_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_whiteboards_repairable.getText().toString().isEmpty()) {
            this.et_whiteboards_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_student_chairs_working.getText().toString().isEmpty()) {
            this.et_student_chairs_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_student_chairs_repairable.getText().toString().isEmpty()) {
            this.et_student_chairs_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_student_desks_student_benches_working.getText().toString().isEmpty()) {
            this.et_student_desks_student_benches_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_student_desks_student_benches_repairable.getText().toString().isEmpty()) {
            this.et_student_desks_student_benches_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_teacher_chairs_working.getText().toString().isEmpty()) {
            this.et_teacher_chairs_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_teacher_chairs_repairable.getText().toString().isEmpty()) {
            this.et_teacher_chairs_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_teacher_tables_working.getText().toString().isEmpty()) {
            this.et_teacher_tables_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_teacher_tables_repairable.getText().toString().isEmpty()) {
            this.et_teacher_tables_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_electric_fans_working.getText().toString().isEmpty()) {
            this.et_electric_fans_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_electric_fans_repairable.getText().toString().isEmpty()) {
            this.et_electric_fans_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_cupboards_working.getText().toString().isEmpty()) {
            this.et_cupboards_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_cupboards_repairable.getText().toString().isEmpty()) {
            this.et_cupboards_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_charts_working.getText().toString().isEmpty()) {
            this.et_charts_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_charts_repairable.getText().toString().isEmpty()) {
            this.et_charts_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_computers_working.getText().toString().isEmpty()) {
            this.et_computers_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_computers_repairable.getText().toString().isEmpty()) {
            this.et_computers_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.et_electric_motors_working.getText().toString().isEmpty()) {
            this.et_electric_motors_working.setText(Constant.ECE_Katchi);
        }
        if (this.et_electric_motors_repairable.getText().toString().isEmpty()) {
            this.et_electric_motors_repairable.setText(Constant.ECE_Katchi);
        }
        if (this.computerLabId == -1) {
            this.computerLabId = 2;
        }
        if (this.physicsLabId == -1) {
            this.physicsLabId = 2;
        }
        if (this.chemistryLabId == -1) {
            this.chemistryLabId = 2;
        }
        if (this.biologyLabId == -1) {
            this.biologyLabId = 2;
        }
        if (this.homeEconomicLabId == -1) {
            this.homeEconomicLabId = 2;
        }
        if (this.libraryId == -1) {
            this.libraryId = 2;
        }
        if (this.playGroundId == -1) {
            this.playGroundId = 2;
        }
        if (this.medicalFirstAidBoxId == -1) {
            this.medicalFirstAidBoxId = 2;
        }
        if (this.sportsEquipmentId == -1) {
            this.sportsEquipmentId = 2;
        }
        if (this.solarEnergySystemId == -1) {
            this.solarEnergySystemId = 2;
        }
        checkValidation();
    }

    private void checkValidation() {
        if (this.et_blackboards_working.getText().toString().length() > 0 && this.et_blackboards_repairable.getText().toString().length() > 0 && this.et_whiteboards_working.getText().toString().length() > 0 && this.et_whiteboards_repairable.getText().toString().length() > 0 && this.et_student_chairs_working.getText().toString().length() > 0 && this.et_student_chairs_repairable.getText().toString().length() > 0 && this.et_student_desks_student_benches_working.getText().toString().length() > 0 && this.et_student_desks_student_benches_repairable.getText().toString().length() > 0 && this.et_teacher_chairs_working.getText().toString().length() > 0 && this.et_teacher_chairs_repairable.getText().toString().length() > 0 && this.et_teacher_tables_working.getText().toString().length() > 0 && this.et_teacher_tables_repairable.getText().toString().length() > 0 && this.et_electric_fans_working.getText().toString().length() > 0 && this.et_electric_fans_repairable.getText().toString().length() > 0 && this.et_cupboards_working.getText().toString().length() > 0 && this.et_cupboards_repairable.getText().toString().length() > 0 && this.et_charts_working.getText().toString().length() > 0 && this.et_charts_repairable.getText().toString().length() > 0 && this.et_computers_working.getText().toString().length() > 0 && this.et_computers_repairable.getText().toString().length() > 0 && this.et_electric_motors_working.getText().toString().length() > 0 && this.et_electric_motors_repairable.getText().toString().length() > 0 && this.computerLabId > -1 && this.physicsLabId > -1 && this.chemistryLabId > -1 && this.biologyLabId > -1 && this.homeEconomicLabId > -1 && this.libraryId > -1 && this.playGroundId > -1 && this.medicalFirstAidBoxId > -1 && this.sportsEquipmentId > -1 && this.solarEnergySystemId > -1) {
            saveFieldsData();
            return;
        }
        DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
        if (this.et_blackboards_working.getText().toString().equals("")) {
            showError(this.et_blackboards_working);
            return;
        }
        if (this.et_blackboards_repairable.getText().toString().equals("")) {
            showError(this.et_blackboards_repairable);
            return;
        }
        if (this.et_whiteboards_working.getText().toString().equals("")) {
            showError(this.et_whiteboards_working);
            return;
        }
        if (this.et_whiteboards_repairable.getText().toString().equals("")) {
            showError(this.et_whiteboards_repairable);
            return;
        }
        if (this.et_student_chairs_working.getText().toString().equals("")) {
            showError(this.et_student_chairs_working);
            return;
        }
        if (this.et_student_chairs_repairable.getText().toString().equals("")) {
            showError(this.et_student_chairs_repairable);
            return;
        }
        if (this.et_student_desks_student_benches_working.getText().toString().equals("")) {
            showError(this.et_student_desks_student_benches_working);
            return;
        }
        if (this.et_student_desks_student_benches_repairable.getText().toString().equals("")) {
            showError(this.et_student_desks_student_benches_repairable);
            return;
        }
        if (this.et_teacher_chairs_working.getText().toString().equals("")) {
            showError(this.et_teacher_chairs_working);
            return;
        }
        if (this.et_teacher_chairs_repairable.getText().toString().equals("")) {
            showError(this.et_teacher_chairs_repairable);
            return;
        }
        if (this.et_teacher_tables_working.getText().toString().equals("")) {
            showError(this.et_teacher_tables_working);
            return;
        }
        if (this.et_teacher_tables_repairable.getText().toString().equals("")) {
            showError(this.et_teacher_tables_repairable);
            return;
        }
        if (this.et_electric_fans_working.getText().toString().equals("")) {
            showError(this.et_electric_fans_working);
            return;
        }
        if (this.et_electric_fans_repairable.getText().toString().equals("")) {
            showError(this.et_electric_fans_repairable);
            return;
        }
        if (this.et_cupboards_working.getText().toString().equals("")) {
            showError(this.et_cupboards_working);
            return;
        }
        if (this.et_cupboards_repairable.getText().toString().equals("")) {
            showError(this.et_cupboards_repairable);
            return;
        }
        if (this.et_charts_working.getText().toString().equals("")) {
            showError(this.et_charts_working);
            return;
        }
        if (this.et_charts_repairable.getText().toString().equals("")) {
            showError(this.et_charts_repairable);
            return;
        }
        if (this.et_computers_working.getText().toString().equals("")) {
            showError(this.et_computers_working);
            return;
        }
        if (this.et_computers_repairable.getText().toString().equals("")) {
            showError(this.et_computers_repairable);
            return;
        }
        if (this.et_electric_motors_working.getText().toString().equals("")) {
            showError(this.et_electric_motors_working);
            return;
        }
        if (this.et_electric_motors_repairable.getText().toString().equals("")) {
            showError(this.et_electric_motors_repairable);
            return;
        }
        if (this.computerLabId < 0) {
            showError(this.rg_computer_lab);
            return;
        }
        if (this.physicsLabId < 0) {
            showError(this.rg_physics_lab);
            return;
        }
        if (this.chemistryLabId < 0) {
            showError(this.rg_chemistry_lab);
            return;
        }
        if (this.biologyLabId < 0) {
            showError(this.rg_biology_lab);
            return;
        }
        if (this.homeEconomicLabId < 0) {
            showError(this.rg_home_economic_lab);
            return;
        }
        if (this.libraryId < 0) {
            showError(this.rg_library);
            return;
        }
        if (this.playGroundId < 0) {
            showError(this.rg_play_ground);
            return;
        }
        if (this.medicalFirstAidBoxId < 0) {
            showError(this.rg_medical_first_aid_box);
        } else if (this.sportsEquipmentId < 0) {
            showError(this.rg_sports_equipment);
        } else if (this.solarEnergySystemId < 0) {
            showError(this.rg_solar_energy_system);
        }
    }

    private void showError(RadioGroup rg) {
        ((RadioButton) rg.getChildAt(0)).setError("Please fill the field.");
        rg.getParent().requestChildFocus(rg, rg);
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
        if (this.et_blackboards_working.getText().toString().length() > 0 || this.et_blackboards_repairable.getText().toString().length() > 0 || this.et_whiteboards_working.getText().toString().length() > 0 || this.et_whiteboards_repairable.getText().toString().length() > 0 || this.et_student_chairs_working.getText().toString().length() > 0 || this.et_student_chairs_repairable.getText().toString().length() > 0 || this.et_student_desks_student_benches_working.getText().toString().length() > 0 || this.et_student_desks_student_benches_repairable.getText().toString().length() > 0 || this.et_teacher_chairs_working.getText().toString().length() > 0 || this.et_teacher_chairs_repairable.getText().toString().length() > 0 || this.et_teacher_tables_working.getText().toString().length() > 0 || this.et_teacher_tables_repairable.getText().toString().length() > 0 || this.et_electric_fans_working.getText().toString().length() > 0 || this.et_electric_fans_repairable.getText().toString().length() > 0 || this.et_cupboards_working.getText().toString().length() > 0 || this.et_cupboards_repairable.getText().toString().length() > 0 || this.et_charts_working.getText().toString().length() > 0 || this.et_charts_repairable.getText().toString().length() > 0 || this.et_computers_working.getText().toString().length() > 0 || this.et_computers_repairable.getText().toString().length() > 0 || this.et_electric_motors_working.getText().toString().length() > 0 || this.et_electric_motors_repairable.getText().toString().length() > 0 || this.computerLabId > -1 || this.physicsLabId > -1 || this.chemistryLabId > -1 || this.biologyLabId > -1 || this.homeEconomicLabId > -1 || this.libraryId > -1 || this.playGroundId > -1 || this.medicalFirstAidBoxId > -1 || this.sportsEquipmentId > -1 || this.solarEnergySystemId > -1) {
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
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.OtherFacilities.1
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.other_facilities.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Blackboards_Working)) {
                        value.setDataValue(OtherFacilities.this.et_blackboards_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Blackboards_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_blackboards_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Whiteboards_Working)) {
                        value.setDataValue(OtherFacilities.this.et_whiteboards_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Whiteboards_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_whiteboards_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Student_Chairs_Working)) {
                        value.setDataValue(OtherFacilities.this.et_student_chairs_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Student_Chairs_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_student_chairs_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Student_Desk_Working)) {
                        value.setDataValue(OtherFacilities.this.et_student_desks_student_benches_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Student_Desk_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_student_desks_student_benches_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Teacher_Chairs_Working)) {
                        value.setDataValue(OtherFacilities.this.et_teacher_chairs_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Teacher_Chairs_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_teacher_chairs_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Teacher_Tables_Working)) {
                        value.setDataValue(OtherFacilities.this.et_teacher_tables_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Teacher_Tables_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_teacher_tables_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Electric_Fans_Working)) {
                        value.setDataValue(OtherFacilities.this.et_electric_fans_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Electric_Fans_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_electric_fans_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Cupboards_Working)) {
                        value.setDataValue(OtherFacilities.this.et_cupboards_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Cupboards_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_cupboards_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Charts_Working)) {
                        value.setDataValue(OtherFacilities.this.et_charts_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Charts_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_charts_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Computers_Working)) {
                        value.setDataValue(OtherFacilities.this.et_computers_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Computers_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_computers_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Electric_Motors_Working)) {
                        value.setDataValue(OtherFacilities.this.et_electric_motors_working.getText().toString());
                    } else if (value.getKRAName().equals(Constant.Electric_Motors_Repairable)) {
                        value.setDataValue(OtherFacilities.this.et_electric_motors_repairable.getText().toString());
                    } else if (value.getKRAName().equals(Constant.ComputerLab)) {
                        if (OtherFacilities.this.computerLabId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.computerLabId));
                        }
                    } else if (value.getKRAName().equals(Constant.PhysicsLab)) {
                        if (OtherFacilities.this.physicsLabId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.physicsLabId));
                        }
                    } else if (value.getKRAName().equals(Constant.ChemistryLab)) {
                        if (OtherFacilities.this.chemistryLabId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.chemistryLabId));
                        }
                    } else if (value.getKRAName().equals(Constant.BiologyLab)) {
                        if (OtherFacilities.this.biologyLabId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.biologyLabId));
                        }
                    } else if (value.getKRAName().equals(Constant.HomeEconomicLab)) {
                        if (OtherFacilities.this.homeEconomicLabId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.homeEconomicLabId));
                        }
                    } else if (value.getKRAName().equals(Constant.Library)) {
                        if (OtherFacilities.this.libraryId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.libraryId));
                        }
                    } else if (value.getKRAName().equals(Constant.PlayGround)) {
                        if (OtherFacilities.this.playGroundId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.playGroundId));
                        }
                    } else if (value.getKRAName().equals(Constant.MedicalFirstAidBox)) {
                        if (OtherFacilities.this.medicalFirstAidBoxId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.medicalFirstAidBoxId));
                        }
                    } else if (value.getKRAName().equals(Constant.SportsEquipment)) {
                        if (OtherFacilities.this.sportsEquipmentId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.sportsEquipmentId));
                        }
                    } else if (value.getKRAName().equals(Constant.SolarEnergySystem)) {
                        if (OtherFacilities.this.solarEnergySystemId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(OtherFacilities.this.solarEnergySystemId));
                        }
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
                        CommonActions.getDbHandler(OtherFacilities.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isOtherFacilitiesDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }
}
