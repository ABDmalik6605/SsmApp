package com.micromerger.ssms.utils;

import android.content.Context;
import java.io.File;

/* loaded from: classes2.dex */
public class SaveToDownloads {
    Context context;
    File file;

    public SaveToDownloads(Context context, File file) {
        this.context = context;
        this.file = file;
    }

    public String getSizeOfFile() {
        return String.valueOf(this.file.length() / 1024);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.net.Uri saveFileToDownloads(java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            android.content.Context r1 = r7.context     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r2.<init>()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r3 = "_display_name"
            java.io.File r4 = r7.file     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r3 = "mime_type"
        */
        //  java.lang.String r4 = "*/*"
        /*
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r3 = "_size"
            java.lang.String r4 = r7.getSizeOfFile()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r3 = "is_pending"
            r4 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r3 = "relative_path"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r4.<init>()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r5 = android.os.Environment.DIRECTORY_DOWNLOADS     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r4.append(r5)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r5 = java.io.File.separator     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r4.append(r5)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r4.append(r8)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r8 = java.io.File.separator     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r4.append(r8)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.lang.String r8 = r4.toString()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            r2.put(r3, r8)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            android.net.Uri r8 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            android.net.Uri r8 = r1.insert(r8, r2)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.io.OutputStream r1 = r1.openOutputStream(r8)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> L9b java.io.FileNotFoundException -> La1
            java.io.File r2 = r7.file     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            long r2 = r2.length()     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            int r3 = (int) r2     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            byte[] r2 = new byte[r3]     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            java.io.File r6 = r7.file     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            java.io.File r6 = r6.getAbsoluteFile()     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            r5.<init>(r6)     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
            r4.<init>(r5)     // Catch: java.io.IOException -> L94 java.io.FileNotFoundException -> L96 java.lang.Throwable -> La7
        L74:
            r0 = 0
            int r5 = r4.read(r2, r0, r3)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.io.FileNotFoundException -> L91
            r6 = -1
            if (r5 == r6) goto L80
            r1.write(r2, r0, r3)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e java.io.FileNotFoundException -> L91
            goto L74
        L80:
            if (r1 == 0) goto L88
            r1.flush()
            r1.close()
        L88:
            r4.close()
            return r8
        L8c:
            r8 = move-exception
            goto La9
        L8e:
            r8 = move-exception
            r0 = r4
            goto L9d
        L91:
            r8 = move-exception
            r0 = r4
            goto La3
        L94:
            r8 = move-exception
            goto L9d
        L96:
            r8 = move-exception
            goto La3
        L98:
            r8 = move-exception
            r4 = r0
            goto Laa
        L9b:
            r8 = move-exception
            r1 = r0
        L9d:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> La7
            throw r8     // Catch: java.lang.Throwable -> La7
        La1:
            r8 = move-exception
            r1 = r0
        La3:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> La7
            throw r8     // Catch: java.lang.Throwable -> La7
        La7:
            r8 = move-exception
            r4 = r0
        La9:
            r0 = r1
        Laa:
            if (r0 == 0) goto Lb2
            r0.flush()
            r0.close()
        Lb2:
            if (r4 == 0) goto Lb7
            r4.close()
        Lb7:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.utils.SaveToDownloads.saveFileToDownloads(java.lang.String):android.net.Uri");
    }
}
