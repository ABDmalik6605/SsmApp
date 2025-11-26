package com.micromerger.ssms.startmonitoring.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.SimpleMatchActivity;
import com.micromerger.ssms.startmonitoring.adapters.StartMonitoringAdapter;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class StartMonitoringMainFragment extends BaseFragment {
    private static String LOG_TAG = "StartMonitoringFragment";
    private StartMonitoringAdapter adapter;
    ClearableEditText et_search_bar;
    private List<EmployeeData> mDataset = new ArrayList();
    LinearLayoutManager mLayoutManager;
    View mView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipe_refresh_layout;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_start_monitoring_main, (ViewGroup) null);
        this.mView = viewInflate;
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewInflate.findViewById(R.id.swipe_refresh_layout);
        this.swipe_refresh_layout = swipeRefreshLayout;
        swipeRefreshLayout.setRefreshing(false);
        this.swipe_refresh_layout.setEnabled(false);
        this.recyclerView = (RecyclerView) this.mView.findViewById(R.id.recyclerview);
        if (CommonObjects.employeeData != null && CommonObjects.employeeData.size() > 0) {
            this.mDataset = CommonObjects.employeeData;
        }
        this.adapter = new StartMonitoringAdapter(this.mDataset);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setAdapter(this.adapter);
        ClearableEditText clearableEditText = (ClearableEditText) this.mView.findViewById(R.id.et_search_bar);
        this.et_search_bar = clearableEditText;
        clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringMainFragment.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                StartMonitoringMainFragment.this.filter(s.toString());
            }
        });
        this.adapter.setOnItemClickListener(new AnonymousClass2());
        ((MainActivity) getActivity()).tvTitle.setText(getString(R.string.sidemenu_startmonitoring));
        return this.mView;
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringMainFragment$2, reason: invalid class name */
    class AnonymousClass2 implements StartMonitoringAdapter.MyClickListener {
        AnonymousClass2() {
        }

        @Override // com.micromerger.ssms.startmonitoring.adapters.StartMonitoringAdapter.MyClickListener
        public void onItemClick(int position, View v, final EmployeeData employeeData) {
            ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(StartMonitoringMainFragment.this.getActivityContext());
            CommonObjects.monitoring = employeeData;
            StartMonitoringMainFragment.this.preferenceHelper.setCurrentMonitoringId(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue());
            if (CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate() == null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = new Date();
                System.out.println(simpleDateFormat.format(date));
                CommonObjects.monitoring.getMonitoring().get(0).setMonitoringStartDate(simpleDateFormat.format(date));
            }
            CommonObjects.school_status_fields.clear();
            CommonObjects.smc_fields.clear();
            CommonObjects.building_illegal_fields.clear();
            CommonObjects.attendance_fields.clear();
            CommonObjects.school_building.clear();
            CommonObjects.boundary_wall.clear();
            CommonObjects.drinking_water.clear();
            CommonObjects.furniture.clear();
            CommonObjects.washroom.clear();
            CommonObjects.electricity.clear();
            CommonObjects.textbooks.clear();
            CommonObjects.classrooms.clear();
            CommonObjects.other_facilities.clear();
            CommonObjects.girls_stipend.clear();
            CommonObjects.education_quality.clear();
            CommonObjects.sanctioned_new_expenditure.clear();
            CommonObjects.enrollment_fields.clear();
            CommonObjects.flood_survey.clear();
            for (int i = 0; i < CommonObjects.monitoring.getMonitoring().get(0).getKRAData().size(); i++) {
                KRAData kRAData = CommonObjects.monitoring.getMonitoring().get(0).getKRAData().get(i);
                if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Status.toLowerCase())) {
                    CommonObjects.school_status_fields.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Management_Committee.toLowerCase())) {
                    CommonObjects.smc_fields.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Building_under_illegal_occupationut.toLowerCase())) {
                    CommonObjects.building_illegal_fields.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Take_Attendance.toLowerCase())) {
                    CommonObjects.attendance_fields.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Building.toLowerCase())) {
                    CommonObjects.school_building.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Boundary_Wall.toLowerCase())) {
                    CommonObjects.boundary_wall.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Drinking_Water.toLowerCase())) {
                    CommonObjects.drinking_water.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Furniture.toLowerCase())) {
                    CommonObjects.furniture.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Washroom.toLowerCase())) {
                    CommonObjects.washroom.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Electricity.toLowerCase())) {
                    CommonObjects.electricity.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Textbooks.toLowerCase())) {
                    CommonObjects.textbooks.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Classrooms.toLowerCase())) {
                    CommonObjects.classrooms.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Other_Facilities.toLowerCase())) {
                    CommonObjects.other_facilities.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Girls_Stipend.toLowerCase())) {
                    CommonObjects.girls_stipend.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Education_Quality.toLowerCase())) {
                    CommonObjects.education_quality.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Sanctioned_New_Expenditure.toLowerCase())) {
                    CommonObjects.sanctioned_new_expenditure.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Enrollment.toLowerCase())) {
                    CommonObjects.enrollment_fields.put(Integer.valueOf(i), kRAData);
                } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Flood_Survey.toLowerCase())) {
                    CommonObjects.flood_survey.put(Integer.valueOf(i), kRAData);
                }
            }
            if (progressDialogCreateProgressDialog.isShowing()) {
                progressDialogCreateProgressDialog.dismiss();
            }
            final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(StartMonitoringMainFragment.this.getActivity(), 0);
            sweetAlertDialog.setTitleText(StartMonitoringMainFragment.this.getString(R.string.main_startm_title)).setContentText("Are you sure, You want to start monitoring of " + employeeData.getSchoolName() + " (" + employeeData.getSchoolSemisCode() + ")?").setConfirmText(StartMonitoringMainFragment.this.getString(R.string.YES)).setCancelText(StartMonitoringMainFragment.this.getString(R.string.NO)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringMainFragment.2.2
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    if (employeeData.getMonitoring().size() > 0) {
                        if (employeeData.getMonitoring().get(0).getUsers().getBiomeritcEnabled().booleanValue()) {
                            StartMonitoringMainFragment.this.startActivity(new Intent(StartMonitoringMainFragment.this.getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.START_MONITORING, true));
                        } else {
                            new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringMainFragment.2.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    StartMonitoringMainFragment.this.ft = StartMonitoringMainFragment.this.fm.beginTransaction();
                                    StartMonitoringMainFragment.this.ft.replace(R.id.container, new SchoolStatus());
                                    StartMonitoringMainFragment.this.ft.addToBackStack(null);
                                    StartMonitoringMainFragment.this.ft.commit();
                                }
                            }).start();
                        }
                    }
                    sweetAlertDialog2.dismiss();
                }
            }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringMainFragment.2.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    sweetAlertDialog.dismiss();
                }
            });
            sweetAlertDialog.show();
        }
    }

    void filter(String text) {
        if (text.equals("")) {
            this.adapter.updateList(this.mDataset);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (EmployeeData employeeData : this.mDataset) {
            if (employeeData.getSchoolName().toLowerCase().contains(text.toLowerCase()) || employeeData.getSchoolSemisCode().contains(text)) {
                arrayList.add(employeeData);
            }
        }
        this.adapter.updateList(arrayList);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        StartMonitoringAdapter startMonitoringAdapter = this.adapter;
        if (startMonitoringAdapter != null) {
            startMonitoringAdapter.sortList();
        }
        ((MainActivity) getActivity()).setTvTitle(getString(R.string.sidemenu_startmonitoring));
        SSMS.setCurrentFragment(this);
        if (CommonObjects.schoolThumbCheck) {
            CommonObjects.schoolThumbCheck = false;
            if (CommonObjects.isMAExist) {
                CommonObjects.isMAExist = false;
                this.ft = this.fm.beginTransaction();
                this.ft.replace(R.id.container, new SchoolStatus());
                this.ft.addToBackStack(null);
                this.ft.commit();
                return;
            }
            DialogCustom.showError(getActivity(), "Thumb impression does not match with the registered impression.");
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }
}
