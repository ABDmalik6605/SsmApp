package com.micromerger.ssms.startmonitoring.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.utils.Constant;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class MonitoringModel {

    @SerializedName("Absent_Reason_id")
    @Expose
    private String absentReasonId;

    @SerializedName(Constant.Active_Ind)
    @Expose
    private String activeInd;

    @SerializedName("AdoptedName")
    @Expose
    private String adoptedName;

    @SerializedName(Constant.AdoptionYear)
    @Expose
    private String adoptionYear;

    @SerializedName("Attendance_Date")
    @Expose
    private String attendanceDate;

    @SerializedName("Bank_Name")
    @Expose
    private String bankName;

    @SerializedName(Constant.Branch_Code)
    @Expose
    private String branchCode;

    @SerializedName(Constant.Branch_Name)
    @Expose
    private String branchName;

    @SerializedName("BranchSchool")
    @Expose
    private String branchSchool;

    @SerializedName(Constant.BuildingType)
    @Expose
    private String buildingType;

    @SerializedName("Class_Group_ID")
    @Expose
    private String classGroupID;

    @SerializedName("Class_ID")
    @Expose
    private String classID;

    @SerializedName("Class_SubGroup_ID")
    @Expose
    private String classSubGroupID;

    @SerializedName(Constant.Comments)
    @Expose
    private String comments;

    @SerializedName("Created_By")
    @Expose
    private String createdBy;

    @SerializedName("Created_Date")
    @Expose
    private String createdDate;

    @SerializedName("Employee_id")
    @Expose
    private String employeeId;

    @SerializedName(Constant.Enrollment_Source)
    @Expose
    private String enrollmentSource;

    @SerializedName("EstablishmentYear")
    @Expose
    private String establishmentYear;

    @SerializedName("Facility_Id")
    @Expose
    private String facilityId;

    @SerializedName(Constant.Got_Funds)
    @Expose
    private String gotFunds;

    @SerializedName(Constant.Has_Building)
    @Expose
    private String hasBuilding;

    @SerializedName("is_Absent")
    @Expose
    private String isAbsent;

    @SerializedName("IsAdopted")
    @Expose
    private String isAdopted;

    @SerializedName(Constant.Is_Functional)
    @Expose
    private String isFunctional;

    @SerializedName("is_Leave")
    @Expose
    private String isLeave;

    @SerializedName("is_Present")
    @Expose
    private String isPresent;

    @SerializedName("Leave_Reason_id")
    @Expose
    private String leaveReasonId;

    @SerializedName("MainSchoolName")
    @Expose
    private String mainSchoolName;

    @SerializedName(Constant.Medium_Type_ID)
    @Expose
    private String mediumTypeID;

    @SerializedName("Modified_By")
    @Expose
    private String modifiedBy;

    @SerializedName("Modified_Date")
    @Expose
    private String modifiedDate;

    @SerializedName("Monitoring_Attendance_ID")
    @Expose
    private String monitoringAttendanceID;

    @SerializedName("Monitoring_Building_id")
    @Expose
    private String monitoringBuildingId;

    @SerializedName("Monitoring_Enrollment_ID")
    @Expose
    private String monitoringEnrollmentID;

    @SerializedName("Monitoring_ID")
    @Expose
    private String monitoringID;

    @SerializedName("Monitoring_SMC_ID")
    @Expose
    private String monitoringSMCID;

    @SerializedName("Monitoring_Status_ID")
    @Expose
    private String monitoringStatusID;

    @SerializedName("Monitoring_Textbook_id")
    @Expose
    private String monitoringTextbookId;

    @SerializedName("Monitoring_UIO_ID")
    @Expose
    private String monitoringUIOID;

    @SerializedName("OtherFacilities_Monitering_ID")
    @Expose
    private String otherFacilitiesMoniteringID;

    @SerializedName(Constant.Other_Reason)
    @Expose
    private String otherReason;

    @SerializedName(Constant.Other_Source)
    @Expose
    private String otherSource;

    @SerializedName(Constant.Ownership)
    @Expose
    private String ownership;

    @SerializedName(Constant.Reason_id)
    @Expose
    private String reasonId;

    @SerializedName(Constant.Remarks_Id)
    @Expose
    private String remarksId;

    @SerializedName("SEMIS_CODE")
    @Expose
    private String sEMISCODE;

    @SerializedName(Constant.SEMIS_Code_Displayed)
    @Expose
    private String sEMISCodeDisplayed;

    @SerializedName(Constant.SMC_Account_Title)
    @Expose
    private String sMCAccountTitle;

    @SerializedName(Constant.SchoolPlacement)
    @Expose
    private String schoolPlacement;

    @SerializedName("ShortfallTextbooks")
    @Expose
    private String shortfallTextbooks;

    @SerializedName(Constant.Status_Id)
    @Expose
    private String statusId;

    @SerializedName("Students_In_Uniform")
    @Expose
    private String studentsInUniform;

    @SerializedName("StudentsWithTextbooks")
    @Expose
    private String studentsWithTextbooks;

    @SerializedName("SurplusTextbooks")
    @Expose
    private String surplusTextbooks;

    @SerializedName("TextbooksDistributed")
    @Expose
    private String textbooksDistributed;

    @SerializedName("TextbooksReceived")
    @Expose
    private String textbooksReceived;

    @SerializedName("TextbooksRequested")
    @Expose
    private String textbooksRequested;

    @SerializedName("TimelyDistributed")
    @Expose
    private String timelyDistributed;

    @SerializedName("Total_Female_Students")
    @Expose
    private String totalFemaleStudents;

    @SerializedName("Total_Female_Students_Present")
    @Expose
    private String totalFemaleStudentsPresent;

    @SerializedName("Total_Male_Students")
    @Expose
    private String totalMaleStudents;

    @SerializedName("Total_Male_Students_Present")
    @Expose
    private String totalMaleStudentsPresent;

    @SerializedName("Total_Non_Working")
    @Expose
    private String totalNonWorking;

    @SerializedName("Total_Students_Present")
    @Expose
    private String totalStudentsPresent;

    @SerializedName("Total_Working")
    @Expose
    private String totalWorking;

    @SerializedName(Constant.TypeOfSchool_ID)
    @Expose
    private String typeOfSchoolID;

    @SerializedName(Constant.Year_Id)
    @Expose
    private String yearId;

    public String getMonitoringStatusID() {
        return this.monitoringStatusID;
    }

    public void setMonitoringStatusID(String monitoringStatusID) {
        this.monitoringStatusID = monitoringStatusID;
    }

    public String getMonitoringID() {
        return this.monitoringID;
    }

    public void setMonitoringID(String monitoringID) {
        this.monitoringID = monitoringID;
    }

    public String getStatusId() {
        return this.statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getReasonId() {
        return this.reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getActiveInd() {
        return this.activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSEMISCodeDisplayed() {
        return this.sEMISCodeDisplayed;
    }

    public void setSEMISCodeDisplayed(String sEMISCodeDisplayed) {
        this.sEMISCodeDisplayed = sEMISCodeDisplayed;
    }

    public String getOtherReason() {
        return this.otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getMonitoringSMCID() {
        return this.monitoringSMCID;
    }

    public void setMonitoringSMCID(String monitoringSMCID) {
        this.monitoringSMCID = monitoringSMCID;
    }

    public String getIsFunctional() {
        return this.isFunctional;
    }

    public void setIsFunctional(String isFunctional) {
        this.isFunctional = isFunctional;
    }

    public String getGotFunds() {
        return this.gotFunds;
    }

    public void setGotFunds(String gotFunds) {
        this.gotFunds = gotFunds;
    }

    public String getYearId() {
        return this.yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getSMCAccountTitle() {
        return this.sMCAccountTitle;
    }

    public void setSMCAccountTitle(String sMCAccountTitle) {
        this.sMCAccountTitle = sMCAccountTitle;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getMonitoringUIOID() {
        return this.monitoringUIOID;
    }

    public void setMonitoringUIOID(String monitoringUIOID) {
        this.monitoringUIOID = monitoringUIOID;
    }

    public String getHasBuilding() {
        return this.hasBuilding;
    }

    public void setHasBuilding(String hasBuilding) {
        this.hasBuilding = hasBuilding;
    }

    public String getRemarksId() {
        return this.remarksId;
    }

    public void setRemarksId(String remarksId) {
        this.remarksId = remarksId;
    }

    public String getMonitoringAttendanceID() {
        return this.monitoringAttendanceID;
    }

    public void setMonitoringAttendanceID(String monitoringAttendanceID) {
        this.monitoringAttendanceID = monitoringAttendanceID;
    }

    public String getAttendanceDate() {
        return this.attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getIsPresent() {
        return this.isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }

    public String getIsAbsent() {
        return this.isAbsent;
    }

    public void setIsAbsent(String isAbsent) {
        this.isAbsent = isAbsent;
    }

    public String getIsLeave() {
        return this.isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    public String getLeaveReasonId() {
        return this.leaveReasonId;
    }

    public void setLeaveReasonId(String leaveReasonId) {
        this.leaveReasonId = leaveReasonId;
    }

    public String getAbsentReasonId() {
        return this.absentReasonId;
    }

    public void setAbsentReasonId(String absentReasonId) {
        this.absentReasonId = absentReasonId;
    }

    public String getMonitoringBuildingId() {
        return this.monitoringBuildingId;
    }

    public void setMonitoringBuildingId(String monitoringBuildingId) {
        this.monitoringBuildingId = monitoringBuildingId;
    }

    public String getOwnership() {
        return this.ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getSchoolPlacement() {
        return this.schoolPlacement;
    }

    public void setSchoolPlacement(String schoolPlacement) {
        this.schoolPlacement = schoolPlacement;
    }

    public String getEstablishmentYear() {
        return this.establishmentYear;
    }

    public void setEstablishmentYear(String establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public String getBranchSchool() {
        return this.branchSchool;
    }

    public void setBranchSchool(String branchSchool) {
        this.branchSchool = branchSchool;
    }

    public String getMainSchoolName() {
        return this.mainSchoolName;
    }

    public void setMainSchoolName(String mainSchoolName) {
        this.mainSchoolName = mainSchoolName;
    }

    public String getSEMISCODE() {
        return this.sEMISCODE;
    }

    public void setSEMISCODE(String sEMISCODE) {
        this.sEMISCODE = sEMISCODE;
    }

    public String getIsAdopted() {
        return this.isAdopted;
    }

    public void setIsAdopted(String isAdopted) {
        this.isAdopted = isAdopted;
    }

    public String getAdoptedName() {
        return this.adoptedName;
    }

    public void setAdoptedName(String adoptedName) {
        this.adoptedName = adoptedName;
    }

    public String getAdoptionYear() {
        return this.adoptionYear;
    }

    public void setAdoptionYear(String adoptionYear) {
        this.adoptionYear = adoptionYear;
    }

    public String getBuildingType() {
        return this.buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getMonitoringTextbookId() {
        return this.monitoringTextbookId;
    }

    public void setMonitoringTextbookId(String monitoringTextbookId) {
        this.monitoringTextbookId = monitoringTextbookId;
    }

    public String getClassID() {
        return this.classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getTextbooksRequested() {
        return this.textbooksRequested;
    }

    public void setTextbooksRequested(String textbooksRequested) {
        this.textbooksRequested = textbooksRequested;
    }

    public String getTextbooksReceived() {
        return this.textbooksReceived;
    }

    public void setTextbooksReceived(String textbooksReceived) {
        this.textbooksReceived = textbooksReceived;
    }

    public String getTextbooksDistributed() {
        return this.textbooksDistributed;
    }

    public void setTextbooksDistributed(String textbooksDistributed) {
        this.textbooksDistributed = textbooksDistributed;
    }

    public String getStudentsWithTextbooks() {
        return this.studentsWithTextbooks;
    }

    public void setStudentsWithTextbooks(String studentsWithTextbooks) {
        this.studentsWithTextbooks = studentsWithTextbooks;
    }

    public String getSurplusTextbooks() {
        return this.surplusTextbooks;
    }

    public void setSurplusTextbooks(String surplusTextbooks) {
        this.surplusTextbooks = surplusTextbooks;
    }

    public String getShortfallTextbooks() {
        return this.shortfallTextbooks;
    }

    public void setShortfallTextbooks(String shortfallTextbooks) {
        this.shortfallTextbooks = shortfallTextbooks;
    }

    public String getTimelyDistributed() {
        return this.timelyDistributed;
    }

    public void setTimelyDistributed(String timelyDistributed) {
        this.timelyDistributed = timelyDistributed;
    }

    public String getOtherFacilitiesMoniteringID() {
        return this.otherFacilitiesMoniteringID;
    }

    public void setOtherFacilitiesMoniteringID(String otherFacilitiesMoniteringID) {
        this.otherFacilitiesMoniteringID = otherFacilitiesMoniteringID;
    }

    public String getFacilityId() {
        return this.facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getTotalWorking() {
        return this.totalWorking;
    }

    public void setTotalWorking(String totalWorking) {
        this.totalWorking = totalWorking;
    }

    public String getTotalNonWorking() {
        return this.totalNonWorking;
    }

    public void setTotalNonWorking(String totalNonWorking) {
        this.totalNonWorking = totalNonWorking;
    }

    public String getMonitoringEnrollmentID() {
        return this.monitoringEnrollmentID;
    }

    public void setMonitoringEnrollmentID(String monitoringEnrollmentID) {
        this.monitoringEnrollmentID = monitoringEnrollmentID;
    }

    public String getEnrollmentSource() {
        return this.enrollmentSource;
    }

    public void setEnrollmentSource(String enrollmentSource) {
        this.enrollmentSource = enrollmentSource;
    }

    public String getOtherSource() {
        return this.otherSource;
    }

    public void setOtherSource(String otherSource) {
        this.otherSource = otherSource;
    }

    public String getMediumTypeID() {
        return this.mediumTypeID;
    }

    public void setMediumTypeID(String mediumTypeID) {
        this.mediumTypeID = mediumTypeID;
    }

    public String getTypeOfSchoolID() {
        return this.typeOfSchoolID;
    }

    public void setTypeOfSchoolID(String typeOfSchoolID) {
        this.typeOfSchoolID = typeOfSchoolID;
    }

    public String getClassGroupID() {
        return this.classGroupID;
    }

    public void setClassGroupID(String classGroupID) {
        this.classGroupID = classGroupID;
    }

    public String getClassSubGroupID() {
        return this.classSubGroupID;
    }

    public void setClassSubGroupID(String classSubGroupID) {
        this.classSubGroupID = classSubGroupID;
    }

    public String getTotalMaleStudents() {
        return this.totalMaleStudents;
    }

    public void setTotalMaleStudents(String totalMaleStudents) {
        this.totalMaleStudents = totalMaleStudents;
    }

    public String getTotalFemaleStudents() {
        return this.totalFemaleStudents;
    }

    public void setTotalFemaleStudents(String totalFemaleStudents) {
        this.totalFemaleStudents = totalFemaleStudents;
    }

    public String getTotalMaleStudentsPresent() {
        return this.totalMaleStudentsPresent;
    }

    public void setTotalMaleStudentsPresent(String totalMaleStudentsPresent) {
        this.totalMaleStudentsPresent = totalMaleStudentsPresent;
    }

    public String getTotalFemaleStudentsPresent() {
        return this.totalFemaleStudentsPresent;
    }

    public void setTotalFemaleStudentsPresent(String totalFemaleStudentsPresent) {
        this.totalFemaleStudentsPresent = totalFemaleStudentsPresent;
    }

    public String getTotalStudentsPresent() {
        return this.totalStudentsPresent;
    }

    public void setTotalStudentsPresent(String totalStudentsPresent) {
        this.totalStudentsPresent = totalStudentsPresent;
    }

    public String getStudentsInUniform() {
        return this.studentsInUniform;
    }

    public void setStudentsInUniform(String studentsInUniform) {
        this.studentsInUniform = studentsInUniform;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
