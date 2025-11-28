package com.micromerger.ssms.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.navigation.NavigationView;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.gson.Gson;
import com.micromerger.ssms.Interfaces.fileUnzipListener;
import com.micromerger.ssms.R;
import com.micromerger.ssms.about.AboutFragment;
import com.micromerger.ssms.addemployee.bean.AddStaffTicketing;
import com.micromerger.ssms.addemployee.bean.EmployeeRecordUpdateDatum;
import com.micromerger.ssms.addemployee.bean.QuarterlyEmployeeRecordUpdateDataForMonitor;
import com.micromerger.ssms.backnreset.BackupReset;
import com.micromerger.ssms.biometricregistration.BiometricRegistration;
import com.micromerger.ssms.contactUs.ContactUs;
import com.micromerger.ssms.export.ExportFragment;
import com.micromerger.ssms.location.UserLocation;
import com.micromerger.ssms.main.beans.CheckUpdateResponse;
import com.micromerger.ssms.main.beans.EmployeeImpressionsZipResponse;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.main.beans.SideMenuResponse;
import com.micromerger.ssms.printreport.PrintReport;
import com.micromerger.ssms.searchemployee.SearchEmployee;
import com.micromerger.ssms.settings.Settings;
import com.micromerger.ssms.staffpresence.StaffPresenceFragment;
import com.micromerger.ssms.startmonitoring.adapters.UnsyncSchoolDataAdapter;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.bean.SchoolDataResponse;
import com.micromerger.ssms.startmonitoring.bean.SchoolIDandMonitoring;
import com.micromerger.ssms.startmonitoring.fragments.MonitoringSchools;
import com.micromerger.ssms.startmonitoring.fragments.MonitoringSchoolsMA;
import com.micromerger.ssms.user.Login;
import com.micromerger.ssms.user.beans.dashboard.DashboardResponse;
import com.micromerger.ssms.user.beans.dashboard.Data;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.user.beans.employeeData.MonitoringConfigsResponse;
import com.micromerger.ssms.user.fragment.DashBoard;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.GPSTracker;
import com.micromerger.ssms.utils.LoadingListener;
import com.micromerger.ssms.utils.SaveToDownloads;
import com.micromerger.ssms.utils.Utils;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, LoadingListener, fileUnzipListener {
    private static final int CAMERA_RESULT = 101;
    private static final int READ_EXTERNAL_STORAGE_RESULT = 103;
    private static final String TAG = "MAIN Activity";
    private static final int WRITE_EXTERNAL_STORAGE_RESULT = 102;
    public static ActivityResultLauncher<IntentSenderRequest> intentSender = null;
    static boolean isActive = false;
    public static ActivityResultLauncher<Intent> openSettings;
    public static ActivityResultLauncher<Intent> requestGPSPermission;
    public static ActivityResultLauncher<String[]> requestMultiplePermissions;
    DrawerLayout drawer;
    SweetAlertDialog impressionsZipDialog;
    private MonitoringSchoolsMA monitoringSchoolsMA;
    NavigationView navigationView;
    ProgressDialog pDialog;
    private ArrayList<String> permissionsToRequest;
    BasePreferenceHelper preferenceHelper;
    InterfaceSchoolData schoolData;
    Integer schoolIDCount;
    SweetAlertDialog schoolLoadingDialog;
    ImageView sliderBtn;
    LinearLayout slider_ll;
    Toolbar toolbar;
    public TextView tvTitle;
    TextView tv_user_email;
    TextView tv_user_name;
    TextView tv_user_phone;
    TextView tv_versionNumber;
    UnsyncSchoolDataAdapter unsyncSchoolDataAdapter;
    Window window;
    List<SideMenuResponse.SideMenu> sideMenuList = new ArrayList();
    List<EmployeeData> monitoredData = new ArrayList();
    int schoolDataCount = 0;
    int totalRemaingSchool = 0;
    Handler handle = new Handler(Looper.myLooper()) { // from class: com.micromerger.ssms.main.MainActivity.29
        @Override // android.os.Handler
        public void handleMessage(Message msg) throws JSONException {
            if (msg.what == 1) {
                if (MainActivity.this.schoolDataCount < CommonObjects.schools.size()) {
                    new SchoolIDandMonitoring();
                    SchoolIDandMonitoring syncSchoolId = MainActivity.this.getSyncSchoolId();
                    int schoolID = syncSchoolId.getSchoolID();
                    int monitoringID = syncSchoolId.getMonitoringID();
                    Log.e("Handler SchoolID  :", schoolID + "");
                    if (schoolID != -1) {
                        if (CommonActions.isConnected(MainActivity.this.getApplicationContext())) {
                            MainActivity.this.getSchoolData(Integer.valueOf(schoolID), Integer.valueOf(monitoringID));
                            return;
                        }
                        if (MainActivity.this.schoolLoadingDialog != null) {
                            MainActivity.this.schoolLoadingDialog.dismiss();
                        }
                        if (MainActivity.this.impressionsZipDialog != null) {
                            MainActivity.this.impressionsZipDialog.dismiss();
                            return;
                        }
                        return;
                    }
                    if (MainActivity.this.schoolLoadingDialog != null) {
                        MainActivity.this.schoolLoadingDialog.dismiss();
                    }
                    if (MainActivity.this.impressionsZipDialog != null) {
                        MainActivity.this.impressionsZipDialog.dismiss();
                        return;
                    }
                    return;
                }
                if (MainActivity.this.schoolLoadingDialog != null) {
                    MainActivity.this.schoolLoadingDialog.dismiss();
                    return;
                }
                return;
            }
            if (msg.what == 2) {
                if (MainActivity.this.schoolLoadingDialog != null) {
                    MainActivity.this.schoolLoadingDialog.dismiss();
                }
                if (MainActivity.this.impressionsZipDialog != null) {
                    MainActivity.this.impressionsZipDialog.dismiss();
                }
            }
        }
    };

    private void backgroundThread() {
    }

    @Override // com.micromerger.ssms.Interfaces.fileUnzipListener
    public void onFileUnZipCounter(String totalFilesUnzip) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        this.window = window;
        window.addFlags(Integer.MIN_VALUE);
        this.window.clearFlags(67108864);
        this.window.setSoftInputMode(3);
        this.window.addFlags(128);
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
        this.impressionsZipDialog = sweetAlertDialog;
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
        this.impressionsZipDialog.setCancelable(false);
        BasePreferenceHelper basePreferenceHelper = new BasePreferenceHelper(getApplicationContext());
        this.preferenceHelper = basePreferenceHelper;
        if (basePreferenceHelper.getDatabaseVersion().equals(String.valueOf(9))) {
            onStartMethod();
        }
    }

    public void onStartMethod() {
        CommonObjects.mainActivity = this;
        this.tv_versionNumber = (TextView) findViewById(R.id.tv_versionNumber);
        setVersionName();
        ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(this);
        this.pDialog = progressDialogCreateProgressDialog;
        progressDialogCreateProgressDialog.hide();
        this.preferenceHelper = new BasePreferenceHelper(getApplicationContext());
        CommonObjects.tracker = new GPSTracker(this);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.navigationView = navigationView;
        navigationView.setItemIconTintList(null);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.tv_user_name = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.tv_user_name);
        this.tv_user_email = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.tv_user_email);
        this.tv_user_phone = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.tv_user_phone);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.sliderBtn = (ImageView) findViewById(R.id.sliderBtn);
        this.slider_ll = (LinearLayout) findViewById(R.id.slider_ll);
        MonitoringSchoolsMA monitoringSchoolsMA = new MonitoringSchoolsMA();
        this.monitoringSchoolsMA = monitoringSchoolsMA;
        setSchoolData(monitoringSchoolsMA);
        this.sliderBtn.setOnClickListener(this);
        this.slider_ll.setOnClickListener(this);
        this.drawer.setDrawerListener(new DrawerLayout.DrawerListener() { // from class: com.micromerger.ssms.main.MainActivity.1
            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerStateChanged(int newState) {
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerOpened(View drawerView) {
                if (Build.VERSION.SDK_INT >= 21) {
                    MainActivity.this.window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.color_sidemenu_dark_header));
                }
            }

            @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerClosed(View drawerView) {
                if (Build.VERSION.SDK_INT >= 21) {
                    MainActivity.this.window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                }
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("android.permission.CAMERA");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        ArrayList<String> arrayListFindUnAskedPermissions = findUnAskedPermissions(arrayList);
        this.permissionsToRequest = arrayListFindUnAskedPermissions;
        if (arrayListFindUnAskedPermissions.size() > 0 && Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> arrayList2 = this.permissionsToRequest;
            requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 0);
        }
        AllSchoolsResponse allSchools = this.preferenceHelper.getAllSchools();
        if (allSchools != null) {
            CommonObjects.schools = allSchools.getData();
            if (CommonObjects.schools != null && CommonObjects.employeeData != null) {
                this.totalRemaingSchool = CommonObjects.schools.size() - CommonObjects.employeeData.size();
            }
        } else {
            CommonObjects.schools = null;
        }
        MonitoringConfigsResponse monitoringConfigs = this.preferenceHelper.getMonitoringConfigs();
        if (monitoringConfigs != null) {
            CommonObjects.monitoringConfigsData = monitoringConfigs.getData();
        } else {
            CommonObjects.monitoringConfigsData = null;
        }
        if (CommonObjects.userObj != null) {
            if (CommonObjects.userObj.userName != null) {
                this.tv_user_name.setText(CommonObjects.userObj.userName);
            }
            if (CommonObjects.userObj.email != null) {
                this.tv_user_email.setText(CommonObjects.userObj.email);
            }
            if (CommonObjects.userObj.phoneNumber != null) {
                this.tv_user_phone.setText(CommonObjects.userObj.phoneNumber);
            }
        }
        try {
            if (CommonObjects.userObj.userId != null) {
                FirebaseCrashlytics.getInstance().setUserId(CommonObjects.userObj.userId);
                FirebaseAnalytics.getInstance(this).setUserId(CommonObjects.userObj.userId);
            }
            if (CommonObjects.userObj.userName != null) {
                FirebaseCrashlytics.getInstance().setCustomKey("user_name", CommonObjects.userObj.userName);
                FirebaseAnalytics.getInstance(this).setUserProperty("user_name", CommonObjects.userObj.userName);
            }
            if (CommonObjects.userObj.email != null) {
                FirebaseCrashlytics.getInstance().setCustomKey("user_email", CommonObjects.userObj.email);
            }
            if (CommonObjects.userObj.roleId != null) {
                FirebaseCrashlytics.getInstance().setCustomKey("roleId", CommonObjects.userObj.roleId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.preferenceHelper.isSideMenuDownloaded()) {
            checkMonitoringConfiguration();
            try {
                this.sideMenuList = this.preferenceHelper.getSideMenu().getData();
                if (CommonObjects.userObj != null) {
                    if (!CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) && !CommonObjects.userObj.role.name().equals(CommonObjects.Roles.SEC.name()) && !CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                        setSideMenu();
                        if (this.preferenceHelper.isReferenceDataDownloaded()) {
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
                        this.impressionsZipDialog = sweetAlertDialog;
                        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                        this.impressionsZipDialog.setTitleText("Downloading ...");
                        this.impressionsZipDialog.setCancelable(false);
                        this.impressionsZipDialog.show();
                        getReferenceData();
                        return;
                    }
                    if (CommonObjects.schools == null) {
                        getMASchools(false);
                        return;
                    }
                    if (!this.preferenceHelper.isReferenceDataDownloaded()) {
                        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(this, 5);
                        this.impressionsZipDialog = sweetAlertDialog2;
                        sweetAlertDialog2.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                        this.impressionsZipDialog.setTitleText("Downloading ...");
                        this.impressionsZipDialog.setCancelable(false);
                        this.impressionsZipDialog.show();
                        getReferenceData();
                    }
                    if (CommonObjects.employeeData.size() < CommonObjects.schools.size()) {
                        SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(this, 5);
                        this.schoolLoadingDialog = sweetAlertDialog3;
                        sweetAlertDialog3.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                        this.schoolLoadingDialog.setTitleText("Downloading");
                        this.schoolLoadingDialog.setContentText("school data " + (CommonObjects.schools.size() - this.totalRemaingSchool) + " out of " + CommonObjects.schools.size());
                        this.schoolDataCount = CommonObjects.schools.size() - this.totalRemaingSchool;
                        this.schoolLoadingDialog.setCancelable(false);
                        this.schoolLoadingDialog.show();
                        Message message = new Message();
                        message.what = 1;
                        Handler handler = this.handle;
                        if (handler == null) {
                            initHandler();
                        } else {
                            handler.sendMessage(message);
                        }
                    } else {
                        checkUpdatedData();
                    }
                    setSideMenu();
                    return;
                }
                return;
            } catch (Exception e2) {
                this.impressionsZipDialog.dismiss();
                e2.printStackTrace();
                return;
            }
        }
        checkMonitoringConfiguration();
        getSideMenu();
    }

    private void setVersionName() {
        this.tv_versionNumber.setText("V 3.0.0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSideMenu() {
        Menu menu = this.navigationView.getMenu();
        menu.clear();
        for (int i = 0; i < this.sideMenuList.size(); i++) {
            SideMenuResponse.SideMenu sideMenu = this.sideMenuList.get(i);
            menu.add(sideMenu.getMenuItemName());
            menu.getItem(i).setIcon(new BitmapDrawable(getResources(), CommonActions.convertToBitmap(sideMenu.getMenuIconUrl())));
        }
        int childCount = this.navigationView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.navigationView.getChildAt(i2);
            if (childAt != null && (childAt instanceof ListView)) {
                ((BaseAdapter) ((HeaderViewListAdapter) ((ListView) childAt).getAdapter()).getWrappedAdapter()).notifyDataSetChanged();
            }
        }
        Log.e("nav size", String.valueOf(this.navigationView.getMenu().size()));
        if (this.sideMenuList.size() <= 0 || this.navigationView.getMenu().getItem(0) == null || !(this.navigationView.getMenu().getItem(0) instanceof MenuItem)) {
            return;
        }
        this.navigationView.getMenu().getItem(0).setChecked(true);
        try {
            onNavigationItemSelected(this.navigationView.getMenu().getItem(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        isActive = true;
        BasePreferenceHelper basePreferenceHelper = new BasePreferenceHelper(getApplicationContext());
        this.preferenceHelper = basePreferenceHelper;
        if (((basePreferenceHelper.getDatabaseVersion() == null || this.preferenceHelper.getDatabaseVersion().isEmpty()) ? 0 : Integer.parseInt(this.preferenceHelper.getDatabaseVersion())) != 9) {
            logoutDeleteAllData();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUpdatedData() {
        if (CommonActions.isConnected(getApplicationContext())) {
            onLoadingStarted();
            HashMap map = new HashMap();
            map.put("userid", CommonObjects.userObj.userId);
            map.put("date", this.preferenceHelper.getCheckDate());
            Log.e("params", map.toString());
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/MobileApp/CheckForMonitoringPlanChange");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/CheckForMonitoringPlanChange").setOkHttpClient(CommonActions.getOkHttpClient()).addApplicationJsonBody(map).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "CheckUpdate").setPriority(Priority.MEDIUM).build().getAsObject(CheckUpdateResponse.class, new AnonymousClass2());
        }
    }

    /* renamed from: com.micromerger.ssms.main.MainActivity$2, reason: invalid class name */
    class AnonymousClass2 implements ParsedRequestListener<CheckUpdateResponse> {
        AnonymousClass2() {
        }

        /* renamed from: com.micromerger.ssms.main.MainActivity$2$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ CheckUpdateResponse val$response;

            AnonymousClass1(final CheckUpdateResponse val$response) {
                this.val$response = val$response;
            }

            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.onLoadingFinished();
                if (this.val$response.getStatus().booleanValue() && MainActivity.isActive) {
                    new SweetAlertDialog(MainActivity.this, 2).setTitleText("New Data Available").setContentText(this.val$response.getMessage()).setCancelText("No").setConfirmText("Yes").showCancelButton(true).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.2.1.2
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.2.1.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            MainActivity.this.monitoredData = new ArrayList();
                            for (EmployeeData employeeData : CommonObjects.employeeData) {
                                if (!employeeData.isSync() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                                    MainActivity.this.monitoredData.add(employeeData);
                                }
                            }
                            if (MainActivity.this.monitoredData.size() > 0) {
                                MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.2.1.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        new SweetAlertDialog(MainActivity.this, 3).setTitleText("Upload Data").setContentText("Kindly upload monitoring data first.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.2.1.1.1.1
                                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                            public void onClick(SweetAlertDialog sDialog2) {
                                                sDialog2.dismissWithAnimation();
                                            }
                                        }).show();
                                    }
                                });
                            } else {
                                CommonActions.deleteCache(MainActivity.this);
                                MainActivity.this.getMASchools(true);
                            }
                        }
                    }).show();
                }
            }
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onResponse(final CheckUpdateResponse response) {
            MainActivity.this.runOnUiThread(new AnonymousClass1(response));
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onError(final ANError anError) {
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.2.2
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.onLoadingFinished();
                    if (anError.getErrorCode() == 401) {
                        if (anError.getErrorBody().contains(MainActivity.this.getString(R.string.server_authorization_denied_message))) {
                            DialogCustom.showError(MainActivity.this, MainActivity.this.getString(R.string.authorization_session_expired_message));
                        } else if (anError.getResponse() != null) {
                            DialogCustom.showError(MainActivity.this, anError.getResponse().message());
                        } else {
                            DialogCustom.showError(MainActivity.this, anError.getErrorDetail());
                        }
                    }
                }
            });
        }
    }

    private void getSideMenu() {
        if (CommonActions.isConnected(getApplicationContext())) {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(this);
            HashMap map = new HashMap();
            map.put("RoleID", CommonObjects.userObj.roleId);
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/Menu/SideMenu");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Menu/SideMenu").setOkHttpClient(CommonActions.getOkHttpClient()).addApplicationJsonBody(map).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "SideMenu").setPriority(Priority.MEDIUM).build().getAsObject(SideMenuResponse.class, new ParsedRequestListener<SideMenuResponse>() { // from class: com.micromerger.ssms.main.MainActivity.3
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final SideMenuResponse response) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (progressDialogCreateProgressDialog.isShowing()) {
                                progressDialogCreateProgressDialog.dismiss();
                            }
                            if (!response.getStatus().booleanValue() || response.getData() == null || response.getData().size() <= 0) {
                                return;
                            }
                            MainActivity.this.preferenceHelper.setSideMenuDownloaded(true);
                            MainActivity.this.sideMenuList = response.getData();
                            MainActivity.this.preferenceHelper.putSideMenu(response);
                            if (!CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) && !CommonObjects.userObj.role.name().equals(CommonObjects.Roles.SEC.name()) && !CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                                MainActivity.this.setSideMenu();
                                if (MainActivity.this.preferenceHelper.isReferenceDataDownloaded()) {
                                    return;
                                }
                                MainActivity.this.impressionsZipDialog = new SweetAlertDialog(MainActivity.this, 5);
                                MainActivity.this.impressionsZipDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                                MainActivity.this.impressionsZipDialog.setTitleText("Downloading ...");
                                MainActivity.this.impressionsZipDialog.setCancelable(false);
                                MainActivity.this.impressionsZipDialog.show();
                                MainActivity.this.getReferenceData();
                                return;
                            }
                            if (CommonObjects.schools == null) {
                                MainActivity.this.getMASchools(false);
                                return;
                            }
                            MainActivity.this.setSideMenu();
                            if (CommonObjects.employeeData.size() >= CommonObjects.schools.size()) {
                                MainActivity.this.checkUpdatedData();
                                return;
                            }
                            MainActivity.this.schoolDataCount = 0;
                            MainActivity.this.schoolLoadingDialog = new SweetAlertDialog(MainActivity.this, 5);
                            MainActivity.this.schoolLoadingDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                            MainActivity.this.schoolLoadingDialog.setTitleText("Downloading");
                            MainActivity.this.schoolLoadingDialog.setContentText("school data " + MainActivity.this.schoolDataCount + " out of " + MainActivity.this.totalRemaingSchool);
                            MainActivity.this.schoolLoadingDialog.setCancelable(false);
                            MainActivity.this.schoolLoadingDialog.show();
                            Message message = new Message();
                            message.what = 1;
                            if (MainActivity.this.handle == null) {
                                MainActivity.this.initHandler();
                            } else {
                                MainActivity.this.handle.sendMessage(message);
                            }
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(final ANError anError) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (progressDialogCreateProgressDialog.isShowing()) {
                                progressDialogCreateProgressDialog.dismiss();
                            }
                            anError.printStackTrace();
                        }
                    });
                }
            });
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.deleteUserData();
                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) Login.class).putExtra(Constant.MESSAGE, MainActivity.this.getString(R.string.error_internet)));
                MainActivity.this.finish();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (Utils.checkEmployeeImpressionsExistence(this).booleanValue()) {
            return;
        }
        getEmployeeImpressionsZip();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getReferenceData() {
        if (CommonActions.isConnected(getApplicationContext())) {
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/ReferenceData/GetReferenceData");
            AndroidNetworking.get("https://mne.seld.gos.pk/Services/api/ReferenceData/GetReferenceData").setOkHttpClient(CommonActions.getOkHttpClient()).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "GetReferenceData").setPriority(Priority.MEDIUM).build().getAsObject(ReferenceDataResponse.class, new ParsedRequestListener<ReferenceDataResponse>() { // from class: com.micromerger.ssms.main.MainActivity.5
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final ReferenceDataResponse response) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (response.getStatus().booleanValue()) {
                                MainActivity.this.preferenceHelper.setReferenceDataDownloaded(true);
                                MainActivity.this.preferenceHelper.putReferenceData(response);
                                if (!MainActivity.this.preferenceHelper.isEmployeeImpressionsDownloaded()) {
                                    MainActivity.this.getEmployeeImpressionsZip();
                                    return;
                                } else {
                                    MainActivity.this.impressionsZipDialog.dismiss();
                                    return;
                                }
                            }
                            MainActivity.this.impressionsZipDialog.dismiss();
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(final ANError anError) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.impressionsZipDialog.dismiss();
                        }
                    });
                }
            });
        } else {
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.impressionsZipDialog.dismiss();
                    MainActivity.this.deleteUserData();
                    MainActivity.this.startActivity(new Intent(MainActivity.this, (Class<?>) Login.class).putExtra(Constant.MESSAGE, MainActivity.this.getString(R.string.error_internet)));
                    MainActivity.this.finish();
                }
            });
        }
    }

    private void checkMonitoringConfiguration() {
        if (CommonActions.isConnected(getApplicationContext())) {
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/MobileApp/MonitoringConfiguration");
            AndroidNetworking.get("https://mne.seld.gos.pk/Services/api/MobileApp/MonitoringConfiguration").setOkHttpClient(CommonActions.getOkHttpClient()).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "MonitoringConfiguration").setPriority(Priority.MEDIUM).build().getAsObject(MonitoringConfigsResponse.class, new ParsedRequestListener<MonitoringConfigsResponse>() { // from class: com.micromerger.ssms.main.MainActivity.7
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(final ANError anError) {
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final MonitoringConfigsResponse response) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (response.getStatus().booleanValue()) {
                                    MainActivity.this.preferenceHelper.putMonitoringConfigs(response);
                                    CommonObjects.monitoringConfigsData = response.getData();
                                    File file = new File(MainActivity.this.getApplicationContext().getFilesDir().toString() + "/user_biometric/");
                                    if (file.exists() || response.getData().getUserBiometricImageBase64() == null || response.getData().getUserBiometricImageBase64().length() <= 0) {
                                        return;
                                    }
                                    MainActivity.this.saveBase64ToFile(response.getData().getUserBiometricImageBase64(), file, CommonObjects.userObj.userId + ".ibsm_template");
                                }
                            } catch (Exception unused) {
                                Log.e(MainActivity.TAG, "Failed to get MonitoringConfiguration");
                            }
                        }
                    });
                }
            });
        }
    }

    public void getEmployeeImpressionsZip() {
        if (CommonActions.isConnected(getApplicationContext())) {
            HashMap map = new HashMap();
            map.put("userID", CommonObjects.userObj.userId);
            Log.e("params", map.toString());
            Log.d(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/Employee/GetEmployeeImpressionsSchoolWiseByMAID");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Employee/GetEmployeeImpressionsSchoolWiseByMAID").setOkHttpClient(CommonActions.getOkHttpClient()).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addApplicationJsonBody(map).setTag((Object) "GetEmployeeImpressionsSchoolWiseByMAID").setPriority(Priority.MEDIUM).build().getAsObject(EmployeeImpressionsZipResponse.class, new ParsedRequestListener<EmployeeImpressionsZipResponse>() { // from class: com.micromerger.ssms.main.MainActivity.8
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final EmployeeImpressionsZipResponse response) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (response.getStatus().booleanValue()) {
                                String data = response.getData();
                                File file = new File(MainActivity.this.getApplicationContext().getFilesDir().toString());
                                if (file.exists()) {
                                    file.delete();
                                }
                                Log.d(ImagesContract.URL, data);
                                if (data != null) {
                                    MainActivity.this.onFileDownloadStart(data, file);
                                    return;
                                }
                                return;
                            }
                            try {
                                MainActivity.this.impressionsZipDialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(final ANError anError) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                MainActivity.this.impressionsZipDialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            return;
        }
        deleteUserData();
        startActivity(new Intent(this, (Class<?>) Login.class).putExtra(Constant.MESSAGE, getString(R.string.error_internet)));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveBase64ToFile(final String base64, final File root, final String fileName) throws IOException {
        try {
            byte[] bArrDecode = Base64.decode(base64, 0);
            try {
                if (!root.exists()) {
                    root.mkdir();
                }
                File file = new File(root.getAbsolutePath() + "/" + fileName);
                Log.e(TAG, file.getAbsolutePath());
                file.createNewFile();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArrDecode);
                    fileOutputStream.close();
                } catch (IOException unused) {
                    Log.e(TAG, "Failed to write file");
                }
            } catch (IOException unused2) {
                Log.e(TAG, "Failed to create file");
            }
        } catch (Exception unused3) {
            Log.e(TAG, "Failed to convert BASE64 to Bytes");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFileDownloadStart(final String url, final File file) {
        AndroidNetworking.download(url, file.getPath(), url.substring(url.lastIndexOf("/") + 1)).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "SchoolsEmployee").setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.micromerger.ssms.main.MainActivity.10
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public void onProgress(long bytesDownloaded, long totalBytes) {
            }
        }).startDownload(new AnonymousClass9(file, url));
    }

    /* renamed from: com.micromerger.ssms.main.MainActivity$9, reason: invalid class name */
    class AnonymousClass9 implements DownloadListener {
        final /* synthetic */ File val$file;
        final /* synthetic */ String val$url;

        AnonymousClass9(final File val$file, final String val$url) {
            this.val$file = val$file;
            this.val$url = val$url;
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onDownloadComplete() {
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.9.1
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.preferenceHelper.setEmployeeImpressionsDownloaded(true);
                    MainActivity.this.impressionsZipDialog.setTitleText("Unzipping files...");
                    new Thread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.9.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = AnonymousClass9.this.val$file.getPath() + AnonymousClass9.this.val$url.substring(AnonymousClass9.this.val$url.lastIndexOf("/"));
                            File file = new File(AnonymousClass9.this.val$file.getPath() + "/unzipped/");
                            if (file.exists()) {
                                file.delete();
                            }
                            try {
                                util.unzip(new File(str), file, "");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            MainActivity.this.impressionsZipDialog.dismiss();
                        }
                    }).start();
                }
            });
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onError(final ANError anError) {
            Log.e(MainActivity.TAG, "onError: " + anError.getResponse());
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.9.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MainActivity.this.impressionsZipDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private Integer getSchoolId() {
        int iIntValue;
        int i = 0;
        while (true) {
            if (i >= CommonObjects.schools.size()) {
                iIntValue = -1;
                break;
            }
            if (CommonObjects.employeeData.size() > 0) {
                boolean z = false;
                for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
                    if (CommonObjects.employeeData.get(i2).getSchoolId().intValue() == CommonObjects.schools.get(i).getSchoolId().intValue()) {
                        z = true;
                    }
                }
                if (!z) {
                    iIntValue = CommonObjects.schools.get(i).getSchoolId().intValue();
                    break;
                }
                i++;
            } else {
                iIntValue = CommonObjects.schools.get(i).getSchoolId().intValue();
                break;
            }
        }
        return Integer.valueOf(iIntValue);
    }

    private void logDownloadedData(SchoolDataResponse response, int SchoolID) throws IOException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
        String json = new Gson().toJson(response);
        File file = new File(Environment.getExternalStorageDirectory() + "/SSMSBACKUP");
        file.mkdirs();
        System.currentTimeMillis();
        File file2 = new File(file, "SSMSGetEmpData(" + SchoolID + ").txt");
        final Uri uri = Uri.parse(file.getPath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(json);
            printWriter.flush();
            printWriter.close();
            fileOutputStream.close();
            Log.d("fileCreated", file.toString());
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(MainActivity.this.getApplication(), "File Created on : " + uri, 1).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("MainActivity", "******* File not found. Did you add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void setDashBoard() throws JSONException {
        if (CommonActions.isConnected(this)) {
            ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(this);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userID", CommonObjects.userObj.userId);
                jSONObject.put("RoleID", CommonObjects.userObj.roleId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/Users/GetUsersSubordinates");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Users/GetUsersSubordinates").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetUsersSubordinates").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(DashboardResponse.class, new AnonymousClass12(progressDialogCreateProgressDialog));
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.13
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.sideMenuList.size() > 0) {
                    MainActivity mainActivity = MainActivity.this;
                    DialogCustom.showError(mainActivity, mainActivity.getString(R.string.error_internet));
                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(((FrameLayout) MainActivity.this.findViewById(R.id.container)).getId(), new DashBoard()).commitAllowingStateLoss();
                }
            }
        });
    }

    /* renamed from: com.micromerger.ssms.main.MainActivity$12, reason: invalid class name */
    class AnonymousClass12 implements ParsedRequestListener<DashboardResponse> {
        final /* synthetic */ ProgressDialog val$pDialog;

        AnonymousClass12(final ProgressDialog val$pDialog) {
            this.val$pDialog = val$pDialog;
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onResponse(final DashboardResponse response) {
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.12.1
                @Override // java.lang.Runnable
                public void run() {
                    final DashBoard dashBoard;
                    Log.e("getDashboard", response.toString());
                    if (AnonymousClass12.this.val$pDialog.isShowing()) {
                        AnonymousClass12.this.val$pDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        List<Data> data = response.getData();
                        Collection collectionSelect = CollectionUtils.select(data, new Predicate() { // from class: com.micromerger.ssms.main.MainActivity.12.1.1
                            @Override // org.apache.commons.collections4.Predicate
                            public boolean evaluate(Object o) {
                                return ((Data) o).getRoles().getDescription().equals(CommonObjects.Roles.DG.name());
                            }
                        });
                        if (collectionSelect instanceof List) {
                            CommonObjects.DGList = (List) collectionSelect;
                        } else {
                            CommonObjects.DGList = new ArrayList(collectionSelect);
                        }
                        Collection collectionSelect2 = CollectionUtils.select(data, new Predicate() { // from class: com.micromerger.ssms.main.MainActivity.12.1.2
                            @Override // org.apache.commons.collections4.Predicate
                            public boolean evaluate(Object o) {
                                return ((Data) o).getRoles().getDescription().equals(CommonObjects.Roles.DDO.name());
                            }
                        });
                        if (collectionSelect2 instanceof List) {
                            CommonObjects.DDOList = (List) collectionSelect2;
                        } else {
                            CommonObjects.DDOList = new ArrayList(collectionSelect2);
                        }
                        Collection collectionSelect3 = CollectionUtils.select(data, new Predicate() { // from class: com.micromerger.ssms.main.MainActivity.12.1.3
                            @Override // org.apache.commons.collections4.Predicate
                            public boolean evaluate(Object o) {
                                return ((Data) o).getRoles().getDescription().equals(CommonObjects.Roles.EDO.name());
                            }
                        });
                        if (collectionSelect3 instanceof List) {
                            CommonObjects.EDOList = (List) collectionSelect3;
                        } else {
                            CommonObjects.EDOList = new ArrayList(collectionSelect3);
                        }
                        Collection collectionSelect4 = CollectionUtils.select(data, new Predicate() { // from class: com.micromerger.ssms.main.MainActivity.12.1.4
                            @Override // org.apache.commons.collections4.Predicate
                            public boolean evaluate(Object o) {
                                return ((Data) o).getRoles().getDescription().equals(CommonObjects.Roles.CMO.name());
                            }
                        });
                        if (collectionSelect4 instanceof List) {
                            CommonObjects.CMOList = (List) collectionSelect4;
                        } else {
                            CommonObjects.CMOList = new ArrayList(collectionSelect4);
                        }
                        Collection collectionSelect5 = CollectionUtils.select(data, new Predicate() { // from class: com.micromerger.ssms.main.MainActivity.12.1.5
                            @Override // org.apache.commons.collections4.Predicate
                            public boolean evaluate(Object o) {
                                return ((Data) o).getRoles().getDescription().equals(CommonObjects.Roles.MA.name());
                            }
                        });
                        if (collectionSelect5 instanceof List) {
                            CommonObjects.MAList = (List) collectionSelect5;
                        } else {
                            CommonObjects.MAList = new ArrayList(collectionSelect5);
                        }
                        if (CommonObjects.DGList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.DGList);
                        } else if (CommonObjects.DDOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.DDOList);
                        } else if (CommonObjects.EDOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.EDOList);
                        } else if (CommonObjects.CMOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.CMOList);
                        } else if (CommonObjects.MAList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.MAList);
                        } else {
                            dashBoard = new DashBoard();
                        }
                        MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.12.1.6
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(((FrameLayout) MainActivity.this.findViewById(R.id.container)).getId(), dashBoard).commitAllowingStateLoss();
                            }
                        });
                        return;
                    }
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.12.1.7
                        @Override // java.lang.Runnable
                        public void run() {
                            DialogCustom.showError(MainActivity.this, response.getMessage());
                            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(((FrameLayout) MainActivity.this.findViewById(R.id.container)).getId(), new DashBoard()).commitAllowingStateLoss();
                        }
                    });
                }
            });
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onError(final ANError anError) {
            MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.12.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AnonymousClass12.this.val$pDialog.isShowing()) {
                        AnonymousClass12.this.val$pDialog.dismiss();
                    }
                    DialogCustom.showError(MainActivity.this, anError.getErrorDetail());
                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(((FrameLayout) MainActivity.this.findViewById(R.id.container)).getId(), new DashBoard()).commitAllowingStateLoss();
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 3);
            sweetAlertDialog.setTitleText(getString(R.string.main_back_title)).setContentText(getString(R.string.main_back_msg)).setCancelText(getString(R.string.NO)).setConfirmText(getString(R.string.YES)).showCancelButton(true).showContentText(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.15
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    if (CommonObjects.userObj != null) {
                        util.logException(new Exception("App Closed User Id: " + CommonObjects.userObj.userId));
                    }
                    sweetAlertDialog2.dismissWithAnimation();
                    MainActivity.this.finish();
                }
            }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.14
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            });
            sweetAlertDialog.show();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                if (SSMS.getCurrentFragment() == null || !SSMS.getCurrentFragment().onBackPressed()) {
                    super.onBackPressed();
                    return;
                }
                return;
            }
            super.onBackPressed();
        }
    }

    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem item) throws JSONException {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            supportFragmentManager.popBackStackImmediate();
        }
        item.getItemId();
        item.setChecked(true);
        this.navigationView.setCheckedItem(item.getGroupId());
        onLoadingFinished();
        if (item.getTitle().equals(Constant.Dashboard)) {
            setDashBoard();
        } else if (item.getTitle().equals(Constant.Start_Monitoring)) {
            if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.SEC.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), this.monitoringSchoolsMA).commit();
            } else {
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                new Thread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.16
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(((FrameLayout) MainActivity.this.findViewById(R.id.container)).getId(), new MonitoringSchools()).commit();
                    }
                }).start();
            }
            this.tvTitle.setText(getString(R.string.sidemenu_startmonitoring));
        } else if (item.getTitle().equals(Constant.Search_Employees)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new SearchEmployee()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_searchemployee));
        } else if (item.getTitle().equals(Constant.Back_Up_and_Reset)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new BackupReset()).commitAllowingStateLoss();
            this.tvTitle.setText(getString(R.string.sidemenu_backup));
            onLoadingFinished();
        } else if (item.getTitle().equals(Constant.Print_Report)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new PrintReport()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_printreport));
        } else if (item.getTitle().equals(Constant.Settings)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new Settings()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_settings));
        } else if (item.getTitle().equals(Constant.Contact_Us)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new ContactUs()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_contactus));
        } else if (item.getTitle().equals(Constant.About_Us)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new AboutFragment()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_about));
        } else if (item.getTitle().equals(Constant.My_Location)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new UserLocation()).commit();
            this.tvTitle.setText(getString(R.string.sidemenu_location));
        } else if (item.getTitle().equals(Constant.Logout)) {
            logoutUserDone();
        } else if (item.getTitle().equals(Constant.Report)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new ExportFragment()).commit();
            this.tvTitle.setText("Monitoring Report");
        } else if (item.getTitle().equals(Constant.Staff_Add_Update)) {
            getSupportFragmentManager().beginTransaction().replace(((FrameLayout) findViewById(R.id.container)).getId(), new StaffPresenceFragment()).commit();
            this.tvTitle.setText(Constant.Staff_Add_Update);
        } else if (item.getTitle().equals(Constant.maBiometric)) {
            final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
            Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(Constant.maBiometric);
            if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(fragmentFindFragmentByTag).commit();
                new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.main.-$$Lambda$MainActivity$RRsLic3fOJoVT956oM3mzK3qIHM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onNavigationItemSelected$0$MainActivity(frameLayout);
                    }
                }, 200L);
            } else {
                getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new BiometricRegistration(), Constant.maBiometric).commit();
            }
            this.tvTitle.setText(Constant.maBiometric);
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    public /* synthetic */ void lambda$onNavigationItemSelected$0$MainActivity(FrameLayout frameLayout) {
        getSupportFragmentManager().beginTransaction().add(frameLayout.getId(), new BiometricRegistration(), Constant.maBiometric).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualSyncBeforeLogout() {
        if (checkIsMonitorindNotComplete().size() > 0) {
            new SweetAlertDialog(this, 3).setTitleText("Upload Data").setContentText("Kindly upload monitoring data first").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.17
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sDialog) {
                    if (MainActivity.this.checkIsMonitorindNotComplete() != null && MainActivity.this.checkIsMonitorindNotComplete().size() > 0) {
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.showUnSyncSchoolsDialog(mainActivity.checkIsMonitorindNotComplete());
                    }
                    sDialog.dismissWithAnimation();
                }
            }).show();
        } else {
            new AsyncAboutUs().execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<EmployeeData> checkIsMonitorindNotComplete() {
        ArrayList arrayList = new ArrayList();
        ArrayList<EmployeeData> arrayList2 = new ArrayList<>();
        try {
            arrayList.clear();
            Iterator<EmployeeData> it = CommonObjects.employeeData.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            if (arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (((EmployeeData) arrayList.get(i)).getMonitoring().get(0).getMonitoringStartDate() != null && ((EmployeeData) arrayList.get(i)).getMonitoring().get(0).getMonitoringEndDate() == null) {
                        arrayList2.add((EmployeeData) arrayList.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean uploadBackupLocal() throws JSONException, IOException {
        File file;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            try {
                arrayList.clear();
                arrayList2.clear();
                AllSchoolsResponse allSchools = this.preferenceHelper.getAllSchools();
                List<School> data = allSchools != null ? allSchools.getData() : null;
                Iterator<EmployeeData> it = CommonObjects.employeeData.iterator();
                while (true) {
                    boolean z = false;
                    if (it.hasNext()) {
                        EmployeeData next = it.next();
                        if (!next.isSync() && next.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                            dismissLoading();
                            CommonObjects.employeeData = CommonActions.getDbHandler(this).getAllSchoolsData();
                            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.18
                                @Override // java.lang.Runnable
                                public void run() {
                                    new SweetAlertDialog(MainActivity.this, 3).setTitleText("Upload Data").setContentText("Kindly upload monitoring data first.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.18.1
                                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    }).show();
                                }
                            });
                            return false;
                        }
                        if (!next.isSync()) {
                            for (int i = 0; i < next.getMonitoring().get(0).getKRAData().size(); i++) {
                                KRAData kRAData = next.getMonitoring().get(0).getKRAData().get(i);
                                Log.d("wtf", kRAData.getKRAName() + kRAData.getKpiname());
                                if (kRAData.getKRAName() != null && kRAData.getKRAName().contains(Constant._Image) && ((String) kRAData.getDataValue()) != null && !((String) kRAData.getDataValue()).equals("")) {
                                    Log.e("image", (String) kRAData.getDataValue());
                                    Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File((String) kRAData.getDataValue()));
                                    if (bitmapDecodeFile != null) {
                                        next.getMonitoring().get(0).getKRAData().get(i).setDataValue(CommonActions.convertToString(bitmapDecodeFile));
                                    } else {
                                        next.getMonitoring().get(0).getKRAData().get(i).setDataValue("");
                                    }
                                }
                            }
                            arrayList.add(next);
                            if (data != null) {
                                for (School school : data) {
                                    if (school.getSchoolSemisCode().equals(next.getSchoolSemisCode())) {
                                        arrayList2.add(school);
                                    }
                                }
                            }
                        }
                    } else if (arrayList.size() > 0) {
                        if (arrayList.size() > 0) {
                            Log.e("size", arrayList.size() + "");
                            JSONObject jSONObject = new JSONObject();
                            try {
                                if (CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name()) || CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                                    allSchools.setData(arrayList2);
                                    jSONObject.put("schools", new Gson().toJson(allSchools));
                                }
                                jSONObject.put("monitoring_data", new Gson().toJson(arrayList));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String strEncodeToString = Base64.encodeToString(jSONObject.toString().getBytes(), 0);
                            Log.e("encodedData", CommonActions.formatFileSize(strEncodeToString.getBytes().length) + strEncodeToString);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date date = new Date();
                            System.out.println(simpleDateFormat.format(date));
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("DataUrl", strEncodeToString);
                            jSONObject2.put("BackupDate", simpleDateFormat.format(date));
                            jSONObject2.put("CreatedBy", CommonObjects.userObj.userId);
                            Log.e("backupDataRequest", jSONObject2.toString());
                            if (Build.VERSION.SDK_INT >= 29) {
                                file = getFilesDir();
                            } else {
                                file = new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS);
                            }
                            File file2 = new File(file + "/SSMSBACKUP");
                            file2.mkdirs();
                            System.currentTimeMillis();
                            File file3 = new File(file2, "SSMS(" + simpleDateFormat.format(date) + ").SSMS");
                            Uri.parse(file2.getPath());
                            String string = jSONObject2.toString();
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                                printWriter.println(string);
                                printWriter.flush();
                                printWriter.close();
                                fileOutputStream.close();
                                if (Build.VERSION.SDK_INT >= 29) {
                                    Log.d(TAG, "uploadBackupLocal: result file " + new File(String.valueOf(new SaveToDownloads(this, file3).saveFileToDownloads("SSMSBACKUP"))).getPath());
                                }
                                Log.d("fileCreated", file2.toString());
                                z = true;
                            } catch (FileNotFoundException e2) {
                                e2.printStackTrace();
                                Log.i(TAG, "******* File not found. Did you add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
                                logoutDeleteAllData();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                logoutDeleteAllData();
                            }
                            dismissLoading();
                            if (z) {
                                runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.19
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity.this.logoutDeleteAllData();
                                    }
                                });
                            } else {
                                logoutDeleteAllData();
                            }
                        }
                    } else {
                        dismissLoading();
                        CommonObjects.employeeData = CommonActions.getDbHandler(this).getAllSchoolsData();
                        logoutDeleteAllData();
                        return false;
                    }
                }
            } catch (OutOfMemoryError e4) {
                dismissLoading();
                runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.21
                    @Override // java.lang.Runnable
                    public void run() {
                        e4.printStackTrace();
                        DialogCustom.showError(MainActivity.this, e4.getMessage());
                        CommonObjects.employeeData = CommonActions.getDbHandler(MainActivity.this).getAllSchoolsData();
                        try {
                            MainActivity.this.finish();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                });
            }
        } catch (JSONException e5) {
            dismissLoading();
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.20
                @Override // java.lang.Runnable
                public void run() {
                    e5.printStackTrace();
                    DialogCustom.showError(MainActivity.this, e5.getMessage());
                    CommonObjects.employeeData = CommonActions.getDbHandler(MainActivity.this).getAllSchoolsData();
                    try {
                        MainActivity.this.finish();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
            });
        }
        return true;
    }

    private void logoutUserDone() {
        this.monitoredData = new ArrayList();
        for (EmployeeData employeeData : CommonObjects.employeeData) {
            if (!employeeData.isSync() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                this.monitoredData.add(employeeData);
            }
        }
        if (this.monitoredData.size() > 0) {
            runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.22
                @Override // java.lang.Runnable
                public void run() {
                    new SweetAlertDialog(MainActivity.this, 3).setTitleText("Upload Data").setContentText("Kindly upload monitoring data first.").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.22.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    }).show();
                }
            });
            return;
        }
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 3);
        sweetAlertDialog.setTitleText(getString(R.string.main_logout_title)).setContentText(getString(R.string.main_logout_msg)).setCancelText(getString(R.string.CANCEL)).setConfirmText(getString(R.string.OK)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.24
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismiss();
                MainActivity.this.manualSyncBeforeLogout();
            }
        }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.main.MainActivity.23
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    private class AsyncAboutUs extends AsyncTask<Void, Void, Void> {
        private SweetAlertDialog progressdialog;

        private AsyncAboutUs() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, 5);
            this.progressdialog = sweetAlertDialog;
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
            this.progressdialog.setTitleText(Constant.Logout);
            this.progressdialog.setContentText("Deleting Data...");
            this.progressdialog.setCancelable(false);
            this.progressdialog.show();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... strings) {
            try {
                MainActivity.this.uploadBackupLocal();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void aVoid) {
            super.onPostExecute((AsyncAboutUs) aVoid);
            SweetAlertDialog sweetAlertDialog = this.progressdialog;
            if (sweetAlertDialog == null || !sweetAlertDialog.isShowing()) {
                return;
            }
            this.progressdialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutDeleteAllData() {
        deleteUserData();
        if (CommonObjects.userObj != null) {
            util.logException(new Exception("Logout User Id: " + CommonObjects.userObj.userId));
        }
        Intent intent = new Intent(this, (Class<?>) Login.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteUserData() {
        this.preferenceHelper.putUser(null);
        this.preferenceHelper.setReferenceDataDownloaded(false);
        this.preferenceHelper.setEmployeeImpressionsDownloaded(false);
        this.preferenceHelper.setCheckDate("");
        this.preferenceHelper.putAllSchools(null);
        CommonActions.getDbHandler(this).deleteSchoolData();
        this.preferenceHelper.setSideMenuDownloaded(false);
        this.preferenceHelper.setEmployeeRecordUpdateDataDownloaded(false);
        this.preferenceHelper.putSideMenu(null);
        this.preferenceHelper.putReferenceData(null);
        this.preferenceHelper.setBackupDate("");
        this.preferenceHelper.setBackupSize("");
        this.preferenceHelper.setCurrentSchoolId(0);
        this.preferenceHelper.setCurrentMonitoringId(0);
        File file = new File(getFilesDir().getAbsolutePath());
        Log.e("path", file.getAbsolutePath());
        CommonActions.deleteRecursive(file);
        CommonActions.deleteCache(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnSyncSchoolsDialog(ArrayList<EmployeeData> unSyncSchoolList) {
        ArrayList arrayList = new ArrayList();
        Iterator<EmployeeData> it = unSyncSchoolList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSchoolName());
        }
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_unsync_schools);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (arrayList.size() > 0) {
            RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.rv_schools_unSyncData_details);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
            UnsyncSchoolDataAdapter unsyncSchoolDataAdapter = new UnsyncSchoolDataAdapter(this, arrayList);
            this.unsyncSchoolDataAdapter = unsyncSchoolDataAdapter;
            recyclerView.setAdapter(unsyncSchoolDataAdapter);
        }
        dialog.show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.drawer.openDrawer(GravityCompat.START);
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle.setText(tvTitle);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                hasPermission("android.permission.CAMERA");
                break;
            case 102:
                hasPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                break;
            case 103:
                hasPermission("android.permission.READ_EXTERNAL_STORAGE");
                break;
        }
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = wanted.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!hasPermission(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean hasPermission(String permission) {
        return !canMakeSmores() || Build.VERSION.SDK_INT < 23 || checkSelfPermission(permission) == 0;
    }

    private boolean canMakeSmores() {
        return Build.VERSION.SDK_INT > 22;
    }

    @Override // com.micromerger.ssms.utils.LoadingListener
    public void onLoadingStarted() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.25
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.pDialog != null) {
                    MainActivity.this.pDialog.show();
                }
            }
        });
    }

    @Override // com.micromerger.ssms.utils.LoadingListener
    public void onLoadingFinished() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.26
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.pDialog != null) {
                    MainActivity.this.pDialog.hide();
                }
            }
        });
    }

    public void dismissLoading() {
        ProgressDialog progressDialog = this.pDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SweetAlertDialog sweetAlertDialog = this.schoolLoadingDialog;
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
            this.schoolLoadingDialog = null;
        }
        if (this.handle != null) {
            this.handle = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = requestGPSPermission;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
        }
        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher2 = intentSender;
        if (activityResultLauncher2 != null) {
            activityResultLauncher2.unregister();
        }
        ActivityResultLauncher<String[]> activityResultLauncher3 = requestMultiplePermissions;
        if (activityResultLauncher3 != null) {
            activityResultLauncher3.unregister();
        }
        ActivityResultLauncher<Intent> activityResultLauncher4 = openSettings;
        if (activityResultLauncher4 != null) {
            activityResultLauncher4.unregister();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMASchools(final boolean roasterDownload) {
        Log.e("1st Start", "getMASchools()");
        if (CommonActions.isConnected(getApplicationContext())) {
            onLoadingStarted();
            HashMap map = new HashMap();
            map.put("User_ID", CommonObjects.userObj.userId);
            Log.e("params", map.toString());
            Log.e(ImagesContract.URL, "https://mne.seld.gos.pk/Services/api/MobileApp/GetMonitoringPlanForMonitor");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetMonitoringPlanForMonitor").setOkHttpClient(CommonActions.getOkHttpClient()).addApplicationJsonBody(map).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "GetSchoolsForMA").setPriority(Priority.MEDIUM).build().getAsObject(AllSchoolsResponse.class, new ParsedRequestListener<AllSchoolsResponse>() { // from class: com.micromerger.ssms.main.MainActivity.27
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(final AllSchoolsResponse response) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.27.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.onLoadingFinished();
                            if (response.getStatus().booleanValue() && response.getData() != null) {
                                MainActivity.this.preferenceHelper.putAllSchools(response);
                                MainActivity.this.preferenceHelper.setCheckDate(response.getDatetime());
                                CommonActions.getDbHandler(MainActivity.this).deleteSchoolData();
                                if (CommonObjects.employeeData != null) {
                                    CommonObjects.employeeData.clear();
                                }
                                CommonObjects.schools = response.getData();
                                Log.e("SCHOOL COUNT", CommonObjects.schools.size() + " ");
                                if (CommonObjects.employeeData != null) {
                                    MainActivity.this.totalRemaingSchool = CommonObjects.schools.size() - CommonObjects.employeeData.size();
                                }
                                Log.e("Remaining Schools", MainActivity.this.totalRemaingSchool + " ");
                                MainActivity.this.setSideMenu();
                                MainActivity.this.getQuarterlyEmployeeRecordUpdateDataForMonitor(roasterDownload);
                                if (!MainActivity.this.preferenceHelper.isReferenceDataDownloaded()) {
                                    MainActivity.this.impressionsZipDialog = new SweetAlertDialog(MainActivity.this, 5);
                                    MainActivity.this.impressionsZipDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                                    MainActivity.this.impressionsZipDialog.setTitleText("Downloading ...");
                                    MainActivity.this.impressionsZipDialog.setCancelable(false);
                                    MainActivity.this.impressionsZipDialog.show();
                                    MainActivity.this.getReferenceData();
                                }
                                MainActivity.this.schoolLoadingDialog = new SweetAlertDialog(MainActivity.this, 5);
                                MainActivity.this.schoolLoadingDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
                                MainActivity.this.schoolLoadingDialog.setTitleText("Downloading");
                                MainActivity.this.schoolLoadingDialog.setContentText("school data 1 out of " + CommonObjects.schools.size());
                                MainActivity.this.schoolLoadingDialog.setCancelable(false);
                                MainActivity.this.schoolLoadingDialog.show();
                                Message message = new Message();
                                message.what = 1;
                                MainActivity.this.handle.sendMessage(message);
                                return;
                            }
                            Log.e("MA SCHOOL FAIL RES -->", response.getStatus() + " ");
                            Toast.makeText(MainActivity.this.getApplicationContext(), "Fail to fetch schools", 0).show();
                        }
                    });
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(final ANError anError) {
                    MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.27.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.onLoadingFinished();
                            if (anError.getErrorCode() == 401) {
                                if (anError.getErrorBody().contains(MainActivity.this.getString(R.string.server_authorization_denied_message))) {
                                    DialogCustom.showError(MainActivity.this, MainActivity.this.getString(R.string.authorization_session_expired_message));
                                    return;
                                } else if (anError.getResponse() != null) {
                                    DialogCustom.showError(MainActivity.this, anError.getResponse().message());
                                    return;
                                } else {
                                    DialogCustom.showError(MainActivity.this, anError.getErrorDetail());
                                    return;
                                }
                            }
                            Message message = new Message();
                            message.what = 1;
                            if (MainActivity.this.handle == null) {
                                MainActivity.this.initHandler();
                            } else {
                                MainActivity.this.handle.sendMessage(message);
                            }
                        }
                    });
                }
            });
            return;
        }
        deleteUserData();
        startActivity(new Intent(this, (Class<?>) Login.class).putExtra(Constant.MESSAGE, getString(R.string.error_internet)));
        finish();
    }

    void initHandler() {
        this.handle = new Handler(Looper.myLooper()) { // from class: com.micromerger.ssms.main.MainActivity.28
            @Override // android.os.Handler
            public void handleMessage(Message msg) throws JSONException {
                if (msg.what == 1) {
                    if (MainActivity.this.schoolDataCount < CommonObjects.schools.size()) {
                        new SchoolIDandMonitoring();
                        SchoolIDandMonitoring syncSchoolId = MainActivity.this.getSyncSchoolId();
                        int schoolID = syncSchoolId.getSchoolID();
                        int monitoringID = syncSchoolId.getMonitoringID();
                        Log.e("Handler SchoolID  :", schoolID + "");
                        if (schoolID != -1) {
                            if (CommonActions.isConnected(MainActivity.this.getApplicationContext())) {
                                MainActivity.this.getSchoolData(Integer.valueOf(schoolID), Integer.valueOf(monitoringID));
                                return;
                            }
                            if (MainActivity.this.schoolLoadingDialog != null) {
                                MainActivity.this.schoolLoadingDialog.dismiss();
                            }
                            if (MainActivity.this.impressionsZipDialog != null) {
                                MainActivity.this.impressionsZipDialog.dismiss();
                                return;
                            }
                            return;
                        }
                        if (MainActivity.this.schoolLoadingDialog != null) {
                            MainActivity.this.schoolLoadingDialog.dismiss();
                        }
                        if (MainActivity.this.impressionsZipDialog != null) {
                            MainActivity.this.impressionsZipDialog.dismiss();
                            return;
                        }
                        return;
                    }
                    if (MainActivity.this.schoolLoadingDialog != null) {
                        MainActivity.this.schoolLoadingDialog.dismiss();
                        return;
                    }
                    return;
                }
                if (msg.what == 2) {
                    if (MainActivity.this.schoolLoadingDialog != null) {
                        MainActivity.this.schoolLoadingDialog.dismiss();
                    }
                    if (MainActivity.this.impressionsZipDialog != null) {
                        MainActivity.this.impressionsZipDialog.dismiss();
                    }
                }
            }
        };
    }

    public void setSchoolData(InterfaceSchoolData schoolData) {
        this.schoolData = schoolData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSchoolData(final Integer schoolId, final Integer monitoringID) throws JSONException {
        Log.e("2nd Step", " getSchoolData()");
        Log.e("SchoolID", schoolId + " ");
        this.schoolIDCount = schoolId;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("UserID", CommonObjects.userObj.userId);
            jSONObject.put("SchoolID", schoolId);
            jSONObject.put("MonitoringID", monitoringID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("url " + schoolId, "https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor");
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetDataAfterLoginForMA").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new ParsedRequestListener<SchoolDataResponse>() { // from class: com.micromerger.ssms.main.MainActivity.30
            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onResponse(final SchoolDataResponse response) {
                MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.30.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (response.getData() != null) {
                            CommonActions.getDbHandler(MainActivity.this).addSchoolData(response.getData());
                            CommonObjects.employeeData.add(response.getData());
                            MainActivity.this.totalRemaingSchool--;
                            MainActivity.this.schoolDataCount++;
                            MainActivity.this.schoolData.schoolId(response.getData().getMonitoring().get(0).getMonitoringID().intValue());
                            if (MainActivity.this.schoolLoadingDialog != null) {
                                MainActivity.this.schoolLoadingDialog.setTitleText("Downloading ...");
                                MainActivity.this.schoolLoadingDialog.setContentText("school data " + (CommonObjects.schools.size() - MainActivity.this.totalRemaingSchool) + " out of " + CommonObjects.schools.size());
                            }
                            Message message = new Message();
                            message.what = 1;
                            if (MainActivity.this.handle == null) {
                                MainActivity.this.initHandler();
                                return;
                            } else {
                                MainActivity.this.handle.sendMessage(message);
                                return;
                            }
                        }
                        if (MainActivity.this.schoolLoadingDialog != null) {
                            MainActivity.this.schoolLoadingDialog.setTitleText("School data could not be fetch.");
                            MainActivity.this.schoolLoadingDialog.setContentText("");
                        }
                        Log.e("IGNORE School", schoolId + " ");
                        Message message2 = new Message();
                        message2.what = 2;
                        if (MainActivity.this.handle == null) {
                            MainActivity.this.initHandler();
                        } else {
                            MainActivity.this.handle.sendMessage(message2);
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onError(ANError anError) {
                Log.e("Error School Data", "ERRORRRR ");
                util.logException(anError);
                if (MainActivity.this.schoolLoadingDialog != null) {
                    MainActivity.this.schoolLoadingDialog.setTitleText("Error downloading");
                    MainActivity.this.schoolLoadingDialog.setContentText("");
                }
                Message message = new Message();
                message.what = 1;
                if (MainActivity.this.handle == null) {
                    MainActivity.this.initHandler();
                } else {
                    MainActivity.this.handle.sendMessage(message);
                }
            }
        });
    }

    private void getCMASchoolData(final Integer schoolId) throws JSONException {
        Log.e("2nd Step", " getSchoolData()");
        Log.e("SchoolID", schoolId + " ");
        this.schoolIDCount = schoolId;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("UserID", CommonObjects.userObj.userId);
            jSONObject.put("SchoolID", schoolId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("url " + schoolId, "https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor");
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetDataAfterLoginForCMO").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new ParsedRequestListener<SchoolDataResponse>() { // from class: com.micromerger.ssms.main.MainActivity.31
            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onResponse(final SchoolDataResponse response) {
                MainActivity.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.MainActivity.31.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (response.getData() != null) {
                            CommonActions.getDbHandler(MainActivity.this).addSchoolData(response.getData());
                            CommonObjects.employeeData.add(response.getData());
                            MainActivity.this.totalRemaingSchool--;
                            MainActivity.this.schoolDataCount++;
                            MainActivity.this.schoolData.schoolId(response.getData().getMonitoring().get(0).getMonitoringID().intValue());
                            if (MainActivity.this.schoolLoadingDialog != null) {
                                MainActivity.this.schoolLoadingDialog.setTitleText("Downloading ...");
                                MainActivity.this.schoolLoadingDialog.setContentText("school data " + (CommonObjects.schools.size() - MainActivity.this.totalRemaingSchool) + " out of " + CommonObjects.schools.size());
                            }
                            Message message = new Message();
                            message.what = 1;
                            if (MainActivity.this.handle == null) {
                                MainActivity.this.initHandler();
                                return;
                            } else {
                                MainActivity.this.handle.sendMessage(message);
                                return;
                            }
                        }
                        if (MainActivity.this.schoolLoadingDialog != null) {
                            MainActivity.this.schoolLoadingDialog.setTitleText("School data could not be fetch.");
                            MainActivity.this.schoolLoadingDialog.setContentText("");
                        }
                        Log.e("IGNORE School", schoolId + " ");
                        Message message2 = new Message();
                        message2.what = 2;
                        if (MainActivity.this.handle == null) {
                            MainActivity.this.initHandler();
                        } else {
                            MainActivity.this.handle.sendMessage(message2);
                        }
                    }
                });
            }

            @Override // com.androidnetworking.interfaces.ParsedRequestListener
            public void onError(ANError anError) {
                Log.e("Error School Data", "ERRORRRR ");
                if (MainActivity.this.schoolLoadingDialog != null) {
                    MainActivity.this.schoolLoadingDialog.setTitleText("Error downloading");
                    MainActivity.this.schoolLoadingDialog.setContentText("");
                }
                Message message = new Message();
                message.what = 1;
                if (MainActivity.this.handle == null) {
                    MainActivity.this.initHandler();
                } else {
                    MainActivity.this.handle.sendMessage(message);
                }
            }
        });
    }

    private class AsyncLoadDatainDB extends AsyncTask<Void, Void, Void> {
        private SweetAlertDialog progressdialog;

        private AsyncLoadDatainDB() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, 5);
            this.progressdialog = sweetAlertDialog;
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#739a5d"));
            this.progressdialog.setTitleText("Please wait...");
            this.progressdialog.setContentText("Loading Data!");
            this.progressdialog.setCancelable(false);
            this.progressdialog.show();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... strings) {
            try {
                CommonObjects.employeeData.clear();
                CommonObjects.employeeData.addAll(CommonActions.getDbHandler(MainActivity.this).getAllSchoolsData());
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void aVoid) {
            super.onPostExecute((AsyncLoadDatainDB) aVoid);
            SweetAlertDialog sweetAlertDialog = this.progressdialog;
            if (sweetAlertDialog == null || !sweetAlertDialog.isShowing()) {
                return;
            }
            this.progressdialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SchoolIDandMonitoring getSyncSchoolId() {
        boolean z;
        SchoolIDandMonitoring schoolIDandMonitoring = new SchoolIDandMonitoring();
        for (int i = 0; i < CommonObjects.schools.size(); i++) {
            if (CommonObjects.employeeData.size() == 0) {
                schoolIDandMonitoring.setSchoolID(CommonObjects.schools.get(0).getSchoolId().intValue());
                schoolIDandMonitoring.setMonitoringID(CommonObjects.schools.get(0).getMonitoring_ID());
                return schoolIDandMonitoring;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= CommonObjects.employeeData.size()) {
                    z = false;
                    break;
                }
                if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(CommonObjects.schools.get(i).getMonitoring_ID()))) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                schoolIDandMonitoring.setSchoolID(CommonObjects.schools.get(i).getSchoolId().intValue());
                schoolIDandMonitoring.setMonitoringID(CommonObjects.schools.get(i).getMonitoring_ID());
                return schoolIDandMonitoring;
            }
        }
        schoolIDandMonitoring.setSchoolID(-1);
        schoolIDandMonitoring.setMonitoringID(-1);
        return schoolIDandMonitoring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getQuarterlyEmployeeRecordUpdateDataForMonitor(boolean roasterDownload) {
        try {
            if (!this.preferenceHelper.isEmployeeRecordUpdateDataDownloaded() || roasterDownload) {
                AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetQuarterlyEmployeeRecordUpdateDataForMonitor").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetQuarterlyEmployeeRecordUpdateDataForMonitor").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setPriority(Priority.MEDIUM).build().getAsObject(QuarterlyEmployeeRecordUpdateDataForMonitor.class, new ParsedRequestListener<QuarterlyEmployeeRecordUpdateDataForMonitor>() { // from class: com.micromerger.ssms.main.MainActivity.32
                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                    public void onResponse(QuarterlyEmployeeRecordUpdateDataForMonitor response) {
                        if (response == null || !response.getStatus().booleanValue() || response.getData() == null) {
                            return;
                        }
                        List<EmployeeRecordUpdateDatum> data = response.getData();
                        ArrayList<AddStaffTicketing> arrayList = new ArrayList<>();
                        for (EmployeeRecordUpdateDatum employeeRecordUpdateDatum : data) {
                            AddStaffTicketing addStaffTicketing = new AddStaffTicketing();
                            addStaffTicketing.setTicketCnic(employeeRecordUpdateDatum.getCnic());
                            addStaffTicketing.setTicketReason(String.valueOf(employeeRecordUpdateDatum.getIssueType()));
                            addStaffTicketing.setTicketDateTime(String.valueOf(System.currentTimeMillis()));
                            addStaffTicketing.setTicketData(EmployeeRecordUpdateDatum.toJson(employeeRecordUpdateDatum));
                            arrayList.add(addStaffTicketing);
                        }
                        if (arrayList.isEmpty()) {
                            return;
                        }
                        CommonActions.getDbHandler(MainActivity.this).deleteStaffTicketingData();
                        CommonActions.getDbHandler(MainActivity.this).insertStaffTicketingData(arrayList);
                        MainActivity.this.preferenceHelper.setEmployeeRecordUpdateDataDownloaded(true);
                    }

                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                    public void onError(ANError anError) {
                        util.logException(anError);
                    }
                });
            }
        } catch (Exception e) {
            util.logException(e);
        }
    }
}