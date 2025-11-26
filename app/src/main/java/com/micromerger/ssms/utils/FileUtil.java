package com.micromerger.ssms.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class FileUtil {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    public static final int MAX_PDF_SIZE = 10485760;

    private FileUtil() {
    }

    public static File from(Context context, Uri uri) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] strArrSplitFileName = splitFileName(fileName);
        File fileRename = rename(File.createTempFile(strArrSplitFileName[0], strArrSplitFileName[1]), fileName);
        fileRename.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(fileRename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (inputStreamOpenInputStream != null) {
            copy(inputStreamOpenInputStream, fileOutputStream);
            inputStreamOpenInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return fileRename;
    }

    private static String[] splitFileName(String fileName) {
        String strSubstring;
        int iLastIndexOf = fileName.lastIndexOf(".");
        if (iLastIndexOf != -1) {
            String strSubstring2 = fileName.substring(0, iLastIndexOf);
            strSubstring = fileName.substring(iLastIndexOf);
            fileName = strSubstring2;
        } else {
            strSubstring = "";
        }
        return new String[]{fileName, strSubstring};
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f A[DONT_GENERATE, PHI: r1
  0x003f: PHI (r1v6 java.lang.String) = (r1v1 java.lang.String), (r1v7 java.lang.String) binds: [B:14:0x0034, B:19:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getFileName(android.content.Context r8, android.net.Uri r9) {
        /*
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L42
            android.content.ContentResolver r2 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)
            if (r8 == 0) goto L3d
            boolean r0 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r0 == 0) goto L3d
            java.lang.String r0 = "_display_name"
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            java.lang.String r0 = r8.getString(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r1 = r0
            goto L3d
        L2e:
            r9 = move-exception
            goto L37
        L30:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L42
            goto L3f
        L37:
            if (r8 == 0) goto L3c
            r8.close()
        L3c:
            throw r9
        L3d:
            if (r8 == 0) goto L42
        L3f:
            r8.close()
        L42:
            if (r1 != 0) goto L57
            java.lang.String r1 = r9.getPath()
            java.lang.String r8 = java.io.File.separator
            int r8 = r1.lastIndexOf(r8)
            r9 = -1
            if (r8 == r9) goto L57
            int r8 = r8 + 1
            java.lang.String r1 = r1.substring(r8)
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.FileUtil.getFileName(android.content.Context, android.net.Uri):java.lang.String");
    }

    private static File rename(File file, String newName) {
        File file2 = new File(file.getParent(), newName);
        if (!file2.equals(file)) {
            if (file2.exists() && file2.delete()) {
                Log.d("FileUtil", "Delete old " + newName + " file");
            }
            if (file.renameTo(file2)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return file2;
    }

    private static long copy(InputStream input, OutputStream output) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = input.read(bArr);
            if (-1 == i) {
                return j;
            }
            output.write(bArr, 0, i);
            j += i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean saveFileInInternalStorage(java.io.File r4, java.io.File r5) throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L48
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L48
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L48
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L48
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r3.<init>(r5, r1)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L36
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L37
            r2.read(r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L37
        L1d:
            r4.write(r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L37
            int r0 = r2.read(r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L37
            r3 = -1
            if (r0 != r3) goto L1d
            r2.close()     // Catch: java.io.IOException -> L2f
            r4.close()     // Catch: java.io.IOException -> L2f
            r4 = 1
            return r4
        L2f:
            return r1
        L30:
            r5 = move-exception
            goto L34
        L32:
            r5 = move-exception
            r4 = r0
        L34:
            r0 = r2
            goto L3b
        L36:
            r4 = r0
        L37:
            r0 = r2
            goto L49
        L39:
            r5 = move-exception
            r4 = r0
        L3b:
            if (r0 == 0) goto L40
            r0.close()     // Catch: java.io.IOException -> L46
        L40:
            if (r4 == 0) goto L47
            r4.close()     // Catch: java.io.IOException -> L46
            goto L47
        L46:
            return r1
        L47:
            throw r5
        L48:
            r4 = r0
        L49:
            if (r0 == 0) goto L4e
            r0.close()     // Catch: java.io.IOException -> L54
        L4e:
            if (r4 == 0) goto L54
            r4.close()     // Catch: java.io.IOException -> L54
        L54:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.FileUtil.saveFileInInternalStorage(java.io.File, java.io.File):boolean");
    }
}
