package com.micromerger.ssms.export;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.micromerger.ssms.R;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.MyFilesProvider;
import com.micromerger.ssms.utils.SaveToDownloads;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ExportFragment extends BaseFragment {
    Calendar calendar;
    Button downloadBtn;
    TextView fromDate;
    View mView;
    Button openBtn;
    ProgressBar progressBar;
    TextView progressText;
    TextView toDate;
    private final String format = "dd-MM-yyyy";
    private final String exportFormat = "MM-dd-yyyy";

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_export, container, false);
        this.calendar = Calendar.getInstance();
        this.fromDate = (TextView) this.mView.findViewById(R.id.from_date);
        this.toDate = (TextView) this.mView.findViewById(R.id.to_date);
        this.downloadBtn = (Button) this.mView.findViewById(R.id.downloadBtn);
        this.openBtn = (Button) this.mView.findViewById(R.id.openBtn);
        this.progressBar = (ProgressBar) this.mView.findViewById(R.id.progressBar);
        this.progressText = (TextView) this.mView.findViewById(R.id.progressText);
        disableButton();
        this.fromDate.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$BnfXf4BkQi9UMJR13f5M43TfgT0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$ExportFragment(view);
            }
        });
        this.toDate.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$oM_qwTT0luvOglS0mzxaT6btgaE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$1$ExportFragment(view);
            }
        });
        this.downloadBtn.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$90u0KFnOop21xrcrLjPTH6oIcW4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$2$ExportFragment(view);
            }
        });
        return this.mView;
    }

    public /* synthetic */ void lambda$onCreateView$0$ExportFragment(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.fromDate), this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onCreateView$1$ExportFragment(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.toDate), this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onCreateView$2$ExportFragment(View view) {
        try {
            downloadFile(getStartDate(), getEndDate());
        } catch (ParseException unused) {
            DialogCustom.showError(getContext(), "Error Formatting Date");
        }
    }

    private String getStartDate() throws ParseException {
        return new SimpleDateFormat("MM-dd-yyyy").format((Object) new SimpleDateFormat("dd-MM-yyyy").parse(this.fromDate.getText().toString()));
    }

    private String getEndDate() throws ParseException {
        return new SimpleDateFormat("MM-dd-yyyy").format((Object) new SimpleDateFormat("dd-MM-yyyy").parse(this.toDate.getText().toString()));
    }

    private DatePickerDialog.OnDateSetListener dateSetListener(final TextView textView) {
        return new DatePickerDialog.OnDateSetListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$zd6LO7CG9QPMA5S93PRlvxRxSjg
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                this.f$0.lambda$dateSetListener$3$ExportFragment(textView, datePicker, i, i2, i3);
            }
        };
    }

    public /* synthetic */ void lambda$dateSetListener$3$ExportFragment(TextView textView, DatePicker datePicker, int i, int i2, int i3) {
        this.calendar.set(1, i);
        this.calendar.set(2, i2);
        this.calendar.set(5, i3);
        setDate(textView);
    }

    private void setDate(TextView textView) {
        textView.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(this.calendar.getTime()));
        this.downloadBtn.setVisibility(0);
        this.openBtn.setVisibility(8);
        checkValidation();
    }

    private void disableButton() {
        this.downloadBtn.setFocusable(false);
        this.downloadBtn.setClickable(false);
        this.downloadBtn.setEnabled(false);
        this.downloadBtn.setAlpha(0.5f);
    }

    private void enableButton() {
        this.downloadBtn.setFocusable(true);
        this.downloadBtn.setClickable(true);
        this.downloadBtn.setEnabled(true);
        this.downloadBtn.setAlpha(1.0f);
    }

    private void checkValidation() {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = null;
        try {
            date = simpleDateFormat.parse(this.fromDate.getText().toString());
        } catch (ParseException e) {
            e = e;
            date = null;
        }
        try {
            date2 = simpleDateFormat.parse(this.toDate.getText().toString());
        } catch (ParseException e2) {
            e = e2;
            e.printStackTrace();
            if (date == null) {
            }
            disableButton();
        }
        if (date == null && date2 != null && (date.before(date2) || date.equals(date2))) {
            enableButton();
        } else {
            disableButton();
        }
    }

    private void downloadFile(String startDate, String endDate) {
        File file;
        if (CommonActions.isConnected(getContext())) {
            try {
                if (Build.VERSION.SDK_INT >= 29) {
                    file = getContext().getFilesDir();
                } else {
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "SSMSEXPORTS");
                }
                String str = "?user_id=" + CommonObjects.userObj.userId + "&start_date=" + startDate + "&end_date=" + endDate;
                String str2 = "SSMS_Monitoring_Report_" + startDate + "_" + endDate + "_" + System.currentTimeMillis() + ".xlsx";
                File file2 = new File(file, str2);
                this.progressBar.setVisibility(0);
                this.progressText.setVisibility(0);
                this.progressText.setText("Starting Download...");
                this.downloadBtn.setVisibility(8);
                AndroidNetworking.download("https://mne.seld.gos.pk/Services/api/MobileApp/GetMonitoringReport" + str, file.getPath(), str2).setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetMonitoringReportByUser").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setPriority(Priority.MEDIUM).build().setDownloadProgressListener(new DownloadProgressListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$S4m1yDPy6mVUV0iHUlzGRaWzoO4
                    @Override // com.androidnetworking.interfaces.DownloadProgressListener
                    public final void onProgress(long j, long j2) {
                        this.f$0.lambda$downloadFile$4$ExportFragment(j, j2);
                    }
                }).startDownload(new AnonymousClass1(file2, str2));
                return;
            } catch (Exception e) {
                this.progressText.setVisibility(8);
                this.progressBar.setVisibility(8);
                DialogCustom.showError(getContext(), "Download Error");
                util.logException(e);
                return;
            }
        }
        DialogCustom.showError(getContext(), "No Internet Connection");
    }

    public /* synthetic */ void lambda$downloadFile$4$ExportFragment(long j, long j2) {
        int i = (int) ((j * 100.0f) / j2);
        if (Build.VERSION.SDK_INT >= 24) {
            this.progressBar.setProgress(i, true);
        } else {
            this.progressBar.setProgress(i);
        }
        this.progressText.setText("Downloading: " + i + " / 100 %");
    }

    /* renamed from: com.micromerger.ssms.export.ExportFragment$1, reason: invalid class name */
    class AnonymousClass1 implements DownloadListener {
        final /* synthetic */ File val$file;
        final /* synthetic */ String val$fileName;

        AnonymousClass1(final File val$file, final String val$fileName) {
            this.val$file = val$file;
            this.val$fileName = val$fileName;
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onDownloadComplete() throws Throwable {
            ExportFragment.this.progressText.setText("Finalizing...");
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    Log.d("GetFile", "onDownloadComplete: " + new SaveToDownloads(ExportFragment.this.getContext(), this.val$file).saveFileToDownloads("SSMSEXPORTS"));
                    Handler handler = new Handler();
                    final String str = this.val$fileName;
                    handler.postDelayed(new Runnable() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$rZ5ohQLQfyepDEkxaaJTv7mdDiY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onDownloadComplete$1$ExportFragment$1(str);
                        }
                    }, 1500L);
                    return;
                } catch (FileNotFoundException unused) {
                    ExportFragment.this.progressText.setVisibility(8);
                    ExportFragment.this.progressBar.setVisibility(8);
                    ExportFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$GMVrOKMb0bpDZRZcplCk0j5nWlk
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onDownloadComplete$2$ExportFragment$1();
                        }
                    });
                    return;
                } catch (Exception unused2) {
                    ExportFragment.this.progressText.setVisibility(8);
                    ExportFragment.this.progressBar.setVisibility(8);
                    ExportFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$EnTKAVdUann3IXKk-ZeO6iw1E2o
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onDownloadComplete$3$ExportFragment$1();
                        }
                    });
                    return;
                }
            }
            ExportFragment.this.progressBar.setVisibility(8);
            ExportFragment.this.progressText.setVisibility(8);
            ExportFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$qBGebKaXq_AnTR88fYIgvlWU4Ro
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadComplete$4$ExportFragment$1();
                }
            });
            ExportFragment.this.downloadBtn.setVisibility(8);
            ExportFragment.this.openBtn.setVisibility(0);
            Button button = ExportFragment.this.openBtn;
            final String str2 = this.val$fileName;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$Ej7qUhdDXzW4m3-GopuyABaV03Y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onDownloadComplete$5$ExportFragment$1(str2, view);
                }
            });
        }

        public /* synthetic */ void lambda$onDownloadComplete$1$ExportFragment$1(final String str) {
            ExportFragment.this.progressBar.setVisibility(8);
            ExportFragment.this.progressText.setVisibility(8);
            Toast.makeText(ExportFragment.this.getContext(), "Download Completed", 0).show();
            ExportFragment.this.downloadBtn.setVisibility(8);
            ExportFragment.this.openBtn.setVisibility(0);
            ExportFragment.this.openBtn.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.export.-$$Lambda$ExportFragment$1$so8CRc6mRvGrZGTb4q7FV9spvws
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onDownloadComplete$0$ExportFragment$1(str, view);
                }
            });
        }

        public /* synthetic */ void lambda$onDownloadComplete$0$ExportFragment$1(String str, View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                File lastModifiedFile = ExportFragment.this.getLastModifiedFile();
                if (lastModifiedFile != null) {
                    ExportFragment.this.openFile(lastModifiedFile);
                    return;
                } else {
                    Toast.makeText(ExportFragment.this.getContext(), "Can't open file", 0).show();
                    return;
                }
            }
            ExportFragment.this.openFile(new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "SSMSEXPORTS" + File.separator + str));
        }

        public /* synthetic */ void lambda$onDownloadComplete$2$ExportFragment$1() {
            Toast.makeText(ExportFragment.this.getContext(), "File was not found", 0).show();
        }

        public /* synthetic */ void lambda$onDownloadComplete$3$ExportFragment$1() {
            Toast.makeText(ExportFragment.this.getContext(), "Error while saving File", 0).show();
        }

        public /* synthetic */ void lambda$onDownloadComplete$4$ExportFragment$1() {
            Toast.makeText(ExportFragment.this.getContext(), "Download Completed", 0).show();
        }

        public /* synthetic */ void lambda$onDownloadComplete$5$ExportFragment$1(String str, View view) {
            ExportFragment.this.openFile(new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "SSMSEXPORTS" + File.separator, str));
        }

        @Override // com.androidnetworking.interfaces.DownloadListener
        public void onError(ANError anError) {
            if (anError.getErrorCode() == 401) {
                if (anError.getResponse() != null) {
                    DialogCustom.showError(ExportFragment.this.getContext(), anError.getResponse().message());
                } else {
                    DialogCustom.showError(ExportFragment.this.getContext(), anError.getMessage());
                }
            } else {
                DialogCustom.showError(ExportFragment.this.getContext(), "No Record Found from " + ((Object) ExportFragment.this.fromDate.getText()) + " to " + ((Object) ExportFragment.this.toDate.getText()) + " date.");
            }
            ExportFragment.this.progressText.setVisibility(8);
            ExportFragment.this.progressBar.setVisibility(8);
            ExportFragment.this.downloadBtn.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getLastModifiedFile() {
        try {
            File[] fileArrListFiles = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/SSMSEXPORTS").listFiles();
            if (fileArrListFiles == null && fileArrListFiles.length == 0) {
                return null;
            }
            File file = fileArrListFiles[0];
            for (int i = 1; i < fileArrListFiles.length; i++) {
                if (file.lastModified() < fileArrListFiles[i].lastModified()) {
                    file = fileArrListFiles[i];
                }
            }
            return file;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openFile(final File file) {
        Intent intent;
        if (isPackageInstalled("com.microsoft.office.excel")) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("ms-excel:ofv|u|" + file.toString()));
        } else {
            intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(MyFilesProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file), "application/vnd.ms-excel");
        }
        intent.setFlags(1);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            DialogCustom.showError(getContext(), "No Application(s) found to open excel file");
        } catch (Exception unused2) {
            DialogCustom.showError(getContext(), "Unable to open file");
        }
    }

    private boolean isPackageInstalled(String packageName) throws PackageManager.NameNotFoundException {
        try {
            getContext().getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
