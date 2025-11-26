package com.micromerger.ssms.searchemployee.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class SearchEmployeeResponse implements Serializable {
    private static final long serialVersionUID = -7663783355777680727L;

    @SerializedName("Data")
    @Expose
    private List<Employee> data = null;

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

    public List<Employee> getData() {
        return this.data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
