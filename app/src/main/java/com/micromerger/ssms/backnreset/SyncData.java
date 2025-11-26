package com.micromerger.ssms.backnreset;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.micromerger.ssms.R;
import com.micromerger.ssms.backnreset.SyncAdapter;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.monitoring.MonitoringDataOperations;
import com.micromerger.ssms.startmonitoring.bean.SubmitEndMonitoringMultipartResponse;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONException;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class SyncData extends BaseFragment {
    private static String LOG_TAG = "StartMonitoringFragment";
    private SyncAdapter adapter;
    ImageView iv_refresh;
    LinearLayoutManager mLayoutManager;
    View mView;
    Map.Entry<Integer, EmployeeData> moniteredEntry;
    RecyclerView recyclerView;
    ProgressBar sync_progress;
    TextView sync_text;
    private List<EmployeeData> mDataset = new ArrayList();
    double totalSchool = 0.0d;
    double syncSchool = 0.0d;
    double progress = 0.0d;
    HashedMap<Integer, EmployeeData> monitoredData = new HashedMap<>();
    private Handler handle = new Handler() { // from class: com.micromerger.ssms.backnreset.SyncData.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                SyncData.this.monitoredData.remove(SyncData.this.moniteredEntry.getKey());
                SyncData.this.postMonitoringData();
            } else {
                int i = msg.what;
            }
        }
    };

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_sync, (ViewGroup) null);
        this.mView = viewInflate;
        this.recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerview);
        this.adapter = new SyncAdapter(CommonObjects.employeeData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new AnonymousClass2());
        this.sync_progress = (ProgressBar) this.mView.findViewById(R.id.sync_progress);
        this.sync_text = (TextView) this.mView.findViewById(R.id.sync_text);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.iv_refresh);
        this.iv_refresh = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.backnreset.SyncData.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonActions.isConnected(SyncData.this.getContext())) {
                    SyncData.this.loadingStarted();
                    for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                        EmployeeData employeeData = CommonObjects.employeeData.get(i);
                        if (!employeeData.isSync() && employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                            SyncData.this.monitoredData.put(Integer.valueOf(i), employeeData);
                        }
                    }
                    SyncData.this.loadingFinished();
                    SyncData.this.postMonitoringData();
                } else {
                    DialogCustom.showError(SyncData.this.getActivityContext(), "No internet connection.");
                }
                SyncData.this.setProgressStatus();
            }
        });
        setProgressStatus();
        return this.mView;
    }

    /* renamed from: com.micromerger.ssms.backnreset.SyncData$2, reason: invalid class name */
    class AnonymousClass2 implements SyncAdapter.MyClickListener {
        AnonymousClass2() {
        }

        @Override // com.micromerger.ssms.backnreset.SyncAdapter.MyClickListener
        public void onItemClick(final int position, View v, final EmployeeData employeeData) {
            CommonObjects.monitoring = CommonObjects.employeeData.get(position);
            SyncData.this.preferenceHelper.setCurrentMonitoringId(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue());
            if (CommonObjects.monitoring.isSync()) {
                SyncData.this.loadingFinished();
                DialogCustom.showSuccessDialog(SyncData.this.getActivityContext(), "Done", "Monitoring details already has been uploaded.");
            } else if (!CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                SyncData.this.loadingFinished();
                DialogCustom.showError(SyncData.this.getActivityContext(), "Please fill and save the all forms.");
            } else {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SyncData.this.getActivity(), 0);
                sweetAlertDialog.setTitleText(SyncData.this.getString(R.string.app_name)).setContentText("Do you want to upload monitoring details?").setConfirmText(SyncData.this.getString(R.string.YES)).setCancelText(SyncData.this.getString(R.string.NO)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.SyncData.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                        sweetAlertDialog2.dismiss();
                        SyncData.this.loadingStarted();
                        new Thread(new Runnable() { // from class: com.micromerger.ssms.backnreset.SyncData.2.2.1
                            @Override // java.lang.Runnable
                            public void run() throws JSONException {
                                SyncData.this.postMonitoringData(employeeData, position);
                            }
                        }).start();
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.backnreset.SyncData.2.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                    }
                });
                sweetAlertDialog.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressStatus() {
        this.totalSchool = 0.0d;
        this.syncSchool = 0.0d;
        this.progress = 0.0d;
        if (CommonObjects.employeeData != null) {
            double size = CommonObjects.employeeData.size();
            this.totalSchool = size;
            Log.e("totalSchool", String.valueOf(size));
            Iterator<EmployeeData> it = CommonObjects.employeeData.iterator();
            while (it.hasNext()) {
                if (it.next().isSync()) {
                    double d = this.syncSchool + 1.0d;
                    this.syncSchool = d;
                    Log.e("sync school", String.valueOf(d));
                }
            }
            double d2 = (this.syncSchool / this.totalSchool) * 100.0d;
            this.progress = d2;
            Log.e("progress", String.valueOf(d2));
            this.sync_progress.setProgress((int) this.progress);
        }
        this.sync_text.setText(((int) this.progress) + "% from 100%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postMonitoringData(final EmployeeData employeeData, final int pos) throws JSONException {
        if (CommonActions.isConnected(getContext())) {
            new MonitoringDataOperations().postMonitoringData(employeeData, new AnonymousClass4(pos), true);
        } else {
            loadingFinished();
            DialogCustom.showError(getActivityContext(), "No internet connection.");
        }
    }

    /* renamed from: com.micromerger.ssms.backnreset.SyncData$4, reason: invalid class name */
    class AnonymousClass4 implements MonitoringDataOperations.SubmitEndMonitoring {
        final /* synthetic */ int val$pos;

        AnonymousClass4(final int val$pos) {
            this.val$pos = val$pos;
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void finishLoading() {
            SyncData.this.loadingFinished();
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void successCallback(SubmitEndMonitoringMultipartResponse response) {
            DialogCustom.showSuccessDialog(SyncData.this.getActivityContext(), "Done", response.getMessage());
            CommonObjects.monitoring.setSync(true);
            CommonObjects.employeeData.set(this.val$pos, CommonObjects.monitoring);
            SyncData.this.adapter.notifyDataSetChanged();
            CommonActions.getDbHandler(SyncData.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(this.val$pos).getDbId().intValue(), CommonObjects.monitoring);
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void failureCallback(Response<SubmitEndMonitoringMultipartResponse> response) {
            if (response.code() == 401) {
                if (response.message().equals(SyncData.this.getString(R.string.server_authorization_denied_message))) {
                    DialogCustom.showError(SyncData.this.getActivity(), SyncData.this.getString(R.string.authorization_session_expired_message));
                    return;
                }
                return;
            }
            DialogCustom.showError(SyncData.this.getActivity(), response.message());
        }

        public /* synthetic */ void lambda$progressStatus$0$SyncData$4() {
            SyncData.this.setProgressStatus();
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void progressStatus() {
            SyncData.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.-$$Lambda$SyncData$4$A07SXxwlU-wJESggp8Y-3uxDvcI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$progressStatus$0$SyncData$4();
                }
            });
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void onErrorCallback(String error) {
            SyncData.this.loadingFinished();
            DialogCustom.showError(SyncData.this.getActivityContext(), error);
        }

        @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
        public void exceptionCallback(Exception e) {
            SyncData.this.loadingFinished();
            DialogCustom.showError(SyncData.this.getActivityContext(), e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postMonitoringData() {
        if (this.monitoredData.size() > 0) {
            loadingStarted();
        }
        if (this.monitoredData.size() > 0) {
            new Thread(new Runnable() { // from class: com.micromerger.ssms.backnreset.SyncData.5
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    MonitoringDataOperations monitoringDataOperations = new MonitoringDataOperations();
                    HashedMap hashedMap = new HashedMap();
                    hashedMap.putAll(SyncData.this.monitoredData);
                    Iterator it = hashedMap.entrySet().iterator();
                    while (it.hasNext()) {
                        SyncData.this.moniteredEntry = (Map.Entry) it.next();
                        EmployeeData value = SyncData.this.moniteredEntry.getValue();
                        monitoringDataOperations.postMonitoringData(value, new AnonymousClass1(value), false);
                    }
                }

                /* renamed from: com.micromerger.ssms.backnreset.SyncData$5$1, reason: invalid class name */
                class AnonymousClass1 implements MonitoringDataOperations.SubmitEndMonitoring {
                    final /* synthetic */ EmployeeData val$employeeData;

                    AnonymousClass1(final EmployeeData val$employeeData) {
                        this.val$employeeData = val$employeeData;
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void finishLoading() {
                        SyncData.this.loadingFinished();
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void successCallback(SubmitEndMonitoringMultipartResponse response) {
                        this.val$employeeData.setSync(true);
                        if (SyncData.this.moniteredEntry.getKey() == null || this.val$employeeData == null) {
                            return;
                        }
                        CommonObjects.employeeData.set(SyncData.this.moniteredEntry.getKey().intValue(), this.val$employeeData);
                        SyncData.this.adapter.notifyDataSetChanged();
                        CommonActions.getDbHandler(SyncData.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(SyncData.this.moniteredEntry.getKey().intValue()).getDbId().intValue(), this.val$employeeData);
                        SyncData.this.loadingFinished();
                        Message message = new Message();
                        message.what = 1;
                        SyncData.this.handle.sendMessage(message);
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void failureCallback(Response<SubmitEndMonitoringMultipartResponse> response) {
                        SyncData.this.loadingFinished();
                        if (response.code() != 401) {
                            DialogCustom.showError(SyncData.this.getActivityContext(), response.message());
                        } else if (response.message().equals(SyncData.this.getString(R.string.server_authorization_denied_message))) {
                            DialogCustom.showError(SyncData.this.getActivity(), SyncData.this.getString(R.string.authorization_session_expired_message));
                        }
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void progressStatus() {
                        if (SyncData.this.getActivity() == null) {
                            return;
                        }
                        FragmentActivity activity = SyncData.this.getActivity();
                        final SyncData syncData = SyncData.this;
                        activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.-$$Lambda$SyncData$5$1$u2Y0f8GwiRwVop5p1Skyno70Uv4
                            @Override // java.lang.Runnable
                            public final void run() {
                                syncData.setProgressStatus();
                            }
                        });
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void onErrorCallback(final String error) {
                        SyncData.this.loadingFinished();
                        try {
                            DialogCustom.showError(SyncData.this.getActivityContext(), error);
                        } catch (WindowManager.BadTokenException unused) {
                            if (SyncData.this.getActivity() != null) {
                                SyncData.this.getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.backnreset.-$$Lambda$SyncData$5$1$P5RnBC1qYlFexyVE1k-auEtFy4I
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.lambda$onErrorCallback$1$SyncData$5$1(error);
                                    }
                                });
                            }
                        }
                    }

                    public /* synthetic */ void lambda$onErrorCallback$1$SyncData$5$1(String str) {
                        Toast.makeText(SyncData.this.getActivityContext(), str, 0).show();
                    }

                    @Override // com.micromerger.ssms.monitoring.MonitoringDataOperations.SubmitEndMonitoring
                    public void exceptionCallback(Exception e) {
                        SyncData.this.loadingFinished();
                        DialogCustom.showError(SyncData.this.getActivityContext(), e.getMessage());
                        e.printStackTrace();
                    }
                }
            }).start();
        }
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
}
