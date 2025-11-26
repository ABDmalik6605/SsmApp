package com.micromerger.ssms.user.beans.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Users implements Serializable {
    private static final long serialVersionUID = -8177265165949656904L;

    @SerializedName("CNIC")
    @Expose
    private String cNIC;

    @SerializedName("Email_Address")
    @Expose
    private String emailAddress;

    @SerializedName("LATITUDE")
    @Expose
    private String latitude;

    @SerializedName("LONGITUDE")
    @Expose
    private String longitude;

    @SerializedName("Mobile_No")
    @Expose
    private String mobileNo;

    @SerializedName("User_ID")
    @Expose
    private Integer userID;

    @SerializedName("User_Image_URL")
    @Expose
    private String userImageURL;

    @SerializedName("User_Name")
    @Expose
    private String userName;

    @SerializedName("User_Type")
    @Expose
    private Integer userType;

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCNIC() {
        return this.cNIC;
    }

    public void setCNIC(String cNIC) {
        this.cNIC = cNIC;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserImageURL() {
        return this.userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
