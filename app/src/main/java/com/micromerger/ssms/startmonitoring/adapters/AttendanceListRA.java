package com.micromerger.ssms.startmonitoring.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.MaskedEditText;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class AttendanceListRA extends RecyclerView.Adapter<ViewHolder> {
    private static MyClickListener myClickListener;
    int keyDel;
    Context mContext;
    private List<Employee> mItems;
    private int position;

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return 0;
    }

    public AttendanceListRA() {
    }

    public AttendanceListRA(MainActivity mainActivity) {
        this.mContext = mainActivity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int view_type) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_attendance, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Employee employee = this.mItems.get(position);
        try {
            if (employee.getEmployeeName() != null) {
                viewHolder.tv_name.setVisibility(0);
                viewHolder.tv_name.setText(employee.getEmployeeName());
            }
            if (employee.getPresent() != null) {
                viewHolder.check.setChecked(employee.getPresent().booleanValue());
                viewHolder.check.setVisibility(0);
            }
            if (employee.getRoleInOrganization() != null) {
                viewHolder.tv_RoleInOrganization.setText(employee.getRoleInOrganization());
                viewHolder.tv_RoleInOrganization.setVisibility(0);
            }
            if (employee.getCnic() != null) {
                viewHolder.tv_cnic.setText(employee.getCnic());
                viewHolder.tv_cnic.setVisibility(0);
            }
            if (viewHolder.tv_cnic.getText().length() >= 13) {
                viewHolder.tv_cnic.setMask("", 3);
            }
            if (employee.getJoiningDate() != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = simpleDateFormat.parse(employee.getJoiningDate());
                simpleDateFormat.applyPattern("yyyy-MM-dd");
                viewHolder.tv_joining_date.setVisibility(0);
                viewHolder.tv_joining_date.setText(simpleDateFormat.format(date));
            } else {
                viewHolder.tv_joining_date.setVisibility(8);
            }
            if (employee.getAttendanceStatus() != null) {
                viewHolder.tv_presentAbsent.setText(employee.getAttendanceStatus());
                viewHolder.tv_presentAbsent.setVisibility(0);
                if (viewHolder.tv_presentAbsent.getText().toString().contentEquals(this.mContext.getString(R.string.attendPresent))) {
                    viewHolder.tv_presentAbsent.setTextColor(this.mContext.getResources().getColor(R.color.colorPrimary));
                } else {
                    viewHolder.tv_presentAbsent.setTextColor(this.mContext.getResources().getColor(R.color.red));
                }
                if (employee.getAttendanceStatus().equalsIgnoreCase(this.mContext.getString(R.string.thumbNotMatch))) {
                    viewHolder.tv_presentAbsent.setTextColor(this.mContext.getResources().getColor(R.color.colorPrimary));
                    employee.setPresent(true);
                    viewHolder.check.setChecked(employee.getPresent().booleanValue());
                }
                if (employee.getEmployeeCode() != null) {
                    viewHolder.tv_employee_id.setVisibility(0);
                    viewHolder.tv_employee_id.setText(employee.getEmployeeCode());
                } else {
                    viewHolder.tv_employee_id.setVisibility(8);
                }
            }
        } catch (Exception e) {
            if (e != null) {
                util.logException(e);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox check;
        View rootView;
        TextView tv_RoleInOrganization;
        MaskedEditText tv_cnic;
        TextView tv_employee_id;
        TextView tv_joining_date;
        TextView tv_name;
        TextView tv_presentAbsent;

        ViewHolder(View v) {
            super(v);
            this.tv_name = (TextView) v.findViewById(R.id.tv_name);
            this.check = (CheckBox) v.findViewById(R.id.checked);
            this.tv_presentAbsent = (TextView) v.findViewById(R.id.tv_presentAbsent);
            this.tv_cnic = (MaskedEditText) v.findViewById(R.id.tv_cnic);
            this.tv_RoleInOrganization = (TextView) v.findViewById(R.id.tv_RoleInOrganization);
            this.tv_joining_date = (TextView) v.findViewById(R.id.tv_joining_date);
            this.tv_employee_id = (TextView) v.findViewById(R.id.tv_employee_id);
            v.setOnClickListener(this);
            this.rootView = v;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AttendanceListRA.myClickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public Employee getItem(int position) {
        return this.mItems.get(position);
    }

    public void updateData(List<Employee> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addData(Employee employee, int position) {
        this.mItems.add(position, employee);
        notifyItemInserted(position);
    }

    public void addData(Employee employee) {
        this.mItems.add(employee);
        notifyDataSetChanged();
    }

    public void addAbsentData(List<Employee> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        this.mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData() {
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    public void sortList() {
        Collections.sort(this.mItems, new Comparator<Employee>() { // from class: com.micromerger.ssms.startmonitoring.adapters.AttendanceListRA.1
            @Override // java.util.Comparator
            public int compare(Employee e1, Employee e2) {
                return e1.getAttendanceStatus().compareTo(e2.getAttendanceStatus());
            }
        });
        notifyDataSetChanged();
    }
}
