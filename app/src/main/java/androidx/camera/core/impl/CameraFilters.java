package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class CameraFilters {
    public static final CameraFilter ANY = new CameraFilter() { // from class: androidx.camera.core.impl.-$$Lambda$CameraFilters$ZuDo3E3lrNzxlM0A-lYf31R2U50
        @Override // androidx.camera.core.CameraFilter
        public final List filter(List list) {
            return CameraFilters.lambda$static$0(list);
        }

        @Override // androidx.camera.core.CameraFilter
        public /* synthetic */ Identifier getIdentifier() {
            return CameraFilter.DEFAULT_ID;
        }
    };
    public static final CameraFilter NONE = new CameraFilter() { // from class: androidx.camera.core.impl.-$$Lambda$CameraFilters$hc8RA3EMhzDkigyjc4Jt28tXy_o
        @Override // androidx.camera.core.CameraFilter
        public final List filter(List list) {
            return Collections.emptyList();
        }

        @Override // androidx.camera.core.CameraFilter
        public /* synthetic */ Identifier getIdentifier() {
            return CameraFilter.DEFAULT_ID;
        }
    };

    static /* synthetic */ List lambda$static$0(List list) {
        return list;
    }

    private CameraFilters() {
    }
}
