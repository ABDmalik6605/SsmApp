package androidx.camera.camera2.internal.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.SelectResolutionQuirk;
import androidx.camera.core.impl.SurfaceConfig;

/* loaded from: classes.dex */
public class MaxPreviewSize {
    private final SelectResolutionQuirk mSelectResolutionQuirk;

    public MaxPreviewSize() {
        this((SelectResolutionQuirk) DeviceQuirks.get(SelectResolutionQuirk.class));
    }

    MaxPreviewSize(SelectResolutionQuirk selectResolutionQuirk) {
        this.mSelectResolutionQuirk = selectResolutionQuirk;
    }

    public Size getMaxPreviewResolution(Size size) {
        Size sizeSelectResolution;
        SelectResolutionQuirk selectResolutionQuirk = this.mSelectResolutionQuirk;
        if (selectResolutionQuirk == null || (sizeSelectResolution = selectResolutionQuirk.selectResolution(SurfaceConfig.ConfigType.PRIV)) == null) {
            return size;
        }
        return sizeSelectResolution.getWidth() * sizeSelectResolution.getHeight() > size.getWidth() * size.getHeight() ? sizeSelectResolution : size;
    }
}
