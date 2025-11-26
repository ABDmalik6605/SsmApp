package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class KRADatum {

    @SerializedName("Activeind")
    @Expose
    private Boolean activeind;

    @SerializedName("DataGroup")
    @Expose
    private String dataGroup;

    @SerializedName("DataValue")
    @Expose
    private String dataValue;

    @SerializedName("IsMandatory")
    @Expose
    private Boolean isMandatory;

    @SerializedName("KPIType")
    @Expose
    private Integer kPIType;

    @SerializedName("KRAName")
    @Expose
    private String kRAName;

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

    public String getDataValue() {
        return this.dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataGroup() {
        return this.dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    public Integer getKPIType() {
        return this.kPIType;
    }

    public void setKPIType(Integer kPIType) {
        this.kPIType = kPIType;
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
