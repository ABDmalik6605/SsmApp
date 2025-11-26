package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class Secondary extends BaseFragment implements View.OnClickListener {
    Button btn_back;
    CardView cv_class1;
    CardView cv_class10;
    CardView cv_class2;
    CardView cv_class3;
    CardView cv_class4;
    CardView cv_class5;
    CardView cv_class6;
    CardView cv_class7;
    CardView cv_class8;
    CardView cv_class9;
    EditText et_total_enrollments;
    EditText et_total_presents;
    LinearLayout ll_class;
    View mView;
    int totalEnroll = 0;
    int totalPresent = 0;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_secondary, (ViewGroup) null);
        this.mView = viewInflate;
        this.btn_back = (Button) viewInflate.findViewById(R.id.btn_back);
        this.ll_class = (LinearLayout) this.mView.findViewById(R.id.ll_class);
        CardView cardView = (CardView) this.mView.findViewById(R.id.cv_class1);
        this.cv_class1 = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.mView.findViewById(R.id.cv_class2);
        this.cv_class2 = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.mView.findViewById(R.id.cv_class3);
        this.cv_class3 = cardView3;
        cardView3.setOnClickListener(this);
        CardView cardView4 = (CardView) this.mView.findViewById(R.id.cv_class4);
        this.cv_class4 = cardView4;
        cardView4.setOnClickListener(this);
        CardView cardView5 = (CardView) this.mView.findViewById(R.id.cv_class5);
        this.cv_class5 = cardView5;
        cardView5.setOnClickListener(this);
        CardView cardView6 = (CardView) this.mView.findViewById(R.id.cv_class6);
        this.cv_class6 = cardView6;
        cardView6.setOnClickListener(this);
        CardView cardView7 = (CardView) this.mView.findViewById(R.id.cv_class7);
        this.cv_class7 = cardView7;
        cardView7.setOnClickListener(this);
        CardView cardView8 = (CardView) this.mView.findViewById(R.id.cv_class8);
        this.cv_class8 = cardView8;
        cardView8.setOnClickListener(this);
        CardView cardView9 = (CardView) this.mView.findViewById(R.id.cv_class9);
        this.cv_class9 = cardView9;
        cardView9.setOnClickListener(this);
        CardView cardView10 = (CardView) this.mView.findViewById(R.id.cv_class10);
        this.cv_class10 = cardView10;
        cardView10.setOnClickListener(this);
        this.et_total_enrollments = (EditText) this.mView.findViewById(R.id.et_total_enrollments);
        this.et_total_presents = (EditText) this.mView.findViewById(R.id.et_total_presents);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Secondary.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Secondary.this.fm.popBackStack();
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
        boolean z = true;
        boolean z2 = true;
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().contains("Is_Completed_9")) {
                if (z) {
                    if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                        this.ll_class.getChildAt(8).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    } else {
                        this.ll_class.getChildAt(8).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                        z = false;
                    }
                }
            } else if (value.getKRAName().contains("Is_Completed_10")) {
                if (z2) {
                    if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                        this.ll_class.getChildAt(9).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    } else {
                        this.ll_class.getChildAt(9).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                        z2 = false;
                    }
                }
            } else if (value.getKRAName().contains(Constant.Is_Completed_)) {
                for (int i = 1; i <= 8; i++) {
                    if (value.getKRAName().equals(Constant.Is_Completed_ + i) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                        this.ll_class.getChildAt(i - 1).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    }
                }
            }
            if (value.getKRAName().contains(Constant.Total_Students_Enrollment_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalEnroll += Integer.parseInt((String) value.getDataValue());
                }
            }
            if (value.getKRAName().contains(Constant.Total_Students_Present_)) {
                Log.e("enrollment_fields", value.getKRAName());
                if (value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                    this.totalPresent += Integer.parseInt((String) value.getDataValue());
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
        }
    }

    void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
    }
}
