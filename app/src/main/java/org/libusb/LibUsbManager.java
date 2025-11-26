package org.libusb;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/* loaded from: classes3.dex */
public class LibUsbManager {
    private static final String TAG = "LibUsb";
    private static SparseArray<UsbDeviceConnection> m_connectionMap = new SparseArray<>();
    private static Context m_context = null;
    private static Vector<DeviceDesc> m_descVector = new Vector<>();
    private static int m_hasPermitUsbDevice = 0;

    private static void logPrintWarning(String str) {
        Log.w("IBScanDevice", str);
    }

    private static void logPrintError(String str) {
        Log.e("IBScanDevice", str);
    }

    public static void setContext(Context context) {
        m_context = context;
    }

    protected static class DeviceDesc {
        public final byte[] descriptors;
        public final int deviceId;

        protected DeviceDesc(int i, byte[] bArr) {
            this.deviceId = i;
            this.descriptors = bArr;
        }
    }

    private static UsbDevice findDevice(int i) {
        for (UsbDevice usbDevice : ((UsbManager) m_context.getSystemService("usb")).getDeviceList().values()) {
            if (usbDevice.getDeviceId() == i) {
                return usbDevice;
            }
        }
        return null;
    }

    private LibUsbManager() {
    }

    static {
        System.loadLibrary("usb");
    }

    protected static DeviceDesc[] getDeviceArray() {
        Context context = m_context;
        if (context != null) {
            UsbManager usbManager = (UsbManager) context.getSystemService("usb");
            HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
            new Vector();
            int i = 0;
            Iterator<UsbDevice> it = deviceList.values().iterator();
            while (it.hasNext()) {
                if (usbManager.hasPermission(it.next())) {
                    i++;
                }
            }
            if (i != m_hasPermitUsbDevice) {
                m_hasPermitUsbDevice = i;
                m_descVector.clear();
                for (UsbDevice usbDevice : deviceList.values()) {
                    if (usbManager.hasPermission(usbDevice) && (usbDevice.getVendorId() == 4415 || usbDevice.getVendorId() == 8122)) {
                        UsbDeviceConnection usbDeviceConnectionOpenDevice = usbManager.openDevice(usbDevice);
                        if (usbDeviceConnectionOpenDevice != null) {
                            byte[] rawDescriptors = usbDeviceConnectionOpenDevice.getRawDescriptors();
                            usbDeviceConnectionOpenDevice.close();
                            m_descVector.add(new DeviceDesc(usbDevice.getDeviceId(), rawDescriptors));
                        }
                    }
                }
            }
        }
        Vector<DeviceDesc> vector = m_descVector;
        return (DeviceDesc[]) vector.toArray(new DeviceDesc[vector.size()]);
    }

    protected static int openDevice(int i) {
        UsbDevice usbDeviceFindDevice;
        UsbDeviceConnection usbDeviceConnectionOpenDevice;
        if (m_context != null && (usbDeviceFindDevice = findDevice(i)) != null) {
            UsbManager usbManager = (UsbManager) m_context.getSystemService("usb");
            if (usbManager.hasPermission(usbDeviceFindDevice) && (usbDeviceConnectionOpenDevice = usbManager.openDevice(usbDeviceFindDevice)) != null) {
                int fileDescriptor = usbDeviceConnectionOpenDevice.getFileDescriptor();
                m_connectionMap.put(usbDeviceFindDevice.getDeviceId(), usbDeviceConnectionOpenDevice);
                return fileDescriptor;
            }
        }
        return -1;
    }

    protected static void closeDevice(int i) {
        UsbDeviceConnection usbDeviceConnection;
        if (m_context == null || (usbDeviceConnection = m_connectionMap.get(i)) == null) {
            return;
        }
        usbDeviceConnection.close();
        m_connectionMap.remove(i);
    }
}
