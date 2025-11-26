package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class Higher_Type extends BaseFragment implements View.OnClickListener {
    Button btn_back;
    CardView cv_arts;
    CardView cv_commerce;
    CardView cv_comp;
    CardView cv_medical;
    CardView cv_other;
    CardView cv_preeng;
    LinearLayout ll_class;
    View mView;
    String title;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public Higher_Type(String s) {
        this.title = "";
        this.title = s;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_higher_type, (ViewGroup) null);
        this.mView = viewInflate;
        this.btn_back = (Button) viewInflate.findViewById(R.id.btn_back);
        this.ll_class = (LinearLayout) this.mView.findViewById(R.id.ll_class);
        CardView cardView = (CardView) this.mView.findViewById(R.id.cv_arts);
        this.cv_arts = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.mView.findViewById(R.id.cv_comp);
        this.cv_comp = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.mView.findViewById(R.id.cv_medical);
        this.cv_medical = cardView3;
        cardView3.setOnClickListener(this);
        CardView cardView4 = (CardView) this.mView.findViewById(R.id.cv_preeng);
        this.cv_preeng = cardView4;
        cardView4.setOnClickListener(this);
        CardView cardView5 = (CardView) this.mView.findViewById(R.id.cv_commerce);
        this.cv_commerce = cardView5;
        cardView5.setOnClickListener(this);
        CardView cardView6 = (CardView) this.mView.findViewById(R.id.cv_other);
        this.cv_other = cardView6;
        cardView6.setOnClickListener(this);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.Higher_Type.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Higher_Type.this.fm.popBackStack();
            }
        });
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.enrollment_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().contains(Constant.Is_Completed_)) {
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Arts_General) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(0).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Science_Computer) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(1).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Science_Pre_Medical) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(2).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Science_Pre_Enginering) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(3).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Commerce) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(4).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + Constant._Others) && Boolean.parseBoolean(String.valueOf(value.getDataValue()))) {
                    this.ll_class.getChildAt(5).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
            }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_arts /* 2131230955 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Arts_General));
                break;
            case R.id.cv_commerce /* 2131230972 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Commerce));
                break;
            case R.id.cv_comp /* 2131230973 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Science_Computer));
                break;
            case R.id.cv_medical /* 2131230982 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Science_Pre_Medical));
                break;
            case R.id.cv_other /* 2131230984 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Others));
                break;
            case R.id.cv_preeng /* 2131230986 */:
                changeFragment(new EnrollmentDetail(this.title, Constant._Science_Pre_Enginering));
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
