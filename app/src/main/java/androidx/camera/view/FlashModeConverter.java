package androidx.camera.view;

import java.util.Objects;

/* loaded from: classes.dex */
final class FlashModeConverter {
    private FlashModeConverter() {
    }

    public static int valueOf(String str) {
        Objects.requireNonNull(str, "name cannot be null");
        str.hashCode();
        switch (str) {
            case "ON":
                return 1;
            case "OFF":
                return 2;
            case "AUTO":
                return 0;
            default:
                throw new IllegalArgumentException("Unknown flash mode name " + str);
        }
    }

    public static String nameOf(int i) {
        if (i == 0) {
            return "AUTO";
        }
        if (i == 1) {
            return "ON";
        }
        if (i == 2) {
            return "OFF";
        }
        throw new IllegalArgumentException("Unknown flash mode " + i);
    }
}
