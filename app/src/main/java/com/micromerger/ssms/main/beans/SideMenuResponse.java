package com.micromerger.ssms.main.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class SideMenuResponse {

    @SerializedName("Data")
    @Expose
    private List<SideMenu> data = null;

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

    public List<SideMenu> getData() {
        return this.data;
    }

    public void setData(List<SideMenu> data) {
        this.data = data;
    }

    public class SideMenu {

        @SerializedName("MenuIconUrl")
        @Expose
        private String menuIconUrl;

        @SerializedName("MenuItemName")
        @Expose
        private String menuItemName;

        public SideMenu() {
        }

        public String getMenuItemName() {
            return this.menuItemName;
        }

        public void setMenuItemName(String menuItemName) {
            this.menuItemName = menuItemName;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

        public String getMenuIconUrl() {
            return this.menuIconUrl;
        }

        public void setMenuIconUrl(String menuIconUrl) {
            this.menuIconUrl = menuIconUrl;
        }
    }
}
