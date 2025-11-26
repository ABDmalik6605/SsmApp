package com.micromerger.ssms.startmonitoring.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class SchoolBuilding extends BaseFragment implements BaseFragment.CameraActivityCallBack, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button cancel_button;
    EditText editText_comments;
    EditText et_adoption_year;
    EditText et_if_shared_with_no_building;
    EditText et_name_of_adopter;
    EditText et_other_gov_school;
    EditText et_rooms_affected;
    boolean isOtherRemarks;
    boolean isPreviewVisible;
    View mView;
    ImageView previewImage;
    RadioGroup rg_adopted_school;
    RadioGroup rg_builiding_illegal;
    RelativeLayout rl_comments;
    LinearLayout rl_illegal_building;
    RelativeLayout rl_ramarks;
    RelativeLayout rv_adoption_year;
    RelativeLayout rv_condition;
    RelativeLayout rv_if_shared_with_no_building;
    RelativeLayout rv_name_of_adopter;
    RelativeLayout rv_other_gov_school;
    RelativeLayout rv_rooms_affected;
    RelativeLayout rv_school_is_placed;
    RelativeLayout rv_type_of_building;
    Button save_button;
    Spinner sp_condition;
    Spinner sp_ownership;
    Spinner sp_remarks;
    Spinner sp_school_is_placed;
    Spinner sp_type_of_building;
    ImageView uploadImage;
    int buildingillegalOcp = 1;
    int remarksId = 0;
    String remarksComments = "";
    String imageBaseUrl64 = "";
    boolean isCapturing = false;
    List<ReferenceDataResponse.ReferenceData> OWNERSHIP_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> SCHOOL_PLACE_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> BUILDING_TYPE_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> BUILDING_CONDITION_LIST = new ArrayList();
    List<ReferenceDataResponse.ReferenceData> REMARKS_LIST = new ArrayList();
    int ownershipId = 0;
    String otherSchoolComment = "";
    String noBuildingComment = "";
    int schoolPlaceId = 0;
    int adoptedSchoolId = -1;
    String adopterName = "";
    String adoptionYear = "";
    int typeOfBuildingId = 0;
    int conditionId = 0;
    boolean isNoBuilding = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_census_school_building, (ViewGroup) null);
        setCameraActivityCallBack(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.rl_illegal_building);
        this.rl_illegal_building = linearLayout;
        linearLayout.setVisibility(0);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_ramarks);
        this.rl_ramarks = relativeLayout;
        relativeLayout.setVisibility(8);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.rl_comments);
        this.rl_comments = relativeLayout2;
        relativeLayout2.setVisibility(8);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_builiding_illegal);
        this.rg_builiding_illegal = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(8);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SchoolBuilding.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    SchoolBuilding.this.getPhotoFromCamera(iIntValue + "_28_" + Constant.Monitoring_Image);
                } catch (Exception unused) {
                    SchoolBuilding.this.isCapturing = false;
                    Toast.makeText(SchoolBuilding.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editText_comments) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.rv_other_gov_school);
        this.rv_other_gov_school = relativeLayout3;
        relativeLayout3.setVisibility(8);
        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.rv_if_shared_with_no_building);
        this.rv_if_shared_with_no_building = relativeLayout4;
        relativeLayout4.setVisibility(8);
        RelativeLayout relativeLayout5 = (RelativeLayout) view.findViewById(R.id.rv_school_is_placed);
        this.rv_school_is_placed = relativeLayout5;
        relativeLayout5.setVisibility(8);
        RelativeLayout relativeLayout6 = (RelativeLayout) view.findViewById(R.id.rv_name_of_adopter);
        this.rv_name_of_adopter = relativeLayout6;
        relativeLayout6.setVisibility(8);
        RelativeLayout relativeLayout7 = (RelativeLayout) view.findViewById(R.id.rv_adoption_year);
        this.rv_adoption_year = relativeLayout7;
        relativeLayout7.setVisibility(8);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_adopted_school);
        this.rg_adopted_school = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        RelativeLayout relativeLayout8 = (RelativeLayout) view.findViewById(R.id.rv_type_of_building);
        this.rv_type_of_building = relativeLayout8;
        relativeLayout8.setVisibility(8);
        RelativeLayout relativeLayout9 = (RelativeLayout) view.findViewById(R.id.rv_condition);
        this.rv_condition = relativeLayout9;
        relativeLayout9.setVisibility(8);
        RelativeLayout relativeLayout10 = (RelativeLayout) view.findViewById(R.id.rv_rooms_affected);
        this.rv_rooms_affected = relativeLayout10;
        relativeLayout10.setVisibility(8);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.3
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.BUILDING_OWNERSHIP);
            }
        });
        if (collectionSelect instanceof List) {
            this.OWNERSHIP_LIST = (List) collectionSelect;
        } else {
            this.OWNERSHIP_LIST = new ArrayList(collectionSelect);
        }
        Spinner spinner = (Spinner) this.mView.findViewById(R.id.sp_ownership);
        this.sp_ownership = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                SchoolBuilding.this.ownershipId = position;
                SchoolBuilding.this.isNoBuilding = false;
                SchoolBuilding.this.rv_other_gov_school.setVisibility(8);
                SchoolBuilding.this.rv_if_shared_with_no_building.setVisibility(8);
                SchoolBuilding.this.rv_school_is_placed.setVisibility(8);
                SchoolBuilding.this.rv_type_of_building.setVisibility(0);
                SchoolBuilding.this.rv_condition.setVisibility(0);
                SchoolBuilding.this.rl_illegal_building.setVisibility(0);
                if (SchoolBuilding.this.OWNERSHIP_LIST.get(position).getReferencevalue().equals(Constant.Shared_building)) {
                    SchoolBuilding.this.rv_other_gov_school.setVisibility(0);
                    return;
                }
                if (SchoolBuilding.this.OWNERSHIP_LIST.get(position).getReferencevalue().equals(Constant.Any_Other_Building)) {
                    SchoolBuilding.this.rv_other_gov_school.setVisibility(0);
                    return;
                }
                if (SchoolBuilding.this.OWNERSHIP_LIST.get(position).getReferencevalue().equals(Constant.No_Building)) {
                    SchoolBuilding.this.rv_if_shared_with_no_building.setVisibility(0);
                    SchoolBuilding.this.rv_school_is_placed.setVisibility(0);
                    SchoolBuilding.this.isNoBuilding = true;
                    SchoolBuilding.this.rl_illegal_building.setVisibility(8);
                    SchoolBuilding.this.rv_type_of_building.setVisibility(8);
                    SchoolBuilding.this.rv_condition.setVisibility(8);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(SchoolBuilding.this.getActivityContext(), SchoolBuilding.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_ownership.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.OWNERSHIP_LIST));
        Collection collectionSelect2 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.5
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.SCHOOL_LOCATION_TYPE);
            }
        });
        if (collectionSelect2 instanceof List) {
            this.SCHOOL_PLACE_LIST = (List) collectionSelect2;
        } else {
            this.SCHOOL_PLACE_LIST = new ArrayList(collectionSelect2);
        }
        Spinner spinner2 = (Spinner) this.mView.findViewById(R.id.sp_school_is_placed);
        this.sp_school_is_placed = spinner2;
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                SchoolBuilding.this.schoolPlaceId = position;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(SchoolBuilding.this.getActivityContext(), SchoolBuilding.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_school_is_placed.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.SCHOOL_PLACE_LIST));
        Collection collectionSelect3 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.7
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.BUILDING_TYPE);
            }
        });
        if (collectionSelect3 instanceof List) {
            this.BUILDING_TYPE_LIST = (List) collectionSelect3;
        } else {
            this.BUILDING_TYPE_LIST = new ArrayList(collectionSelect3);
        }
        Spinner spinner3 = (Spinner) this.mView.findViewById(R.id.sp_type_of_building);
        this.sp_type_of_building = spinner3;
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.8
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                SchoolBuilding.this.typeOfBuildingId = position;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(SchoolBuilding.this.getActivityContext(), SchoolBuilding.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_type_of_building.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.BUILDING_TYPE_LIST));
        Collection collectionSelect4 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolBuilding$VGT4dW2NuYbKtNgLlnCEIkHVWO0
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals(Constant.SCHOOL_BUILDING_CONDITION);
            }
        });
        if (collectionSelect4 instanceof List) {
            this.BUILDING_CONDITION_LIST = (List) collectionSelect4;
        } else {
            this.BUILDING_CONDITION_LIST = new ArrayList(collectionSelect4);
        }
        Spinner spinner4 = (Spinner) this.mView.findViewById(R.id.sp_condition);
        this.sp_condition = spinner4;
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view2, int position, long id2) {
                for (ReferenceDataResponse.ReferenceData referenceData : SchoolBuilding.this.BUILDING_CONDITION_LIST) {
                    if (referenceData.getSortOrder() == position) {
                        SchoolBuilding.this.conditionId = referenceData.getReferencekey().intValue();
                        if (SchoolBuilding.this.conditionId == 2 || SchoolBuilding.this.conditionId == 3 || SchoolBuilding.this.conditionId == 5 || SchoolBuilding.this.conditionId == 6) {
                            SchoolBuilding.this.rv_rooms_affected.setVisibility(0);
                            return;
                        } else {
                            SchoolBuilding.this.rv_rooms_affected.setVisibility(8);
                            return;
                        }
                    }
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                CommonActions.hideSoftKeyboard(SchoolBuilding.this.getActivityContext(), SchoolBuilding.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_condition.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.BUILDING_CONDITION_LIST));
        Collection collectionSelect5 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.10
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.ILLEGAL_OCCUPATION_REASON);
            }
        });
        if (collectionSelect5 instanceof List) {
            this.REMARKS_LIST = (List) collectionSelect5;
        } else {
            this.REMARKS_LIST = new ArrayList(collectionSelect5);
        }
        Spinner spinner5 = (Spinner) this.mView.findViewById(R.id.sp_remarks);
        this.sp_remarks = spinner5;
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.11
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                SchoolBuilding.this.remarksId = position;
                if (SchoolBuilding.this.REMARKS_LIST.get(position).getReferencevalue().equals(Constant.OTHER_ILLEGAL_OCCUPATION_REASON)) {
                    SchoolBuilding.this.isOtherRemarks = true;
                    SchoolBuilding.this.rl_comments.setVisibility(0);
                } else {
                    SchoolBuilding.this.isOtherRemarks = false;
                    SchoolBuilding.this.rl_comments.setVisibility(8);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(SchoolBuilding.this.getActivityContext(), SchoolBuilding.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_remarks.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.REMARKS_LIST));
        EditText editText2 = (EditText) view.findViewById(R.id.et_other_gov_school);
        this.et_other_gov_school = editText2;
        editText2.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_other_gov_school) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        EditText editText3 = (EditText) view.findViewById(R.id.et_if_shared_with_no_building);
        this.et_if_shared_with_no_building = editText3;
        editText3.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_if_shared_with_no_building) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        this.et_name_of_adopter = (EditText) view.findViewById(R.id.et_name_of_adopter);
        this.et_adoption_year = (EditText) view.findViewById(R.id.et_adoption_year);
        this.et_rooms_affected = (EditText) view.findViewById(R.id.et_rooms_affected);
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
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        List<ReferenceDataResponse.ReferenceData> list = this.BUILDING_CONDITION_LIST;
        if (list == null || list.isEmpty()) {
            Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$SchoolBuilding$J_Gv7Nc8n-NFeTtZ6fhizxeIQjc
                @Override // org.apache.commons.collections4.Predicate
                public final boolean evaluate(Object obj) {
                    return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals(Constant.SCHOOL_BUILDING_CONDITION);
                }
            });
            if (collectionSelect instanceof List) {
                this.BUILDING_CONDITION_LIST = (List) collectionSelect;
            } else {
                this.BUILDING_CONDITION_LIST = new ArrayList(collectionSelect);
            }
        }
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_building.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            Log.e("school_building", value.getKRAName());
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Ownership)) {
                    int i = Integer.parseInt((String) value.getDataValue());
                    this.ownershipId = i;
                    if (i < this.OWNERSHIP_LIST.size()) {
                        this.sp_ownership.setSelection(this.ownershipId);
                    }
                } else if (value.getKRAName().equals(Constant.Comments)) {
                    this.otherSchoolComment = (String) value.getDataValue();
                    this.noBuildingComment = (String) value.getDataValue();
                    this.et_other_gov_school.setText(this.otherSchoolComment);
                    this.et_if_shared_with_no_building.setText(this.noBuildingComment);
                } else if (value.getKRAName().equals(Constant.SchoolPlacement)) {
                    int i2 = Integer.parseInt((String) value.getDataValue());
                    this.schoolPlaceId = i2;
                    if (i2 < this.SCHOOL_PLACE_LIST.size()) {
                        this.sp_school_is_placed.setSelection(this.schoolPlaceId);
                    }
                } else if (value.getKRAName().equals(Constant.IsAdapted)) {
                    int i3 = Integer.parseInt((String) value.getDataValue());
                    this.adoptedSchoolId = i3;
                    ((RadioButton) this.rg_adopted_school.getChildAt(i3)).setChecked(true);
                } else if (value.getKRAName().equals(Constant.AdopterName)) {
                    String str = (String) value.getDataValue();
                    this.adopterName = str;
                    this.et_name_of_adopter.setText(str);
                } else if (value.getKRAName().equals(Constant.AdoptionYear)) {
                    String str2 = (String) value.getDataValue();
                    this.adoptionYear = str2;
                    this.et_adoption_year.setText(str2);
                } else if (value.getKRAName().equals(Constant.BuildingType)) {
                    int i4 = Integer.parseInt((String) value.getDataValue());
                    this.typeOfBuildingId = i4;
                    if (i4 < this.BUILDING_TYPE_LIST.size()) {
                        this.sp_type_of_building.setSelection(this.typeOfBuildingId);
                    }
                } else if (value.getKRAName().equals(Constant.SchoolBuildingCondition)) {
                    try {
                        this.conditionId = Integer.parseInt((String) value.getDataValue());
                        Log.d("conditionId", "onResume: " + this.conditionId);
                        Iterator<ReferenceDataResponse.ReferenceData> it = this.BUILDING_CONDITION_LIST.iterator();
                        int i5 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (it.next().getReferencekey().intValue() == this.conditionId && i5 < this.BUILDING_CONDITION_LIST.size()) {
                                this.sp_condition.setSelection(i5);
                                break;
                            }
                            i5++;
                        }
                    } catch (Exception e) {
                        Log.e("conditionId", "onResume: " + e);
                        util.logException(new Exception("School Building ConditionL " + e));
                    }
                } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals(Constant.Illegal_Occupation)) {
                    int i6 = Integer.parseInt((String) value.getDataValue());
                    this.buildingillegalOcp = i6;
                    ((RadioButton) this.rg_builiding_illegal.getChildAt(i6)).setChecked(true);
                    if (this.buildingillegalOcp == 0) {
                        this.isPreviewVisible = true;
                        this.previewImage.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Remarks_Id)) {
                    int i7 = Integer.parseInt((String) value.getDataValue());
                    this.remarksId = i7;
                    if (i7 < this.REMARKS_LIST.size()) {
                        this.sp_remarks.setSelection(this.remarksId);
                    }
                    if (this.REMARKS_LIST.get(this.remarksId).getReferencevalue().equals(Constant.OTHER_ILLEGAL_OCCUPATION_REASON)) {
                        this.isOtherRemarks = true;
                        this.rl_comments.setVisibility(0);
                    }
                } else if (value.getKRAName().equals(Constant.Remarks_Comments)) {
                    String str3 = (String) value.getDataValue();
                    this.remarksComments = str3;
                    this.editText_comments.setText(str3);
                } else if (value.getKRAName().equals(Constant.Monitoring_Image)) {
                    this.imageBaseUrl64 = (String) value.getDataValue();
                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBaseUrl64));
                    if (bitmapDecodeFile != null) {
                        this.previewImage.setImageBitmap(bitmapDecodeFile);
                    }
                    if (this.imageBaseUrl64.length() > 0) {
                        this.isPreviewVisible = true;
                    }
                } else if (value.getKRAName().equals(Constant.Image_Date)) {
                    Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                } else if (value.getKRAName().equals("Rooms_Affected_Count")) {
                    this.et_rooms_affected.setText((String) value.getDataValue());
                }
            }
        }
        if (this.buildingillegalOcp == 0) {
            if (this.REMARKS_LIST.get(this.remarksId).getReferencevalue().equals(Constant.OTHER_ILLEGAL_OCCUPATION_REASON)) {
                this.rl_comments.setVisibility(0);
            } else {
                this.rl_comments.setVisibility(8);
            }
            if (this.imageBaseUrl64.length() > 0) {
                this.previewImage.setVisibility(0);
            } else {
                this.previewImage.setVisibility(8);
            }
        } else {
            this.rl_comments.setVisibility(8);
            this.previewImage.setVisibility(8);
        }
        if (this.OWNERSHIP_LIST.get(this.ownershipId).getReferencevalue().equals(Constant.Shared_building) || this.OWNERSHIP_LIST.get(this.ownershipId).getReferencevalue().equals(Constant.Any_Other_Building)) {
            this.rv_other_gov_school.setVisibility(0);
        }
        if (this.OWNERSHIP_LIST.get(this.ownershipId).getReferencevalue().equals(Constant.No_Building)) {
            this.isNoBuilding = true;
            this.rl_illegal_building.setVisibility(8);
            this.rv_if_shared_with_no_building.setVisibility(0);
            this.rv_school_is_placed.setVisibility(0);
            this.rv_type_of_building.setVisibility(8);
            this.rv_condition.setVisibility(8);
        }
        if (this.adoptedSchoolId == 0) {
            this.rv_name_of_adopter.setVisibility(0);
            this.rv_adoption_year.setVisibility(0);
        }
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.sp_ownership.setEnabled(false);
        this.sp_ownership.setFocusable(false);
        this.et_if_shared_with_no_building.setEnabled(false);
        this.et_if_shared_with_no_building.setFocusable(false);
        this.et_other_gov_school.setEnabled(false);
        this.et_other_gov_school.setFocusable(false);
        this.sp_school_is_placed.setEnabled(false);
        this.sp_school_is_placed.setFocusable(false);
        this.sp_condition.setEnabled(false);
        this.sp_condition.setFocusable(false);
        this.rg_adopted_school.setEnabled(false);
        this.rg_adopted_school.setFocusable(false);
        for (int i8 = 0; i8 < this.rg_adopted_school.getChildCount(); i8++) {
            ((RadioButton) this.rg_adopted_school.getChildAt(i8)).setFocusable(false);
            ((RadioButton) this.rg_adopted_school.getChildAt(i8)).setEnabled(false);
        }
        this.et_name_of_adopter.setEnabled(false);
        this.et_name_of_adopter.setFocusable(false);
        this.et_adoption_year.setEnabled(false);
        this.et_adoption_year.setFocusable(false);
        this.sp_type_of_building.setEnabled(false);
        this.sp_type_of_building.setFocusable(false);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
        this.rg_builiding_illegal.setEnabled(false);
        this.rg_builiding_illegal.setFocusable(false);
        for (int i9 = 0; i9 < this.rg_builiding_illegal.getChildCount(); i9++) {
            ((RadioButton) this.rg_builiding_illegal.getChildAt(i9)).setFocusable(false);
            ((RadioButton) this.rg_builiding_illegal.getChildAt(i9)).setEnabled(false);
        }
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.sp_remarks.setEnabled(false);
        this.sp_remarks.setFocusable(false);
        this.editText_comments.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.et_rooms_affected.setEnabled(false);
        this.et_rooms_affected.setFocusable(false);
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
        }
        if (id2 != R.id.save_button) {
            return;
        }
        this.otherSchoolComment = this.et_other_gov_school.getText().toString();
        this.noBuildingComment = this.et_if_shared_with_no_building.getText().toString();
        this.remarksComments = this.editText_comments.getText().toString();
        this.adopterName = this.et_name_of_adopter.getText().toString();
        this.adoptionYear = this.et_adoption_year.getText().toString();
        String referencevalue = this.OWNERSHIP_LIST.get(this.ownershipId).getReferencevalue();
        referencevalue.hashCode();
        switch (referencevalue) {
            case "No Building":
                if (!this.noBuildingComment.equals("")) {
                    if (!this.SCHOOL_PLACE_LIST.get(this.schoolPlaceId).getReferencevalue().equals(Constant.Select)) {
                        checkSchoolAdoptionValiddation();
                        break;
                    } else {
                        DialogCustom.showError(getActivityContext(), "Please select school location type.");
                        break;
                    }
                } else {
                    this.et_if_shared_with_no_building.setError("Please fill the field.");
                    DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
                    break;
                }
            case "Select":
                DialogCustom.showError(getActivityContext(), "Please select the building ownership.");
                break;
            case "Shared with Other Government school building (SEMIS Code)":
            case "Any Other Building":
                if (!this.otherSchoolComment.equals("")) {
                    if (!this.BUILDING_TYPE_LIST.get(this.typeOfBuildingId).getReferencevalue().equals(Constant.Select)) {
                        if (!this.BUILDING_CONDITION_LIST.get(this.conditionId).getReferencevalue().equals(Constant.Select)) {
                            checkBuildingValidation();
                            break;
                        } else {
                            DialogCustom.showError(getActivityContext(), "Please select school building condition.");
                            break;
                        }
                    } else {
                        DialogCustom.showError(getActivityContext(), "Please select building condition.");
                        break;
                    }
                } else {
                    this.et_other_gov_school.setError("Please fill the field.");
                    DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
                    break;
                }
            default:
                if (!this.BUILDING_CONDITION_LIST.get(this.conditionId).getReferencevalue().equals(Constant.Select)) {
                    checkBuildingValidation();
                    break;
                } else {
                    DialogCustom.showError(getActivityContext(), "Please select school building condition.");
                    break;
                }
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_is_this_school_adopted_no /* 2131231533 */:
                this.adoptedSchoolId = 1;
                this.rv_name_of_adopter.setVisibility(8);
                this.rv_adoption_year.setVisibility(8);
                break;
            case R.id.rb_is_this_school_adopted_yes /* 2131231534 */:
                this.adoptedSchoolId = 0;
                this.rv_name_of_adopter.setVisibility(0);
                this.rv_adoption_year.setVisibility(0);
                break;
            case R.id.rb_no /* 2131231547 */:
                this.buildingillegalOcp = 1;
                this.rl_ramarks.setVisibility(8);
                this.rl_comments.setVisibility(8);
                this.previewImage.setVisibility(8);
                this.uploadImage.setVisibility(8);
                break;
            case R.id.rb_yes /* 2131231607 */:
                this.buildingillegalOcp = 0;
                this.rl_ramarks.setVisibility(0);
                if (this.isOtherRemarks) {
                    this.rl_comments.setVisibility(0);
                } else {
                    this.rl_comments.setVisibility(8);
                }
                if (this.isPreviewVisible) {
                    this.previewImage.setVisibility(0);
                } else {
                    this.previewImage.setVisibility(8);
                }
                this.uploadImage.setVisibility(0);
                break;
        }
    }

    private void checkBuildingValidation() {
        int i = this.conditionId;
        if ((i == 2 || i == 3 || i == 5 || i == 6) && TextUtils.isEmpty(this.et_rooms_affected.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), "Please enter No. of rooms affected");
            this.et_rooms_affected.setError("This field is required");
            return;
        }
        int i2 = this.buildingillegalOcp;
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            checkSchoolAdoptionValiddation();
        } else {
            if (!this.REMARKS_LIST.get(this.remarksId).getReferencevalue().equals(Constant.Select)) {
                if (!this.REMARKS_LIST.get(this.remarksId).getReferencevalue().equals(Constant.OTHER_ILLEGAL_OCCUPATION_REASON)) {
                    checkSchoolAdoptionValiddation();
                    return;
                } else if (this.editText_comments.getText().length() > 0) {
                    checkSchoolAdoptionValiddation();
                    return;
                } else {
                    this.editText_comments.setError("Please fill the field.");
                    DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
                    return;
                }
            }
            DialogCustom.showError(getActivityContext(), "Please select illegal occupation remarks.");
        }
    }

    private void checkSchoolAdoptionValiddation() {
        int i = this.adoptedSchoolId;
        if (i == 0 || i == 1) {
            saveFieldsData();
        } else {
            DialogCustom.showError(getActivityContext(), "Please select adoption of school.");
        }
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
            return;
        }
        if (this.et_other_gov_school.getText().toString().length() > 0 || this.et_if_shared_with_no_building.getText().length() > 0 || this.et_name_of_adopter.getText().toString().length() > 0 || this.et_adoption_year.getText().toString().length() > 0 || this.adoptedSchoolId > -1 || this.buildingillegalOcp > -1 || this.remarksComments.length() > 0 || this.imageBaseUrl64.length() > 0) {
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
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.SchoolBuilding.14
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_building.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Ownership)) {
                        value.setDataValue(String.valueOf(SchoolBuilding.this.ownershipId));
                    } else if (value.getKRAName().equals(Constant.Comments)) {
                        if (SchoolBuilding.this.OWNERSHIP_LIST.get(SchoolBuilding.this.ownershipId).getReferencevalue().equals(Constant.Shared_building) || SchoolBuilding.this.OWNERSHIP_LIST.get(SchoolBuilding.this.ownershipId).getReferencevalue().equals(Constant.Any_Other_Building)) {
                            value.setDataValue(SchoolBuilding.this.otherSchoolComment);
                        } else if (SchoolBuilding.this.OWNERSHIP_LIST.get(SchoolBuilding.this.ownershipId).getReferencevalue().equals(Constant.No_Building)) {
                            value.setDataValue(SchoolBuilding.this.noBuildingComment);
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.SchoolPlacement)) {
                        if (SchoolBuilding.this.OWNERSHIP_LIST.get(SchoolBuilding.this.ownershipId).getReferencevalue().equals(Constant.No_Building)) {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.schoolPlaceId));
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.IsAdapted)) {
                        if (SchoolBuilding.this.adoptedSchoolId == -1) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.adoptedSchoolId));
                        }
                    } else if (value.getKRAName().equals(Constant.AdopterName)) {
                        if (SchoolBuilding.this.adoptedSchoolId == 0) {
                            value.setDataValue(SchoolBuilding.this.adopterName);
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.AdoptionYear)) {
                        if (SchoolBuilding.this.adoptedSchoolId == 0) {
                            value.setDataValue(SchoolBuilding.this.adoptionYear);
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.BuildingType)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.typeOfBuildingId));
                        }
                    } else if (value.getKRAName().equals(Constant.SchoolBuildingCondition)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.conditionId));
                        }
                    } else if (value.getKRAName().equals(Constant.Is_Completed)) {
                        value.setDataValue(true);
                    } else if (value.getKRAName().equals(Constant.Illegal_Occupation)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue(null);
                        } else {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.buildingillegalOcp));
                        }
                    } else if (value.getKRAName().equals(Constant.Remarks_Id)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue("");
                        } else if (SchoolBuilding.this.buildingillegalOcp == 0) {
                            value.setDataValue(String.valueOf(SchoolBuilding.this.remarksId));
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.Remarks_Comments)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue("");
                        } else if (SchoolBuilding.this.buildingillegalOcp == 0) {
                            value.setDataValue(SchoolBuilding.this.remarksComments);
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.Monitoring_Image)) {
                        if (SchoolBuilding.this.isNoBuilding) {
                            value.setDataValue("");
                        } else if (SchoolBuilding.this.buildingillegalOcp == 0) {
                            value.setDataValue(SchoolBuilding.this.imageBaseUrl64);
                        } else {
                            value.setDataValue("");
                        }
                    } else if (value.getKRAName().equals(Constant.Latitude)) {
                        if (SchoolBuilding.this.location != null) {
                            value.setDataValue(Double.valueOf(SchoolBuilding.this.location.getLatitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Longitude)) {
                        if (SchoolBuilding.this.location != null) {
                            value.setDataValue(Double.valueOf(SchoolBuilding.this.location.getLongitude()));
                        }
                    } else if (value.getKRAName().equals(Constant.Image_Date)) {
                        if (!SchoolBuilding.this.imageBaseUrl64.equals("")) {
                            value.setDataValue(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                        }
                    } else if (value.getKRAName().equals("Rooms_Affected_Count") && (SchoolBuilding.this.conditionId == 2 || SchoolBuilding.this.conditionId == 3 || SchoolBuilding.this.conditionId == 5 || SchoolBuilding.this.conditionId == 6)) {
                        value.setDataValue(SchoolBuilding.this.et_rooms_affected.getText().toString());
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
                        CommonActions.getDbHandler(SchoolBuilding.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        break;
                    }
                    i++;
                }
                Constant.isSchoolBuildingDATA = true;
            }
        }).start();
        this.fm.popBackStack();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath) {
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock) {
            return;
        }
        if (bitmap != null) {
            this.isPreviewVisible = true;
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
        if (bitmapPath != null) {
            this.imageBaseUrl64 = bitmapPath;
        }
    }
}
