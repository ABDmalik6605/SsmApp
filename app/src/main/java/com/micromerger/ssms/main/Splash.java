package com.micromerger.ssms.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.micromerger.ssms.R;
import com.micromerger.ssms.user.Login;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.DatabaseHandler;
import com.micromerger.ssms.utils.util;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class Splash extends AppCompatActivity {
    BasePreferenceHelper preferenceHelper;
    ProgressBar progress_wheel;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash);
        this.progress_wheel = (ProgressBar) findViewById(R.id.progress_wheel);
        this.preferenceHelper = new BasePreferenceHelper(getApplicationContext());
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        File databasePath = getDatabasePath(DatabaseHandler.DATABASE_NAME);
        if (((this.preferenceHelper.getDatabaseVersion() == null || this.preferenceHelper.getDatabaseVersion().isEmpty()) ? 0 : Integer.parseInt(this.preferenceHelper.getDatabaseVersion())) != 9 && databasePath.exists()) {
            Log.i("DatabaseFile", "onCreate: deleted database file " + databasePath.delete());
            databaseHandler.openOrCreateDatabase(this);
            deleteUserData();
        } else {
            databaseHandler.openOrCreateDatabase(this);
        }
        new LoadingThread(this, null).start();
        Log.e("FLAVOUR", "https://mne.seld.gos.pk/Services/api/ ");
    }

    private void onFileDownloadStart(final String url, final File file) {
        AndroidNetworking.download(url, file.getPath(), url.substring(url.lastIndexOf("/") + 1)).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "SchoolsEmployee").setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.micromerger.ssms.main.Splash.2
            @Override // com.androidnetworking.interfaces.DownloadProgressListener
            public void onProgress(long bytesDownloaded, long totalBytes) {
            }
        }).startDownload(new AnonymousClass1(file, url));
    }

    /* renamed from: com.micromerger.ssms.main.Splash$1, reason: invalid class name */
    class AnonymousClass1 implements DownloadListener {
        final /* synthetic */ File val$file;
        final /* synthetic */ String val$url;

        AnonymousClass1(final File val$file, final String val$url) {
            this.val$file = val$file;
            this.val$url = val$url;
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onDownloadComplete() {
            Splash.this.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.Splash.1.1
                @Override // java.lang.Runnable
                public void run() {
                    new Thread(new Runnable() { // from class: com.micromerger.ssms.main.Splash.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = AnonymousClass1.this.val$file.getPath() + AnonymousClass1.this.val$url.substring(AnonymousClass1.this.val$url.lastIndexOf("/"));
                            File file = new File(AnonymousClass1.this.val$file.getPath() + "/unzipped/");
                            try {
                                util.unzip(new File(str), file, "");
                                Log.d("Splash", "run: Unzip File " + file);
                            } catch (IOException e) {
                                Log.e("Splash", "run: Unzip Exception " + e);
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            });
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onError(final ANError anError) {
            Log.e("Splash", "onError: " + anError.getErrorDetail());
            Log.e("Splash", "onError: " + anError.getResponse());
            anError.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressStart() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.Splash.3
            @Override // java.lang.Runnable
            public void run() {
                Splash.this.progress_wheel.setVisibility(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressStop() {
        runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.main.Splash.4
            @Override // java.lang.Runnable
            public void run() {
                Splash.this.progress_wheel.setVisibility(8);
            }
        });
    }

    private class LoadingThread extends Thread {
        private LoadingThread() {
        }

        /* synthetic */ LoadingThread(Splash splash, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Splash.this.preferenceHelper.getUser() != null) {
                Splash.this.progressStart();
                util.logException(new Exception("App Started User Id: " + Splash.this.preferenceHelper.getUser().userId));
                try {
                    if (CommonActions.getDbHandler(Splash.this).getAllSchoolsData() != null) {
                        CommonObjects.userObj = Splash.this.preferenceHelper.getUser();
                        CommonObjects.employeeData = CommonActions.getDbHandler(Splash.this).getAllSchoolsData();
                    }
                    if (!CommonObjects.userObj.role.name().equals(CommonObjects.Roles.MA.name())) {
                        Splash.this.progressStop();
                        Intent intent = new Intent(Splash.this, (Class<?>) MainActivity.class);
                        intent.setFlags(268468224);
                        Splash.this.startActivity(intent);
                        Splash.this.finish();
                        return;
                    }
                    if (Splash.this.preferenceHelper.getAllSchools() == null) {
                        Splash.this.progressStop();
                        Intent intent2 = new Intent(Splash.this, (Class<?>) Login.class);
                        intent2.setFlags(268468224);
                        Splash.this.startActivity(intent2);
                        Splash.this.finish();
                        return;
                    }
                    Splash.this.progressStop();
                    Intent intent3 = new Intent(Splash.this, (Class<?>) MainActivity.class);
                    intent3.setFlags(268468224);
                    Splash.this.startActivity(intent3);
                    Splash.this.finish();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Splash.this.progressStop();
                    Splash.this.deleteUserData();
                    Intent intent4 = new Intent(Splash.this, (Class<?>) Login.class);
                    intent4.setFlags(268468224);
                    Splash.this.startActivity(intent4);
                    Splash.this.finish();
                    Log.e("ALL Data Deleted", "ERROR Splash");
                    return;
                }
            }
            try {
                CommonObjects.employeeData = CommonActions.getDbHandler(Splash.this).getAllSchoolsData();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            Intent intent5 = new Intent(Splash.this, (Class<?>) Login.class);
            intent5.setFlags(268468224);
            Splash.this.startActivity(intent5);
            Splash.this.finish();
        }
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
        File file = new File(getFilesDir().getAbsolutePath());
        Log.e("path", file.getAbsolutePath());
        CommonActions.deleteRecursive(file);
        CommonActions.deleteCache(this);
        Log.e("ALL Data Deleted", "SSMS");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}
