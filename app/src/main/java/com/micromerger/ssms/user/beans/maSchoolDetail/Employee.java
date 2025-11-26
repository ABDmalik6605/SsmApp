package com.micromerger.ssms.user.beans.maSchoolDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Employee implements Serializable {
    private static final long serialVersionUID = 4645992082073178698L;

    @SerializedName("ActiveInd")
    @Expose
    private Boolean activeInd;

    @SerializedName("Address")
    @Expose
    private String address;

    @SerializedName("Cnic")
    @Expose
    private String cnic;

    @SerializedName("Dateofbirth")
    @Expose
    private String dateofbirth;

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

    @SerializedName("Gender")
    @Expose
    private Boolean gender;

    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

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

    public Boolean getActiveInd() {
        return this.activeInd;
    }

    public void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
