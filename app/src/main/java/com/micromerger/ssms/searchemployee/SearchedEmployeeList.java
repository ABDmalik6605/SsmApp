package com.micromerger.ssms.searchemployee;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.searchemployee.SearchedEmployeeAdapter;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchedEmployeeList extends Fragment {
    SearchedEmployeeAdapter adapter;
    ClearableEditText et_search_bar;
    LinearLayoutManager mLayoutManager;
    View mView;
    RecyclerView rv_dashboard;
    List<Employee> searchedEmployees;

    public SearchedEmployeeList() {
        this.searchedEmployees = new ArrayList();
    }

    public SearchedEmployeeList(List<Employee> searchedEmployees) {
        this.searchedEmployees = new ArrayList();
        this.searchedEmployees = searchedEmployees;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_dashboard, (ViewGroup) null);
        this.mView = viewInflate;
        this.rv_dashboard = (RecyclerView) viewInflate.findViewById(R.id.rv_dashboard);
        this.adapter = new SearchedEmployeeAdapter(this.searchedEmployees);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.rv_dashboard.setLayoutManager(linearLayoutManager);
        this.rv_dashboard.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new SearchedEmployeeAdapter.MyClickListener() { // from class: com.micromerger.ssms.searchemployee.SearchedEmployeeList.1
            @Override // com.micromerger.ssms.searchemployee.SearchedEmployeeAdapter.MyClickListener
            public void onItemClick(int position, View v) {
                SearchedEmployeeList searchedEmployeeList = SearchedEmployeeList.this;
                searchedEmployeeList.goToEmployeeDetail(new SearchEmployeeDetail(searchedEmployeeList.searchedEmployees.get(position)));
            }
        });
        ClearableEditText clearableEditText = (ClearableEditText) this.mView.findViewById(R.id.et_search_bar);
        this.et_search_bar = clearableEditText;
        clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.searchemployee.SearchedEmployeeList.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                SearchedEmployeeList.this.filter(s.toString());
            }
        });
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToEmployeeDetail(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
    }

    void filter(String text) {
        if (text.equals("")) {
            this.adapter.updateList(this.searchedEmployees);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Employee employee : this.searchedEmployees) {
            if (employee.getEmployeeName().toLowerCase().contains(text.toLowerCase())) {
                arrayList.add(employee);
            }
        }
        this.adapter.updateList(arrayList);
    }
}
