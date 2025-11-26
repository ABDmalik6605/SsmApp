package com.micromerger.ssms.utils;

import android.content.Context;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;

/* loaded from: classes2.dex */
public class CommonObjectsUtils {
    private final Context context;
    int monitoringId;
    private final BasePreferenceHelper preferenceHelper;
    int schoolId;

    public CommonObjectsUtils() {
        Context appContext = SSMS.getAppContext();
        this.context = appContext;
        this.preferenceHelper = new BasePreferenceHelper(appContext);
    }

    public void checkCommonObjects() {
        this.schoolId = this.preferenceHelper.getCurrentSchoolId();
        this.monitoringId = this.preferenceHelper.getCurrentMonitoringId();
        if (CommonObjects.userObj == null) {
            setCommonUser();
        }
        if (CommonObjects.schools == null) {
            setAllSchools();
        }
        if (CommonObjects.employeeData == null) {
            setEmployeeData();
        }
        if (CommonObjects.school == null && this.schoolId != -1 && this.monitoringId != -1 && CommonObjects.schools != null) {
            setCurrentSchool(this.schoolId, this.monitoringId);
        }
        if (CommonObjects.monitoring != null || this.schoolId == -1 || this.monitoringId == -1 || CommonObjects.employeeData == null) {
            return;
        }
        setCurrentMonitoring(this.schoolId, this.monitoringId);
    }

    public void setCommonUser() {
        CommonObjects.userObj = this.preferenceHelper.getUser();
    }

    public void setAllSchools() {
        AllSchoolsResponse allSchools = this.preferenceHelper.getAllSchools();
        if (allSchools != null) {
            CommonObjects.schools = allSchools.getData();
        }
    }

    public void setCurrentSchool(int schoolId, int monitoringId) {
        CommonObjects.school = getSchoolFromId(schoolId, monitoringId);
    }

    public void setEmployeeData() {
        CommonObjects.employeeData = CommonActions.getDbHandler(this.context).getAllSchoolsData();
    }

    public void setCurrentMonitoring(int schoolId, int monitoringId) {
        CommonObjects.monitoring = getCurrentMonitoringData(schoolId, monitoringId);
    }

    private School getSchoolFromId(int schoolId, int monitoringId) {
        for (School school : CommonObjects.schools) {
            if (school.getSchoolId().intValue() == schoolId && school.getMonitoring_ID() == monitoringId) {
                return school;
            }
        }
        return null;
    }

    private EmployeeData getCurrentMonitoringData(int schoolId, int monitoringId) {
        for (EmployeeData employeeData : CommonObjects.employeeData) {
            if (employeeData.getSchoolId().intValue() == schoolId && employeeData.getMonitoring().get(0).getMonitoringID().intValue() == monitoringId) {
                return employeeData;
            }
        }
        return null;
    }
}
