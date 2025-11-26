package com.micromerger.ssms.utils;

import com.micromerger.ssms.main.MainActivity;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.user.UserBean;
import com.micromerger.ssms.user.beans.dashboard.Data;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.user.beans.employeeData.MonitoringConfigsData;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class CommonObjects {
    public static List<EmployeeData> employeeData = null;
    public static boolean isMAExist = false;
    public static boolean isMock = false;
    public static boolean mAGeofenceCheck = false;
    public static MainActivity mainActivity = null;
    public static EmployeeData monitoring = null;
    public static MonitoringConfigsData monitoringConfigsData = null;
    public static School school = null;
    public static boolean schoolThumbCheck = false;
    public static List<School> schools = null;
    public static String searchedEmployeeId = "";
    public static GPSTracker tracker;
    public static UserBean userObj;
    public static List<Data> DGList = new ArrayList();
    public static List<Data> DDOList = new ArrayList();
    public static List<Data> EDOList = new ArrayList();
    public static List<Data> CMOList = new ArrayList();
    public static List<Data> MAList = new ArrayList();
    public static boolean isSchoolOpen = false;
    public static HashedMap<Integer, KRAData> school_status_fields = new HashedMap<>();
    public static HashedMap<Integer, KRAData> smc_fields = new HashedMap<>();
    public static HashedMap<Integer, KRAData> building_illegal_fields = new HashedMap<>();
    public static HashedMap<Integer, KRAData> attendance_fields = new HashedMap<>();
    public static HashedMap<Integer, KRAData> census = new HashedMap<>();
    public static HashedMap<Integer, KRAData> enrollment_fields = new HashedMap<>();
    public static HashedMap<Integer, KRAData> school_building = new HashedMap<>();
    public static HashedMap<Integer, KRAData> boundary_wall = new HashedMap<>();
    public static HashedMap<Integer, KRAData> drinking_water = new HashedMap<>();
    public static HashedMap<Integer, KRAData> furniture = new HashedMap<>();
    public static HashedMap<Integer, KRAData> washroom = new HashedMap<>();
    public static HashedMap<Integer, KRAData> electricity = new HashedMap<>();
    public static HashedMap<Integer, KRAData> textbooks = new HashedMap<>();
    public static HashedMap<Integer, KRAData> classrooms = new HashedMap<>();
    public static HashedMap<Integer, KRAData> other_facilities = new HashedMap<>();
    public static HashedMap<Integer, KRAData> girls_stipend = new HashedMap<>();
    public static HashedMap<Integer, KRAData> education_quality = new HashedMap<>();
    public static HashedMap<Integer, KRAData> sanctioned_new_expenditure = new HashedMap<>();
    public static HashedMap<Integer, KRAData> flood_survey = new HashedMap<>();

    public enum Roles {
        SEC,
        DG,
        DDO,
        EDO,
        CMO,
        MA
    }
}
