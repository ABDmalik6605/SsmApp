package com.micromerger.ssms.utils.biometric;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import com.integratedbiometrics.ibscanmatcher.IBMatcher;
import com.integratedbiometrics.ibscanmatcher.IBMatcherException;
import com.micromerger.ssms.utils.CommonObjects;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes2.dex */
public class IBMatcherDatabase {
    public static final String COLUMN_NAME_CREATE_DATE = "create_date";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_MODIFY_DATE = "modify_date";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_TEMPLATE = "template";
    public static final String COLUMN_NAME__ID = "_id";
    public static final int CURSOR_INDEX_CREATE_DATE = 3;
    public static final int CURSOR_INDEX_DESCRIPTION = 2;
    public static final int CURSOR_INDEX_MODIFY_DATE = 4;
    public static final int CURSOR_INDEX_NAME = 1;
    public static final int CURSOR_INDEX__ID = 0;
    public static final String DATABASE_NAME = "fingerprints.db";
    public static final int DATABASE_VERSION = 4;
    private static final String FINGERPRINT_DB_TAG = "IBMatcher Database";
    public static final String TABLE_NAME = "fingerprints";
    private final IBMatcher m_ibMatcher = IBMatcher.getInstance();
    private final DatabaseHelper m_openHelper;

    public static class Entry {
        private final Date m_createDate;
        private final String m_description;
        private final int m_matchScore;
        private final Date m_modifyDate;
        private final String m_name;
        private final IBMatcher.Template m_template;

        protected Entry(String name, String description, Date createDate, Date modifyDate, IBMatcher.Template template) {
            this(name, description, createDate, modifyDate, template, 0);
        }

        protected Entry(String name, String description, Date createDate, Date modifyDate, IBMatcher.Template template, int matchScore) {
            this.m_name = name;
            this.m_description = description;
            this.m_createDate = createDate;
            this.m_modifyDate = modifyDate;
            this.m_template = template;
            this.m_matchScore = matchScore;
        }

        public String getName() {
            return this.m_name;
        }

        public String getDescription() {
            return this.m_description;
        }

        public Date getCreateDate() {
            return this.m_createDate;
        }

        public Date getModifyDate() {
            return this.m_modifyDate;
        }

        public IBMatcher.Template getTemplate() {
            return this.m_template;
        }

        public int getMatchScore() {
            return this.m_matchScore;
        }
    }

    public IBMatcherDatabase(final Context context) {
        this.m_openHelper = new DatabaseHelper(context);
    }

    public Cursor getCursor() {
        try {
            Cursor cursorQuery = this.m_openHelper.getReadableDatabase().query(TABLE_NAME, new String[]{COLUMN_NAME__ID, "name", COLUMN_NAME_DESCRIPTION, COLUMN_NAME_CREATE_DATE, COLUMN_NAME_MODIFY_DATE}, null, null, null, null, null);
            if (cursorQuery == null) {
                Log.e(FINGERPRINT_DB_TAG, "Find failed");
                return null;
            }
            Log.i(FINGERPRINT_DB_TAG, "Found " + cursorQuery.getCount() + " entries");
            return cursorQuery;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Query failed with exception " + e.toString());
            return null;
        }
    }

    public long getSize() {
        return new File(this.m_openHelper.getReadableDatabase().getPath()).length();
    }

