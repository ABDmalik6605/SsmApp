package com.integratedbiometrics.ibscanultimate;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.integratedbiometrics.ibscancommon.IBCommon;
import com.integratedbiometrics.ibscanultimate.IBScanException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class IBScanDevice {
    public static final long IBSU_FINGER_ALL = 1023;
    public static final long IBSU_FINGER_BOTH_THUMBS = 48;
    public static final long IBSU_FINGER_LEFT_HAND = 15;
    public static final long IBSU_FINGER_LEFT_INDEX = 8;
    public static final long IBSU_FINGER_LEFT_LITTLE = 1;
    public static final long IBSU_FINGER_LEFT_LITTLE_RING = 3;
    public static final long IBSU_FINGER_LEFT_MIDDLE = 4;
    public static final long IBSU_FINGER_LEFT_MIDDLE_INDEX = 12;
    public static final long IBSU_FINGER_LEFT_RING = 2;
    public static final long IBSU_FINGER_LEFT_THUMB = 16;
    public static final long IBSU_FINGER_NONE = 0;
    public static final long IBSU_FINGER_RIGHT_HAND = 960;
    public static final long IBSU_FINGER_RIGHT_INDEX = 64;
    public static final long IBSU_FINGER_RIGHT_INDEX_MIDDLE = 192;
    public static final long IBSU_FINGER_RIGHT_LITTLE = 512;
    public static final long IBSU_FINGER_RIGHT_MIDDLE = 128;
    public static final long IBSU_FINGER_RIGHT_RING = 256;
    public static final long IBSU_FINGER_RIGHT_RING_LITTLE = 768;
    public static final long IBSU_FINGER_RIGHT_THUMB = 32;
    public static final long IBSU_LED_F_BLINK_GREEN = 268435456;
    public static final long IBSU_LED_F_BLINK_RED = 536870912;
    public static final long IBSU_LED_F_LEFT_INDEX_GREEN = 4194304;
    public static final long IBSU_LED_F_LEFT_INDEX_RED = 8388608;
    public static final long IBSU_LED_F_LEFT_LITTLE_GREEN = 16777216;
    public static final long IBSU_LED_F_LEFT_LITTLE_RED = 33554432;
    public static final long IBSU_LED_F_LEFT_MIDDLE_GREEN = 1048576;
    public static final long IBSU_LED_F_LEFT_MIDDLE_RED = 2097152;
    public static final long IBSU_LED_F_LEFT_RING_GREEN = 67108864;
    public static final long IBSU_LED_F_LEFT_RING_RED = 134217728;
    public static final long IBSU_LED_F_LEFT_THUMB_GREEN = 65536;
    public static final long IBSU_LED_F_LEFT_THUMB_RED = 131072;
    public static final long IBSU_LED_F_PROGRESS_LEFT_HAND = 32;
    public static final long IBSU_LED_F_PROGRESS_RIGHT_HAND = 128;
    public static final long IBSU_LED_F_PROGRESS_ROLL = 16;
    public static final long IBSU_LED_F_PROGRESS_TWO_THUMB = 64;
    public static final long IBSU_LED_F_RIGHT_INDEX_GREEN = 4096;
    public static final long IBSU_LED_F_RIGHT_INDEX_RED = 8192;
    public static final long IBSU_LED_F_RIGHT_LITTLE_GREEN = 1024;
    public static final long IBSU_LED_F_RIGHT_LITTLE_RED = 2048;
    public static final long IBSU_LED_F_RIGHT_MIDDLE_GREEN = 16384;
    public static final long IBSU_LED_F_RIGHT_MIDDLE_RED = 1073741824;
    public static final long IBSU_LED_F_RIGHT_RING_GREEN = 256;
    public static final long IBSU_LED_F_RIGHT_RING_RED = 512;
    public static final long IBSU_LED_F_RIGHT_THUMB_GREEN = 262144;
    public static final long IBSU_LED_F_RIGHT_THUMB_RED = 524288;
    public static final int LED_INIT_BLUE = 1;
    public static final int LED_NONE = 0;
    public static final int LED_SCAN_CURVE_BLUE = 64;
    public static final int LED_SCAN_CURVE_GREEN = 32;
    public static final int LED_SCAN_CURVE_RED = 16;
    public static final int LED_SCAN_GREEN = 2;
    public static final int MAX_CONTRAST_VALUE = 34;
    private static int METHOD_STACK_INDEX = 0;
    public static final int MIN_CONTRAST_VALUE = 0;
    public static final int OPTION_AUTO_CAPTURE = 2;
    public static final int OPTION_AUTO_CONTRAST = 1;
    public static final int OPTION_IGNORE_FINGER_COUNT = 4;
    private final long m_handleNative;
    private IBScanDeviceListener m_listener = null;
    private boolean m_isOpened = true;

    private native Object ConvertImageToISOANSINative(IBCommon.ImageDataExt[] imageDataExtArr, int i, int i2, int i3, NativeError nativeError);

    private native int GetSpoofScoreNative(String str, ImageData imageData, NativeError nativeError);

    private native boolean IsSpoofFingerDetectedNative(ImageData imageData, NativeError nativeError);

    private native int SaveBitmapImageNative(String str, byte[] bArr, int i, int i2, int i3, double d, double d2, NativeError nativeError);

    private native int SaveJP2ImageNative(String str, byte[] bArr, int i, int i2, int i3, double d, double d2, int i4, NativeError nativeError);

    private native int SavePngImageNative(String str, byte[] bArr, int i, int i2, int i3, double d, double d2, NativeError nativeError);

    private native int SaveRAWImageNative(byte[] bArr, int i, String str, NativeError nativeError);

    private native int SaveStandardFileNative(IBCommon.StandardFormatData standardFormatData, String str, NativeError nativeError);

    private native int SetEncryptionKeyNative(byte[] bArr, int i, NativeError nativeError);

    private native int addFingerImageNative(ImageData imageData, long j, int i, boolean z, NativeError nativeError);

    private native void beginCaptureImageNative(int i, int i2, int i3, NativeError nativeError);

    private native int calculateNfiqScoreNative(ImageData imageData, NativeError nativeError);

    private native void cancelCaptureImageNative(NativeError nativeError);

    private native Object[] captureImageExtendedNative(NativeError nativeError);

    private native void captureImageManuallyNative(NativeError nativeError);

    private native Object[] captureImageNative(NativeError nativeError);

    private native void closeNative(NativeError nativeError);

    private native void createBmpExNative(byte[] bArr, Bitmap bitmap, NativeError nativeError);

    private native void enableEventNative(int i, boolean z, NativeError nativeError);

    private native int generateDisplayImageNative(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte b, int i5, int i6, boolean z, NativeError nativeError);

    private native int generateZoomOutImageExNative(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte b, NativeError nativeError);

    private native Object[] getCombineImageExNative(ImageData imageData, ImageData imageData2, int i, NativeError nativeError);

    private native Object getCombineImageNative(ImageData imageData, ImageData imageData2, int i, NativeError nativeError);

    private native int getContrastNative(NativeError nativeError);

    private native Object[] getEnhancedImageReservedNative(String str, ImageData imageData, NativeError nativeError);

    private native long getLEDsNative(NativeError nativeError);

    private native int getLEOperationModeNative(NativeError nativeError);

    private native int getOperableBeeperNative(NativeError nativeError);

    private native LedState getOperableLEDsNative(NativeError nativeError);

    private native int getPlatenStateAtCaptureNative(NativeError nativeError);

    private native String getPropertyNative(int i, NativeError nativeError);

    private native Object[] getResultImageExtNative(int i, NativeError nativeError);

    private native RollingData getRollingInfoNative(NativeError nativeError);

    private native boolean isCaptureActiveNative(NativeError nativeError);

    private native boolean isCaptureAvailableNative(int i, int i2, NativeError nativeError);

    private native long isFingerDuplicatedNative(ImageData imageData, long j, int i, int i2, NativeError nativeError);

    private native boolean isFingerTouchingNative(NativeError nativeError);

    private native boolean isValidFingerGeometryNative(ImageData imageData, long j, int i, NativeError nativeError);

    private native int removeFingerImageNative(long j, NativeError nativeError);

    private native int setBeeperNative(int i, int i2, int i3, int i4, int i5, NativeError nativeError);

    private native void setContrastNative(int i, NativeError nativeError);

    private native void setLEDsNative(long j, NativeError nativeError);

    private native void setLEOperationModeNative(int i, NativeError nativeError);

    private native void setPropertyNative(int i, String str, NativeError nativeError);

    private native void setPropertyReservedNative(String str, int i, String str2, NativeError nativeError);

    private native Object[] wsqDecodeToMemNative(byte[] bArr, int i, NativeError nativeError);

    private native int wsqEncodeToFileNative(String str, byte[] bArr, int i, int i2, int i3, int i4, int i5, double d, String str2, NativeError nativeError);

    private native Object[] wsqEncodeToMemNative(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d, String str, NativeError nativeError);

    public enum PropertyId {
        PRODUCT_ID(0),
        SERIAL_NUMBER(1),
        VENDOR_ID(2),
        IBIA_VENDOR_ID(3),
        IBIA_VERSION(4),
        IBIA_DEVICE_ID(5),
        FIRMWARE(6),
        REVISION(7),
        PRODUCTION_DATE(8),
        SERVICE_DATE(9),
        IMAGE_WIDTH(10),
        IMAGE_HEIGHT(11),
        IGNORE_FINGER_TIME(12),
        RECOMMENDED_LEVEL(13),
        POLLINGTIME_TO_BGETIMAGE(14),
        ENABLE_POWER_SAVE_MODE(15),
        RETRY_WRONG_COMMUNICATION(16),
        CAPTURE_TIMEOUT(17),
        ROLL_MIN_WIDTH(18),
        ROLL_MODE(19),
        ROLL_LEVEL(20),
        CAPTURE_AREA_THRESHOLD(21),
        ENABLE_DECIMATION(22),
        ENABLE_CAPTURE_ON_RELEASE(23),
        DEVICE_INDEX(24),
        DEVICE_ID(25),
        SUPER_DRY_MODE(26),
        MIN_CAPTURE_TIME_IN_SUPER_DRY_MODE(27),
        ROLLED_IMAGE_WIDTH(28),
        ROLLED_IMAGE_HEIGHT(29),
        NO_PREVIEW_IMAGE(30),
        ROLL_IMAGE_OVERRIDE(31),
        WARNING_MESSAGE_INVALID_AREA(32),
        ENABLE_WET_FINGER_DETECT(33),
        WET_FINGER_DETECT_LEVEL(34),
        WET_FINGER_DETECT_LEVEL_THRESHOLD(35),
        START_POSITION_OF_ROLLING_AREA(36),
        START_ROLL_WITHOUT_LOCK(37),
        ENABLE_TOF(38),
        ENABLE_ENCRYPTION(39),
        IS_SPOOF_SUPPORTED(40),
        ENABLE_SPOOF(41),
        SPOOF_LEVEL(42),
        VIEW_ENCRYPTION_IMAGE_MODE(43),
        FINGERPRINT_SEGMENTATION_MODE(44),
        ROLL_METHOD(45),
        RENEWAL_OPPOSITE_IMGAE_LEVEL(46),
        PREVIEW_IMAGE_QUALITY_FOR_KOJAK(47),
        ADAPTIVE_CAPTURE_MODE(48),
        ENABLE_KOJAK_BEHAVIOR_2_6(49),
        DISABLE_SEGMENT_ROTATION(50),
        RESERVED_1(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION),
        RESERVED_2(201),
        RESERVED_100(202),
        RESERVED_IMAGE_PROCESS_THRESHOLD(400),
        RESERVED_ENABLE_TOF_FOR_ROLL(TypedValues.CycleType.TYPE_CURVE_FIT),
        RESERVED_CAPTURE_BRIGHTNESS_THRESHOLD_FOR_FLAT(TypedValues.CycleType.TYPE_VISIBILITY),
        RESERVED_CAPTURE_BRIGHTNESS_THRESHOLD_FOR_ROLL(TypedValues.CycleType.TYPE_ALPHA),
        RESERVED_ENHANCED_RESULT_IMAGE(404),
        RESERVED_ENHANCED_RESULT_IMAGE_LEVEL(405),
        RESERVED_ENABLE_SLIP_DETECTION(406),
        RESERVED_SLIP_DETECTION_LEVEL(407),
        RESERVED_ENABLE_TRICK_CAPTURE(408),
        RESERVED_SPOOF_LEVEL_THRESHOLD(409);

        private final int code;

        PropertyId(int i) {
            this.code = i;
        }

        protected static PropertyId fromCode(int i) {
            for (PropertyId propertyId : values()) {
                if (propertyId.code == i) {
                    return propertyId;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum ImageType {
        TYPE_NONE(0, "Unsupported image type"),
        ROLL_SINGLE_FINGER(1, "One-finger rolled fingerprint"),
        FLAT_SINGLE_FINGER(2, "One-finger flat fingerprint"),
        FLAT_TWO_FINGERS(3, "Two-finger flat fingerprint"),
        FLAT_FOUR_FINGERS(4, "Four-finger flat fingerprint"),
        FLAT_THREE_FINGERS(5, "Three-finger flat fingerprint");

        private final int code;
        private final String description;

        ImageType(int i, String str) {
            this.code = i;
            this.description = str;
        }

        protected static ImageType fromCode(int i) {
            for (ImageType imageType : values()) {
                if (imageType.code == i) {
                    return imageType;
                }
            }
            return TYPE_NONE;
        }

        protected int toCode() {
            return this.code;
        }

        public String toDescription() {
            return this.description;
        }
    }

    public enum ImageResolution {
        RESOLUTION_500(500),
        RESOLUTION_1000(1000);

        private final int code;

        ImageResolution(int i) {
            this.code = i;
        }

        protected static ImageResolution fromCode(int i) {
            for (ImageResolution imageResolution : values()) {
                if (imageResolution.code == i) {
                    return imageResolution;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum LEOperationMode {
        AUTO(0),
        ON(1),
        OFF(2);

        private final int code;

        LEOperationMode(int i) {
            this.code = i;
        }

        protected static LEOperationMode fromCode(int i) {
            for (LEOperationMode lEOperationMode : values()) {
                if (lEOperationMode.code == i) {
                    return lEOperationMode;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum LedType {
        NONE(0),
        TSCAN(1),
        FSCAN(2);

        private final int code;

        LedType(int i) {
            this.code = i;
        }

        protected static LedType fromCode(int i) {
            for (LedType ledType : values()) {
                if (ledType.code == i) {
                    return ledType;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public static class LedState {
        public final int ledCount;
        public final LedType ledType;
        public final long operableLEDs;

        protected LedState(int i, int i2, long j) {
            LedType ledTypeFromCode = LedType.fromCode(i);
            this.ledType = ledTypeFromCode;
            this.ledCount = i2;
            this.operableLEDs = j;
            if (ledTypeFromCode == null) {
                IBScanDevice.logPrintError(IBScanDevice.getMethodName() + ": unrecognized ledType code(" + i + ") received from native code");
            }
        }

        public String toString() {
            return "LED type = " + this.ledType + "\nLED count = " + this.ledCount + "\nOperable LEDs = " + String.format("%1$08X", Long.valueOf(this.operableLEDs)) + "\n";
        }
    }

    public enum ImageFormat {
        GRAY(0),
        RGB24(1),
        RGB32(2),
        UNKNOWN(3);

        private final int code;

        ImageFormat(int i) {
            this.code = i;
        }

        protected static ImageFormat fromCode(int i) {
            for (ImageFormat imageFormat : values()) {
                if (imageFormat.code == i) {
                    return imageFormat;
                }
            }
            return UNKNOWN;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum EncyptionMode {
        ENCRYPTION_KEY_RANDOM(0),
        ENCRYPTION_KEY_CUSTOM(1);

        private final int code;

        EncyptionMode(int i) {
            this.code = i;
        }

        protected static EncyptionMode fromCode(int i) {
            for (EncyptionMode encyptionMode : values()) {
                if (encyptionMode.code == i) {
                    return encyptionMode;
                }
            }
            return null;
        }

        public int toCode() {
            return this.code;
        }
    }

    public static class WsqImage {
        public final byte[] buffer;
        public final int len;

        protected WsqImage(byte[] bArr, int i) {
            this.buffer = bArr;
            this.len = i;
        }
    }

    public static class ImageData {
        private static final int COMPLETE_ACQUISITION_COLOR = -16711936;
        private static final int QUALITY_ARROW_COLOR = -16776961;
        private static final int QUALITY_ARROW_HEIGHT = 10;
        private static final int QUALITY_ARROW_LENGTH = 40;
        private static final int TAKE_ACQUISITION_COLOR = -65536;
        private static final int TARGET_LINE_WIDTH = 2;
        public final short bitsPerPixel;
        public final byte[] buffer;
        public final ImageFormat format;
        public final double frameTime;
        public final int height;
        public final boolean isFinal;
        public final int pitch;
        public final int processThres;
        public final double resolutionX;
        public final double resolutionY;
        public final int width;

        protected ImageData(byte[] bArr, int i, int i2, double d, double d2, double d3, int i3, short s, int i4, boolean z, int i5) {
            this.buffer = bArr;
            this.width = i;
            this.height = i2;
            this.resolutionX = d;
            this.resolutionY = d2;
            this.frameTime = d3;
            this.pitch = i3;
            this.bitsPerPixel = s;
            ImageFormat imageFormatFromCode = ImageFormat.fromCode(i4);
            this.format = imageFormatFromCode;
            this.isFinal = z;
            this.processThres = i5;
            if (imageFormatFromCode == null) {
                IBScanDevice.logPrintError(IBScanDevice.getMethodName() + ": unrecognized format code(" + i4 + ") received from native code");
            }
        }

        public Bitmap toBitmap() {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
            if (bitmapCreateBitmap != null) {
                byte[] bArr = new byte[this.width * this.height * 4];
                for (int i = 0; i < this.height; i++) {
                    int i2 = 0;
                    while (true) {
                        int i3 = this.width;
                        if (i2 < i3) {
                            byte b = this.buffer[(((this.height - i) - 1) * i3) + i2];
                            bArr[(((i * i3) + i2) * 4) + 2] = b;
                            bArr[(((i * i3) + i2) * 4) + 1] = b;
                            bArr[((i * i3) + i2) * 4] = b;
                            bArr[(((i3 * i) + i2) * 4) + 3] = -1;
                            i2++;
                        }
                    }
                }
                bitmapCreateBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
            }
            return bitmapCreateBitmap;
        }

        public Bitmap toBitmapScaled(int i, int i2) {
            if (i <= 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            Bitmap bitmap = toBitmap();
            if (bitmap != null) {
                return Bitmap.createScaledBitmap(bitmap, i, i2, false);
            }
            return null;
        }

        public Bitmap toBitmapScaled(int i, int i2, RollingState rollingState, int i3) {
            if (i <= 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (rollingState == null) {
                throw new IllegalArgumentException();
            }
            Bitmap bitmapScaled = toBitmapScaled(i, i2);
            if (bitmapScaled != null) {
                drawRollingLine(bitmapScaled, i, i2, rollingState, i3, 2);
            }
            return bitmapScaled;
        }

        public Bitmap toBitmapScaled(int i, int i2, boolean z, boolean z2, boolean z3) {
            if (i <= 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            Bitmap bitmapScaled = toBitmapScaled(i, i2);
            if (bitmapScaled != null) {
                drawQualityArrow(bitmapScaled, i, i2, z, z2, z3);
            }
            return bitmapScaled;
        }

        public Bitmap toBitmapScaled(int i, int i2, RollingState rollingState, int i3, boolean z, boolean z2, boolean z3) {
            if (i <= 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (rollingState == null) {
                throw new IllegalArgumentException();
            }
            Bitmap bitmapScaled = toBitmapScaled(i, i2);
            if (bitmapScaled != null) {
                drawRollingLine(bitmapScaled, i, i2, rollingState, i3, 2);
                drawQualityArrow(bitmapScaled, i, i2, z, z2, z3);
            }
            return bitmapScaled;
        }

        public Bitmap toBitmapScaled(int i, int i2, RollingState rollingState, int i3, int i4, boolean z, boolean z2, boolean z3) {
            if (i <= 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (rollingState == null) {
                throw new IllegalArgumentException();
            }
            if (i4 < 1 || i4 > 6) {
                throw new IllegalArgumentException();
            }
            Bitmap bitmapScaled = toBitmapScaled(i, i2);
            if (bitmapScaled != null) {
                drawRollingLine(bitmapScaled, i, i2, rollingState, i3, i4);
                drawQualityArrow(bitmapScaled, i, i2, z, z2, z3);
            }
            return bitmapScaled;
        }

        public boolean saveToFile(File file, String str) throws IOException {
            Bitmap bitmap;
            if (file == null || str == null) {
                throw new IllegalArgumentException();
            }
            Bitmap.CompressFormat compressFormatValueOf = Bitmap.CompressFormat.valueOf(str.toUpperCase());
            if (compressFormatValueOf == null || (bitmap = toBitmap()) == null) {
                return false;
            }
            return bitmap.compress(compressFormatValueOf, 100, new FileOutputStream(file));
        }

        private void drawRollingLine(Bitmap bitmap, int i, int i2, RollingState rollingState, int i3, int i4) {
            if ((rollingState.equals(RollingState.TAKE_ACQUISITION) || rollingState.equals(RollingState.COMPLETE_ACQUISITION)) && i3 >= 0) {
                int i5 = (i3 * i) / this.width;
                int i6 = rollingState.equals(RollingState.TAKE_ACQUISITION) ? -65536 : COMPLETE_ACQUISITION_COLOR;
                for (int i7 = 0; i7 < i2; i7++) {
                    int i8 = i5 - (i4 / 2);
                    for (int i9 = i8; i9 < i8 + i4; i9++) {
                        if (i9 >= 0 && i9 < i) {
                            bitmap.setPixel(i9, i7, i6);
                        }
                    }
                }
            }
        }

        public void drawQualityArrow(Bitmap bitmap, int i, int i2, boolean z, boolean z2, boolean z3) {
            if (z2) {
                for (int i3 = 0; i3 < 40; i3++) {
                    int i4 = i2 / 2;
                    for (int i5 = i4 - 5; i5 < i4 + 5; i5++) {
                        if (i5 > 0 && i5 < i2) {
                            bitmap.setPixel(i3, i5, QUALITY_ARROW_COLOR);
                        }
                    }
                }
            }
            if (z3) {
                for (int i6 = (i - 40) - 1; i6 < i; i6++) {
                    int i7 = i2 / 2;
                    for (int i8 = i7 - 5; i8 < i7 + 5; i8++) {
                        if (i8 > 0 && i8 < i2) {
                            bitmap.setPixel(i6, i8, QUALITY_ARROW_COLOR);
                        }
                    }
                }
            }
            if (z) {
                for (int i9 = 0; i9 < 40; i9++) {
                    int i10 = i / 2;
                    for (int i11 = i10 - 5; i11 < i10 + 5; i11++) {
                        if (i11 > 0 && i11 < i) {
                            bitmap.setPixel(i11, i9, QUALITY_ARROW_COLOR);
                        }
                    }
                }
            }
        }
    }

    public enum FingerCountState {
        FINGER_COUNT_OK(0),
        TOO_MANY_FINGERS(1),
        TOO_FEW_FINGERS(2),
        NON_FINGER(3);

        private final int code;

        FingerCountState(int i) {
            this.code = i;
        }

        protected static FingerCountState fromCode(int i) {
            for (FingerCountState fingerCountState : values()) {
                if (fingerCountState.code == i) {
                    return fingerCountState;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum FingerQualityState {
        FINGER_NOT_PRESENT(0),
        GOOD(1),
        FAIR(2),
        POOR(3),
        INVALID_AREA_TOP(4),
        INVALID_AREA_LEFT(5),
        INVALID_AREA_RIGHT(6),
        INVALID_AREA_BOTTOM(7);

        private final int code;

        FingerQualityState(int i) {
            this.code = i;
        }

        protected static FingerQualityState fromCode(int i) {
            for (FingerQualityState fingerQualityState : values()) {
                if (fingerQualityState.code == i) {
                    return fingerQualityState;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum PlatenState {
        CLEARD(0),
        HAS_FINGERS(1);

        private final int code;

        PlatenState(int i) {
            this.code = i;
        }

        protected static PlatenState fromCode(int i) {
            for (PlatenState platenState : values()) {
                if (platenState.code == i) {
                    return platenState;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum RollingState {
        NOT_PRESENT(0),
        TAKE_ACQUISITION(1),
        COMPLETE_ACQUISITION(2),
        RESULT_IMAGE(3);

        private final int code;

        RollingState(int i) {
            this.code = i;
        }

        protected static RollingState fromCode(int i) {
            for (RollingState rollingState : values()) {
                if (rollingState.code == i) {
                    return rollingState;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public static class RollingData {
        public final int rollingLineX;
        public final RollingState rollingState;

        protected RollingData(int i, int i2) {
            RollingState rollingStateFromCode = RollingState.fromCode(i);
            this.rollingState = rollingStateFromCode;
            this.rollingLineX = i2;
            if (rollingStateFromCode == null) {
                IBScanDevice.logPrintError(IBScanDevice.getMethodName() + ": unrecognized rollingState code(" + i + ") received from native code");
            }
        }
    }

    public enum BeeperType {
        BEEPER_TYPE_NONE(0),
        BEEPER_TYPE_MONOTONE(1);

        private final int code;

        BeeperType(int i) {
            this.code = i;
        }

        protected static BeeperType fromCode(int i) {
            for (BeeperType beeperType : values()) {
                if (beeperType.code == i) {
                    return beeperType;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum BeepPattern {
        BEEP_PATTERN_GENERIC(0),
        BEEP_PATTERN_REPEAT(1);

        private final int code;

        BeepPattern(int i) {
            this.code = i;
        }

        protected static BeepPattern fromCode(int i) {
            for (BeepPattern beepPattern : values()) {
                if (beepPattern.code == i) {
                    return beepPattern;
                }
            }
            return null;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public static class SegmentPosition {
        public final int x1;
        public final int x2;
        public final int x3;
        public final int x4;
        public final int y1;
        public final int y2;
        public final int y3;
        public final int y4;

        protected SegmentPosition(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.x1 = i;
            this.y1 = i2;
            this.x2 = i3;
            this.y2 = i4;
            this.x3 = i5;
            this.y3 = i6;
            this.x4 = i7;
            this.y4 = i8;
        }
    }

    public enum EventType {
        COMMUNICATION_BROKEN(1),
        PREVIEW_IMAGE_AVAILABLE(2),
        ACQUISITION_BEGUN(3),
        ACQUISITION_COMPLETED(4),
        RESULT_IMAGE_AVAILABLE(5),
        FINGER_QUALITY_CHANGED(6),
        FINGER_COUNT_CHANGED(7),
        PLATEN_STATE_CHANGED(9),
        WARNING_RECEIVED(11),
        RESULT_IMAGE_EXTENDED_AVAILABLE(12),
        KEYBUTTON(13);

        private final int code;

        EventType(int i) {
            this.code = i;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public enum CombineImageWhichHand {
        COMBINE_IMAGE_LEFT_HAND(0),
        COMBINE_IMAGE_RIGHT_HAND(1);

        private final int code;

        CombineImageWhichHand(int i) {
            this.code = i;
        }

        protected int toCode() {
            return this.code;
        }
    }

    public void close() throws IBScanException {
        NativeError nativeError = new NativeError();
        closeNative(nativeError);
        handleError(nativeError);
        this.m_isOpened = false;
    }

    public boolean isOpened() {
        return this.m_isOpened;
    }

    public void enableEvent(EventType eventType, boolean z) throws IBScanException {
        if (eventType == null) {
            logPrintWarning(getMethodName() + ": received null event");
            throw new IllegalArgumentException("Received null event");
        }
        NativeError nativeError = new NativeError();
        enableEventNative(eventType.toCode(), z, nativeError);
        handleError(nativeError);
    }

    public void setProperty(PropertyId propertyId, String str) throws IBScanException {
        if (propertyId == null) {
            logPrintWarning(getMethodName() + ": received null propertyId");
            throw new IllegalArgumentException("Received null propertyId");
        }
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null propertyValue");
            throw new IllegalArgumentException("Received null propertyValue");
        }
        NativeError nativeError = new NativeError();
        setPropertyNative(propertyId.toCode(), str, nativeError);
        handleError(nativeError);
    }

    public void setPropertyReserved(String str, PropertyId propertyId, String str2) throws IBScanException {
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null reservedKey");
            throw new IllegalArgumentException("Received null reservedKey");
        }
        if (propertyId == null) {
            logPrintWarning(getMethodName() + ": received null propertyId");
            throw new IllegalArgumentException("Received null propertyId");
        }
        if (str2 == null) {
            logPrintWarning(getMethodName() + ": received null propertyValue");
            throw new IllegalArgumentException("Received null propertyValue");
        }
        NativeError nativeError = new NativeError();
        setPropertyReservedNative(str, propertyId.toCode(), str2, nativeError);
        handleError(nativeError);
    }

    public String getProperty(PropertyId propertyId) throws IBScanException {
        if (propertyId == null) {
            logPrintWarning(getMethodName() + ": received null propertyId");
            throw new IllegalArgumentException("Received null propertyId");
        }
        NativeError nativeError = new NativeError();
        String propertyNative = getPropertyNative(propertyId.toCode(), nativeError);
        handleError(nativeError);
        return propertyNative;
    }

    public boolean isCaptureAvailable(ImageType imageType, ImageResolution imageResolution) throws IBScanException {
        if (imageType == null) {
            logPrintWarning(getMethodName() + ": received null imageType");
            throw new IllegalArgumentException("Received null imageType");
        }
        if (imageResolution == null) {
            logPrintWarning(getMethodName() + ": received null imageResolution");
            throw new IllegalArgumentException("Received null imageResolution");
        }
        NativeError nativeError = new NativeError();
        boolean zIsCaptureAvailableNative = isCaptureAvailableNative(imageType.toCode(), imageResolution.toCode(), nativeError);
        handleError(nativeError);
        return zIsCaptureAvailableNative;
    }

    public void beginCaptureImage(ImageType imageType, ImageResolution imageResolution, int i) throws IBScanException {
        if (imageType == null) {
            logPrintWarning(getMethodName() + ": received null imageType");
            throw new IllegalArgumentException("Received null imageType");
        }
        if (imageResolution == null) {
            logPrintWarning(getMethodName() + ": received null imageResolution");
            throw new IllegalArgumentException("Received null imageResolution");
        }
        NativeError nativeError = new NativeError();
        beginCaptureImageNative(imageType.toCode(), imageResolution.toCode(), i, nativeError);
        handleError(nativeError);
    }

    public void cancelCaptureImage() throws IBScanException {
        NativeError nativeError = new NativeError();
        cancelCaptureImageNative(nativeError);
        handleError(nativeError);
    }

    public boolean isCaptureActive() throws IBScanException {
        NativeError nativeError = new NativeError();
        boolean zIsCaptureActiveNative = isCaptureActiveNative(nativeError);
        handleError(nativeError);
        return zIsCaptureActiveNative;
    }

    public void captureImageManually() throws IBScanException {
        NativeError nativeError = new NativeError();
        captureImageManuallyNative(nativeError);
        handleError(nativeError);
    }

    public int getContrast() throws IBScanException {
        NativeError nativeError = new NativeError();
        int contrastNative = getContrastNative(nativeError);
        handleError(nativeError);
        return contrastNative;
    }

    public void setContrast(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        setContrastNative(i, nativeError);
        handleError(nativeError);
    }

    public void setLEOperationMode(LEOperationMode lEOperationMode) throws IBScanException {
        if (lEOperationMode == null) {
            logPrintWarning(getMethodName() + ": received null leOperationMode");
            throw new IllegalArgumentException("Received null leOperationMode");
        }
        NativeError nativeError = new NativeError();
        setLEOperationModeNative(lEOperationMode.toCode(), nativeError);
        handleError(nativeError);
    }

    public LEOperationMode getLEOperationMode() throws IBScanException {
        NativeError nativeError = new NativeError();
        int lEOperationModeNative = getLEOperationModeNative(nativeError);
        handleError(nativeError);
        LEOperationMode lEOperationModeFromCode = LEOperationMode.fromCode(lEOperationModeNative);
        if (lEOperationModeFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized leOperationMode code (" + lEOperationModeNative + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return lEOperationModeFromCode;
    }

    public boolean isFingerTouching() throws IBScanException {
        NativeError nativeError = new NativeError();
        boolean zIsFingerTouchingNative = isFingerTouchingNative(nativeError);
        handleError(nativeError);
        return zIsFingerTouchingNative;
    }

    public LedState getOperableLEDs() throws IBScanException {
        NativeError nativeError = new NativeError();
        LedState operableLEDsNative = getOperableLEDsNative(nativeError);
        handleError(nativeError);
        if (operableLEDsNative == null || operableLEDsNative.ledType == null) {
            logPrintError(getMethodName() + ": null or invalid ledState returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return operableLEDsNative;
    }

    public long getLEDs() throws IBScanException {
        NativeError nativeError = new NativeError();
        long lEDsNative = getLEDsNative(nativeError);
        handleError(nativeError);
        return lEDsNative;
    }

    public void setLEDs(long j) throws IBScanException {
        NativeError nativeError = new NativeError();
        setLEDsNative(j, nativeError);
        handleError(nativeError);
    }

    public Object[] captureImage() throws IBScanException {
        NativeError nativeError = new NativeError();
        Object[] objArrCaptureImageNative = captureImageNative(nativeError);
        handleError(nativeError);
        boolean z = false;
        if (objArrCaptureImageNative == null || objArrCaptureImageNative.length != 5 || objArrCaptureImageNative[0] == null || objArrCaptureImageNative[1] == null || objArrCaptureImageNative[2] == null || objArrCaptureImageNative[3] == null || objArrCaptureImageNative[4] == null) {
            logPrintError(getMethodName() + ": null or invalid image information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        Object[] objArr = new Object[5];
        int[] iArr = (int[]) objArrCaptureImageNative[4];
        ImageType imageTypeFromCode = ImageType.fromCode(((Integer) objArrCaptureImageNative[1]).intValue());
        FingerCountState fingerCountStateFromCode = FingerCountState.fromCode(((Integer) objArrCaptureImageNative[3]).intValue());
        objArr[0] = objArrCaptureImageNative[0];
        objArr[1] = imageTypeFromCode;
        objArr[2] = objArrCaptureImageNative[2];
        objArr[3] = fingerCountStateFromCode;
        FingerQualityState[] fingerQualityStateArr = new FingerQualityState[iArr.length];
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                z = true;
                break;
            }
            fingerQualityStateArr[i] = FingerQualityState.fromCode(iArr[i]);
            if (fingerQualityStateArr[i] == null) {
                logPrintError(getMethodName() + ": unrecognized fingerQuality code (" + iArr[i] + ") returned from native code");
                break;
            }
            i++;
        }
        objArr[4] = fingerQualityStateArr;
        if (fingerCountStateFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized fingerCountState code (" + ((Integer) objArrCaptureImageNative[3]).intValue() + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        } else if (imageTypeFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized imageType code (" + ((Integer) objArrCaptureImageNative[1]).intValue() + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        } else if (!z) {
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return objArr;
    }

    public Object[] captureImageExtended() throws IBScanException {
        boolean z;
        NativeError nativeError = new NativeError();
        Object[] objArrCaptureImageExtendedNative = captureImageExtendedNative(nativeError);
        handleError(nativeError);
        if (objArrCaptureImageExtendedNative == null || objArrCaptureImageExtendedNative.length != 8 || objArrCaptureImageExtendedNative[0] == null || objArrCaptureImageExtendedNative[1] == null || objArrCaptureImageExtendedNative[2] == null || objArrCaptureImageExtendedNative[3] == null || objArrCaptureImageExtendedNative[4] == null || objArrCaptureImageExtendedNative[5] == null || objArrCaptureImageExtendedNative[6] == null || objArrCaptureImageExtendedNative[7] == null) {
            logPrintError(getMethodName() + ": null or invalid image information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        Object[] objArr = new Object[8];
        int iIntValue = ((Integer) objArrCaptureImageExtendedNative[0]).intValue();
        IBScanException.Type typeFromCode = IBScanException.Type.fromCode(((Integer) objArrCaptureImageExtendedNative[0]).intValue());
        IBScanException iBScanException = typeFromCode == null ? null : new IBScanException(typeFromCode);
        int[] iArr = (int[]) objArrCaptureImageExtendedNative[7];
        ImageType imageTypeFromCode = ImageType.fromCode(((Integer) objArrCaptureImageExtendedNative[2]).intValue());
        FingerCountState fingerCountStateFromCode = FingerCountState.fromCode(((Integer) objArrCaptureImageExtendedNative[6]).intValue());
        objArr[0] = iBScanException;
        objArr[1] = objArrCaptureImageExtendedNative[1];
        objArr[2] = imageTypeFromCode;
        objArr[3] = objArrCaptureImageExtendedNative[3];
        objArr[4] = objArrCaptureImageExtendedNative[4];
        objArr[5] = objArrCaptureImageExtendedNative[5];
        objArr[6] = fingerCountStateFromCode;
        FingerQualityState[] fingerQualityStateArr = new FingerQualityState[iArr.length];
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                z = true;
                break;
            }
            fingerQualityStateArr[i] = FingerQualityState.fromCode(iArr[i]);
            if (fingerQualityStateArr[i] == null) {
                logPrintError(getMethodName() + ": unrecognized fingerQuality code (" + iArr[i] + ") returned from native code");
                z = false;
                break;
            }
            i++;
        }
        objArr[7] = fingerQualityStateArr;
        if (fingerCountStateFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized fingerCountState code (" + ((Integer) objArrCaptureImageExtendedNative[6]).intValue() + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        } else if (imageTypeFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized imageType code (" + ((Integer) objArrCaptureImageExtendedNative[2]).intValue() + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        } else if (iBScanException == null && iIntValue != 0) {
            logPrintError(getMethodName() + ": unrecognized imageStatus code (" + ((Integer) objArrCaptureImageExtendedNative[0]).intValue() + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        } else if (!z) {
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return objArr;
    }

    public PlatenState getPlatenStateAtCapture() throws IBScanException {
        NativeError nativeError = new NativeError();
        int platenStateAtCaptureNative = getPlatenStateAtCaptureNative(nativeError);
        handleError(nativeError);
        PlatenState platenStateFromCode = PlatenState.fromCode(platenStateAtCaptureNative);
        if (platenStateFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized platenState code (" + platenStateAtCaptureNative + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return platenStateFromCode;
    }

    public RollingData getRollingInfo() throws IBScanException {
        NativeError nativeError = new NativeError();
        RollingData rollingInfoNative = getRollingInfoNative(nativeError);
        handleError(nativeError);
        if (rollingInfoNative == null || rollingInfoNative.rollingState == null) {
            logPrintError(getMethodName() + ": null or invalid rollingData returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return rollingInfoNative;
    }

    public int calculateNfiqScore(ImageData imageData) throws IBScanException {
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null image");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        int iCalculateNfiqScoreNative = calculateNfiqScoreNative(imageData, nativeError);
        handleError(nativeError);
        return iCalculateNfiqScoreNative;
    }

    public Object[] getResultImageExt(IBCommon.FingerPosition fingerPosition) throws IBScanException {
        NativeError nativeError = new NativeError();
        Object[] resultImageExtNative = getResultImageExtNative(fingerPosition.toCode(), nativeError);
        handleError(nativeError);
        return resultImageExtNative;
    }

    public Object ConvertImageToISOANSI(IBCommon.ImageDataExt[] imageDataExtArr, int i, IBCommon.ImageFormat imageFormat, IBCommon.StandardFormat standardFormat) throws IBScanException {
        NativeError nativeError = new NativeError();
        Object objConvertImageToISOANSINative = ConvertImageToISOANSINative(imageDataExtArr, i, imageFormat.toCode(), standardFormat.toCode(), nativeError);
        handleError(nativeError);
        return objConvertImageToISOANSINative;
    }

    public int wsqEncodeToFile(String str, byte[] bArr, int i, int i2, int i3, int i4, int i5, double d, String str2) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iWsqEncodeToFileNative = wsqEncodeToFileNative(str, bArr, i, i2, i3, i4, i5, d, str2, nativeError);
        handleError(nativeError);
        return iWsqEncodeToFileNative;
    }

    public Object[] wsqEncodeToMem(byte[] bArr, int i, int i2, int i3, int i4, int i5, double d, String str) throws IBScanException {
        if (bArr == null) {
            logPrintWarning(getMethodName() + ": received null inputImage");
            throw new IllegalArgumentException("Received null inputImage");
        }
        NativeError nativeError = new NativeError();
        Object[] objArrWsqEncodeToMemNative = wsqEncodeToMemNative(bArr, i, i2, i3, i4, i5, d, str, nativeError);
        handleError(nativeError);
        if (objArrWsqEncodeToMemNative == null || objArrWsqEncodeToMemNative.length != 2 || objArrWsqEncodeToMemNative[0] == null || objArrWsqEncodeToMemNative[1] == null) {
            logPrintError(getMethodName() + ": null or invalid WSQ information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        Object obj = objArrWsqEncodeToMemNative[0];
        Object obj2 = objArrWsqEncodeToMemNative[1];
        return objArrWsqEncodeToMemNative;
    }

    public Object[] wsqDecodeToMem(byte[] bArr, int i) throws IBScanException {
        if (bArr == null) {
            logPrintWarning(getMethodName() + ": received null compressedImage");
            throw new IllegalArgumentException("Received null compressedImage");
        }
        NativeError nativeError = new NativeError();
        Object[] objArrWsqDecodeToMemNative = wsqDecodeToMemNative(bArr, i, nativeError);
        handleError(nativeError);
        if (objArrWsqDecodeToMemNative == null || objArrWsqDecodeToMemNative.length != 6 || objArrWsqDecodeToMemNative[0] == null || objArrWsqDecodeToMemNative[1] == null || objArrWsqDecodeToMemNative[2] == null || objArrWsqDecodeToMemNative[3] == null || objArrWsqDecodeToMemNative[4] == null || objArrWsqDecodeToMemNative[5] == null) {
            logPrintError(getMethodName() + ": null or invalid WSQ information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        Object obj = objArrWsqDecodeToMemNative[0];
        Object obj2 = objArrWsqDecodeToMemNative[1];
        Object obj3 = objArrWsqDecodeToMemNative[2];
        Object obj4 = objArrWsqDecodeToMemNative[3];
        Object obj5 = objArrWsqDecodeToMemNative[4];
        Object obj6 = objArrWsqDecodeToMemNative[5];
        return objArrWsqDecodeToMemNative;
    }

    public int SaveRAWImage(byte[] bArr, int i, String str) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSaveRAWImageNative = SaveRAWImageNative(bArr, i, str, nativeError);
        handleError(nativeError);
        return iSaveRAWImageNative;
    }

    public int SaveStandardFile(IBCommon.StandardFormatData standardFormatData, String str) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSaveStandardFileNative = SaveStandardFileNative(standardFormatData, str, nativeError);
        handleError(nativeError);
        return iSaveStandardFileNative;
    }

    public int generateZoomOutImageEx(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte b) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iGenerateZoomOutImageExNative = generateZoomOutImageExNative(bArr, i, i2, bArr2, i3, i4, b, nativeError);
        handleError(nativeError);
        return iGenerateZoomOutImageExNative;
    }

    public void createBmpEx(byte[] bArr, Bitmap bitmap) throws IBScanException {
        NativeError nativeError = new NativeError();
        createBmpExNative(bArr, bitmap, nativeError);
        handleError(nativeError);
    }

    public Object[] getEnhancedImageReserved(String str, ImageData imageData) throws IBScanException {
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null reservedKey");
            throw new IllegalArgumentException("Received null reservedKey");
        }
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null image");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        Object[] enhancedImageReservedNative = getEnhancedImageReservedNative(str, imageData, nativeError);
        handleError(nativeError);
        if (enhancedImageReservedNative == null || enhancedImageReservedNative.length != 4 || enhancedImageReservedNative[0] == null || enhancedImageReservedNative[1] == null || enhancedImageReservedNative[2] == null || enhancedImageReservedNative[3] == null) {
            logPrintError(getMethodName() + ": null or invalid image information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return new Object[]{enhancedImageReservedNative[0], enhancedImageReservedNative[1], enhancedImageReservedNative[2], enhancedImageReservedNative[3]};
    }

    public Object getCombineImage(ImageData imageData, ImageData imageData2, CombineImageWhichHand combineImageWhichHand) throws IBScanException {
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null ImageData1");
            throw new IllegalArgumentException("Received null ImageData1");
        }
        if (imageData2 == null) {
            logPrintWarning(getMethodName() + ": received null ImageData2");
            throw new IllegalArgumentException("Received null ImageData2");
        }
        NativeError nativeError = new NativeError();
        Object combineImageNative = getCombineImageNative(imageData, imageData2, combineImageWhichHand.toCode(), nativeError);
        handleError(nativeError);
        return combineImageNative;
    }

    public BeeperType getOperableBeeper() throws IBScanException {
        NativeError nativeError = new NativeError();
        int operableBeeperNative = getOperableBeeperNative(nativeError);
        handleError(nativeError);
        BeeperType beeperTypeFromCode = BeeperType.fromCode(operableBeeperNative);
        if (beeperTypeFromCode == null) {
            logPrintError(getMethodName() + ": unrecognized beeperType code (" + operableBeeperNative + ") returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return beeperTypeFromCode;
    }

    public void setBeeper(BeepPattern beepPattern, int i, int i2, int i3, int i4) throws IBScanException {
        NativeError nativeError = new NativeError();
        setBeeperNative(beepPattern.toCode(), i, i2, i3, i4, nativeError);
        handleError(nativeError);
    }

    public Object[] getCombineImageEx(ImageData imageData, ImageData imageData2, CombineImageWhichHand combineImageWhichHand) throws IBScanException {
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null ImageData1");
            throw new IllegalArgumentException("Received null ImageData1");
        }
        if (imageData2 == null) {
            logPrintWarning(getMethodName() + ": received null ImageData2");
            throw new IllegalArgumentException("Received null ImageData2");
        }
        NativeError nativeError = new NativeError();
        Object[] combineImageExNative = getCombineImageExNative(imageData, imageData2, combineImageWhichHand.toCode(), nativeError);
        handleError(nativeError);
        if (combineImageExNative == null || combineImageExNative.length != 4 || combineImageExNative[0] == null || combineImageExNative[1] == null || combineImageExNative[2] == null || combineImageExNative[3] == null) {
            logPrintError(getMethodName() + ": null or invalid image information returned from native code");
            nativeError.code = IBScanException.Type.COMMAND_FAILED.toCode();
            handleError(nativeError);
        }
        return new Object[]{combineImageExNative[0], combineImageExNative[1], combineImageExNative[2], combineImageExNative[3]};
    }

    public int generateDisplayImage(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte b, int i5, int i6, boolean z) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iGenerateDisplayImageNative = generateDisplayImageNative(bArr, i, i2, bArr2, i3, i4, b, i5, i6, z, nativeError);
        handleError(nativeError);
        return iGenerateDisplayImageNative;
    }

    public int removeFingerImage(long j) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iRemoveFingerImageNative = removeFingerImageNative(j, nativeError);
        handleError(nativeError);
        return iRemoveFingerImageNative;
    }

    public int addFingerImage(ImageData imageData, long j, ImageType imageType, boolean z) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iAddFingerImageNative = addFingerImageNative(imageData, j, imageType.toCode(), z, nativeError);
        handleError(nativeError);
        return iAddFingerImageNative;
    }

    public long isFingerDuplicated(ImageData imageData, long j, ImageType imageType, int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        long jIsFingerDuplicatedNative = isFingerDuplicatedNative(imageData, j, imageType.toCode(), i, nativeError);
        handleError(nativeError);
        return jIsFingerDuplicatedNative;
    }

    public boolean isValidFingerGeometry(ImageData imageData, long j, ImageType imageType) throws IBScanException {
        NativeError nativeError = new NativeError();
        boolean zIsValidFingerGeometryNative = isValidFingerGeometryNative(imageData, j, imageType.toCode(), nativeError);
        handleError(nativeError);
        return zIsValidFingerGeometryNative;
    }

    public int GetSpoofScore(String str, ImageData imageData) throws IBScanException {
        if (str == null) {
            logPrintWarning(getMethodName() + ": received null reservedKey");
            throw new IllegalArgumentException();
        }
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null image");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        int iGetSpoofScoreNative = GetSpoofScoreNative(str, imageData, nativeError);
        handleError(nativeError);
        return iGetSpoofScoreNative;
    }

    public boolean IsSpoofFingerDetected(ImageData imageData) throws IBScanException {
        if (imageData == null) {
            logPrintWarning(getMethodName() + ": received null image");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        boolean zIsSpoofFingerDetectedNative = IsSpoofFingerDetectedNative(imageData, nativeError);
        handleError(nativeError);
        return zIsSpoofFingerDetectedNative;
    }

    public int SaveBitmapImage(String str, byte[] bArr, int i, int i2, int i3, double d, double d2) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSaveBitmapImageNative = SaveBitmapImageNative(str, bArr, i, i2, i3, d, d2, nativeError);
        handleError(nativeError);
        return iSaveBitmapImageNative;
    }

    public int SaveJP2Image(String str, byte[] bArr, int i, int i2, int i3, double d, double d2, int i4) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSaveJP2ImageNative = SaveJP2ImageNative(str, bArr, i, i2, i3, d, d2, i4, nativeError);
        handleError(nativeError);
        return iSaveJP2ImageNative;
    }

    public int SavePngImage(String str, byte[] bArr, int i, int i2, int i3, double d, double d2) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSavePngImageNative = SavePngImageNative(str, bArr, i, i2, i3, d, d2, nativeError);
        handleError(nativeError);
        return iSavePngImageNative;
    }

    public int SetEncryptionKey(byte[] bArr, EncyptionMode encyptionMode) throws IBScanException {
        NativeError nativeError = new NativeError();
        int iSetEncryptionKeyNative = SetEncryptionKeyNative(bArr, encyptionMode.toCode(), nativeError);
        handleError(nativeError);
        return iSetEncryptionKeyNative;
    }

    public void setScanDeviceListener(IBScanDeviceListener iBScanDeviceListener) {
        this.m_listener = iBScanDeviceListener;
    }

    protected static final class NativeError {
        public int code = 0;

        protected NativeError() {
        }
    }

    protected IBScanDevice(long j) {
        this.m_handleNative = j;
    }

    private static void handleError(NativeError nativeError) throws IBScanException {
        if (nativeError.code != 0) {
            IBScanException.Type typeFromCode = IBScanException.Type.fromCode(nativeError.code);
            if (typeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized error code (" + nativeError.code + ") returned from native code");
                typeFromCode = IBScanException.Type.COMMAND_FAILED;
            }
            throw new IBScanException(typeFromCode);
        }
    }

    private static void logPrintWarning(String str) {
        Log.w("IBScanDevice", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logPrintError(String str) {
        Log.e("IBScanDevice", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = METHOD_STACK_INDEX;
        return length > i ? stackTrace[i].getMethodName() : "?";
    }

    private void callbackDeviceCommunicationBroken() {
        IBScanDeviceListener iBScanDeviceListener = this.m_listener;
        if (iBScanDeviceListener != null) {
            iBScanDeviceListener.deviceCommunicationBroken(this);
        }
    }

    private void callbackDeviceImagePreviewAvailable(ImageData imageData) {
        IBScanDeviceListener iBScanDeviceListener = this.m_listener;
        if (iBScanDeviceListener != null) {
            iBScanDeviceListener.deviceImagePreviewAvailable(this, imageData);
        }
    }

    private void callbackDeviceFingerCountChanged(int i) {
        if (this.m_listener != null) {
            FingerCountState fingerCountStateFromCode = FingerCountState.fromCode(i);
            if (fingerCountStateFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized fingerState code (" + i + ") returned from native code");
                return;
            }
            this.m_listener.deviceFingerCountChanged(this, fingerCountStateFromCode);
        }
    }

    private void callbackDeviceFingerQualityChanged(int[] iArr) {
        if (this.m_listener == null || iArr == null) {
            return;
        }
        FingerQualityState[] fingerQualityStateArr = new FingerQualityState[iArr.length];
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                z = true;
                break;
            }
            fingerQualityStateArr[i] = FingerQualityState.fromCode(iArr[i]);
            if (fingerQualityStateArr[i] == null) {
                logPrintError(getMethodName() + ": unrecognized fingerQuality code (" + iArr[i] + ") returned from native code");
                break;
            }
            i++;
        }
        if (z) {
            this.m_listener.deviceFingerQualityChanged(this, fingerQualityStateArr);
        }
    }

    private void callbackDeviceAcquisitionBegun(int i) {
        if (this.m_listener != null) {
            ImageType imageTypeFromCode = ImageType.fromCode(i);
            if (imageTypeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized imageType code (" + imageTypeFromCode + ") returned from native code");
                return;
            }
            this.m_listener.deviceAcquisitionBegun(this, imageTypeFromCode);
        }
    }

    private void callbackDeviceAcquisitionCompleted(int i) {
        if (this.m_listener != null) {
            ImageType imageTypeFromCode = ImageType.fromCode(i);
            if (imageTypeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized imageType code (" + imageTypeFromCode + ") returned from native code");
                return;
            }
            this.m_listener.deviceAcquisitionCompleted(this, imageTypeFromCode);
        }
    }

    private void callbackDeviceImageResultAvailable(ImageData imageData, int i, ImageData[] imageDataArr) {
        if (this.m_listener != null) {
            ImageType imageTypeFromCode = ImageType.fromCode(i);
            if (imageTypeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized imageType code (" + imageTypeFromCode + ") returned from native code");
                return;
            }
            this.m_listener.deviceImageResultAvailable(this, imageData, imageTypeFromCode, imageDataArr);
        }
    }

    private void callbackDeviceImageResultExtendedAvailable(int i, ImageData imageData, int i2, int i3, ImageData[] imageDataArr, SegmentPosition[] segmentPositionArr) {
        if (this.m_listener != null) {
            ImageType imageTypeFromCode = ImageType.fromCode(i2);
            if (imageTypeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized imageType code (" + imageTypeFromCode + ") returned from native code");
                return;
            }
            IBScanException.Type typeFromCode = i == 0 ? null : IBScanException.Type.fromCode(i);
            if (i != 0 && typeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized imageStatus code (" + i + ") returned from native code");
                return;
            }
            this.m_listener.deviceImageResultExtendedAvailable(this, i == 0 ? null : new IBScanException(typeFromCode), imageData, imageTypeFromCode, i3, imageDataArr, segmentPositionArr);
        }
    }

    private void callbackDevicePlatenStateChanged(int i) {
        if (this.m_listener != null) {
            PlatenState platenStateFromCode = PlatenState.fromCode(i);
            if (platenStateFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized platenState code (" + i + ") returned from native code");
                return;
            }
            this.m_listener.devicePlatenStateChanged(this, platenStateFromCode);
        }
    }

    private void callbackDeviceWarningReceived(int i) {
        if (this.m_listener != null) {
            IBScanException.Type typeFromCode = IBScanException.Type.fromCode(i);
            if (typeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized warning code (" + i + ") returned from native code");
                return;
            }
            this.m_listener.deviceWarningReceived(this, new IBScanException(typeFromCode));
        }
    }

    private void callbackDevicePressedKeyButtons(int i) {
        IBScanDeviceListener iBScanDeviceListener = this.m_listener;
        if (iBScanDeviceListener != null) {
            iBScanDeviceListener.devicePressedKeyButtons(this, i);
        }
    }

    static {
        int i = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            i++;
            if (stackTraceElement.getClassName().equals(IBScanDevice.class.getName())) {
                break;
            }
        }
        METHOD_STACK_INDEX = i;
        System.loadLibrary("usb");
        if (System.getProperty("PPI_BUILD") != null) {
            System.loadLibrary("DeviceParallel");
        }
        System.loadLibrary("IBScanUltimate");
        System.loadLibrary("ibscanultimatejni");
    }
}
