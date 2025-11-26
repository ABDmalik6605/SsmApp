package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Employee {

    @SerializedName("ActiveInd")
    @Expose
    private Boolean activeInd;

    @SerializedName("Address")
    @Expose
    private String address;
    private String attendanceStatus = "";

    @SerializedName("Cadre_ID")
    @Expose
    private int cadreID;

    @SerializedName("Cnic")
    @Expose
    private String cnic;

    @SerializedName("Dateofbirth")
    @Expose
    private String dateofbirth;

    @SerializedName("DonotAskForVaccination")
    @Expose
    private Boolean donotAskForVaccination;

    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress;

    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;

    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;

    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;

    @SerializedName("EmployeeProfileImage")
    @Expose
    private Object employeeProfileImage;

    @SerializedName("Gender")
    @Expose
    private Boolean gender;

    @SerializedName("IsFirstDose")
    @Expose
    private boolean isFirstDose;

    @SerializedName("IsFullyVaccinated")
    @Expose
    private boolean isFullyVaccinated;
    private Boolean isPresent;

    @SerializedName("IsSecondDose")
    @Expose
    private boolean isSecondDose;

    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;

    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("RetirementDate")
    @Expose
    private String retirementDate;

    @SerializedName("RoleInOrganization")
    @Expose
    private String roleInOrganization;

    @SerializedName("SchoolName")
    @Expose
    private String schoolName;

    @SerializedName("SemisCode")
    @Expose
    private String semisCode;

    @SerializedName("VaccinationType")
    @Expose
    private int vaccinationType;

    public String getAttendanceStatus() {
        return this.attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Boolean getPresent() {
        return this.isPresent;
    }

    public void setPresent(Boolean present) {
        this.isPresent = present;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return this.employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return this.gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDateofbirth() {
        return this.dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getSemisCode() {
        return this.semisCode;
    }

    public void setSemisCode(String semisCode) {
        this.semisCode = semisCode;
    }

    public String getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getRetirementDate() {
        return this.retirementDate;
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getRoleInOrganization() {
        return this.roleInOrganization;
    }

    public void setRoleInOrganization(String roleInOrganization) {
        this.roleInOrganization = roleInOrganization;
    }

    public Boolean getActiveInd() {
        return this.activeInd;
    }

    public void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
    }

    public Object getEmployeeProfileImage() {
        return this.employeeProfileImage;
    }

    public void setEmployeeProfileImage(Object employeeProfileImage) {
        this.employeeProfileImage = employeeProfileImage;
    }

    public Integer getCadre_ID() {
        return Integer.valueOf(this.cadreID);
    }

    public void setCadre_ID(int cadreID) {
        this.cadreID = cadreID;
    }

    public Integer getVaccinationType() {
        return Integer.valueOf(this.vaccinationType);
    }

    public void setVaccinationType(int vaccinationType) {
        this.vaccinationType = vaccinationType;
    }

    public Boolean getIsFirstDose() {
        return Boolean.valueOf(this.isFirstDose);
    }

    public void setIsFirstDose(boolean isFirstDose) {
        this.isFirstDose = isFirstDose;
    }

    public Boolean getIsSecondDose() {
        return Boolean.valueOf(this.isSecondDose);
    }

    public void setIsSecondDose(boolean isSecondDose) {
        this.isSecondDose = isSecondDose;
    }

    public Boolean getIsFullyVaccinated() {
        return Boolean.valueOf(this.isFullyVaccinated);
    }

    public void setIsFullyVaccinated(boolean isFullyVaccinated) {
        this.isFullyVaccinated = isFullyVaccinated;
    }

    public Boolean getDonotAskForVaccination() {
        return this.donotAskForVaccination;
    }

    public void setDonotAskForVaccination(boolean donotAskForVaccination) {
        this.donotAskForVaccination = Boolean.valueOf(donotAskForVaccination);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
