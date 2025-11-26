package com.micromerger.ssms.printreport;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.micromerger.ssms.R;
import com.micromerger.ssms.printreport.beans.ReportResponse;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PrintReport extends Fragment implements DatePickerDialog.OnDateSetListener {
    public static final String DATEPICKER_TAG = "datepicker";
    Button btn_print;
    final Calendar calendar;
    final DatePickerDialog datePickerDialog;
    TextView date_from;
    TextView date_to;
    boolean isFromDate;
    View mView;
    String fromDate = "";
    String toDate = "";

    public PrintReport() {
        Calendar calendar = Calendar.getInstance();
        this.calendar = calendar;
        this.datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(1), calendar.get(2), calendar.get(5), false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_print_report, (ViewGroup) null);
        this.mView = viewInflate;
        this.date_from = (TextView) viewInflate.findViewById(R.id.date_from);
        this.date_to = (TextView) this.mView.findViewById(R.id.date_to);
        this.date_from.setKeyListener(null);
        this.date_to.setKeyListener(null);
        this.date_from.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.printreport.PrintReport.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrintReport.this.isFromDate = true;
                PrintReport.this.datePickerDialog.setYearRange(1990, 2025);
                PrintReport.this.datePickerDialog.setCloseOnSingleTapDay(false);
                PrintReport.this.datePickerDialog.show(PrintReport.this.getActivity().getSupportFragmentManager(), PrintReport.DATEPICKER_TAG);
            }
        });
        this.date_to.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.printreport.PrintReport.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrintReport.this.isFromDate = false;
                PrintReport.this.datePickerDialog.setYearRange(1990, 2025);
                PrintReport.this.datePickerDialog.setCloseOnSingleTapDay(false);
                PrintReport.this.datePickerDialog.show(PrintReport.this.getActivity().getSupportFragmentManager(), PrintReport.DATEPICKER_TAG);
            }
        });
        Button button = (Button) this.mView.findViewById(R.id.btn_print);
        this.btn_print = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.printreport.PrintReport.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                PrintReport.this.checkDates();
            }
        });
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkDates() throws JSONException {
        if (this.fromDate.length() > 0) {
            if (this.toDate.length() > 0) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (simpleDateFormat.parse(this.fromDate).compareTo(simpleDateFormat.parse(this.toDate)) <= 0) {
                        getReport();
                    } else {
                        DialogCustom.showError(getActivity(), "From date can not be greaterthan to date.");
                    }
                    return false;
                } catch (ParseException e) {
                    e.printStackTrace();
                    DialogCustom.showError(getActivity(), e.getMessage());
                    return false;
                }
            }
            DialogCustom.showError(getActivity(), "Please select to date.");
            return false;
        }
        DialogCustom.showError(getActivity(), "Please select from date.");
        return false;
    }

    private void getReport() throws JSONException {
        if (CommonActions.isConnected(getContext())) {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(getActivity());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userID", CommonObjects.userObj.userId);
                jSONObject.put("FromDate", this.fromDate);
                jSONObject.put("toDate", this.toDate);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Report/GetReport").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "Get Report").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(ReportResponse.class, new ParsedRequestListener<ReportResponse>() { // from class: com.micromerger.ssms.printreport.PrintReport.4
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(ReportResponse response) throws IOException {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        String data = response.getData();
                        File file = new File(Environment.getExternalStorageDirectory(), "SSMS/");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(file, Constant.Report + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ".xlsx");
                        try {
                            file2.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            fileOutputStream.write(Base64.decode(data, 2));
                            fileOutputStream.close();
                            CommonActions.openXLSX(PrintReport.this.getContext(), file2);
                            return;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            DialogCustom.showError(PrintReport.this.getActivity(), e2.getMessage() + "");
                            return;
                        }
                    }
                    DialogCustom.showError(PrintReport.this.getActivity(), response.getMessage());
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    DialogCustom.showError(PrintReport.this.getActivity(), anError.getErrorDetail());
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), "No Internet Connection");
    }

    @Override // com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        if (this.isFromDate) {
            String str = year + "-" + (month + 1) + "-" + day;
            this.fromDate = str;
            this.date_from.setText(str);
            return;
        }
        String str2 = year + "-" + (month + 1) + "-" + day;
        this.toDate = str2;
        this.date_to.setText(str2);
    }
}
