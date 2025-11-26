package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class Census extends BaseFragment implements View.OnClickListener {
    CardView cv_boundary_wall;
    CardView cv_classrooms;
    CardView cv_drinking_water;
    CardView cv_education_quality;
    CardView cv_electricity;
    CardView cv_furniture;
    CardView cv_girls_stipend;
    CardView cv_other_facilities;
    CardView cv_sanctioned_new_expenditure;
    CardView cv_school_building;
    CardView cv_textbooks;
    CardView cv_washroom;
    View rootView;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census, (ViewGroup) null);
        this.rootView = viewInflate;
        CardView cardView = (CardView) viewInflate.findViewById(R.id.cv_school_building);
        this.cv_school_building = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.rootView.findViewById(R.id.cv_boundary_wall);
        this.cv_boundary_wall = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.rootView.findViewById(R.id.cv_drinking_water);
        this.cv_drinking_water = cardView3;
        cardView3.setOnClickListener(this);
        CardView cardView4 = (CardView) this.rootView.findViewById(R.id.cv_furniture);
        this.cv_furniture = cardView4;
        cardView4.setOnClickListener(this);
        CardView cardView5 = (CardView) this.rootView.findViewById(R.id.cv_washroom);
        this.cv_washroom = cardView5;
        cardView5.setOnClickListener(this);
        CardView cardView6 = (CardView) this.rootView.findViewById(R.id.cv_electricity);
        this.cv_electricity = cardView6;
        cardView6.setOnClickListener(this);
        CardView cardView7 = (CardView) this.rootView.findViewById(R.id.cv_textbooks);
        this.cv_textbooks = cardView7;
        cardView7.setOnClickListener(this);
        CardView cardView8 = (CardView) this.rootView.findViewById(R.id.cv_classrooms);
        this.cv_classrooms = cardView8;
        cardView8.setOnClickListener(this);
        CardView cardView9 = (CardView) this.rootView.findViewById(R.id.cv_other_facilities);
        this.cv_other_facilities = cardView9;
        cardView9.setOnClickListener(this);
        CardView cardView10 = (CardView) this.rootView.findViewById(R.id.cv_girls_stipend);
        this.cv_girls_stipend = cardView10;
        cardView10.setOnClickListener(this);
        CardView cardView11 = (CardView) this.rootView.findViewById(R.id.cv_education_quality);
        this.cv_education_quality = cardView11;
        cardView11.setOnClickListener(this);
        CardView cardView12 = (CardView) this.rootView.findViewById(R.id.cv_sanctioned_new_expenditure);
        this.cv_sanctioned_new_expenditure = cardView12;
        cardView12.setOnClickListener(this);
        this.cv_sanctioned_new_expenditure.setVisibility(8);
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_building.entrySet().iterator();
        if (it.hasNext()) {
            if (it.next().getValue().getActiveind().booleanValue()) {
                this.cv_school_building.setVisibility(0);
            } else {
                this.cv_school_building.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it2 = CommonObjects.boundary_wall.entrySet().iterator();
        if (it2.hasNext()) {
            if (it2.next().getValue().getActiveind().booleanValue()) {
                this.cv_boundary_wall.setVisibility(0);
            } else {
                this.cv_boundary_wall.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it3 = CommonObjects.drinking_water.entrySet().iterator();
        if (it3.hasNext()) {
            if (it3.next().getValue().getActiveind().booleanValue()) {
                this.cv_drinking_water.setVisibility(0);
            } else {
                this.cv_drinking_water.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it4 = CommonObjects.furniture.entrySet().iterator();
        if (it4.hasNext()) {
            if (it4.next().getValue().getActiveind().booleanValue()) {
                this.cv_furniture.setVisibility(0);
            } else {
                this.cv_furniture.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it5 = CommonObjects.washroom.entrySet().iterator();
        if (it5.hasNext()) {
            if (it5.next().getValue().getActiveind().booleanValue()) {
                this.cv_washroom.setVisibility(0);
            } else {
                this.cv_washroom.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it6 = CommonObjects.electricity.entrySet().iterator();
        if (it6.hasNext()) {
            if (it6.next().getValue().getActiveind().booleanValue()) {
                this.cv_electricity.setVisibility(0);
            } else {
                this.cv_electricity.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it7 = CommonObjects.textbooks.entrySet().iterator();
        if (it7.hasNext()) {
            if (it7.next().getValue().getActiveind().booleanValue()) {
                this.cv_textbooks.setVisibility(0);
            } else {
                this.cv_textbooks.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it8 = CommonObjects.classrooms.entrySet().iterator();
        if (it8.hasNext()) {
            if (it8.next().getValue().getActiveind().booleanValue()) {
                this.cv_classrooms.setVisibility(0);
            } else {
                this.cv_classrooms.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it9 = CommonObjects.other_facilities.entrySet().iterator();
        if (it9.hasNext()) {
            if (it9.next().getValue().getActiveind().booleanValue()) {
                this.cv_other_facilities.setVisibility(0);
            } else {
                this.cv_other_facilities.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it10 = CommonObjects.girls_stipend.entrySet().iterator();
        if (it10.hasNext()) {
            if (it10.next().getValue().getActiveind().booleanValue()) {
                this.cv_girls_stipend.setVisibility(0);
            } else {
                this.cv_girls_stipend.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it11 = CommonObjects.education_quality.entrySet().iterator();
        if (it11.hasNext()) {
            if (it11.next().getValue().getActiveind().booleanValue()) {
                this.cv_education_quality.setVisibility(0);
            } else {
                this.cv_education_quality.setVisibility(8);
            }
        }
    }

    private void checkFormStatus() {
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_building.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getKRAName().equals(Constant.Is_Completed) && !value.getDataValue().equals("")) {
                this.cv_school_building.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("school_building", value.getKRAName() + " = " + value.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.boundary_wall.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value2 = it.next().getValue();
            if (value2.getKRAName().equals(Constant.Is_Completed) && !value2.getDataValue().equals("")) {
                this.cv_boundary_wall.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("school_building", value2.getKRAName() + " = " + value2.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it2 = CommonObjects.drinking_water.entrySet().iterator();
        while (it2.hasNext()) {
            KRAData value3 = it2.next().getValue();
            if (value3.getKRAName().equals(Constant.Is_Completed) && !value3.getDataValue().equals("")) {
                this.cv_drinking_water.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("school_building", value3.getKRAName() + " = " + value3.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it3 = CommonObjects.furniture.entrySet().iterator();
        while (it3.hasNext()) {
            KRAData value4 = it3.next().getValue();
            if (value4.getKRAName().equals(Constant.Is_Completed) && !value4.getDataValue().equals("")) {
                this.cv_furniture.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("school_building", value4.getKRAName() + " = " + value4.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it4 = CommonObjects.washroom.entrySet().iterator();
        while (it4.hasNext()) {
            KRAData value5 = it4.next().getValue();
            if (value5.getKRAName().equals(Constant.Is_Completed) && !value5.getDataValue().equals("")) {
                this.cv_washroom.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("washroom", value5.getKRAName() + " = " + value5.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it5 = CommonObjects.electricity.entrySet().iterator();
        while (it5.hasNext()) {
            KRAData value6 = it5.next().getValue();
            if (value6.getKRAName().equals(Constant.Is_Completed) && !value6.getDataValue().equals("")) {
                this.cv_electricity.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("electricity", value6.getKRAName() + " = " + value6.getDataValue());
        }
        int i = 0;
        Iterator<Map.Entry<Integer, KRAData>> it6 = CommonObjects.textbooks.entrySet().iterator();
        while (it6.hasNext()) {
            KRAData value7 = it6.next().getValue();
            if (checkTextBooksClasses(value7.getKRAName()) && !value7.getDataValue().equals("") && (i = i + 1) >= 12) {
                this.cv_textbooks.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("textbooks", value7.getKRAName() + " = " + value7.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it7 = CommonObjects.classrooms.entrySet().iterator();
        while (it7.hasNext()) {
            KRAData value8 = it7.next().getValue();
            if (value8.getKRAName().equals(Constant.Is_Completed) && !value8.getDataValue().equals("")) {
                this.cv_classrooms.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("classrooms", value8.getKRAName() + " = " + value8.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it8 = CommonObjects.other_facilities.entrySet().iterator();
        while (it8.hasNext()) {
            KRAData value9 = it8.next().getValue();
            if (value9.getKRAName().equals(Constant.Is_Completed) && !value9.getDataValue().equals("")) {
                this.cv_other_facilities.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("other_facilities", value9.getKRAName() + " = " + value9.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it9 = CommonObjects.girls_stipend.entrySet().iterator();
        while (it9.hasNext()) {
            KRAData value10 = it9.next().getValue();
            if (value10.getKRAName().equals(Constant.Is_Completed) && !value10.getDataValue().equals("")) {
                this.cv_girls_stipend.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("girls_stipend", value10.getKRAName() + " = " + value10.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it10 = CommonObjects.education_quality.entrySet().iterator();
        while (it10.hasNext()) {
            KRAData value11 = it10.next().getValue();
            if (value11.getKRAName().equals(Constant.Is_Completed) && !value11.getDataValue().equals("")) {
                this.cv_education_quality.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("education_quality", value11.getKRAName() + " = " + value11.getDataValue());
        }
        Iterator<Map.Entry<Integer, KRAData>> it11 = CommonObjects.sanctioned_new_expenditure.entrySet().iterator();
        while (it11.hasNext()) {
            KRAData value12 = it11.next().getValue();
            if (value12.getKRAName().equals(Constant.Is_Completed) && !value12.getDataValue().equals("")) {
                this.cv_sanctioned_new_expenditure.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            Log.e("sanctioned_new_expend", value12.getKRAName() + " = " + value12.getDataValue());
        }
    }

    private boolean checkTextBooksClasses(String isCompleteText) {
        isCompleteText.hashCode();
        switch (isCompleteText) {
            case "Is_Completed_10":
            case "Is_Completed_11":
            case "Is_Completed_12":
            case "Is_Completed_1":
            case "Is_Completed_2":
            case "Is_Completed_3":
            case "Is_Completed_4":
            case "Is_Completed_5":
            case "Is_Completed_6":
            case "Is_Completed_7":
            case "Is_Completed_8":
            case "Is_Completed_9":
                return true;
            default:
                return false;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        checkFormStatus();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_boundary_wall /* 2131230957 */:
                changeFragment(new BoundaryWall(Constant.Boundary_Wall));
                break;
            case R.id.cv_classrooms /* 2131230971 */:
                changeFragment(new Classrooms());
                break;
            case R.id.cv_drinking_water /* 2131230974 */:
                changeFragment(new DrinkingWater(Constant.Drinking_Water));
                break;
            case R.id.cv_education_quality /* 2131230975 */:
                changeFragment(new EducationQuality());
                break;
            case R.id.cv_electricity /* 2131230976 */:
                changeFragment(new Electricity(Constant.Electricity));
                break;
            case R.id.cv_furniture /* 2131230978 */:
                changeFragment(new Furniture(Constant.Furniture));
                break;
            case R.id.cv_girls_stipend /* 2131230979 */:
                Log.e("SchoolGenderTypeId", CommonObjects.monitoring.getSchoolGenderTypeId() + "_");
                if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 1) {
                    DialogCustom.showError(getActivityContext(), "Not applicable.");
                    break;
                } else {
                    changeFragment(new GirlsStipend());
                    break;
                }
            case R.id.cv_other_facilities /* 2131230985 */:
                changeFragment(new OtherFacilities());
                break;
            case R.id.cv_sanctioned_new_expenditure /* 2131230987 */:
                changeFragment(new SanctionedNewExpenditure());
                break;
            case R.id.cv_school_building /* 2131230988 */:
                changeFragment(new SchoolBuilding());
                break;
            case R.id.cv_textbooks /* 2131230990 */:
                changeFragment(new Textbooks());
                break;
            case R.id.cv_washroom /* 2131230991 */:
                changeFragment(new Washroom(Constant.Washroom));
                break;
        }
    }

    void changeFragment(Fragment fragment) {
        if (this.ft != null) {
            try {
                this.ft = this.fm.beginTransaction();
                this.ft.replace(R.id.container, fragment);
                this.ft.addToBackStack(null);
                this.ft.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
