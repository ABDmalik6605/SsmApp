package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
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
public class Enrollment_School_Type extends BaseFragment implements View.OnClickListener {
    CardView cv_elementary;
    CardView cv_higher;
    CardView cv_secondary;
    View mView;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollment_school_type, (ViewGroup) null);
        this.mView = viewInflate;
        CardView cardView = (CardView) viewInflate.findViewById(R.id.cv_elementary);
        this.cv_elementary = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.mView.findViewById(R.id.cv_secondary);
        this.cv_secondary = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.mView.findViewById(R.id.cv_higher);
        this.cv_higher = cardView3;
        cardView3.setOnClickListener(this);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.enrollment_fields.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            KRAData value = it.next().getValue();
            if (value.getKRAName().equals(Constant.TypeOfSchool_ID)) {
                if (((String) value.getDataValue()).equals("1") || ((String) value.getDataValue()).equals("2") || ((String) value.getDataValue()).equals("3")) {
                    this.cv_elementary.setVisibility(0);
                } else {
                    this.cv_elementary.setVisibility(8);
                }
                if (((String) value.getDataValue()).equals("4")) {
                    this.cv_secondary.setVisibility(0);
                } else {
                    this.cv_secondary.setVisibility(8);
                }
                if (((String) value.getDataValue()).equals("5")) {
                    this.cv_higher.setVisibility(0);
                } else {
                    this.cv_higher.setVisibility(8);
                }
            }
        }
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.cv_elementary) {
            changeFragment(new Elementary(""));
        } else if (id2 == R.id.cv_higher) {
            changeFragment(new Higher());
        } else {
            if (id2 != R.id.cv_secondary) {
                return;
            }
            changeFragment(new Secondary());
        }
    }

    void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
    }
}
