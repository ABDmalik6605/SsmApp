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
import com.google.android.gms.common.internal.ImagesContract;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.SimpleMatchActivity;
import com.micromerger.ssms.startmonitoring.adapters.MonitoringSchoolsAdapter;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.bean.SchoolDataResponse;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.Utils;
import com.micromerger.ssms.utils.model.SchoolMetaData;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MonitoringSchools extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
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
        clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                MonitoringSchools.this.filter(s.toString());
            }
        });
        this.adapter.setOnItemClickListener(new MonitoringSchoolsAdapter.MyClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.2
            @Override // com.micromerger.ssms.startmonitoring.adapters.MonitoringSchoolsAdapter.MyClickListener
            public void onItemClick(int position, View v, final School school) throws JSONException {
                MonitoringSchools.this.onSchoolClick(position, school);
            }
        });
        this.swipe_refresh_layout.setOnRefreshListener(this);
        ((MainActivity) getActivity()).tvTitle.setText(getString(R.string.sidemenu_startmonitoring));
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Thread thread = new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.3
            @Override // java.lang.Runnable
            public void run() {
                MonitoringSchools.this.loadingStarted();
                if (CommonObjects.schools != null) {
                    if (MonitoringSchools.this.getActivity() != null) {
                        MonitoringSchools.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MonitoringSchools.this.swipe_refresh_layout.setRefreshing(false);
                                MonitoringSchools.this.swipe_refresh_layout.setEnabled(false);
                                CommonObjects.employeeData = CommonActions.getDbHandler(MonitoringSchools.this.getActivity()).getAllSchoolsData();
                                ArrayList arrayList = new ArrayList();
                                ArrayList arrayList2 = new ArrayList();
                                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                                    EmployeeData employeeData = CommonObjects.employeeData.get(i);
                                    for (int i2 = 0; i2 < CommonObjects.schools.size(); i2++) {
                                        if (employeeData.getSchoolId() == CommonObjects.schools.get(i2).getSchoolId() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                            arrayList.add(Integer.valueOf(i2));
                                            arrayList2.add(String.valueOf(employeeData.getMonitoring().get(0).getMonitoringEndDate()));
                                        }
                                    }
                                }
                                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                    CommonObjects.schools.get(((Integer) arrayList.get(i3)).intValue()).setIsMonitoringComplete(true);
                                    CommonObjects.schools.get(((Integer) arrayList.get(i3)).intValue()).setMonitoringEndDate((String) arrayList2.get(i3));
                                }
                                for (int i4 = 0; i4 < CommonObjects.schools.size(); i4++) {
                                    try {
                                        for (int i5 = 0; i5 < CommonObjects.employeeData.size(); i5++) {
                                            if (CommonObjects.employeeData.get(i5).getSchoolId().equals(CommonObjects.schools.get(i4).getSchoolId())) {
                                                CommonObjects.schools.get(i4).setDownloaded(true);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                MonitoringSchools.this.adapter.updateList(CommonObjects.schools);
                                MonitoringSchools.this.loadingFinished();
                            }
                        });
                        return;
                    } else {
                        MonitoringSchools.this.loadingFinished();
                        return;
                    }
                }
                MonitoringSchools.this.loadingFinished();
                MonitoringSchools.this.swipe_refresh_layout.post(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.3.2
                    @Override // java.lang.Runnable
                    public void run() throws JSONException {
                        MonitoringSchools.this.swipe_refresh_layout.setRefreshing(true);
                        MonitoringSchools.this.getAllSchools();
                    }
                });
            }
        });
        this.thread = thread;
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSchoolClick(int position, School school) throws JSONException {
        Log.e("school", school.getSchoolId() + school.getSchoolName());
        CommonObjects.monitoring = null;
        if (CommonObjects.employeeData != null && CommonObjects.employeeData.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= CommonObjects.employeeData.size()) {
                    break;
                }
                if (CommonObjects.employeeData.get(i).getSchoolId().equals(school.getSchoolId())) {
                    Log.e("employeeData", CommonObjects.employeeData.get(i).getDbId() + "_");
                    CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                    CommonObjects.school = school;
                    this.preferenceHelper.setCurrentSchoolId(school.getSchoolId().intValue());
                    this.preferenceHelper.setCurrentMonitoringId(school.getMonitoring_ID());
                    break;
                }
                i++;
            }
        }
        if (CommonObjects.monitoring == null || CommonObjects.school == null) {
            Log.e("getSchoolData", school.getSchoolId() + "_");
            getSchoolData(school, school.getSchoolId());
            return;
        }
        showStartMonitoringDialog(school.getSchoolId());
    }

    private void getSchoolData(final School school, final Integer schoolId) throws JSONException {
        if (CommonActions.isConnected(getActivity())) {
            loadingStarted();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("UserID", CommonObjects.userObj.userId);
                jSONObject.put("SchoolID", schoolId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(ImagesContract.URL + CommonObjects.userObj.userId, "https://mne.seld.gos.pk/Services/api/Monitoring/GetDataAfterLoginForManagers");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Monitoring/GetDataAfterLoginForManagers").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetDataAfterLoginForManagers").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new ParsedRequestListener<SchoolDataResponse>() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.4
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final SchoolDataResponse response) {
                    MonitoringSchools.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Log.e("response", response.toString());
                            if (response.getData() != null) {
                                CommonActions.getDbHandler(MonitoringSchools.this.getActivity()).addSchoolData(response.getData());
                                CommonObjects.employeeData.clear();
                                CommonObjects.employeeData = CommonActions.getDbHandler(MonitoringSchools.this.getActivity()).getAllSchoolsData();
                                MonitoringSchools.this.loadingFinished();
                                CommonObjects.monitoring = null;
                                if (CommonObjects.employeeData.size() > 0) {
                                    int i = 0;
                                    while (true) {
                                        if (i >= CommonObjects.employeeData.size()) {
                                            break;
                                        }
                                        if (CommonObjects.employeeData.get(i).getSchoolId().equals(schoolId)) {
                                            Log.e("employeeData", CommonObjects.employeeData.get(i).getDbId() + "_");
                                            CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                                            CommonObjects.school = school;
                                            MonitoringSchools.this.preferenceHelper.setCurrentSchoolId(school.getSchoolId().intValue());
                                            MonitoringSchools.this.preferenceHelper.setCurrentMonitoringId(school.getMonitoring_ID());
                                            break;
                                        }
                                        i++;
                                    }
                                }
                                MonitoringSchools.this.showStartMonitoringDialog(schoolId);
                                return;
                            }
                            MonitoringSchools.this.loadingFinished();
                            DialogCustom.showError(MonitoringSchools.this.getActivityContext(), "School data could not be loaded.");
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    MonitoringSchools.this.loadingFinished();
                    DialogCustom.showError(MonitoringSchools.this.getActivityContext(), anError.getErrorDetail());
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), "No internet connection found.");
    }

    void addSchoolId(SchoolMetaData schoolMetaData) {
        CommonActions.getDbHandler(getContext()).addSchoolMetaData(schoolMetaData);
        CommonObjects.monitoring.getMonitoring().get(0).setMonitoringStartDate(schoolMetaData.value_name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStartMonitoringDialog(Integer schoolId) {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), 0);
        sweetAlertDialog.setTitleText(getString(R.string.main_startm_title)).setContentText("Are you sure, You want to start monitoring of " + CommonObjects.monitoring.getSchoolName() + " (" + CommonObjects.monitoring.getSchoolSemisCode() + ")?").setConfirmText(getString(R.string.YES)).setCancelText(getString(R.string.NO)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.6
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                MonitoringSchools.this.loadingStarted();
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    Log.e("mId", CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() + " , " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID());
                }
                if (CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate() == null || CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate().isEmpty()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = new Date();
                    System.out.println(simpleDateFormat.format(date));
                    CommonObjects.monitoring.getMonitoring().get(0).setMonitoringStartDate(simpleDateFormat.format(date));
                    Utils.patchPreviousMonitoringData(MonitoringSchools.this.getActivityContext(), false);
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
                MonitoringSchools.this.loadingFinished();
                if (CommonObjects.monitoring.getMonitoring().size() > 0) {
                    if (CommonObjects.monitoring.getMonitoring().get(0).getUsers().getBiomeritcEnabled().booleanValue()) {
                        MonitoringSchools.this.startActivity(new Intent(MonitoringSchools.this.getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.START_MONITORING, true));
                    } else {
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MonitoringSchools.this.ft = MonitoringSchools.this.fm.beginTransaction();
                                MonitoringSchools.this.ft.replace(R.id.container, new SchoolStatus());
                                MonitoringSchools.this.ft.addToBackStack(null);
                                MonitoringSchools.this.ft.commit();
                            }
                        }).start();
                    }
                }
                sweetAlertDialog2.dismiss();
            }
        }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.5
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllSchools() throws JSONException {
        if (CommonActions.isConnected(getActivity())) {
            this.swipe_refresh_layout.setRefreshing(true);
            loadingStarted();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("User_ID", CommonObjects.userObj.userId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(ImagesContract.URL + CommonObjects.userObj.userId, "https://mne.seld.gos.pk/Services/api/Schools/GetAllSchoolsForMobile");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Schools/GetAllSchoolsForMobile").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetAllSchoolsForMobile").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(AllSchoolsResponse.class, new ParsedRequestListener<AllSchoolsResponse>() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.7
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final AllSchoolsResponse response) {
                    MonitoringSchools.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (response.getData() != null && response.getData().size() > 0) {
                                MonitoringSchools.this.swipe_refresh_layout.setRefreshing(false);
                                MonitoringSchools.this.swipe_refresh_layout.setEnabled(false);
                                MonitoringSchools.this.preferenceHelper.putAllSchools(response);
                                CommonObjects.schools = response.getData();
                                CommonObjects.employeeData = CommonActions.getDbHandler(MonitoringSchools.this.getActivity()).getAllSchoolsData();
                                ArrayList arrayList = new ArrayList();
                                ArrayList arrayList2 = new ArrayList();
                                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                                    EmployeeData employeeData = CommonObjects.employeeData.get(i);
                                    for (int i2 = 0; i2 < CommonObjects.schools.size(); i2++) {
                                        if (employeeData.getSchoolId() == CommonObjects.schools.get(i2).getSchoolId() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                            arrayList.add(Integer.valueOf(i2));
                                            arrayList2.add(String.valueOf(employeeData.getMonitoring().get(0).getMonitoringEndDate()));
                                        }
                                    }
                                }
                                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                    CommonObjects.schools.get(((Integer) arrayList.get(i3)).intValue()).setIsMonitoringComplete(true);
                                    CommonObjects.schools.get(((Integer) arrayList.get(i3)).intValue()).setMonitoringEndDate((String) arrayList2.get(i3));
                                }
                                MonitoringSchools.this.adapter.updateList(CommonObjects.schools);
                                MonitoringSchools.this.swipe_refresh_layout.setRefreshing(false);
                            } else {
                                DialogCustom.showError(MonitoringSchools.this.getActivityContext(), "No school found.");
                                MonitoringSchools.this.swipe_refresh_layout.setRefreshing(false);
                            }
                            MonitoringSchools.this.loadingFinished();
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    DialogCustom.showError(MonitoringSchools.this.getActivityContext(), anError.getErrorDetail());
                    MonitoringSchools.this.swipe_refresh_layout.setRefreshing(false);
                    MonitoringSchools.this.loadingFinished();
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), "No internet connection found.");
    }

    void filter(String text) {
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

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
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
        this.thread.interrupt();
        loadingFinished();
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() throws JSONException {
        getAllSchools();
    }
}
