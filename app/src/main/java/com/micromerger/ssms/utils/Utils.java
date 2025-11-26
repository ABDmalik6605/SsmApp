package com.micromerger.ssms.utils;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.user.beans.employeeData.KRADatum;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class Utils {
    public static void skipEnrollmentOfClasses_11_12(final Context context, final int[] classes, final String[] categories) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$Utils$AMS-8O_meJXRWD6AvEBkoDlULkI
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$skipEnrollmentOfClasses_11_12$0(classes, categories, context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$skipEnrollmentOfClasses_11_12$0(int[] iArr, String[] strArr, Context context) {
        HashedMap hashedMap = new HashedMap();
        for (int i : iArr) {
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                KRAData value = entry.getValue();
                if (value.getKRAName().contains("_" + i)) {
                    hashedMap.put(Integer.valueOf(iIntValue), value);
                }
            }
            for (String str : strArr) {
                Iterator it = hashedMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it.next();
                    Integer num = (Integer) entry2.getKey();
                    KRAData kRAData = (KRAData) entry2.getValue();
                    if (kRAData.getKRAName().equals(Constant.Total_Male_Students_ + i + str)) {
                        kRAData.setDataValue(Constant.ECE_Katchi);
                    } else {
                        if (kRAData.getKRAName().equals(Constant.Total_Female_Students_ + i + str)) {
                            kRAData.setDataValue(Constant.ECE_Katchi);
                        } else {
                            if (kRAData.getKRAName().equals(Constant.Total_Students_Enrollment_ + i + str)) {
                                kRAData.setDataValue(Constant.ECE_Katchi);
                            } else {
                                if (kRAData.getKRAName().equals(Constant.Total_Male_Students_Present_ + i + str)) {
                                    kRAData.setDataValue(Constant.ECE_Katchi);
                                } else {
                                    if (kRAData.getKRAName().equals(Constant.Total_Female_Students_Present_ + i + str)) {
                                        kRAData.setDataValue(Constant.ECE_Katchi);
                                    } else {
                                        if (kRAData.getKRAName().equals(Constant.Total_Students_Present_ + i + str)) {
                                            kRAData.setDataValue(Constant.ECE_Katchi);
                                        } else {
                                            if (kRAData.getKRAName().equals(Constant.Students_In_Uniform_ + i + str)) {
                                                kRAData.setDataValue(Constant.ECE_Katchi);
                                            } else {
                                                if (kRAData.getKRAName().equals(Constant.Is_Completed_ + i + str)) {
                                                    kRAData.setDataValue(true);
                                                } else {
                                                    if (kRAData.getKRAName().equalsIgnoreCase(Constant.Lesson_Plan_Available_ + i + str)) {
                                                        kRAData.setDataValue("1");
                                                    } else {
                                                        if (kRAData.getKRAName().equalsIgnoreCase(Constant.Scheme_of_Studies_ + i + str)) {
                                                            kRAData.setDataValue("1");
                                                        } else {
                                                            if (kRAData.getKRAName().equalsIgnoreCase(Constant.Daily_Diary_ + i + str)) {
                                                                kRAData.setDataValue("1");
                                                            } else {
                                                                if (kRAData.getKRAName().equalsIgnoreCase(Constant.Time_Table_ + i + str)) {
                                                                    kRAData.setDataValue("1");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(num.intValue(), kRAData);
                }
            }
        }
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static void skipEnrollmentOfClasses_9_10(final Context context, final int[] classes, final String[] categories) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$Utils$apf66HZ9A-72_BSnjcfFVP5rmMI
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$skipEnrollmentOfClasses_9_10$1(classes, categories, context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$skipEnrollmentOfClasses_9_10$1(int[] iArr, String[] strArr, Context context) {
        HashedMap hashedMap = new HashedMap();
        for (int i : iArr) {
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                KRAData value = entry.getValue();
                if (value.getKRAName().contains("_" + i)) {
                    hashedMap.put(Integer.valueOf(iIntValue), value);
                }
            }
            for (String str : strArr) {
                Iterator it = hashedMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it.next();
                    Integer num = (Integer) entry2.getKey();
                    KRAData kRAData = (KRAData) entry2.getValue();
                    if (kRAData.getKRAName().equals(Constant.Total_Male_Students_ + i + str)) {
                        kRAData.setDataValue(Constant.ECE_Katchi);
                    } else {
                        if (kRAData.getKRAName().equals(Constant.Total_Female_Students_ + i + str)) {
                            kRAData.setDataValue(Constant.ECE_Katchi);
                        } else {
                            if (kRAData.getKRAName().equals(Constant.Total_Students_Enrollment_ + i + str)) {
                                kRAData.setDataValue(Constant.ECE_Katchi);
                            } else {
                                if (kRAData.getKRAName().equals(Constant.Total_Male_Students_Present_ + i + str)) {
                                    kRAData.setDataValue(Constant.ECE_Katchi);
                                } else {
                                    if (kRAData.getKRAName().equals(Constant.Total_Female_Students_Present_ + i + str)) {
                                        kRAData.setDataValue(Constant.ECE_Katchi);
                                    } else {
                                        if (kRAData.getKRAName().equals(Constant.Total_Students_Present_ + i + str)) {
                                            kRAData.setDataValue(Constant.ECE_Katchi);
                                        } else {
                                            if (kRAData.getKRAName().equals(Constant.Students_In_Uniform_ + i + str)) {
                                                kRAData.setDataValue(Constant.ECE_Katchi);
                                            } else {
                                                if (kRAData.getKRAName().equals(Constant.Is_Completed_ + i + str)) {
                                                    kRAData.setDataValue(true);
                                                } else {
                                                    if (kRAData.getKRAName().equalsIgnoreCase(Constant.Lesson_Plan_Available_ + i + str)) {
                                                        kRAData.setDataValue("1");
                                                    } else {
                                                        if (kRAData.getKRAName().equalsIgnoreCase(Constant.Scheme_of_Studies_ + i + str)) {
                                                            kRAData.setDataValue("1");
                                                        } else {
                                                            if (kRAData.getKRAName().equalsIgnoreCase(Constant.Daily_Diary_ + i + str)) {
                                                                kRAData.setDataValue("1");
                                                            } else {
                                                                if (kRAData.getKRAName().equalsIgnoreCase(Constant.Time_Table_ + i + str)) {
                                                                    kRAData.setDataValue("1");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(num.intValue(), kRAData);
                }
            }
        }
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static void skipEnrollmentOfClasses_0_8(final Context context, final int[] classes) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$Utils$0MS5QnZEMvV2V7Dirt-2nUlA3PU
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$skipEnrollmentOfClasses_0_8$2(classes, context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$skipEnrollmentOfClasses_0_8$2(int[] iArr, Context context) {
        HashedMap hashedMap = new HashedMap();
        for (int i : iArr) {
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                KRAData value = entry.getValue();
                if (value.getKRAName().contains("_" + i)) {
                    hashedMap.put(Integer.valueOf(iIntValue), value);
                }
            }
            Iterator it = hashedMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                Integer num = (Integer) entry2.getKey();
                KRAData kRAData = (KRAData) entry2.getValue();
                if (kRAData.getKRAName().equals(Constant.Total_Male_Students_ + i)) {
                    kRAData.setDataValue(Constant.ECE_Katchi);
                } else {
                    if (kRAData.getKRAName().equals(Constant.Total_Female_Students_ + i)) {
                        kRAData.setDataValue(Constant.ECE_Katchi);
                    } else {
                        if (kRAData.getKRAName().equals(Constant.Total_Students_Enrollment_ + i)) {
                            kRAData.setDataValue(Constant.ECE_Katchi);
                        } else {
                            if (kRAData.getKRAName().equals(Constant.Total_Male_Students_Present_ + i)) {
                                kRAData.setDataValue(Constant.ECE_Katchi);
                            } else {
                                if (kRAData.getKRAName().equals(Constant.Total_Female_Students_Present_ + i)) {
                                    kRAData.setDataValue(Constant.ECE_Katchi);
                                } else {
                                    if (kRAData.getKRAName().equals(Constant.Total_Students_Present_ + i)) {
                                        kRAData.setDataValue(Constant.ECE_Katchi);
                                    } else {
                                        if (kRAData.getKRAName().equals(Constant.Students_In_Uniform_ + i)) {
                                            kRAData.setDataValue(Constant.ECE_Katchi);
                                        } else {
                                            if (kRAData.getKRAName().equals(Constant.Is_Completed_ + i)) {
                                                kRAData.setDataValue(true);
                                            } else {
                                                if (kRAData.getKRAName().equalsIgnoreCase(Constant.Lesson_Plan_Available_ + i)) {
                                                    kRAData.setDataValue("1");
                                                } else {
                                                    if (kRAData.getKRAName().equalsIgnoreCase(Constant.Scheme_of_Studies_ + i)) {
                                                        kRAData.setDataValue("1");
                                                    } else {
                                                        if (kRAData.getKRAName().equalsIgnoreCase(Constant.Daily_Diary_ + i)) {
                                                            kRAData.setDataValue("1");
                                                        } else {
                                                            if (kRAData.getKRAName().equalsIgnoreCase(Constant.Time_Table_ + i)) {
                                                                kRAData.setDataValue("1");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(num.intValue(), kRAData);
            }
        }
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static void skipTextBooksOfClasses(final Context context, final int[] classes) {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.utils.-$$Lambda$Utils$4Uc4SVg1Jd0788WY54S9Xetw3F8
            @Override // java.lang.Runnable
            public final void run() {
                Utils.lambda$skipTextBooksOfClasses$3(classes, context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$skipTextBooksOfClasses$3(int[] iArr, Context context) {
        HashedMap hashedMap = new HashedMap();
        for (int i : iArr) {
            for (Map.Entry<Integer, KRAData> entry : CommonObjects.textbooks.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                KRAData value = entry.getValue();
                if (value.getKRAName().contains("_" + i)) {
                    hashedMap.put(Integer.valueOf(iIntValue), value);
                }
            }
            Iterator it = hashedMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                Integer num = (Integer) entry2.getKey();
                KRAData kRAData = (KRAData) entry2.getValue();
                if (kRAData.getKRAName().equals(Constant.TextbooksRequested_ + i)) {
                    kRAData.setDataValue(Constant.ECE_Katchi);
                } else {
                    if (kRAData.getKRAName().equals(Constant.TextbooksReceived_ + i)) {
                        kRAData.setDataValue(Constant.ECE_Katchi);
                    } else {
                        if (kRAData.getKRAName().equals(Constant.TextbooksDistributed_ + i)) {
                            kRAData.setDataValue(Constant.ECE_Katchi);
                        } else {
                            if (kRAData.getKRAName().equals(Constant.StudentsWithTextbooks_ + i)) {
                                kRAData.setDataValue(Constant.ECE_Katchi);
                            } else {
                                if (kRAData.getKRAName().equals(Constant.SurplusTextbooks_ + i)) {
                                    kRAData.setDataValue("");
                                } else {
                                    if (kRAData.getKRAName().equals(Constant.ShortfallTextbooks_ + i)) {
                                        kRAData.setDataValue("");
                                    } else {
                                        if (kRAData.getKRAName().equals(Constant.TimelyDistributed_ + i)) {
                                            kRAData.setDataValue("1");
                                        } else {
                                            if (kRAData.getKRAName().equals(Constant.Comments_ + i)) {
                                                kRAData.setDataValue("");
                                            } else {
                                                if (kRAData.getKRAName().equals(Constant.Is_Completed_ + i)) {
                                                    kRAData.setDataValue(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(num.intValue(), kRAData);
            }
        }
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID().equals(CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static Boolean checkEmployeeImpressionsExistence(Context context) {
        try {
            File file = new File(context.getFilesDir().getPath() + File.separator + "unzipped");
            return Boolean.valueOf(file.exists() && file.length() > 0);
        } catch (Exception e) {
            util.logException(new Exception("checkEmployeeImpressionsExistence: " + e));
            return false;
        }
    }

    public static boolean isMockSettingsON(Context context, Location location) {
        if (Build.VERSION.SDK_INT >= 18) {
            return location.isFromMockProvider();
        }
        return !Settings.Secure.getString(context.getContentResolver(), "mock_location").equals(Constant.ECE_Katchi);
    }

    public static boolean isSameCurrentMonth(String date) {
        try {
            int i = Calendar.getInstance().get(2) + 1;
            Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date2);
            return i == calendar.get(2) + 1;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void clearGirlsStipendData(Context context) {
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.girls_stipend.entrySet()) {
            Integer key = entry.getKey();
            KRAData value = entry.getValue();
            value.setDataValue("");
            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
        }
        for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
            if (Objects.equals(CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID(), CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static void clearEnrollments(Context context) {
        int[] iArr = {104, 105, 106, 107, 108, 109, 111, 112, 329, 330, 322, 323, 324, 325};
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
            Integer key = entry.getKey();
            KRAData value = entry.getValue();
            for (int i = 0; i < 14; i++) {
                if (entry.getValue().getKraid().intValue() == iArr[i]) {
                    value.setDataValue("");
                }
            }
            CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
        }
        for (int i2 = 0; i2 < CommonObjects.employeeData.size(); i2++) {
            if (Objects.equals(CommonObjects.employeeData.get(i2).getMonitoring().get(0).getMonitoringID(), CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                CommonObjects.employeeData.set(i2, CommonObjects.monitoring);
                CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i2).getDbId().intValue(), CommonObjects.monitoring);
                return;
            }
        }
    }

    public static void patchPreviousMonitoringData(Context context, Boolean enrollment) {
        try {
            if (CommonObjects.monitoring != null && CommonObjects.monitoring.getPreviousMonitoring() != null) {
                List<KRADatum> kRAData = CommonObjects.monitoring.getPreviousMonitoring().getKRAData();
                List<KRAData> kRAData2 = CommonObjects.monitoring.getMonitoring().get(0).getKRAData();
                for (KRADatum kRADatum : kRAData) {
                    for (KRAData kRAData3 : kRAData2) {
                        if (enrollment.booleanValue()) {
                            if (Objects.equals(kRADatum.getKraid(), kRAData3.getKraid()) && Objects.equals(kRADatum.getDataGroup(), kRAData3.getDataGroup()) && Objects.equals(kRAData3.getKpiname(), Constant.Enrollment)) {
                                kRAData3.setDataValue(kRADatum.getDataValue());
                            }
                        } else if (Objects.equals(kRADatum.getKraid(), kRAData3.getKraid()) && Objects.equals(kRADatum.getDataGroup(), kRAData3.getDataGroup())) {
                            kRAData3.setDataValue(kRADatum.getDataValue());
                        }
                    }
                }
            }
            for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                if (Objects.equals(CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID(), CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID())) {
                    CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                    CommonActions.getDbHandler(context).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                    return;
                }
            }
        } catch (Exception e) {
            util.logException(new Exception("PreviousMonitoring Patch Exception: " + e));
        }
    }
}
