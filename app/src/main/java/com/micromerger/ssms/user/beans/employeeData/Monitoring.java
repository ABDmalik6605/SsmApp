package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Monitoring {

    @SerializedName("Is_Monitoring_Complete")
    @Expose
    private Boolean isMonitoringComplete;

    @SerializedName("KRAData")
    @Expose
    private List<KRAData> kRAData = null;

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
    private Users users;

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

    public List<KRAData> getKRAData() {
        return this.kRAData;
    }

    public void setKRAData(List<KRAData> kRAData) {
        this.kRAData = kRAData;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
