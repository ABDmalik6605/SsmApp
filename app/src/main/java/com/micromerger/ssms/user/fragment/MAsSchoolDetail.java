package com.micromerger.ssms.user.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.user.adapters.MAsSchoolDetailAdapter;
import com.micromerger.ssms.user.beans.dashboard.Data;
import com.micromerger.ssms.user.beans.maSchoolDetail.MAsSchoolDetailResponse;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.DateValidator;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MAsSchoolDetail extends Fragment {
    MAsSchoolDetailAdapter adapter;
    Data employee;
    ClearableEditText et_search_bar;
    LinearLayoutManager mLayoutManager;
    View mView;
    RecyclerView rv_dashboard;
    List<com.micromerger.ssms.user.beans.maSchoolDetail.Data> schools = new ArrayList();
    String title = "";
    TextView tv_no_internet;

    public MAsSchoolDetail(Data employee) {
        this.employee = employee;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_dashboard, (ViewGroup) null);
        this.mView = viewInflate;
        this.tv_no_internet = (TextView) viewInflate.findViewById(R.id.tv_no_internet);
        this.rv_dashboard = (RecyclerView) this.mView.findViewById(R.id.rv_dashboard);
        ClearableEditText clearableEditText = (ClearableEditText) this.mView.findViewById(R.id.et_search_bar);
        this.et_search_bar = clearableEditText;
        clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.user.fragment.MAsSchoolDetail.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                MAsSchoolDetail.this.filter(s.toString());
            }
        });
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws JSONException {
        super.onViewCreated(view, savedInstanceState);
        if (CommonActions.isConnected(getActivity())) {
            final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(getActivity());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userID", this.employee.getUsers().getUserID());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Employee/GetSchoolsAssignedToMA").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "GetSchoolsAssignedToMA").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(MAsSchoolDetailResponse.class, new ParsedRequestListener<MAsSchoolDetailResponse>() { // from class: com.micromerger.ssms.user.fragment.MAsSchoolDetail.2
                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onResponse(MAsSchoolDetailResponse response) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    if (response.getStatus().booleanValue()) {
                        MAsSchoolDetail.this.schools = response.getData();
                        if (MAsSchoolDetail.this.schools != null) {
                            Collections.sort(MAsSchoolDetail.this.schools, new Comparator<com.micromerger.ssms.user.beans.maSchoolDetail.Data>() { // from class: com.micromerger.ssms.user.fragment.MAsSchoolDetail.2.1
                                @Override // java.util.Comparator
                                public int compare(com.micromerger.ssms.user.beans.maSchoolDetail.Data o1, com.micromerger.ssms.user.beans.maSchoolDetail.Data o2) {
                                    long transactionTime = DateValidator.getInstance().getTransactionTime(o2.getMonitoring().get(0).getMonitoringStartDate());
                                    long transactionTime2 = DateValidator.getInstance().getTransactionTime(o1.getMonitoring().get(0).getMonitoringStartDate());
                                    if (transactionTime < transactionTime2) {
                                        return -1;
                                    }
                                    return transactionTime == transactionTime2 ? 0 : 1;
                                }
                            });
                        }
                        MAsSchoolDetail mAsSchoolDetail = MAsSchoolDetail.this;
                        mAsSchoolDetail.adapter = new MAsSchoolDetailAdapter(mAsSchoolDetail.schools);
                        MAsSchoolDetail mAsSchoolDetail2 = MAsSchoolDetail.this;
                        mAsSchoolDetail2.mLayoutManager = new LinearLayoutManager(mAsSchoolDetail2.getActivity());
                        MAsSchoolDetail.this.rv_dashboard.setLayoutManager(MAsSchoolDetail.this.mLayoutManager);
                        MAsSchoolDetail.this.rv_dashboard.setAdapter(MAsSchoolDetail.this.adapter);
                        return;
                    }
                    DialogCustom.showError(MAsSchoolDetail.this.getActivity(), response.getMessage());
                }

                @Override // com.androidnetworking.interfaces.ParsedRequestListener
                public void onError(ANError anError) {
                    if (progressDialogCreateProgressDialog.isShowing()) {
                        progressDialogCreateProgressDialog.dismiss();
                    }
                    DialogCustom.showError(MAsSchoolDetail.this.getActivity(), anError.getErrorDetail());
                    MAsSchoolDetail.this.rv_dashboard.setVisibility(8);
                    MAsSchoolDetail.this.tv_no_internet.setVisibility(0);
                    MAsSchoolDetail.this.tv_no_internet.setText("No schools found.");
                }
            });
            return;
        }
        DialogCustom.showError(getActivity(), getString(R.string.error_internet));
        this.rv_dashboard.setVisibility(8);
        this.tv_no_internet.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTvTitle(getString(R.string.school_status));
    }

    void filter(String text) {
        if (text.equals("")) {
            this.adapter.updateList(this.schools);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (com.micromerger.ssms.user.beans.maSchoolDetail.Data data : this.schools) {
            if (data.getSchoolName().toLowerCase().contains(text.toLowerCase())) {
                arrayList.add(data);
            }
        }
        this.adapter.updateList(arrayList);
    }
}
