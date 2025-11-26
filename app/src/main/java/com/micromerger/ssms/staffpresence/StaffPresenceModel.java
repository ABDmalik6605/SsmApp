package com.micromerger.ssms.staffpresence;

/* loaded from: classes2.dex */
public class StaffPresenceModel {
    private String cnic;
    private String name;
    private String personalNo;
    private String reason;
    private int recordId;
    private boolean synced;
    private long timeInMillis;

    public StaffPresenceModel(int recordId, String name, String cnic, String personalNo, String reason, long timeInMillis, boolean synced) {
        this.recordId = recordId;
        this.name = name;
        this.cnic = cnic;
        this.personalNo = personalNo;
        this.reason = reason;
        this.timeInMillis = timeInMillis;
        this.synced = synced;
    }

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPersonalNo() {
        return this.personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getTimeInMillis() {
        return this.timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public boolean isSynced() {
        return this.synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public String toString() {
        return "StaffPresenceModel{name='" + this.name + "', cnic='" + this.cnic + "', personalNo='" + this.personalNo + "', status='" + this.reason + "', timeInMillis=" + this.timeInMillis + ", synced=" + this.synced + '}';
    }
}
