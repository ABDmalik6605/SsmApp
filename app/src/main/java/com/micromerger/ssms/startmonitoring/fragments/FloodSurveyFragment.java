package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class FloodSurveyFragment extends BaseFragment {
    List<ReferenceDataResponse.ReferenceData> INACCESSIBILITY_REASON_LIST;
    LinearLayout accessible_layout;
    RelativeLayout after_flood_layout;
    LinearLayout damage_done_yes_layout;
    EditText et_overall_observation;
    EditText et_reason_inaccessibility;
    EditText et_remarks;
    LinearLayout inundated_layout;
    View mView;
    RadioButton rb_after_flood_accessible;
    RadioButton rb_after_flood_inundated;
    RadioButton rb_boundary_wall_fully;
    RadioButton rb_boundary_wall_no;
    RadioButton rb_boundary_wall_partially;
    RadioButton rb_damage_done_no;
    RadioButton rb_damage_done_yes;
    RadioButton rb_floor_fully;
    RadioButton rb_floor_no;
    RadioButton rb_floor_partially;
    RadioButton rb_occupied_no;
    RadioButton rb_occupied_yes;
    RadioButton rb_rain_flood_no;
    RadioButton rb_rain_flood_yes;
    RadioButton rb_roof_fully;
    RadioButton rb_roof_no;
    RadioButton rb_roof_partially;
    RadioButton rb_school_building_damage_fully;
    RadioButton rb_school_building_damage_partially;
    RadioButton rb_toilet_fully;
    RadioButton rb_toilet_no;
    RadioButton rb_toilet_partially;
    RadioButton rb_walls_of_rooms_fully;
    RadioButton rb_walls_of_rooms_no;
    RadioButton rb_walls_of_rooms_partially;
    RadioGroup rg_after_flood;
    RadioGroup rg_boundary_wall;
    RadioGroup rg_damage_done;
    RadioGroup rg_floor;
    RadioGroup rg_occupied;
    RadioGroup rg_rain_flood;
    RadioGroup rg_roof;
    RadioGroup rg_school_building_damage;
    RadioGroup rg_toilet;
    RadioGroup rg_walls_of_rooms;
    Button save_btn;
    private final int optionNo = 0;
    private final int optionPartially = 1;
    private final int optionFully = 2;
    private final int afterFloodAccessible = 1;
    private final int afterFloodInadequate = 2;
    private final int optionDamagePartially = 1;
    private final int optionDamageFully = 2;
    int afterFlood = -1;
    int occupied = -1;
    int rainFlood = -1;
    int damageDone = -1;
    int schoolBuildingDamage = -1;
    int boundaryWall = -1;
    int roof = -1;
    int wallsOfRoom = -1;
    int toilet = -1;
    int floor = -1;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_flood_survey, container, false);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.after_flood_layout = (RelativeLayout) view.findViewById(R.id.after_flood_layout);
        this.rg_after_flood = (RadioGroup) view.findViewById(R.id.rg_after_flood);
        this.rb_after_flood_accessible = (RadioButton) view.findViewById(R.id.rb_after_flood_accessible);
        this.rb_after_flood_inundated = (RadioButton) view.findViewById(R.id.rb_after_flood_inundated);
        this.inundated_layout = (LinearLayout) view.findViewById(R.id.inundated_layout);
        this.et_remarks = (EditText) view.findViewById(R.id.et_remarks);
        this.accessible_layout = (LinearLayout) view.findViewById(R.id.accessible_layout);
        this.rg_occupied = (RadioGroup) view.findViewById(R.id.rg_occupied);
        this.rb_occupied_yes = (RadioButton) view.findViewById(R.id.rb_occupied_yes);
        this.rb_occupied_no = (RadioButton) view.findViewById(R.id.rb_occupied_no);
        this.rg_rain_flood = (RadioGroup) view.findViewById(R.id.rg_rain_flood);
        this.rb_rain_flood_yes = (RadioButton) view.findViewById(R.id.rb_rain_flood_yes);
        this.rb_rain_flood_no = (RadioButton) view.findViewById(R.id.rb_rain_flood_no);
        this.rg_damage_done = (RadioGroup) view.findViewById(R.id.rg_damage_done);
        this.rb_damage_done_yes = (RadioButton) view.findViewById(R.id.rb_damage_done_yes);
        this.rb_damage_done_no = (RadioButton) view.findViewById(R.id.rb_damage_done_no);
        this.damage_done_yes_layout = (LinearLayout) view.findViewById(R.id.damage_done_yes_layout);
        this.rg_school_building_damage = (RadioGroup) view.findViewById(R.id.rg_school_building_damage);
        this.rb_school_building_damage_partially = (RadioButton) view.findViewById(R.id.rb_school_building_damage_partially);
        this.rb_school_building_damage_fully = (RadioButton) view.findViewById(R.id.rb_school_building_damage_fully);
        this.rg_boundary_wall = (RadioGroup) view.findViewById(R.id.rg_boundary_wall);
        this.rb_boundary_wall_no = (RadioButton) view.findViewById(R.id.rb_boundary_wall_no);
        this.rb_boundary_wall_partially = (RadioButton) view.findViewById(R.id.rb_boundary_wall_partially);
        this.rb_boundary_wall_fully = (RadioButton) view.findViewById(R.id.rb_boundary_wall_fully);
        this.rg_roof = (RadioGroup) view.findViewById(R.id.rg_roof);
        this.rb_roof_no = (RadioButton) view.findViewById(R.id.rb_roof_no);
        this.rb_roof_partially = (RadioButton) view.findViewById(R.id.rb_roof_partially);
        this.rb_roof_fully = (RadioButton) view.findViewById(R.id.rb_roof_fully);
        this.rg_walls_of_rooms = (RadioGroup) view.findViewById(R.id.rg_walls_of_rooms);
        this.rb_walls_of_rooms_no = (RadioButton) view.findViewById(R.id.rb_walls_of_rooms_no);
        this.rb_walls_of_rooms_partially = (RadioButton) view.findViewById(R.id.rb_walls_of_rooms_partially);
        this.rb_walls_of_rooms_fully = (RadioButton) view.findViewById(R.id.rb_walls_of_rooms_fully);
        this.rg_toilet = (RadioGroup) view.findViewById(R.id.rg_toilet);
        this.rb_toilet_no = (RadioButton) view.findViewById(R.id.rb_toilet_no);
        this.rb_toilet_partially = (RadioButton) view.findViewById(R.id.rb_toilet_partially);
        this.rb_toilet_fully = (RadioButton) view.findViewById(R.id.rb_toilet_fully);
        this.rg_floor = (RadioGroup) view.findViewById(R.id.rg_floor);
        this.rb_floor_no = (RadioButton) view.findViewById(R.id.rb_floor_no);
        this.rb_floor_partially = (RadioButton) view.findViewById(R.id.rb_floor_partially);
        this.rb_floor_fully = (RadioButton) view.findViewById(R.id.rb_floor_fully);
        this.et_overall_observation = (EditText) view.findViewById(R.id.et_overall_observation);
        this.et_reason_inaccessibility = (EditText) view.findViewById(R.id.et_reason_inaccessibility);
        Button button = (Button) view.findViewById(R.id.save_btn);
        this.save_btn = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$Axk12sRlyQfJsZ6jN0t76eqxQzI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.saveForm(view2);
            }
        });
        this.rg_after_flood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$kUYcWJtiTOplOvO2VhTnLFlIKio
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$0$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_occupied.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$nzGwOiUmdzTzMNPexGCH6AL9Wxc
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$1$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_rain_flood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$GY27CgG3ucF7VFtcqIIYSoT6p48
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$2$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_damage_done.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$UDXVeyeXgpucsUhuSpChMlAk0JI
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$3$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_school_building_damage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$m27vwCfBP6KKl-D6DHV8OA0L8rE
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$4$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_boundary_wall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$lM1R_2TCzovYTslCqN4XwjBh96k
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$5$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_roof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$jPuEr4WlSlMdZ8dmCnKGHLLtWUc
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$6$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_walls_of_rooms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$q_c49BxtxLfSxI9EO-AuFqTXTrM
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$7$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_toilet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$o9IZ6321lhDenEDdNRD2AC_Xle0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$8$FloodSurveyFragment(radioGroup, i);
            }
        });
        this.rg_floor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$Fy0Q50SZ_oiIPCjabdn0CXlnbAw
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.lambda$onViewCreated$9$FloodSurveyFragment(radioGroup, i);
            }
        });
    }

    public /* synthetic */ void lambda$onViewCreated$0$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        this.save_btn.setVisibility(0);
        if (i == R.id.rb_after_flood_accessible) {
            afterFloodAccessible();
        } else if (i == R.id.rb_after_flood_inundated) {
            afterFloodInundated();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_occupied_yes) {
            this.occupied = 0;
        } else if (i == R.id.rb_occupied_no) {
            this.occupied = 1;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$2$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_rain_flood_yes) {
            this.rainFlood = 0;
        } else if (i == R.id.rb_rain_flood_no) {
            this.rainFlood = 1;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$3$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_damage_done_yes) {
            damageDoneYes();
        } else if (i == R.id.rb_damage_done_no) {
            damageDoneNo();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$4$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_school_building_damage_partially) {
            this.schoolBuildingDamage = 1;
        } else if (i == R.id.rb_school_building_damage_fully) {
            this.schoolBuildingDamage = 2;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$5$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_boundary_wall_no) {
            this.boundaryWall = 0;
        } else if (i == R.id.rb_boundary_wall_partially) {
            this.boundaryWall = 1;
        } else if (i == R.id.rb_boundary_wall_fully) {
            this.boundaryWall = 2;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$6$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_roof_no) {
            this.roof = 0;
        } else if (i == R.id.rb_roof_partially) {
            this.roof = 1;
        } else if (i == R.id.rb_roof_fully) {
            this.roof = 2;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$7$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_walls_of_rooms_no) {
            this.wallsOfRoom = 0;
        } else if (i == R.id.rb_walls_of_rooms_partially) {
            this.wallsOfRoom = 1;
        } else if (i == R.id.rb_walls_of_rooms_fully) {
            this.wallsOfRoom = 2;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$8$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_toilet_no) {
            this.toilet = 0;
        } else if (i == R.id.rb_toilet_partially) {
            this.toilet = 1;
        } else if (i == R.id.rb_toilet_fully) {
            this.toilet = 2;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$9$FloodSurveyFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_floor_no) {
            this.floor = 0;
        } else if (i == R.id.rb_floor_partially) {
            this.floor = 1;
        } else if (i == R.id.rb_floor_fully) {
            this.floor = 2;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        patchData();
        if (CommonObjects.monitoring == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.save_btn.setClickable(false);
        this.save_btn.setFocusable(false);
        this.rg_after_flood.setClickable(false);
        this.rg_after_flood.setFocusable(false);
        this.rg_occupied.setClickable(false);
        this.rg_occupied.setFocusable(false);
        this.rg_rain_flood.setClickable(false);
        this.rg_rain_flood.setFocusable(false);
        this.rg_damage_done.setClickable(false);
        this.rg_damage_done.setFocusable(false);
        this.rg_school_building_damage.setClickable(false);
        this.rg_school_building_damage.setFocusable(false);
        this.rg_boundary_wall.setClickable(false);
        this.rg_boundary_wall.setFocusable(false);
        this.rg_roof.setClickable(false);
        this.rg_roof.setFocusable(false);
        this.rg_walls_of_rooms.setClickable(false);
        this.rg_walls_of_rooms.setFocusable(false);
        this.rg_toilet.setClickable(false);
        this.rg_toilet.setFocusable(false);
        this.rg_floor.setClickable(false);
        this.rg_floor.setFocusable(false);
        this.et_overall_observation.setClickable(false);
        this.et_overall_observation.setFocusable(false);
        this.et_reason_inaccessibility.setClickable(false);
        this.et_reason_inaccessibility.setFocusable(false);
        this.et_remarks.setClickable(false);
        this.et_remarks.setFocusable(false);
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
        } else {
            this.fm.popBackStack();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void patchData() throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 684
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.startmonitoring.fragments.FloodSurveyFragment.patchData():void");
    }

    private void afterFloodAccessible() {
        this.afterFlood = 1;
        this.accessible_layout.setVisibility(0);
        this.inundated_layout.setVisibility(8);
        this.damage_done_yes_layout.setVisibility(8);
        this.et_reason_inaccessibility.setText("");
        this.et_remarks.setText("");
    }

    private void afterFloodInundated() {
        this.afterFlood = 2;
        this.accessible_layout.setVisibility(8);
        this.inundated_layout.setVisibility(0);
        this.rg_occupied.clearCheck();
        this.rg_rain_flood.clearCheck();
        this.rg_damage_done.clearCheck();
        damageDoneNo();
    }

    private void damageDoneYes() {
        this.damageDone = 0;
        this.damage_done_yes_layout.setVisibility(0);
    }

    private void damageDoneNo() {
        this.damageDone = 1;
        this.rg_school_building_damage.clearCheck();
        this.schoolBuildingDamage = -1;
        this.rg_boundary_wall.clearCheck();
        this.boundaryWall = -1;
        this.rg_roof.clearCheck();
        this.roof = -1;
        this.rg_walls_of_rooms.clearCheck();
        this.wallsOfRoom = -1;
        this.rg_toilet.clearCheck();
        this.toilet = -1;
        this.rg_floor.clearCheck();
        this.floor = -1;
        this.et_overall_observation.setText("");
        this.damage_done_yes_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveForm(View view) {
        int i = this.afterFlood;
        if (i != 1) {
            if (i == 2) {
                if (TextUtils.isEmpty(this.et_reason_inaccessibility.getText().toString().trim())) {
                    DialogCustom.showError(view.getContext(), "Please enter Reason for inaccessibility");
                    return;
                } else {
                    saveInundatedData(view);
                    return;
                }
            }
            DialogCustom.showError(view.getContext(), "Please select After Flood");
            return;
        }
        if (this.occupied == -1) {
            DialogCustom.showError(view.getContext(), "Please select Whether occupied by IDPs or not?");
            return;
        }
        if (this.rainFlood == -1) {
            DialogCustom.showError(view.getContext(), "Please select Rain / Flood water in School premises");
            return;
        }
        int i2 = this.damageDone;
        if (i2 == -1) {
            DialogCustom.showError(view.getContext(), "Please select Damage Done");
            return;
        }
        if (i2 == 1) {
            saveAccessibleData(view);
            return;
        }
        if (this.schoolBuildingDamage == -1) {
            DialogCustom.showError(view.getContext(), "Please select School Building Damage");
            return;
        }
        if (this.boundaryWall == -1) {
            DialogCustom.showError(view.getContext(), "Please select Boundary Wall");
            return;
        }
        if (this.roof == -1) {
            DialogCustom.showError(view.getContext(), "Please select Roof");
            return;
        }
        if (this.wallsOfRoom == -1) {
            DialogCustom.showError(view.getContext(), "Please select Walls of rooms");
            return;
        }
        if (this.toilet == -1) {
            DialogCustom.showError(view.getContext(), "Please select Toilet");
        } else if (this.floor == -1) {
            DialogCustom.showError(view.getContext(), "Please select Floor");
        } else {
            saveAccessibleData(view);
        }
    }

    private void saveAccessibleData(View view) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$mAWJ9qcqCuMokzh5Xo4Fi4QH_jY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveAccessibleData$10$FloodSurveyFragment();
            }
        }).start();
        Toast.makeText(view.getContext(), "Form Saved", 0).show();
        this.fm.popBackStack();
    }

    public /* synthetic */ void lambda$saveAccessibleData$10$FloodSurveyFragment() {
        KRAData value;
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.flood_survey.entrySet()) {
            Integer key = entry.getKey();
            value = entry.getValue();
            String kRAName = value.getKRAName();
            kRAName.hashCode();
            switch (kRAName) {
                case "After_Flood":
                    value.setDataValue(String.valueOf(this.afterFlood));
                    break;
                case "Overall_Observation":
                    value.setDataValue(this.et_overall_observation.getText().toString().trim());
                    break;
                case "Damage_Done":
                    value.setDataValue(String.valueOf(this.damageDone));
                    break;
                case "Remarks":
                case "Reason_Of_Inaccessibility_Other":
                    value.setDataValue("");
                    break;
                case "Walls_Of_Rooms_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.wallsOfRoom));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
                case "Whether_Occupied_By_IDPs_Or_Not":
                    value.setDataValue(String.valueOf(this.occupied));
                    break;
                case "School_Building_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.schoolBuildingDamage));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
                case "Rain_Flood_Water_In_School_Premises":
                    value.setDataValue(String.valueOf(this.rainFlood));
                    break;
                case "Boundary_Wall_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.boundaryWall));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
                case "Roof_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.roof));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
                case "Toilet_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.toilet));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
                case "Floor_Damage":
                    if (this.damageDone == 0) {
                        value.setDataValue(String.valueOf(this.floor));
                        break;
                    } else {
                        value.setDataValue("");
                        break;
                    }
            }
            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
        }
        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
            if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    private void saveInundatedData(View view) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$FloodSurveyFragment$ODU_qIldNUF29bZPF5X6KNkvKVk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveInundatedData$11$FloodSurveyFragment();
            }
        }).start();
        Toast.makeText(view.getContext(), "Form Saved", 0).show();
        this.fm.popBackStack();
    }

    public /* synthetic */ void lambda$saveInundatedData$11$FloodSurveyFragment() {
        KRAData value;
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.flood_survey.entrySet()) {
            Integer key = entry.getKey();
            value = entry.getValue();
            String kRAName = value.getKRAName();
            kRAName.hashCode();
            switch (kRAName) {
                case "After_Flood":
                    value.setDataValue(String.valueOf(this.afterFlood));
                    break;
                case "Overall_Observation":
                case "Damage_Done":
                case "Walls_Of_Rooms_Damage":
                case "Whether_Occupied_By_IDPs_Or_Not":
                case "School_Building_Damage":
                case "Rain_Flood_Water_In_School_Premises":
                case "Boundary_Wall_Damage":
                case "Roof_Damage":
                case "Toilet_Damage":
                case "Floor_Damage":
                    value.setDataValue("");
                    break;
                case "Remarks":
                    value.setDataValue(this.et_remarks.getText().toString().trim());
                    break;
                case "Reason_Of_Inaccessibility_Other":
                    value.setDataValue(this.et_reason_inaccessibility.getText().toString());
                    break;
            }
            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
        }
        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
            if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }
}
