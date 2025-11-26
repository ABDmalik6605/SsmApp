package com.micromerger.ssms.backnreset.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class RestoreBackupData implements Serializable {
    private static final long serialVersionUID = -6682868096955133912L;

    @SerializedName("ActiveInd")
    @Expose
    private Boolean activeInd;

    @SerializedName("BackupDate")
    @Expose
    private String backupDate;

    @SerializedName("BkDataId")
    @Expose
    private Integer bkDataId;

    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("DataUrl")
    @Expose
    private Object dataUrl;

    @SerializedName("ModifiedBy")
    @Expose
    private Object modifiedBy;

    @SerializedName("ModifiedDate")
    @Expose
    private Object modifiedDate;

    public Integer getBkDataId() {
        return this.bkDataId;
    }

    public void setBkDataId(Integer bkDataId) {
        this.bkDataId = bkDataId;
    }

    public Object getDataUrl() {
        return this.dataUrl;
    }

    public void setDataUrl(Object dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getBackupDate() {
        return this.backupDate;
    }

    public void setBackupDate(String backupDate) {
        this.backupDate = backupDate;
    }

    public Boolean getActiveInd() {
        return this.activeInd;
    }

    public void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
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

    public Object getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Object getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
