package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class PreviousMonitoring {

    @SerializedName("Is_Monitoring_Complete")
    @Expose
    private Object isMonitoringComplete;

    @SerializedName("KRAData")
    @Expose
    private List<KRADatum> kRAData = null;

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

    @SerializedName("Users")
    @Expose
    private Object users;

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

    public Object getIsMonitoringComplete() {
        return this.isMonitoringComplete;
    }

    public void setIsMonitoringComplete(Object isMonitoringComplete) {
        this.isMonitoringComplete = isMonitoringComplete;
    }

    public List<KRADatum> getKRAData() {
        return this.kRAData;
    }

    public void setKRAData(List<KRADatum> kRAData) {
        this.kRAData = kRAData;
    }

    public Object getUsers() {
        return this.users;
    }

    public void setUsers(Object users) {
        this.users = users;
    }
}
