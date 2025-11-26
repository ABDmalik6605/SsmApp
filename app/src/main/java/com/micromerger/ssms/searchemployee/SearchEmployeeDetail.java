package com.micromerger.ssms.searchemployee;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;
import com.micromerger.ssms.user.beans.employeeData.Employee;

/* loaded from: classes2.dex */
public class SearchEmployeeDetail extends Fragment {
    private ClipboardManager clipboardManager;
    Employee employee;
    View mView;
    TextView tv_address;
    TextView tv_cnic;
    TextView tv_desig;
    TextView tv_dob;
    TextView tv_doj;
    TextView tv_id;
    TextView tv_name;
    TextView tv_rd;
    TextView tv_school;
    TextView tv_semis_code;

    public SearchEmployeeDetail() {
    }

    public SearchEmployeeDetail(Employee employee) {
        this.employee = employee;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_searched_employee_detail, (ViewGroup) null);
        this.mView = viewInflate;
        this.tv_id = (TextView) viewInflate.findViewById(R.id.tv_id);
        this.tv_school = (TextView) this.mView.findViewById(R.id.tv_school);
        this.tv_semis_code = (TextView) this.mView.findViewById(R.id.tv_semis_code);
        this.tv_name = (TextView) this.mView.findViewById(R.id.tv_name);
        this.tv_address = (TextView) this.mView.findViewById(R.id.tv_address);
        this.tv_dob = (TextView) this.mView.findViewById(R.id.tv_dob);
        this.tv_doj = (TextView) this.mView.findViewById(R.id.tv_doj);
        this.tv_rd = (TextView) this.mView.findViewById(R.id.tv_rd);
        this.tv_desig = (TextView) this.mView.findViewById(R.id.tv_desig);
        this.tv_cnic = (TextView) this.mView.findViewById(R.id.tv_cnic);
        this.clipboardManager = (ClipboardManager) this.mView.getContext().getSystemService("clipboard");
        ((ImageButton) this.mView.findViewById(R.id.copy_button_id)).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.searchemployee.-$$Lambda$SearchEmployeeDetail$N9loGQ_MG0IxWrXmrdBzTSB1alU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$SearchEmployeeDetail(view);
            }
        });
        Employee employee = this.employee;
        if (employee != null) {
            this.tv_id.setText(String.valueOf(employee.getEmployeeCode()));
            this.tv_school.setText(this.employee.getSchoolName());
            this.tv_semis_code.setText(this.employee.getSemisCode());
            this.tv_name.setText(this.employee.getEmployeeName());
            this.tv_address.setText(this.employee.getAddress());
            if (this.employee.getDateofbirth() != null && this.employee.getDateofbirth().length() > 0 && !this.employee.getDateofbirth().equals("0001-01-01T00:00:00")) {
                this.tv_dob.setText(this.employee.getDateofbirth().substring(0, this.employee.getDateofbirth().indexOf(ExifInterface.GPS_DIRECTION_TRUE)));
            }
            if (this.employee.getJoiningDate() != null && this.employee.getJoiningDate().length() > 0 && !this.employee.getJoiningDate().equals("0001-01-01T00:00:00")) {
                this.tv_doj.setText(this.employee.getJoiningDate().substring(0, this.employee.getJoiningDate().indexOf(ExifInterface.GPS_DIRECTION_TRUE)));
            }
            if (this.employee.getRetirementDate() != null && this.employee.getRetirementDate().length() > 0 && !this.employee.getRetirementDate().equals("0001-01-01T00:00:00")) {
                this.tv_rd.setText(this.employee.getRetirementDate().substring(0, this.employee.getRetirementDate().indexOf(ExifInterface.GPS_DIRECTION_TRUE)));
            }
            this.tv_desig.setText(this.employee.getRoleInOrganization());
            if (this.employee.getCnic() != null) {
                this.tv_cnic.setText(this.employee.getCnic());
            }
        }
        return this.mView;
    }

    public /* synthetic */ void lambda$onCreateView$0$SearchEmployeeDetail(View view) {
        StringBuilder sb = new StringBuilder();
        if (this.tv_id.getText() != null && this.tv_id.getText().length() != 0) {
            sb.append(this.tv_id.getText());
            sb.append("\n");
        }
        if (this.tv_school.getText() != null && this.tv_school.getText().length() != 0) {
            sb.append(this.tv_school.getText());
            sb.append("\n");
        }
        if (this.tv_semis_code.getText() != null && this.tv_semis_code.getText().length() != 0) {
            sb.append(this.tv_semis_code.getText());
            sb.append("\n");
        }
        if (this.tv_name.getText() != null && this.tv_name.getText().length() != 0) {
            sb.append(this.tv_name.getText());
            sb.append("\n");
        }
        if (this.tv_address.getText() != null && this.tv_address.getText().length() != 0) {
            sb.append(this.tv_address.getText());
            sb.append("\n");
        }
        if (this.tv_dob.getText() != null && this.tv_dob.getText().length() != 0) {
            sb.append(this.tv_dob.getText());
            sb.append("\n");
        }
        if (this.tv_doj.getText() != null && this.tv_doj.getText().length() != 0) {
            sb.append(this.tv_doj.getText());
            sb.append("\n");
        }
        if (this.tv_rd.getText() != null && this.tv_rd.getText().length() != 0) {
            sb.append(this.tv_rd.getText());
            sb.append("\n");
        }
        if (this.tv_desig.getText() != null && this.tv_desig.getText().length() != 0) {
            sb.append(this.tv_desig.getText());
            sb.append("\n");
        }
        if (this.tv_cnic.getText() != null && this.tv_cnic.getText().length() != 0) {
            sb.append(this.tv_cnic.getText());
        }
        copyToClipboard(sb.toString());
    }

    private void copyToClipboard(String text) {
        try {
            if (this.clipboardManager == null || text == null) {
                return;
            }
            this.clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text));
            Toast.makeText(this.mView.getContext(), "Data Copied to Clipboard", 1).show();
        } catch (Exception unused) {
        }
    }
}
