package com.micromerger.ssms.main.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class ReferenceDataResponse implements Serializable {
    private static final long serialVersionUID = 6444781032364798209L;

    @SerializedName("Data")
    @Expose
    private List<ReferenceData> data = new ArrayList();

    @SerializedName("datetime")
    @Expose
    private String datetime;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Status")
    @Expose
    private Boolean status;

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

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

    public List<ReferenceData> getData() {
        return this.data;
    }

    public void setData(List<ReferenceData> data) {
        this.data = data;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public class ReferenceData implements Serializable {
        private static final long serialVersionUID = -8784251538271635243L;

        @SerializedName("Referencecode")
        @Expose
        private String referencecode;

        @SerializedName("Referencedataid")
        @Expose
        private Integer referencedataid;

        @SerializedName("Referencekey")
        @Expose
        private Integer referencekey;

        @SerializedName("Referencevalue")
        @Expose
        private String referencevalue = "";

        @SerializedName("SortOrder")
        @Expose
        private int sortOrder;

        public ReferenceData() {
        }

        public Integer getReferencedataid() {
            return this.referencedataid;
        }

        public void setReferencedataid(Integer referencedataid) {
            this.referencedataid = referencedataid;
        }

        public String getReferencecode() {
            return this.referencecode;
        }

        public void setReferencecode(String referencecode) {
            this.referencecode = referencecode;
        }

        public Integer getReferencekey() {
            return this.referencekey;
        }

        public void setReferencekey(Integer referencekey) {
            this.referencekey = referencekey;
        }

        public String getReferencevalue() {
            return this.referencevalue;
        }

        public void setReferencevalue(String referencevalue) {
            this.referencevalue = referencevalue;
        }

        public int getSortOrder() {
            return this.sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
