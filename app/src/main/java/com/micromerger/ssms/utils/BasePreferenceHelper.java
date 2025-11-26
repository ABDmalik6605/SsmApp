package com.micromerger.ssms.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.main.beans.SideMenuResponse;
import com.micromerger.ssms.startmonitoring.bean.AllSchoolsResponse;
import com.micromerger.ssms.user.UserBean;
import com.micromerger.ssms.user.beans.employeeData.MonitoringConfigsResponse;

/* loaded from: classes2.dex */
public class BasePreferenceHelper extends PreferenceHelper {
    private static final String FILENAME = "prefrences";
    protected static final String KEY_APP_FIRST_TIME = "KEY_APP_FIRST_TIME";
    protected static final String KEY_AUTH_TOKEN = "auth_token";
    protected static final String KEY_All_SCHOOLS = "all_schools";
    protected static final String KEY_BACKUP_DATE = "backup_date";
    protected static final String KEY_BACKUP_SIZE = "backup_size";
    protected static final String KEY_CHECK_DATE = "check_date";
    protected static final String KEY_CURRENT_MONITORING_ID = "KEY_CURRENT_MONITORING_ID";
    protected static final String KEY_CURRENT_SCHOOL_ID = "KEY_CURRENT_SCHOOL_ID";
    protected static final String KEY_DATABASE_VERSION = "KEY_DATABASE_VERSION";
    protected static final String KEY_DOWNLOAD_EMPLOYEE_DATA = "download_employee_data";
    protected static final String KEY_DOWNLOAD_EMPLOYEE_IMPRESSIONS = "download_employee_impressions";
    protected static final String KEY_DOWNLOAD_REFERENCE_DATA = "download_reference_data";
    protected static final String KEY_DOWNLOAD_SIDE_MENU_DATA = "download_side_menu";
    protected static final String KEY_EMPLOYEE_DATA = "employee_data";
    protected static final String KEY_EMPLOYEE_UPDATE_DATA_DOWNLOADED = "employee_update_data";
    protected static final String KEY_MONITORING_CONFIGS = "monitoring_configs";
    protected static final String KEY_REFERENCE_DATA = "reference_data";
    protected static final String KEY_RESET_DATA = "reset_data";
    protected static final String KEY_SIDE_MENU = "side_menu";
    protected static final String KEY_USER = "user";
    Context con;
    Gson gson = new Gson();

    public BasePreferenceHelper(Context con) {
        this.con = con;
    }

    public String getDatabaseVersion() {
        return getStringPreference(this.con, FILENAME, KEY_DATABASE_VERSION);
    }

    public void setDatabaseVersion(String databaseVersion) {
        putStringPreference(this.con, FILENAME, KEY_DATABASE_VERSION, databaseVersion);
    }

    public int getCurrentSchoolId() {
        return getIntegerPreference(this.con, FILENAME, KEY_CURRENT_SCHOOL_ID);
    }

    public void setCurrentSchoolId(int currentSchoolId) {
        putIntegerPreference(this.con, FILENAME, KEY_CURRENT_SCHOOL_ID, currentSchoolId);
    }

    public int getCurrentMonitoringId() {
        return getIntegerPreference(this.con, FILENAME, KEY_CURRENT_MONITORING_ID);
    }

    public void setCurrentMonitoringId(int currentMonitoringId) {
        putIntegerPreference(this.con, FILENAME, KEY_CURRENT_MONITORING_ID, currentMonitoringId);
    }

    public UserBean getUser() {
        if (getStringPreference(this.con, FILENAME, KEY_USER) == null) {
            return new UserBean();
        }
        return (UserBean) this.gson.fromJson(getStringPreference(this.con, FILENAME, KEY_USER), UserBean.class);
    }

    public void putUser(UserBean user) {
        putStringPreference(this.con, FILENAME, KEY_USER, this.gson.toJson(user, UserBean.class));
    }

    public void removeUser() {
        removePreference(this.con, FILENAME, KEY_USER);
    }

