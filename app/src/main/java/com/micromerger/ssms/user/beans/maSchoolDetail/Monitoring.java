package com.micromerger.ssms.user.beans.maSchoolDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Monitoring implements Serializable {
    private static final long serialVersionUID = 5932453302813517552L;

    @SerializedName("Is_Monitoring_Complete")
    @Expose
    private Boolean isMonitoringComplete;

    @SerializedName("KRAData")
    @Expose
    private List<Object> kRAData = null;

    @SerializedName("Monitoring_End_Date")
    @Expose
    private String monitoringEndDate;

    @SerializedName("Monitoring_ID")
    @Expose
    private Integer monitoringID;

    @SerializedName("Monitoring_Start_Date")
    @Expose
    private String monitoringStartDate;

    @SerializedName("Scheduled_Monitering_Date")
    @Expose
    private String scheduledMoniteringDate;

    public Integer getMonitoringID() {
        return this.monitoringID;
    }

    public void setMonitoringID(Integer monitoringID) {
        this.monitoringID = monitoringID;
    }

    public String getScheduledMoniteringDate() {
        return this.scheduledMoniteringDate;
    }

    public void setScheduledMoniteringDate(String scheduledMoniteringDate) {
        this.scheduledMoniteringDate = scheduledMoniteringDate;
    }

    public String getMonitoringStartDate() {
        return this.monitoringStartDate;
    }

    public void setMonitoringStartDate(String monitoringStartDate) {
        this.monitoringStartDate = monitoringStartDate;
    }

    public String getMonitoringEndDate() {
        return this.monitoringEndDate;
    }

    public void setMonitoringEndDate(String monitoringEndDate) {
        this.monitoringEndDate = monitoringEndDate;
    }

    public Boolean getIsMonitoringComplete() {
        return this.isMonitoringComplete;
    }

    public void setIsMonitoringComplete(Boolean isMonitoringComplete) {
        this.isMonitoringComplete = isMonitoringComplete;
    }

    public List<Object> getKRAData() {
        return this.kRAData;
    }

    public void setKRAData(List<Object> kRAData) {
        this.kRAData = kRAData;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