    public boolean enroll(final String name, final String description, final IBMatcher.Template template) throws IOException {
        if (name == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null name");
            throw new IllegalArgumentException("Received null name");
        }
        if (description == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null description");
            throw new IllegalArgumentException("Received null description");
        }
        if (template == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null template");
            throw new IllegalArgumentException("Received null template");
        }
        ContentValues contentValues = new ContentValues();
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        contentValues.put(COLUMN_NAME_CREATE_DATE, lValueOf);
        contentValues.put(COLUMN_NAME_MODIFY_DATE, lValueOf);
        contentValues.put("name", name);
        contentValues.put(COLUMN_NAME_DESCRIPTION, description);
        byte[] bArrConvertTemplateToBytes = convertTemplateToBytes(template);
        if (bArrConvertTemplateToBytes == null) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to convert template to bytes");
            return false;
        }
        contentValues.put(COLUMN_NAME_TEMPLATE, bArrConvertTemplateToBytes);
        try {
            if (this.m_openHelper.getWritableDatabase().insert(TABLE_NAME, "name", contentValues) > 0) {
                Log.i(FINGERPRINT_DB_TAG, "Create new row with row ID for user \"" + name + "\"");
                return true;
            }
            Log.e(FINGERPRINT_DB_TAG, "Create new row with row ID for user \"" + name + "\"");
            return false;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Insert failed with exception " + e.toString());
            return false;
        }
    }

    public Entry match(final IBMatcher.Template template) throws IOException {
        if (template == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null template");
            throw new IllegalArgumentException("Received null template");
        }
        try {
            Cursor cursorQuery = this.m_openHelper.getReadableDatabase().query(TABLE_NAME, new String[]{"name", COLUMN_NAME_DESCRIPTION, COLUMN_NAME_CREATE_DATE, COLUMN_NAME_MODIFY_DATE, COLUMN_NAME_TEMPLATE}, null, null, null, null, null);
            if (cursorQuery == null) {
                Log.e(FINGERPRINT_DB_TAG, "Find failed");
                return null;
            }
            Log.i(FINGERPRINT_DB_TAG, "Found " + cursorQuery.getCount() + " entries");
            cursorQuery.moveToFirst();
            while (!cursorQuery.isAfterLast()) {
                String string = cursorQuery.getString(0);
                if (string == null) {
                    Log.e(FINGERPRINT_DB_TAG, "Found null name in entry");
                } else {
                    String string2 = cursorQuery.getString(1);
                    if (string2 == null) {
                        Log.e(FINGERPRINT_DB_TAG, "Found null description in entry");
                    } else {
                        Long lValueOf = Long.valueOf(cursorQuery.getLong(2));
                        if (lValueOf == null) {
                            Log.e(FINGERPRINT_DB_TAG, "Found null create date in entry");
                        } else {
                            Long lValueOf2 = Long.valueOf(cursorQuery.getLong(3));
                            if (lValueOf2 == null) {
                                Log.e(FINGERPRINT_DB_TAG, "Found null modify date in entry");
                            } else {
                                byte[] blob = cursorQuery.getBlob(4);
                                if (blob == null) {
                                    Log.e(FINGERPRINT_DB_TAG, "Found null template in entry");
                                } else {
                                    IBMatcher.Template templateConvertBytesToTemplate = convertBytesToTemplate(blob);
                                    if (template == null) {
                                        Log.e(FINGERPRINT_DB_TAG, "Failed to convert template");
                                    } else {
                                        try {
                                            int iMatchTemplates = this.m_ibMatcher.matchTemplates(template, templateConvertBytesToTemplate);
                                            if (iMatchTemplates > 0) {
                                                cursorQuery.close();
                                                return new Entry(string, string2, new Date(lValueOf.longValue()), new Date(lValueOf2.longValue()), template, iMatchTemplates);
                                            }
                                            continue;
                                        } catch (IBMatcherException e) {
                                            Log.e(FINGERPRINT_DB_TAG, "Match failed with exception " + e.getType().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                cursorQuery.moveToNext();
            }
            cursorQuery.close();
            return null;
        } catch (SQLException e2) {
            Log.e(FINGERPRINT_DB_TAG, "Query failed with exception " + e2.toString());
            return null;
        }
    }

    public Entry find(final String name) throws IOException {
        if (name == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null name");
            throw new IllegalArgumentException("Received null name");
        }
        SQLiteDatabase readableDatabase = this.m_openHelper.getReadableDatabase();
        try {
            Cursor cursorQuery = readableDatabase.query(TABLE_NAME, new String[]{COLUMN_NAME_DESCRIPTION, COLUMN_NAME_CREATE_DATE, COLUMN_NAME_MODIFY_DATE, COLUMN_NAME_TEMPLATE}, "name = \"" + name + "\"", null, null, null, null);
            if (cursorQuery == null) {
                Log.e(FINGERPRINT_DB_TAG, "Find failed for user \"" + name + "\"");
                return null;
            }
            Log.i(FINGERPRINT_DB_TAG, "Found " + cursorQuery.getCount() + " entries for user \"" + name + "\"");
            if (cursorQuery.getCount() < 1) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Found no entry for user \"" + name + "\"");
                return null;
            }
            cursorQuery.moveToFirst();
            String string = cursorQuery.getString(0);
            if (string == null) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Found null description in entry for user \"" + name + "\"");
                return null;
            }
            Long lValueOf = Long.valueOf(cursorQuery.getLong(1));
            if (lValueOf == null) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Found null create date in entry for user \"" + name + "\"");
                return null;
            }
            Long lValueOf2 = Long.valueOf(cursorQuery.getLong(2));
            if (lValueOf2 == null) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Found null modify date in entry for user \"" + name + "\"");
                return null;
            }
            byte[] blob = cursorQuery.getBlob(3);
            if (blob == null) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Found null template in entry for user \"" + name + "\"");
                return null;
            }
            IBMatcher.Template templateConvertBytesToTemplate = convertBytesToTemplate(blob);
            if (templateConvertBytesToTemplate == null) {
                cursorQuery.close();
                Log.e(FINGERPRINT_DB_TAG, "Failed to convert template for user \"" + name + "\"");
                return null;
            }
            return new Entry(name, string, new Date(lValueOf.longValue()), new Date(lValueOf2.longValue()), templateConvertBytesToTemplate);
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Query failed for \"" + name + "\" with exception " + e.toString());
            return null;
        }
    }

    public boolean update(final String name, final IBMatcher.Template template) throws IOException {
        if (name == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null name");
            throw new IllegalArgumentException("Received null name");
        }
        if (template == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null template");
            throw new IllegalArgumentException("Received null template");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_MODIFY_DATE, Long.valueOf(System.currentTimeMillis()));
        byte[] bArrConvertTemplateToBytes = convertTemplateToBytes(template);
        if (bArrConvertTemplateToBytes == null) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to convert template to bytes");
            return false;
        }
        contentValues.put(COLUMN_NAME_TEMPLATE, bArrConvertTemplateToBytes);
        try {
            long jUpdate = this.m_openHelper.getWritableDatabase().update(TABLE_NAME, contentValues, "name = \"" + name + "\"", null);
            if (jUpdate >= 0) {
                Log.i(FINGERPRINT_DB_TAG, "Updated " + jUpdate + " rows for user \"" + name + "\"");
                return true;
            }
            Log.i(FINGERPRINT_DB_TAG, "Update failed for user \"" + name + "\"");
            return false;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Update failed with exception " + e.toString());
            return false;
        }
    }

    public boolean update(final String name, final String description, final IBMatcher.Template template) throws IOException {
        if (name == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null name");
            throw new IllegalArgumentException("Received null name");
        }
        if (template == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null template");
            throw new IllegalArgumentException("Received null template");
        }
        if (description == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null description");
            throw new IllegalArgumentException("Received null description");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_MODIFY_DATE, Long.valueOf(System.currentTimeMillis()));
        contentValues.put(COLUMN_NAME_DESCRIPTION, description);
        byte[] bArrConvertTemplateToBytes = convertTemplateToBytes(template);
        if (bArrConvertTemplateToBytes == null) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to convert template to bytes");
            return false;
        }
        contentValues.put(COLUMN_NAME_TEMPLATE, bArrConvertTemplateToBytes);
        try {
            long jUpdate = this.m_openHelper.getWritableDatabase().update(TABLE_NAME, contentValues, "name = \"" + name + "\"", null);
            if (jUpdate >= 0) {
                Log.i(FINGERPRINT_DB_TAG, "Updated " + jUpdate + " rows for user \"" + name + "\"");
                return true;
            }
            Log.i(FINGERPRINT_DB_TAG, "Update failed for user \"" + name + "\"");
            return false;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Update failed with exception " + e.toString());
            return false;
        }
    }

    public boolean delete(final String name) {
        if (name == null) {
            Log.e(FINGERPRINT_DB_TAG, "Received null name");
            throw new IllegalArgumentException("Received null name");
        }
        try {
            int iDelete = this.m_openHelper.getWritableDatabase().delete(TABLE_NAME, "name = " + name, null);
            if (iDelete >= 0) {
                Log.i(FINGERPRINT_DB_TAG, "Deleted " + iDelete + " rows for user \"" + name + "\"");
                return true;
            }
            Log.i(FINGERPRINT_DB_TAG, "Delete failed for user \"" + name + "\"");
            return false;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Delete failed with exception " + e.toString());
            return false;
        }
    }

    public boolean clear() {
        try {
            int iDelete = this.m_openHelper.getWritableDatabase().delete(TABLE_NAME, "1", null);
            if (iDelete >= 0) {
                Log.i(FINGERPRINT_DB_TAG, "Deleted " + iDelete + " rows");
                return true;
            }
            Log.i(FINGERPRINT_DB_TAG, "Delete failed");
            return false;
        } catch (SQLException e) {
            Log.e(FINGERPRINT_DB_TAG, "Delete failed with exception " + e.toString());
            return false;
        }
    }

    public void reset() throws SQLException {
        SQLiteDatabase writableDatabase = this.m_openHelper.getWritableDatabase();
        writableDatabase.execSQL("DROP TABLE IF EXISTS fingerprints");
        this.m_openHelper.onCreate(writableDatabase);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, IBMatcherDatabase.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 4);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(final SQLiteDatabase db) throws SQLException {
            try {
                db.execSQL("CREATE TABLE fingerprints (_id INTEGER PRIMARY KEY,name TEXT,description TEXT,create_date TEXT,modify_date TEXT,template BLOB);");
            } catch (SQLException e) {
                Log.e(IBMatcherDatabase.FINGERPRINT_DB_TAG, "Failed to create database with exception: " + e.toString());
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) throws SQLException {
            Log.w(IBMatcherDatabase.FINGERPRINT_DB_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS fingerprints");
            onCreate(db);
        }
    }

    public byte[] getBytesFromTemplate(final IBMatcher.Template template, Context ctx) {
        return convertTemplateToBytes(template, ctx);
    }

    private byte[] convertTemplateToBytes(final IBMatcher.Template template) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/dbtemp.ibsm_template");
        file.deleteOnExit();
        try {
            file.createNewFile();
            try {
                this.m_ibMatcher.saveTemplate(template, file.getAbsolutePath());
                try {
                    int length = (int) file.length();
                    if (length <= 0) {
                        Log.e(FINGERPRINT_DB_TAG, "Failed to get length of template file");
                        return null;
                    }
                    byte[] bArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    int i = fileInputStream.read(bArr);
                    fileInputStream.close();
                    if (i == length) {
                        return bArr;
                    }
                    Log.e(FINGERPRINT_DB_TAG, "Reading template file, expected" + length + "bytes, read only " + i + " bytes");
                    return null;
                } catch (IOException unused) {
                    Log.e(FINGERPRINT_DB_TAG, "Failed to read template file");
                    return null;
                }
            } catch (IBMatcherException unused2) {
                Log.e(FINGERPRINT_DB_TAG, "Failed to save template file");
                return null;
            }
        } catch (IOException unused3) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to create temporary file for template");
            return null;
        }
    }

    private byte[] convertTemplateToBytes(final IBMatcher.Template template, Context ctx) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/dbtemp.ibsm_template");
        file.deleteOnExit();
        try {
            file.createNewFile();
        } catch (IOException unused) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to create temporary external file for template");
        }
        try {
            if (!file.exists()) {
                file = new File(ctx.getFilesDir().toString() + "/dbtemp.ibsm_template");
                file.deleteOnExit();
                file.createNewFile();
            }
            try {
                this.m_ibMatcher.saveTemplate(template, file.getAbsolutePath());
                try {
                    int length = (int) file.length();
                    if (length <= 0) {
                        Log.e(FINGERPRINT_DB_TAG, "Failed to get length of template file");
                        return null;
                    }
                    byte[] bArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    int i = fileInputStream.read(bArr);
                    fileInputStream.close();
                    if (i == length) {
                        return bArr;
                    }
                    Log.e(FINGERPRINT_DB_TAG, "Reading template file, expected" + length + "bytes, read only " + i + " bytes");
                    return null;
                } catch (IOException unused2) {
                    Log.e(FINGERPRINT_DB_TAG, "Failed to read template file");
                    return null;
                }
            } catch (IBMatcherException unused3) {
                Log.e(FINGERPRINT_DB_TAG, "Failed to save template file");
                return null;
            }
        } catch (IOException unused4) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to create temporary file for template");
            return null;
        }
    }

    private IBMatcher.Template convertBytesToTemplate(final byte[] templateBytes) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/dbtemp.ibsm_template");
        file.deleteOnExit();
        try {
            file.createNewFile();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(templateBytes);
                fileOutputStream.close();
                try {
                    return this.m_ibMatcher.loadTemplate(file.getAbsolutePath());
                } catch (IBMatcherException unused) {
                    Log.e(FINGERPRINT_DB_TAG, "Failed to load template");
                    return null;
                }
            } catch (IOException unused2) {
                Log.e(FINGERPRINT_DB_TAG, "Failed to write temporary file for template");
                return null;
            }
        } catch (IOException unused3) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to create temporary file for template");
            return null;
        }
    }

    public boolean getSavedTemplate(IBMatcher.Template userTemplate, final Activity act, Context ctx) throws IOException {
        try {
            CommonObjects.isMAExist = false;
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + CommonObjects.userObj.userId + ".ibsm_template");
            if (!file.exists()) {
                copyFile(new File(ctx.getFilesDir().toString()).getPath(), CommonObjects.userObj.userId + ".ibsm_template", Environment.getExternalStorageDirectory().getPath());
            }
            int iMatchTemplates = this.m_ibMatcher.matchTemplates(this.m_ibMatcher.loadTemplate(file.getAbsolutePath()), userTemplate);
            file.delete();
            if (iMatchTemplates <= 0) {
                return false;
            }
            CommonObjects.isMAExist = true;
            return true;
        } catch (IBMatcherException unused) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to load template");
            return false;
        }
    }

    public boolean getSavedTemplate(IBMatcher.Template userTemplate, Context ctx) {
        try {
            CommonObjects.isMAExist = false;
            File file = new File(ctx.getFilesDir().toString() + "/user_biometric/" + CommonObjects.userObj.userId + ".ibsm_template");
            if (!file.exists()) {
                return false;
            }
            if (this.m_ibMatcher.matchTemplates(this.m_ibMatcher.loadTemplate(file.getAbsolutePath()), userTemplate) <= 0) {
                return false;
            }
            CommonObjects.isMAExist = true;
            return true;
        } catch (IBMatcherException unused) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to load template");
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0116 A[EDGE_INSN: B:40:0x0116->B:66:0x011d BREAK  A[LOOP:3: B:31:0x00db->B:42:0x0119]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean getSavedEmployee(com.integratedbiometrics.ibscanmatcher.IBMatcher.Template r18, final android.app.Activity r19, java.lang.String r20, android.content.Context r21) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.biometric.IBMatcherDatabase.getSavedEmployee(com.integratedbiometrics.ibscanmatcher.IBMatcher$Template, android.app.Activity, java.lang.String, android.content.Context):boolean");
    }

    public boolean checkEmployee(IBMatcher.Template userTemplate, final Activity act, Context ctx, String eId) {
        try {
            CommonObjects.searchedEmployeeId = "";
            File[] fileArrListFiles = new File(new File(ctx.getFilesDir().toString()).getPath() + "/unzipped/SchoolsEmployee/").listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return false;
            }
            for (File file : fileArrListFiles) {
                file.isFile();
                Log.e("file", file.getName());
                String strSubstring = file.getName().substring(0, file.getName().indexOf("."));
                if (strSubstring.equals(eId)) {
                    if (this.m_ibMatcher.matchTemplates(this.m_ibMatcher.loadTemplate(file.getAbsolutePath()), userTemplate) > 0) {
                        CommonObjects.searchedEmployeeId = strSubstring;
                        return true;
                    }
                }
            }
            return false;
        } catch (IBMatcherException unused) {
            Log.e(FINGERPRINT_DB_TAG, "Failed to load template");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void copyFile(String inputPath, String inputFile, String outputPath) throws IOException {
        try {
            File file = new File(outputPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileInputStream fileInputStream = new FileInputStream(inputPath + "/" + inputFile);
            FileOutputStream fileOutputStream = new FileOutputStream(outputPath + "/" + inputFile);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("tag1", e.getMessage());
        } catch (Exception e2) {
            Log.e("tag", e2.getMessage());
        }
    }
}
