package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes2.dex */
public class Elementary extends BaseFragment implements View.OnClickListener {
    Button btn_back;
    CardView cv_class1;
    CardView cv_class10;
    CardView cv_class11;
    CardView cv_class12;
    CardView cv_class2;
    CardView cv_class3;
    CardView cv_class4;
    CardView cv_class5;
    CardView cv_class6;
    CardView cv_class7;
    CardView cv_class8;
    CardView cv_class9;
    CardView cv_class_katchi;
    int[] elementaryList;
    EditText et_total_enrollments;
    EditText et_total_presents;
    int[] higherSecondaryList;
    LinearLayout ll_class;
    View mView;
    int[] middleList;
    int[] secondaryList;
    TextView tv_title;
    int totalEnroll = 0;
    int totalPresent = 0;
    String title = "";
    int[] primaryList = {0, 1, 2, 3, 4, 5};
    int[] middleList08 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] middleList68 = {6, 7, 8};
    int[] elementaryList08 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] elementaryList68 = {6, 7, 8};
    int[] secondaryList010 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] secondaryList610 = {6, 7, 8, 9, 10};
    int[] hiSecondaryList012 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    int[] hiSecondaryList612 = {6, 7, 8, 9, 10, 11, 12};
    String[] higherCategories = {Constant._Arts_General, Constant._Science_Computer, Constant._Science_Biology, Constant._Commerce, Constant._Others};
    String[] collageCategories = {Constant._Arts_General, Constant._Science_Computer, Constant._Science_Pre_Medical, Constant._Science_Pre_Enginering, Constant._Commerce, Constant._Others};
    List<ReferenceDataResponse.ReferenceData> SCHOOL_LEVEL_LIST = new ArrayList();

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public Elementary() {
    }

    public Elementary(String s) {
    }

    private void showSelectedFields(int[] array) {
        for (int i : array) {
            this.ll_class.getChildAt(i).setVisibility(0);
        }
    }

    private void showAllFields() {
        for (int i = 0; i <= 12; i++) {
            this.ll_class.getChildAt(i).setVisibility(0);
        }
    }

    private void showClasses(String schoolLevel) {
        schoolLevel.hashCode();
        switch (schoolLevel) {
            case "1":
                showSelectedFields(this.primaryList);
                Utils.skipEnrollmentOfClasses_0_8(getContext(), new int[]{6, 7, 8});
                Utils.skipEnrollmentOfClasses_9_10(getContext(), new int[]{9, 10}, this.higherCategories);
                Utils.skipEnrollmentOfClasses_11_12(getContext(), new int[]{11, 12}, this.collageCategories);
                break;
            case "2":
                if (this.middleList.length == 3) {
                    Utils.skipEnrollmentOfClasses_0_8(getContext(), new int[]{0, 1, 2, 3, 4, 5});
                }
                showSelectedFields(this.middleList);
                Utils.skipEnrollmentOfClasses_9_10(getContext(), new int[]{9, 10}, this.higherCategories);
                Utils.skipEnrollmentOfClasses_11_12(getContext(), new int[]{11, 12}, this.collageCategories);
                break;
            case "3":
                if (this.elementaryList.length == 3) {
                    Utils.skipEnrollmentOfClasses_0_8(getContext(), new int[]{0, 1, 2, 3, 4, 5});
                }
                showSelectedFields(this.elementaryList);
                Utils.skipEnrollmentOfClasses_9_10(getContext(), new int[]{9, 10}, this.higherCategories);
                Utils.skipEnrollmentOfClasses_11_12(getContext(), new int[]{11, 12}, this.collageCategories);
                break;
            case "4":
                if (this.secondaryList.length == 5) {
                    Utils.skipEnrollmentOfClasses_0_8(getContext(), new int[]{0, 1, 2, 3, 4, 5});
                }
                showSelectedFields(this.secondaryList);
                Utils.skipEnrollmentOfClasses_11_12(getContext(), new int[]{11, 12}, this.collageCategories);
                break;
            case "5":
                if (this.higherSecondaryList.length == 7) {
                    Utils.skipEnrollmentOfClasses_0_8(getContext(), new int[]{0, 1, 2, 3, 4, 5});
                }
                showSelectedFields(this.higherSecondaryList);
                break;
            default:
                showAllFields();
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_elemantary, (ViewGroup) null);
        this.mView = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.tv_title = textView;
        textView.setText(this.title);
        this.btn_back = (Button) this.mView.findViewById(R.id.btn_back);
        this.ll_class = (LinearLayout) this.mView.findViewById(R.id.ll_class);
        CardView cardView = (CardView) this.mView.findViewById(R.id.cv_class_katchi);
        this.cv_class_katchi = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.mView.findViewById(R.id.cv_class1);
        this.cv_class1 = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.mView.findViewById(R.id.cv_class2);
        this.cv_class2 = cardView3;
        cardView3.setOnClickListener(this);
        CardView cardView4 = (CardView) this.mView.findViewById(R.id.cv_class3);
        this.cv_class3 = cardView4;
        cardView4.setOnClickListener(this);
        CardView cardView5 = (CardView) this.mView.findViewById(R.id.cv_class4);
        this.cv_class4 = cardView5;
        cardView5.setOnClickListener(this);
        CardView cardView6 = (CardView) this.mView.findViewById(R.id.cv_class5);
        this.cv_class5 = cardView6;
        cardView6.setOnClickListener(this);
        CardView cardView7 = (CardView) this.mView.findViewById(R.id.cv_class6);
        this.cv_class6 = cardView7;
        cardView7.setOnClickListener(this);
        CardView cardView8 = (CardView) this.mView.findViewById(R.id.cv_class7);
        this.cv_class7 = cardView8;
        cardView8.setOnClickListener(this);
        CardView cardView9 = (CardView) this.mView.findViewById(R.id.cv_class8);
        this.cv_class8 = cardView9;
        cardView9.setOnClickListener(this);
        CardView cardView10 = (CardView) this.mView.findViewById(R.id.cv_class9);
        this.cv_class9 = cardView10;
        cardView10.setOnClickListener(this);
        CardView cardView11 = (CardView) this.mView.findViewById(R.id.cv_class10);
        this.cv_class10 = cardView11;
        cardView11.setOnClickListener(this);
        CardView cardView12 = (CardView) this.mView.findViewById(R.id.cv_class11);
        this.cv_class11 = cardView12;
        cardView12.setOnClickListener(this);
        CardView cardView13 = (CardView) this.mView.findViewById(R.id.cv_class12);
        this.cv_class12 = cardView13;
        cardView13.setOnClickListener(this);
        this.et_total_enrollments = (EditText) this.mView.findViewById(R.id.et_total_enrollments);
        this.et_total_presents = (EditText) this.mView.findViewById(R.id.et_total_presents);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws NumberFormatException {
        super.onViewCreated(view, savedInstanceState);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.-$$Lambda$Elementary$ierVS2Gn-1qllY2lnD52Bx2DPkU
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("SCHOOL_LEVEL");
            }
        });
        if (collectionSelect instanceof List) {
            this.SCHOOL_LEVEL_LIST = (List) collectionSelect;
        } else {
            this.SCHOOL_LEVEL_LIST = new ArrayList(collectionSelect);
        }
        int i = 0;
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_status_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().equalsIgnoreCase("Monitoring_School_Level")) {
                i = Integer.parseInt((String) value.getDataValue());
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it2 = CommonObjects.school_status_fields.entrySet().iterator();
        while (it2.hasNext()) {
            KRAData value2 = it2.next().getValue();
            if (value2.getKRAName().equalsIgnoreCase("Monitoring_School_Level")) {
                i = Integer.parseInt((String) value2.getDataValue());
            } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_10")) {
                if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.secondaryList = this.secondaryList010;
                }
            } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_10")) {
                if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.secondaryList = this.secondaryList610;
                }
            } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_12")) {
                if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.higherSecondaryList = this.hiSecondaryList012;
                }
            } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_12")) {
                if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.higherSecondaryList = this.hiSecondaryList612;
                }
            } else if (value2.getKRAName().equalsIgnoreCase("Class_Katchi_to_08")) {
                if (i == 2) {
                    if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                        this.middleList = this.middleList08;
                    }
                } else if (i == 3 && ((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.elementaryList = this.elementaryList08;
                }
            } else if (value2.getKRAName().equalsIgnoreCase("Class_06_to_08")) {
                if (i == 2) {
                    if (((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                        this.middleList = this.middleList68;
                    }
                } else if (i == 3 && ((String) value2.getDataValue()).equals(Constant.ECE_Katchi)) {
                    this.elementaryList = this.elementaryList68;
                }
            }
        }
        int iIntValue = this.SCHOOL_LEVEL_LIST.get(i).getReferencekey().intValue();
        if (iIntValue == Integer.parseInt("1")) {
            this.title = "Primary School";
        } else if (iIntValue == Integer.parseInt("2")) {
            this.title = "Middle School";
        } else if (iIntValue == Integer.parseInt("3")) {
            this.title = "Elementary School";
        } else if (iIntValue == Integer.parseInt("4")) {
            this.title = "Secondary School";
        } else if (iIntValue == Integer.parseInt("5")) {
            this.title = "Higher Secondary School";
        }
        this.tv_title.setText(this.title);
        showClasses(String.valueOf(iIntValue));
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Elementary.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Elementary.this.getActivity() != null) {
                    Elementary.this.fm.popBackStack();
                }
            }
        });
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        this.totalEnroll = 0;
        this.totalPresent = 0;
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.enrollment_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            Log.d("KRADATA : ", value.getKRAName() + " = " + value.getDataValue());
            if (value.getKRAName().contains(Constant.Class_ID_)) {
                for (int i = 0; i <= 8; i++) {
                    value.getKRAName().equals(Constant.Class_ID_ + i);
                }
            }
            if (value.getKRAName().contains(Constant.Is_Completed_)) {
                for (int i2 = 0; i2 <= 8; i2++) {
                    if (value.getKRAName().equals(Constant.Is_Completed_ + i2) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                        this.ll_class.getChildAt(i2).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                }
            }
            if (value.getKRAName().contains("Is_Completed_9")) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(9).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(9).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            } else if (value.getKRAName().contains("Is_Completed_10")) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(10).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(10).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            }
            if (value.getKRAName().contains("Is_Completed_11")) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(11).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(11).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            }
            if (value.getKRAName().contains("Is_Completed_12")) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(12).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(12).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            }
            if (value.getKRAName().contains(Constant.Total_Male_Students_Present_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalPresent += Integer.parseInt((String) value.getDataValue());
                }
            } else if (value.getKRAName().contains(Constant.Total_Male_Students_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalEnroll += Integer.parseInt((String) value.getDataValue());
                }
            }
            if (value.getKRAName().contains(Constant.Total_Female_Students_Present_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalPresent += Integer.parseInt((String) value.getDataValue());
                }
            } else if (value.getKRAName().contains(Constant.Total_Female_Students_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalEnroll += Integer.parseInt((String) value.getDataValue());
                }
            }
        }
        this.et_total_enrollments.setText(String.valueOf(this.totalEnroll));
        this.et_total_presents.setText(String.valueOf(this.totalPresent));
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_class1 /* 2131230958 */:
                changeFragment(new EnrollmentDetail("1"));
                break;
            case R.id.cv_class10 /* 2131230959 */:
                changeFragment(new Secondary_Type(Constant.Class_10));
                break;
            case R.id.cv_class11 /* 2131230960 */:
                changeFragment(new Higher_Type(Constant.Class_11));
                break;
            case R.id.cv_class12 /* 2131230961 */:
                changeFragment(new Higher_Type(Constant.Class_12));
                break;
            case R.id.cv_class2 /* 2131230962 */:
                changeFragment(new EnrollmentDetail("2"));
                break;
            case R.id.cv_class3 /* 2131230963 */:
                changeFragment(new EnrollmentDetail("3"));
                break;
            case R.id.cv_class4 /* 2131230964 */:
                changeFragment(new EnrollmentDetail("4"));
                break;
            case R.id.cv_class5 /* 2131230965 */:
                changeFragment(new EnrollmentDetail("5"));
                break;
            case R.id.cv_class6 /* 2131230966 */:
                changeFragment(new EnrollmentDetail(Constant.Class_6));
                break;
            case R.id.cv_class7 /* 2131230967 */:
                changeFragment(new EnrollmentDetail(Constant.Class_7));
                break;
            case R.id.cv_class8 /* 2131230968 */:
                changeFragment(new EnrollmentDetail(Constant.Class_8));
                break;
            case R.id.cv_class9 /* 2131230969 */:
                changeFragment(new Secondary_Type("9"));
                break;
            case R.id.cv_class_katchi /* 2131230970 */:
                changeFragment(new EnrollmentDetail(Constant.ECE_Katchi));
                break;
        }
    }

    void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
    }
}
