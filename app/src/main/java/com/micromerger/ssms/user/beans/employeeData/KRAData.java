package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class KRAData {

    @SerializedName("Activeind")
    @Expose
    private Boolean activeind;

    @SerializedName("DataGroup")
    @Expose
    private String dataGroup;

    @SerializedName("DataValue")
    @Expose
    private Object dataValue;

    @SerializedName("EndDate")
    @Expose
    private String endDate;

    @SerializedName("IsMandatory")
    @Expose
    private Boolean isMandatory;

    @SerializedName("KRAName")
    @Expose
    private String kRAName;

    @SerializedName("KpiType")
    @Expose
    private Integer kpiType;

    @SerializedName("Kpiid")
    @Expose
    private Integer kpiid;

    @SerializedName("Kpiname")
    @Expose
    private String kpiname;

    @SerializedName("KpitypeName")
    @Expose
    private String kpitypeName;

    @SerializedName("Kraid")
    @Expose
    private Integer kraid;

    @SerializedName("StartDate")
    @Expose
    private String startDate;

    public Integer getKpiid() {
        return this.kpiid;
    }

    public void setKpiid(Integer kpiid) {
        this.kpiid = kpiid;
    }

    public String getKpiname() {
        return this.kpiname;
    }

    public void setKpiname(String kpiname) {
        this.kpiname = kpiname;
    }

    public Integer getKraid() {
        return this.kraid;
    }

    public void setKraid(Integer kraid) {
        this.kraid = kraid;
    }

    public String getKRAName() {
        return this.kRAName;
    }

    public void setKRAName(String kRAName) {
        this.kRAName = kRAName;
    }

    public Object getDataValue() {
        return this.dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataGroup() {
        return this.dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getKpiType() {
        return this.kpiType;
    }

    public void setKpiType(Integer kpiType) {
        this.kpiType = kpiType;
    }

    public String getKpitypeName() {
        return this.kpitypeName;
    }

    public void setKpitypeName(String kpitypeName) {
        this.kpitypeName = kpitypeName;
    }

    public Boolean getIsMandatory() {
        return this.isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Boolean getActiveind() {
        return this.activeind;
    }

    public void setActiveind(Boolean activeind) {
        this.activeind = activeind;
    }
}
