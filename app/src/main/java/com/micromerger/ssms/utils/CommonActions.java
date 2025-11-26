package com.micromerger.ssms.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.micromerger.ssms.R;
import com.micromerger.ssms.api.AuthTokenInterceptor;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.bean.SchoolDataResponse;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import net.sqlcipher.database.SQLiteDatabase;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CommonActions {
    private static final AuthTokenInterceptor authTokenInterceptor = new AuthTokenInterceptor();
    private static OkHttpClient client = null;
    private static DatabaseHandler dbHandler = null;
    Context con;

    public CommonActions(Context con) {
        this.con = con;
    }

    public static void snackMsgs(View v, String msg) {
        Snackbar snackbarMake = Snackbar.make(v, msg, 0);
        ((TextView) snackbarMake.getView().findViewById(R.id.snackbar_text)).setTextColor(-1);
        snackbarMake.show();
    }

    public static boolean testEmpty(String str) {
        return str == null || str.matches("^\\s*$");
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(email).matches();
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.show();
        return progressDialog;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager;
        if (context != null) {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } else {
            connectivityManager = (ConnectivityManager) SSMS.getAppContext().getSystemService("connectivity");
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 != null && networkInfo2.isConnected()) {
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isGpsEnabled(Context context) {
        return ((LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION)).isProviderEnabled("gps");
    }

    public void changeAppLang(String lang, Context con) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= 24) {
            setSystemLocale(configuration, locale);
        } else {
            setSystemLocaleLegacy(configuration, locale);
        }
        if (Build.VERSION.SDK_INT < 17) {
            con.getApplicationContext().getResources().updateConfiguration(configuration, con.getResources().getDisplayMetrics());
        }
    }

    public Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    public Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    public void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    public void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }

    public static Bitmap convertToBitmap(String base64Str) throws IllegalArgumentException {
        if (base64Str == null) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(base64Str.substring(base64Str.indexOf(",") + 1), 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    public static OkHttpClient getOkHttpClient() {
        return getTestOkHttpClient();
    }

    public static OkHttpClient getLiveOkHttpClient() throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException {
        OkHttpClient okHttpClient = client;
        if (okHttpClient == null || !okHttpClient.interceptors().contains(authTokenInterceptor)) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(SSMS.getAppContext().getResources().openRawResource(R.raw.ssms_prod_updated));
                try {
                    Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(bufferedInputStream);
                    System.out.println("ca=" + ((X509Certificate) certificateGenerateCertificate).getSubjectDN());
                    bufferedInputStream.close();
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    keyStore.load(null, null);
                    keyStore.setCertificateEntry("ca", certificateGenerateCertificate);
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    SSLContext sSLContext = SSLContext.getInstance("TLS");
                    sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
                    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.micromerger.ssms.utils.CommonActions.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    });
                    client = new OkHttpClient().newBuilder().sslSocketFactory(sSLContext.getSocketFactory(), (X509TrustManager) trustManagerFactory.getTrustManagers()[0]).hostnameVerifier(new HostnameVerifier() { // from class: com.micromerger.ssms.utils.CommonActions.2
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    }).readTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).connectTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).writeTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).addInterceptor(authTokenInterceptor).build();
                } catch (Throwable th) {
                    bufferedInputStream.close();
                    throw th;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", e.getMessage());
            }
        }
        return client;
    }

    public static OkHttpClient getTestOkHttpClient() {
        OkHttpClient okHttpClient = client;
        if (okHttpClient == null || !okHttpClient.interceptors().contains(authTokenInterceptor)) {
            try {
                client = new OkHttpClient().newBuilder().readTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).connectTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).writeTimeout(WebConstant.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS).addInterceptor(authTokenInterceptor).build();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", e.getMessage());
            }
        }
        return client;
    }

    public static void openXLSX(Context context, File xlsx) {
        Uri uriFromFile = Uri.fromFile(new File(xlsx.getPath()));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uriFromFile, "application/vnd.ms-excel");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            DialogCustom.showSuccessDialog(context, "Downloading Completed", "No Application available to view XLS,You can access report from SSMS folder.");
        }
    }

    public static DatabaseHandler getDbHandler(Context context) {
        if (dbHandler == null) {
            dbHandler = new DatabaseHandler(context);
        }
        return dbHandler;
    }

    public static String convertToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static String convertImageToBase64(String imagePath) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmapDecodeFile == null) {
            return "";
        }
        bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static Bitmap decodeFile(File file) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            while ((options.outWidth / i) / 2 >= 700 && (options.outHeight / i) / 2 >= 700) {
                i *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String bitmapToBasr64String(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File file : fileOrDirectory.listFiles()) {
                deleteRecursive(file);
            }
        }
        fileOrDirectory.delete();
    }

    public static String formatFileSize(long size) {
        double d = size;
        double d2 = d / 1024.0d;
        double d3 = d2 / 1024.0d;
        double d4 = d3 / 1024.0d;
        double d5 = d4 / 1024.0d;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (d5 > 1.0d) {
            return decimalFormat.format(d5).concat(" TB");
        }
        if (d4 > 1.0d) {
            return decimalFormat.format(d4).concat(" GB");
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3).concat(" MB");
        }
        if (d2 > 1.0d) {
            return decimalFormat.format(d2).concat(" KB");
        }
        return decimalFormat.format(d).concat(" Bytes");
    }

    public static void showKeyboard(Context ctx) {
        ((InputMethodManager) ctx.getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    public static void hideKeyboard(Context ctx) {
        ((InputMethodManager) ctx.getSystemService("input_method")).toggleSoftInput(1, 0);
    }

    public static void hideSoftKeyboard(Context context, View view) {
        if (view != null) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void deleteCache(Context context) {
        try {
            deleteDir(context.getCacheDir());
        } catch (Exception unused) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (String str : dir.list()) {
                if (!deleteDir(new File(dir, str))) {
                    return false;
                }
            }
            return dir.delete();
        }
        if (dir == null || !dir.isFile()) {
            return false;
        }
        return dir.delete();
    }

    public static boolean getDataAfterLoginForManagers(Activity activity, School school, BasePreferenceHelper preferenceHelper) throws JSONException {
        boolean[] zArr = {false};
        if (isConnected(activity)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("UserID", CommonObjects.userObj.userId);
                jSONObject.put("SchoolID", school.getSchoolId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(ImagesContract.URL + CommonObjects.userObj.userId, "https://mne.seld.gos.pk/Services/api/Monitoring/GetDataAfterLoginForManagers");
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Monitoring/GetDataAfterLoginForManagers").setOkHttpClient(getOkHttpClient()).setTag((Object) "GetDataAfterLoginForManagers").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new AnonymousClass3(activity, zArr, school, preferenceHelper));
        } else {
            DialogCustom.showError(activity, "No internet connection found.");
        }
        return zArr[0];
    }

    /* renamed from: com.micromerger.ssms.utils.CommonActions$3, reason: invalid class name */
    class AnonymousClass3 implements ParsedRequestListener<SchoolDataResponse> {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ BasePreferenceHelper val$preferenceHelper;
        final /* synthetic */ boolean[] val$result;
        final /* synthetic */ School val$school;

        AnonymousClass3(final Activity val$activity, final boolean[] val$result, final School val$school, final BasePreferenceHelper val$preferenceHelper) {
            this.val$activity = val$activity;
            this.val$result = val$result;
            this.val$school = val$school;
            this.val$preferenceHelper = val$preferenceHelper;
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onResponse(final SchoolDataResponse response) {
            final Activity activity = this.val$activity;
            final boolean[] zArr = this.val$result;
            final School school = this.val$school;
            final BasePreferenceHelper basePreferenceHelper = this.val$preferenceHelper;
            activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$CommonActions$3$aItm-UdZBdTePywa5U3WX1vhSn4
                @Override // java.lang.Runnable
                public final void run() {
                    CommonActions.AnonymousClass3.lambda$onResponse$0(response, activity, zArr, school, basePreferenceHelper);
                }
            });
        }

        static /* synthetic */ void lambda$onResponse$0(SchoolDataResponse schoolDataResponse, Activity activity, boolean[] zArr, School school, BasePreferenceHelper basePreferenceHelper) {
            Log.e("response", schoolDataResponse.toString());
            if (schoolDataResponse.getData() != null) {
                CommonActions.getDbHandler(activity).addSchoolData(schoolDataResponse.getData());
                CommonObjects.employeeData.clear();
                CommonObjects.employeeData = CommonActions.getDbHandler(activity).getAllSchoolsData();
                zArr[0] = true;
                CommonObjects.monitoring = null;
                if (CommonObjects.employeeData.size() > 0) {
                    for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                        if (CommonObjects.employeeData.get(i).getSchoolId().equals(school.getSchoolId())) {
                            Log.e("employeeData", CommonObjects.employeeData.get(i).getDbId() + "_");
                            CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                            CommonObjects.school = school;
                            basePreferenceHelper.setCurrentSchoolId(school.getSchoolId().intValue());
                            basePreferenceHelper.setCurrentMonitoringId(school.getMonitoring_ID());
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            zArr[0] = false;
            DialogCustom.showError(activity, "School data could not be loaded.");
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onError(ANError anError) {
            this.val$result[0] = false;
            DialogCustom.showError(this.val$activity.getApplicationContext(), anError.getMessage());
        }
    }

    public static boolean getDataAfterLoginForMA_CMO(Activity activity, School school, BasePreferenceHelper preferenceHelper) throws JSONException {
        boolean[] zArr = {false};
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("UserID", CommonObjects.userObj.userId);
            jSONObject.put("SchoolID", school.getSchoolId());
            jSONObject.put("MonitoringID", school.getMonitoring_ID());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/MobileApp/GetSchoolDetailForMonitor").setOkHttpClient(getOkHttpClient()).setTag((Object) "GetDataAfterLoginForMA").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SchoolDataResponse.class, new AnonymousClass4(zArr, activity, school, preferenceHelper));
        return zArr[0];
    }

    /* renamed from: com.micromerger.ssms.utils.CommonActions$4, reason: invalid class name */
    class AnonymousClass4 implements ParsedRequestListener<SchoolDataResponse> {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ BasePreferenceHelper val$preferenceHelper;
        final /* synthetic */ boolean[] val$result;
        final /* synthetic */ School val$school;

        AnonymousClass4(final boolean[] val$result, final Activity val$activity, final School val$school, final BasePreferenceHelper val$preferenceHelper) {
            this.val$result = val$result;
            this.val$activity = val$activity;
            this.val$school = val$school;
            this.val$preferenceHelper = val$preferenceHelper;
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onResponse(final SchoolDataResponse response) {
            this.val$result[0] = true;
            if (response.getData() != null) {
                CommonActions.getDbHandler(this.val$activity).addSchoolData(response.getData());
                CommonObjects.employeeData.add(response.getData());
                int i = 0;
                while (true) {
                    if (i >= CommonObjects.employeeData.size()) {
                        break;
                    }
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(this.val$school.getMonitoring_ID()))) {
                        CommonObjects.monitoring = CommonObjects.employeeData.get(i);
                        CommonObjects.school = this.val$school;
                        this.val$preferenceHelper.setCurrentSchoolId(this.val$school.getSchoolId().intValue());
                        this.val$preferenceHelper.setCurrentMonitoringId(this.val$school.getMonitoring_ID());
                        break;
                    }
                    i++;
                }
                for (int i2 = 0; i2 < CommonObjects.schools.size(); i2++) {
                    try {
                        for (int i3 = 0; i3 < CommonObjects.employeeData.size(); i3++) {
                            if (CommonObjects.schools.get(i2).getMonitoring_ID() == this.val$school.getSchoolId().intValue() && CommonObjects.employeeData.get(i3).getMonitoring().get(0).getMonitoringID().equals(Integer.valueOf(CommonObjects.schools.get(i2).getMonitoring_ID()))) {
                                CommonObjects.schools.get(i2).setDownloaded(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            final Activity activity = this.val$activity;
            activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$CommonActions$4$lKdJhcq_UMsBe_-uWA8Ht9_uQpU
                @Override // java.lang.Runnable
                public final void run() {
                    Toast.makeText(activity, "School not found", 0).show();
                }
            });
        }

        @Override // com.androidnetworking.interfaces.ParsedRequestListener
        public void onError(ANError anError) {
            this.val$result[0] = false;
            final Activity activity = this.val$activity;
            activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$CommonActions$4$NVfoF_fhWU_NN06IGubdyZZrsrM
                @Override // java.lang.Runnable
                public final void run() {
                    Activity activity2 = activity;
                    Toast.makeText(activity2, activity2.getString(R.string.download_error), 0).show();
                }
            });
        }
    }
}
