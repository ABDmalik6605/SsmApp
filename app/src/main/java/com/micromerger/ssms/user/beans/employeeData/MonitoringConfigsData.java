package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class MonitoringConfigsData implements Serializable {

    @SerializedName("App_Version")
    @Expose
    private String appVersion;

    @SerializedName("MonitoringBiometricVerification")
    @Expose
    private boolean monitorBiometricVerification;

    @SerializedName("MonitoringGeofenceEnable")
    @Expose
    private boolean monitoringGeofenceEnable;

    @SerializedName("MonitoringGeofenceRadiusMeters")
    @Expose
    private double monitoringGeofenceRadiusMeters;

    @SerializedName("MonitoringMode")
    @Expose
    private String monitoringMode;

    @SerializedName("User_Biometric_Image")
    @Expose
    private String userBiometricImage;

    @SerializedName("User_Biometric_Image_Base64")
    @Expose
    private String userBiometricImageBase64;

    public String getMonitoringMode() {
        return this.monitoringMode;
    }

    public void setMonitoringMode(String monitoringMode) {
        this.monitoringMode = monitoringMode;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public boolean isMonitorBiometricVerification() {
        return this.monitorBiometricVerification;
    }

    public void setMonitorBiometricVerification(boolean monitorBiometricVerification) {
        this.monitorBiometricVerification = monitorBiometricVerification;
    }

    public String getUserBiometricImage() {
        return this.userBiometricImage;
    }

    public void setUserBiometricImage(String userBiometricImage) {
        this.userBiometricImage = userBiometricImage;
    }

    public String getUserBiometricImageBase64() {
        return this.userBiometricImageBase64;
    }

    public void setUserBiometricImageBase64(String userBiometricImageBase64) {
        this.userBiometricImageBase64 = userBiometricImageBase64;
    }

    public boolean getMonitoringGeofenceEnable() {
        return this.monitoringGeofenceEnable;
    }

    public void setMonitoringGeofenceEnable(boolean monitoringGeofenceEnable) {
        this.monitoringGeofenceEnable = monitoringGeofenceEnable;
    }

    public double getMonitoringGeofenceRadiusMeters() {
        return this.monitoringGeofenceRadiusMeters;
    }

    public void setMonitoringGeofenceRadiusMeters(double monitoringGeofenceRadiusMeters) {
        this.monitoringGeofenceRadiusMeters = monitoringGeofenceRadiusMeters;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
