package com.micromerger.ssms.addemployee;

import com.micromerger.ssms.addemployee.bean.EmployeeUpdateRecord;

/* loaded from: classes2.dex */
public class EmployeeDbRecord {
    private int alreadySynced;
    private String attendanceRegisterPicturePath;
    private String cnic;
    private String cnicPicturePath;
    private String dateTime;
    private String employeeId;
    private String employeePayslipPicturePath;
    private String employeePicturePath;
    private EmployeeUpdateRecord employeeUpdateRecord;
    private String govtLetterPicturePath;
    private String informationLetterPicturePath;
    private String joiningReportPicturePath;
    private String name;
    private String offerOrderPicturePath;
    private String otherPicturePath;
    private String postingOrderPicturePath;
    private String reason;
    private String reasonOrderPicturePath;
    private int recordId;
    private int syncStatus;

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public EmployeeUpdateRecord getAddEmployeeRecord() {
        return this.employeeUpdateRecord;
    }

    public void setAddEmployeeRecord(EmployeeUpdateRecord employeeUpdateRecord) {
        this.employeeUpdateRecord = employeeUpdateRecord;
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

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmployeePicturePath() {
        return this.employeePicturePath;
    }

    public void setEmployeePicturePath(String employeePicturePath) {
        this.employeePicturePath = employeePicturePath;
    }

    public String getCnicPicturePath() {
        return this.cnicPicturePath;
    }

    public void setCnicPicturePath(String cnicPicturePath) {
        this.cnicPicturePath = cnicPicturePath;
    }

    public String getGovtLetterPicturePath() {
        return this.govtLetterPicturePath;
    }

    public void setGovtLetterPicturePath(String govtLetterPicturePath) {
        this.govtLetterPicturePath = govtLetterPicturePath;
    }

    public String getOtherPicturePath() {
        return this.otherPicturePath;
    }

    public void setOtherPicturePath(String otherPicturePath) {
        this.otherPicturePath = otherPicturePath;
    }

    public String getPostingOrderPicturePath() {
        return this.postingOrderPicturePath;
    }

    public void setPostingOrderPicturePath(String postingOrderPicturePath) {
        this.postingOrderPicturePath = postingOrderPicturePath;
    }

    public String getReasonOrderPicturePath() {
        return this.reasonOrderPicturePath;
    }

    public void setReasonOrderPicturePath(String reasonOrderPicturePath) {
        this.reasonOrderPicturePath = reasonOrderPicturePath;
    }

    public String getEmployeePayslipPicturePath() {
        return this.employeePayslipPicturePath;
    }

    public void setEmployeePayslipPicturePath(String employeePayslipPicturePath) {
        this.employeePayslipPicturePath = employeePayslipPicturePath;
    }

    public String getOfferOrderPicturePath() {
        return this.offerOrderPicturePath;
    }

    public void setOfferOrderPicturePath(String offerOrderPicturePath) {
        this.offerOrderPicturePath = offerOrderPicturePath;
    }

    public String getJoiningReportPicturePath() {
        return this.joiningReportPicturePath;
    }

    public void setJoiningReportPicturePath(String joiningReportPicturePath) {
        this.joiningReportPicturePath = joiningReportPicturePath;
    }

    public String getAttendanceRegisterPicturePath() {
        return this.attendanceRegisterPicturePath;
    }

    public void setAttendanceRegisterPicturePath(String attendanceRegisterPicturePath) {
        this.attendanceRegisterPicturePath = attendanceRegisterPicturePath;
    }

    public String getInformationLetterPicturePath() {
        return this.informationLetterPicturePath;
    }

    public void setInformationLetterPicturePath(String informationLetterPicturePath) {
        this.informationLetterPicturePath = informationLetterPicturePath;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getAlreadySynced() {
        return this.alreadySynced;
    }

    public void setAlreadySynced(int alreadySynced) {
        this.alreadySynced = alreadySynced;
    }

    public String toString() {
        return "EmployeeDbRecord{recordId=" + this.recordId + ", employeeUpdateRecord=" + this.employeeUpdateRecord + ", name='" + this.name + "', cnic='" + this.cnic + "', employeeId='" + this.employeeId + "', reason='" + this.reason + "', employeePicturePath='" + this.employeePicturePath + "', cnicPicturePath='" + this.cnicPicturePath + "', govtLetterPicturePath='" + this.govtLetterPicturePath + "', otherPicturePath='" + this.otherPicturePath + "', postingOrderPicturePath='" + this.postingOrderPicturePath + "', appointmentOrderPicturePath='" + this.reasonOrderPicturePath + "', employeePayslipPicturePath='" + this.employeePayslipPicturePath + "', offerOrderPicturePath='" + this.offerOrderPicturePath + "', joiningReportPicturePath='" + this.joiningReportPicturePath + "', attendanceRegisterPicturePath='" + this.attendanceRegisterPicturePath + "', informationLetterPicturePath='" + this.informationLetterPicturePath + "', dateTime='" + this.dateTime + "', syncStatus=" + this.syncStatus + ", alreadySynced=" + this.alreadySynced + '}';
    }
}