    public boolean isReferenceDataDownloaded() {
        return getBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_REFERENCE_DATA);
    }

    public void setReferenceDataDownloaded(boolean b) {
        putBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_REFERENCE_DATA, b);
    }

    public boolean isEmployeeImpressionsDownloaded() {
        return getBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_EMPLOYEE_IMPRESSIONS);
    }

    public void setEmployeeImpressionsDownloaded(boolean b) {
        putBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_EMPLOYEE_IMPRESSIONS, b);
    }

    public boolean isSideMenuDownloaded() {
        return getBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_SIDE_MENU_DATA);
    }

    public void setSideMenuDownloaded(boolean b) {
        putBooleanPreference(this.con, FILENAME, KEY_DOWNLOAD_SIDE_MENU_DATA, b);
    }

    public boolean isEmployeeRecordUpdateDataDownloaded() {
        return getBooleanPreference(this.con, FILENAME, KEY_EMPLOYEE_UPDATE_DATA_DOWNLOADED);
    }

    public void setEmployeeRecordUpdateDataDownloaded(boolean b) {
        putBooleanPreference(this.con, FILENAME, KEY_EMPLOYEE_UPDATE_DATA_DOWNLOADED, b);
    }

    public boolean getappFirstTime() {
        return getBooleanPreference(this.con, FILENAME, KEY_APP_FIRST_TIME);
    }

    public void setappFirstTime(boolean b) {
        putBooleanPreference(this.con, FILENAME, KEY_APP_FIRST_TIME, false);
    }

    public SideMenuResponse getSideMenu() {
        if (getStringPreference(this.con, FILENAME, KEY_SIDE_MENU) == null) {
            return new SideMenuResponse();
        }
        return (SideMenuResponse) this.gson.fromJson(getStringPreference(this.con, FILENAME, KEY_SIDE_MENU), SideMenuResponse.class);
    }

    public void putSideMenu(SideMenuResponse sideMenuResponse) {
        putStringPreference(this.con, FILENAME, KEY_SIDE_MENU, this.gson.toJson(sideMenuResponse, SideMenuResponse.class));
    }

    public ReferenceDataResponse getReferenceData() {
        if (getStringPreference(this.con, FILENAME, KEY_REFERENCE_DATA) == null) {
            return new ReferenceDataResponse();
        }
        return (ReferenceDataResponse) this.gson.fromJson(getStringPreference(this.con, FILENAME, KEY_REFERENCE_DATA), ReferenceDataResponse.class);
    }

    public void putReferenceData(ReferenceDataResponse referenceDataResponse) {
        putStringPreference(this.con, FILENAME, KEY_REFERENCE_DATA, this.gson.toJson(referenceDataResponse, ReferenceDataResponse.class));
    }

    public MonitoringConfigsResponse getMonitoringConfigs() {
        if (getStringPreference(this.con, FILENAME, KEY_MONITORING_CONFIGS) == null) {
            return new MonitoringConfigsResponse();
        }
        return (MonitoringConfigsResponse) this.gson.fromJson(getStringPreference(this.con, FILENAME, KEY_MONITORING_CONFIGS), MonitoringConfigsResponse.class);
    }

    public void putMonitoringConfigs(MonitoringConfigsResponse monitoringConfigsResponse) {
        putStringPreference(this.con, FILENAME, KEY_MONITORING_CONFIGS, this.gson.toJson(monitoringConfigsResponse, MonitoringConfigsResponse.class));
    }

    public AllSchoolsResponse getAllSchools() {
        if (getStringPreference(this.con, FILENAME, KEY_All_SCHOOLS) == null) {
            return null;
        }
        return (AllSchoolsResponse) this.gson.fromJson(getStringPreference(this.con, FILENAME, KEY_All_SCHOOLS), AllSchoolsResponse.class);
    }

    public void putAllSchools(AllSchoolsResponse allSchoolsResponse) {
        putStringPreference(this.con, FILENAME, KEY_All_SCHOOLS, this.gson.toJson(allSchoolsResponse, AllSchoolsResponse.class));
    }

    public String getCheckDate() {
        return getStringPreference(this.con, FILENAME, KEY_CHECK_DATE);
    }

    public void setCheckDate(String date) {
        putStringPreference(this.con, FILENAME, KEY_CHECK_DATE, date);
    }

    public String getBackupDate() {
        return getStringPreference(this.con, FILENAME, KEY_BACKUP_DATE);
    }

    public void setBackupDate(String date) {
        putStringPreference(this.con, FILENAME, KEY_BACKUP_DATE, date);
    }

    public String getBackupSize() {
        return getStringPreference(this.con, FILENAME, KEY_BACKUP_SIZE);
    }

    public void setBackupSize(String size) {
        putStringPreference(this.con, FILENAME, KEY_BACKUP_SIZE, size);
    }

    public String getAuthToken() {
        return getStringPreference(this.con, FILENAME, KEY_AUTH_TOKEN);
    }

    public void setAuthToken(String token) {
        putStringPreference(this.con, FILENAME, KEY_AUTH_TOKEN, token);
    }
}
