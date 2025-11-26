package com.micromerger.ssms.user.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.user.adapters.DashboardAdapter;
import com.micromerger.ssms.user.beans.dashboard.Data;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.widgets.ClearableEditText;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DashBoard extends Fragment {
    DashboardAdapter adapter;
    List<Data> employees;
    ClearableEditText et_search_bar;
    LinearLayoutManager mLayoutManager;
    View mView;
    boolean noEmployee;
    RecyclerView rv_dashboard;
    String title;
    TextView tv_no_internet;

    public DashBoard() {
        this.employees = new ArrayList();
        this.title = "";
        this.noEmployee = false;
        this.noEmployee = true;
    }

    public DashBoard(List<Data> data) {
        this.employees = new ArrayList();
        this.title = "";
        this.noEmployee = false;
        this.employees = data;
        this.title = data.get(0).getRoles().getDescription();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_dashboard, (ViewGroup) null);
        this.mView = viewInflate;
        this.tv_no_internet = (TextView) viewInflate.findViewById(R.id.tv_no_internet);
        RecyclerView recyclerView = (RecyclerView) this.mView.findViewById(R.id.rv_dashboard);
        this.rv_dashboard = recyclerView;
        if (this.noEmployee) {
            recyclerView.setVisibility(8);
            this.tv_no_internet.setVisibility(0);
            this.tv_no_internet.setText("No subordinate(s) found.");
        } else {
            this.adapter = new DashboardAdapter(this.employees);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            this.mLayoutManager = linearLayoutManager;
            this.rv_dashboard.setLayoutManager(linearLayoutManager);
            this.rv_dashboard.setAdapter(this.adapter);
            ClearableEditText clearableEditText = (ClearableEditText) this.mView.findViewById(R.id.et_search_bar);
            this.et_search_bar = clearableEditText;
            clearableEditText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.user.fragment.DashBoard.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    DashBoard.this.filter(s.toString());
                }
            });
            this.adapter.setOnItemClickListener(new DashboardAdapter.MyClickListener() { // from class: com.micromerger.ssms.user.fragment.DashBoard.2
                @Override // com.micromerger.ssms.user.adapters.DashboardAdapter.MyClickListener
                public void onItemClick(int position, View v) {
                    DashBoard dashBoard;
                    boolean z = false;
                    if (DashBoard.this.title.equals(CommonObjects.Roles.DG.name())) {
                        if (CommonObjects.DDOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.DDOList);
                        } else if (CommonObjects.EDOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.EDOList);
                        } else if (CommonObjects.CMOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.CMOList);
                        } else if (CommonObjects.MAList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.MAList);
                        } else {
                            dashBoard = new DashBoard();
                        }
                    } else if (DashBoard.this.title.equals(CommonObjects.Roles.DDO.name())) {
                        if (CommonObjects.EDOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.EDOList);
                        } else if (CommonObjects.CMOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.CMOList);
                        } else if (CommonObjects.MAList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.MAList);
                        } else {
                            dashBoard = new DashBoard();
                        }
                    } else if (DashBoard.this.title.equals(CommonObjects.Roles.EDO.name())) {
                        if (CommonObjects.CMOList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.CMOList);
                        } else if (CommonObjects.MAList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.MAList);
                        } else {
                            dashBoard = new DashBoard();
                        }
                    } else if (DashBoard.this.title.equals(CommonObjects.Roles.CMO.name())) {
                        if (CommonObjects.MAList.size() > 0) {
                            dashBoard = new DashBoard(CommonObjects.MAList);
                        } else {
                            dashBoard = new DashBoard();
                        }
                    } else if (DashBoard.this.title.equals(CommonObjects.Roles.MA.name())) {
                        z = true;
                        dashBoard = null;
                    } else {
                        dashBoard = new DashBoard();
                    }
                    if (!z) {
                        DashBoard.this.setNewDashBoard(dashBoard);
                    } else {
                        DashBoard dashBoard2 = DashBoard.this;
                        dashBoard2.setNewDashBoard(new MAsSchoolDetail(dashBoard2.employees.get(position)));
                    }
                }
            });
        }
        return this.mView;
    }

    void filter(String text) {
        if (text.equals("")) {
            this.adapter.updateList(this.employees);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Data data : this.employees) {
            if (data.getUsers().getUserName().toLowerCase().contains(text.toLowerCase())) {
                arrayList.add(data);
            }
        }
        this.adapter.updateList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewDashBoard(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.title.equals("")) {
            ((MainActivity) getActivity()).setTvTitle(getString(R.string.sidemenu_dashboard));
            return;
        }
        ((MainActivity) getActivity()).setTvTitle(this.title + "s");
    }
}
