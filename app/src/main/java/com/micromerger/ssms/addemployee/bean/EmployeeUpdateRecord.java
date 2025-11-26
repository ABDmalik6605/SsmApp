package com.micromerger.ssms.addemployee.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.utils.Constant;

/* loaded from: classes2.dex */
public class EmployeeUpdateRecord {

    @SerializedName("Appointment_Order_Picture")
    @Expose
    private String appointmentOrderPicture;

    @SerializedName("Attendance_Register_Picture")
    @Expose
    private String attendanceRegisterPicture;

    @SerializedName("Authority_Order_Picture")
    @Expose
    private String authorityOrderPicture;

    @SerializedName("BasicPayScale")
    @Expose
    private String basicPayScale;

    @SerializedName("CNIC_Picture")
    @Expose
    private String cNICPicture;

    @SerializedName("CNIC")
    @Expose
    private String cnic;

    @SerializedName(Constant.Comments)
    @Expose
    private String comments;

    @SerializedName("Contact_No")
    @Expose
    private String contactNo;

    @SerializedName("Created_By")
    @Expose
    private Integer createdBy;

    @SerializedName("Created_Date")
    @Expose
    private String createdDate;

    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    private String dateTime;

    @SerializedName("Designation")
    @Expose
    private String designation;

    @SerializedName("Employee_Attendance")
    @Expose
    private Integer employeeAttendance;

    @SerializedName("Employee_Code")
    @Expose
    private String employeeCode;

    @SerializedName("Employee_ID")
    @Expose
    private Integer employeeID;

    @SerializedName("Employee_Name")
    @Expose
    private String employeeName;

    @SerializedName("Employee_Payslip_Picture")
    @Expose
    private String employeePayslipPicture;

    @SerializedName("Employee_Picture")
    @Expose
    private String employeePicture;

    @SerializedName("Employee_Record_ID")
    @Expose
    private Integer employeeRecordID;

    @SerializedName("Expired_Information_Letter_Picture")
    @Expose
    private String expiredInformationLetterPicture;

    @SerializedName("Gender")
    @Expose
    private Integer gender;

    @SerializedName("Govt_Letter_Picture")
    @Expose
    private String govtLetterPicture;

    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;

    @SerializedName("Joining_Report_Picture")
    @Expose
    private String joiningReportPicture;

    @SerializedName("Expired_Obituary_Order_Death_Certificate_Picture")
    @Expose
    private String obituaryOrderPicture;

    @SerializedName("Offer_Order_Picture")
    @Expose
    private String offerOrderPicture;

    @SerializedName("Order_Effecting_Date")
    @Expose
    private String orderEffectingDate;

    @SerializedName("Other_Picture")
    @Expose
    private String otherPicture;

    @SerializedName("Parent_Ticket_ID")
    @Expose
    private Integer parentTicketID;

    @SerializedName("Posting_Picture")
    @Expose
    private String postingPicture;

    @SerializedName("Promotion_Order_Picture")
    @Expose
    private String promotionOrderPicture;

    @SerializedName("Reason_For_Update")
    @Expose
    private Integer reasonForUpdate;
    private int recordId;

    @SerializedName("Referred_Letter_Dated")
    @Expose
    private String referredLetterDated;

    @SerializedName("Referred_Letter_Number")
    @Expose
    private String referredLetterNumber;

    @SerializedName("Reopen_Ticket")
    @Expose
    private Boolean reopenTicket;

    @SerializedName("Reply_From")
    @Expose
    private String replyFrom;

    @SerializedName("Retirement_Information_Letter_Picture")
    @Expose
    private String retirementInformationLetterPicture;

    @SerializedName("Retirement_Order_Picture")
    @Expose
    private String retirementOrderPicture;

    @SerializedName("School_ID_Visit")
    @Expose
    private Integer schoolIDVisit;

    @SerializedName("School_SEMIS_Code_Visit")
    @Expose
    private String schoolSEMISCodeVisit;

    @SerializedName("Status")
    @Expose
    private Integer status;

    @SerializedName("Transfer_Order_Picture")
    @Expose
    private String transferOrderPicture;

    @SerializedName("User_ID")
    @Expose
    private Integer userID;

    @SerializedName("Verbal_Option")
    @Expose
    private Integer verbalOption;

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getEmployeeRecordID() {
        return this.employeeRecordID;
    }

