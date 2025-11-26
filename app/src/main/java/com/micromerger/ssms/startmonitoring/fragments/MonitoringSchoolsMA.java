package com.micromerger.ssms.startmonitoring.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.InterfaceSchoolData;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.GeofencingActivity;
import com.micromerger.ssms.startmonitoring.SimpleMatchActivity;
import com.micromerger.ssms.startmonitoring.adapters.MonitoringSchoolsAdapter;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.bean.SchoolDataResponse;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.CommonObjectsUtils;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.Utils;
import com.micromerger.ssms.utils.model.SchoolMetaData;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MonitoringSchoolsMA extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, InterfaceSchoolData {
    private static String LOG_TAG = "MonitoringSchools";
    private MonitoringSchoolsAdapter adapter;
    ClearableEditText et_search_bar;
    LinearLayoutManager mLayoutManager;
    View mView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipe_refresh_layout;
    Thread thread;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_start_monitoring_main, (ViewGroup) null);
        this.mView = viewInflate;
        this.swipe_refresh_layout = (SwipeRefreshLayout) viewInflate.findViewById(R.id.swipe_refresh_layout);
        this.recyclerView = (RecyclerView) this.mView.findViewById(R.id.recyclerview);
        this.adapter = new MonitoringSchoolsAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setAdapter(this.adapter);
        ClearableEditText clearableEditText = (ClearableEditText) this.mView.findViewById(R.id.et_search_bar);
        this.et_search_bar = clearableEditText;
        clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                MonitoringSchoolsMA.this.filter(s.toString());
            }
        });
        this.adapter.setOnItemClickListener(new MonitoringSchoolsAdapter.MyClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.2
            @Override // com.micromerger.ssms.startmonitoring.adapters.MonitoringSchoolsAdapter.MyClickListener
            public void onItemClick(int position, View v, final School school) {
                MonitoringSchoolsMA.this.onSchoolClick(position, school);
            }
        });
        this.swipe_refresh_layout.setOnRefreshListener(this);
        ((MainActivity) getActivity()).tvTitle.setText(getString(R.string.sidemenu_startmonitoring));
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Thread thread = new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.3
            @Override // java.lang.Runnable
            public void run() {
                MonitoringSchoolsMA.this.loadingStarted();
                if (MonitoringSchoolsMA.this.preferenceHelper.getAllSchools() != null) {
                    if (CommonObjects.schools != null) {
                        if (MonitoringSchoolsMA.this.getActivity() != null) {
                            MonitoringSchoolsMA.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MonitoringSchoolsMA.this.swipe_refresh_layout.setRefreshing(false);
                                    MonitoringSchoolsMA.this.swipe_refresh_layout.setEnabled(false);
                                    ArrayList arrayList = new ArrayList();
                                    ArrayList arrayList2 = new ArrayList();
                                    ArrayList arrayList3 = new ArrayList();
                                    ArrayList arrayList4 = new ArrayList();
                                    if (CommonObjects.employeeData != null) {
                                        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                                            EmployeeData employeeData = CommonObjects.employeeData.get(i);
                                            if (employeeData != null) {
                                                for (int i2 = 0; i2 < CommonObjects.schools.size(); i2++) {
                                                    if (employeeData.getMonitoring().get(0).getMonitoringID().intValue() == CommonObjects.schools.get(i2).getMonitoring_ID()) {
                                                        if (employeeData.getMonitoring().get(0).getMonitoringStartDate() != null) {
                                                            arrayList3.add(Integer.valueOf(i2));
                                                            arrayList4.add(String.valueOf(employeeData.getMonitoring().get(0).getMonitoringStartDate()));
                                                        }
                                                        if (employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                                            arrayList.add(Integer.valueOf(i2));
                                                            arrayList2.add(String.valueOf(employeeData.getMonitoring().get(0).getMonitoringEndDate()));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                        CommonObjects.schools.get(((Integer) arrayList3.get(i3)).intValue()).setMonitoringStartDate((String) arrayList4.get(i3));
                                    }
                                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                        CommonObjects.schools.get(((Integer) arrayList.get(i4)).intValue()).setIsMonitoringComplete(true);
                                        CommonObjects.schools.get(((Integer) arrayList.get(i4)).intValue()).setMonitoringEndDate((String) arrayList2.get(i4));
                                    }
                                    for (int i5 = 0; i5 < CommonObjects.schools.size(); i5++) {
                                        try {
                                            for (int i6 = 0; i6 < CommonObjects.employeeData.size(); i6++) {
                                                if (CommonObjects.employeeData.get(i6).getSchoolId().equals(CommonObjects.schools.get(i5).getSchoolId())) {
                                                    CommonObjects.schools.get(i5).setDownloaded(true);
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    MonitoringSchoolsMA.this.adapter.updateList(CommonObjects.schools);
                                    MonitoringSchoolsMA.this.adapter.sortList();
                                    MonitoringSchoolsMA.this.loadingFinished();
                                }
                            });
                            return;
                        } else {
                            MonitoringSchoolsMA.this.loadingFinished();
                            return;
                        }
                    }
                    MonitoringSchoolsMA.this.loadingFinished();
                }
            }
        });
        this.thread = thread;
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSchoolClick(int position, final School school) {
        Log.e("CLICKED SCHOOL -> ", school.getSchoolId() + " " + school.getSchoolName());
        CommonObjects.monitoring = null;
        if (CommonObjects.employeeData != null && CommonObjects.employeeData.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= CommonObjects.employeeData.size()) {
                    break;
                }
                if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(school.getMonitoring_ID()))) {
                    CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                    CommonObjects.school = school;
                    this.preferenceHelper.setCurrentSchoolId(school.getSchoolId().intValue());
                    this.preferenceHelper.setCurrentMonitoringId(school.getMonitoring_ID());
                    break;
                }
                i++;
            }
        }
        if (CommonObjects.monitoring == null) {
            new SweetAlertDialog(getActivityContext(), 3).setTitleText("Error").setContentText("School could not be loaded.").setConfirmText("Download").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.5
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) throws JSONException {
                    sDialog.dismissWithAnimation();
                    if (CommonActions.isConnected(MonitoringSchoolsMA.this.getContext())) {
                        MonitoringSchoolsMA monitoringSchoolsMA = MonitoringSchoolsMA.this;
                        School school2 = school;
                        monitoringSchoolsMA.getSchoolData(school2, school2.getSchoolId(), Integer.valueOf(school.getMonitoring_ID()));
                        return;
                    }
                    Snackbar.make(MonitoringSchoolsMA.this.mView.findViewById(R.id.rl), MonitoringSchoolsMA.this.getString(R.string.error_internet), -1).show();
                }
            }).setCancelText("Cancel").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.4
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.cancel();
                }
            }).show();
            return;
        }
        if (getMonitoringMode().equals("multiple") || isMonitoringStarted(CommonObjects.monitoring) || getStartedMonitoringCount().intValue() < 1) {
            if (CommonObjects.monitoringConfigsData.getMonitoringGeofenceEnable()) {
                Intent intent = new Intent(getActivity(), (Class<?>) GeofencingActivity.class);
                intent.putExtra(Constant.Latitude, school.getLatitude());
                intent.putExtra(Constant.Longitude, school.getLongitude());
                intent.putExtra(Constant.Fence, CommonObjects.monitoringConfigsData.getMonitoringGeofenceRadiusMeters());
                startActivity(intent);
                return;
            }
            if (CommonObjects.monitoringConfigsData.isMonitorBiometricVerification()) {
                startActivity(new Intent(getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.START_MONITORING_MA, true));
                return;
            } else {
                showStartMonitoringDialog(school.getSchoolId());
                return;
            }
        }
        showCompleteExistingMonitoringFirstDialog();
    }

    private String getMonitoringMode() {
        String monitoringMode;
        try {
            monitoringMode = CommonObjects.monitoringConfigsData.getMonitoringMode();
        } catch (Exception unused) {
            monitoringMode = "";
        }
        return monitoringMode == null ? "" : monitoringMode;
    }

    private boolean isMonitoringStarted(EmployeeData monitoring) {
        KRAData kRAData;
        String str;
        for (int i = 0; i < monitoring.getMonitoring().get(0).getKRAData().size(); i++) {
            try {
                try {
                    kRAData = monitoring.getMonitoring().get(0).getKRAData().get(i);
                } catch (Exception unused) {
                }
                if (kRAData.getKRAName().equals(Constant.Status_Id) && (str = (String) kRAData.getDataValue()) != null && !str.isEmpty()) {
                    return true;
                }
            } catch (Exception unused2) {
            }
        }
        return false;
    }

    private Integer getStartedMonitoringCount() {
        String str;
        Integer numValueOf = 0;
        if (CommonObjects.employeeData != null) {
            List<EmployeeData> list = CommonObjects.employeeData;
            for (int i = 0; i < list.size(); i++) {
                try {
                    EmployeeData employeeData = list.get(i);
                    if (!employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                        for (int i2 = 0; i2 < employeeData.getMonitoring().get(0).getKRAData().size(); i2++) {
                            try {
                                KRAData kRAData = employeeData.getMonitoring().get(0).getKRAData().get(i2);
                                if (kRAData.getKRAName().equals(Constant.Status_Id) && (str = (String) kRAData.getDataValue()) != null && !str.isEmpty()) {
                                    numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            return numValueOf;
        }
        try {
            return CommonActions.getDbHandler(getContext()).getStartedMonitoringCount();
        } catch (Exception unused3) {
            return numValueOf;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSchoolData(final School school, final Integer schoolId, final Integer monitoringID) throws JSONException {
        loadingStarted();
        JSONObject jSONObject = new JSONObject();
        if (CommonObjects.userObj == null) {
            new CommonObjectsUtils().checkCommonObjects();
        }
        try {
            jSONObject.put("UserID", CommonObjects.userObj.userId);
            jSONObject.put("SchoolID", schoolId);
            jSONObject.put("MonitoringID", monitoringID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("url " + schoolId, "https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor");
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetDataAfterLoginForMA").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new ParsedRequestListener<SchoolDataResponse>() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.6
            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onResponse(final SchoolDataResponse response) {
                MonitoringSchoolsMA.this.loadingFinished();
                if (response.getData() != null) {
                    CommonActions.getDbHandler(MonitoringSchoolsMA.this.getActivity()).addSchoolData(response.getData());
                    CommonObjects.employeeData.add(response.getData());
                    int i = 0;
                    while (true) {
                        if (i >= CommonObjects.employeeData.size()) {
                            break;
                        }
                        if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(school.getMonitoring_ID()))) {
                            CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                            CommonObjects.school = school;
                            MonitoringSchoolsMA.this.preferenceHelper.setCurrentSchoolId(school.getSchoolId().intValue());
                            MonitoringSchoolsMA.this.preferenceHelper.setCurrentMonitoringId(school.getMonitoring_ID());
                            break;
                        }
                        i++;
                    }
                    Snackbar.make(MonitoringSchoolsMA.this.mView, "School downloaded successfully!", -1).show();
                    MonitoringSchoolsMA.this.schoolId(monitoringID.intValue());
                    return;
                }
                Snackbar.make(MonitoringSchoolsMA.this.mView.findViewById(R.id.rl), "School not found", -1).show();
            }

            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onError(ANError anError) {
                MonitoringSchoolsMA.this.loadingFinished();
                Snackbar.make(MonitoringSchoolsMA.this.mView.findViewById(R.id.rl), MonitoringSchoolsMA.this.getString(R.string.download_error), -1).show();
            }
        });
    }

    void addSchoolId(SchoolMetaData schoolMetaData) {
        CommonActions.getDbHandler(getContext()).addSchoolMetaData(schoolMetaData);
        CommonObjects.monitoring.getMonitoring().get(0).setMonitoringStartDate(schoolMetaData.value_name);
    }

    private void showStartMonitoringDialog(Integer schoolId) {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), 0);
        sweetAlertDialog.setTitleText(getString(R.string.main_startm_title)).setContentText("Are you sure, You want to start monitoring of " + CommonObjects.monitoring.getSchoolName() + " (" + CommonObjects.monitoring.getSchoolSemisCode() + ")?").setConfirmText(getString(R.string.YES)).setCancelText(getString(R.string.NO)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.8
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                MonitoringSchoolsMA.this.loadingStarted();
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    Log.d("mId", CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() + " , " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID());
                }
                if (CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate() == null || CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate().isEmpty()) {
                    CommonObjects.monitoring.getMonitoring().get(0).setMonitoringStartDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
                    Utils.patchPreviousMonitoringData(MonitoringSchoolsMA.this.getActivityContext(), false);
                    util.logException(new Exception("Monitoring Started: Monitoring Id: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " School Id: " + CommonObjects.monitoring.getSchoolId()));
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
                for (int i2 = 0; i2 < CommonObjects.monitoring.getMonitoring().get(0).getKRAData().size(); i2++) {
                    KRAData kRAData = CommonObjects.monitoring.getMonitoring().get(0).getKRAData().get(i2);
                    if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Status.toLowerCase())) {
                        CommonObjects.school_status_fields.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Management_Committee.toLowerCase())) {
                        CommonObjects.smc_fields.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Building_under_illegal_occupationut.toLowerCase())) {
                        CommonObjects.building_illegal_fields.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Take_Attendance.toLowerCase())) {
                        CommonObjects.attendance_fields.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.School_Building.toLowerCase())) {
                        CommonObjects.school_building.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Boundary_Wall.toLowerCase())) {
                        CommonObjects.boundary_wall.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Drinking_Water.toLowerCase())) {
                        CommonObjects.drinking_water.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Furniture.toLowerCase())) {
                        CommonObjects.furniture.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Washroom.toLowerCase())) {
                        CommonObjects.washroom.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Electricity.toLowerCase())) {
                        CommonObjects.electricity.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Textbooks.toLowerCase())) {
                        CommonObjects.textbooks.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Classrooms.toLowerCase())) {
                        CommonObjects.classrooms.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Other_Facilities.toLowerCase())) {
                        CommonObjects.other_facilities.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Girls_Stipend.toLowerCase())) {
                        CommonObjects.girls_stipend.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Education_Quality.toLowerCase())) {
                        CommonObjects.education_quality.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Sanctioned_New_Expenditure.toLowerCase())) {
                        CommonObjects.sanctioned_new_expenditure.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Enrollment.toLowerCase())) {
                        CommonObjects.enrollment_fields.put(Integer.valueOf(i2), kRAData);
                    } else if (kRAData.getKpiname().toLowerCase().contains(Constant.Flood_Survey.toLowerCase())) {
                        CommonObjects.flood_survey.put(Integer.valueOf(i2), kRAData);
                    }
                }
                MonitoringSchoolsMA.this.loadingFinished();
                if (CommonObjects.monitoring.getMonitoring().size() > 0) {
                    if (CommonObjects.monitoring.getMonitoring().get(0).getUsers().getBiomeritcEnabled().booleanValue()) {
                        MonitoringSchoolsMA.this.startActivity(new Intent(MonitoringSchoolsMA.this.getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.START_MONITORING, true));
                    } else {
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.8.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MonitoringSchoolsMA.this.ft = MonitoringSchoolsMA.this.fm.beginTransaction();
                                MonitoringSchoolsMA.this.ft.replace(R.id.container, new SchoolStatus());
                                MonitoringSchoolsMA.this.ft.addToBackStack(null);
                                MonitoringSchoolsMA.this.ft.commit();
                            }
                        }).start();
                    }
                }
                sweetAlertDialog2.dismiss();
            }
        }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA.7
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();
    }

    private void showCompleteExistingMonitoringFirstDialog() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), 3);
        sweetAlertDialog.setTitleText("MULTIPLE MONITORING").setContentText("Multiple monitoring is not allowed, kindly complete already started monitoring first.").setConfirmText(getString(R.string.OK)).setConfirmClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
        sweetAlertDialog.show();
    }

    void filter(String text) {
        if (CommonObjects.schools == null || CommonObjects.schools.size() <= 0) {
            return;
        }
        if (text.equals("")) {
            this.adapter.updateList(CommonObjects.schools);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (School school : CommonObjects.schools) {
            if (school != null && school.getSchoolName() != null && school.getSchoolSemisCode() != null && school.getSchoolPrefix() != null && (school.getSchoolName().toLowerCase().contains(text.toLowerCase()) || school.getSchoolSemisCode().contains(text) || school.getSchoolPrefix().toLowerCase().contains(text))) {
                arrayList.add(school);
            }
        }
        this.adapter.updateList(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTvTitle(getString(R.string.sidemenu_startmonitoring));
        SSMS.setCurrentFragment(this);
        if (CommonObjects.schoolThumbCheck) {
            CommonObjects.schoolThumbCheck = false;
            if (CommonObjects.isMAExist) {
                CommonObjects.isMAExist = false;
                showStartMonitoringDialog(null);
                return;
            } else {
                DialogCustom.showError(getActivity(), "Thumb impression does not match with the registered impression.");
                return;
            }
        }
        if (CommonObjects.mAGeofenceCheck) {
            CommonObjects.mAGeofenceCheck = false;
            if (CommonObjects.monitoringConfigsData.isMonitorBiometricVerification()) {
                startActivity(new Intent(getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.START_MONITORING_MA, true));
            } else {
                showStartMonitoringDialog(null);
            }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.thread.interrupt();
        loadingFinished();
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // com.micromerger.ssms.main.InterfaceSchoolData
    public void schoolId(int schoolID) {
        for (int i = 0; i < CommonObjects.schools.size(); i++) {
            try {
                for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
                    if (CommonObjects.schools.get(i).getMonitoring_ID() == schoolID && CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(CommonObjects.schools.get(i).getMonitoring_ID()))) {
                        CommonObjects.schools.get(i).setDownloaded(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getActivity() != null) {
            this.adapter.updateList(CommonObjects.schools);
            this.adapter.sortList();
        }
    }
}
