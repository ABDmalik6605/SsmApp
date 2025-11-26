package com.micromerger.ssms.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class Decompress {
    private String _UserID;
    private String _location;
    private String _zipFile;

    public Decompress(String zipFile, String location, String UserID) {
        this._zipFile = zipFile;
        this._location = location;
        this._UserID = UserID;
        _dirChecker("");
    }

    public void unzip() throws IOException {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this._zipFile));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    Log.e("Decompress : ", "user ID :" + this._UserID + "Unzipping " + nextEntry.getName());
                    _dirChecker(nextEntry.getName().split("/")[0]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(this._location);
                    sb.append(nextEntry.getName());
                    FileOutputStream fileOutputStream = new FileOutputStream(sb.toString());
                    while (true) {
                        int i = zipInputStream.read();
                        if (i != -1) {
                            fileOutputStream.write(i);
                        }
                    }
                    zipInputStream.closeEntry();
                    fileOutputStream.close();
                } else {
                    zipInputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            Log.e("Decompress", "unzip", e);
        }
    }

    private void _dirChecker(String dir) {
        File file = new File(this._location + dir);
        if (file.isDirectory()) {
            return;
        }
        file.mkdirs();
    }
}
