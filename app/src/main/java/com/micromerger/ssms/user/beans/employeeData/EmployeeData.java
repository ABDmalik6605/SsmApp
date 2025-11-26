package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.micromerger.ssms.utils.Constant;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class EmployeeData implements Serializable {

    @SerializedName("District_Id")
    @Expose
    private int District_Id;
    private Integer dbId;
    private boolean isSync;

    @SerializedName(Constant.Latitude)
    @Expose
    private String latitude;

    @SerializedName(Constant.Longitude)
    @Expose
    private String longitude;

    @SerializedName("PreviousMonitoring")
    @Expose
    private PreviousMonitoring previousMonitoring;

    @SerializedName("SchoolGenderTypeId")
    @Expose
    private Integer schoolGenderTypeId;

    @SerializedName("SchoolId")
    @Expose
    private Integer schoolId;

    @SerializedName("SchoolLevel")
    @Expose
    private Integer schoolLevel;

    @SerializedName("SchoolName")
    @Expose
    private String schoolName;

    @SerializedName("SchoolPrefix")
    @Expose
    private String schoolPrefix;

    @SerializedName("SchoolSemisCode")
    @Expose
    private String schoolSemisCode;

    @SerializedName("Monitoring")
    @Expose
    private List<Monitoring> monitoring = null;

    @SerializedName("Employees")
    @Expose
    private List<Employee> employees = null;

    public Integer getDbId() {
        return this.dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public boolean isSync() {
        return this.isSync;
    }

    public void setSync(boolean sync) {
        this.isSync = sync;
    }

    public Integer getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolSemisCode() {
        return this.schoolSemisCode;
    }

    public void setSchoolSemisCode(String schoolSemisCode) {
        this.schoolSemisCode = schoolSemisCode;
    }

    public String getSchoolPrefix() {
        return this.schoolPrefix;
    }

    public void setSchoolPrefix(String schoolPrefix) {
        this.schoolPrefix = schoolPrefix;
    }

    public Integer getSchoolLevel() {
        return this.schoolLevel;
    }

    public void setSchoolLevel(Integer schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public Integer getSchoolGenderTypeId() {
        return this.schoolGenderTypeId;
    }

    public void setSchoolGenderTypeId(Integer schoolGenderTypeId) {
        this.schoolGenderTypeId = schoolGenderTypeId;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<Monitoring> getMonitoring() {
        return this.monitoring;
    }

    public void setMonitoring(List<Monitoring> monitoring) {
        this.monitoring = monitoring;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getDistrict_Id() {
        return this.District_Id;
    }

    public void setDistrict_Id(int district_Id) {
        this.District_Id = district_Id;
    }

    public PreviousMonitoring getPreviousMonitoring() {
        return this.previousMonitoring;
    }

    public void setPreviousMonitoring(PreviousMonitoring previousMonitoring) {
        this.previousMonitoring = previousMonitoring;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
