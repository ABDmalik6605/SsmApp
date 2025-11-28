package com.androidnetworking.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.androidnetworking.common.ANConstants;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.core.Core;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import okhttp3.Cache;
import okhttp3.Response;
import okio.Okio;

/* loaded from: classes.dex */
public class Utils {
    public static File getDiskCacheDir(Context context, String str) {
        return new File(context.getCacheDir(), str);
    }

    public static Cache getCache(Context context, int i, String str) {
        return new Cache(getDiskCacheDir(context, str), i);
    }

    public static String getMimeType(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    public static ANResponse<Bitmap> decodeBitmap(Response response, int i, int i2, Bitmap.Config config, ImageView.ScaleType scaleType) {
        return decodeBitmap(response, i, i2, config, new BitmapFactory.Options(), scaleType);
    }

    public static ANResponse<Bitmap> decodeBitmap(Response response, int i, int i2, Bitmap.Config config, BitmapFactory.Options options, ImageView.ScaleType scaleType) {
        Bitmap bitmapCreateScaledBitmap;
        byte[] byteArray = new byte[0];
        try {
            byteArray = Okio.buffer(response.body().source()).readByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i == 0 && i2 == 0) {
            options.inPreferredConfig = config;
            bitmapCreateScaledBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            int resizedDimension = getResizedDimension(i, i2, i3, i4, scaleType);
            int resizedDimension2 = getResizedDimension(i2, i, i4, i3, scaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i3, i4, resizedDimension, resizedDimension2);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
            if (bitmapDecodeByteArray == null || (bitmapDecodeByteArray.getWidth() <= resizedDimension && bitmapDecodeByteArray.getHeight() <= resizedDimension2)) {
                bitmapCreateScaledBitmap = bitmapDecodeByteArray;
            } else {
                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeByteArray, resizedDimension, resizedDimension2, true);
                bitmapDecodeByteArray.recycle();
            }
        }
        if (bitmapCreateScaledBitmap == null) {
            return ANResponse.failed(getErrorForParse(new ANError(response)));
        }
        return ANResponse.success(bitmapCreateScaledBitmap);
    }

    private static int getResizedDimension(int i, int i2, int i3, int i4, ImageView.ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        }
        if (i == 0) {
            return (int) (i3 * (i2 / i4));
        }
        if (i2 == 0) {
            return i;
        }
        double d = i4 / i3;
        if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            double d2 = i2;
            return ((double) i) * d < d2 ? (int) (d2 / d) : i;
        }
        double d3 = i2;
        return ((double) i) * d > d3 ? (int) (d3 / d) : i;
    }

    public static int findBestSampleSize(int i, int i2, int i3, int i4) {
        double dMin = Math.min(i / i3, i2 / i4);
        float f = 1.0f;
        while (true) {
            float f2 = 2.0f * f;
            if (f2 > dMin) {
                return (int) f;
            }
            f = f2;
        }
    }

    public static void saveFile(Response response, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        byte[] bArr = new byte[2048];
        InputStream inputStream = null;
        try {
            InputStream inputStreamByteStream = response.body().byteStream();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileOutputStream = new FileOutputStream(new File(file, str2));
                while (true) {
                    try {
                        int i = inputStreamByteStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamByteStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                throw th;
                            }
                        }
                        throw th;
                    }
                }
                fileOutputStream.flush();
                if (inputStreamByteStream != null) {
                    try {
                        inputStreamByteStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void sendAnalytics(final AnalyticsListener analyticsListener, final long j, final long j2, final long j3, final boolean z) {
        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.androidnetworking.utils.Utils.1
            @Override // java.lang.Runnable
            public void run() {
                AnalyticsListener analyticsListener2 = analyticsListener;
                if (analyticsListener2 != null) {
                    analyticsListener2.onReceived(j, j2, j3, z);
                }
            }
        });
    }

    public static ANError getErrorForConnection(ANError aNError) {
        aNError.setErrorDetail(ANConstants.CONNECTION_ERROR);
        aNError.setErrorCode(0);
        return aNError;
    }

    public static ANError getErrorForServerResponse(ANError aNError, ANRequest aNRequest, int i) {
        ANError networkError = aNRequest.parseNetworkError(aNError);
        networkError.setErrorCode(i);
        networkError.setErrorDetail(ANConstants.RESPONSE_FROM_SERVER_ERROR);
        return networkError;
    }

    public static ANError getErrorForParse(ANError aNError) {
        aNError.setErrorCode(0);
        aNError.setErrorDetail(ANConstants.PARSE_ERROR);
        return aNError;
    }
}