package com.micromerger.ssms.backnreset.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class RestoreBackupResponse implements Serializable {
    private static final long serialVersionUID = 5420998958903865272L;

    @SerializedName("Data")
    @Expose
    private RestoreBackupData data;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Status")
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestoreBackupData getData() {
        return this.data;
    }

    public void setData(RestoreBackupData data) {
        this.data = data;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
