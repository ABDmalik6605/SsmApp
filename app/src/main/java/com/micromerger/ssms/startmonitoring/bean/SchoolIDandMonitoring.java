package com.micromerger.ssms.startmonitoring.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class SchoolIDandMonitoring implements Serializable {
    private int monitoringID;
    private int schoolID;

    public int getSchoolID() {
        return this.schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getMonitoringID() {
        return this.monitoringID;
    }

    public void setMonitoringID(int monitoringID) {
        this.monitoringID = monitoringID;
    }
}
