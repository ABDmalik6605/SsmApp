package com.google.firebase.iid;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@21.0.0 */
/* loaded from: classes2.dex */
final /* synthetic */ class FcmBroadcastProcessor$$Lambda$4 implements Continuation {
    static final Continuation $instance = new FcmBroadcastProcessor$$Lambda$4();

    private FcmBroadcastProcessor$$Lambda$4() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return Integer.valueOf(TypedValues.CycleType.TYPE_ALPHA);
    }
}
