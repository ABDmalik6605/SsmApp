package com.micromerger.ssms.addemployee.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class EmployeeUpdateRecordList {

    @SerializedName("EmployeeUpdateRecordList")
    @Expose
    private List<EmployeeUpdateRecord> employeeUpdateRecordList = null;

    @SerializedName("token")
    @Expose
    private String token;

    public List<EmployeeUpdateRecord> getEmployeeUpdateRecordList() {
        return this.employeeUpdateRecordList;
    }

    public void setEmployeeUpdateRecordList(List<EmployeeUpdateRecord> employeeUpdateRecordList) {
        this.employeeUpdateRecordList = employeeUpdateRecordList;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
