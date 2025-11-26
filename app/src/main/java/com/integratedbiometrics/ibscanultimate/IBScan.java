package com.integratedbiometrics.ibscanultimate;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;
import com.integratedbiometrics.ibscanultimate.IBScanException;
import org.libusb.LibUsbManager;

/* loaded from: classes2.dex */
public class IBScan {
    private static final String ACTION_USB_PERMISSION = "ibscan.USB_PERMISSION";
    private static int METHOD_STACK_INDEX = 0;
    private static final int PID_COLUMBO = 4352;
    private static final int PID_COLUMBO_MINI = 28928;
    private static final int PID_COLUMBO_REV1 = 4353;
    private static final int PID_CURVE = 4100;
    private static final int PID_DANNO = 5632;
    private static final int PID_FIVE0 = 5376;
    private static final int PID_FIVE0_DERMALOG = 52;
    private static final int PID_FIVE0_REV1 = 5377;
    private static final int PID_HOLMES = 4608;
    private static final int PID_KOJAK = 4864;
    private static final int PID_KOJAK_DERMALOG = 54;
    private static final int PID_KOJAK_LOCK = 6656;
    private static final int PID_KOJAK_REV1 = 4865;
    private static final int PID_SHERLOCK = 4112;
    private static final int PID_SHERLOCK_AUO = 4113;
    private static final int PID_WATSON = 4101;
    private static final int PID_WATSON_MINI = 4128;
    private static final int PID_WATSON_MINI_REV1 = 4129;
    private static final int PID_WATSON_REV1 = 4102;
    private static final int VID_DERMALOG = 8122;
    private static final int VID_IB = 4415;
    private static IBScan m_instance;
    private IBScanListener m_listener = null;
    private Context m_context = null;
    private final BroadcastReceiver m_usbReceiver = new BroadcastReceiver() { // from class: com.integratedbiometrics.ibscanultimate.IBScan.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice == null || !IBScan.isScanDevice(usbDevice)) {
                    return;
                }
                IBScan.callbackScanDeviceAttached(usbDevice.getDeviceId());
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice2 == null || !IBScan.isScanDevice(usbDevice2)) {
                    return;
                }
                IBScan.callbackScanDeviceDetached(usbDevice2.getDeviceId());
                return;
            }
            if (action.equals(IBScan.ACTION_USB_PERMISSION) && intent.hasExtra("permission")) {
                boolean booleanExtra = intent.getBooleanExtra("permission", false);
                UsbDevice usbDevice3 = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice3 == null || !IBScan.isScanDevice(usbDevice3)) {
                    return;
                }
                IBScan.callbackScanDevicePermissionGranted(usbDevice3.getDeviceId(), booleanExtra);
            }
        }
    };

    private native void enableTraceLogNative(boolean z, NativeError nativeError);

    private native int getDeviceCountNative(NativeError nativeError);

    private native DeviceDesc getDeviceDescNative(int i, NativeError nativeError);

    private native String getErrorStringNative(int i, NativeError nativeError);

    private native int getInitProgressNative(int i, NativeError nativeError);

    private native String getRequiredSDKVersionNative(int i, NativeError nativeError);

    private native SdkVersion getSdkVersionNative(NativeError nativeError);

    private native void initNative();

    private native void openDeviceAsyncExNative(int i, String str, NativeError nativeError);

    private native void openDeviceAsyncNative(int i, NativeError nativeError);

    private native IBScanDevice openDeviceExNative(int i, String str, NativeError nativeError);

    private native IBScanDevice openDeviceNative(int i, NativeError nativeError);

    private native void setCustomerKeyNative(int i, int i2, String str, NativeError nativeError);

    private native int unloadLibraryNative(NativeError nativeError);

    private native void updateUsbPermissionNative();

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

    public static final class DeviceDesc {
        public final String customerString;
        public final String devRevision;
        public final int deviceId;
        public final String fwVersion;
        public final String interfaceType;
        public final boolean isLocked;
        public final boolean isOpened;
        public final String productName;
        public final String serialNumber;

        protected DeviceDesc(String str, String str2, String str3, String str4, String str5, boolean z, int i, boolean z2, String str6) {
            this.serialNumber = str;
            this.productName = str2;
            this.interfaceType = str3;
            this.fwVersion = str4;
            this.devRevision = str5;
            this.isOpened = z;
            this.deviceId = i;
            this.isLocked = z2;
            this.customerString = str6;
        }

        public String toString() {
            return "Serial Number: " + this.serialNumber + "\nProduct Name: " + this.productName + "\nInterface Type: " + this.interfaceType + "\nFirmware Version: " + this.fwVersion + "\nDevice Revision: " + this.devRevision + "\nDevice Opened: " + this.isOpened + "\nDevice Locked: " + this.isLocked + "\nCustomer String: " + this.customerString + "\nDevice ID: " + this.deviceId + "\n";
        }
    }

    public void enableTraceLog(boolean z) throws IBScanException {
        NativeError nativeError = new NativeError();
        enableTraceLogNative(z, nativeError);
        handleError(nativeError);
    }

    public void updateUsbPermission() {
        updateUsbPermissionNative();
    }

    public SdkVersion getSdkVersion() throws IBScanException {
        NativeError nativeError = new NativeError();
        SdkVersion sdkVersionNative = getSdkVersionNative(nativeError);
        handleError(nativeError);
        return sdkVersionNative;
    }

    public int getDeviceCount() throws IBScanException {
        NativeError nativeError = new NativeError();
        int deviceCountNative = getDeviceCountNative(nativeError);
        handleError(nativeError);
        return deviceCountNative;
    }

    public void requestPermission(int i) {
        PendingIntent broadcast;
        UsbDevice usbDeviceFindDevice = findDevice(i);
        if (usbDeviceFindDevice != null) {
            UsbManager usbManager = (UsbManager) this.m_context.getSystemService("usb");
            Intent intent = new Intent(ACTION_USB_PERMISSION);
            if (Build.VERSION.SDK_INT >= 23) {
                broadcast = PendingIntent.getBroadcast(this.m_context, 0, intent, 335544320);
            } else {
                broadcast = PendingIntent.getBroadcast(this.m_context, 0, intent, 0);
            }
            usbManager.requestPermission(usbDeviceFindDevice, broadcast);
        }
    }

    public boolean hasPermission(int i) {
        UsbDevice usbDeviceFindDevice = findDevice(i);
        if (usbDeviceFindDevice != null) {
            return ((UsbManager) this.m_context.getSystemService("usb")).hasPermission(usbDeviceFindDevice);
        }
        return false;
    }

    public DeviceDesc getDeviceDescription(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        DeviceDesc deviceDescNative = getDeviceDescNative(i, nativeError);
        handleError(nativeError);
        return deviceDescNative;
    }

    public IBScanDevice openDevice(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        IBScanDevice iBScanDeviceOpenDeviceNative = openDeviceNative(i, nativeError);
        handleError(nativeError);
        return iBScanDeviceOpenDeviceNative;
    }

    public IBScanDevice openDevice(int i, String str) throws IBScanException {
        if (str == null) {
            logPrintWarning(getMethodName() + ": receive null uniformityMaskPath");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        IBScanDevice iBScanDeviceOpenDeviceExNative = openDeviceExNative(i, str, nativeError);
        handleError(nativeError);
        return iBScanDeviceOpenDeviceExNative;
    }

    public void openDeviceAsync(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        openDeviceAsyncNative(i, nativeError);
        handleError(nativeError);
    }

    public void openDeviceAsync(int i, String str) throws IBScanException {
        if (str == null) {
            logPrintWarning(getMethodName() + ": receive null uniformityMaskPath");
            throw new IllegalArgumentException();
        }
        NativeError nativeError = new NativeError();
        openDeviceAsyncExNative(i, str, nativeError);
        handleError(nativeError);
    }

    public int getInitProgress(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        int initProgressNative = getInitProgressNative(i, nativeError);
        handleError(nativeError);
        return initProgressNative;
    }

    public void unloadLibrary() throws IBScanException {
        NativeError nativeError = new NativeError();
        unloadLibraryNative(nativeError);
        handleError(nativeError);
    }

    public String getRequiredSDKVersion(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        String requiredSDKVersionNative = getRequiredSDKVersionNative(i, nativeError);
        handleError(nativeError);
        return requiredSDKVersionNative;
    }

    public enum HashType {
        SHA256(0),
        Reserved(1);

        private final int code;

        HashType(int i) {
            this.code = i;
        }

        protected static HashType fromCode(int i) {
            for (HashType hashType : values()) {
                if (hashType.code == i) {
                    return hashType;
                }
            }
            return null;
        }

        public int toCode() {
            return this.code;
        }
    }

    public void setCustomerKey(int i, HashType hashType, String str) throws IBScanException {
        NativeError nativeError = new NativeError();
        setCustomerKeyNative(i, hashType.toCode(), str, nativeError);
        handleError(nativeError);
    }

    public String getErrorString(int i) throws IBScanException {
        NativeError nativeError = new NativeError();
        String errorStringNative = getErrorStringNative(i, nativeError);
        handleError(nativeError);
        return errorStringNative;
    }

    public void setScanListener(IBScanListener iBScanListener) {
        this.m_listener = iBScanListener;
    }

    public static boolean isScanDevice(UsbDevice usbDevice) {
        int vendorId = usbDevice.getVendorId();
        if (vendorId != VID_IB && vendorId != VID_DERMALOG) {
            return false;
        }
        int[] iArr = {PID_CURVE, PID_WATSON, PID_WATSON_REV1, PID_SHERLOCK, PID_SHERLOCK_AUO, PID_WATSON_MINI, PID_WATSON_MINI_REV1, PID_COLUMBO, PID_COLUMBO_REV1, PID_KOJAK, PID_KOJAK_REV1, PID_HOLMES, PID_FIVE0, PID_FIVE0_REV1, 52, PID_DANNO, PID_KOJAK_LOCK, 54, PID_COLUMBO_MINI};
        int productId = usbDevice.getProductId();
        for (int i = 0; i < 19; i++) {
            if (iArr[i] == productId) {
                return true;
            }
        }
        return false;
    }

    public void setContext(Context context) {
        Context context2 = this.m_context;
        if (context2 != null) {
            context2.unregisterReceiver(this.m_usbReceiver);
            this.m_context = null;
            LibUsbManager.setContext(null);
        }
        if (context != null) {
            this.m_context = context;
            LibUsbManager.setContext(context);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            intentFilter.addAction(ACTION_USB_PERMISSION);
            this.m_context.registerReceiver(this.m_usbReceiver, intentFilter);
        }
    }

    public static IBScan getInstance(Context context) {
        if (m_instance == null) {
            m_instance = new IBScan();
        }
        m_instance.setContext(context);
        return m_instance;
    }

    protected static final class NativeError {
        public int code = 0;

        protected NativeError() {
        }
    }

    private IBScan() {
        initNative();
    }

    private UsbDevice findDevice(int i) {
        Context context = this.m_context;
        if (context != null) {
            for (UsbDevice usbDevice : ((UsbManager) context.getSystemService("usb")).getDeviceList().values()) {
                if (usbDevice.getDeviceId() == i) {
                    return usbDevice;
                }
            }
        }
        return null;
    }

    private static void handleError(NativeError nativeError) throws IBScanException {
        if (nativeError.code != 0) {
            IBScanException.Type typeFromCode = IBScanException.Type.fromCode(nativeError.code);
            if (typeFromCode == null) {
                logPrintError(getMethodName() + ": unrecognized error code(" + nativeError.code + ") returned from native code");
                typeFromCode = IBScanException.Type.COMMAND_FAILED;
            }
            throw new IBScanException(typeFromCode);
        }
    }

    private static void logPrintWarning(String str) {
        Log.w("IBScan", str);
    }

    private static void logPrintError(String str) {
        Log.e("IBScan", str);
    }

    private static String getMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = METHOD_STACK_INDEX;
        return length > i ? stackTrace[i].getMethodName() : "?";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackScanDeviceAttached(int i) {
        IBScanListener iBScanListener;
        IBScan iBScan = m_instance;
        if (iBScan == null || (iBScanListener = iBScan.m_listener) == null) {
            return;
        }
        iBScanListener.scanDeviceAttached(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackScanDeviceDetached(int i) {
        IBScanListener iBScanListener;
        IBScan iBScan = m_instance;
        if (iBScan == null || (iBScanListener = iBScan.m_listener) == null) {
            return;
        }
        iBScanListener.scanDeviceDetached(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackScanDevicePermissionGranted(int i, boolean z) {
        IBScanListener iBScanListener;
        IBScan iBScan = m_instance;
        if (iBScan == null || (iBScanListener = iBScan.m_listener) == null) {
            return;
        }
        iBScanListener.scanDevicePermissionGranted(i, z);
    }

    private static void callbackScanDeviceInitProgress(int i, int i2) {
        IBScanListener iBScanListener;
        IBScan iBScan = m_instance;
        if (iBScan == null || (iBScanListener = iBScan.m_listener) == null) {
            return;
        }
        iBScanListener.scanDeviceInitProgress(i, i2);
    }

    private static void callbackScanDeviceCountChanged(int i) {
        IBScanListener iBScanListener;
        IBScan iBScan = m_instance;
        if (iBScan == null || (iBScanListener = iBScan.m_listener) == null) {
            return;
        }
        iBScanListener.scanDeviceCountChanged(i);
    }

    private static void callbackScanDeviceOpenComplete(int i, IBScanDevice iBScanDevice, int i2) {
        IBScan iBScan = m_instance;
        if (iBScan == null || iBScan.m_listener == null) {
            return;
        }
        iBScan.m_listener.scanDeviceOpenComplete(i, iBScanDevice, new IBScanException(IBScanException.Type.fromCode(i2)));
    }

    static {
        int i = 0;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            i++;
            if (stackTraceElement.getClassName().equals(IBScan.class.getName())) {
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
