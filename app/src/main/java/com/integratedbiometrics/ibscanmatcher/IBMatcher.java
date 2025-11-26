package com.integratedbiometrics.ibscanmatcher;

import android.graphics.Bitmap;
import android.util.Log;
import com.integratedbiometrics.ibscancommon.IBCommon;
import com.integratedbiometrics.ibscanmatcher.IBMatcherException;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class IBMatcher {
    public static final short CAPTURE_DEVICE_TYPE_ID_COLUMBO = 48;
    public static final short CAPTURE_DEVICE_TYPE_ID_CURVE = 4097;
    public static final short CAPTURE_DEVICE_TYPE_ID_HOLMES = 64;
    public static final short CAPTURE_DEVICE_TYPE_ID_SHERLOCK = 16;
    public static final short CAPTURE_DEVICE_TYPE_ID_UNKNOWN = 0;
    public static final short CAPTURE_DEVICE_TYPE_ID_WATSON = 4101;
    public static final short CAPTURE_DEVICE_TYPE_ID_WATSON_MINI = 32;
    public static final short CAPTURE_DEVICE_VENDOR_ID_UNREPORTED = 0;
    public static final short CAPTURE_DEVICE_VENDOR_INTEGRATED_BIOMETRICS = -21555;
    private static int METHOD_STACK_INDEX = 0;
    public static final byte SCALE_UNIT_CENTIMETER = 2;
    public static final byte SCALE_UNIT_INCH = 1;
    private static IBMatcher m_instance;
    private final int m_handleNative = initNative();

    private native IBCommon.ImageDataExt compressImageNative(IBCommon.ImageDataExt imageDataExt, int i, NativeError nativeError);

    private native IBCommon.ImageDataExt decompressImageNative(IBCommon.ImageDataExt imageDataExt, NativeError nativeError);

    private native Template extractTemplateNative(IBCommon.ImageDataExt imageDataExt, NativeError nativeError);

    private native int getMatchingLevelNative(NativeError nativeError);

    private native SdkVersion getSdkVersionNative(NativeError nativeError);

    private native int initNative();

    private native IBCommon.ImageDataExt loadImageFromFirNative(String str, NativeError nativeError);

    private native IBCommon.ImageDataExt loadImageNative(String str, NativeError nativeError);

    private native Template loadTemplateFromFmrNative(String str, NativeError nativeError);

    private native Template loadTemplateNative(String str, NativeError nativeError);

    private native int matchTemplatesNative(Template template, Template template2, NativeError nativeError);

    private native Template[] multiEnrollmentNative(IBCommon.ImageDataExt imageDataExt, IBCommon.ImageDataExt imageDataExt2, IBCommon.ImageDataExt imageDataExt3, IBCommon.ImageDataExt imageDataExt4, IBCommon.ImageDataExt imageDataExt5, IBCommon.ImageDataExt imageDataExt6, NativeError nativeError);

    private native boolean saveImageAsFirNative(IBCommon.ImageDataExt imageDataExt, String str, NativeError nativeError);

    private native boolean saveImageNative(IBCommon.ImageDataExt imageDataExt, String str, NativeError nativeError);

    private native boolean saveTemplateAsFmrNative(Template template, String str, NativeError nativeError);

    private native boolean saveTemplateNative(Template template, String str, NativeError nativeError);

    private native void setMatchingLevelNative(int i, NativeError nativeError);

    private native Template singleEnrollmentNative(IBCommon.ImageDataExt imageDataExt, IBCommon.ImageDataExt imageDataExt2, IBCommon.ImageDataExt imageDataExt3, NativeError nativeError);

    public static final class SdkVersion {
        public final String file;
        public final String product;

        protected SdkVersion(String str, String str2) {
            this.product = str;
            this.file = str2;
        }

        public String toString() {
            return "Product: " + this.product + "\nFile: " + this.file + "\n";
        }
    }

    public static final class Template {
        public final IBCommon.CaptureDeviceTechId captureDeviceTechId;
        public final short captureDeviceTypeId;
        public final short captureDeviceVendorId;
        public final IBCommon.FingerPosition fingerPosition;
        public final short imageSamplingX;
        public final short imageSamplingY;
        public final short imageSizeX;
        public final short imageSizeY;
        public final IBCommon.ImpressionType impressionType;
        public final byte[] minutiae;
        public final int reserved;
        public final TemplateVersion version;

        protected Template(int i, int i2, int i3, int i4, short s, short s2, short s3, short s4, short s5, short s6, byte[] bArr, int i5) {
            this.version = TemplateVersion.fromCode(i);
            this.fingerPosition = IBCommon.FingerPosition.fromCode(i2);
            this.impressionType = IBCommon.ImpressionType.fromCode(i3);
            this.captureDeviceTechId = IBCommon.CaptureDeviceTechId.fromCode(i4);
            this.captureDeviceVendorId = s;
            this.captureDeviceTypeId = s2;
            this.imageSamplingX = s3;
            this.imageSamplingY = s4;
            this.imageSizeX = s5;
            this.imageSizeY = s6;
            this.minutiae = bArr;
            this.reserved = i5;
        }

        public String toString() {
            return "Template version = " + this.version.toString() + "\nImpression type = " + this.impressionType.toString() + "\nFinger position = " + this.fingerPosition.toString() + "\nCapture device tech ID = " + this.captureDeviceTechId.toString() + "\nCapture device vendor ID = " + ((int) this.captureDeviceVendorId) + "\nCapture device type ID = " + ((int) this.captureDeviceTypeId) + "\nImage sampling = " + ((int) this.imageSamplingX) + " x " + ((int) this.imageSamplingY) + "\nImage size = " + ((int) this.imageSizeX) + " x " + ((int) this.imageSizeY) + "\n";
        }
    }

    public enum TemplateVersion {
        IBISDK_0(0),
        IBISDK_1(1),
        IBISDK_2(2),
        IBISDK_3(3),
        NEW_0(16);

        private final int code;

        TemplateVersion(int i) {
            this.code = i;
        }

        protected static TemplateVersion fromCode(int i) {
            for (TemplateVersion templateVersion : values()) {
                if (templateVersion.code == i) {
                    return templateVersion;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public SdkVersion getSdkVersion() throws IBMatcherException {
        SdkVersion sdkVersionNative;
        NativeError nativeError = new NativeError();
        synchronized (this) {
            sdkVersionNative = getSdkVersionNative(nativeError);
        }
        handleError(nativeError);
        return sdkVersionNative;
    }

    public Template extractTemplate(IBCommon.ImageDataExt imageDataExt) throws IBMatcherException {
        Template templateExtractTemplateNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            templateExtractTemplateNative = extractTemplateNative(imageDataExt, nativeError);
        }
        handleError(nativeError);
        if (templateExtractTemplateNative == null || templateExtractTemplateNative.captureDeviceTechId == null || templateExtractTemplateNative.fingerPosition == null || templateExtractTemplateNative.version == null || templateExtractTemplateNative.impressionType == null || templateExtractTemplateNative.minutiae == null) {
            logPrintError(getMethodName() + ": null or invalid template returned from native code");
            nativeError.code = IBMatcherException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return templateExtractTemplateNative;
    }

    public IBCommon.ImageDataExt compressImage(IBCommon.ImageDataExt imageDataExt, IBCommon.ImageFormat imageFormat) throws IBMatcherException {
        IBCommon.ImageDataExt imageDataExtCompressImageNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        if (imageFormat == null) {
            logPrintWarning(getMethodName() + ": received null imageFormat");
            throw new IllegalArgumentException("Received null imageFormat");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            imageDataExtCompressImageNative = compressImageNative(imageDataExt, imageFormat.toCode(), nativeError);
        }
        handleError(nativeError);
        return imageDataExtCompressImageNative;
    }

    public IBCommon.ImageDataExt decompressImage(IBCommon.ImageDataExt imageDataExt) throws IBMatcherException {
        IBCommon.ImageDataExt imageDataExtDecompressImageNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            imageDataExtDecompressImageNative = decompressImageNative(imageDataExt, nativeError);
        }
        handleError(nativeError);
        return imageDataExtDecompressImageNative;
    }

    public Bitmap convertImageToBitmap(IBCommon.ImageDataExt imageDataExt) {
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(imageDataExt.imageSizeX, imageDataExt.imageSizeY, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap != null) {
            byte[] bArr = new byte[imageDataExt.imageSizeX * imageDataExt.imageSizeY * 4];
            for (int i = 0; i < imageDataExt.imageSizeY; i++) {
                for (int i2 = 0; i2 < imageDataExt.imageSizeX; i2++) {
                    int i3 = ((imageDataExt.imageSizeX * i) + i2) * 4;
                    int i4 = (((imageDataExt.imageSizeX * i) + i2) * 4) + 1;
                    int i5 = (((imageDataExt.imageSizeX * i) + i2) * 4) + 2;
                    byte b = imageDataExt.imageData[(((imageDataExt.imageSizeY - i) - 1) * imageDataExt.imageSizeX) + i2];
                    bArr[i5] = b;
                    bArr[i4] = b;
                    bArr[i3] = b;
                    bArr[(((imageDataExt.imageSizeX * i) + i2) * 4) + 3] = -1;
                }
            }
            bitmapCreateBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        }
        return bitmapCreateBitmap;
    }

    public boolean saveImage(IBCommon.ImageDataExt imageDataExt, String str) throws IBMatcherException {
        boolean zSaveImageNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            zSaveImageNative = saveImageNative(imageDataExt, str, nativeError);
        }
        handleError(nativeError);
        return zSaveImageNative;
    }

    public IBCommon.ImageDataExt loadImage(String str) throws IBMatcherException {
        IBCommon.ImageDataExt imageDataExtLoadImageNative;
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            imageDataExtLoadImageNative = loadImageNative(str, nativeError);
        }
        handleError(nativeError);
        return imageDataExtLoadImageNative;
    }

    public boolean saveImageAsFir(IBCommon.ImageDataExt imageDataExt, String str) throws IBMatcherException {
        boolean zSaveImageAsFirNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null imageDataExt");
            throw new IllegalArgumentException("Received null imageDataExt");
        }
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            zSaveImageAsFirNative = saveImageAsFirNative(imageDataExt, str, nativeError);
        }
        handleError(nativeError);
        return zSaveImageAsFirNative;
    }

    public IBCommon.ImageDataExt loadImageFromFir(String str) throws IBMatcherException {
        IBCommon.ImageDataExt imageDataExtLoadImageFromFirNative;
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            imageDataExtLoadImageFromFirNative = loadImageFromFirNative(str, nativeError);
        }
        handleError(nativeError);
        return imageDataExtLoadImageFromFirNative;
    }

    public boolean saveTemplate(Template template, String str) throws IBMatcherException {
        boolean zSaveTemplateNative;
        if (template == null) {
            logPrintWarning(getMethodName() + ": received null template");
            throw new IllegalArgumentException("Received null template");
        }
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            zSaveTemplateNative = saveTemplateNative(template, str, nativeError);
        }
        handleError(nativeError);
        return zSaveTemplateNative;
    }

    public Template loadTemplate(String str) throws IBMatcherException {
        Template templateLoadTemplateNative;
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            templateLoadTemplateNative = loadTemplateNative(str, nativeError);
        }
        handleError(nativeError);
        return templateLoadTemplateNative;
    }

    public boolean saveTemplateAsFmr(Template template, String str) throws IBMatcherException {
        boolean zSaveTemplateAsFmrNative;
        if (template == null) {
            logPrintWarning(getMethodName() + ": received null template");
            throw new IllegalArgumentException("Received null template");
        }
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            zSaveTemplateAsFmrNative = saveTemplateAsFmrNative(template, str, nativeError);
        }
        handleError(nativeError);
        return zSaveTemplateAsFmrNative;
    }

    public Template loadTemplateFromFmr(String str) throws IBMatcherException {
        Template templateLoadTemplateFromFmrNative;
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null filePath");
            throw new IllegalArgumentException("Received null filePath");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            templateLoadTemplateFromFmrNative = loadTemplateFromFmrNative(str, nativeError);
        }
        handleError(nativeError);
        return templateLoadTemplateFromFmrNative;
    }

    public int matchTemplates(Template template, Template template2) throws IBMatcherException {
        int iMatchTemplatesNative;
        if (template == null) {
            logPrintWarning(getMethodName() + ": received null first template");
            throw new IllegalArgumentException("Received null first template");
        }
        if (template2 == null) {
            logPrintWarning(getMethodName() + ": received null second template");
            throw new IllegalArgumentException("Received null second template");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            iMatchTemplatesNative = matchTemplatesNative(template, template2, nativeError);
        }
        handleError(nativeError);
        return iMatchTemplatesNative;
    }

    public void setMatchingLevel(int i) throws IBMatcherException {
        NativeError nativeError = new NativeError();
        synchronized (this) {
            setMatchingLevelNative(i, nativeError);
        }
        handleError(nativeError);
    }

    public int getMatchingLevel() throws IBMatcherException {
        int matchingLevelNative;
        NativeError nativeError = new NativeError();
        synchronized (this) {
            matchingLevelNative = getMatchingLevelNative(nativeError);
        }
        handleError(nativeError);
        return matchingLevelNative;
    }

    public Template singleEnrollment(IBCommon.ImageDataExt imageDataExt, IBCommon.ImageDataExt imageDataExt2, IBCommon.ImageDataExt imageDataExt3) throws IBMatcherException {
        Template templateSingleEnrollmentNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null first image");
            throw new IllegalArgumentException("Received null first image");
        }
        if (imageDataExt2 == null) {
            logPrintWarning(getMethodName() + ": received null second image");
            throw new IllegalArgumentException("Received null second image");
        }
        if (imageDataExt3 == null) {
            logPrintWarning(getMethodName() + ": received null third image");
            throw new IllegalArgumentException("Received null third image");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            templateSingleEnrollmentNative = singleEnrollmentNative(imageDataExt, imageDataExt2, imageDataExt3, nativeError);
        }
        handleError(nativeError);
        return templateSingleEnrollmentNative;
    }

    public Template[] multiEnrollment(IBCommon.ImageDataExt imageDataExt, IBCommon.ImageDataExt imageDataExt2, IBCommon.ImageDataExt imageDataExt3, IBCommon.ImageDataExt imageDataExt4, IBCommon.ImageDataExt imageDataExt5, IBCommon.ImageDataExt imageDataExt6) throws IBMatcherException {
        Template[] templateArrMultiEnrollmentNative;
        if (imageDataExt == null) {
            logPrintWarning(getMethodName() + ": received null first image");
            throw new IllegalArgumentException("Received null first image");
        }
        if (imageDataExt2 == null) {
            logPrintWarning(getMethodName() + ": received null second image");
            throw new IllegalArgumentException("Received null second image");
        }
        if (imageDataExt3 == null) {
            logPrintWarning(getMethodName() + ": received null third image");
            throw new IllegalArgumentException("Received null third image");
        }
        if (imageDataExt4 == null) {
            logPrintWarning(getMethodName() + ": received null fourth image");
            throw new IllegalArgumentException("Received null fourth image");
        }
        if (imageDataExt5 == null) {
            logPrintWarning(getMethodName() + ": received null fifth image");
            throw new IllegalArgumentException("Received null fifth image");
        }
        if (imageDataExt6 == null) {
            logPrintWarning(getMethodName() + ": received null sixth image");
            throw new IllegalArgumentException("Received null sixth image");
        }
        NativeError nativeError = new NativeError();
        synchronized (this) {
            templateArrMultiEnrollmentNative = multiEnrollmentNative(imageDataExt, imageDataExt2, imageDataExt3, imageDataExt4, imageDataExt5, imageDataExt6, nativeError);
        }
        handleError(nativeError);
        return templateArrMultiEnrollmentNative;
    }

    public static synchronized IBMatcher getInstance() {
        if (m_instance == null) {
            m_instance = new IBMatcher();
        }
        return m_instance;
    }

    protected static final class NativeError {
        public int code = 0;

        protected NativeError() {
        }
    }

    private IBMatcher() {
    }

    private static void handleError(NativeError nativeError) throws IBMatcherException {
        if (nativeError.code != 0) {
            IBMatcherException.Type typeFromCode = IBMatcherException.Type.fromCode(nativeError.code);
            if (typeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized error code (" + nativeError.code + ") returned from native code");
                typeFromCode = IBMatcherException.Type.COMMAND_FAILED;
            }
            throw new IBMatcherException(typeFromCode);
        }
    }

    private static void logPrintWarning(String str) {
        Log.w("IBMatcher", str);
    }

    private static void logPrintError(String str) {
        Log.e("IBMatcher", str);
    }

    private static String getMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = METHOD_STACK_INDEX;
        return length > i ? stackTrace[i].getMethodName() : "?";
    }

    static {
        int i = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            i++;
            if (stackTraceElement.getClassName().equals(IBMatcher.class.getName())) {
                break;
            }
        }
        METHOD_STACK_INDEX = i;
        System.loadLibrary("ibscanmatcher");
        System.loadLibrary("ibscanmatcherjni");
    }
}