    public void setEmployeeRecordID(Integer employeeRecordID) {
        this.employeeRecordID = employeeRecordID;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getSchoolIDVisit() {
        return this.schoolIDVisit;
    }

    public void setSchoolIDVisit(Integer schoolIDVisit) {
        this.schoolIDVisit = schoolIDVisit;
    }

    public String getSchoolSEMISCodeVisit() {
        return this.schoolSEMISCodeVisit;
    }

    public void setSchoolSEMISCodeVisit(String schoolSEMISCodeVisit) {
        this.schoolSEMISCodeVisit = schoolSEMISCodeVisit;
    }

    public Integer getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
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

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBasicPayScale() {
        return this.basicPayScale;
    }

    public void setBasicPayScale(String basicPayScale) {
        this.basicPayScale = basicPayScale;
    }

    public Integer getReasonForUpdate() {
        return this.reasonForUpdate;
    }

    public void setReasonForUpdate(Integer reasonForUpdate) {
        this.reasonForUpdate = reasonForUpdate;
    }

    public String getEmployeePicture() {
        return this.employeePicture;
    }

    public void setEmployeePicture(String employeePicture) {
        this.employeePicture = employeePicture;
    }

    public String getcNICPicture() {
        return this.cNICPicture;
    }

    public void setcNICPicture(String cNICPicture) {
        this.cNICPicture = cNICPicture;
    }

    public String getGovtLetterPicture() {
        return this.govtLetterPicture;
    }

    public void setGovtLetterPicture(String govtLetterPicture) {
        this.govtLetterPicture = govtLetterPicture;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOtherPicture() {
        return this.otherPicture;
    }

    public void setOtherPicture(String otherPicture) {
        this.otherPicture = otherPicture;
    }

    public String getPostingPicture() {
        return this.postingPicture;
    }

    public void setPostingPicture(String postingPicture) {
        this.postingPicture = postingPicture;
    }

    public String getAppointmentOrderPicture() {
        return this.appointmentOrderPicture;
    }

    public void setAppointmentOrderPicture(String appointmentOrderPicture) {
        this.appointmentOrderPicture = appointmentOrderPicture;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getVerbalOption() {
        return this.verbalOption;
    }

    public void setVerbalOption(Integer verbalOption) {
        this.verbalOption = verbalOption;
    }

    public Integer getEmployeeAttendance() {
        return this.employeeAttendance;
    }

    public void setEmployeeAttendance(Integer employeeAttendance) {
        this.employeeAttendance = employeeAttendance;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getReopenTicket() {
        return this.reopenTicket;
    }

    public void setReopenTicket(Boolean reopenTicket) {
        this.reopenTicket = reopenTicket;
    }

    public String getReplyFrom() {
        return this.replyFrom;
    }

    public void setReplyFrom(String replyFrom) {
        this.replyFrom = replyFrom;
    }

    public Integer getParentTicketID() {
        return this.parentTicketID;
    }

    public void setParentTicketID(Integer parentTicketID) {
        this.parentTicketID = parentTicketID;
    }

    public String getEmployeePayslipPicture() {
        return this.employeePayslipPicture;
    }

    public void setEmployeePayslipPicture(String employeePayslipPicture) {
        this.employeePayslipPicture = employeePayslipPicture;
    }

    public String getOfferOrderPicture() {
        return this.offerOrderPicture;
    }

    public void setOfferOrderPicture(String offerOrderPicture) {
        this.offerOrderPicture = offerOrderPicture;
    }

    public String getJoiningReportPicture() {
        return this.joiningReportPicture;
    }

    public void setJoiningReportPicture(String joiningReportPicture) {
        this.joiningReportPicture = joiningReportPicture;
    }

    public String getAttendanceRegisterPicture() {
        return this.attendanceRegisterPicture;
    }

    public void setAttendanceRegisterPicture(String attendanceRegisterPicture) {
        this.attendanceRegisterPicture = attendanceRegisterPicture;
    }

    public String getTransferOrderPicture() {
        return this.transferOrderPicture;
    }

    public void setTransferOrderPicture(String transferOrderPicture) {
        this.transferOrderPicture = transferOrderPicture;
    }

    public String getPromotionOrderPicture() {
        return this.promotionOrderPicture;
    }

    public void setPromotionOrderPicture(String promotionOrderPicture) {
        this.promotionOrderPicture = promotionOrderPicture;
    }

    public String getObituaryOrderPicture() {
        return this.obituaryOrderPicture;
    }

    public void setObituaryOrderPicture(String obituaryOrderPicture) {
        this.obituaryOrderPicture = obituaryOrderPicture;
    }

    public String getExpiredInformationLetterPicture() {
        return this.expiredInformationLetterPicture;
    }

    public void setExpiredInformationLetterPicture(String expiredInformationLetterPicture) {
        this.expiredInformationLetterPicture = expiredInformationLetterPicture;
    }

    public String getAuthorityOrderPicture() {
        return this.authorityOrderPicture;
    }

    public void setAuthorityOrderPicture(String authorityOrderPicture) {
        this.authorityOrderPicture = authorityOrderPicture;
    }

    public String getRetirementOrderPicture() {
        return this.retirementOrderPicture;
    }

    public void setRetirementOrderPicture(String retirementOrderPicture) {
        this.retirementOrderPicture = retirementOrderPicture;
    }

    public String getRetirementInformationLetterPicture() {
        return this.retirementInformationLetterPicture;
    }

    public void setRetirementInformationLetterPicture(String retirementInformationLetterPicture) {
        this.retirementInformationLetterPicture = retirementInformationLetterPicture;
    }

    public String getReferredLetterNumber() {
        return this.referredLetterNumber;
    }

    public void setReferredLetterNumber(String referredLetterNumber) {
        this.referredLetterNumber = referredLetterNumber;
    }

    public String getReferredLetterDated() {
        return this.referredLetterDated;
    }

    public void setReferredLetterDated(String referredLetterDated) {
        this.referredLetterDated = referredLetterDated;
    }

    public String getOrderEffectingDate() {
        return this.orderEffectingDate;
    }

    public void setOrderEffectingDate(String orderEffectingDate) {
        this.orderEffectingDate = orderEffectingDate;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String toString() {
        return "EmployeeUpdateRecord{recordId=" + this.recordId + ", employeeRecordID=" + this.employeeRecordID + ", userID=" + this.userID + ", schoolIDVisit=" + this.schoolIDVisit + ", schoolSEMISCodeVisit='" + this.schoolSEMISCodeVisit + "', employeeID=" + this.employeeID + ", employeeCode='" + this.employeeCode + "', employeeName='" + this.employeeName + "', cnic='" + this.cnic + "', gender=" + this.gender + ", contactNo='" + this.contactNo + "', dateOfBirth='" + this.dateOfBirth + "', joiningDate='" + this.joiningDate + "', designation='" + this.designation + "', basicPayScale='" + this.basicPayScale + "', reasonForUpdate=" + this.reasonForUpdate + ", employeePicture='" + this.employeePicture + "', cNICPicture='" + this.cNICPicture + "', govtLetterPicture='" + this.govtLetterPicture + "', createdBy=" + this.createdBy + ", createdDate='" + this.createdDate + "', otherPicture='" + this.otherPicture + "', postingPicture='" + this.postingPicture + "', appointmentOrderPicture='" + this.appointmentOrderPicture + "', comments='" + this.comments + "', verbalOption=" + this.verbalOption + ", employeeAttendance=" + this.employeeAttendance + ", status=" + this.status + ", reopenTicket=" + this.reopenTicket + ", replyFrom='" + this.replyFrom + "', parentTicketID=" + this.parentTicketID + ", employeePayslipPicture='" + this.employeePayslipPicture + "', offerOrderPicture='" + this.offerOrderPicture + "', joiningReportPicture='" + this.joiningReportPicture + "', attendanceRegisterPicture='" + this.attendanceRegisterPicture + "', transferOrderPicture='" + this.transferOrderPicture + "', promotionOrderPicture='" + this.promotionOrderPicture + "', obituaryOrderPicture='" + this.obituaryOrderPicture + "', expiredInformationLetterPicture='" + this.expiredInformationLetterPicture + "', authorityOrderPicture='" + this.authorityOrderPicture + "', retirementOrderPicture='" + this.retirementOrderPicture + "', retirementInformationLetterPicture='" + this.retirementInformationLetterPicture + "', referredLetterNumber='" + this.referredLetterNumber + "', referredLetterDated='" + this.referredLetterDated + "', orderEffectingDate='" + this.orderEffectingDate + "', dateTime='" + this.dateTime + "'}";
    }
}
