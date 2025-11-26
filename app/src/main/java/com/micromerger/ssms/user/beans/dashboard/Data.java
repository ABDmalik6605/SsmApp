package com.micromerger.ssms.user.beans.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/* loaded from: classes2.dex */
public class Data implements Serializable {
    private static final long serialVersionUID = 2384686143386218319L;

    @SerializedName("Roles")
    @Expose
    private Roles roles;

    @SerializedName("Users")
    @Expose
    private Users users;

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
