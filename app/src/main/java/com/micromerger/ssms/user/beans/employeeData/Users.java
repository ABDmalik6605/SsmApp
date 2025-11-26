package com.micromerger.ssms.user.beans.employeeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Users {

    @SerializedName("BiomeritcEnabled")
    @Expose
    private Boolean biomeritcEnabled;

    @SerializedName("User_ID")
    @Expose
    private Integer userID;

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Boolean getBiomeritcEnabled() {
        return this.biomeritcEnabled;
    }

    public void setBiomeritcEnabled(Boolean biomeritcEnabled) {
        this.biomeritcEnabled = biomeritcEnabled;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
