package com.micromerger.ssms.addemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class SchoolsSpinnerAdapter extends BaseAdapter {
    Context context;
    List<Employee> employees;
    LayoutInflater inflater;
    List<School> schools;

    private String getValue(@Nullable String value) {
        return value != null ? value : "";
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return 0L;
    }

    public SchoolsSpinnerAdapter(Context context, List<School> schools, List<Employee> employees) {
        this.context = context;
        this.schools = schools;
        this.employees = employees;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List list = this.schools;
        if (list == null && (list = this.employees) == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public View getView(int position, View view, ViewGroup parent) {
        View viewInflate = this.inflater.inflate(R.layout.custom_spinner_items, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.textView);
        if (this.schools != null) {
            textView.setText(this.context.getResources().getString(R.string.school_dropdown_title, getValue(this.schools.get(position).getSchoolPrefix()), getValue(this.schools.get(position).getSchoolName()), getValue(this.schools.get(position).getSchoolSemisCode())));
        }
        List<Employee> list = this.employees;
        if (list != null) {
            textView.setText(list.get(position).getEmployeeName());
        }
        return viewInflate;
    }
}
