package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentFloodSurveyBinding implements ViewBinding {
    public final LinearLayout accessibleLayout;
    public final RelativeLayout afterFloodLayout;
    public final RelativeLayout afterFloodRadioLayout;
    public final RelativeLayout boundaryWallLayout;
    public final RelativeLayout boundaryWallRadioLayout;
    public final TextView damageDone;
    public final RelativeLayout damageDoneLayout;
    public final RelativeLayout damageDoneRadioLayout;
    public final LinearLayout damageDoneYesLayout;
    public final EditText etOverallObservation;
    public final EditText etReasonInaccessibility;
    public final EditText etRemarks;
    public final RelativeLayout floorLayout;
    public final RelativeLayout floorRadioLayout;
    public final LinearLayout inundatedLayout;
    public final RelativeLayout occupiedLayout;
    public final RelativeLayout occupiedRadioLayout;
    public final RelativeLayout rainFloodLayout;
    public final RelativeLayout rainFloodRadioLayout;
    public final RadioButton rbAfterFloodAccessible;
    public final RadioButton rbAfterFloodInundated;
    public final RadioButton rbBoundaryWallFully;
    public final RadioButton rbBoundaryWallNo;
    public final RadioButton rbBoundaryWallPartially;
    public final RadioButton rbDamageDoneNo;
    public final RadioButton rbDamageDoneYes;
    public final RadioButton rbFloorFully;
    public final RadioButton rbFloorNo;
    public final RadioButton rbFloorPartially;
    public final RadioButton rbOccupiedNo;
    public final RadioButton rbOccupiedYes;
    public final RadioButton rbRainFloodNo;
    public final RadioButton rbRainFloodYes;
    public final RadioButton rbRoofFully;
    public final RadioButton rbRoofNo;
    public final RadioButton rbRoofPartially;
    public final RadioButton rbSchoolBuildingDamageFully;
    public final RadioButton rbSchoolBuildingDamagePartially;
    public final RadioButton rbToiletFully;
    public final RadioButton rbToiletNo;
    public final RadioButton rbToiletPartially;
    public final RadioButton rbWallsOfRoomsFully;
    public final RadioButton rbWallsOfRoomsNo;
    public final RadioButton rbWallsOfRoomsPartially;
    public final LinearLayout reasonForInaccessibilityLayout;
    public final RadioGroup rgAfterFlood;
    public final RadioGroup rgBoundaryWall;
    public final RadioGroup rgDamageDone;
    public final RadioGroup rgFloor;
    public final RadioGroup rgOccupied;
    public final RadioGroup rgRainFlood;
    public final RadioGroup rgRoof;
    public final RadioGroup rgSchoolBuildingDamage;
    public final RadioGroup rgToilet;
    public final RadioGroup rgWallsOfRooms;
    public final RelativeLayout roofLayout;
    public final RelativeLayout roofRadioLayout;
    private final RelativeLayout rootView;
    public final Button saveBtn;
    public final RelativeLayout schoolBuildingDamageLayout;
    public final RelativeLayout schoolBuildingDamageRadioLayout;
    public final ScrollView scrollView;
    public final TextView title;
    public final RelativeLayout toiletLayout;
    public final RelativeLayout toiletRadioLayout;
    public final TextView tvBoundaryWallLayout;
    public final TextView tvDamageDoneLayout;
    public final TextView tvFloorLayout;
    public final TextView tvFurnitureAvailability;
    public final TextView tvOccupiedLayout;
    public final TextView tvOverallObservation;
    public final TextView tvRainFloodLayout;
    public final TextView tvRemarks;
    public final TextView tvRoofLayout;
    public final TextView tvSchoolBuildingDamageLayout;
    public final TextView tvToiletLayout;
    public final TextView tvWallsOfRoomsLayout;
    public final RelativeLayout wallsOfRoomsLayout;
    public final RelativeLayout wallsOfRoomsRadioLayout;

    private FragmentFloodSurveyBinding(RelativeLayout rootView, LinearLayout accessibleLayout, RelativeLayout afterFloodLayout, RelativeLayout afterFloodRadioLayout, RelativeLayout boundaryWallLayout, RelativeLayout boundaryWallRadioLayout, TextView damageDone, RelativeLayout damageDoneLayout, RelativeLayout damageDoneRadioLayout, LinearLayout damageDoneYesLayout, EditText etOverallObservation, EditText etReasonInaccessibility, EditText etRemarks, RelativeLayout floorLayout, RelativeLayout floorRadioLayout, LinearLayout inundatedLayout, RelativeLayout occupiedLayout, RelativeLayout occupiedRadioLayout, RelativeLayout rainFloodLayout, RelativeLayout rainFloodRadioLayout, RadioButton rbAfterFloodAccessible, RadioButton rbAfterFloodInundated, RadioButton rbBoundaryWallFully, RadioButton rbBoundaryWallNo, RadioButton rbBoundaryWallPartially, RadioButton rbDamageDoneNo, RadioButton rbDamageDoneYes, RadioButton rbFloorFully, RadioButton rbFloorNo, RadioButton rbFloorPartially, RadioButton rbOccupiedNo, RadioButton rbOccupiedYes, RadioButton rbRainFloodNo, RadioButton rbRainFloodYes, RadioButton rbRoofFully, RadioButton rbRoofNo, RadioButton rbRoofPartially, RadioButton rbSchoolBuildingDamageFully, RadioButton rbSchoolBuildingDamagePartially, RadioButton rbToiletFully, RadioButton rbToiletNo, RadioButton rbToiletPartially, RadioButton rbWallsOfRoomsFully, RadioButton rbWallsOfRoomsNo, RadioButton rbWallsOfRoomsPartially, LinearLayout reasonForInaccessibilityLayout, RadioGroup rgAfterFlood, RadioGroup rgBoundaryWall, RadioGroup rgDamageDone, RadioGroup rgFloor, RadioGroup rgOccupied, RadioGroup rgRainFlood, RadioGroup rgRoof, RadioGroup rgSchoolBuildingDamage, RadioGroup rgToilet, RadioGroup rgWallsOfRooms, RelativeLayout roofLayout, RelativeLayout roofRadioLayout, Button saveBtn, RelativeLayout schoolBuildingDamageLayout, RelativeLayout schoolBuildingDamageRadioLayout, ScrollView scrollView, TextView title, RelativeLayout toiletLayout, RelativeLayout toiletRadioLayout, TextView tvBoundaryWallLayout, TextView tvDamageDoneLayout, TextView tvFloorLayout, TextView tvFurnitureAvailability, TextView tvOccupiedLayout, TextView tvOverallObservation, TextView tvRainFloodLayout, TextView tvRemarks, TextView tvRoofLayout, TextView tvSchoolBuildingDamageLayout, TextView tvToiletLayout, TextView tvWallsOfRoomsLayout, RelativeLayout wallsOfRoomsLayout, RelativeLayout wallsOfRoomsRadioLayout) {
        this.rootView = rootView;
        this.accessibleLayout = accessibleLayout;
        this.afterFloodLayout = afterFloodLayout;
        this.afterFloodRadioLayout = afterFloodRadioLayout;
        this.boundaryWallLayout = boundaryWallLayout;
        this.boundaryWallRadioLayout = boundaryWallRadioLayout;
        this.damageDone = damageDone;
        this.damageDoneLayout = damageDoneLayout;
        this.damageDoneRadioLayout = damageDoneRadioLayout;
        this.damageDoneYesLayout = damageDoneYesLayout;
        this.etOverallObservation = etOverallObservation;
        this.etReasonInaccessibility = etReasonInaccessibility;
        this.etRemarks = etRemarks;
        this.floorLayout = floorLayout;
        this.floorRadioLayout = floorRadioLayout;
        this.inundatedLayout = inundatedLayout;
        this.occupiedLayout = occupiedLayout;
        this.occupiedRadioLayout = occupiedRadioLayout;
        this.rainFloodLayout = rainFloodLayout;
        this.rainFloodRadioLayout = rainFloodRadioLayout;
        this.rbAfterFloodAccessible = rbAfterFloodAccessible;
        this.rbAfterFloodInundated = rbAfterFloodInundated;
        this.rbBoundaryWallFully = rbBoundaryWallFully;
        this.rbBoundaryWallNo = rbBoundaryWallNo;
        this.rbBoundaryWallPartially = rbBoundaryWallPartially;
        this.rbDamageDoneNo = rbDamageDoneNo;
        this.rbDamageDoneYes = rbDamageDoneYes;
        this.rbFloorFully = rbFloorFully;
        this.rbFloorNo = rbFloorNo;
        this.rbFloorPartially = rbFloorPartially;
        this.rbOccupiedNo = rbOccupiedNo;
        this.rbOccupiedYes = rbOccupiedYes;
        this.rbRainFloodNo = rbRainFloodNo;
        this.rbRainFloodYes = rbRainFloodYes;
        this.rbRoofFully = rbRoofFully;
        this.rbRoofNo = rbRoofNo;
        this.rbRoofPartially = rbRoofPartially;
        this.rbSchoolBuildingDamageFully = rbSchoolBuildingDamageFully;
        this.rbSchoolBuildingDamagePartially = rbSchoolBuildingDamagePartially;
        this.rbToiletFully = rbToiletFully;
        this.rbToiletNo = rbToiletNo;
        this.rbToiletPartially = rbToiletPartially;
        this.rbWallsOfRoomsFully = rbWallsOfRoomsFully;
        this.rbWallsOfRoomsNo = rbWallsOfRoomsNo;
        this.rbWallsOfRoomsPartially = rbWallsOfRoomsPartially;
        this.reasonForInaccessibilityLayout = reasonForInaccessibilityLayout;
        this.rgAfterFlood = rgAfterFlood;
        this.rgBoundaryWall = rgBoundaryWall;
        this.rgDamageDone = rgDamageDone;
        this.rgFloor = rgFloor;
        this.rgOccupied = rgOccupied;
        this.rgRainFlood = rgRainFlood;
        this.rgRoof = rgRoof;
        this.rgSchoolBuildingDamage = rgSchoolBuildingDamage;
        this.rgToilet = rgToilet;
        this.rgWallsOfRooms = rgWallsOfRooms;
        this.roofLayout = roofLayout;
        this.roofRadioLayout = roofRadioLayout;
        this.saveBtn = saveBtn;
        this.schoolBuildingDamageLayout = schoolBuildingDamageLayout;
        this.schoolBuildingDamageRadioLayout = schoolBuildingDamageRadioLayout;
        this.scrollView = scrollView;
        this.title = title;
        this.toiletLayout = toiletLayout;
        this.toiletRadioLayout = toiletRadioLayout;
        this.tvBoundaryWallLayout = tvBoundaryWallLayout;
        this.tvDamageDoneLayout = tvDamageDoneLayout;
        this.tvFloorLayout = tvFloorLayout;
        this.tvFurnitureAvailability = tvFurnitureAvailability;
        this.tvOccupiedLayout = tvOccupiedLayout;
        this.tvOverallObservation = tvOverallObservation;
        this.tvRainFloodLayout = tvRainFloodLayout;
        this.tvRemarks = tvRemarks;
        this.tvRoofLayout = tvRoofLayout;
        this.tvSchoolBuildingDamageLayout = tvSchoolBuildingDamageLayout;
        this.tvToiletLayout = tvToiletLayout;
        this.tvWallsOfRoomsLayout = tvWallsOfRoomsLayout;
        this.wallsOfRoomsLayout = wallsOfRoomsLayout;
        this.wallsOfRoomsRadioLayout = wallsOfRoomsRadioLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentFloodSurveyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentFloodSurveyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_flood_survey, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentFloodSurveyBinding bind(View rootView) {
        int i = R.id.accessible_layout;
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.accessible_layout);
        if (linearLayout != null) {
            i = R.id.after_flood_layout;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.after_flood_layout);
            if (relativeLayout != null) {
                i = R.id.after_flood_radio_layout;
                RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.after_flood_radio_layout);
                if (relativeLayout2 != null) {
                    i = R.id.boundary_wall_layout;
                    RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.boundary_wall_layout);
                    if (relativeLayout3 != null) {
                        i = R.id.boundary_wall_radio_layout;
                        RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.boundary_wall_radio_layout);
                        if (relativeLayout4 != null) {
                            i = R.id.damage_done;
                            TextView textView = (TextView) rootView.findViewById(R.id.damage_done);
                            if (textView != null) {
                                i = R.id.damage_done_layout;
                                RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.damage_done_layout);
                                if (relativeLayout5 != null) {
                                    i = R.id.damage_done_radio_layout;
                                    RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.damage_done_radio_layout);
                                    if (relativeLayout6 != null) {
                                        i = R.id.damage_done_yes_layout;
                                        LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.damage_done_yes_layout);
                                        if (linearLayout2 != null) {
                                            i = R.id.et_overall_observation;
                                            EditText editText = (EditText) rootView.findViewById(R.id.et_overall_observation);
                                            if (editText != null) {
                                                i = R.id.et_reason_inaccessibility;
                                                EditText editText2 = (EditText) rootView.findViewById(R.id.et_reason_inaccessibility);
                                                if (editText2 != null) {
                                                    i = R.id.et_remarks;
                                                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_remarks);
                                                    if (editText3 != null) {
                                                        i = R.id.floor_layout;
                                                        RelativeLayout relativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.floor_layout);
                                                        if (relativeLayout7 != null) {
                                                            i = R.id.floor_radio_layout;
                                                            RelativeLayout relativeLayout8 = (RelativeLayout) rootView.findViewById(R.id.floor_radio_layout);
                                                            if (relativeLayout8 != null) {
                                                                i = R.id.inundated_layout;
                                                                LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.inundated_layout);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.occupied_layout;
                                                                    RelativeLayout relativeLayout9 = (RelativeLayout) rootView.findViewById(R.id.occupied_layout);
                                                                    if (relativeLayout9 != null) {
                                                                        i = R.id.occupied_radio_layout;
                                                                        RelativeLayout relativeLayout10 = (RelativeLayout) rootView.findViewById(R.id.occupied_radio_layout);
                                                                        if (relativeLayout10 != null) {
                                                                            i = R.id.rain_flood_layout;
                                                                            RelativeLayout relativeLayout11 = (RelativeLayout) rootView.findViewById(R.id.rain_flood_layout);
                                                                            if (relativeLayout11 != null) {
                                                                                i = R.id.rain_flood_radio_layout;
                                                                                RelativeLayout relativeLayout12 = (RelativeLayout) rootView.findViewById(R.id.rain_flood_radio_layout);
                                                                                if (relativeLayout12 != null) {
                                                                                    i = R.id.rb_after_flood_accessible;
                                                                                    RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_after_flood_accessible);
                                                                                    if (radioButton != null) {
                                                                                        i = R.id.rb_after_flood_inundated;
                                                                                        RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_after_flood_inundated);
                                                                                        if (radioButton2 != null) {
                                                                                            i = R.id.rb_boundary_wall_fully;
                                                                                            RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_boundary_wall_fully);
                                                                                            if (radioButton3 != null) {
                                                                                                i = R.id.rb_boundary_wall_no;
                                                                                                RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_boundary_wall_no);
                                                                                                if (radioButton4 != null) {
                                                                                                    i = R.id.rb_boundary_wall_partially;
                                                                                                    RadioButton radioButton5 = (RadioButton) rootView.findViewById(R.id.rb_boundary_wall_partially);
                                                                                                    if (radioButton5 != null) {
                                                                                                        i = R.id.rb_damage_done_no;
                                                                                                        RadioButton radioButton6 = (RadioButton) rootView.findViewById(R.id.rb_damage_done_no);
                                                                                                        if (radioButton6 != null) {
                                                                                                            i = R.id.rb_damage_done_yes;
                                                                                                            RadioButton radioButton7 = (RadioButton) rootView.findViewById(R.id.rb_damage_done_yes);
                                                                                                            if (radioButton7 != null) {
                                                                                                                i = R.id.rb_floor_fully;
                                                                                                                RadioButton radioButton8 = (RadioButton) rootView.findViewById(R.id.rb_floor_fully);
                                                                                                                if (radioButton8 != null) {
                                                                                                                    i = R.id.rb_floor_no;
                                                                                                                    RadioButton radioButton9 = (RadioButton) rootView.findViewById(R.id.rb_floor_no);
                                                                                                                    if (radioButton9 != null) {
                                                                                                                        i = R.id.rb_floor_partially;
                                                                                                                        RadioButton radioButton10 = (RadioButton) rootView.findViewById(R.id.rb_floor_partially);
                                                                                                                        if (radioButton10 != null) {
                                                                                                                            i = R.id.rb_occupied_no;
                                                                                                                            RadioButton radioButton11 = (RadioButton) rootView.findViewById(R.id.rb_occupied_no);
                                                                                                                            if (radioButton11 != null) {
                                                                                                                                i = R.id.rb_occupied_yes;
                                                                                                                                RadioButton radioButton12 = (RadioButton) rootView.findViewById(R.id.rb_occupied_yes);
                                                                                                                                if (radioButton12 != null) {
                                                                                                                                    i = R.id.rb_rain_flood_no;
                                                                                                                                    RadioButton radioButton13 = (RadioButton) rootView.findViewById(R.id.rb_rain_flood_no);
                                                                                                                                    if (radioButton13 != null) {
                                                                                                                                        i = R.id.rb_rain_flood_yes;
                                                                                                                                        RadioButton radioButton14 = (RadioButton) rootView.findViewById(R.id.rb_rain_flood_yes);
                                                                                                                                        if (radioButton14 != null) {
                                                                                                                                            i = R.id.rb_roof_fully;
                                                                                                                                            RadioButton radioButton15 = (RadioButton) rootView.findViewById(R.id.rb_roof_fully);
                                                                                                                                            if (radioButton15 != null) {
                                                                                                                                                i = R.id.rb_roof_no;
                                                                                                                                                RadioButton radioButton16 = (RadioButton) rootView.findViewById(R.id.rb_roof_no);
                                                                                                                                                if (radioButton16 != null) {
                                                                                                                                                    i = R.id.rb_roof_partially;
                                                                                                                                                    RadioButton radioButton17 = (RadioButton) rootView.findViewById(R.id.rb_roof_partially);
                                                                                                                                                    if (radioButton17 != null) {
                                                                                                                                                        i = R.id.rb_school_building_damage_fully;
                                                                                                                                                        RadioButton radioButton18 = (RadioButton) rootView.findViewById(R.id.rb_school_building_damage_fully);
                                                                                                                                                        if (radioButton18 != null) {
                                                                                                                                                            i = R.id.rb_school_building_damage_partially;
                                                                                                                                                            RadioButton radioButton19 = (RadioButton) rootView.findViewById(R.id.rb_school_building_damage_partially);
                                                                                                                                                            if (radioButton19 != null) {
                                                                                                                                                                i = R.id.rb_toilet_fully;
                                                                                                                                                                RadioButton radioButton20 = (RadioButton) rootView.findViewById(R.id.rb_toilet_fully);
                                                                                                                                                                if (radioButton20 != null) {
                                                                                                                                                                    i = R.id.rb_toilet_no;
                                                                                                                                                                    RadioButton radioButton21 = (RadioButton) rootView.findViewById(R.id.rb_toilet_no);
                                                                                                                                                                    if (radioButton21 != null) {
                                                                                                                                                                        i = R.id.rb_toilet_partially;
                                                                                                                                                                        RadioButton radioButton22 = (RadioButton) rootView.findViewById(R.id.rb_toilet_partially);
                                                                                                                                                                        if (radioButton22 != null) {
                                                                                                                                                                            i = R.id.rb_walls_of_rooms_fully;
                                                                                                                                                                            RadioButton radioButton23 = (RadioButton) rootView.findViewById(R.id.rb_walls_of_rooms_fully);
                                                                                                                                                                            if (radioButton23 != null) {
                                                                                                                                                                                i = R.id.rb_walls_of_rooms_no;
                                                                                                                                                                                RadioButton radioButton24 = (RadioButton) rootView.findViewById(R.id.rb_walls_of_rooms_no);
                                                                                                                                                                                if (radioButton24 != null) {
                                                                                                                                                                                    i = R.id.rb_walls_of_rooms_partially;
                                                                                                                                                                                    RadioButton radioButton25 = (RadioButton) rootView.findViewById(R.id.rb_walls_of_rooms_partially);
                                                                                                                                                                                    if (radioButton25 != null) {
                                                                                                                                                                                        i = R.id.reason_for_inaccessibility_layout;
                                                                                                                                                                                        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.reason_for_inaccessibility_layout);
                                                                                                                                                                                        if (linearLayout4 != null) {
                                                                                                                                                                                            i = R.id.rg_after_flood;
                                                                                                                                                                                            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_after_flood);
                                                                                                                                                                                            if (radioGroup != null) {
                                                                                                                                                                                                i = R.id.rg_boundary_wall;
                                                                                                                                                                                                RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_boundary_wall);
                                                                                                                                                                                                if (radioGroup2 != null) {
                                                                                                                                                                                                    i = R.id.rg_damage_done;
                                                                                                                                                                                                    RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rg_damage_done);
                                                                                                                                                                                                    if (radioGroup3 != null) {
                                                                                                                                                                                                        i = R.id.rg_floor;
                                                                                                                                                                                                        RadioGroup radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rg_floor);
                                                                                                                                                                                                        if (radioGroup4 != null) {
                                                                                                                                                                                                            i = R.id.rg_occupied;
                                                                                                                                                                                                            RadioGroup radioGroup5 = (RadioGroup) rootView.findViewById(R.id.rg_occupied);
                                                                                                                                                                                                            if (radioGroup5 != null) {
                                                                                                                                                                                                                i = R.id.rg_rain_flood;
                                                                                                                                                                                                                RadioGroup radioGroup6 = (RadioGroup) rootView.findViewById(R.id.rg_rain_flood);
                                                                                                                                                                                                                if (radioGroup6 != null) {
                                                                                                                                                                                                                    i = R.id.rg_roof;
                                                                                                                                                                                                                    RadioGroup radioGroup7 = (RadioGroup) rootView.findViewById(R.id.rg_roof);
                                                                                                                                                                                                                    if (radioGroup7 != null) {
                                                                                                                                                                                                                        i = R.id.rg_school_building_damage;
                                                                                                                                                                                                                        RadioGroup radioGroup8 = (RadioGroup) rootView.findViewById(R.id.rg_school_building_damage);
                                                                                                                                                                                                                        if (radioGroup8 != null) {
                                                                                                                                                                                                                            i = R.id.rg_toilet;
                                                                                                                                                                                                                            RadioGroup radioGroup9 = (RadioGroup) rootView.findViewById(R.id.rg_toilet);
                                                                                                                                                                                                                            if (radioGroup9 != null) {
                                                                                                                                                                                                                                i = R.id.rg_walls_of_rooms;
                                                                                                                                                                                                                                RadioGroup radioGroup10 = (RadioGroup) rootView.findViewById(R.id.rg_walls_of_rooms);
                                                                                                                                                                                                                                if (radioGroup10 != null) {
                                                                                                                                                                                                                                    i = R.id.roof_layout;
                                                                                                                                                                                                                                    RelativeLayout relativeLayout13 = (RelativeLayout) rootView.findViewById(R.id.roof_layout);
                                                                                                                                                                                                                                    if (relativeLayout13 != null) {
                                                                                                                                                                                                                                        i = R.id.roof_radio_layout;
                                                                                                                                                                                                                                        RelativeLayout relativeLayout14 = (RelativeLayout) rootView.findViewById(R.id.roof_radio_layout);
                                                                                                                                                                                                                                        if (relativeLayout14 != null) {
                                                                                                                                                                                                                                            i = R.id.save_btn;
                                                                                                                                                                                                                                            Button button = (Button) rootView.findViewById(R.id.save_btn);
                                                                                                                                                                                                                                            if (button != null) {
                                                                                                                                                                                                                                                i = R.id.school_building_damage_layout;
                                                                                                                                                                                                                                                RelativeLayout relativeLayout15 = (RelativeLayout) rootView.findViewById(R.id.school_building_damage_layout);
                                                                                                                                                                                                                                                if (relativeLayout15 != null) {
                                                                                                                                                                                                                                                    i = R.id.school_building_damage_radio_layout;
                                                                                                                                                                                                                                                    RelativeLayout relativeLayout16 = (RelativeLayout) rootView.findViewById(R.id.school_building_damage_radio_layout);
                                                                                                                                                                                                                                                    if (relativeLayout16 != null) {
                                                                                                                                                                                                                                                        i = R.id.scroll_view;
                                                                                                                                                                                                                                                        ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scroll_view);
                                                                                                                                                                                                                                                        if (scrollView != null) {
                                                                                                                                                                                                                                                            i = R.id.title;
                                                                                                                                                                                                                                                            TextView textView2 = (TextView) rootView.findViewById(R.id.title);
                                                                                                                                                                                                                                                            if (textView2 != null) {
                                                                                                                                                                                                                                                                i = R.id.toilet_layout;
                                                                                                                                                                                                                                                                RelativeLayout relativeLayout17 = (RelativeLayout) rootView.findViewById(R.id.toilet_layout);
                                                                                                                                                                                                                                                                if (relativeLayout17 != null) {
                                                                                                                                                                                                                                                                    i = R.id.toilet_radio_layout;
                                                                                                                                                                                                                                                                    RelativeLayout relativeLayout18 = (RelativeLayout) rootView.findViewById(R.id.toilet_radio_layout);
                                                                                                                                                                                                                                                                    if (relativeLayout18 != null) {
                                                                                                                                                                                                                                                                        i = R.id.tv_boundary_wall_layout;
                                                                                                                                                                                                                                                                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_boundary_wall_layout);
                                                                                                                                                                                                                                                                        if (textView3 != null) {
                                                                                                                                                                                                                                                                            i = R.id.tv_damage_done_layout;
                                                                                                                                                                                                                                                                            TextView textView4 = (TextView) rootView.findViewById(R.id.tv_damage_done_layout);
                                                                                                                                                                                                                                                                            if (textView4 != null) {
                                                                                                                                                                                                                                                                                i = R.id.tv_floor_layout;
                                                                                                                                                                                                                                                                                TextView textView5 = (TextView) rootView.findViewById(R.id.tv_floor_layout);
                                                                                                                                                                                                                                                                                if (textView5 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.tv_furniture_availability;
                                                                                                                                                                                                                                                                                    TextView textView6 = (TextView) rootView.findViewById(R.id.tv_furniture_availability);
                                                                                                                                                                                                                                                                                    if (textView6 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.tv_occupied_layout;
                                                                                                                                                                                                                                                                                        TextView textView7 = (TextView) rootView.findViewById(R.id.tv_occupied_layout);
                                                                                                                                                                                                                                                                                        if (textView7 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.tv_overall_observation;
                                                                                                                                                                                                                                                                                            TextView textView8 = (TextView) rootView.findViewById(R.id.tv_overall_observation);
                                                                                                                                                                                                                                                                                            if (textView8 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.tv_rain_flood_layout;
                                                                                                                                                                                                                                                                                                TextView textView9 = (TextView) rootView.findViewById(R.id.tv_rain_flood_layout);
                                                                                                                                                                                                                                                                                                if (textView9 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.tv_remarks;
                                                                                                                                                                                                                                                                                                    TextView textView10 = (TextView) rootView.findViewById(R.id.tv_remarks);
                                                                                                                                                                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.tv_roof_layout;
                                                                                                                                                                                                                                                                                                        TextView textView11 = (TextView) rootView.findViewById(R.id.tv_roof_layout);
                                                                                                                                                                                                                                                                                                        if (textView11 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.tv_school_building_damage_layout;
                                                                                                                                                                                                                                                                                                            TextView textView12 = (TextView) rootView.findViewById(R.id.tv_school_building_damage_layout);
                                                                                                                                                                                                                                                                                                            if (textView12 != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.tv_toilet_layout;
                                                                                                                                                                                                                                                                                                                TextView textView13 = (TextView) rootView.findViewById(R.id.tv_toilet_layout);
                                                                                                                                                                                                                                                                                                                if (textView13 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.tv_walls_of_rooms_layout;
                                                                                                                                                                                                                                                                                                                    TextView textView14 = (TextView) rootView.findViewById(R.id.tv_walls_of_rooms_layout);
                                                                                                                                                                                                                                                                                                                    if (textView14 != null) {
                                                                                                                                                                                                                                                                                                                        i = R.id.walls_of_rooms_layout;
                                                                                                                                                                                                                                                                                                                        RelativeLayout relativeLayout19 = (RelativeLayout) rootView.findViewById(R.id.walls_of_rooms_layout);
                                                                                                                                                                                                                                                                                                                        if (relativeLayout19 != null) {
                                                                                                                                                                                                                                                                                                                            i = R.id.walls_of_rooms_radio_layout;
                                                                                                                                                                                                                                                                                                                            RelativeLayout relativeLayout20 = (RelativeLayout) rootView.findViewById(R.id.walls_of_rooms_radio_layout);
                                                                                                                                                                                                                                                                                                                            if (relativeLayout20 != null) {
                                                                                                                                                                                                                                                                                                                                return new FragmentFloodSurveyBinding((RelativeLayout) rootView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, relativeLayout5, relativeLayout6, linearLayout2, editText, editText2, editText3, relativeLayout7, relativeLayout8, linearLayout3, relativeLayout9, relativeLayout10, relativeLayout11, relativeLayout12, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioButton13, radioButton14, radioButton15, radioButton16, radioButton17, radioButton18, radioButton19, radioButton20, radioButton21, radioButton22, radioButton23, radioButton24, radioButton25, linearLayout4, radioGroup, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6, radioGroup7, radioGroup8, radioGroup9, radioGroup10, relativeLayout13, relativeLayout14, button, relativeLayout15, relativeLayout16, scrollView, textView2, relativeLayout17, relativeLayout18, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, relativeLayout19, relativeLayout20);
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
