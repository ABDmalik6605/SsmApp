package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusOtherFacilitiesBinding implements ViewBinding {
    public final RelativeLayout biologyLabLayout;
    public final Button cancelButton;
    public final RelativeLayout chemistryLabLayout;
    public final EditText etBlackboardsRepairable;
    public final EditText etBlackboardsWorking;
    public final EditText etChartsRepairable;
    public final EditText etChartsWorking;
    public final EditText etComputersRepairable;
    public final EditText etComputersWorking;
    public final EditText etCupboardsRepairable;
    public final EditText etCupboardsWorking;
    public final EditText etElectricFansRepairable;
    public final EditText etElectricFansWorking;
    public final EditText etElectricMotorsRepairable;
    public final EditText etElectricMotorsWorking;
    public final EditText etStudentChairsRepairable;
    public final EditText etStudentChairsWorking;
    public final EditText etStudentDesksStudentBenchesRepairable;
    public final EditText etStudentDesksStudentBenchesWorking;
    public final EditText etTeacherChairsRepairable;
    public final EditText etTeacherChairsWorking;
    public final EditText etTeacherTablesRepairable;
    public final EditText etTeacherTablesWorking;
    public final EditText etWhiteboardsRepairable;
    public final EditText etWhiteboardsWorking;
    public final RelativeLayout homeEconomicLabLayout;
    public final RelativeLayout parentView;
    public final RelativeLayout physicsLabLayout;
    public final RadioButton rbBiologyLabAf;
    public final RadioButton rbBiologyLabAnf;
    public final RadioButton rbBiologyLabNa;
    public final RadioButton rbChemistryLabAf;
    public final RadioButton rbChemistryLabAnf;
    public final RadioButton rbChemistryLabNa;
    public final RadioButton rbComputerLabAf;
    public final RadioButton rbComputerLabAnf;
    public final RadioButton rbComputerLabNa;
    public final RadioButton rbHomeEconomicLabAf;
    public final RadioButton rbHomeEconomicLabAnf;
    public final RadioButton rbHomeEconomicLabNa;
    public final RadioButton rbLibraryAf;
    public final RadioButton rbLibraryAnf;
    public final RadioButton rbLibraryNa;
    public final RadioButton rbMedicalFirstAidBoxAf;
    public final RadioButton rbMedicalFirstAidBoxAnf;
    public final RadioButton rbMedicalFirstAidBoxNa;
    public final RadioButton rbPhysicsLabAf;
    public final RadioButton rbPhysicsLabAnf;
    public final RadioButton rbPhysicsLabNa;
    public final RadioButton rbPlayGroundAf;
    public final RadioButton rbPlayGroundAnf;
    public final RadioButton rbPlayGroundNa;
    public final RadioButton rbSolarEnergySystemAf;
    public final RadioButton rbSolarEnergySystemAnf;
    public final RadioButton rbSolarEnergySystemNa;
    public final RadioButton rbSportsEquipmentAf;
    public final RadioButton rbSportsEquipmentAnf;
    public final RadioButton rbSportsEquipmentNa;
    public final RadioGroup rgBiologyLab;
    public final RadioGroup rgChemistryLab;
    public final RadioGroup rgComputerLab;
    public final RadioGroup rgHomeEconomicLab;
    public final RadioGroup rgLibrary;
    public final RadioGroup rgMedicalFirstAidBox;
    public final RadioGroup rgPhysicsLab;
    public final RadioGroup rgPlayGround;
    public final RadioGroup rgSolarEnergySystem;
    public final RadioGroup rgSportsEquipment;
    private final RelativeLayout rootView;
    public final Button saveButton;
    public final Button skipButton;
    public final TextView tvBiologyLab;
    public final TextView tvBlackboardsRepairable;
    public final TextView tvBlackboardsWorking;
    public final TextView tvChartsRepairable;
    public final TextView tvChartsWorking;
    public final TextView tvChemistryLab;
    public final TextView tvComputerLab;
    public final TextView tvComputersRepairable;
    public final TextView tvComputersWorking;
    public final TextView tvCupboardsRepairable;
    public final TextView tvCupboardsWorking;
    public final TextView tvElectricFansRepairable;
    public final TextView tvElectricFansWorking;
    public final TextView tvElectricMotorsRepairable;
    public final TextView tvElectricMotorsWorking;
    public final TextView tvHomeEconomicLab;
    public final TextView tvLibrary;
    public final TextView tvMedicalFirstAidBox;
    public final TextView tvPhysicsLab;
    public final TextView tvPlayGround;
    public final TextView tvSolarEnergySystem;
    public final TextView tvSportsEquipment;
    public final TextView tvStudentChairsRepairable;
    public final TextView tvStudentChairsWorking;
    public final TextView tvStudentDesksStudentBenchesRepairable;
    public final TextView tvStudentDesksStudentBenchesWorking;
    public final TextView tvTeacherChairsRepairable;
    public final TextView tvTeacherChairsWorking;
    public final TextView tvTeacherTablesRepairable;
    public final TextView tvTeacherTablesWorking;
    public final TextView tvWhiteboardsRepairable;
    public final TextView tvWhiteboardsWorking;

    private FragmentCensusOtherFacilitiesBinding(RelativeLayout rootView, RelativeLayout biologyLabLayout, Button cancelButton, RelativeLayout chemistryLabLayout, EditText etBlackboardsRepairable, EditText etBlackboardsWorking, EditText etChartsRepairable, EditText etChartsWorking, EditText etComputersRepairable, EditText etComputersWorking, EditText etCupboardsRepairable, EditText etCupboardsWorking, EditText etElectricFansRepairable, EditText etElectricFansWorking, EditText etElectricMotorsRepairable, EditText etElectricMotorsWorking, EditText etStudentChairsRepairable, EditText etStudentChairsWorking, EditText etStudentDesksStudentBenchesRepairable, EditText etStudentDesksStudentBenchesWorking, EditText etTeacherChairsRepairable, EditText etTeacherChairsWorking, EditText etTeacherTablesRepairable, EditText etTeacherTablesWorking, EditText etWhiteboardsRepairable, EditText etWhiteboardsWorking, RelativeLayout homeEconomicLabLayout, RelativeLayout parentView, RelativeLayout physicsLabLayout, RadioButton rbBiologyLabAf, RadioButton rbBiologyLabAnf, RadioButton rbBiologyLabNa, RadioButton rbChemistryLabAf, RadioButton rbChemistryLabAnf, RadioButton rbChemistryLabNa, RadioButton rbComputerLabAf, RadioButton rbComputerLabAnf, RadioButton rbComputerLabNa, RadioButton rbHomeEconomicLabAf, RadioButton rbHomeEconomicLabAnf, RadioButton rbHomeEconomicLabNa, RadioButton rbLibraryAf, RadioButton rbLibraryAnf, RadioButton rbLibraryNa, RadioButton rbMedicalFirstAidBoxAf, RadioButton rbMedicalFirstAidBoxAnf, RadioButton rbMedicalFirstAidBoxNa, RadioButton rbPhysicsLabAf, RadioButton rbPhysicsLabAnf, RadioButton rbPhysicsLabNa, RadioButton rbPlayGroundAf, RadioButton rbPlayGroundAnf, RadioButton rbPlayGroundNa, RadioButton rbSolarEnergySystemAf, RadioButton rbSolarEnergySystemAnf, RadioButton rbSolarEnergySystemNa, RadioButton rbSportsEquipmentAf, RadioButton rbSportsEquipmentAnf, RadioButton rbSportsEquipmentNa, RadioGroup rgBiologyLab, RadioGroup rgChemistryLab, RadioGroup rgComputerLab, RadioGroup rgHomeEconomicLab, RadioGroup rgLibrary, RadioGroup rgMedicalFirstAidBox, RadioGroup rgPhysicsLab, RadioGroup rgPlayGround, RadioGroup rgSolarEnergySystem, RadioGroup rgSportsEquipment, Button saveButton, Button skipButton, TextView tvBiologyLab, TextView tvBlackboardsRepairable, TextView tvBlackboardsWorking, TextView tvChartsRepairable, TextView tvChartsWorking, TextView tvChemistryLab, TextView tvComputerLab, TextView tvComputersRepairable, TextView tvComputersWorking, TextView tvCupboardsRepairable, TextView tvCupboardsWorking, TextView tvElectricFansRepairable, TextView tvElectricFansWorking, TextView tvElectricMotorsRepairable, TextView tvElectricMotorsWorking, TextView tvHomeEconomicLab, TextView tvLibrary, TextView tvMedicalFirstAidBox, TextView tvPhysicsLab, TextView tvPlayGround, TextView tvSolarEnergySystem, TextView tvSportsEquipment, TextView tvStudentChairsRepairable, TextView tvStudentChairsWorking, TextView tvStudentDesksStudentBenchesRepairable, TextView tvStudentDesksStudentBenchesWorking, TextView tvTeacherChairsRepairable, TextView tvTeacherChairsWorking, TextView tvTeacherTablesRepairable, TextView tvTeacherTablesWorking, TextView tvWhiteboardsRepairable, TextView tvWhiteboardsWorking) {
        this.rootView = rootView;
        this.biologyLabLayout = biologyLabLayout;
        this.cancelButton = cancelButton;
        this.chemistryLabLayout = chemistryLabLayout;
        this.etBlackboardsRepairable = etBlackboardsRepairable;
        this.etBlackboardsWorking = etBlackboardsWorking;
        this.etChartsRepairable = etChartsRepairable;
        this.etChartsWorking = etChartsWorking;
        this.etComputersRepairable = etComputersRepairable;
        this.etComputersWorking = etComputersWorking;
        this.etCupboardsRepairable = etCupboardsRepairable;
        this.etCupboardsWorking = etCupboardsWorking;
        this.etElectricFansRepairable = etElectricFansRepairable;
        this.etElectricFansWorking = etElectricFansWorking;
        this.etElectricMotorsRepairable = etElectricMotorsRepairable;
        this.etElectricMotorsWorking = etElectricMotorsWorking;
        this.etStudentChairsRepairable = etStudentChairsRepairable;
        this.etStudentChairsWorking = etStudentChairsWorking;
        this.etStudentDesksStudentBenchesRepairable = etStudentDesksStudentBenchesRepairable;
        this.etStudentDesksStudentBenchesWorking = etStudentDesksStudentBenchesWorking;
        this.etTeacherChairsRepairable = etTeacherChairsRepairable;
        this.etTeacherChairsWorking = etTeacherChairsWorking;
        this.etTeacherTablesRepairable = etTeacherTablesRepairable;
        this.etTeacherTablesWorking = etTeacherTablesWorking;
        this.etWhiteboardsRepairable = etWhiteboardsRepairable;
        this.etWhiteboardsWorking = etWhiteboardsWorking;
        this.homeEconomicLabLayout = homeEconomicLabLayout;
        this.parentView = parentView;
        this.physicsLabLayout = physicsLabLayout;
        this.rbBiologyLabAf = rbBiologyLabAf;
        this.rbBiologyLabAnf = rbBiologyLabAnf;
        this.rbBiologyLabNa = rbBiologyLabNa;
        this.rbChemistryLabAf = rbChemistryLabAf;
        this.rbChemistryLabAnf = rbChemistryLabAnf;
        this.rbChemistryLabNa = rbChemistryLabNa;
        this.rbComputerLabAf = rbComputerLabAf;
        this.rbComputerLabAnf = rbComputerLabAnf;
        this.rbComputerLabNa = rbComputerLabNa;
        this.rbHomeEconomicLabAf = rbHomeEconomicLabAf;
        this.rbHomeEconomicLabAnf = rbHomeEconomicLabAnf;
        this.rbHomeEconomicLabNa = rbHomeEconomicLabNa;
        this.rbLibraryAf = rbLibraryAf;
        this.rbLibraryAnf = rbLibraryAnf;
        this.rbLibraryNa = rbLibraryNa;
        this.rbMedicalFirstAidBoxAf = rbMedicalFirstAidBoxAf;
        this.rbMedicalFirstAidBoxAnf = rbMedicalFirstAidBoxAnf;
        this.rbMedicalFirstAidBoxNa = rbMedicalFirstAidBoxNa;
        this.rbPhysicsLabAf = rbPhysicsLabAf;
        this.rbPhysicsLabAnf = rbPhysicsLabAnf;
        this.rbPhysicsLabNa = rbPhysicsLabNa;
        this.rbPlayGroundAf = rbPlayGroundAf;
        this.rbPlayGroundAnf = rbPlayGroundAnf;
        this.rbPlayGroundNa = rbPlayGroundNa;
        this.rbSolarEnergySystemAf = rbSolarEnergySystemAf;
        this.rbSolarEnergySystemAnf = rbSolarEnergySystemAnf;
        this.rbSolarEnergySystemNa = rbSolarEnergySystemNa;
        this.rbSportsEquipmentAf = rbSportsEquipmentAf;
        this.rbSportsEquipmentAnf = rbSportsEquipmentAnf;
        this.rbSportsEquipmentNa = rbSportsEquipmentNa;
        this.rgBiologyLab = rgBiologyLab;
        this.rgChemistryLab = rgChemistryLab;
        this.rgComputerLab = rgComputerLab;
        this.rgHomeEconomicLab = rgHomeEconomicLab;
        this.rgLibrary = rgLibrary;
        this.rgMedicalFirstAidBox = rgMedicalFirstAidBox;
        this.rgPhysicsLab = rgPhysicsLab;
        this.rgPlayGround = rgPlayGround;
        this.rgSolarEnergySystem = rgSolarEnergySystem;
        this.rgSportsEquipment = rgSportsEquipment;
        this.saveButton = saveButton;
        this.skipButton = skipButton;
        this.tvBiologyLab = tvBiologyLab;
        this.tvBlackboardsRepairable = tvBlackboardsRepairable;
        this.tvBlackboardsWorking = tvBlackboardsWorking;
        this.tvChartsRepairable = tvChartsRepairable;
        this.tvChartsWorking = tvChartsWorking;
        this.tvChemistryLab = tvChemistryLab;
        this.tvComputerLab = tvComputerLab;
        this.tvComputersRepairable = tvComputersRepairable;
        this.tvComputersWorking = tvComputersWorking;
        this.tvCupboardsRepairable = tvCupboardsRepairable;
        this.tvCupboardsWorking = tvCupboardsWorking;
        this.tvElectricFansRepairable = tvElectricFansRepairable;
        this.tvElectricFansWorking = tvElectricFansWorking;
        this.tvElectricMotorsRepairable = tvElectricMotorsRepairable;
        this.tvElectricMotorsWorking = tvElectricMotorsWorking;
        this.tvHomeEconomicLab = tvHomeEconomicLab;
        this.tvLibrary = tvLibrary;
        this.tvMedicalFirstAidBox = tvMedicalFirstAidBox;
        this.tvPhysicsLab = tvPhysicsLab;
        this.tvPlayGround = tvPlayGround;
        this.tvSolarEnergySystem = tvSolarEnergySystem;
        this.tvSportsEquipment = tvSportsEquipment;
        this.tvStudentChairsRepairable = tvStudentChairsRepairable;
        this.tvStudentChairsWorking = tvStudentChairsWorking;
        this.tvStudentDesksStudentBenchesRepairable = tvStudentDesksStudentBenchesRepairable;
        this.tvStudentDesksStudentBenchesWorking = tvStudentDesksStudentBenchesWorking;
        this.tvTeacherChairsRepairable = tvTeacherChairsRepairable;
        this.tvTeacherChairsWorking = tvTeacherChairsWorking;
        this.tvTeacherTablesRepairable = tvTeacherTablesRepairable;
        this.tvTeacherTablesWorking = tvTeacherTablesWorking;
        this.tvWhiteboardsRepairable = tvWhiteboardsRepairable;
        this.tvWhiteboardsWorking = tvWhiteboardsWorking;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusOtherFacilitiesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusOtherFacilitiesBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_other_facilities, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusOtherFacilitiesBinding bind(View rootView) {
        int i = R.id.biology_lab_layout;
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.biology_lab_layout);
        if (relativeLayout != null) {
            i = R.id.cancel_button;
            Button button = (Button) rootView.findViewById(R.id.cancel_button);
            if (button != null) {
                i = R.id.chemistry_lab_layout;
                RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.chemistry_lab_layout);
                if (relativeLayout2 != null) {
                    i = R.id.et_blackboards_repairable;
                    EditText editText = (EditText) rootView.findViewById(R.id.et_blackboards_repairable);
                    if (editText != null) {
                        i = R.id.et_blackboards_working;
                        EditText editText2 = (EditText) rootView.findViewById(R.id.et_blackboards_working);
                        if (editText2 != null) {
                            i = R.id.et_charts_repairable;
                            EditText editText3 = (EditText) rootView.findViewById(R.id.et_charts_repairable);
                            if (editText3 != null) {
                                i = R.id.et_charts_working;
                                EditText editText4 = (EditText) rootView.findViewById(R.id.et_charts_working);
                                if (editText4 != null) {
                                    i = R.id.et_computers_repairable;
                                    EditText editText5 = (EditText) rootView.findViewById(R.id.et_computers_repairable);
                                    if (editText5 != null) {
                                        i = R.id.et_computers_working;
                                        EditText editText6 = (EditText) rootView.findViewById(R.id.et_computers_working);
                                        if (editText6 != null) {
                                            i = R.id.et_cupboards_repairable;
                                            EditText editText7 = (EditText) rootView.findViewById(R.id.et_cupboards_repairable);
                                            if (editText7 != null) {
                                                i = R.id.et_cupboards_working;
                                                EditText editText8 = (EditText) rootView.findViewById(R.id.et_cupboards_working);
                                                if (editText8 != null) {
                                                    i = R.id.et_electric_fans_repairable;
                                                    EditText editText9 = (EditText) rootView.findViewById(R.id.et_electric_fans_repairable);
                                                    if (editText9 != null) {
                                                        i = R.id.et_electric_fans_working;
                                                        EditText editText10 = (EditText) rootView.findViewById(R.id.et_electric_fans_working);
                                                        if (editText10 != null) {
                                                            i = R.id.et_electric_motors_repairable;
                                                            EditText editText11 = (EditText) rootView.findViewById(R.id.et_electric_motors_repairable);
                                                            if (editText11 != null) {
                                                                i = R.id.et_electric_motors_working;
                                                                EditText editText12 = (EditText) rootView.findViewById(R.id.et_electric_motors_working);
                                                                if (editText12 != null) {
                                                                    i = R.id.et_student_chairs_repairable;
                                                                    EditText editText13 = (EditText) rootView.findViewById(R.id.et_student_chairs_repairable);
                                                                    if (editText13 != null) {
                                                                        i = R.id.et_student_chairs_working;
                                                                        EditText editText14 = (EditText) rootView.findViewById(R.id.et_student_chairs_working);
                                                                        if (editText14 != null) {
                                                                            i = R.id.et_student_desks_student_benches_repairable;
                                                                            EditText editText15 = (EditText) rootView.findViewById(R.id.et_student_desks_student_benches_repairable);
                                                                            if (editText15 != null) {
                                                                                i = R.id.et_student_desks_student_benches_working;
                                                                                EditText editText16 = (EditText) rootView.findViewById(R.id.et_student_desks_student_benches_working);
                                                                                if (editText16 != null) {
                                                                                    i = R.id.et_teacher_chairs_repairable;
                                                                                    EditText editText17 = (EditText) rootView.findViewById(R.id.et_teacher_chairs_repairable);
                                                                                    if (editText17 != null) {
                                                                                        i = R.id.et_teacher_chairs_working;
                                                                                        EditText editText18 = (EditText) rootView.findViewById(R.id.et_teacher_chairs_working);
                                                                                        if (editText18 != null) {
                                                                                            i = R.id.et_teacher_tables_repairable;
                                                                                            EditText editText19 = (EditText) rootView.findViewById(R.id.et_teacher_tables_repairable);
                                                                                            if (editText19 != null) {
                                                                                                i = R.id.et_teacher_tables_working;
                                                                                                EditText editText20 = (EditText) rootView.findViewById(R.id.et_teacher_tables_working);
                                                                                                if (editText20 != null) {
                                                                                                    i = R.id.et_whiteboards_repairable;
                                                                                                    EditText editText21 = (EditText) rootView.findViewById(R.id.et_whiteboards_repairable);
                                                                                                    if (editText21 != null) {
                                                                                                        i = R.id.et_whiteboards_working;
                                                                                                        EditText editText22 = (EditText) rootView.findViewById(R.id.et_whiteboards_working);
                                                                                                        if (editText22 != null) {
                                                                                                            i = R.id.home_economic_lab_layout;
                                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.home_economic_lab_layout);
                                                                                                            if (relativeLayout3 != null) {
                                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) rootView;
                                                                                                                i = R.id.physics_lab_layout;
                                                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.physics_lab_layout);
                                                                                                                if (relativeLayout5 != null) {
                                                                                                                    i = R.id.rb_biology_lab_af;
                                                                                                                    RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_biology_lab_af);
                                                                                                                    if (radioButton != null) {
                                                                                                                        i = R.id.rb_biology_lab_anf;
                                                                                                                        RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_biology_lab_anf);
                                                                                                                        if (radioButton2 != null) {
                                                                                                                            i = R.id.rb_biology_lab_na;
                                                                                                                            RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_biology_lab_na);
                                                                                                                            if (radioButton3 != null) {
                                                                                                                                i = R.id.rb_chemistry_lab_af;
                                                                                                                                RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_chemistry_lab_af);
                                                                                                                                if (radioButton4 != null) {
                                                                                                                                    i = R.id.rb_chemistry_lab_anf;
                                                                                                                                    RadioButton radioButton5 = (RadioButton) rootView.findViewById(R.id.rb_chemistry_lab_anf);
                                                                                                                                    if (radioButton5 != null) {
                                                                                                                                        i = R.id.rb_chemistry_lab_na;
                                                                                                                                        RadioButton radioButton6 = (RadioButton) rootView.findViewById(R.id.rb_chemistry_lab_na);
                                                                                                                                        if (radioButton6 != null) {
                                                                                                                                            i = R.id.rb_computer_lab_af;
                                                                                                                                            RadioButton radioButton7 = (RadioButton) rootView.findViewById(R.id.rb_computer_lab_af);
                                                                                                                                            if (radioButton7 != null) {
                                                                                                                                                i = R.id.rb_computer_lab_anf;
                                                                                                                                                RadioButton radioButton8 = (RadioButton) rootView.findViewById(R.id.rb_computer_lab_anf);
                                                                                                                                                if (radioButton8 != null) {
                                                                                                                                                    i = R.id.rb_computer_lab_na;
                                                                                                                                                    RadioButton radioButton9 = (RadioButton) rootView.findViewById(R.id.rb_computer_lab_na);
                                                                                                                                                    if (radioButton9 != null) {
                                                                                                                                                        i = R.id.rb_home_economic_lab_af;
                                                                                                                                                        RadioButton radioButton10 = (RadioButton) rootView.findViewById(R.id.rb_home_economic_lab_af);
                                                                                                                                                        if (radioButton10 != null) {
                                                                                                                                                            i = R.id.rb_home_economic_lab_anf;
                                                                                                                                                            RadioButton radioButton11 = (RadioButton) rootView.findViewById(R.id.rb_home_economic_lab_anf);
                                                                                                                                                            if (radioButton11 != null) {
                                                                                                                                                                i = R.id.rb_home_economic_lab_na;
                                                                                                                                                                RadioButton radioButton12 = (RadioButton) rootView.findViewById(R.id.rb_home_economic_lab_na);
                                                                                                                                                                if (radioButton12 != null) {
                                                                                                                                                                    i = R.id.rb_library_af;
                                                                                                                                                                    RadioButton radioButton13 = (RadioButton) rootView.findViewById(R.id.rb_library_af);
                                                                                                                                                                    if (radioButton13 != null) {
                                                                                                                                                                        i = R.id.rb_library_anf;
                                                                                                                                                                        RadioButton radioButton14 = (RadioButton) rootView.findViewById(R.id.rb_library_anf);
                                                                                                                                                                        if (radioButton14 != null) {
                                                                                                                                                                            i = R.id.rb_library_na;
                                                                                                                                                                            RadioButton radioButton15 = (RadioButton) rootView.findViewById(R.id.rb_library_na);
                                                                                                                                                                            if (radioButton15 != null) {
                                                                                                                                                                                i = R.id.rb_medical_first_aid_box_af;
                                                                                                                                                                                RadioButton radioButton16 = (RadioButton) rootView.findViewById(R.id.rb_medical_first_aid_box_af);
                                                                                                                                                                                if (radioButton16 != null) {
                                                                                                                                                                                    i = R.id.rb_medical_first_aid_box_anf;
                                                                                                                                                                                    RadioButton radioButton17 = (RadioButton) rootView.findViewById(R.id.rb_medical_first_aid_box_anf);
                                                                                                                                                                                    if (radioButton17 != null) {
                                                                                                                                                                                        i = R.id.rb_medical_first_aid_box_na;
                                                                                                                                                                                        RadioButton radioButton18 = (RadioButton) rootView.findViewById(R.id.rb_medical_first_aid_box_na);
                                                                                                                                                                                        if (radioButton18 != null) {
                                                                                                                                                                                            i = R.id.rb_physics_lab_af;
                                                                                                                                                                                            RadioButton radioButton19 = (RadioButton) rootView.findViewById(R.id.rb_physics_lab_af);
                                                                                                                                                                                            if (radioButton19 != null) {
                                                                                                                                                                                                i = R.id.rb_physics_lab_anf;
                                                                                                                                                                                                RadioButton radioButton20 = (RadioButton) rootView.findViewById(R.id.rb_physics_lab_anf);
                                                                                                                                                                                                if (radioButton20 != null) {
                                                                                                                                                                                                    i = R.id.rb_physics_lab_na;
                                                                                                                                                                                                    RadioButton radioButton21 = (RadioButton) rootView.findViewById(R.id.rb_physics_lab_na);
                                                                                                                                                                                                    if (radioButton21 != null) {
                                                                                                                                                                                                        i = R.id.rb_play_ground_af;
                                                                                                                                                                                                        RadioButton radioButton22 = (RadioButton) rootView.findViewById(R.id.rb_play_ground_af);
                                                                                                                                                                                                        if (radioButton22 != null) {
                                                                                                                                                                                                            i = R.id.rb_play_ground_anf;
                                                                                                                                                                                                            RadioButton radioButton23 = (RadioButton) rootView.findViewById(R.id.rb_play_ground_anf);
                                                                                                                                                                                                            if (radioButton23 != null) {
                                                                                                                                                                                                                i = R.id.rb_play_ground_na;
                                                                                                                                                                                                                RadioButton radioButton24 = (RadioButton) rootView.findViewById(R.id.rb_play_ground_na);
                                                                                                                                                                                                                if (radioButton24 != null) {
                                                                                                                                                                                                                    i = R.id.rb_solar_energy_system_af;
                                                                                                                                                                                                                    RadioButton radioButton25 = (RadioButton) rootView.findViewById(R.id.rb_solar_energy_system_af);
                                                                                                                                                                                                                    if (radioButton25 != null) {
                                                                                                                                                                                                                        i = R.id.rb_solar_energy_system_anf;
                                                                                                                                                                                                                        RadioButton radioButton26 = (RadioButton) rootView.findViewById(R.id.rb_solar_energy_system_anf);
                                                                                                                                                                                                                        if (radioButton26 != null) {
                                                                                                                                                                                                                            i = R.id.rb_solar_energy_system_na;
                                                                                                                                                                                                                            RadioButton radioButton27 = (RadioButton) rootView.findViewById(R.id.rb_solar_energy_system_na);
                                                                                                                                                                                                                            if (radioButton27 != null) {
                                                                                                                                                                                                                                i = R.id.rb_sports_equipment_af;
                                                                                                                                                                                                                                RadioButton radioButton28 = (RadioButton) rootView.findViewById(R.id.rb_sports_equipment_af);
                                                                                                                                                                                                                                if (radioButton28 != null) {
                                                                                                                                                                                                                                    i = R.id.rb_sports_equipment_anf;
                                                                                                                                                                                                                                    RadioButton radioButton29 = (RadioButton) rootView.findViewById(R.id.rb_sports_equipment_anf);
                                                                                                                                                                                                                                    if (radioButton29 != null) {
                                                                                                                                                                                                                                        i = R.id.rb_sports_equipment_na;
                                                                                                                                                                                                                                        RadioButton radioButton30 = (RadioButton) rootView.findViewById(R.id.rb_sports_equipment_na);
                                                                                                                                                                                                                                        if (radioButton30 != null) {
                                                                                                                                                                                                                                            i = R.id.rg_biology_lab;
                                                                                                                                                                                                                                            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_biology_lab);
                                                                                                                                                                                                                                            if (radioGroup != null) {
                                                                                                                                                                                                                                                i = R.id.rg_chemistry_lab;
                                                                                                                                                                                                                                                RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_chemistry_lab);
                                                                                                                                                                                                                                                if (radioGroup2 != null) {
                                                                                                                                                                                                                                                    i = R.id.rg_computer_lab;
                                                                                                                                                                                                                                                    RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rg_computer_lab);
                                                                                                                                                                                                                                                    if (radioGroup3 != null) {
                                                                                                                                                                                                                                                        i = R.id.rg_home_economic_lab;
                                                                                                                                                                                                                                                        RadioGroup radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rg_home_economic_lab);
                                                                                                                                                                                                                                                        if (radioGroup4 != null) {
                                                                                                                                                                                                                                                            i = R.id.rg_library;
                                                                                                                                                                                                                                                            RadioGroup radioGroup5 = (RadioGroup) rootView.findViewById(R.id.rg_library);
                                                                                                                                                                                                                                                            if (radioGroup5 != null) {
                                                                                                                                                                                                                                                                i = R.id.rg_medical_first_aid_box;
                                                                                                                                                                                                                                                                RadioGroup radioGroup6 = (RadioGroup) rootView.findViewById(R.id.rg_medical_first_aid_box);
                                                                                                                                                                                                                                                                if (radioGroup6 != null) {
                                                                                                                                                                                                                                                                    i = R.id.rg_physics_lab;
                                                                                                                                                                                                                                                                    RadioGroup radioGroup7 = (RadioGroup) rootView.findViewById(R.id.rg_physics_lab);
                                                                                                                                                                                                                                                                    if (radioGroup7 != null) {
                                                                                                                                                                                                                                                                        i = R.id.rg_play_ground;
                                                                                                                                                                                                                                                                        RadioGroup radioGroup8 = (RadioGroup) rootView.findViewById(R.id.rg_play_ground);
                                                                                                                                                                                                                                                                        if (radioGroup8 != null) {
                                                                                                                                                                                                                                                                            i = R.id.rg_solar_energy_system;
                                                                                                                                                                                                                                                                            RadioGroup radioGroup9 = (RadioGroup) rootView.findViewById(R.id.rg_solar_energy_system);
                                                                                                                                                                                                                                                                            if (radioGroup9 != null) {
                                                                                                                                                                                                                                                                                i = R.id.rg_sports_equipment;
                                                                                                                                                                                                                                                                                RadioGroup radioGroup10 = (RadioGroup) rootView.findViewById(R.id.rg_sports_equipment);
                                                                                                                                                                                                                                                                                if (radioGroup10 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.save_button;
                                                                                                                                                                                                                                                                                    Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                                                                                                                                                                                                                                                    if (button2 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.skip_button;
                                                                                                                                                                                                                                                                                        Button button3 = (Button) rootView.findViewById(R.id.skip_button);
                                                                                                                                                                                                                                                                                        if (button3 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.tv_biology_lab;
                                                                                                                                                                                                                                                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_biology_lab);
                                                                                                                                                                                                                                                                                            if (textView != null) {
                                                                                                                                                                                                                                                                                                i = R.id.tv_blackboards_repairable;
                                                                                                                                                                                                                                                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_blackboards_repairable);
                                                                                                                                                                                                                                                                                                if (textView2 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.tv_blackboards_working;
                                                                                                                                                                                                                                                                                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_blackboards_working);
                                                                                                                                                                                                                                                                                                    if (textView3 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.tv_charts_repairable;
                                                                                                                                                                                                                                                                                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_charts_repairable);
                                                                                                                                                                                                                                                                                                        if (textView4 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.tv_charts_working;
                                                                                                                                                                                                                                                                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_charts_working);
                                                                                                                                                                                                                                                                                                            if (textView5 != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.tv_chemistry_lab;
                                                                                                                                                                                                                                                                                                                TextView textView6 = (TextView) rootView.findViewById(R.id.tv_chemistry_lab);
                                                                                                                                                                                                                                                                                                                if (textView6 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.tv_computer_lab;
                                                                                                                                                                                                                                                                                                                    TextView textView7 = (TextView) rootView.findViewById(R.id.tv_computer_lab);
                                                                                                                                                                                                                                                                                                                    if (textView7 != null) {
                                                                                                                                                                                                                                                                                                                        i = R.id.tv_computers_repairable;
                                                                                                                                                                                                                                                                                                                        TextView textView8 = (TextView) rootView.findViewById(R.id.tv_computers_repairable);
                                                                                                                                                                                                                                                                                                                        if (textView8 != null) {
                                                                                                                                                                                                                                                                                                                            i = R.id.tv_computers_working;
                                                                                                                                                                                                                                                                                                                            TextView textView9 = (TextView) rootView.findViewById(R.id.tv_computers_working);
                                                                                                                                                                                                                                                                                                                            if (textView9 != null) {
                                                                                                                                                                                                                                                                                                                                i = R.id.tv_cupboards_repairable;
                                                                                                                                                                                                                                                                                                                                TextView textView10 = (TextView) rootView.findViewById(R.id.tv_cupboards_repairable);
                                                                                                                                                                                                                                                                                                                                if (textView10 != null) {
                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_cupboards_working;
                                                                                                                                                                                                                                                                                                                                    TextView textView11 = (TextView) rootView.findViewById(R.id.tv_cupboards_working);
                                                                                                                                                                                                                                                                                                                                    if (textView11 != null) {
                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_electric_fans_repairable;
                                                                                                                                                                                                                                                                                                                                        TextView textView12 = (TextView) rootView.findViewById(R.id.tv_electric_fans_repairable);
                                                                                                                                                                                                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                                                                                                                                                                                                            i = R.id.tv_electric_fans_working;
                                                                                                                                                                                                                                                                                                                                            TextView textView13 = (TextView) rootView.findViewById(R.id.tv_electric_fans_working);
                                                                                                                                                                                                                                                                                                                                            if (textView13 != null) {
                                                                                                                                                                                                                                                                                                                                                i = R.id.tv_electric_motors_repairable;
                                                                                                                                                                                                                                                                                                                                                TextView textView14 = (TextView) rootView.findViewById(R.id.tv_electric_motors_repairable);
                                                                                                                                                                                                                                                                                                                                                if (textView14 != null) {
                                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_electric_motors_working;
                                                                                                                                                                                                                                                                                                                                                    TextView textView15 = (TextView) rootView.findViewById(R.id.tv_electric_motors_working);
                                                                                                                                                                                                                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_home_economic_lab;
                                                                                                                                                                                                                                                                                                                                                        TextView textView16 = (TextView) rootView.findViewById(R.id.tv_home_economic_lab);
                                                                                                                                                                                                                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                                                                                                                                                                                                                            i = R.id.tv_library;
                                                                                                                                                                                                                                                                                                                                                            TextView textView17 = (TextView) rootView.findViewById(R.id.tv_library);
                                                                                                                                                                                                                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                                                                                                                                                                                                                i = R.id.tv_medical_first_aid_box;
                                                                                                                                                                                                                                                                                                                                                                TextView textView18 = (TextView) rootView.findViewById(R.id.tv_medical_first_aid_box);
                                                                                                                                                                                                                                                                                                                                                                if (textView18 != null) {
                                                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_physics_lab;
                                                                                                                                                                                                                                                                                                                                                                    TextView textView19 = (TextView) rootView.findViewById(R.id.tv_physics_lab);
                                                                                                                                                                                                                                                                                                                                                                    if (textView19 != null) {
                                                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_play_ground;
                                                                                                                                                                                                                                                                                                                                                                        TextView textView20 = (TextView) rootView.findViewById(R.id.tv_play_ground);
                                                                                                                                                                                                                                                                                                                                                                        if (textView20 != null) {
                                                                                                                                                                                                                                                                                                                                                                            i = R.id.tv_solar_energy_system;
                                                                                                                                                                                                                                                                                                                                                                            TextView textView21 = (TextView) rootView.findViewById(R.id.tv_solar_energy_system);
                                                                                                                                                                                                                                                                                                                                                                            if (textView21 != null) {
                                                                                                                                                                                                                                                                                                                                                                                i = R.id.tv_sports_equipment;
                                                                                                                                                                                                                                                                                                                                                                                TextView textView22 = (TextView) rootView.findViewById(R.id.tv_sports_equipment);
                                                                                                                                                                                                                                                                                                                                                                                if (textView22 != null) {
                                                                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_student_chairs_repairable;
                                                                                                                                                                                                                                                                                                                                                                                    TextView textView23 = (TextView) rootView.findViewById(R.id.tv_student_chairs_repairable);
                                                                                                                                                                                                                                                                                                                                                                                    if (textView23 != null) {
                                                                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_student_chairs_working;
                                                                                                                                                                                                                                                                                                                                                                                        TextView textView24 = (TextView) rootView.findViewById(R.id.tv_student_chairs_working);
                                                                                                                                                                                                                                                                                                                                                                                        if (textView24 != null) {
                                                                                                                                                                                                                                                                                                                                                                                            i = R.id.tv_student_desks_student_benches_repairable;
                                                                                                                                                                                                                                                                                                                                                                                            TextView textView25 = (TextView) rootView.findViewById(R.id.tv_student_desks_student_benches_repairable);
                                                                                                                                                                                                                                                                                                                                                                                            if (textView25 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                i = R.id.tv_student_desks_student_benches_working;
                                                                                                                                                                                                                                                                                                                                                                                                TextView textView26 = (TextView) rootView.findViewById(R.id.tv_student_desks_student_benches_working);
                                                                                                                                                                                                                                                                                                                                                                                                if (textView26 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_teacher_chairs_repairable;
                                                                                                                                                                                                                                                                                                                                                                                                    TextView textView27 = (TextView) rootView.findViewById(R.id.tv_teacher_chairs_repairable);
                                                                                                                                                                                                                                                                                                                                                                                                    if (textView27 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_teacher_chairs_working;
                                                                                                                                                                                                                                                                                                                                                                                                        TextView textView28 = (TextView) rootView.findViewById(R.id.tv_teacher_chairs_working);
                                                                                                                                                                                                                                                                                                                                                                                                        if (textView28 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                            i = R.id.tv_teacher_tables_repairable;
                                                                                                                                                                                                                                                                                                                                                                                                            TextView textView29 = (TextView) rootView.findViewById(R.id.tv_teacher_tables_repairable);
                                                                                                                                                                                                                                                                                                                                                                                                            if (textView29 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                                i = R.id.tv_teacher_tables_working;
                                                                                                                                                                                                                                                                                                                                                                                                                TextView textView30 = (TextView) rootView.findViewById(R.id.tv_teacher_tables_working);
                                                                                                                                                                                                                                                                                                                                                                                                                if (textView30 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                                    i = R.id.tv_whiteboards_repairable;
                                                                                                                                                                                                                                                                                                                                                                                                                    TextView textView31 = (TextView) rootView.findViewById(R.id.tv_whiteboards_repairable);
                                                                                                                                                                                                                                                                                                                                                                                                                    if (textView31 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                                        i = R.id.tv_whiteboards_working;
                                                                                                                                                                                                                                                                                                                                                                                                                        TextView textView32 = (TextView) rootView.findViewById(R.id.tv_whiteboards_working);
                                                                                                                                                                                                                                                                                                                                                                                                                        if (textView32 != null) {
                                                                                                                                                                                                                                                                                                                                                                                                                            return new FragmentCensusOtherFacilitiesBinding(relativeLayout4, relativeLayout, button, relativeLayout2, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13, editText14, editText15, editText16, editText17, editText18, editText19, editText20, editText21, editText22, relativeLayout3, relativeLayout4, relativeLayout5, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioButton13, radioButton14, radioButton15, radioButton16, radioButton17, radioButton18, radioButton19, radioButton20, radioButton21, radioButton22, radioButton23, radioButton24, radioButton25, radioButton26, radioButton27, radioButton28, radioButton29, radioButton30, radioGroup, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6, radioGroup7, radioGroup8, radioGroup9, radioGroup10, button2, button3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView29, textView30, textView31, textView32);
                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
