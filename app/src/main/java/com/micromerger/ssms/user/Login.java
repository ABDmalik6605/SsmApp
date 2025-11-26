package com.micromerger.ssms.user;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.messaging.Constants;
import com.micromerger.ssms.BuildConfig;
import com.micromerger.ssms.PrivacyActivity;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.user.beans.forgotPassword.ForgotPasswordResponse;
import com.micromerger.ssms.user.beans.login.LoginResponse;
import com.micromerger.ssms.user.beans.login.Roles;
import com.micromerger.ssms.user.beans.login.Users;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.safetynet.SafetyNetUtils;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class Login extends Activity implements View.OnClickListener {
    private static final int CAMERA_RESULT = 101;
    private static final int READ_EXTERNAL_STORAGE_RESULT = 103;
    private static final int WRITE_EXTERNAL_STORAGE_RESULT = 102;
    Button btnPrivacy;
    Button btnforgot;
    Button btnlogin;
    EditText edit_pass;
    EditText edit_user;
    ImageView maincontent_logo;
    RelativeLayout parent;
    ProgressDialog pd;
    private ArrayList<String> permissionsToRequest;
    BasePreferenceHelper preferenceHelper;
    int resultCode = 0;
    ScrollView scv;
    Animation shake;
    Snackbar snackbar;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DialogCustom.showInfoDialog(this, "IMPORTANT", getString(R.string.info_alert_content));
        this.shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.parent);
        this.parent = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.snackbar = Snackbar.make(this.parent, "", -2);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scv);
        this.scv = scrollView;
        scrollView.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.maincontent_logo);
        this.maincontent_logo = imageView;
        imageView.setOnClickListener(this);
        EditText editText = (EditText) findViewById(R.id.edit_user);
        this.edit_user = editText;
        editText.setOnClickListener(this);
        EditText editText2 = (EditText) findViewById(R.id.edit_pass);
        this.edit_pass = editText2;
        editText2.setOnClickListener(this);
        Button button = (Button) findViewById(R.id.btnlogin);
        this.btnlogin = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.btnforgot);
        this.btnforgot = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.btn_privacy);
        this.btnPrivacy = button3;
        button3.setOnClickListener(this);
        this.preferenceHelper = new BasePreferenceHelper(getApplicationContext());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("android.permission.CAMERA");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        ArrayList<String> arrayListFindUnAskedPermissions = findUnAskedPermissions(arrayList);
        this.permissionsToRequest = arrayListFindUnAskedPermissions;
        if (arrayListFindUnAskedPermissions.size() > 0 && Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> arrayList2 = this.permissionsToRequest;
            requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), this.resultCode);
        }
        if (getIntent().hasExtra(Constant.MESSAGE)) {
            DialogCustom.showError(this, getIntent().getStringExtra(Constant.MESSAGE));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Snackbar snackbar = this.snackbar;
        if (snackbar != null) {
            snackbar.dismiss();
        }
        switch (v.getId()) {
            case R.id.btn_privacy /* 2131230868 */:
                startActivity(new Intent(this, (Class<?>) PrivacyActivity.class));
                break;
            case R.id.btnforgot /* 2131230877 */:
                Snackbar snackbarMake = Snackbar.make(this.parent, "", -2);
                this.snackbar = snackbarMake;
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarMake.getView();
                snackbarLayout.setBackgroundColor(Color.parseColor("#455A64"));
                ((TextView) snackbarLayout.findViewById(R.id.snackbar_text)).setVisibility(4);
                View viewInflate = getLayoutInflater().inflate(R.layout.snackbar, (ViewGroup) null);
                Button button = (Button) viewInflate.findViewById(R.id.btn_send);
                final EditText editText = (EditText) viewInflate.findViewById(R.id.et_name);
                final EditText editText2 = (EditText) viewInflate.findViewById(R.id.et_email);
                ((Button) viewInflate.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.user.Login.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Login login = Login.this;
                        login.hideKeyboard(login.btnforgot);
                        if (Login.this.snackbar != null) {
                            Login.this.snackbar.dismiss();
                        }
                    }
                });
                button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.user.Login.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Login login = Login.this;
                        login.hideKeyboard(login.btnforgot);
                        if (CommonActions.testEmpty(editText.getText().toString())) {
                            editText.startAnimation(Login.this.shake);
                            editText.requestFocus();
                            editText.setError(Login.this.getString(R.string.login_forgot_username_empty));
                            return;
                        }
                        if (CommonActions.testEmpty(editText2.getText().toString())) {
                            editText2.startAnimation(Login.this.shake);
                            editText2.requestFocus();
                            editText2.setError(Login.this.getString(R.string.login_forgot_cnic_empty));
                        } else {
                            if (CommonActions.isConnected(Login.this.getApplicationContext())) {
                                final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(Login.this);
                                HashMap map = new HashMap();
                                map.put("UserName", editText.getText().toString());
                                map.put("CNIC", editText2.getText().toString());
                                Log.e("Object", map + " ");
                                AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Authenticate/ForgotPassword").setOkHttpClient(CommonActions.getOkHttpClient()).addApplicationJsonBody(map).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "Forgot Password").setPriority(Priority.MEDIUM).build().getAsObject(ForgotPasswordResponse.class, new ParsedRequestListener<ForgotPasswordResponse>() { // from class: com.micromerger.ssms.user.Login.2.1
                                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                                    public void onResponse(ForgotPasswordResponse response) {
                                        ProgressDialog progressDialog = progressDialogCreateProgressDialog;
                                        if (progressDialog != null && progressDialog.isShowing()) {
                                            progressDialogCreateProgressDialog.dismiss();
                                        }
                                        if (response.getStatus().booleanValue()) {
                                            if (Login.this.snackbar != null && Login.this.snackbar.isShown()) {
                                                Login.this.snackbar.dismiss();
                                            }
                                            DialogCustom.showSuccessDialog(Login.this, "Success", response.getMessage());
                                            return;
                                        }
                                        DialogCustom.showError(Login.this, response.getMessage());
                                    }

                                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                                    public void onError(ANError anError) {
                                        ProgressDialog progressDialog = progressDialogCreateProgressDialog;
                                        if (progressDialog != null && progressDialog.isShowing()) {
                                            progressDialogCreateProgressDialog.dismiss();
                                        }
                                        CommonActions.snackMsgs(Login.this.parent, anError.getErrorDetail());
                                    }
                                });
                                return;
                            }
                            CommonActions.snackMsgs(Login.this.parent, Login.this.getString(R.string.error_internet));
                        }
                    }
                });
                snackbarLayout.addView(viewInflate, 0);
                Snackbar snackbar2 = this.snackbar;
                if (snackbar2 != null) {
                    snackbar2.show();
                    break;
                }
                break;
            case R.id.btnlogin /* 2131230878 */:
                if (CommonActions.testEmpty(this.edit_user.getText().toString()) && CommonActions.testEmpty(this.edit_pass.getText().toString())) {
                    this.edit_user.startAnimation(this.shake);
                    this.edit_pass.startAnimation(this.shake);
                    CommonActions.snackMsgs(this.parent, getString(R.string.login_emptyfields));
                } else if (CommonActions.testEmpty(this.edit_user.getText().toString())) {
                    this.edit_user.startAnimation(this.shake);
                    CommonActions.snackMsgs(this.parent, getString(R.string.login_enterusername));
                } else if (CommonActions.testEmpty(this.edit_pass.getText().toString())) {
                    this.edit_pass.startAnimation(this.shake);
                    CommonActions.snackMsgs(this.parent, getString(R.string.login_enterpass));
                } else if (CommonActions.isConnected(getApplicationContext())) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 && checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                            loginService(this.edit_user.getText().toString(), this.edit_pass.getText().toString());
                        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                            loginService(this.edit_user.getText().toString(), this.edit_pass.getText().toString());
                        } else {
                            ArrayList<String> arrayList = this.permissionsToRequest;
                            requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), this.resultCode);
                            loginService(this.edit_user.getText().toString(), this.edit_pass.getText().toString());
                        }
                    } else {
                        loginService(this.edit_user.getText().toString(), this.edit_pass.getText().toString());
                    }
                } else {
                    CommonActions.snackMsgs(this.parent, getString(R.string.error_internet));
                }
                hideKeyboard(this.btnlogin);
                break;
        }
    }

    private double totalRam() {
        try {
            ((ActivityManager) getSystemService("activity")).getMemoryInfo(new ActivityManager.MemoryInfo());
            return r1.totalMem / 1.073741824E9d;
        } catch (Exception e) {
            util.logException(e);
            return 0.0d;
        }
    }

    private double availableRam() {
        try {
            ((ActivityManager) getSystemService("activity")).getMemoryInfo(new ActivityManager.MemoryInfo());
            return r1.availMem / 1.073741824E9d;
        } catch (Exception e) {
            util.logException(e);
            return 0.0d;
        }
    }

    public void loginService(final String user, String pass) {
        try {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(this);
            boolean zSendSafetyNetRequest = SafetyNetUtils.INSTANCE.sendSafetyNetRequest(this, this);
            HashMap map = new HashMap();
            map.put("User_Name", user);
            map.put("Password", pass);
            map.put("App_Version", BuildConfig.VERSION_NAME);
            map.put("Phone_Device_Id", Settings.Secure.getString(getContentResolver(), "android_id"));
            map.put("Phone_OS_Name", "Android " + Build.VERSION.RELEASE);
            map.put("Phone_OS_API_Version", String.valueOf(Build.VERSION.SDK_INT));
            map.put("Phone_Manufacturer_Name", Build.MANUFACTURER);
            map.put("Phone_Model_Name", Build.MODEL);
            map.put("Phone_Total_RAM_GB", String.format("%.2f", Double.valueOf(totalRam())));
            map.put("Phone_Available_RAM_GB", String.format("%.2f", Double.valueOf(availableRam())));
            map.put("Is_Phone_Rooted", Boolean.valueOf(zSendSafetyNetRequest));
            Log.d("Login Request", map + " ");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/Login").setOkHttpClient(CommonActions.getOkHttpClient()).addApplicationJsonBody(map).addHeaders(HttpHeaders.CONTENT_TYPE, AbstractSpiCall.ACCEPT_JSON_VALUE).addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setTag((Object) "Login").setPriority(Priority.MEDIUM).build().getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() { // from class: com.micromerger.ssms.user.Login.3
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(LoginResponse response) {
                    ProgressDialog progressDialog;
                    if (progressDialogCreateProgressDialog.isShowing() && (progressDialog = progressDialogCreateProgressDialog) != null) {
                        progressDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        Log.d("Login Response", response.getData().getUsers().getUserName());
                        Users users = response.getData().getUsers();
                        Roles roles = response.getData().getRoles();
                        Login.this.preferenceHelper.setAuthToken(response.getAuthToken());
                        CommonObjects.userObj = new UserBean();
                        CommonObjects.userObj.userId = String.valueOf(users.getUserID());
                        CommonObjects.userObj.phoneNumber = users.getMobileNo();
                        CommonObjects.userObj.userName = users.getUserName();
                        CommonObjects.userObj.email = users.getEmailAddress();
                        CommonObjects.userObj.roleId = String.valueOf(roles.getRoleID());
                        CommonObjects.userObj.latitude = users.getLatitude();
                        CommonObjects.userObj.longitude = users.getLongitude();
                        if (roles.getDescription().equals(CommonObjects.Roles.SEC.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.SEC;
                        } else if (roles.getDescription().equals(CommonObjects.Roles.DG.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.DG;
                        } else if (roles.getDescription().equals(CommonObjects.Roles.DDO.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.DDO;
                        } else if (roles.getDescription().equals(CommonObjects.Roles.EDO.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.EDO;
                        } else if (roles.getDescription().equals(CommonObjects.Roles.CMO.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.CMO;
                        } else if (roles.getDescription().equals(CommonObjects.Roles.MA.name())) {
                            CommonObjects.userObj.role = CommonObjects.Roles.MA;
                        }
                        Login.this.preferenceHelper.putUser(CommonObjects.userObj);
                        Login.this.preferenceHelper.setDatabaseVersion(String.valueOf(9));
                        util.logException(new Exception("Login Performed User Id: " + users.getUserID() + " Role Id: " + roles.getRoleID()));
                        Login.this.deleteUserBiometric();
                        if (users.getUserImageURL() != null) {
                            if (!users.getUserImageURL().toString().equals("")) {
                                Login.this.thumbFile(users.getUserImageURL().toString(), CommonObjects.userObj.userId + ".ibsm_template");
                                return;
                            }
                            Intent intent = new Intent(Login.this, (Class<?>) MainActivity.class);
                            intent.setFlags(268468224);
                            Login.this.startActivity(intent);
                            Login.this.finish();
                            return;
                        }
                        Intent intent2 = new Intent(Login.this, (Class<?>) MainActivity.class);
                        intent2.setFlags(268468224);
                        Login.this.startActivity(intent2);
                        Login.this.finish();
                        return;
                    }
                    CommonObjects.userObj = null;
                    CommonActions.snackMsgs(Login.this.parent, response.getMessage());
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    ProgressDialog progressDialog = progressDialogCreateProgressDialog;
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    CommonObjects.userObj = null;
                    CommonActions.snackMsgs(Login.this.parent, anError.getErrorDetail());
                    anError.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteUserBiometric() {
        try {
            File file = new File(getFilesDir().toString() + "/user_biometric");
            if (file.isDirectory()) {
                String[] list = file.list();
                if (list != null) {
                    for (String str : list) {
                        new File(file, str).delete();
                    }
                }
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    public void thumbFile(String url, final String fileName) {
        final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(this);
        File file = new File(getApplicationContext().getFilesDir().toString());
        Log.e("THUMB URL", url.toString());
        AndroidNetworking.download(url.toString(), file.getPath(), fileName).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "THUMB FILE").setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.micromerger.ssms.user.Login.5
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public void onProgress(long bytesDownloaded, long totalBytes) {
            }
        }).startDownload(new DownloadListener() { // from class: com.micromerger.ssms.user.Login.4
            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onDownloadComplete() {
                ProgressDialog progressDialog = progressDialogCreateProgressDialog;
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialogCreateProgressDialog.dismiss();
                }
                Intent intent = new Intent(Login.this, (Class<?>) MainActivity.class);
                intent.setFlags(268468224);
                Login.this.startActivity(intent);
                Login.this.finish();
            }

            @Override // com.androidnetworking.interfaces.DownloadListener
            public void onError(ANError anError) {
                ProgressDialog progressDialog = progressDialogCreateProgressDialog;
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialogCreateProgressDialog.dismiss();
                }
                CommonObjects.userObj = null;
                Login.this.preferenceHelper.putUser(CommonObjects.userObj);
                CommonActions.snackMsgs(Login.this.parent, anError.getErrorDetail());
                Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, anError.toString() + ", " + anError.getResponse() + ", " + anError.getErrorBody() + ", " + anError.getErrorDetail());
            }
        });
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
}
