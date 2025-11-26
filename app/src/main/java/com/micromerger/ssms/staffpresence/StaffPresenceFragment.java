package com.micromerger.ssms.staffpresence;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.micromerger.ssms.R;
import com.micromerger.ssms.addemployee.AddEmployeeFragment;
import com.micromerger.ssms.addemployee.AddEmployeeOperations;
import com.micromerger.ssms.addemployee.EmployeeDbRecord;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.staffpresence.StaffPresenceAdapter;
import com.micromerger.ssms.staffpresence.StaffPresenceOperations;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class StaffPresenceFragment extends BaseFragment {
    private AddEmployeeOperations addEmployeeOperations;
    Button add_btn;
    private List<EmployeeDbRecord> employeeDbRecords;
    TextView no_data_text;
    RecyclerView recyclerview;
    private StaffPresenceAdapter staffPresenceAdapter;
    ArrayList<StaffPresenceModel> staffPresenceModels = new ArrayList<>();
    private StaffPresenceOperations staffPresenceOperations;
    Button sync_btn;
    Thread thread;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staff_presence, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.staffPresenceOperations = new StaffPresenceOperations(view.getContext());
        this.addEmployeeOperations = new AddEmployeeOperations(getActivityContext());
        this.recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        this.sync_btn = (Button) view.findViewById(R.id.sync_btn);
        this.add_btn = (Button) view.findViewById(R.id.add_btn);
        this.no_data_text = (TextView) view.findViewById(R.id.no_data_text);
        this.staffPresenceAdapter = new StaffPresenceAdapter(getActivity());
        this.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerview.setAdapter(this.staffPresenceAdapter);
        this.staffPresenceAdapter.setOnItemClickListener(new StaffPresenceAdapter.ItemClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$NKb6SpL4jRalHDOffbZcGX6enm8
            @Override // com.micromerger.ssms.staffpresence.StaffPresenceAdapter.ItemClickListener
            public final void onItemClick(int i, View view2, StaffPresenceModel staffPresenceModel) {
                this.f$0.lambda$onViewCreated$0$StaffPresenceFragment(i, view2, staffPresenceModel);
            }
        });
        this.staffPresenceAdapter.setOnDeleteClickListener(new StaffPresenceAdapter.DeleteClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$26iZEZjmtoqfs2ooJfVXo9XCBzg
            @Override // com.micromerger.ssms.staffpresence.StaffPresenceAdapter.DeleteClickListener
            public final void onDeleteClick(int i, View view2, StaffPresenceModel staffPresenceModel) {
                this.f$0.lambda$onViewCreated$2$StaffPresenceFragment(i, view2, staffPresenceModel);
            }
        });
        new FetchStaffRecordsTask().execute(new Void[0]);
        this.sync_btn.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$nYBWaFzbWBa5KmTaDYa-S9Im-H4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$4$StaffPresenceFragment(view2);
            }
        });
        this.add_btn.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$B9lnFx9bt3D1QG1hPZRCLbR2Ysw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$5$StaffPresenceFragment(view2);
            }
        });
    }

    public /* synthetic */ void lambda$onViewCreated$0$StaffPresenceFragment(int i, View view, StaffPresenceModel staffPresenceModel) {
        if (!staffPresenceModel.isSynced()) {
            this.ft = this.fm.beginTransaction();
            this.ft.replace(R.id.container, new AddEmployeeFragment(staffPresenceModel));
            this.ft.addToBackStack(null);
            this.ft.commit();
            return;
        }
        DialogCustom.showSuccessDialog(getActivityContext(), "Already synced", "The employee record is already synced");
    }

    public /* synthetic */ void lambda$onViewCreated$2$StaffPresenceFragment(int i, View view, final StaffPresenceModel staffPresenceModel) {
        new SweetAlertDialog(getActivityContext(), 3).setTitleText("Delete Record!").setContentText("Are you sure to delete this employee record?").showCancelButton(true).setCancelText("Cancel").setCancelClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$18zbsqLuyGI0EZ4Cz-_HOeH-q48
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog) {
                this.f$0.lambda$onViewCreated$1$StaffPresenceFragment(staffPresenceModel, sweetAlertDialog);
            }
        }).show();
    }

    public /* synthetic */ void lambda$onViewCreated$1$StaffPresenceFragment(StaffPresenceModel staffPresenceModel, SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismiss();
        deleteRecord(staffPresenceModel);
    }

    public /* synthetic */ void lambda$onViewCreated$4$StaffPresenceFragment(View view) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), 3);
        sweetAlertDialog.setTitleText(getString(R.string.app_name)).setContentText("Do you want to sync the employee records?").setConfirmText(getString(R.string.YES)).setCancelText(getString(R.string.NO)).showCancelButton(true).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$eqJYXAgfOfaJRjgs-WdRdOQXDZs
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public final void onClick(SweetAlertDialog sweetAlertDialog2) {
                this.f$0.lambda$onViewCreated$3$StaffPresenceFragment(sweetAlertDialog2);
            }
        }).setCancelClickListener($$Lambda$pzTll4R5UAHa5SIfN_X_XIH0p_A.INSTANCE);
        sweetAlertDialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$3$StaffPresenceFragment(SweetAlertDialog sweetAlertDialog) {
        sweetAlertDialog.dismiss();
        if (CommonActions.isConnected(getActivityContext())) {
            loadingStarted();
            this.staffPresenceOperations.postEmployeeRecords(new StaffPresenceOperations.EmployeeRecordsInterface() { // from class: com.micromerger.ssms.staffpresence.StaffPresenceFragment.1
                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void finishLoading() {
                    StaffPresenceFragment.this.loadingFinished();
                }

                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void successCallback(EmployeeRecordsResponse response) {
                    for (EmployeeDbRecord employeeDbRecord : StaffPresenceFragment.this.employeeDbRecords) {
                        employeeDbRecord.setSyncStatus(1);
                        employeeDbRecord.setAlreadySynced(1);
                    }
                    StaffPresenceFragment.this.staffPresenceOperations.updateEmployeeRecords(StaffPresenceFragment.this.employeeDbRecords);
                    StaffPresenceFragment staffPresenceFragment = StaffPresenceFragment.this;
                    staffPresenceFragment.renderList(staffPresenceFragment.employeeDbRecords);
                    StaffPresenceFragment.this.staffPresenceAdapter.notifyDataSetChanged();
                    DialogCustom.showSuccessDialog(StaffPresenceFragment.this.getActivityContext(), "Success", response.getMessage());
                }

                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void failureCallback(Response<EmployeeRecordsResponse> response) {
                    StaffPresenceFragment.this.loadingFinished();
                    DialogCustom.showError(StaffPresenceFragment.this.getActivityContext(), response.message());
                }

                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void onErrorCallback(String error) {
                    StaffPresenceFragment.this.loadingFinished();
                    DialogCustom.showError(StaffPresenceFragment.this.getActivityContext(), error);
                }

                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void exceptionCallback(Exception e) {
                    StaffPresenceFragment.this.loadingFinished();
                    DialogCustom.showError(StaffPresenceFragment.this.getActivityContext(), e.getMessage());
                }

                @Override // com.micromerger.ssms.staffpresence.StaffPresenceOperations.EmployeeRecordsInterface
                public void emptyListCallback() {
                    StaffPresenceFragment.this.loadingFinished();
                    DialogCustom.showError(StaffPresenceFragment.this.getActivityContext(), "No Employee Records to sync");
                }
            });
        } else {
            DialogCustom.showError(getActivityContext(), "No Internet Connection");
        }
    }

    public /* synthetic */ void lambda$onViewCreated$5$StaffPresenceFragment(View view) {
        List<School> schools = getSchools();
        if (schools != null && !schools.isEmpty()) {
            if (this.ft != null) {
                try {
                    this.ft = this.fm.beginTransaction();
                    this.ft.replace(R.id.container, new AddEmployeeFragment());
                    this.ft.addToBackStack(null);
                    this.ft.commit();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        Toast.makeText(getActivityContext(), "No schools available", 0).show();
    }

    private List<School> getSchools() {
        try {
            return this.preferenceHelper.getAllSchools().getData();
        } catch (Exception unused) {
            return null;
        }
    }

    private void deleteRecord(StaffPresenceModel staffPresenceModel) {
        EmployeeDbRecord employeeDbRecord = this.addEmployeeOperations.getEmployeeDbRecord(staffPresenceModel.getRecordId());
        if (employeeDbRecord != null) {
            deleteImages(employeeDbRecord);
        }
        this.staffPresenceOperations.deleteEmployeeRecord(Integer.valueOf(staffPresenceModel.getRecordId()));
        this.employeeDbRecords.clear();
        List<EmployeeDbRecord> employeeDbRecords = this.staffPresenceOperations.getEmployeeDbRecords();
        this.employeeDbRecords = employeeDbRecords;
        renderList(employeeDbRecords);
        getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$m3tMfmcllWyhjxP2B5QXNgWekFU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$deleteRecord$6$StaffPresenceFragment();
            }
        });
    }

    public /* synthetic */ void lambda$deleteRecord$6$StaffPresenceFragment() {
        this.staffPresenceAdapter.notifyDataSetChanged();
        DialogCustom.showSuccessDialog(getActivityContext(), "Success", "Record Deleted");
    }

    private void deleteImages(EmployeeDbRecord employeeDbRecord) {
        try {
            if (employeeDbRecord.getEmployeePicturePath() != null && !employeeDbRecord.getEmployeePicturePath().isEmpty()) {
                File file = new File(employeeDbRecord.getEmployeePicturePath());
                if (file.exists()) {
                    System.out.println(file.delete());
                }
            }
            if (employeeDbRecord.getCnicPicturePath() != null && !employeeDbRecord.getCnicPicturePath().isEmpty()) {
                File file2 = new File(employeeDbRecord.getCnicPicturePath());
                if (file2.exists()) {
                    System.out.println(file2.delete());
                }
            }
            if (employeeDbRecord.getGovtLetterPicturePath() != null && !employeeDbRecord.getGovtLetterPicturePath().isEmpty()) {
                File file3 = new File(employeeDbRecord.getGovtLetterPicturePath());
                if (file3.exists()) {
                    System.out.println(file3.delete());
                }
            }
            if (employeeDbRecord.getPostingOrderPicturePath() != null && !employeeDbRecord.getPostingOrderPicturePath().isEmpty()) {
                File file4 = new File(employeeDbRecord.getPostingOrderPicturePath());
                if (file4.exists()) {
                    System.out.println(file4.delete());
                }
            }
            if (employeeDbRecord.getReasonOrderPicturePath() != null && !employeeDbRecord.getReasonOrderPicturePath().isEmpty()) {
                File file5 = new File(employeeDbRecord.getReasonOrderPicturePath());
                if (file5.exists()) {
                    System.out.println(file5.delete());
                }
            }
            if (employeeDbRecord.getOtherPicturePath() != null && !employeeDbRecord.getOtherPicturePath().isEmpty()) {
                File file6 = new File(employeeDbRecord.getOtherPicturePath());
                if (file6.exists()) {
                    System.out.println(file6.delete());
                }
            }
            if (employeeDbRecord.getEmployeePayslipPicturePath() != null && !employeeDbRecord.getEmployeePayslipPicturePath().isEmpty()) {
                File file7 = new File(employeeDbRecord.getEmployeePayslipPicturePath());
                if (file7.exists()) {
                    System.out.println(file7.delete());
                }
            }
            if (employeeDbRecord.getOfferOrderPicturePath() != null && !employeeDbRecord.getOfferOrderPicturePath().isEmpty()) {
                File file8 = new File(employeeDbRecord.getOfferOrderPicturePath());
                if (file8.exists()) {
                    System.out.println(file8.delete());
                }
            }
            if (employeeDbRecord.getJoiningReportPicturePath() != null && !employeeDbRecord.getJoiningReportPicturePath().isEmpty()) {
                File file9 = new File(employeeDbRecord.getJoiningReportPicturePath());
                if (file9.exists()) {
                    System.out.println(file9.delete());
                }
            }
            if (employeeDbRecord.getAttendanceRegisterPicturePath() != null && !employeeDbRecord.getAttendanceRegisterPicturePath().isEmpty()) {
                File file10 = new File(employeeDbRecord.getAttendanceRegisterPicturePath());
                if (file10.exists()) {
                    System.out.println(file10.delete());
                }
            }
            if (employeeDbRecord.getInformationLetterPicturePath() == null || employeeDbRecord.getInformationLetterPicturePath().isEmpty()) {
                return;
            }
            File file11 = new File(employeeDbRecord.getInformationLetterPicturePath());
            if (file11.exists()) {
                System.out.println(file11.delete());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderList(final List<EmployeeDbRecord> employeeDbRecords) {
        this.employeeDbRecords = employeeDbRecords;
        if (employeeDbRecords != null && !employeeDbRecords.isEmpty()) {
            this.recyclerview.setVisibility(0);
            this.no_data_text.setVisibility(8);
            this.sync_btn.setEnabled(true);
            this.sync_btn.setAlpha(1.0f);
            Thread thread = new Thread(new Runnable() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$v2qY3gZVFXgJ7lTNia34kfhh-dc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$renderList$7$StaffPresenceFragment(employeeDbRecords);
                }
            });
            this.thread = thread;
            thread.start();
            return;
        }
        this.recyclerview.setVisibility(8);
        this.no_data_text.setVisibility(0);
        this.sync_btn.setEnabled(false);
        this.sync_btn.setAlpha(0.5f);
    }

    public /* synthetic */ void lambda$renderList$7$StaffPresenceFragment(List list) {
        this.staffPresenceModels.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            EmployeeDbRecord employeeDbRecord = (EmployeeDbRecord) it.next();
            this.staffPresenceModels.add(new StaffPresenceModel(employeeDbRecord.getRecordId(), employeeDbRecord.getName(), employeeDbRecord.getCnic(), employeeDbRecord.getAddEmployeeRecord().getEmployeeCode(), employeeDbRecord.getReason(), Long.parseLong(employeeDbRecord.getDateTime()), employeeDbRecord.getSyncStatus() == 1));
        }
        this.staffPresenceAdapter.updateList(this.staffPresenceModels);
        this.staffPresenceAdapter.sortList();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        SSMS.setCurrentFragment(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Thread thread = this.thread;
        if (thread != null) {
            thread.interrupt();
        }
        SSMS.setCurrentFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class FetchStaffRecordsTask extends AsyncTask<Void, Void, List<EmployeeDbRecord>> {
        private List<EmployeeDbRecord> employeeDbRecords;

        private FetchStaffRecordsTask() {
            this.employeeDbRecords = null;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            StaffPresenceFragment.this.loadingStarted();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<EmployeeDbRecord> doInBackground(Void... voids) {
            try {
                this.employeeDbRecords = StaffPresenceFragment.this.staffPresenceOperations.getEmployeeDbRecords();
            } catch (Exception e) {
                util.logException(e);
            }
            return this.employeeDbRecords;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final List<EmployeeDbRecord> list) {
            super.onPostExecute((FetchStaffRecordsTask) list);
            try {
                StaffPresenceFragment.this.getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceFragment$FetchStaffRecordsTask$qr3io0YQMgjCrYKuh33wJ3RasNg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onPostExecute$0$StaffPresenceFragment$FetchStaffRecordsTask(list);
                    }
                });
            } catch (Exception e) {
                util.logException(e);
            }
        }

        public /* synthetic */ void lambda$onPostExecute$0$StaffPresenceFragment$FetchStaffRecordsTask(List list) {
            StaffPresenceFragment.this.renderList(list);
            StaffPresenceFragment.this.loadingFinished();
        }
    }
}
