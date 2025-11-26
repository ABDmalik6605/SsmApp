package com.micromerger.ssms.utils;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.micromerger.ssms.addemployee.EmployeeDbRecord;
import com.micromerger.ssms.addemployee.bean.AddStaffTicketing;
import com.micromerger.ssms.addemployee.bean.EmployeeUpdateRecord;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.model.SchoolMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteStatement;

/* loaded from: classes2.dex */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SchoolMonitoring.db";
    public static final int DATABASE_VERSION = 9;
    private static final String KEY_ID = "id";
    private static final String KEY_KEY = "key_name";
    private static final String KEY_SCHOOL = "school";
    private static final String KEY_SCHOOL_ID = "school_id";
    private static final String KEY_UPDATED_SCHOOL = "updated_school";
    private static final String KEY_VALUE = "value_name";
    private static final String RECORD_ALREADY_SYNC = "already_sync";
    private static final String RECORD_APPOINTMENT_ORDER_PICTURE = "appointment_order_picture";
    private static final String RECORD_ATTENDANCE_REGISTER_PICTURE = "attendance_register_picture";
    private static final String RECORD_CNIC = "cnic";
    private static final String RECORD_CNIC_PICTURE = "cnic_picture";
    private static final String RECORD_DATE_TIME = "date_time";
    private static final String RECORD_EMPLOYEE_ID = "employee_id";
    private static final String RECORD_EMPLOYEE_PAYSLIP = "employee_payslip";
    private static final String RECORD_EMPLOYEE_PICTURE = "employee_picture";
    private static final String RECORD_EMPLOYEE_RECORD_UPDATE_JSON = "employee_record_update_json";
    private static final String RECORD_GOVT_LETTER_PICTURE = "govt_letter_picture";
    private static final String RECORD_ID = "record_id";
    private static final String RECORD_INFORMATION_LETTER = "information_letter";
    private static final String RECORD_JOINING_REPORT = "joining_report";
    private static final String RECORD_NAME = "name";
    private static final String RECORD_OFFER_ORDER = "offer_order";
    private static final String RECORD_OTHER_PICTURE = "other_picture";
    private static final String RECORD_POSTING_ORDER_PICTURE = "posting_order_picture";
    private static final String RECORD_REASON = "reason";
    private static final String RECORD_SYNC_STATUS = "sync_status";
    private static final String TABLE_ADD_STAFF_TICKETING = "addStaffTicketing";
    private static final String TABLE_EMPLOYEE_DATA = "employee_school_data";
    private static final String TABLE_EMPLOYEE_RECORDS = "employee_records";
    private static final String TABLE_SCHOOL_META_DATA = "school_meta_data";
    private static final String TICKET_CNIC = "cnic";
    private static final String TICKET_DATA = "data";
    private static final String TICKET_DATE_TIME = "date_time";
    private static final String TICKET_ID = "ticket_id";
    private static final String TICKET_REASON = "reason";
    private final String databasePassword;
    Context mContext;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 9);
        this.databasePassword = "$eld2O22!MmdB";
        this.mContext = context;
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL("CREATE TABLE employee_school_data(id INTEGER PRIMARY KEY,school TEXT,updated_school TEXT)");
        db.execSQL("CREATE TABLE school_meta_data(id INTEGER PRIMARY KEY,school_id INTEGER,key_name TEXT,value_name TEXT)");
        db.execSQL("CREATE TABLE employee_records(record_id INTEGER PRIMARY KEY,name TEXT,cnic TEXT,employee_id TEXT,reason TEXT,employee_record_update_json TEXT,employee_picture TEXT,cnic_picture TEXT,govt_letter_picture TEXT,appointment_order_picture TEXT,posting_order_picture TEXT,other_picture TEXT,employee_payslip TEXT,offer_order TEXT,joining_report TEXT,attendance_register_picture TEXT,information_letter TEXT,date_time TEXT,already_sync INTEGER,sync_status INTEGER)");
        db.execSQL("CREATE TABLE addStaffTicketing(ticket_id INTEGER PRIMARY KEY AUTOINCREMENT,cnic TEXT,reason TEXT,date_time TEXT,data TEXT)");
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        db.execSQL("DROP TABLE IF EXISTS employee_school_data");
        db.execSQL("DROP TABLE IF EXISTS school_meta_data");
        db.execSQL("DROP TABLE IF EXISTS employee_records");
        db.execSQL("DROP TABLE IF EXISTS employee_records");
        onCreate(db);
    }

    public void openOrCreateDatabase(Context context) {
        SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DATABASE_NAME).getAbsolutePath(), "$eld2O22!MmdB", (SQLiteDatabase.CursorFactory) null);
    }

    public void addEmployeeRecord(EmployeeDbRecord employeeDbRecord) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_ID, Integer.valueOf(employeeDbRecord.getRecordId()));
        contentValues.put("name", employeeDbRecord.getName());
        contentValues.put("cnic", employeeDbRecord.getCnic());
        contentValues.put(RECORD_EMPLOYEE_ID, employeeDbRecord.getEmployeeId());
        contentValues.put("reason", employeeDbRecord.getReason());
        contentValues.put(RECORD_EMPLOYEE_RECORD_UPDATE_JSON, new Gson().toJson(employeeDbRecord.getAddEmployeeRecord(), EmployeeUpdateRecord.class));
        contentValues.put(RECORD_EMPLOYEE_PICTURE, employeeDbRecord.getEmployeePicturePath());
        contentValues.put(RECORD_CNIC_PICTURE, employeeDbRecord.getCnicPicturePath());
        contentValues.put(RECORD_GOVT_LETTER_PICTURE, employeeDbRecord.getGovtLetterPicturePath());
        contentValues.put(RECORD_APPOINTMENT_ORDER_PICTURE, employeeDbRecord.getReasonOrderPicturePath());
        contentValues.put(RECORD_POSTING_ORDER_PICTURE, employeeDbRecord.getPostingOrderPicturePath());
        contentValues.put(RECORD_OTHER_PICTURE, employeeDbRecord.getOtherPicturePath());
        contentValues.put(RECORD_EMPLOYEE_PAYSLIP, employeeDbRecord.getEmployeePayslipPicturePath());
        contentValues.put(RECORD_OFFER_ORDER, employeeDbRecord.getOfferOrderPicturePath());
        contentValues.put(RECORD_JOINING_REPORT, employeeDbRecord.getJoiningReportPicturePath());
        contentValues.put(RECORD_ATTENDANCE_REGISTER_PICTURE, employeeDbRecord.getAttendanceRegisterPicturePath());
        contentValues.put(RECORD_INFORMATION_LETTER, employeeDbRecord.getInformationLetterPicturePath());
        contentValues.put("date_time", employeeDbRecord.getDateTime());
        contentValues.put(RECORD_SYNC_STATUS, Integer.valueOf(employeeDbRecord.getSyncStatus()));
        contentValues.put(RECORD_ALREADY_SYNC, Integer.valueOf(employeeDbRecord.getAlreadySynced()));
        writableDatabase.insert(TABLE_EMPLOYEE_RECORDS, (String) null, contentValues);
    }

    public EmployeeDbRecord getEmployeeDbRecord(int recordId) {
        Cursor cursorRawQuery = getReadableDatabase("$eld2O22!MmdB").rawQuery("SELECT * FROM employee_records WHERE record_id = " + recordId, (String[]) null);
        if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0) {
            return null;
        }
        cursorRawQuery.moveToFirst();
        EmployeeDbRecord employeeDbRecord = new EmployeeDbRecord();
        employeeDbRecord.setRecordId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ID)));
        employeeDbRecord.setAddEmployeeRecord((EmployeeUpdateRecord) new Gson().fromJson(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_RECORD_UPDATE_JSON)), EmployeeUpdateRecord.class));
        employeeDbRecord.setName(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("name")));
        employeeDbRecord.setCnic(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("cnic")));
        employeeDbRecord.setEmployeeId(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_ID)));
        employeeDbRecord.setReason(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("reason")));
        employeeDbRecord.setEmployeePicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PICTURE)));
        employeeDbRecord.setCnicPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_CNIC_PICTURE)));
        employeeDbRecord.setGovtLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_GOVT_LETTER_PICTURE)));
        employeeDbRecord.setReasonOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_APPOINTMENT_ORDER_PICTURE)));
        employeeDbRecord.setPostingOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_POSTING_ORDER_PICTURE)));
        employeeDbRecord.setOtherPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OTHER_PICTURE)));
        employeeDbRecord.setEmployeePayslipPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PAYSLIP)));
        employeeDbRecord.setOfferOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OFFER_ORDER)));
        employeeDbRecord.setJoiningReportPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_JOINING_REPORT)));
        employeeDbRecord.setAttendanceRegisterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_ATTENDANCE_REGISTER_PICTURE)));
        employeeDbRecord.setInformationLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_INFORMATION_LETTER)));
        employeeDbRecord.setDateTime(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("date_time")));
        employeeDbRecord.setSyncStatus(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_SYNC_STATUS)));
        employeeDbRecord.setAlreadySynced(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ALREADY_SYNC)));
        cursorRawQuery.close();
        return employeeDbRecord;
    }

    public List<EmployeeDbRecord> getEmployeeRecords(boolean notSynced) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getWritableDatabase("$eld2O22!MmdB").rawQuery(notSynced ? "SELECT * FROM employee_records WHERE sync_status=0" : "SELECT * FROM employee_records", (String[]) null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.isBeforeFirst() && cursorRawQuery.moveToFirst()) {
            do {
                try {
                    EmployeeDbRecord employeeDbRecord = new EmployeeDbRecord();
                    employeeDbRecord.setRecordId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ID)));
                    employeeDbRecord.setAddEmployeeRecord((EmployeeUpdateRecord) new Gson().fromJson(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_RECORD_UPDATE_JSON)), EmployeeUpdateRecord.class));
                    employeeDbRecord.setName(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("name")));
                    employeeDbRecord.setCnic(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("cnic")));
                    employeeDbRecord.setEmployeeId(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_ID)));
                    employeeDbRecord.setReason(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("reason")));
                    employeeDbRecord.setEmployeePicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PICTURE)));
                    employeeDbRecord.setCnicPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_CNIC_PICTURE)));
                    employeeDbRecord.setGovtLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_GOVT_LETTER_PICTURE)));
                    employeeDbRecord.setReasonOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_APPOINTMENT_ORDER_PICTURE)));
                    employeeDbRecord.setPostingOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_POSTING_ORDER_PICTURE)));
                    employeeDbRecord.setOtherPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OTHER_PICTURE)));
                    employeeDbRecord.setEmployeePayslipPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PAYSLIP)));
                    employeeDbRecord.setOfferOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OFFER_ORDER)));
                    employeeDbRecord.setJoiningReportPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_JOINING_REPORT)));
                    employeeDbRecord.setAttendanceRegisterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_ATTENDANCE_REGISTER_PICTURE)));
                    employeeDbRecord.setInformationLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_INFORMATION_LETTER)));
                    employeeDbRecord.setDateTime(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("date_time")));
                    employeeDbRecord.setSyncStatus(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_SYNC_STATUS)));
                    employeeDbRecord.setAlreadySynced(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ALREADY_SYNC)));
                    arrayList.add(employeeDbRecord);
                } catch (Exception e) {
                    e.printStackTrace();
                    util.logException(e);
                }
            } while (cursorRawQuery.moveToNext());
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return arrayList;
    }

    public List<EmployeeDbRecord> getEmployeeRecordsFromCNIC(String cnic, String reason) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getWritableDatabase("$eld2O22!MmdB").rawQuery("SELECT * FROM employee_records WHERE cnic = " + cnic + " AND reason = \"" + reason + "\"", (String[]) null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.isBeforeFirst() && cursorRawQuery.moveToFirst()) {
            do {
                try {
                    EmployeeDbRecord employeeDbRecord = new EmployeeDbRecord();
                    employeeDbRecord.setRecordId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ID)));
                    employeeDbRecord.setAddEmployeeRecord((EmployeeUpdateRecord) new Gson().fromJson(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_RECORD_UPDATE_JSON)), EmployeeUpdateRecord.class));
                    employeeDbRecord.setName(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("name")));
                    employeeDbRecord.setCnic(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("cnic")));
                    employeeDbRecord.setEmployeeId(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_ID)));
                    employeeDbRecord.setReason(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("reason")));
                    employeeDbRecord.setEmployeePicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PICTURE)));
                    employeeDbRecord.setCnicPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_CNIC_PICTURE)));
                    employeeDbRecord.setGovtLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_GOVT_LETTER_PICTURE)));
                    employeeDbRecord.setReasonOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_APPOINTMENT_ORDER_PICTURE)));
                    employeeDbRecord.setPostingOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_POSTING_ORDER_PICTURE)));
                    employeeDbRecord.setOtherPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OTHER_PICTURE)));
                    employeeDbRecord.setEmployeePayslipPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_EMPLOYEE_PAYSLIP)));
                    employeeDbRecord.setOfferOrderPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_OFFER_ORDER)));
                    employeeDbRecord.setJoiningReportPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_JOINING_REPORT)));
                    employeeDbRecord.setAttendanceRegisterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_ATTENDANCE_REGISTER_PICTURE)));
                    employeeDbRecord.setInformationLetterPicturePath(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(RECORD_INFORMATION_LETTER)));
                    employeeDbRecord.setDateTime(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("date_time")));
                    employeeDbRecord.setSyncStatus(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_SYNC_STATUS)));
                    employeeDbRecord.setAlreadySynced(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(RECORD_ALREADY_SYNC)));
                    arrayList.add(employeeDbRecord);
                } catch (Exception e) {
                    e.printStackTrace();
                    util.logException(e);
                }
            } while (cursorRawQuery.moveToNext());
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return arrayList;
    }

    public void deleteEmployeeRecord(Integer recordId) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        if (writableDatabase.isOpen()) {
            writableDatabase.delete(TABLE_EMPLOYEE_RECORDS, "record_id = ?", new String[]{String.valueOf(recordId)});
        }
    }

    public void updateEmployeeRecord(EmployeeDbRecord employeeDbRecord) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_EMPLOYEE_RECORD_UPDATE_JSON, new Gson().toJson(employeeDbRecord.getAddEmployeeRecord()));
        contentValues.put("name", employeeDbRecord.getName());
        contentValues.put("cnic", employeeDbRecord.getCnic());
        contentValues.put(RECORD_EMPLOYEE_ID, employeeDbRecord.getEmployeeId());
        contentValues.put(RECORD_EMPLOYEE_ID, employeeDbRecord.getEmployeeId());
        contentValues.put("reason", employeeDbRecord.getReason());
        contentValues.put(RECORD_EMPLOYEE_PICTURE, employeeDbRecord.getEmployeePicturePath());
        contentValues.put(RECORD_CNIC_PICTURE, employeeDbRecord.getCnicPicturePath());
        contentValues.put(RECORD_GOVT_LETTER_PICTURE, employeeDbRecord.getGovtLetterPicturePath());
        contentValues.put(RECORD_APPOINTMENT_ORDER_PICTURE, employeeDbRecord.getReasonOrderPicturePath());
        contentValues.put(RECORD_POSTING_ORDER_PICTURE, employeeDbRecord.getPostingOrderPicturePath());
        contentValues.put(RECORD_OTHER_PICTURE, employeeDbRecord.getOtherPicturePath());
        contentValues.put(RECORD_EMPLOYEE_PAYSLIP, employeeDbRecord.getEmployeePayslipPicturePath());
        contentValues.put(RECORD_OFFER_ORDER, employeeDbRecord.getOfferOrderPicturePath());
        contentValues.put(RECORD_JOINING_REPORT, employeeDbRecord.getJoiningReportPicturePath());
        contentValues.put(RECORD_ATTENDANCE_REGISTER_PICTURE, employeeDbRecord.getAttendanceRegisterPicturePath());
        contentValues.put(RECORD_INFORMATION_LETTER, employeeDbRecord.getInformationLetterPicturePath());
        contentValues.put("date_time", employeeDbRecord.getDateTime());
        contentValues.put(RECORD_SYNC_STATUS, Integer.valueOf(employeeDbRecord.getSyncStatus()));
        contentValues.put(RECORD_ALREADY_SYNC, Integer.valueOf(employeeDbRecord.getAlreadySynced()));
        if (writableDatabase.isOpen()) {
            System.out.println(writableDatabase.update(TABLE_EMPLOYEE_RECORDS, contentValues, "record_id = ?", new String[]{String.valueOf(employeeDbRecord.getRecordId())}));
        }
    }

    public void addSchoolMetaData(SchoolMetaData schoolMetaData) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SCHOOL_ID, Integer.valueOf(schoolMetaData.schoolId));
        contentValues.put(KEY_KEY, schoolMetaData.key_name);
        contentValues.put(KEY_VALUE, schoolMetaData.value_name);
        writableDatabase.insert(TABLE_SCHOOL_META_DATA, (String) null, contentValues);
    }

    public SchoolMetaData getSchoolMetaData(int schoolId, String key) {
        Cursor cursorRawQuery = getReadableDatabase("$eld2O22!MmdB").rawQuery("SELECT * FROM school_meta_data WHERE school_id = " + schoolId + " AND " + KEY_KEY + " = " + key, (String[]) null);
        if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0) {
            return null;
        }
        cursorRawQuery.moveToFirst();
        SchoolMetaData schoolMetaData = new SchoolMetaData();
        schoolMetaData.setId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(KEY_ID)));
        schoolMetaData.setSchoolId(cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(KEY_SCHOOL_ID)));
        schoolMetaData.setKeyName(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_KEY)));
        schoolMetaData.setValueName(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_VALUE)));
        cursorRawQuery.close();
        return schoolMetaData;
    }

    public void addSchoolData(List<EmployeeData> employeeData) throws SQLException {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        writableDatabase.beginTransaction();
        SQLiteStatement sQLiteStatementCompileStatement = writableDatabase.compileStatement("INSERT INTO employee_school_data(id,school,updated_school) VALUES (?,?,?)");
        for (int i = 0; i < employeeData.size(); i++) {
            sQLiteStatementCompileStatement.bindString(1, i + "");
            employeeData.get(i).setDbId(Integer.valueOf(i));
            sQLiteStatementCompileStatement.bindString(2, new Gson().toJson(employeeData.get(i), EmployeeData.class));
            sQLiteStatementCompileStatement.bindString(3, new Gson().toJson(employeeData.get(i), EmployeeData.class));
            sQLiteStatementCompileStatement.execute();
            sQLiteStatementCompileStatement.clearBindings();
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        writableDatabase.close();
    }

    public EmployeeData addSchoolData(EmployeeData employeeData) {
        int count;
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT  id FROM employee_school_data ORDER BY id DESC LIMIT 1", (String[]) null);
        if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0 || !cursorRawQuery.moveToFirst()) {
            count = -1;
        } else {
            try {
                count = cursorRawQuery.getInt(0);
            } catch (Exception e) {
                e.printStackTrace();
                util.logException(e);
                count = cursorRawQuery.getCount() + 1;
            }
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        Log.e("Add Employess dbID :", count + "_");
        if (count > -1) {
            ContentValues contentValues = new ContentValues();
            int i = count + 1;
            employeeData.setDbId(Integer.valueOf(i));
            contentValues.put(KEY_ID, Integer.valueOf(i));
            contentValues.put(KEY_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
            contentValues.put(KEY_UPDATED_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
            Log.e("Add Emp Inserting.. ", contentValues.toString());
            writableDatabase.insert(TABLE_EMPLOYEE_DATA, (String) null, contentValues);
            Log.e("Add Emp Inserted ", contentValues.toString());
        } else {
            ContentValues contentValues2 = new ContentValues();
            employeeData.setDbId(0);
            contentValues2.put(KEY_ID, (Integer) 0);
            contentValues2.put(KEY_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
            contentValues2.put(KEY_UPDATED_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
            Log.e("Add Emp Inserting.. ", contentValues2.toString());
            writableDatabase.insert(TABLE_EMPLOYEE_DATA, (String) null, contentValues2);
            Log.e("Add Emp Inserted ", contentValues2.toString());
        }
        writableDatabase.close();
        return employeeData;
    }

    public EmployeeData getSchoolData(int id2) {
        EmployeeData employeeData = new EmployeeData();
        SQLiteDatabase readableDatabase = getReadableDatabase("$eld2O22!MmdB");
        Cursor cursorQuery = readableDatabase.query(TABLE_EMPLOYEE_DATA, new String[]{KEY_UPDATED_SCHOOL}, "id=?", new String[]{String.valueOf(id2)}, null, null, null, null);
        if (cursorQuery != null && cursorQuery.getCount() > 0) {
            cursorQuery.moveToFirst();
            Log.e("getSchool", cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)));
            employeeData = (EmployeeData) new Gson().fromJson(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)), EmployeeData.class);
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        readableDatabase.close();
        return employeeData;
    }

    public List<EmployeeData> getAllSchoolsData() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT updated_school FROM employee_school_data", (String[]) null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.isBeforeFirst()) {
            Log.e("count", cursorRawQuery.getCount() + "_Emplyee Data");
            if (cursorRawQuery.moveToFirst()) {
                do {
                    try {
                        StringBuilder sb = new StringBuilder(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)) == null ? "" : cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)));
                        Log.e("GET EMP DATA : ", "COUNT GET" + cursorRawQuery.getCount() + String.valueOf(sb));
                        System.out.println("SSMS-File Name" + String.valueOf(sb));
                        arrayList.add((EmployeeData) new Gson().fromJson(String.valueOf(sb), EmployeeData.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        util.logException(e);
                    }
                } while (cursorRawQuery.moveToNext());
            }
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        writableDatabase.close();
        return arrayList;
    }

    public Integer getStartedMonitoringCount() {
        String str;
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT updated_school FROM employee_school_data", (String[]) null);
        Integer numValueOf = 0;
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.isBeforeFirst()) {
            if (cursorRawQuery.moveToFirst()) {
                do {
                    try {
                        EmployeeData employeeData = (EmployeeData) new Gson().fromJson(String.valueOf(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)) == null ? "" : cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL))), EmployeeData.class);
                        if (!employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                            for (int i = 0; i < employeeData.getMonitoring().get(0).getKRAData().size(); i++) {
                                KRAData kRAData = employeeData.getMonitoring().get(0).getKRAData().get(i);
                                if (kRAData.getKRAName().equals(Constant.Status_Id) && (str = (String) kRAData.getDataValue()) != null && !str.isEmpty()) {
                                    numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        util.logException(e);
                    }
                } while (cursorRawQuery.moveToNext());
            }
            cursorRawQuery.close();
            writableDatabase.close();
            return numValueOf;
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        writableDatabase.close();
        return numValueOf;
    }

    public int updateSchoolData(int id2, EmployeeData employeeData) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_UPDATED_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
        int iUpdate = writableDatabase.isOpen() ? writableDatabase.update(TABLE_EMPLOYEE_DATA, contentValues, "id = ?", new String[]{String.valueOf(id2)}) : -1;
        Log.i("update", String.valueOf(iUpdate));
        return iUpdate;
    }

    public int updateResetSchoolData(int id2, EmployeeData employeeData) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_UPDATED_SCHOOL, new Gson().toJson(employeeData, EmployeeData.class));
        int iUpdate = writableDatabase.update(TABLE_EMPLOYEE_DATA, contentValues, "id = ?", new String[]{String.valueOf(id2)});
        writableDatabase.close();
        Log.i("update", String.valueOf(iUpdate));
        return iUpdate;
    }

    public void resetSelectedSchoolData(int id2) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT  * FROM employee_school_data  where  id='" + id2 + "'", (String[]) null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.moveToFirst()) {
            do {
                int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(KEY_ID));
                EmployeeData employeeData = (EmployeeData) new Gson().fromJson(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)), EmployeeData.class);
                Log.d("Reset_Selected_School", employeeData.toString());
                if (employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(KEY_UPDATED_SCHOOL, cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_SCHOOL)));
                    Log.i("update_School", String.valueOf(writableDatabase.update(TABLE_EMPLOYEE_DATA, contentValues, "id = ?", new String[]{String.valueOf(i)})));
                }
            } while (cursorRawQuery.moveToNext());
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        writableDatabase.close();
    }

    public void resetSchoolData() {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT  * FROM employee_school_data", (String[]) null);
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 0 && cursorRawQuery.moveToFirst()) {
            do {
                int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(KEY_ID));
                ((EmployeeData) new Gson().fromJson(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_UPDATED_SCHOOL)), EmployeeData.class)).getMonitoring().get(0).setIsMonitoringComplete(false);
                ContentValues contentValues = new ContentValues();
                contentValues.put(KEY_UPDATED_SCHOOL, cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(KEY_SCHOOL)));
                Log.i("update", String.valueOf(writableDatabase.update(TABLE_EMPLOYEE_DATA, contentValues, "id = ?", new String[]{String.valueOf(i)})));
            } while (cursorRawQuery.moveToNext());
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        writableDatabase.close();
    }

    public void deleteSchoolData() {
        getWritableDatabase("$eld2O22!MmdB").delete(TABLE_EMPLOYEE_DATA, (String) null, (String[]) null);
    }

    public void insertStaffTicketingData(ArrayList<AddStaffTicketing> ticketingData) {
        SQLiteDatabase writableDatabase = getWritableDatabase("$eld2O22!MmdB");
        writableDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            Iterator<AddStaffTicketing> it = ticketingData.iterator();
            while (it.hasNext()) {
                AddStaffTicketing next = it.next();
                contentValues.put("cnic", next.getTicketCnic());
                contentValues.put("reason", next.getTicketReason());
                contentValues.put("date_time", next.getTicketDateTime());
                contentValues.put("data", next.getTicketData());
                writableDatabase.insert(TABLE_ADD_STAFF_TICKETING, (String) null, contentValues);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public AddStaffTicketing fetchStaffTicketingData(String cnic, String reason) {
        Cursor cursorRawQuery = getReadableDatabase("$eld2O22!MmdB").rawQuery("SELECT * FROM addStaffTicketing WHERE cnic = " + cnic + " AND reason = " + reason + " ORDER BY date_time DESC LIMIT 1", (String[]) null);
        if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0) {
            return null;
        }
        cursorRawQuery.moveToFirst();
        AddStaffTicketing addStaffTicketing = new AddStaffTicketing();
        addStaffTicketing.setTicketCnic(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("cnic")));
        addStaffTicketing.setTicketReason(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("reason")));
        addStaffTicketing.setTicketDateTime(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("date_time")));
        addStaffTicketing.setTicketData(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("data")));
        cursorRawQuery.close();
        return addStaffTicketing;
    }

    public void deleteStaffTicketingData() {
        getWritableDatabase("$eld2O22!MmdB").delete(TABLE_ADD_STAFF_TICKETING, (String) null, (String[]) null);
    }
}
