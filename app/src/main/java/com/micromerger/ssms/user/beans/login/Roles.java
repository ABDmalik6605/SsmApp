package com.micromerger.ssms.user.beans.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Roles implements Serializable {
    private static final long serialVersionUID = -7538845610005365197L;

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Role_ID")
    @Expose
    private Integer roleID;

    @SerializedName("RoleName")
    @Expose
    private String roleName;

    public Integer getRoleID() {
        return this.roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
