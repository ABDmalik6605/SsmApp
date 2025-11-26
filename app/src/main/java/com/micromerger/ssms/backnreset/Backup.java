package com.micromerger.ssms.backnreset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.gson.Gson;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.printreport.beans.ReportResponse;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.SaveToDownloads;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Backup extends BaseFragment {
    private static final String TAG = "com.micromerger.ssms.backnreset.Backup";
    Button btn_Manual_sync;
    Button btn_back_up;
    View mView;
    List<EmployeeData> backupDataList = new ArrayList();
    List<School> backupSchoolList = new ArrayList();
    final HashedMap<Integer, String> imagesPath = new HashedMap<>();

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_backup, (ViewGroup) null);
        this.mView = viewInflate;
        this.btn_back_up = (Button) viewInflate.findViewById(R.id.btn_back_up);
        Button button = (Button) this.mView.findViewById(R.id.btn_Manual_sync);
        this.btn_Manual_sync = button;
        button.setOnClickListener(new AnonymousClass1());
        this.btn_back_up.setOnClickListener(new AnonymousClass2());
        return this.mView;
    }

    /* renamed from: com.micromerger.ssms.backnreset.Backup$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            new SweetAlertDialog(Backup.this.getActivity(), 3).setTitleText("Are you sure?").setContentText(Backup.this.getString(R.string.doYouWantStartBackUpFile)).setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.1.2
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismiss();
                    Backup.this.loadingStarted();
                    new Thread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.1.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Backup.this.uploadBackupLocal();
                        }
                    }).start();
                }
            }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.1.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.cancel();
                }
            }).show();
        }
    }

    /* renamed from: com.micromerger.ssms.backnreset.Backup$2, reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CommonActions.isConnected(Backup.this.getActivity())) {
                new SweetAlertDialog(Backup.this.getActivity(), 3).setTitleText("Are you sure?").setContentText("Do you want to backup?").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        Backup.this.loadingStarted();
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.2.2.1
                            @Override // java.lang.Runnable
                            public void run() throws JSONException {
                                Backup.this.uploadBackup();
                            }
                        }).start();
                    }
                }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.2.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                }).show();
            } else {
                DialogCustom.showError(Backup.this.getActivity(), "No internet connection found.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean uploadBackup() throws JSONException {
        try {
            try {
                this.backupDataList.clear();
                this.backupSchoolList.clear();
                AllSchoolsResponse allSchools = this.preferenceHelper.getAllSchools();
                List<School> data = allSchools.getData();
                for (EmployeeData employeeData : CommonObjects.employeeData) {
                    if (!employeeData.isSync() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                        loadingFinished();
                        CommonObjects.employeeData = CommonActions.getDbHandler(getActivity()).getAllSchoolsData();
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    new SweetAlertDialog(Backup.this.getActivityContext(), 3).setTitleText("Upload Data").setContentText("Kindly upload monitoring data first.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.3.1
                                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    }).show();
                                }
                            });
                        }
                        return false;
                    }
                    if (!employeeData.isSync()) {
                        for (int i = 0; i < employeeData.getMonitoring().get(0).getKRAData().size(); i++) {
                            KRAData kRAData = employeeData.getMonitoring().get(0).getKRAData().get(i);
                            if (kRAData.getKRAName() != null && kRAData.getKRAName().contains(Constant._Image) && ((String) kRAData.getDataValue()) != null && !((String) kRAData.getDataValue()).equals("")) {
                                Log.e("image", (String) kRAData.getDataValue());
                                Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File((String) kRAData.getDataValue()));
                                if (bitmapDecodeFile != null) {
                                    employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue(CommonActions.convertToString(bitmapDecodeFile));
                                } else {
                                    try {
                                        employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue(kRAData.getDataValue());
                                    } catch (Exception unused) {
                                        employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                    }
                                }
                            }
                        }
                        this.backupDataList.add(employeeData);
                        for (School school : data) {
                            if (school.getSchoolSemisCode().equals(employeeData.getSchoolSemisCode())) {
                                this.backupSchoolList.add(school);
                            }
                        }
                    }
                }
                List<EmployeeData> list = this.backupDataList;
                if (list != null) {
                    if (list.size() <= 0) {
                        return true;
                    }
                    Log.e("size", this.backupDataList.size() + "");
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                            allSchools.setData(this.backupSchoolList);
                            jSONObject.put("schools", new Gson().toJson(allSchools));
                        }
                        jSONObject.put("monitoring_data", new Gson().toJson(this.backupDataList));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final String strEncodeToString = Base64.encodeToString(jSONObject.toString().getBytes(), 0);
                    Log.e("encodedData", CommonActions.formatFileSize(strEncodeToString.getBytes().length) + strEncodeToString);
                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    final Date date = new Date();
                    System.out.println(simpleDateFormat.format(date));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("DataUrl", strEncodeToString);
                    jSONObject2.put("BackupDate", simpleDateFormat.format(date));
                    jSONObject2.put("CreatedBy", CommonObjects.userObj.userId);
                    Log.e("backupDataRequest", jSONObject2.toString());
                    AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/DataRestore/BackupData").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "BackupData").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addJSONObjectBody(jSONObject2).setPriority(Priority.MEDIUM).build().getAsObject(ReportResponse.class, new ParsedRequestListener<ReportResponse>() { // from class: com.micromerger.ssms.backnreset.Backup.4
                        @Override // com.androidnetworking.interfaces.ParsedRequestListener
                        public void onResponse(final ReportResponse response) {
                            Log.e("response", response.getMessage());
                            Backup.this.loadingFinished();
                            Backup.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (response.getStatus().booleanValue()) {
                                        Backup.this.preferenceHelper.setBackupDate(simpleDateFormat.format(date));
                                        Backup.this.preferenceHelper.setBackupSize(CommonActions.formatFileSize(strEncodeToString.getBytes().length));
                                        CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                                        DialogCustom.showSuccessDialog(Backup.this.getActivityContext(), "Done", response.getMessage());
                                    } else {
                                        CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                                        DialogCustom.showError(Backup.this.getActivity(), response.getMessage());
                                    }
                                    try {
                                        if (Backup.this.getActivity() != null) {
                                            Backup.this.fm.popBackStack();
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            });
                        }

                        @Override // com.androidnetworking.interfaces.ParsedRequestListener
                        public void onError(final ANError anError) {
                            Backup.this.loadingFinished();
                            Backup.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    DialogCustom.showError(Backup.this.getActivityContext(), anError.getErrorDetail());
                                    CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                                    try {
                                        if (Backup.this.getActivity() != null) {
                                            Backup.this.fm.popBackStack();
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                    return true;
                }
                loadingFinished();
                CommonObjects.employeeData = CommonActions.getDbHandler(getActivity()).getAllSchoolsData();
                getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.5
                    @Override // java.lang.Runnable
                    public void run() {
                        new SweetAlertDialog(Backup.this.getActivityContext(), 3).setTitleText("Upload Data").setContentText("No data found for backup.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.5.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                });
                return false;
            } catch (JSONException e2) {
                loadingFinished();
                getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.6
                    @Override // java.lang.Runnable
                    public void run() {
                        e2.printStackTrace();
                        DialogCustom.showError(Backup.this.getActivity(), e2.getMessage());
                        CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                        try {
                            if (Backup.this.getActivity() != null) {
                                Backup.this.fm.popBackStack();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                });
                return true;
            }
        } catch (OutOfMemoryError e3) {
            loadingFinished();
            getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.7
                @Override // java.lang.Runnable
                public void run() {
                    e3.printStackTrace();
                    DialogCustom.showError(Backup.this.getActivity(), e3.getMessage());
                    CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                    try {
                        if (Backup.this.getActivity() != null) {
                            Backup.this.fm.popBackStack();
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            });
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean uploadBackupLocal() {
        File file;
        try {
            try {
                this.backupDataList.clear();
                this.backupSchoolList.clear();
                AllSchoolsResponse allSchools = this.preferenceHelper.getAllSchools();
                List<School> data = allSchools.getData();
                for (EmployeeData employeeData : CommonObjects.employeeData) {
                    if (!employeeData.isSync() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                        for (int i = 0; i < employeeData.getMonitoring().get(0).getKRAData().size(); i++) {
                            KRAData kRAData = employeeData.getMonitoring().get(0).getKRAData().get(i);
                            if (kRAData.getKRAName() != null && kRAData.getKRAName().contains(Constant._Image) && ((String) kRAData.getDataValue()) != null && !((String) kRAData.getDataValue()).equals("")) {
                                Log.e("image", (String) kRAData.getDataValue());
                                this.imagesPath.put(Integer.valueOf(i), (String) kRAData.getDataValue());
                                Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File((String) kRAData.getDataValue()));
                                if (bitmapDecodeFile != null) {
                                    this.imagesPath.put(Integer.valueOf(i), (String) kRAData.getDataValue());
                                    employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue(CommonActions.convertToString(bitmapDecodeFile).trim());
                                } else {
                                    try {
                                        employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue(kRAData.getDataValue());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        employeeData.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                    }
                                }
                            }
                        }
                        this.backupDataList.add(employeeData);
                        for (School school : data) {
                            if (school.getSchoolSemisCode().equals(employeeData.getSchoolSemisCode())) {
                                this.backupSchoolList.add(school);
                            }
                        }
                    }
                }
                List<EmployeeData> list = this.backupDataList;
                if (list != null) {
                    if (list.size() > 0) {
                        Log.e("size", this.backupDataList.size() + "");
                        LocalBackUpSchoolsModel localBackUpSchoolsModel = new LocalBackUpSchoolsModel();
                        if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                            allSchools.setData(this.backupSchoolList);
                            localBackUpSchoolsModel.setSchools(allSchools);
                        }
                        localBackUpSchoolsModel.setMonitoring_data(this.backupDataList);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date date = new Date();
                        ManualSyncModel manualSyncModel = new ManualSyncModel(localBackUpSchoolsModel, simpleDateFormat.format(date), CommonObjects.userObj.userId);
                        Gson gson = new Gson();
                        if (Build.VERSION.SDK_INT >= 29) {
                            file = getContext().getFilesDir();
                        } else {
                            file = new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS);
                        }
                        File file2 = new File(file + "/SSMSBACKUP");
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        final String str = "SSMS(" + simpleDateFormat.format(date) + ").SSMS";
                        File file3 = new File(file2, str);
                        Uri.parse(file2.getPath());
                        String json = gson.toJson(manualSyncModel);
                        try {
                            FileWriter fileWriter = new FileWriter(file3);
                            fileWriter.write(json);
                            fileWriter.flush();
                            fileWriter.close();
                            if (Build.VERSION.SDK_INT >= 29) {
                                File file4 = new File(String.valueOf(new SaveToDownloads(getContext(), file3).saveFileToDownloads("SSMSBACKUP")));
                                Log.d(TAG, "uploadBackupLocal: result file " + file4.getPath());
                            }
                            Log.e("File Created", file2.toString());
                            getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    Toast.makeText(Backup.this.getActivity(), "File Created on : Download/SSMSBACKUP/" + str, 1).show();
                                }
                            });
                        } catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                            Log.i(TAG, "******* File not found. Did you add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        loadingFinished();
                        return true;
                    }
                    loadingFinished();
                    getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.9
                        @Override // java.lang.Runnable
                        public void run() {
                            new SweetAlertDialog(Backup.this.getActivityContext(), 3).setTitleText("Upload Data").setContentText("No data found for backup.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.9.1
                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            }).show();
                        }
                    });
                    return true;
                }
                loadingFinished();
                getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.10
                    @Override // java.lang.Runnable
                    public void run() {
                        new SweetAlertDialog(Backup.this.getActivityContext(), 3).setTitleText("Upload Data").setContentText("No data found for backup.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.10.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                });
                return false;
            } catch (OutOfMemoryError e4) {
                loadingFinished();
                getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.12
                    @Override // java.lang.Runnable
                    public void run() {
                        e4.printStackTrace();
                        DialogCustom.showError(Backup.this.getActivity(), e4.getMessage());
                        CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                        try {
                            if (Backup.this.getActivity() != null) {
                                Backup.this.fm.popBackStack();
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                });
                return true;
            }
        } catch (Exception e5) {
            loadingFinished();
            getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.Backup.11
                @Override // java.lang.Runnable
                public void run() {
                    e5.printStackTrace();
                    DialogCustom.showError(Backup.this.getActivity(), e5.getMessage());
                    CommonObjects.employeeData = CommonActions.getDbHandler(Backup.this.getActivity()).getAllSchoolsData();
                    try {
                        if (Backup.this.getActivity() != null) {
                            Backup.this.fm.popBackStack();
                        }
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
            });
            return true;
        }
    }

    private void openFileFolderFromPath(final Uri path) {
        new SweetAlertDialog(getActivity(), 3).setTitleText("Are you sure?").setContentText("Do you want to open Folder?").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.14
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismiss();
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setDataAndType(path, "resource/folder");
                Backup.this.startActivity(Intent.createChooser(intent, "Open folder"));
            }
        }).setCancelText("No").setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.Backup.13
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.cancel();
            }
        }).show();
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

    public class ManualSyncModel {
        public String BackupDate;
        public String CreatedBy;
        public LocalBackUpSchoolsModel DataUrl;

        public ManualSyncModel(LocalBackUpSchoolsModel dataUrl, String backupDate, String createdBy) {
            this.DataUrl = dataUrl;
            this.BackupDate = backupDate;
            this.CreatedBy = createdBy;
        }

        public LocalBackUpSchoolsModel getDataUrl() {
            return this.DataUrl;
        }

        public void setDataUrl(LocalBackUpSchoolsModel dataUrl) {
            this.DataUrl = dataUrl;
        }

        public String getBackupDate() {
            return this.BackupDate;
        }

        public void setBackupDate(String backupDate) {
            this.BackupDate = backupDate;
        }

        public String getCreatedBy() {
            return this.CreatedBy;
        }

        public void setCreatedBy(String createdBy) {
            this.CreatedBy = createdBy;
        }
    }

    public class LocalBackUpSchoolsModel {
        public List<EmployeeData> monitoring_data;
        public AllSchoolsResponse schools;

        public LocalBackUpSchoolsModel() {
        }

        public LocalBackUpSchoolsModel(AllSchoolsResponse schools, List<EmployeeData> monitoring_data) {
            this.schools = schools;
            this.monitoring_data = monitoring_data;
        }

        public AllSchoolsResponse getSchools() {
            return this.schools;
        }

        public void setSchools(AllSchoolsResponse schools) {
            this.schools = schools;
        }

        public List<EmployeeData> getMonitoring_data() {
            return this.monitoring_data;
        }

        public void setMonitoring_data(List<EmployeeData> monitoring_data) {
            this.monitoring_data = monitoring_data;
        }
    }
}
