package com.micromerger.ssms.searchemployee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.micromerger.ssms.R;
import com.micromerger.ssms.searchemployee.beans.SearchEmployeeResponse;
import com.micromerger.ssms.startmonitoring.SimpleMatchActivity;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SearchEmployee extends BaseFragment {
    Button btn_search;
    EditText edit_id;
    EditText edit_name;
    EditText edit_nic;
    RelativeLayout finger;
    View mView;

    enum SearchType {
        CNIC,
        NAME,
        ID,
        THUMB
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_search_employee, (ViewGroup) null);
        loadingFinished();
        this.edit_nic = (EditText) this.mView.findViewById(R.id.edit_nic);
        this.edit_name = (EditText) this.mView.findViewById(R.id.edit_name);
        this.edit_id = (EditText) this.mView.findViewById(R.id.edit_id);
        Button button = (Button) this.mView.findViewById(R.id.btn_search);
        this.btn_search = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.searchemployee.SearchEmployee.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                CommonActions.hideSoftKeyboard(SearchEmployee.this.getActivity(), SearchEmployee.this.getActivity().getCurrentFocus());
                if (CommonObjects.userObj.role == CommonObjects.Roles.MA) {
                    SearchEmployee.this.searchEmployeeByData();
                } else {
                    SearchEmployee.this.searchEmployeeFromServer();
                }
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.finger);
        this.finger = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.searchemployee.SearchEmployee.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchEmployee.this.startActivity(new Intent(SearchEmployee.this.getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.SEARCH_EMPLOYEE, true));
            }
        });
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchEmployeeFromServer() throws JSONException {
        if (this.edit_nic.getText().toString().length() > 0 || this.edit_name.getText().toString().length() > 0 || this.edit_id.getText().toString().length() > 0) {
            if (CommonActions.isConnected(getContext())) {
                final ProgressDialog progressDialogCreateProgressDialog = CommonActions.createProgressDialog(getActivity());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("CNIC", this.edit_nic.getText().toString());
                    jSONObject.put("Name", this.edit_name.getText().toString());
                    jSONObject.put("Employee_Code", this.edit_id.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AndroidNetworking.post("https://mne.seld.gos.pk/Services/api/Employee/SearchEmployee").setOkHttpClient(CommonActions.getOkHttpClient()).setTag((Object) "SearchEmployee").addHeaders("X-ApiKey", "0L0zgzUYB2S4nSN8f3A0W5YFITpMSKQx").setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE).addJSONObjectBody(jSONObject).setPriority(Priority.MEDIUM).build().getAsObject(SearchEmployeeResponse.class, new ParsedRequestListener<SearchEmployeeResponse>() { // from class: com.micromerger.ssms.searchemployee.SearchEmployee.3
                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                    public void onResponse(SearchEmployeeResponse response) {
                        if (progressDialogCreateProgressDialog.isShowing()) {
                            progressDialogCreateProgressDialog.dismiss();
                        }
                        if (response.getStatus().booleanValue()) {
                            if (response.getData() != null && response.getData().size() > 0) {
                                FragmentTransaction fragmentTransactionBeginTransaction = SearchEmployee.this.getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransactionBeginTransaction.replace(R.id.container, new SearchedEmployeeList(response.getData()));
                                fragmentTransactionBeginTransaction.addToBackStack(null);
                                fragmentTransactionBeginTransaction.commit();
                                return;
                            }
                            DialogCustom.showError(SearchEmployee.this.getActivity(), "No employee exist.");
                            return;
                        }
                        DialogCustom.showError(SearchEmployee.this.getActivity(), response.getMessage());
                    }

                    @Override // com.androidnetworking.interfaces.ParsedRequestListener
                    public void onError(ANError anError) {
                        if (progressDialogCreateProgressDialog.isShowing()) {
                            progressDialogCreateProgressDialog.dismiss();
                        }
                        DialogCustom.showError(SearchEmployee.this.getActivity(), anError.getErrorDetail());
                    }
                });
                return;
            }
            DialogCustom.showError(getActivity(), "No Internet Connection");
            return;
        }
        DialogCustom.showError(getActivity(), "Atleast select one field.");
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (CommonObjects.schoolThumbCheck) {
            CommonObjects.schoolThumbCheck = false;
            if (CommonObjects.searchedEmployeeId.equals("")) {
                DialogCustom.showError(getActivity(), "No employee exist.");
            } else {
                Log.e("id", CommonObjects.searchedEmployeeId);
                searchEmployee(CommonObjects.searchedEmployeeId, SearchType.THUMB);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchEmployeeByData() {
        if (this.edit_nic.getText().toString().length() > 0 || this.edit_name.getText().toString().length() > 0 || this.edit_id.getText().toString().length() > 0) {
            if (this.edit_nic.getText().toString().length() > 0) {
                searchEmployee(this.edit_nic.getText().toString(), SearchType.CNIC);
                return;
            } else if (this.edit_id.getText().toString().length() > 0) {
                searchEmployee(this.edit_id.getText().toString(), SearchType.ID);
                return;
            } else {
                if (this.edit_name.getText().toString().length() > 0) {
                    searchEmployee(this.edit_name.getText().toString(), SearchType.NAME);
                    return;
                }
                return;
            }
        }
        DialogCustom.showError(getActivity(), "Atleast select one field.");
    }

    private void searchEmployee(final String s, final SearchType type) {
        List arrayList;
        try {
            ArrayList arrayList2 = new ArrayList();
            Log.e("s", s);
            if (CommonObjects.employeeData != null) {
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    EmployeeData employeeData = CommonObjects.employeeData.get(i);
                    new ArrayList();
                    Collection collectionSelect = CollectionUtils.select(employeeData.getEmployees(), new Predicate() { // from class: com.micromerger.ssms.searchemployee.SearchEmployee.4
                        @Override // org.apache.commons.collections4.Predicate
                        public boolean evaluate(Object o) {
                            Employee employee = (Employee) o;
                            if (type == SearchType.CNIC) {
                                return employee.getCnic().toLowerCase().contains(s.toLowerCase());
                            }
                            if (type == SearchType.ID) {
                                Log.d("employeeString", "evaluate: " + employee.toString());
                                return String.valueOf(employee.getEmployeeCode()).toLowerCase().contains(s.toLowerCase());
                            }
                            if (type == SearchType.THUMB) {
                                Log.e("s", s);
                                Toast.makeText(SearchEmployee.this.getContext(), "searched " + s, 0).show();
                                if (!String.valueOf(employee.getEmployeeId()).toLowerCase().equals(s.toLowerCase())) {
                                    return false;
                                }
                                Toast.makeText(SearchEmployee.this.getContext(), "searched " + s, 0).show();
                                return true;
                            }
                            return employee.getEmployeeName().toLowerCase().contains(s.toLowerCase());
                        }
                    });
                    if (collectionSelect instanceof List) {
                        arrayList = (List) collectionSelect;
                    } else {
                        arrayList = new ArrayList(collectionSelect);
                    }
                    if (arrayList.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            arrayList2.add((Employee) it.next());
                        }
                    }
                }
            }
            if (arrayList2.size() > 0) {
                FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.replace(R.id.container, new SearchedEmployeeList(arrayList2));
                fragmentTransactionBeginTransaction.addToBackStack(null);
                fragmentTransactionBeginTransaction.commit();
                return;
            }
            DialogCustom.showError(getActivity(), "No employee exist.");
        } catch (Exception e) {
            e.printStackTrace();
            util.logException(e);
        }
    }
}
