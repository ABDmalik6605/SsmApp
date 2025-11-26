package androidx.camera.camera2.internal.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.SelectResolutionQuirk;
import androidx.camera.core.impl.SurfaceConfig;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ResolutionSelector {
    private final SelectResolutionQuirk mSelectResolutionQuirk;

    public ResolutionSelector() {
        this((SelectResolutionQuirk) DeviceQuirks.get(SelectResolutionQuirk.class));
    }

    ResolutionSelector(SelectResolutionQuirk selectResolutionQuirk) {
        this.mSelectResolutionQuirk = selectResolutionQuirk;
    }

    public List<Size> insertOrPrioritize(SurfaceConfig.ConfigType configType, List<Size> list) {
        Size sizeSelectResolution;
        SelectResolutionQuirk selectResolutionQuirk = this.mSelectResolutionQuirk;
        if (selectResolutionQuirk == null || (sizeSelectResolution = selectResolutionQuirk.selectResolution(configType)) == null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(sizeSelectResolution);
        for (Size size : list) {
            if (!size.equals(sizeSelectResolution)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }
}
