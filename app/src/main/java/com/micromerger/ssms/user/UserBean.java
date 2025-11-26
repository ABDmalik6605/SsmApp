package com.micromerger.ssms.user;

import com.micromerger.ssms.utils.CommonObjects;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UserBean implements Serializable {
    public String Password;
    public String adress;
    public String email;
    public String imageURL;
    public String isGuest;
    public String isLoggedIn;
    public String isRegisteredUser;
    public String latitude;
    public String locSkip;
    public String longitude;
    public String phoneNumber;
    public CommonObjects.Roles role;
    public String roleId;
    public String searchRadius;
    public String userId;
    public String userName;

    public String toString() {
        return "UserBean{userId='" + this.userId + "'roleId='" + this.roleId + "', Password='" + this.Password + "', userName='" + this.userName + "', email='" + this.email + "', phoneNumber='" + this.phoneNumber + "', adress='" + this.adress + "', isLoggedIn='" + this.isLoggedIn + "', isRegisteredUser='" + this.isRegisteredUser + "', isGuest='" + this.isGuest + "', imageURL='" + this.imageURL + "', searchRadius='" + this.searchRadius + "', latitude='" + this.latitude + "', longitude='" + this.longitude + "', locSkip='" + this.locSkip + "', role='" + this.role + "'}";
    }
}
