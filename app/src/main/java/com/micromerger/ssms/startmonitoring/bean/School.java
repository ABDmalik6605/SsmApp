package com.micromerger.ssms.startmonitoring.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.utils.Constant;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class School implements Serializable {
    private static final long serialVersionUID = 8095009191316622394L;

    @SerializedName("District_Id")
    @Expose
    private int District_Id;

    @SerializedName("MonitoringStartDate")
    @Expose
    private String MonitoringStartDate;

    @SerializedName("Monitoring_ID")
    @Expose
    private int Monitoring_ID;

    @SerializedName("isDownloaded")
    @Expose
    private Boolean isDownloaded;

    @SerializedName("isMonitoringComplete")
    @Expose
    private Boolean isMonitoringComplete;

    @SerializedName("isSync")
    @Expose
    private Boolean isSync;

    @SerializedName(Constant.Latitude)
    @Expose
    private String latitude;

    @SerializedName(Constant.Longitude)
    @Expose
    private String longitude;

    @SerializedName("MonitoringEndDate")
    @Expose
    private String monitoringEndDate;

    @SerializedName("ScheduledMoniteringDate")
    @Expose
    private String scheduledDate;

    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;

    @SerializedName("SchoolName")
    @Expose
    private String schoolName;

    @SerializedName("SchoolPrefix")
    @Expose
    private String schoolPrefix;

    @SerializedName("SchoolSemisCode")
    @Expose
    private String schoolSemisCode;

    public Boolean getIsMonitoringComplete() {
        return this.isMonitoringComplete;
    }

    public void setIsMonitoringComplete(Boolean isMonitoringComplete) {
        this.isMonitoringComplete = isMonitoringComplete;
    }

    public Boolean getIsSync() {
        return this.isSync;
    }

    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    public String getMonitoringEndDate() {
        return this.monitoringEndDate;
    }

    public void setMonitoringEndDate(String monitoringEndDate) {
        this.monitoringEndDate = monitoringEndDate;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolSemisCode() {
        return this.schoolSemisCode;
    }

    public void setSchoolSemisCode(String schoolSemisCode) {
        this.schoolSemisCode = schoolSemisCode;
    }

    public int getDistrict_Id() {
        return this.District_Id;
    }

    public void setDistrict_Id(int district_Id) {
        this.District_Id = district_Id;
    }

    public Boolean isDownloaded() {
        return this.isDownloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.isDownloaded = downloaded;
    }

    public int getMonitoring_ID() {
        return this.Monitoring_ID;
    }

    public void setMonitoring_ID(int monitoring_ID) {
        this.Monitoring_ID = monitoring_ID;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSchoolPrefix() {
        return this.schoolPrefix;
    }

    public void setSchoolPrefix(String schoolPrefix) {
        this.schoolPrefix = schoolPrefix;
    }

    public String getScheduledDate() {
        return this.scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getMonitoringStartDate() {
        return this.MonitoringStartDate;
    }

    public void setMonitoringStartDate(String monitoringStartDate) {
        this.MonitoringStartDate = monitoringStartDate;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
