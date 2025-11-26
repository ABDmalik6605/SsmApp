package com.micromerger.ssms.startmonitoring.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class SchoolDataResponse implements Serializable {

    @SerializedName("Data")
    @Expose
    private EmployeeData data;

    @SerializedName("datetime")
    @Expose
    private String datetime;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Status")
    @Expose
    private Boolean status;

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

    public EmployeeData getData() {
        return this.data;
    }

    public void setData(EmployeeData data) {
        this.data = data;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
