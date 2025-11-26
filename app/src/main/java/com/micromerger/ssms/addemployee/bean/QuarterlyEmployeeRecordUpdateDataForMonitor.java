package com.micromerger.ssms.addemployee.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class QuarterlyEmployeeRecordUpdateDataForMonitor {

    @SerializedName("Data")
    @Expose
    private List<EmployeeRecordUpdateDatum> data = null;

    @SerializedName("datetime")
    @Expose
    private String datetime;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Status")
    @Expose
    private Boolean status;

    @SerializedName("total")
    @Expose
    private Integer total;

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EmployeeRecordUpdateDatum> getData() {
        return this.data;
    }

    public void setData(List<EmployeeRecordUpdateDatum> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
