package com.micromerger.ssms.startmonitoring.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AllSchoolsResponse implements Serializable {
    private static final long serialVersionUID = -7509629873003227434L;

    @SerializedName("Data")
    @Expose
    private List<School> data = new ArrayList();

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

    public List<School> getData() {
        return this.data;
    }

    public void setData(List<School> data) {
        this.data = data;
    }
}
