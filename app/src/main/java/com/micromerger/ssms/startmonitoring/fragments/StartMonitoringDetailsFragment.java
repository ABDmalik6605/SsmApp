package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.gson.Gson;
import com.micromerger.ssms.BuildConfig;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.monitoring.MonitoringDataOperations;
import com.micromerger.ssms.startmonitoring.bean.SubmitEndMonitoringMultipartResponse;
import com.micromerger.ssms.startmonitoring.fragments.enrollment.Enrollment_Filter;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class StartMonitoringDetailsFragment extends BaseFragment implements View.OnClickListener {
    CardView attendanceLayout;
    Button btn_end_monitoring;
    CardView censusLayout;
    CardView enrollmentLayout;
    EditText et_final_remarks;
    CardView floodLayout;
    private int pos = -1;
    View rootView;
    CardView smcLayout;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public static StartMonitoringDetailsFragment newInstance() {
        StartMonitoringDetailsFragment startMonitoringDetailsFragment = new StartMonitoringDetailsFragment();
        startMonitoringDetailsFragment.setRetainInstance(true);
        return startMonitoringDetailsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_start_monitoring_details, (ViewGroup) null);
        this.rootView = viewInflate;
        CardView cardView = (CardView) viewInflate.findViewById(R.id.smcLayout);
        this.smcLayout = cardView;
        cardView.setOnClickListener(this);
        CardView cardView2 = (CardView) this.rootView.findViewById(R.id.attendanceLayout);
        this.attendanceLayout = cardView2;
        cardView2.setOnClickListener(this);
        CardView cardView3 = (CardView) this.rootView.findViewById(R.id.censusLayout);
        this.censusLayout = cardView3;
        cardView3.setOnClickListener(this);
        CardView cardView4 = (CardView) this.rootView.findViewById(R.id.enrollmentLayout);
        this.enrollmentLayout = cardView4;
        cardView4.setOnClickListener(this);
        CardView cardView5 = (CardView) this.rootView.findViewById(R.id.floodLayout);
        this.floodLayout = cardView5;
        cardView5.setOnClickListener(this);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.smc_fields.entrySet().iterator();
        if (it.hasNext()) {
            if (it.next().getValue().getActiveind().booleanValue() && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                this.smcLayout.setVisibility(0);
            } else {
                this.smcLayout.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it2 = CommonObjects.attendance_fields.entrySet().iterator();
        if (it2.hasNext()) {
            if (it2.next().getValue().getActiveind().booleanValue() && (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name()))) {
                this.attendanceLayout.setVisibility(0);
            } else {
                this.attendanceLayout.setVisibility(8);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it3 = CommonObjects.enrollment_fields.entrySet().iterator();
        if (it3.hasNext()) {
            if (it3.next().getValue().getActiveind().booleanValue() && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                this.enrollmentLayout.setVisibility(0);
            } else {
                this.enrollmentLayout.setVisibility(8);
            }
        }
        if (CommonObjects.monitoring != null) {
            int i = 0;
            while (true) {
                if (i >= CommonObjects.monitoring.getMonitoring().get(0).getKRAData().size()) {
                    break;
                }
                KRAData kRAData = CommonObjects.monitoring.getMonitoring().get(0).getKRAData().get(i);
                if (kRAData.getKpitypeName().equals(Constant.Census) && kRAData.getActiveind().booleanValue() && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                    this.censusLayout.setVisibility(0);
                    break;
                }
                i++;
            }
        }
        Button button = (Button) this.rootView.findViewById(R.id.btn_end_monitoring);
        this.btn_end_monitoring = button;
        button.setOnClickListener(this);
        this.et_final_remarks = (EditText) this.rootView.findViewById(R.id.et_final_remarks);
        return this.rootView;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.school_status_fields.entrySet().iterator();
        while (it.hasNext()) {
            KRAData value = it.next().getValue();
            if (value.getKRAName().equalsIgnoreCase("Final_Comments") && value.getDataValue() != null && !value.getDataValue().equals("")) {
                this.et_final_remarks.setText((String) value.getDataValue());
            }
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.et_final_remarks.setEnabled(false);
            this.et_final_remarks.setFocusable(false);
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
            case R.id.attendanceLayout /* 2131230828 */:
                changeFragment(new AttendanceOption());
                break;
            case R.id.btn_end_monitoring /* 2131230864 */:
                if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                    new SweetAlertDialog(getActivityContext(), 2).setTitleText("Done").setContentText("Monitoring details already has been submitted.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            StartMonitoringDetailsFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    int backStackEntryCount = StartMonitoringDetailsFragment.this.fm.getBackStackEntryCount();
                                    for (int i = 0; i < backStackEntryCount; i++) {
                                        StartMonitoringDetailsFragment.this.fm.popBackStackImmediate();
                                    }
                                }
                            });
                        }
                    }).show();
                    break;
                } else if (checkForm()) {
                    new SweetAlertDialog(getActivityContext(), 3).setTitleText("Are you sure?").setContentText("Do you want to end the monitoring?").setCancelText("No").setConfirmText("Yes").showCancelButton(true).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.3
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.2
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            StartMonitoringDetailsFragment.this.saveData();
                        }
                    }).show();
                    break;
                }
                break;
            case R.id.censusLayout /* 2131230904 */:
                changeFragment(new Census());
                break;
            case R.id.enrollmentLayout /* 2131231091 */:
                changeFragment(new Enrollment_Filter());
                break;
            case R.id.floodLayout /* 2131231222 */:
                changeFragment(new FloodSurveyFragment());
                break;
            case R.id.smcLayout /* 2131231803 */:
                changeFragment(new SchoolManagementCommittee());
                break;
        }
    }

    private void setFinalKRAs() {
        try {
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.school_status_fields.entrySet()) {
                Integer key = entry.getKey();
                KRAData value = entry.getValue();
                if (value.getKRAName().equalsIgnoreCase("App_Version") && value.getKraid().intValue() == 342) {
                    value.setDataValue(BuildConfig.VERSION_NAME);
                } else if (value.getKRAName().equalsIgnoreCase("Final_Comments") && value.getKraid().intValue() == 343) {
                    value.setDataValue(this.et_final_remarks.getText().toString().trim());
                }
                CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveData() {
        setFinalKRAs();
        CommonObjects.monitoring.getMonitoring().get(0).setIsMonitoringComplete(true);
        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
            if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().intValue() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue()) {
                this.pos = i;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = new Date();
                System.out.println(simpleDateFormat.format(date));
                CommonObjects.monitoring.getMonitoring().get(0).setMonitoringEndDate(simpleDateFormat.format(date));
                if (CommonObjects.monitoring.getMonitoring().get(0).getMonitoringStartDate() == null) {
                    CommonObjects.monitoring.getMonitoring().get(0).setMonitoringEndDate(simpleDateFormat.format(CommonActions.getDbHandler(getActivityContext()).getSchoolMetaData(CommonObjects.monitoring.getSchoolId().intValue(), "key_name").value_name));
                }
                CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                CommonActions.getDbHandler(getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                JSONObject jSONObject = null;
                try {
                    jSONObject = new JSONObject(new Gson().toJson(CommonObjects.employeeData.get(this.pos), EmployeeData.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("MonitoringData", jSONObject.toString());
                util.logException(new Exception("Monitoring Submitted: Monitoring Id: " + CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " School Id: " + CommonObjects.monitoring.getSchoolId()));
                new SweetAlertDialog(getActivityContext(), 2).setTitleText("Done").setContentText("Monitoring has been submitted in local storage. Do you want to upload monitoring data?").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.4
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        StartMonitoringDetailsFragment.this.loadingStarted();
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment.4.1
                            @Override // java.lang.Runnable
                            public void run() throws JSONException {
                                StartMonitoringDetailsFragment.this.postMonitoringData(CommonObjects.employeeData.get(StartMonitoringDetailsFragment.this.pos));
                            }
                        }).start();
                    }
                }).show();
                return;
            }
        }
    }

    private boolean checkForm() {
        if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
            for (KRAData kRAData : CommonObjects.monitoring.getMonitoring().get(0).getKRAData()) {
                if (CommonObjects.isSchoolOpen) {
                    if (kRAData.getKRAName().contains(Constant.Is_Completed)) {
                        Log.d("KRAs", kRAData.getKpiname() + " " + kRAData.getKRAName());
                        if (kRAData.getIsMandatory().booleanValue() && kRAData.getActiveind().booleanValue() && !Boolean.parseBoolean(String.valueOf(kRAData.getDataValue()))) {
                            if (kRAData.getKpiname().toLowerCase().equals(Constant.Take_Attendance) || CommonObjects.monitoring.getEmployees().size() <= 0) {
                                return true;
                            }
                            DialogCustom.showError(getActivityContext(), "Please fill " + kRAData.getKpiname().toLowerCase() + " form.");
                        }
                    } else if (kRAData.getKRAName().equals(Constant.Muster_Roll_Image)) {
                        Log.d("KRAs", kRAData.getKpiname() + " " + kRAData.getKRAName());
                        if (kRAData.getIsMandatory().booleanValue() && kRAData.getActiveind().booleanValue()) {
                            Log.d("KRAs", kRAData.getKpiname() + " " + kRAData.getKRAName());
                            if (kRAData.getDataValue() != null && kRAData.getDataValue().equals("")) {
                                DialogCustom.showError(getActivityContext(), "Please atleast take one picture of muster roll in attendance section.");
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (kRAData.getKpiname().equals(Constant.Take_Attendance) && kRAData.getKRAName().contains(Constant.Is_Completed)) {
                    Log.d("KRAs", kRAData.getKpiname() + " " + kRAData.getKRAName());
                    if (!Boolean.parseBoolean(String.valueOf(kRAData.getDataValue()))) {
                        if (kRAData.getKpiname().toLowerCase().equals(Constant.Take_Attendance) || CommonObjects.monitoring.getEmployees().size() <= 0) {
                            return true;
                        }
                        DialogCustom.showError(getActivityContext(), "Please fill " + kRAData.getKpiname().toLowerCase() + " form.");
                    }
                }
            }
            return true;
        }
        if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.SEC.name())) {
            for (KRAData kRAData2 : CommonObjects.monitoring.getMonitoring().get(0).getKRAData()) {
                if (CommonObjects.isSchoolOpen) {
                    if (kRAData2.getKpiname().equals(Constant.Take_Attendance) && kRAData2.getKRAName().contains(Constant.Is_Completed)) {
                        if (!Boolean.parseBoolean(String.valueOf(kRAData2.getDataValue()))) {
                            if (kRAData2.getKpiname().toLowerCase().equals(Constant.Take_Attendance) || CommonObjects.monitoring.getEmployees().size() <= 0) {
                                return true;
                            }
                            DialogCustom.showError(getActivityContext(), "Please fill " + kRAData2.getKpiname().toLowerCase() + " form.");
                        }
                    } else if (kRAData2.getKRAName().equals(Constant.Muster_Roll_Image) && kRAData2.getIsMandatory().booleanValue() && kRAData2.getActiveind().booleanValue() && kRAData2.getDataValue() != null && kRAData2.getDataValue().equals("")) {
                        DialogCustom.showError(getActivityContext(), "Please atleast take one picture of muster roll in attendance section.");
                    }
                } else if (kRAData2.getKpiname().equals(Constant.Take_Attendance) && kRAData2.getKRAName().contains(Constant.Is_Completed) && !Boolean.parseBoolean(String.valueOf(kRAData2.getDataValue()))) {
                    if (kRAData2.getKpiname().toLowerCase().equals(Constant.Take_Attendance) || CommonObjects.monitoring.getEmployees().size() <= 0) {
                        return true;
                    }
                    DialogCustom.showError(getActivityContext(), "Please fill " + kRAData2.getKpiname().toLowerCase() + " form.");
                }
            }
            return true;
        }
        for (KRAData kRAData3 : CommonObjects.monitoring.getMonitoring().get(0).getKRAData()) {
            Log.d("KRAs", kRAData3.getKpiname() + " " + kRAData3.getKRAName());
            if (CommonObjects.isSchoolOpen) {
                if (kRAData3.getKRAName().contains(Constant.Is_Completed)) {
                    Log.d("KRAs", kRAData3.getKpiname() + " " + kRAData3.getKRAName());
                    if (kRAData3.getIsMandatory().booleanValue() && kRAData3.getActiveind().booleanValue() && kRAData3.getDataValue() != null && String.valueOf(kRAData3.getDataValue()).contentEquals("")) {
                        DialogCustom.showError(getActivityContext(), "Please fill " + kRAData3.getKpiname().toLowerCase() + " form.");
                    }
                } else if (kRAData3.getKRAName().equals(Constant.Muster_Roll_Image)) {
                    Log.d("KRAs", kRAData3.getKpiname() + " " + kRAData3.getKRAName());
                    if (kRAData3.getIsMandatory().booleanValue() && kRAData3.getActiveind().booleanValue()) {
                        Log.d("KRAs", kRAData3.getKpiname() + " " + kRAData3.getKRAName());
                        if (kRAData3.getDataValue() != null && kRAData3.getDataValue().equals("")) {
                            DialogCustom.showError(getActivityContext(), "Please atleast take one picture of muster roll in attendance section.");
                        }
                    }
                } else {
                    continue;
                }
            } else if (kRAData3.getKpiname().equals(Constant.Take_Attendance) && kRAData3.getKRAName().contains(Constant.Is_Completed)) {
                Log.d("KRAs", kRAData3.getKpiname() + " " + kRAData3.getKRAName());
                if (!Boolean.parseBoolean(String.valueOf(kRAData3.getDataValue()))) {
                    if (kRAData3.getKpiname().toLowerCase().equals(Constant.Take_Attendance) || CommonObjects.monitoring.getEmployees().size() <= 0) {
                        return true;
                    }
                    DialogCustom.showError(getActivityContext(), "Please fill " + kRAData3.getKpiname().toLowerCase() + " form.");
                }
            }
        }
        return true;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postMonitoringData(final EmployeeData employeeData) throws JSONException {
        if (CommonActions.isConnected(getContext())) {
            new MonitoringDataOperations().postMonitoringData(employeeData, new AnonymousClass5(), true);
            return;
        }
        loadingFinished();
        CommonObjects.employeeData.set(this.pos, CommonObjects.monitoring);
        DialogCustom.showError(getActivityContext(), "No Internet Connection.");
    }

    /* renamed from: com.micromerger.ssms.startmonitoring.fragments.StartMonitoringDetailsFragment$5, reason: invalid class name */
    class AnonymousClass5 implements MonitoringDataOperations.SubmitEndMonitoring {
        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void progressStatus() {
        }

        AnonymousClass5() {
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void finishLoading() {
            StartMonitoringDetailsFragment.this.loadingFinished();
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void successCallback(SubmitEndMonitoringMultipartResponse response) {
            CommonObjects.monitoring.setSync(true);
            CommonObjects.employeeData.set(StartMonitoringDetailsFragment.this.pos, CommonObjects.monitoring);
            CommonActions.getDbHandler(StartMonitoringDetailsFragment.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(StartMonitoringDetailsFragment.this.pos).getDbId().intValue(), CommonObjects.monitoring);
            new SweetAlertDialog(StartMonitoringDetailsFragment.this.getActivityContext(), 2).setTitleText("Done").setContentText(response.getMessage()).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$StartMonitoringDetailsFragment$5$mtBRlcG11YFbpUImFrENJ0wkFsM
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public final void onClick(SweetAlertDialog sweetAlertDialog) {
                    this.f$0.lambda$successCallback$1$StartMonitoringDetailsFragment$5(sweetAlertDialog);
                }
            }).show();
        }

        public /* synthetic */ void lambda$successCallback$1$StartMonitoringDetailsFragment$5(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismiss();
            StartMonitoringDetailsFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$StartMonitoringDetailsFragment$5$N2PCPXAAx8p7njsoubV16f0ij40
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$successCallback$0$StartMonitoringDetailsFragment$5();
                }
            });
        }

        public /* synthetic */ void lambda$successCallback$0$StartMonitoringDetailsFragment$5() {
            int backStackEntryCount = StartMonitoringDetailsFragment.this.fm.getBackStackEntryCount();
            for (int i = 0; i < backStackEntryCount; i++) {
                StartMonitoringDetailsFragment.this.fm.popBackStackImmediate();
            }
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void failureCallback(Response<SubmitEndMonitoringMultipartResponse> response) {
            DialogCustom.showError(StartMonitoringDetailsFragment.this.getActivity(), response.message());
            StartMonitoringDetailsFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$StartMonitoringDetailsFragment$5$V9MlY_W0gVV54iu5k0GaXt4QbU0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$failureCallback$2$StartMonitoringDetailsFragment$5();
                }
            });
        }

        public /* synthetic */ void lambda$failureCallback$2$StartMonitoringDetailsFragment$5() {
            int backStackEntryCount = StartMonitoringDetailsFragment.this.fm.getBackStackEntryCount();
            for (int i = 0; i < backStackEntryCount; i++) {
                StartMonitoringDetailsFragment.this.fm.popBackStackImmediate();
            }
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void onErrorCallback(String error) {
            CommonObjects.employeeData.set(StartMonitoringDetailsFragment.this.pos, CommonObjects.monitoring);
            StartMonitoringDetailsFragment.this.loadingFinished();
            DialogCustom.showError(StartMonitoringDetailsFragment.this.getActivity(), error);
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void exceptionCallback(Exception e) {
            CommonObjects.employeeData.set(StartMonitoringDetailsFragment.this.pos, CommonObjects.monitoring);
            StartMonitoringDetailsFragment.this.loadingFinished();
            e.printStackTrace();
            DialogCustom.showError(StartMonitoringDetailsFragment.this.getActivity(), e.getMessage());
            StartMonitoringDetailsFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$StartMonitoringDetailsFragment$5$NGNPdv0njd0ANyAtc4G5rEXq5R4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$exceptionCallback$3$StartMonitoringDetailsFragment$5();
                }
            });
        }

        public /* synthetic */ void lambda$exceptionCallback$3$StartMonitoringDetailsFragment$5() {
            int backStackEntryCount = StartMonitoringDetailsFragment.this.fm.getBackStackEntryCount();
            for (int i = 0; i < backStackEntryCount; i++) {
                StartMonitoringDetailsFragment.this.fm.popBackStackImmediate();
            }
        }
    }

    void changeFragment(Fragment fragment) {
        this.ft = this.fm.beginTransaction();
        this.ft.replace(R.id.container, fragment);
        this.ft.addToBackStack(null);
        this.ft.commit();
    }
}
