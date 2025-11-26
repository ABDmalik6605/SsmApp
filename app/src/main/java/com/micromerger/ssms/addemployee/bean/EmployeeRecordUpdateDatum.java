package com.micromerger.ssms.addemployee.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.utils.Constant;

/* loaded from: classes2.dex */
public class EmployeeRecordUpdateDatum {

    @SerializedName("CNIC")
    @Expose
    private String cnic;

    @SerializedName(Constant.Comments)
    @Expose
    private String comments;

    @SerializedName("Employee_Name")
    @Expose
    private String employeeName;

    @SerializedName("Issue_Type")
    @Expose
    private Integer issueType;

    @SerializedName("Other_Picture")
    @Expose
    private String otherPicture;

    @SerializedName("Parent_Ticket_Id")
    @Expose
    private Integer parentTicketId;

    @SerializedName("PID")
    @Expose
    private String pid;

    @SerializedName("School_ID_Visit")
    @Expose
    private Integer schoolIdVisit;

    @SerializedName("Status")
    @Expose
    private Integer status;

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getParentTicketId() {
        return this.parentTicketId;
    }

    public void setParentTicketId(Integer parentTicketId) {
        this.parentTicketId = parentTicketId;
    }

    public Integer getSchoolIdVisit() {
        return this.schoolIdVisit;
    }

    public void setSchoolIdVisit(Integer schoolIdVisit) {
        this.schoolIdVisit = schoolIdVisit;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getIssueType() {
        return this.issueType;
    }

    public void setIssueType(Integer issueType) {
        this.issueType = issueType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOtherPicture() {
        return this.otherPicture;
    }

    public void setOtherPicture(String otherPicture) {
        this.otherPicture = otherPicture;
    }

    public static String toJson(EmployeeRecordUpdateDatum employeeRecordUpdateDatum) {
        return new Gson().toJson(employeeRecordUpdateDatum, EmployeeRecordUpdateDatum.class);
    }
}
