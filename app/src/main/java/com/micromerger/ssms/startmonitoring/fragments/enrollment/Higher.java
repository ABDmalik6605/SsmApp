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
public class Higher extends BaseFragment implements View.OnClickListener {
    Button btn_back;
    CardView cv_class11;
    CardView cv_class12;
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
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_higher, (ViewGroup) null);
        this.mView = viewInflate;
        this.btn_back = (Button) viewInflate.findViewById(R.id.btn_back);
        this.ll_class = (LinearLayout) this.mView.findViewById(R.id.ll_class);
        CardView cardView = (CardView) this.mView.findViewById(R.id.cv_class11);
        this.cv_class11 = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.mView.findViewById(R.id.cv_class12);
        this.cv_class12 = cardView2;
        cardView2.setOnClickListener(this);
        this.et_total_enrollments = (EditText) this.mView.findViewById(R.id.et_total_enrollments);
        this.et_total_presents = (EditText) this.mView.findViewById(R.id.et_total_presents);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Higher.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Higher.this.getActivity() != null) {
                    Higher.this.fm.popBackStack();
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
        boolean z = true;
        boolean z2 = true;
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().contains("Is_Completed_11") && z) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(0).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(0).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    z = false;
                }
            }
            if (value.getKRAName().contains("Is_Completed_12") && z2) {
                if (Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(1).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                } else {
                    this.ll_class.getChildAt(1).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    z2 = false;
                }
            }
            if (value.getKRAName().contains(Constant.Total_Students_Enrollment_) && value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                this.totalEnroll += Integer.parseInt((String) value.getDataValue());
            }
            if (value.getKRAName().contains(Constant.Total_Students_Present_) && value.getDataValue() != null && !((String) value.getDataValue()).equals("")) {
                this.totalPresent += Integer.parseInt((String) value.getDataValue());
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
            case R.id.cv_class11 /* 2131230960 */:
                changeFragment(new Higher_Type(Constant.Class_11));
                break;
            case R.id.cv_class12 /* 2131230961 */:
                changeFragment(new Higher_Type(Constant.Class_12));
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
