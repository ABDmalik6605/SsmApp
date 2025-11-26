package com.micromerger.ssms.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class util {
    public static void logException(Exception ex) {
        if (ex != null) {
            FirebaseCrashlytics.getInstance().recordException(ex);
            ex.printStackTrace();
        }
    }

    public static boolean unpackZip(String path, String zipname) throws IOException {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipname)));
            byte[] bArr = new byte[1024];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    new File(path + nextEntry.getName()).mkdirs();
                    FileOutputStream fileOutputStream = new FileOutputStream(path);
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i != -1) {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                    Log.d("UnzippedFolders", nextEntry.getName());
                } else {
                    zipInputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void logDownloadedData(String response, int SchoolID, Context context) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        File file = new File(Environment.getExternalStorageDirectory() + "/SSMSBACKUP");
        file.mkdirs();
        System.currentTimeMillis();
        File file2 = new File(file, "SSMSCHECK(" + simpleDateFormat.format(date) + ").txt");
        Uri uri = Uri.parse(file.getPath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(response);
            printWriter.flush();
            printWriter.close();
            fileOutputStream.close();
            Log.d("fileCreated", file.toString());
            Toast.makeText(context, "File Created on : " + uri, 1).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("MainActivity", "******* File not found. Did you add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void unzip(File zipFile, File targetDirectory, String schoolID) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    return;
                }
                Log.i("Decompress : ", "user ID :" + schoolID + "Unzipping " + nextEntry.getName());
                File file = new File(targetDirectory, nextEntry.getName());
                if (file.getCanonicalPath().startsWith(targetDirectory.getCanonicalPath())) {
                    File parentFile = nextEntry.isDirectory() ? file : file.getParentFile();
                    if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                        throw new FileNotFoundException("Failed to ensure directory: " + parentFile.getAbsolutePath());
                    }
                    if (!nextEntry.isDirectory()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        while (true) {
                            try {
                                int i = zipInputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                            } catch (Throwable th) {
                                fileOutputStream.close();
                                throw th;
                            }
                        }
                        fileOutputStream.close();
                    }
                }
            }
        } finally {
            zipInputStream.close();
        }
    }

    public static Bitmap decodeFile(File f, int WIDTH, int HIGHT) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            while ((options.outWidth / i) / 2 >= WIDTH && (options.outHeight / i) / 2 >= HIGHT) {
                i *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, options2);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }
}
